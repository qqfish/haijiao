/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.google.gson.Gson;
import com.haijiao.presentation.bean.schedule.ScheduleArray;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.scheduleLocation;
import java.util.List;

public class BookTeacherAction extends SessionAction {
    IClassService classService;
    ITeacherService teacherService;
    IUserService userService;
    String teacherEmail;
    String json;
    String lesson;
    String nextPageMessage;
    int times;

    public BookTeacherAction() {
    }
    
    @Override
    public String execute(){
        //List<List<Integer>> array = sa.getArray();
        User stu = userService.getUserByEmail((String)this.getSessionValue("email"));
        if(stu == null){
            return INPUT;
        }
        if(stu.getUserType().equals("teacher")){
            return "teacher";
        }
        Gson gson = new Gson();
        System.out.println(lesson);
        ScheduleArray array = gson.fromJson(json, ScheduleArray.class);
        List<scheduleLocation> sList = array.toList();
        System.out.println(sList.size());
        classService.bookTeacher(teacherEmail, (String)this.getSessionValue("email"), lesson, sList, times);
        
        nextPageMessage = this.getText("successMessage");
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

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }
}
