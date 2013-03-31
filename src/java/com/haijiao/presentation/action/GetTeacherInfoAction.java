/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.ITeacherService;

public class GetTeacherInfoAction extends RequestAction {
    ITeacherService teacherService;
    Teacher tea;

    @Override
    public String execute(){
        String email = (String)this.getValue("teacherEmail");
        tea = teacherService.getTeacherByEmail(email);
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
    
}
