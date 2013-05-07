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
    String nextPageMessage;
    
    @Override
    public String execute(){
        if (classService.dealWithReservation(clazzId, accept)) {
            if(accept == true){
                nextPageMessage = this.getText("successMessage");
            } else {
                nextPageMessage = this.getText("refuseMessage");
            }
            return SUCCESS;
        } else {
            nextPageMessage = this.getText("errorMessage");
            return "error";
        }
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

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }
}
