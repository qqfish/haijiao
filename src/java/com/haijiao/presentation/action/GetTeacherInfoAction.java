/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.presentation.bean.schedule.ScheduleBean;

public class GetTeacherInfoAction extends RequestAction{
    ITeacherService teacherService;
    Teacher tea;
    ScheduleBean scheduleBean;

    @Override
    public String execute(){
        String email = (String)this.getValue("teacherEmail");
        tea = teacherService.getTeacherByEmail(email);
        //scheduleBean = new ScheduleBean(tea.getSchedule());
        return SUCCESS;
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public Teacher getTea() {
        return tea;
    }

    public void setTea(Teacher tea) {
        this.tea = tea;
    }

    public ScheduleBean getScheduleBean() {
        return scheduleBean;
    }

    public void setScheduleBean(ScheduleBean scheduleBean) {
        this.scheduleBean = scheduleBean;
    }
    
}
