/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.config;
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
public class UploadPicAction extends SessionAction{
    private static final int BUFFER_SIZE = 16 * 1024;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private IUserService userService;
    
    @Override
    public String execute(){
        try{
            FileInputStream in = new FileInputStream(upload);
            String email = (String) this.getSessionValue("email");
            if(email == null){
                return "error";
            }
            String path = config.userHome + "/" + email + "/" + config.imageFolder;
            File folder = new File(ServletActionContext.getServletContext().getRealPath("/") + path);
            if(!folder.exists()){
                folder.mkdirs();
            } else if(!folder.isDirectory()){
                folder.delete();
                folder.mkdirs();
            }
            for(int i = 0; i < folder.listFiles().length; i++){
                folder.listFiles()[i].delete();
            }
            path = path + "/" + uploadFileName;
            File imageFile = new File(ServletActionContext.getServletContext().getRealPath("/") + path);
            System.out.println(ServletActionContext.getServletContext().getRealPath("/"));
            if(!imageFile.exists()){
                imageFile.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(imageFile);
            byte[] b = new byte[BUFFER_SIZE];
            int len = 0;
            while((len=in.read(b))>0){  
                out.write(b,0,len);
            }
            out.close();
            userService.setPicUrl(email, path);
            return SUCCESS;
        }
        catch(FileNotFoundException ex){
            Logger.getLogger(UploadPicAction.class.getName()).log(Level.SEVERE, null, ex);
            return INPUT;
        } catch (IOException ex) {
            Logger.getLogger(UploadPicAction.class.getName()).log(Level.SEVERE, null, ex);
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

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    
}
