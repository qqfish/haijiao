/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author hp
 */
public class UploadProcessAction extends SessionAction{
    String process;
    
    @Override
    public String execute(){
        System.out.println("in");
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            System.out.println((String) this.getSessionValue("currentProcess"));
            response.getWriter().write((String) this.getSessionValue("currentProcess"));
        } catch (IOException ex) {
            Logger.getLogger(UploadProcessAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
