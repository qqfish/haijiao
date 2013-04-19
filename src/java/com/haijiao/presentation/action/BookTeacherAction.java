/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.google.gson.Gson;
import com.haijiao.presentation.bean.schedule.ScheduleArray;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.global.scheduleLocation;
import java.util.ArrayList;
import java.util.List;

public class BookTeacherAction extends SessionAction {
    IClassService classService;
    ITeacherService teacherService;
    String teacherEmail;
    String json;
    int times;

    public BookTeacherAction() {
    }
    
    @Override
    public String execute(){
        //List<List<Integer>> array = sa.getArray();
        System.out.println(json);
        System.out.println(teacherEmail);
        Gson gson = new Gson();
        ScheduleArray array = gson.fromJson(json, ScheduleArray.class);
        List<scheduleLocation> sList = array.toList();
        classService.bookTeacher(teacherEmail, (String)this.getSessionValue("email"), "tmp", sList, times);
        
        this.sessionPutIn("message", this.getText("successMessage"));
        return SUCCESS;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public IClassService getStudentService() {
        return classService;
    }

    public void setStudentService(IClassService classService) {
        this.classService = classService;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
