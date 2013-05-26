/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action.teacherAchtion;

import com.google.gson.Gson;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.global.scheduleLocation;
import com.haijiao.presentation.action.SessionAction;
import com.haijiao.presentation.bean.schedule.ScheduleArray;
import java.util.List;

/**
 *
 * @author fish
 */
public class AddFreeTimeAction extends SessionAction {

    IClassService classService;
    String json;
    String nextPageMessage;

    @Override
    public String execute() {
        //List<List<Integer>> array = sa.getArray();
        Gson gson = new Gson();
        ScheduleArray array = gson.fromJson(json, ScheduleArray.class);
        //create list
        List<scheduleLocation> sList = array.toList(1);
        //remove list
        List<scheduleLocation> rList = array.toList(2);
        if (classService.teacherAddClazz((String) this.getSessionValue("email"), sList)
                && classService.teacherRemoveClazz((String) this.getSessionValue("email"), rList)) {
            nextPageMessage = "成功修改空闲时间";
            return SUCCESS;
        } else {
            nextPageMessage = "修改空闲时间失败";
            return INPUT;
        }
    }

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }
}
