/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

public class SessionAction extends ActionSupport implements SessionAware {
    
    private Map session;
    
    @Override
    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }
    
    public Object getSessionValue(String key){
        return this.session.get(key);
    }
    
    public void sessionPutIn(String key, Object name) {
        this.session.put(key, name);
    }
    
    public void sessionClear() {
        this.session.clear();
    }
}
