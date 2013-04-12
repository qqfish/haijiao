/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.bean.schedule;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fish
 */
public class ScheduleBean {
    private List<FreeTimeBean> clazzes;
    
    public ScheduleBean() {
    }

    public List<FreeTimeBean> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<FreeTimeBean> clazzes) {
        this.clazzes = clazzes;
    }
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
