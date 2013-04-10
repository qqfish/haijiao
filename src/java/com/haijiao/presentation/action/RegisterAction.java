/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IUserService;

public class RegisterAction extends SessionAction{
    private String email;
    private String password1;
    private String password2;
    private String userType;
    private IUserService userService;
    
    @Override
    public String execute(){
        if(!userService.confirmExist(email)){
            userService.register(email, password1, userType);
            User theUser = userService.getUserByEmail(email);
            this.putIn("user", theUser);
            this.putIn("userType", userType);
            this.putIn("login", true);
            this.putIn("email", email);
            this.putIn("message", this.getText("registerSuccess"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("userExist"));
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
    
    public void validate() {
        if(email==null || email.trim().length()==0){
            this.addFieldError("email", this.getText("emailNull"));
        }
        if(password1==null || password1.trim().length()==0){
            this.addFieldError("password1", this.getText("passwordNull"));
        }
        else if(password1.trim().length()<6){
            this.addFieldError("password1", this.getText("passwordShort"));
        }
        if(password2==null || password2.trim().length()==0){
            this.addFieldError("password2", this.getText("passwordNull"));
        }
        else if(password2==null || password2.trim().length()<6){
            this.addFieldError("password2", this.getText("passwordShort"));
        }
        else if(!password1.trim().equals(password2.trim())){
            this.addFieldError("password2", this.getText("passwordNotEqual"));
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

}
