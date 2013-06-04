/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.file.AllFile;
import com.haijiao.Domain.file.FileManager;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("haijiao")
@Namespace("/")
@InterceptorRefs({  
    @InterceptorRef("LoginCheckerStack")  
})  
@Action("getCommonFile")
@Results({
    @Result(name="success",location="fileList.jsp"),
    @Result(name="unconnected",location="error.jsp")
})
public class GetCommonFileAction extends SessionAction {
    private FileManager fileManager;
    private List<AllFile> fileList;
    
    @Override
    public String execute(){
       fileList = fileManager.getFileList();
       return SUCCESS;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public List<AllFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<AllFile> fileList) {
        this.fileList = fileList;
    }
    
}
