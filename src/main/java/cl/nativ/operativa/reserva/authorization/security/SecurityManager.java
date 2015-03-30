package cl.nativ.operativa.reserva.authorization.security;

/**
 * Created by marcos.jara on 25-03-2015.
 */
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

public class SecurityManager  extends DefaultWebSecurityManager {
    public SecurityManager(){
        super();
    }

    public void setActiveSessionsCacheName(String activeSessionsCacheName) {
        SessionManager sessionManager = this.getSessionManager();
        ((CachingSessionDAO)((DefaultSessionManager)sessionManager).getSessionDAO()).setActiveSessionsCacheName(activeSessionsCacheName);
    }

}
