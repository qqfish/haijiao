/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

public class RequestAction extends ActionSupport implements ServletRequestAware{
    
    HttpServletRequest request;
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    public Object getValue(String key){
        return this.request.getParameter(key);
    }

}
