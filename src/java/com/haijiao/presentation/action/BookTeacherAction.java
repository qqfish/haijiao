/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.google.gson.Gson;
import com.haijiao.Domain.bean.ScheduleArray;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.service.ITeacherService;
import java.util.List;

public class BookTeacherAction extends SessionAction {
    IClassService classService;
    ITeacherService teacherService;
    String teacherEmail;
    String gsonStr;
    Gson gson;
    Teacher tea;

    public BookTeacherAction() {
        this.gson = new Gson();
    }
    
    @Override
    public String execute(){
        ScheduleArray sa = gson.fromJson(gsonStr, ScheduleArray.class);
        List<List<Integer>> array = sa.getArray();
        for(int i=0; i<array.size(); i++){
            for(int j=0; j<array.get(i).size(); j++){
                if(array.get(i).get(j)!=null){
                    for(int k=j; k<array.get(i).size(); k++){
                        if(k==array.get(i).size()-1){
                            classService.bookTeacher(teacherEmail,(String)this.getValue("username"), "temp", null, 1, i, j, k);
                            break;
                        } else if(array.get(i).get(k+1)==null){
                            classService.bookTeacher(teacherEmail,(String)this.getValue("username"), "temp", null, 1, i, j, k);
                            break;
                        }
                    }
                }
            }
        }
        tea = teacherService.getTeacherByEmail(teacherEmail);
        this.putIn("message", this.getText("successMessage"));
        return SUCCESS;
    }

    public IClassService getStudentService() {
        return classService;
    }

    public void setStudentService(IClassService classService) {
        this.classService = classService;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }

    public String getGsonStr() {
        return gsonStr;
    }

    public void setGsonStr(String gsonStr) {
        this.gsonStr = gsonStr;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

<<<<<<< HEAD
    public Teacher getTea() {
        return tea;
    }

    public void setTea(Teacher tea) {
        this.tea = tea;
=======
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
>>>>>>> schedule not test yet
    }
}
