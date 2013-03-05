/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.service.ITeacherService;
import com.haijiao.Domain.bean.Teacher;

public class GetTeacherInfoAction extends SessionAction {
    ITeacherService teacherService;
    Teacher tea;

    @Override
    public String execute(){
        String account = this.getValue("username").toString();
        tea = teacherService.getTeacherByAccount(account);
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
