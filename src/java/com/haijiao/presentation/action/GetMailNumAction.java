/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IMailService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */

@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Action("getMailNum")
public class GetMailNumAction extends SessionAction{
    @Resource
    IMailService mailService;
    
    @Override
    public String execute(){
        String email = (String)this.getSessionValue("email");
        if(email != null){
            HttpServletResponse response = ServletActionContext.getResponse();
            int num = mailService.getUnreadMailNum(email);
            try {
                response.getWriter().write(String.valueOf(num));
            } catch (IOException ex) {
                Logger.getLogger(GetMailNumAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }
    
}
