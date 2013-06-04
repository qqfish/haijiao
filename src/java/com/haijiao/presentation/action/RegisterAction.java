/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Results({
    @Result(name="success",location="/register.jsp"),
    @Result(name="input",location="/index.jsp")
})
public class RegisterAction extends SessionAction{
    private String email;
    private String password1;
    private String password2;
    private String userType;
    @Resource
    private IUserService userService;
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IStudentService studentService;
    private String errorMessage;
    
    @Override
    public String execute(){
        if(!userService.confirmExist(email)){
            userService.register(email, password1, userType);
            User theUser = userService.getUserByEmail(email);
            this.sessionPutIn("name", theUser.getName());
            this.sessionPutIn("userType", userType);
            this.sessionPutIn("email",email);
            if(userType.equals("teacher")){
                this.sessionPutIn("todayClazz", teacherService.getTodayClasses(email));
            } else {
                this.sessionPutIn("todayClazz", studentService.getTodayClasses(email));
            }
            userService.setStatus(email, User.Status.onlineAndAvailable);
            return SUCCESS;
        } else {
            errorMessage = "userExist";
            return INPUT;
        }
    }
    
    public String student(){
        userType = "student";
        return execute();
    }
    
    public String teacher(){
        userType = "teacher";
        return execute();
    }
    
    @Override
    public void validate() {
        if(email==null || email.trim().length()==0){
            errorMessage = "emailNull";
            this.addActionError(null);
        }
        else if(password1==null || password1.trim().length()==0){
            errorMessage = "passwordNull";
            this.addActionError(null);
        }
        else if(password1.trim().length()<6){
            errorMessage = "passwordShort";
            this.addActionError(null);
        }
        else if(password2==null || password2.trim().length()==0){
            errorMessage = "passwordNull";
            this.addActionError(null);
        }
        else if(password2==null || password2.trim().length()<6){
            errorMessage = "passwordShort";
            this.addActionError(null);
        }
        else if(!password1.trim().equals(password2.trim())){
            errorMessage = "passwordNotEqual";
            this.addActionError(null);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
