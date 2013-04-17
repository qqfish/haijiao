/**
 * author: Jerry Zou
 * Date:2013/1/12
 */

package com.haijiao.presentation.action;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;

public class LoginAction extends SessionAction {

    private IUserService userService;
    private ITeacherService teacherService;
    private IStudentService studentService;
    private String email;
    private String password;
    
    @Override
    public String execute() throws Exception {
        String userType = userService.confirmLogin(email, password);
        if(userType == null){
            this.putIn("message",this.getText("loginFail"));
            return INPUT;
        } else {
            if(userType.equals("teacher")){
                System.out.println(userType);
                System.out.println(email);
                System.out.println(password);
                Teacher theTeacher = teacherService.getTeacherByEmail(email);
                this.putIn("teacher", theTeacher);
            } else if (userType.equals("student")){
                Student theStudent = studentService.getStudentByEmail(email);
                this.putIn("student", theStudent);
            }
            User theUser = userService.getUserByEmail(email);
            userService.setStatus(email, User.Status.onlineAndAvailable);
            this.putIn("user", theUser);
            this.putIn("login", true);
            this.putIn("userType", userType);
            this.putIn("email",email);
            this.putIn("message",this.getText("loginsuccess"));
            return SUCCESS;
        }
        
    }
    
    @Override
    public void validate(){
        if(email.isEmpty() || email.trim().length()==0){
            this.putIn("message",this.getText("emailNull"));
            this.addActionError(null);
        }
        else if(password.isEmpty() || password.trim().length()==0){
            this.putIn("message",this.getText("passwordNull"));
            this.addActionError(null);
        }
        else if(password.trim().length()<6){
            this.putIn("message",this.getText("passwordShort"));
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