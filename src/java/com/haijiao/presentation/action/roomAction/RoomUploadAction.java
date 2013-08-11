/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action.roomAction;

import com.haijiao.presentation.action.SessionAction;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Action("roomUpload")
public class RoomUploadAction extends SessionAction {

    private String data;

    public String execute() {
        InputStream ins = null;
        try {
            File file = new File("D:\\tmp\\test.pdf");
            ins = new ByteArrayInputStream(data.getBytes("ISO-8859-1"));
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.getWriter().write("ok");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RoomUploadAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RoomUploadAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RoomUploadAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ins.close();
            } catch (IOException ex) {
                Logger.getLogger(RoomUploadAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
