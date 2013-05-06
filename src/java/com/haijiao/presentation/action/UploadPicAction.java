/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.config;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 *
 * @author fish
 */
public class UploadPicAction extends SessionAction {

    private static final long serialVersionUID = 572146812454l;
    private static final int BUFFER_SIZE = 16 * 1024;
    private File picFile;
    private String contentType;
    private String fileName;
    private String imageFileName;
    private String caption;
    private IUserService userService;

    private static void copy(File src, File dst) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                while (in.read(buffer) > 0) {
                    out.write(buffer);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getExtention(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
    }

    @Override
    public String execute() {
        imageFileName = new Date().getTime() + getExtention(fileName);
        String email = (String) this.getSessionValue("email");
        if(email == null)
            return INPUT;
        String locFilePath = config.userHome + "/" + config.imageFolder;
        File folder = new File(locFilePath);
        for(int i = 0; i < folder.listFiles().length; i++){
            folder.listFiles()[i].delete();
        }
        locFilePath += "/" + imageFileName;
        File locFile = new File(locFilePath);
        copy(picFile, locFile);
        userService.setPicUrl(email, locFilePath);
        return SUCCESS;
    }

    public void setPicFileContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setPicFileFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPicFile(File picFile) {
        this.picFile = picFile;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
