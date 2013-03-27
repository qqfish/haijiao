/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.service.IClazzService;

public class DealWithReservation extends SessionAction {
    private IClazzService clazzService;
    private Integer clazzId;
    private boolean accept;
    
    public String execute(){
        if (clazzService.dealWithReservation(clazzId, accept)) {
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

    public IClazzService getClazzService() {
        return clazzService;
    }

    public void setClazzService(IClazzService clazzService) {
        this.clazzService = clazzService;
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
