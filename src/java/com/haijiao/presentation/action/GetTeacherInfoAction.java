/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.dao.ITeacherDAO;

public class GetTeacherInfoAction extends RequestAction {
    ITeacherDAO teacherService;
    Teacher tea;

    @Override
    public String execute(){
        String email = (String)this.getValue("teacherEmail");
        tea = teacherService.getTeacherByAccount(email);
        return SUCCESS;
    }
    
    public ITeacherDAO getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherDAO teacherService) {
        this.teacherService = teacherService;
    }

    public Teacher getTea() {
        return tea;
    }

    public void setTea(Teacher tea) {
        this.tea = tea;
    }
    
}
