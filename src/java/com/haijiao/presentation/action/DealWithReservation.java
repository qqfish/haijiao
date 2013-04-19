/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IClassService;

public class DealWithReservation extends SessionAction {
    private IClassService classService;
    private Integer clazzId;
    private boolean accept;
    
    public String execute(){
        if (classService.dealWithReservation(clazzId, accept)) {
            if(accept == true){
                this.sessionPutIn("message", this.getText("successMessage"));
            } else {
                this.sessionPutIn("message", this.getText("refuseMessage"));
            }
            return SUCCESS;
        } else {
            this.sessionPutIn("message", this.getText("errorMessage"));
            return "error";
        }
    }

    public IClassService getTeacherService() {
        return classService;
    }

    public void setTeacherService(IClassService classService) {
        this.classService = classService;
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
