/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.haijiao.presentation.bean.schedule;
//
//import com.google.gson.Gson;
//import com.haijiao.Domain.bean.Clazz;
//import com.haijiao.Domain.bean.Teacher;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author fish
// */
//public class ScheduleBean {
//    private List<FreeTimeBean> clazzes;
//    
//    public ScheduleBean(Teacher tea) {
//        super();
//        clazzes = new ArrayList();
//        for(int i = 0; i < tea.getSchedule().size(); i++){
//            FreeTimeBean newClazz = new FreeTimeBean(tea.getSchedule().get(i));
//            clazzes.add(newClazz);
//        }
//    }
//    
//    public ScheduleBean(List<Clazz> c) {
//        super();
//        clazzes = new ArrayList();
//        for(int i = 0; i < c.size(); i++){
//            FreeTimeBean newClazz = new FreeTimeBean(c.get(i));
//            if(!clazzes.contains(newClazz)){
//                clazzes.add(newClazz);
//            }
//        }
//    }
//
//    public List<FreeTimeBean> getClazzes() {
//        return clazzes;
//    }
//
//    public void setClazzes(List<FreeTimeBean> clazzes) {
//        this.clazzes = clazzes;
//    }
//    
//    public String toJson(){
//        Gson gson = new Gson();
//        return gson.toJson(this);
//    }
//}
