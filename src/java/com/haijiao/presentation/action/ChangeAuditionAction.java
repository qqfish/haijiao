/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.SupportService.dao.ITeacherDAO;

public class ChangeAuditionAction extends SessionAction{
    ITeacherDAO teacherService;

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
    
    public ITeacherDAO getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherDAO teacherService) {
        this.teacherService = teacherService;
    }
    
}
