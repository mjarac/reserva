package cl.nativ.operativa.reserva.dao.interfac;

import java.util.HashMap;
import java.util.List;

/**
 * Created by marcos.jara on 27-03-2015.
 */
public interface IAuthorizationDao {
    public List<Object[]> getPrivileges(HashMap<String, String> params);
}
