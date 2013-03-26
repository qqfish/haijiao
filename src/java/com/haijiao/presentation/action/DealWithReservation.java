/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.service.IClazzService;

public class DealWithReservation extends SessionAction {
    private IClazzService clazzService;
    private Clazz c;
    private boolean accept;
    
    public String execute(){
        if (clazzService.dealWithReservation((String)this.getValue("username"), c, accept)) {
            return SUCCESS;
        } else {
            return "error";
        }
    }

    public IClazzService getClazzService() {
        return clazzService;
    }

    public void setClazzService(IClazzService clazzService) {
        this.clazzService = clazzService;
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
