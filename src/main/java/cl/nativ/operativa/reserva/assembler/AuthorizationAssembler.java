package cl.nativ.operativa.reserva.assembler;

import cl.nativ.operativa.reserva.resource.Permiso;
import cl.nativ.operativa.reserva.resource.Privilegio;
import cl.nativ.operativa.reserva.resource.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos.jara on 26-03-2015.
 */
public class AuthorizationAssembler {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationAssembler.class);

    public static Usuario toResourceUsuario(List<Object[]> users){
        logger.debug("toResourceUsuario");
        Usuario usuario = new Usuario();
        usuario.setRut(String.valueOf(users.get(0)[6]));
        usuario.setUsername(String.valueOf(users.get(0)[4]));
        return usuario;
    }

    public static ArrayList<Privilegio> toResourcePrivileges(List<Object[]> privileges){
        ArrayList<Privilegio> privilegios = new ArrayList<Privilegio>();

        String codigo_ant = "";
        Privilegio privilegio = null;
        for(Object[] p: privileges){
            if(!codigo_ant.trim().equals(String.valueOf(p[0]))) {
                if(privilegio != null) privilegios.add(privilegio);
                privilegio = new Privilegio();
                privilegio.setCodigo(String.valueOf(p[0]));
                privilegio.setGrupoId(String.valueOf(p[1]));
                privilegio.setNombreFacultad(String.valueOf(p[2]));
                privilegio.setTipo(String.valueOf(p[3]));
                codigo_ant = String.valueOf(p[0]);
            }
            Permiso permiso = new Permiso();
            permiso.setNombre(String.valueOf(p[4]));
            permiso.setDescripcion(String.valueOf(p[5]));
            privilegio.addPermiso(permiso);
        }
        privilegios.add(privilegio);

        return privilegios;
    }
}
