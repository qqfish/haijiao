/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IUserService;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Action("resetChangePassword")
@Results({
    @Result(name="success",type="redirect",location="index.action"),
    @Result(name="input",location="/resetPassword.jsp")
})
public class ResetChangePasswordAction extends SessionAction {
    @Resource
    private IUserService userService;
    private String id;
    private String newpwd;
    private String newpwd2;
    private String oldpwd;
    
    public String changePasswordById(){
        if ( !newpwd.equals(newpwd2) ) {
            this.sessionPutIn("nextPageMessage", "两次输入的密码不一致");
            return "input";
        }
        userService.changePasswordById( id, newpwd );
        this.sessionPutIn("nextPageMessage", "修改密码成功");
        return SUCCESS;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getNewpwd2() {
        return newpwd2;
    }

    public void setNewpwd2(String newpwd2) {
        this.newpwd2 = newpwd2;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }
}
