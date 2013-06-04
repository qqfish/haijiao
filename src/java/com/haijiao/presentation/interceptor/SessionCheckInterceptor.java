/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.interceptor;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import java.util.Map;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;

public class SessionCheckInterceptor implements Interceptor {
    //设置参数  

    private String sessionAttribute;
    private String reloginResult;
    private IUserService userService;
    private ITeacherService teacherService;
    private IStudentService studentService;


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
            Cookie[] cookies = ServletActionContext.getRequest().getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("user")) {
                        String email = cookie.getValue();
                        User theUser = userService.getUserByEmail(email);
                        String userType = theUser.getUserType();
                        userService.setStatus(email, User.Status.onlineAndAvailable);
                        userService.setActiveDate(email);
                        session.put("name", theUser.getName());
                        session.put("userType", userType);
                        session.put("email",email);
                        if(userType.equals("teacher")){
                            session.put("todayClazz", teacherService.getTodayClasses(email));
                        } else {
                            session.put("todayClazz", studentService.getTodayClasses(email));
                        }
                        String resultCode = invocation.invoke();
                        return resultCode;
                    }
                }
            }
            return reloginResult;
        }
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }
    
}
