/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.config;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.apache.commons.codec.binary.Base64;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */
@Controller
@ParentPackage("haijiao")
@Namespace("/")
@InterceptorRefs({
    @InterceptorRef("LoginCheckerStack"),
    @InterceptorRef(value = "fileUpload", params = {
        "allowedTypes", "image/jpg,image/png,image/gif,image/jpeg",
        "maximumSize", "1025956"
    })
})
@Action("uploadPic")
@Results({
    @Result(name = "input", type = "redirect", location = "toChangeInfo.action"),
    @Result(name = "error", type = "redirect", location = "index.action"),
    @Result(name = "success", type = "redirect", location = "toChangeInfo.action")
})
public class UploadPicAction extends SessionAction {

    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    @Resource
    private IUserService userService;

    @Override
    public String execute() {
        System.out.println(uploadContentType);
        try {
            FileInputStream in = new FileInputStream(upload);
            String email = (String) this.getSessionValue("email");
            if (email == null) {
                this.sessionPutIn("nextPageMessage", "请先登陆");
                return "error";
            }
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int len = 0;
            while ((len = in.read()) != -1) {
                out.write(len);
            }
            byte[] b = out.toByteArray();
            out.close();
            String datauri = "data:" + uploadContentType + ";base64," + Base64.encodeBase64String(b);
            userService.setPicUrl(email, datauri);
            this.sessionPutIn("nextPageMessage", "上传完成");
            return SUCCESS;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadPicAction.class.getName()).log(Level.SEVERE, null, ex);
            this.sessionPutIn("nextPageMessage", "没有找到对应文件");
            return INPUT;
        } catch (IOException ex) {
            Logger.getLogger(UploadPicAction.class.getName()).log(Level.SEVERE, null, ex);
            this.sessionPutIn("nextPageMessage", "上传出错");
            return INPUT;
        }
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
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
