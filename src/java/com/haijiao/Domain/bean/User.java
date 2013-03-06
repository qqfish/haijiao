/**
 * User.java
 * @author fish
 */

package com.haijiao.Domain.bean;
import com.haijiao.Domain.file.DataFile;
import com.haijiao.Domain.file.UserFileGroup;
import java.util.ArrayList;
import java.util.List;

public class User {
    protected String userId;
    protected String account;
    protected String name;
    protected String userType;
    protected int coin;              //该账户中剩下的智慧币
    protected List<Comment> comments;
    protected List<UserFileGroup> fileGroups;

    public User() {
        comments = new ArrayList();
        fileGroups = new ArrayList();
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
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
    
    public void addFileGroup(String groupName){
        UserFileGroup group = new UserFileGroup(groupName);
        fileGroups.add(group);
    }
    
    public DataFile getFile(String group, String name){
        UserFileGroup groupResult = null;
        for(int i = 0; i < fileGroups.size(); i++){
            if(fileGroups.get(i).getGroupName().equals(group)){
                groupResult = fileGroups.get(i);
                break;
            }
        }
        if(groupResult == null){
            return null;
        }
        
        return groupResult.getFile(name);
    }

    
    public boolean addFile(String groupName, DataFile file){
        UserFileGroup group = null;
        for(int i = 0; i < fileGroups.size(); i++){
            if(fileGroups.get(i).getGroupName().equals(groupName)){
                group = fileGroups.get(i);
                break;
            }
        }
        if(group == null){
            return false;
        }
        
        group.addFile(file);
        return true;
    }
    
    public void removeFile(String groupName, DataFile file){
        UserFileGroup group = null;
        for(int i = 0; i < fileGroups.size(); i++){
            if(fileGroups.get(i).getGroupName().equals(groupName)){
                group = fileGroups.get(i);
                break;
            }
        }
        if(group == null){
            return;
        }
        
        group.removeFile(file);
    }
    
    public void moveFile(String fromGroup, String toGroup, DataFile file){
        UserFileGroup from = null;
        UserFileGroup to = null;
        for(int i = 0; i < fileGroups.size(); i++){
            if(fileGroups.get(i).getGroupName().equals(fromGroup)){
                from = fileGroups.get(i);
            }
            if(fileGroups.get(i).getGroupName().equals(toGroup)){
                to = fileGroups.get(i);
            }
        }
        if(from == null || to == null){
            return;
        }
        
        from.removeFile(file);
        to.addFile(file);
    }

    public List<UserFileGroup> getFileGroups() {
        return fileGroups;
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
