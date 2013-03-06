/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.ITeacherService;

public class ChangeTeacherInfoAction extends SessionAction {
    ITeacherService teacherService;
    Teacher tea;

    @Override
    public String execute(){
        if(teacherService.changeInfo(this.getValue("username").toString(), tea)){
            return SUCCESS;
        } else {
            this.putIn("errormessage", "修改基本信息失败，数据库未响应！！！");
            return "unconnected";
        }
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
