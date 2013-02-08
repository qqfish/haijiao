/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport{
    private String account;
    private String password1;
    private String password2;
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
    
    public void validate() {
        if(account==null || account.trim().length()==0){
            this.addFieldError("account", this.getText("accountNull"));
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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
    
}
