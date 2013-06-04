/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;
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
    @Result(name="success",location="/index.jsp")
})
public class LogoutAction extends SessionAction {
    @Resource
    private IUserService userService;
    
    @Override
    public String execute(){
        String email = (String)this.getSessionValue("email");
        if(email != null){
            Cookie username = new Cookie("user","");
            username.setMaxAge(1);
            username.setPath("/");
            ServletActionContext.getResponse().addCookie(username);
            userService.setStatus(email, User.Status.offline);
            this.sessionClear();
            this.sessionPutIn("message", "退出成功");
        }
        else
            this.sessionPutIn("message", "已退出");
        return SUCCESS;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
