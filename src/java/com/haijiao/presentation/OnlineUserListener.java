/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.SpringContext;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author hp
 */
public class OnlineUserListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String email = (String) se.getSession().getAttribute("email");
        if (email != null) {
            IUserService userService = (IUserService) SpringContext.getContext().getBean("userServiceImpl");
            if (userService.getUserByEmail(email).getUserType().equals("teacher")) {
                ITeacherService teacherService = (ITeacherService) SpringContext.getContext().getBean("teacherServiceImpl");
                teacherService.setRoomOccupied(email, null);
            }
            userService.setStatus(email, User.Status.offline);
        }
    }
}
