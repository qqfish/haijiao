/**
 * User.java
 * @author fish
 */

package com.haijiao.Domain.bean;
import com.haijiao.Domain.file.DataFile;
import com.haijiao.Domain.file.UserFileGroup;
import java.util.ArrayList;
import java.util.List;

public class User extends BaseBean{
    protected String userId;     //在房间中用到？
    protected String email;      //用户的账号,即Email
    protected String name;      //用户的真实姓名
    protected String userType; //用户的类型："teacher" or "student"
    protected Integer score;     //用户的评分
    protected String password;//用户密码
    protected int coin;              //该账户中剩下的智慧币
    protected String intro;        //用户的个人简介，显示在个人主页上
    protected String picUrl;      //用户头像的URL
    protected List<Comment> commentsToThis; //所有对本用户的评论
    protected List<UserFileGroup> fileGroups;

    public User() {
        commentsToThis = new ArrayList();
        fileGroups = new ArrayList();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public List<Comment> getCommentsToThis() {
        return commentsToThis;
    }

    public void setCommentsToThis(List<Comment> commentsToThis) {
        this.commentsToThis = commentsToThis;
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
}
