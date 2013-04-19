/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.presentation.bean.schedule.ScheduleBean;

public class IndexAction extends SessionAction {
    ScheduleBean scheduleBean;
    IUserService userService;
    @Override
    public String execute() throws Exception {
        String email = (String)this.getSessionValue("email");
        if(email!=null){
            User user = userService.getUserByEmail(email);
            if(user.getUserType().equals("teacher")){
                Teacher tea = (Teacher) user;
                this.sessionPutIn("message", this.getText("teacherInfo"));
                scheduleBean = new ScheduleBean(tea.getSchedule());
                return "teacher";
            } else {
                this.sessionPutIn("message", this.getText("studentInfo"));
                return "student";
            }
        } else {
            return SUCCESS;
        }
    }

    public ScheduleBean getScheduleBean() {
        return scheduleBean;
    }

    public void setScheduleBean(ScheduleBean scheduleBean) {
        this.scheduleBean = scheduleBean;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    
}
