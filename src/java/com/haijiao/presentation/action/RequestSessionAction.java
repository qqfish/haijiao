/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author hp
 */
public class RequestSessionAction extends ActionSupport implements ServletRequestAware, SessionAware{
    HttpServletRequest request;
    private Map session;
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public Map getSession() {
        return session;
    }
    
    public Object getOutRequest(String key){
        return this.request.getParameter(key);
    }
    
    public Object getOutSession(String key){
        return this.session.get(key);
    }
    
    public void putIn(String key, Object name) {
        this.session.put(key, name);
    }
    
    public void clear() {
        this.session.clear();
    }
}
