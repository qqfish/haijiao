/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;

public class IndexAction extends SessionAction {
    User user;
    
    @Override
    public String execute() throws Exception {
        user = (User)this.getValue("user");
        if(user != null){
            
            if(user.getUserType().equals("teacher")){
                return "teacher";
            } else {
                return "student";
            }
        } else {
            return SUCCESS;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
