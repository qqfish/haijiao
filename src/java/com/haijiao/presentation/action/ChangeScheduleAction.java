/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.service.IScheduleService;

public class ChangeScheduleAction extends SessionAction {
    IScheduleService scheduleService;
    Schedule s;
    
    @Override
    public String execute(){
        if(scheduleService.changeSchedule(this.getValue("username").toString(), s)){
            return SUCCESS;
        } else {
            this.putIn("errormessage", "修改失败，服务器未响应");
            return "unconnected";
        }
    }

    public IScheduleService getScheduleService() {
        return scheduleService;
    }

    public void setScheduleService(IScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    public Schedule getS() {
        return s;
    }

    public void setS(Schedule s) {
        this.s = s;
    }
}
