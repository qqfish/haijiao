/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.service.ITeacherService;

public class DealWithReservation extends SessionAction {
    private ITeacherService teacherService;
    private Clazz c;
    private boolean accept;
    
    public String execute(){
        if (teacherService.dealWithReservation((String)this.getValue("username"), c, accept)) {
            return SUCCESS;
        } else {
            return "error";
        }
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public Clazz getC() {
        return c;
    }

    public void setC(Clazz c) {
        this.c = c;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
