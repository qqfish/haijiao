/**
 * author: Jerry Zou
 * Date:2013/1/12
 */

package com.haijiao.presentation.action;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IUserService;

public class LoginAction extends SessionAction {

    private IUserService userService;
    private String email;
    private String password;
    
    @Override
    public String execute() throws Exception {
        String userType = userService.confirmLogin(email, password);
        if(userType == null || email==null || email.trim().length()==0){
            return INPUT;
        } else {
            User theUser = userService.getUserByEmail(email);
            this.putIn("user", theUser);
            this.putIn("email", email);
            this.putIn("login", true);
            this.putIn("userType", userType);
            this.putIn("message",this.getText("loginsuccess"));
            return SUCCESS;
        }
        
    }
    
    @Override
    public void validate(){
        if(email==null || email.trim().length()==0){
            this.addFieldError("email", this.getText("emailNull"));
        }
        if(password==null || password.trim().length()==0){
            this.addFieldError("password", this.getText("passwordNull"));
        }
        else if(password.trim().length()<6){
            this.addFieldError("password", this.getText("passwordShort"));
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
}