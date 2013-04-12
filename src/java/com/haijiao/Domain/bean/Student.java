/**
 *
 * @author fish
 */

package com.haijiao.Domain.bean;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity    
@Table(name="student")
@PrimaryKeyJoinColumn
public class Student extends User{
    private String grade;   //学生当前的年级
    private String school;  //学生就读学校
    private String tel;        //学生或家长的联系方式
    private String telType; //="student" or "parent"
    
    @OneToMany(fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name = "scheid", unique = true)
    private List<Clazz> schedule;      //学生的时间表
    
    @ManyToMany(mappedBy = "studentlist")
    private List<Teacher> teacherList;   //收藏老师的列表

    public Student() {
        this.teacherList = new ArrayList<Teacher>();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }

    public List<Clazz> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Clazz> schedule) {
        this.schedule = schedule;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

}
