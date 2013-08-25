/**
 * User.java
 * @author fish
 */

package com.haijiao.Domain.bean;
import com.google.gson.Gson;
import com.haijiao.Domain.file.UserFile;
import com.haijiao.Domain.file.UserFileGroup;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate=true,dynamicInsert=true)
@Table    
@Inheritance(strategy = InheritanceType.JOINED )
public class User extends BaseBean{
    public class Status{
        public static final int offline = 0;
        public static final int onlineAndAvailable = 1;
        public static final int onlineAndBusy = 2;
    }
    protected String email;     //用户的账号,即Email
    protected String name;      //用户的真实姓名
    protected String userType;  //用户的类型："teacher" or "student"
    @Column(columnDefinition="int default 0")
    protected Integer score;    //用户的评分
    @Column(columnDefinition="int default 0")
    protected Integer scoreNum; //评分数
    @Column(columnDefinition="int default 0")
    protected Integer loginNum; //登录数
    protected String password;  //用户密码
    protected int coin;         //该账户中剩下的智慧币
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="intro", columnDefinition="TEXT", nullable=true)
    protected String intro;     //用户的个人简介，显示在个人主页上
    
    @OneToOne
    protected Picture pic;      //用户头像的datauri
    
    protected String sex;       //性别
    @Temporal(TemporalType.DATE)
    protected Date birthday;    //生日
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastActiveDate;//最近活跃时间
    protected int status;       //可选项为Status
    
    @OneToMany(mappedBy = "to")
    protected List<Mail> mailBox;   //收到的私信
    
    @OneToMany
    @JoinColumn(name="uid")
    protected List<UserFileGroup> fileGroups;

    public User() {
        fileGroups = new ArrayList();
        mailBox = new ArrayList<Mail>();
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

    public Picture getPic() {
        return pic;
    }

    public void setPic(Picture pic) {
        this.pic = pic;
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

    public Integer getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(Integer scoreNum) {
        this.scoreNum = scoreNum;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
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

    public Date getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(Date lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Mail> getMailBox() {
        return mailBox;
    }

    public void setMailBox(List<Mail> mailBox) {
        this.mailBox = mailBox;
    }
    
    public void addFileGroup(String groupName){
        UserFileGroup group = new UserFileGroup(groupName);
        fileGroups.add(group);
    }
    
    public void addPersistFileGroup(UserFileGroup group){
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
        //System.out.println(gson.toJson(this));
        return gson.toJson(this);
    }
    
    public String fileGroupsToJson(){
        Gson gson = new Gson();
        System.out.println(gson.toJson(fileGroups));
        return gson.toJson(fileGroups);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 41 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 41 * hash + (this.userType != null ? this.userType.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.userType == null) ? (other.userType != null) : !this.userType.equals(other.userType)) {
            return false;
        }
        return true;
    }
    
}
