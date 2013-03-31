/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.service.ITeacherService;

public class ChangeAuditionAction extends SessionAction{
    ITeacherService teacherService;

    @Override
    public String execute(){
        if(teacherService.changeAudition(this.getValue("username").toString())){
            return SUCCESS;
        }
        else {
            this.putIn("errormessage", "数据库未响应！");
            return "unconnected";
        }
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

}
