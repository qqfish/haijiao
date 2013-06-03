/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.interceptor;

import java.util.Map;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.StrutsStatics;

public class SessionCheckInterceptor implements Interceptor {
    //设置参数  

    private String sessionAttribute;
    private String reloginResult;

    public void setSessionAttribute(String sessionAttribute) {
        this.sessionAttribute = sessionAttribute;
    }

    public void setReloginResult(String reloginResult) {
        this.reloginResult = reloginResult;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        //读取Session  
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        //判断Session中是否有相应的attribute  
        if (session.containsKey(sessionAttribute)) {
            String resultCode = invocation.invoke();
            return resultCode;
        } else {
            HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("user"))
                        return cookie.getValue();
                }
            }
            return reloginResult;
        }
    }
}
