/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.SpringContext;
import com.haijiao.SupportService.service.IUserService;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author hp
 */
public class OnlineUserListener implements HttpSessionListener{
    
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String email = (String) se.getSession().getAttribute("email");
        System.out.println(email);
        if(email != null){
            IUserService userService = (IUserService) SpringContext.getContext().getBean("userServiceImpl");
            userService.setStatus(email, User.Status.offline);
        }
    }
    
}
