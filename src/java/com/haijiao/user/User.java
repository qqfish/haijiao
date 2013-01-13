/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.user;

import com.haijiao.file.File;
import java.util.List;

/**
 *
 * @author fish
 */
public class User {
    private String userId;
    private List<File> files;

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
