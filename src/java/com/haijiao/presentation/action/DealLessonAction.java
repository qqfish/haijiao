/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.ITeacherService;

public class DealLessonAction extends SessionAction {
    private ITeacherService teacherService;
    private String nextPageMessage;
    private String lessonName;
    private String lesson;
    private String grade;
    
    public String addLesson(){
        teacherService.addLesson((String)this.getSessionValue("email"), grade+lesson);
        nextPageMessage = "成功添加课程";
        return SUCCESS;
    }
    
    public String deleteLesson(){
        teacherService.deleteLesson((String)this.getSessionValue("email"), lessonName);
        nextPageMessage = "成功删除课程";
        return SUCCESS;
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
