/**
 * author: Jerry Zou
 * Date:2013/1/12
 */

package com.haijiao.presentation.action;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Results({
    @Result(name="input",location="/index.jsp"),
    @Result(name="success",type="chain",location="index")
})
public class LoginAction extends SessionAction {
    @Resource
    private IUserService userService;
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IStudentService studentService;
    private String email;
    private String password;
    private String autologin;
    private String errorMessage;
    
    @Override
    public String execute() throws Exception {
        String userType = userService.confirmLogin(email, password);
        if(userType == null){
            errorMessage = "loginFail";
            return INPUT;
        } else {
            if(autologin.equals("on")){
                Cookie username = new Cookie("user",email);
                username.setMaxAge(10*24*60*60);
                username.setPath("/");
                ServletActionContext.getResponse().addCookie(username);
            }
            User theUser = userService.getUserByEmail(email);
            userService.setStatus(email, User.Status.onlineAndAvailable);
            userService.setActiveDate(email);
            this.sessionPutIn("name", theUser.getName());
            this.sessionPutIn("userType", userType);
            this.sessionPutIn("email",email);
//            if(userType.equals("teacher")){
//                this.sessionPutIn("todayClazz", teacherService.getTodayClasses(email));
//            } else {
//                this.sessionPutIn("todayClazz", studentService.getTodayClasses(email));
//            }
            return SUCCESS;
        }
        
    }
    
    @Override
    public void validate(){
        if(email.isEmpty() || email.trim().length()==0){
            errorMessage = "LemailNull";
            this.addActionError(null);
        }
        else if(password.isEmpty() || password.trim().length()==0){
            errorMessage = "LpasswordNull";
            this.addActionError(null);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAutologin() {
        return autologin;
    }

    public void setAutologin(String autologin) {
        this.autologin = autologin;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}