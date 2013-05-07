/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author hp
 */
public class UploadAction extends SessionAction{
    private static final int BUFFER_SIZE = 16 * 1024;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    
    @Override
    public String execute(){
        try{
            FileInputStream in = new FileInputStream(upload);
            File imageFile = new File(ServletActionContext.getServletContext().getRealPath("/images") + File.separator + uploadFileName);
            System.out.println(imageFile.getPath());
            FileOutputStream out = new FileOutputStream(imageFile);
            byte[] b = new byte[BUFFER_SIZE];
            int len = 0;
            while((len=in.read(b))>0){  
                out.write(b,0,len);
            }
            out.close();
            return SUCCESS;
        }
        catch(FileNotFoundException ex){
            Logger.getLogger(UploadAction.class.getName()).log(Level.SEVERE, null, ex);
            return INPUT;
        } catch (IOException ex) {
            Logger.getLogger(UploadAction.class.getName()).log(Level.SEVERE, null, ex);
            return INPUT;
        }
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    
}
