package cl.nativ.operativa.reserva.servicio.impl;

import cl.nativ.operativa.reserva.assembler.AuthorizationAssembler;
import cl.nativ.operativa.reserva.dao.imp.AutenticacionDaoImpl;
import cl.nativ.operativa.reserva.dao.imp.AuthorizationDaoImpl;
import cl.nativ.operativa.reserva.resource.Privilegio;
import cl.nativ.operativa.reserva.resource.Usuario;
import cl.nativ.operativa.reserva.servicio.interfac.IAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by marcos.jara on 26-03-2015.
 */
public class AuthorizationServiceImpl implements IAuthorizationService {
    private final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);
    private AutenticacionDaoImpl autenticacionDao;
    private AuthorizationDaoImpl authorizationDao;

    @Override
    public Usuario findByLogin(String identificador) {
        logger.debug("AuthorizationServiceImpl - findByLogin: "+identificador);
        return AuthorizationAssembler.toResourceUsuario(autenticacionDao.findByLogin(identificador));
    }

    @Override
    public ArrayList<Privilegio> getPrivileges(HashMap<String, String> params) {
        return AuthorizationAssembler.toResourcePrivileges(authorizationDao.getPrivileges(params));
    }

    public AutenticacionDaoImpl getAutenticacionDao() {
        return autenticacionDao;
    }

    public void setAutenticacionDao(AutenticacionDaoImpl autenticacionDao) {
        this.autenticacionDao = autenticacionDao;
    }

    public AuthorizationDaoImpl getAuthorizationDao() { return authorizationDao; }

    public void setAuthorizationDao(AuthorizationDaoImpl authorizationDao) { this.authorizationDao = authorizationDao; }
}
