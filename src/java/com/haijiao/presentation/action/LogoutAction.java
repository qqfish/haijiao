/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

public class LogoutAction extends SessionAction {
    @Override
    public String execute(){
        this.sessionClear();
        this.putIn("message", "退出成功");
        return SUCCESS;
    }
}
