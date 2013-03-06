/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.service.ITeacherService;

public class ChangeScheduleAction extends SessionAction {
    ITeacherService teacherService;
    Schedule s;
    
    @Override
    public String execute(){
        if(teacherService.changeSchedule(this.getValue("username").toString(), s)){
            return SUCCESS;
        } else {
            this.putIn("errormessage", "修改失败，服务器未响应");
            return "unconnected";
        }
    }
    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public Schedule getS() {
        return s;
    }

    public void setS(Schedule s) {
        this.s = s;
    }
}
