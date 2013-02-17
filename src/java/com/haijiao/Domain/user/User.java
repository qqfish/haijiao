/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.user;

import com.haijiao.Domain.file.DataFile;
import java.util.List;

/**
 *
 * @author fish
 */
public class User {
    protected String userId;
    protected List<DataFile> files;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<DataFile> getFiles() {
        return files;
    }

    public void setFiles(List<DataFile> files) {
        this.files = files;
    }
    
}
