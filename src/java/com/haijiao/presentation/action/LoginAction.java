/**
 * author: Jerry Zou
 * Date:2013/1/12
 */

package com.haijiao.presentation.action;
import com.haijiao.Domain.service.IStudentService;

public class LoginAction extends SessionAction {

    private IStudentService studentService;
    private String account;
    private String password;
    
    @Override
    public String execute() throws Exception {
        if(studentService.confirmLogin(account, password)){
            this.putIn("username", account);
            this.putIn("login", true);
            return "success";
        }
        return "login";
    }
    
    public void validate(){
        if(account==null || account.trim().length()==0){
            this.addFieldError("account", this.getText("accountNull"));
        }
        if(password==null || password.trim().length()==0){
            this.addFieldError("password", this.getText("passwordNull"));
        }
        else if(password.trim().length()<6){
            this.addFieldError("password", this.getText("passwordShort"));
        }
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }
}