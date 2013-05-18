/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.service.IMailService;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;

public class DealApplyAction extends RequestSessionAction {

    IClassService classService;
    String nextPageMessage;
    ITeacherService teacherService;
    IStudentService studentService;
    IMailService mailService;
    Integer classId;
    String toEmail;

    @Override
    public String execute() {
        if ("stop".equals((String) this.getOutRequest("button"))) {
            stop();
        } else if ("accept".equals((String) this.getOutRequest("button"))) {
            accept();
        } else if ("decline".equals((String) this.getOutRequest("button"))) {
            decline();
        } else if ("studentStop".equals((String) this.getOutRequest("button"))) {
            studentStop();
        } else {
            //do nothing;
        }
        String userType = (String) this.getOutSession("userType");
        String email = (String) this.getOutSession("email");
        if (userType.equals("teacher")) {
            this.putIn("todayClazz", teacherService.getTodayClasses(email));
        } else {
            this.putIn("todayClazz", studentService.getTodayClasses(email));
        }
        return SUCCESS;
    }

    public void stop() {
        classService.teacherPauseBook(classId, 1);
        nextPageMessage = "成功暂停一周";
        mailService.sendMail( (String)this.getOutSession("email"), toEmail,
                "同学您好，您的课程被老师暂停一周。"
                + "本邮件由系统发送，具体情况可回复本邮件与老师联系。"
        );
    }

    public void accept() {
        classService.dealWithReservation(classId, true);
        nextPageMessage = "成功接受该课程";
        mailService.sendMail( (String)this.getOutSession("email"), toEmail,
                "同学您好，您的课程请求已被老师接受，届时请做好上课准备。"
                + "本邮件由系统发送，具体情况可回复本邮件与老师联系。"
        );
    }

    public void decline() {
        classService.dealWithReservation(classId, false);
        nextPageMessage = "成功拒绝接受该课程";
        mailService.sendMail( (String)this.getOutSession("email"), toEmail,
                "同学您好，您的课程请求已被老师拒绝。"
                + "本邮件由系统发送，具体情况可回复本邮件与老师联系。"
        );
    }

    public void studentStop() {
        classService.studentPauseBook(classId, 1);
        nextPageMessage = "成功暂停一周";
        mailService.sendMail( (String)this.getOutSession("email"), toEmail,
                "老师您好，您的学生发送了暂停一周课程的请求。"
                + "本邮件由系统发送，具体情况可回复本邮件与学生联系。"
        );
    }

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public IMailService getMailService() {
        return mailService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
}
