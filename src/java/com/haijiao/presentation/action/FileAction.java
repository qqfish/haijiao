/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.config;
import com.haijiao.global.Converter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
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
    @InterceptorRef("LoginCheckerStack")
})
@Results({
    @Result(name = "input", type = "redirect", location = "index.action?tab=file"),
    @Result(name = "error", type = "redirect", location = "index.action?tab=file"),
    @Result(name = "success", type = "redirect", location = "index.action?tab=file")
})
public class FileAction extends SessionAction {

    private static final int BUFFER_SIZE = 16 * 1024;
    @Resource
    private IUserService userService;
    private String src;
    private String dest;
    private String name;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;

    public String move() {
        String email = (String) this.getSessionValue("email");
        userService.moveFile(email, src, dest, uploadFileName);
        return SUCCESS;
    }

    public String create() {
        String email = (String) this.getSessionValue("email");
        userService.createGroup(email, dest);
        this.sessionPutIn("nextPageMessage", "创建成功");
        return SUCCESS;
    }

    public String deleteGroup() {
        String email = (String) this.getSessionValue("email");
        userService.deleteGroup(email, dest);
        this.sessionPutIn("nextPageMessage", "删除成功");
        return SUCCESS;
    }

    public String deleteFile() {
        String email = (String) this.getSessionValue("email");
        userService.deleteFile(email, dest, name);
        this.sessionPutIn("nextPageMessage", "删除成功");
        return SUCCESS;
    }

    @Action(value = "upload", interceptorRefs =
            @InterceptorRef(value = "fileUpload", params = {
        "allowedTypes", "application/octet-stream,application/x-zip-compressed",
        "maximumSize", "1025956"
    }))
    public String upload() {
        try {
            String email = (String) this.getSessionValue("email");
            if (email == null) {
                this.sessionPutIn("nextPageMessage", "请先登陆");
                return "error";
            }
            String path = config.userHome + File.separator + email + File.separator + config.fileFolder;
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            } else if (!folder.isDirectory()) {
                folder.delete();
                folder.mkdirs();
            }
            boolean ispdf = uploadFileName.endsWith(".pdf");
            if (!ispdf) {
                uploadFileName = uploadFileName.substring(0, uploadFileName.lastIndexOf(".")) + ".pdf";
            }
            String tmppath = path + File.separator + uploadFileName;
            File newFile = new File(tmppath);
            for (int i =0;;i ++) {
                if (!newFile.exists()) {
                    newFile.createNewFile();
                    break;
                } else {
                    tmppath = path + File.separator + String.valueOf(i) + uploadFileName;
                    newFile = new File(tmppath);
                }
            }
            path = tmppath;
            if (ispdf) {
                FileInputStream in = new FileInputStream(upload);
                FileOutputStream out = new FileOutputStream(newFile);
                byte[] b = new byte[BUFFER_SIZE];
                int len = 0;
                while ((len = in.read(b)) > 0) {
                    out.write(b, 0, len);
                }
                out.close();
            } else {
                Converter.getDocumentConverter().convert(upload, newFile);
            }
            userService.uploadFile(email, dest, uploadFileName, path);
            this.sessionPutIn("nextPageMessage", "上传成功");
            return SUCCESS;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return INPUT;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
