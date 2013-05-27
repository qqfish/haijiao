/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.ITeacherService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author hp
 */
public class GetRoomStatusAction extends SessionAction{
    private ITeacherService teacherService;
    
    @Override
    public String execute(){
        String email = (String)this.getSessionValue("email");
        if(email != null){
            HttpServletResponse response = ServletActionContext.getResponse();
            boolean status = teacherService.getRoomStatus(email);
            try {
                response.getWriter().write(String.valueOf(status));
            } catch (IOException ex) {
                Logger.getLogger(GetMailNumAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;     
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }
    
}
