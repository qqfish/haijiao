/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.service.ITeacherService;

public class DealWithReservation extends SessionAction {
    private ITeacherService teacherService;
    private Integer clazzId;
    private boolean accept;
    
    public String execute(){
        if (teacherService.dealWithReservation(clazzId, accept)) {
            if(accept == true){
                this.putIn("message", this.getText("successMessage"));
            } else {
                this.putIn("message", this.getText("refuseMessage"));
            }
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("errorMessage"));
            return "error";
        }
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }
    

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }
}
