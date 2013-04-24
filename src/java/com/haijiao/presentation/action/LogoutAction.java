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
}
