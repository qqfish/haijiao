/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action.roomAction;

import com.haijiao.SupportService.service.IUserService;
import com.haijiao.presentation.action.RequestSessionAction;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.annotation.Resource;
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
 * @author fish
 */
@Controller
@ParentPackage("haijiao")
@Namespace("/")
@InterceptorRefs({
    @InterceptorRef("LoginCheckerStack")
})
@Results({
    @Result(name = "success", type = "stream", params = {
        "contentType", "application/octet-stream",
        "inputName", "inputStream",
        "contentDisposition", "attachment;filename=\"${downloadFileName}\"",
        "bufferSize", "4096"}),
    @Result(name = "input", type = "chain", location = "index")
})
@Action("downloadPDF")
public class DownloadPDFAction extends RequestSessionAction {

    private String downloadFileName;
    private String path;

    public String execute() throws UnsupportedEncodingException {
        downloadFileName = URLEncoder.encode(path.substring(path.lastIndexOf("/")), "UTF-8");
        System.out.println(path);
        return SUCCESS;
    }

    public InputStream getInputStream() throws Exception {
        return new FileInputStream(new File(path));
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
}
