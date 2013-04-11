/**
 * User.java
 * @author fish
 */

package com.haijiao.Domain.bean;
import com.google.gson.Gson;
import com.haijiao.Domain.file.UserFile;
import com.haijiao.Domain.file.UserFileGroup;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table    
@Inheritance(strategy = InheritanceType.JOINED )
public class User extends BaseBean{
    protected String email;      //用户的账号,即Email
    protected String name;      //用户的真实姓名
    protected String userType; //用户的类型："teacher" or "student"
    protected Integer score;     //用户的评分
    protected String password;  //用户密码
    protected int coin;              //该账户中剩下的智慧币
    protected String intro;        //用户的个人简介，显示在个人主页上
    protected String picUrl;      //用户头像的URL
    protected String sex;          //性别
    protected Date birthday;
    protected boolean online;
    
    @OneToMany(mappedBy="commenter",fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    protected List<Comment> commentsToThis; //所有对本用户的评论
    
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name="uid")
    protected List<UserFileGroup> fileGroups;

    public User() {
        commentsToThis = new ArrayList();
        fileGroups = new ArrayList();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
    
    public UserFile getFile(String group, String name){
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

    
    public boolean addFile(String groupName, UserFile file){
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
    
    public void removeFile(String groupName, UserFile file){
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
    
    public void moveFile(String fromGroup, String toGroup, UserFile file){
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
    
    public String toJson(){
        Gson gson = new Gson();
        System.out.println(gson.toJson(this));
        return gson.toJson(this);
    }
}
