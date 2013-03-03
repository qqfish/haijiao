/**
 * User.java
 * @author fish
 */

package com.haijiao.Domain.bean;
import com.haijiao.Domain.file.DataFile;
import java.util.List;

public class User {
    protected String userId;
    protected String account;
    protected String name;
    protected String userType;
    protected List<DataFile> files;
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<DataFile> getFiles() {
        return files;
    }

    public void setFiles(List<DataFile> files) {
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
}
