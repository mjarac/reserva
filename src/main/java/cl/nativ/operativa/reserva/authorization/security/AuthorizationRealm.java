package cl.nativ.operativa.reserva.authorization.security;

import cl.nativ.operativa.reserva.resource.Agrupacion;
import cl.nativ.operativa.reserva.resource.Permiso;
import cl.nativ.operativa.reserva.resource.Privilegio;
import cl.nativ.operativa.reserva.controller.AuthorizationController;
import cl.nativ.operativa.reserva.resource.Usuario;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by marcos.jara on 25-03-2015.
 */
@Component
public class AuthorizationRealm extends AuthorizingRealm {
    private final Logger logger = LoggerFactory.getLogger(AuthorizationRealm.class);
    @Autowired
    private AuthorizationController authorizationController;
    @Value("${moduleKey}")
    private String moduleKey;

    @Override
    protected SimpleAuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        this.logger.debug("Accediendo a datos de autorizacion");
        this.logger.debug("Username: " + principalCollection.getPrimaryPrincipal());

        Subject subject = SecurityUtils.getSubject();
        AuthorizationInfo info = new AuthorizationInfo();
        HashMap<Agrupacion, String> objetosOrganismosPermisos = new HashMap();
        HashMap<String, String> datosEntradaPrivilegios = new HashMap();
        datosEntradaPrivilegios.put("username", subject.getPrincipal().toString());
        datosEntradaPrivilegios.put("moduleKey", this.moduleKey);
        ArrayList<Privilegio> privilegios = authorizationController.getPrivileges(datosEntradaPrivilegios);

        this.logger.debug("Privilegios: " + privilegios.toString());
        this.logger.debug("Is Empty:" + privilegios.isEmpty());
        for (Privilegio privilegio : privilegios)
        {
            String permisosConcatenados = "";
            boolean esElPrimerPermiso = true;
            for (Permiso permiso : privilegio.getPermisos())
            {
                info.addStringPermission(permiso.getNombre());
                if (esElPrimerPermiso)
                {
                    permisosConcatenados = permisosConcatenados + permiso.getNombre();
                    esElPrimerPermiso = false;
                }
                else
                {
                    permisosConcatenados = permisosConcatenados + ";" + permiso.getNombre();
                }
            }
            objetosOrganismosPermisos.put(new Agrupacion(privilegio.getCodigo(), privilegio.getGrupoId(), privilegio.getNombreFacultad(), privilegio.getTipo()), permisosConcatenados);
        }
        this.logger.debug("PERMISOS SETEADOS: " + info.getStringPermissions());
        this.logger.debug("SETEANDO MAPA EN AuthorizationInfo!");
        info.setObjetosOrganismosPermisos(objetosOrganismosPermisos);
        if (principalCollection.getPrimaryPrincipal().equals("marcos.jara"))
        {
            info.addStringPermission("botonsaludar:ver");
            info.addStringPermission("recurso:accion:instancia");
            info.addStringPermission("recurso:accion:instancia");
        }
        this.logger.debug("CON NUEVOS PERMISOS AGREGADOS: " + info.getStringPermissions());
        this.logger.debug("VALIDACION DEL NULL...");
        if (!privilegios.isEmpty()) {
            for (String permiso : info.getStringPermissions()) {
                this.logger.debug("LISTA DE PERMISOS: " + permiso);
            }
        } else {
            this.logger.debug("No tiene permisos...");
        }
        this.logger.debug("RECORRIENDO HASHMAP ORGANISMOS");
        String listaOrganismosObjetos = "";
        if (objetosOrganismosPermisos != null) {
            for (Agrupacion key : objetosOrganismosPermisos.keySet())
            {
                listaOrganismosObjetos = listaOrganismosObjetos + ";" + key;
                this.logger.debug("Datos de la clave " + key + ": " + (String)objetosOrganismosPermisos.get(key));
            }
        } else {
            this.logger.debug("El mapaOrganismosPermisos es nulo");
        }
        this.logger.debug("LISTA DE ORGANISMOS: " + listaOrganismosObjetos);
        /*Set<String> roleNames = new LinkedHashSet<String>();
        roleNames.add("USER");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        Set<String> permissions = new LinkedHashSet<String>();
        permissions.add("document:read");
        info.setStringPermissions(permissions);*/

        return info;
    }

    public AuthorizationInfo getAuthorizationForUser(PrincipalCollection principalCollection)
    {
        return (AuthorizationInfo)getAuthorizationInfo(principalCollection);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException
    {
        this.logger.debug("Accediendo a datos de autenticacion...");
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        AuthenticationInfo info = null;
        this.logger.debug("Antes de acceder");
        Usuario usuario = authorizationController.findByLogin(username);
        this.logger.debug("Luego de acceder");
        if (usuario == null || usuario.getUsername() == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        info = new SimpleAuthenticationInfo(usuario.getUsername(), "123456".toCharArray(), getName());
        return info;
    }

    public AuthorizationController getAuthorizationController() {
        return authorizationController;
    }

    public void setAuthorizationController(AuthorizationController authorizationController) {
        this.authorizationController = authorizationController;
    }

}
