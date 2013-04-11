/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.bean.schedule;

import com.google.gson.Gson;
import com.haijiao.Domain.bean.Schedule;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fish
 */
public class ScheduleBean {
    private List<ClazzBean> clazzes;

    public ScheduleBean(Schedule s){
        clazzes = new ArrayList();
        if(s == null){
            return;
        }
//        for(int i = 0; i < s.getClazzes().size(); i++){
//            clazzes.add(new ClazzBean(s.getClazzes().get(i)));
//        }
    }
    
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
