/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IUserService;

public class LogoutAction extends SessionAction {
    IUserService userService;
    
    @Override
    public String execute(){
        userService.setStatus((String)(this.getSessionValue("email")), User.Status.offline);
        this.sessionClear();
        this.sessionPutIn("message", "退出成功");
        return SUCCESS;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
