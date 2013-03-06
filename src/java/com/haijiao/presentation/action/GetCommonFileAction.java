/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.file.AllFile;
import com.haijiao.Domain.file.FileManager;
import java.util.List;

public class GetCommonFileAction extends SessionAction {
    FileManager fileManager;
    List<AllFile> fileList;
    
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
