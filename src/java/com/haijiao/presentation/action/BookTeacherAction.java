
package com.haijiao.presentation.action;

import com.google.gson.Gson;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.presentation.bean.schedule.ScheduleArray;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.service.IMailService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.scheduleLocation;
import java.util.List;

public class BookTeacherAction extends SessionAction {

    IClassService classService;
    ITeacherService teacherService;
    IUserService userService;
    IMailService mailService;
    String teacherEmail;
    String json;
    String lesson;
    String nextPageMessage;
    int times;

    public BookTeacherAction() {
    }

    @Override
    public String execute() {
        //List<List<Integer>> array = sa.getArray();
        User stu = userService.getUserByEmail((String) this.getSessionValue("email"));
        if (stu == null) {
            nextPageMessage = this.getText("noLogin");
            return SUCCESS;
        }
        if (stu.getUserType().equals("teacher")) {
            return SUCCESS;
        }
        Gson gson = new Gson();
        ScheduleArray array = gson.fromJson(json, ScheduleArray.class);
        List<scheduleLocation> sList = array.toList(1);
        int remainCoin = ((Student) stu).getRemainCoin();
        Teacher tea = teacherService.getTeacherByEmail(teacherEmail);
        remainCoin -= (tea.getWagePerhour() * sList.size() * times);
        if (remainCoin >= 0) {
            classService.bookTeacher(teacherEmail, (String) this.getSessionValue("email"), lesson, sList, times);
            mailService.sendMail( (String)this.getSessionValue("email"), teacherEmail,
                "老师您好，有学生选择了您的课程，请尽快确认。"
                + "本邮件由系统发送，具体情况可回复本邮件与学生联系。"
        );
            nextPageMessage = this.getText("successMessage");
            return SUCCESS;
        } else {
            nextPageMessage = this.getText("noEnoughMoney");
            return SUCCESS;
        }
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

    public IMailService getMailService() {
        return mailService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }
}
