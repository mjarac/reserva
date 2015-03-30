package cl.nativ.operativa.reserva.dao.imp;

import cl.nativ.operativa.reserva.dao.interfac.IAutenticacionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by marcos.jara on 26-03-2015.
 */
public class AutenticacionDaoImpl implements IAutenticacionDao{
    private final Logger logger = LoggerFactory.getLogger(AutenticacionDaoImpl.class);
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Object[]> findByLogin(String identificador) {
        logger.debug("findByLogin DAO: "+identificador);
        Query query = entityManager.createNativeQuery("SELECT aut_usuario.id, aut_usuario.activo, aut_usuario.apellido_materno, aut_usuario.apellido_paterno, aut_usuario.identificador,\n" +
                    "aut_usuario.nombres,\n" +
                    "aut_usuario.rut \n" +
                    "FROM authorization.aut_usuario WHERE rut = '"+identificador+"'");
        return query.getResultList();
    }

    /*public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }*/
}
