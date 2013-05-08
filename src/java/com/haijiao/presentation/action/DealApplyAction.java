/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;

public class DealApplyAction extends RequestSessionAction {

    IClassService classService;
    String nextPageMessage;
    ITeacherService teacherService;
    IStudentService studentService;

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
        Integer classId = Integer.parseInt((String) this.getOutRequest("classId"));
        classService.teacherPauseBook(classId, 1);
        nextPageMessage = "成功暂停一周";
    }

    public void accept() {
        Integer classId = Integer.parseInt((String) this.getOutRequest("classId"));
        classService.dealWithReservation(classId, true);
        nextPageMessage = "成功接受该课程";
    }

    public void decline() {
        Integer classId = Integer.parseInt((String) this.getOutRequest("classId"));
        classService.dealWithReservation(classId, false);
        nextPageMessage = "成功拒绝接受该课程";
    }

    public void studentStop() {
        Integer classId = Integer.parseInt((String) this.getOutRequest("classId"));
        classService.studentPauseBook(classId, 1);
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
}
