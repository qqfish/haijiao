/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.user;

import com.haijiao.Domain.file.File;
import java.util.List;

/**
 *
 * @author fish
 */
public class User {
    protected String userId;
    protected List<File> files;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
    
}
