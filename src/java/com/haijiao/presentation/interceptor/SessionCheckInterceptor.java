/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.interceptor;

import java.util.Map;  
import com.opensymphony.xwork2.ActionContext;  
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.Interceptor;  
  
public class SessionCheckInterceptor implements Interceptor{  
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
    public void destroy() { }  
    
    @Override
    public void init() {    }  
    
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {  
        //读取Session  
        Map<String, Object> session = invocation.getInvocationContext().getSession();  
        //判断Session中是否有相应的attribute  
        if (session.containsKey(sessionAttribute)){  
            String resultCode = invocation.invoke();  
            return resultCode;  
        }else{  
            return reloginResult;  
        }         
    }  
}  