package cl.nativ.operativa.reserva.servicio.interfac;

import cl.nativ.operativa.reserva.resource.Privilegio;
import cl.nativ.operativa.reserva.resource.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by marcos.jara on 26-03-2015.
 */
public interface IAuthorizationService {
    public Usuario findByLogin(String identificador);
    public ArrayList<Privilegio> getPrivileges(HashMap<String, String> params);
}
