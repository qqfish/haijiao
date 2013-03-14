/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.IUserService;
import java.util.ArrayList;
import java.util.List;

public class SearchTeacherAction extends SessionAction {
    IUserService userService;
    String name;
    Integer grade;
    String subject;
    String net;
    List<Teacher> teacherlist;
    
    public SearchTeacherAction(){
        teacherlist = new ArrayList<Teacher>();
    }
    
    public String execute(){
        /*****
         * 此处加入如果某项为空的处理
         */
        teacherlist = userService.searchTeacher(name, grade, subject, net);
        return SUCCESS;
        /***
         *  此处加入出错处理
         */
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public List<Teacher> getTeacherlist() {
        return teacherlist;
    }

    public void setTeacherlist(List<Teacher> teacherlist) {
        this.teacherlist = teacherlist;
    }
            
    

}
