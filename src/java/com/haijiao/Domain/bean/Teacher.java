/**
 * Teacher.java
 * @author fish
 */

package com.haijiao.Domain.bean;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity    
@Table(name="teacher")
@PrimaryKeyJoinColumn
public class Teacher extends User{
    private String school;        //就读大学
    private String brief_intro;  //老师的简单介绍，显示在搜索页面
    private String tel;             //老师的手机
    private String videoUrl;    //老师的介绍视频地址
    
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @Column(name = "lid")
    private List<Label> labels; //老师的标签 
    
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name="tid")
    private List<Lesson> lessons;   //该老师开设课程
    
    private boolean audition;        //该老师是否接受试听
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "scheid", unique = true)
    private Schedule schedule;      //记录老师的时间表
    
    private int wagePerhour;         //老师每小时的辅导费
    
    @ManyToMany(fetch = FetchType.EAGER , cascade = {CascadeType.PERSIST})
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "teach",
            joinColumns = @JoinColumn(name="sid"),
            inverseJoinColumns = @JoinColumn(name="tid")
            )
    private List<Student> studentlist; //教授过的学生列表
    
    @OneToMany(mappedBy = "teacher",fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Clazz> classlist;    //课程列表

    public Teacher() {
        this.lessons = new ArrayList<Lesson>();
        this.labels = new ArrayList<Label>();
        this.studentlist = new ArrayList<Student>();
        this.classlist = new ArrayList<Clazz>();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getBrief_intro() {
        return brief_intro;
    }

    public void setBrief_intro(String brief_intro) {
        this.brief_intro = brief_intro;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public boolean isAudition() {
        return audition;
    }

    public void setAudition(boolean audition) {
        this.audition = audition;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public int getWagePerhour() {
        return wagePerhour;
    }

    public void setWagePerhour(int wagePerhour) {
        this.wagePerhour = wagePerhour;
    }

    public List<Student> getStudentlist() {
        return studentlist;
    }

    public void setStudentlist(List<Student> studentlist) {
        this.studentlist = studentlist;
    }

    public List<Clazz> getClasslist() {
        return classlist;
    }

    public void setClasslist(List<Clazz> classlist) {
        this.classlist = classlist;
    }
    
    public String toJson(){
        Gson gson = new Gson();
        System.out.println("begin:");
        System.out.println(this.schedule.getId());
        System.out.println(gson.toJson(this.schedule));
        System.out.println("end");
        return gson.toJson(this);
    }

}
