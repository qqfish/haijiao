/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IMailService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author hp
 */
public class GetMailNumAction extends SessionAction{
    IMailService mailService;
    
    @Override
    public String execute(){
        String email = (String)this.getSessionValue("email");
        HttpServletResponse response = ServletActionContext.getResponse();
        int num = mailService.getUnreadMailNum(email);
        try {
            response.getWriter().write(String.valueOf(num));
        } catch (IOException ex) {
            Logger.getLogger(GetMailNumAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }
    
}
