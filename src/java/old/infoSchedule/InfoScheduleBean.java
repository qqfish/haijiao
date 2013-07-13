/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.haijiao.presentation.bean.infoSchedule;
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
//public class InfoScheduleBean {
//    private List<InfoFreeTimeBean> clazzes;
//    
//    public InfoScheduleBean(Teacher tea) {
//        clazzes = new ArrayList();
//        for(int i = 0; i < tea.getSchedule().size(); i++){
//            InfoFreeTimeBean newClazz = new InfoFreeTimeBean(tea.getSchedule().get(i));
//            clazzes.add(newClazz);
//        }
//    }
//    
//    public InfoScheduleBean(List<Clazz> c) {
//        clazzes = new ArrayList();
//        for(int i = 0; i < c.size(); i++){
//            InfoFreeTimeBean newClazz = new InfoFreeTimeBean(c.get(i));
//            if(!clazzes.contains(newClazz)){
//                clazzes.add(newClazz);
//            }
//        }
//    }
//
//    public List<InfoFreeTimeBean> getClazzes() {
//        return clazzes;
//    }
//
//    public void setClazzes(List<InfoFreeTimeBean> clazzes) {
//        this.clazzes = clazzes;
//    }
//    
//    public String toJson(){
//        Gson gson = new Gson();
//        return gson.toJson(this);
//    }
//}
