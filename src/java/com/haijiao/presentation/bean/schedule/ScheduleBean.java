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
    private List<ClazzBean> clazzes;
    
    public ScheduleBean() {
    }

    public List<ClazzBean> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<ClazzBean> clazzes) {
        this.clazzes = clazzes;
    }
    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
