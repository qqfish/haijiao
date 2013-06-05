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
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
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
    @InterceptorRef(value="fileUpload",params={
        "allowedTypes","application/octet-stream,application/x-zip-compressed",
        "maximumSize","1025956"
    })
})
@Results({
    @Result(name="input",type="chain",location="index"),
    @Result(name="error",type="chain",location="index"),
    @Result(name="success",type="chain",location="index")
})
public class FileAction extends SessionAction {

    private static final int BUFFER_SIZE = 16 * 1024;
    @Resource
    private IUserService userService;
    private String src;
    private String dest;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private String downloadFileName;
    private String nextPageMessage;

    public String move() {
        String email = (String) this.getSessionValue("email");
        userService.moveFile(email, src, dest, uploadFileName);
        return SUCCESS;
    }

    public String create() {
        String email = (String) this.getSessionValue("email");
        userService.createGroup(email, dest);
        return SUCCESS;
    }

    public String delete() {
        String email = (String) this.getSessionValue("email");
        userService.deleteGroup(email, dest);
        return SUCCESS;
    }
    
    public String upload() {
        FileInputStream in = null;
        try {
            String email = (String) this.getSessionValue("email");
            in = new FileInputStream(upload);
            if (email == null) {
                nextPageMessage = "请先登陆";
                return "error";
            }
            String path = config.userHome + File.separator + email + File.separator + config.fileFolder;
            File folder = new File(ServletActionContext.getServletContext().getRealPath("/") + path);
            if (!folder.exists()) {
                folder.mkdirs();
            } else if (!folder.isDirectory()) {
                folder.delete();
                folder.mkdirs();
            }
            for (int i = 0; i < folder.listFiles().length; i++) {
                folder.listFiles()[i].delete();
            }
            path = path + "/" + uploadFileName;
            File newFile = new File(ServletActionContext.getServletContext().getRealPath("/") + path);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(newFile);
            byte[] b = new byte[BUFFER_SIZE];
            int len = 0;
            while ((len = in.read(b)) > 0) {
                out.write(b, 0, len);
            }
            out.close();
            if(dest == null)
                dest = "default";
            userService.uploadFile(email, dest, uploadFileName, path);
            return SUCCESS;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(FileAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return INPUT;
    }
    
    public InputStream getInputStream() throws Exception {
        String email = (String) this.getSessionValue("email");
        String path = userService.download(email, src, uploadFileName);
        return new FileInputStream(new File(ServletActionContext.getServletContext().getRealPath("/") + path));   
    }   

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
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

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }
    
}
