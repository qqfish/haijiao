/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IUserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.annotation.Resource;
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
    @Result(name="success", type="stream", params={
                "contentType","application/octet-stream",
                "inputName","inputStream",
                "contentDisposition","attachment;filename=\"${downloadFileName}\"",
                "bufferSize","4096"}),
    @Result(name="input", type="redirect",location="index.action?tab=file")
})
public class DownloadAction extends SessionAction {
    @Resource
    private IUserService userService;
    private String downloadFileName;
    private String src;
    
    public String execute(){
        String email = (String) this.getSessionValue("email");
        String path = userService.download(email, src, downloadFileName);
        System.out.println(path);
        if(path == null)
            return INPUT;
        return SUCCESS;
    }
    
    public InputStream getInputStream() throws Exception {
        String email = (String) this.getSessionValue("email");
        String path = userService.download(email, src, downloadFileName);
        return new FileInputStream(new File(path));   
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    
}
