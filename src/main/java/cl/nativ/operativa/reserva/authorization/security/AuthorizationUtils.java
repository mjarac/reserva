package cl.nativ.operativa.reserva.authorization.security;

import cl.nativ.operativa.reserva.resource.Agrupacion;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by marcos.jara on 25-03-2015.
 */
public class AuthorizationUtils {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationUtils.class);

    public AuthorizationUtils() {}

    public static String obtenerAtributoUsuario(String nombreAtributo)
    {
        Subject subject = SecurityUtils.getSubject();
        logger.debug("Nombre atributo entrada: " + nombreAtributo);
        logger.debug("Principals: " + subject.getPrincipals());
        return (String)((Map)subject.getPrincipals().asList().get(1)).get(nombreAtributo);
    }

    @Deprecated
    public static List<String> obtenerOrganismosDeUnPermiso(String permiso)
    {
        return obtenerCodigosAgrupacionDeUnPermiso(permiso);
    }

    public static List<String> obtenerCodigosAgrupacionDeUnPermiso(String permiso)
    {
        Subject subject = SecurityUtils.getSubject();


        List<String> agrupaciones = new ArrayList();


        Map<Agrupacion, String> mapaAgrupacionesPermisos = obtenerAgrupacionesPermisos(subject.getPrincipals());
        if (mapaAgrupacionesPermisos != null)
        {
            for (Agrupacion agrupacion : mapaAgrupacionesPermisos.keySet())
            {
                String permisosDeLaAgrupacion = (String)mapaAgrupacionesPermisos.get(agrupacion);
                if (permisosDeLaAgrupacion.contains(permiso)) {
                    agrupaciones.add(agrupacion.getCodigo());
                }
            }
            logger.debug("Mapa de agrupaciones no es null...");
            return agrupaciones;
        }
        logger.debug("Mapa de agrupaciones es null...");
        return null;
    }

    public static List<Agrupacion> obtenerAgrupacionesDeUnPermiso(String permiso)
    {
        Subject subject = SecurityUtils.getSubject();


        List<Agrupacion> agrupaciones = new ArrayList();


        Map<Agrupacion, String> mapaAgrupacionesPermisos = obtenerAgrupacionesPermisos(subject.getPrincipals());
        if (mapaAgrupacionesPermisos != null)
        {
            for (Agrupacion agrupacion : mapaAgrupacionesPermisos.keySet())
            {
                String permisosDeLaAgrupacion = (String)mapaAgrupacionesPermisos.get(agrupacion);
                if (permisosDeLaAgrupacion.contains(permiso)) {
                    agrupaciones.add(agrupacion);
                }
            }
            logger.debug("Mapa de agrupaciones no es null...");
            return agrupaciones;
        }
        logger.debug("Mapa de agrupaciones es null...");
        return null;
    }

    private static Map<Agrupacion, String> obtenerAgrupacionesPermisos(PrincipalCollection principalCollection)
    {
        for (Realm realm : ((RealmSecurityManager)SecurityUtils.getSecurityManager()).getRealms())
        {
            logger.debug("REALM: " + realm.getName());
            if ((realm instanceof AuthorizationRealm))
            {
                logger.debug("Es instancia de AuthorizationRealm...");
                return ((AuthorizationRealm)realm).getAuthorizationForUser(principalCollection).getObjetosOrganismosPermisos();
            }
        }
        return null;
    }
}
