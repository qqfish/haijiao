/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Timeslice;
import com.haijiao.Domain.service.IClazzService;
import java.util.List;

public class BookTeacherAction extends SessionAction {
    IClazzService clazzService;
    String teacherName;
    Clazz clazz;
    
    @Override
    public String execute(){
        if (clazzService.bookTeacher((String)this.getValue("username"), teacherName, clazz)) {
            return SUCCESS;
        } else {
            return "fail";
        }
    }

    public IClazzService getClazzService() {
        return clazzService;
    }

    public void setClazzService(IClazzService clazzService) {
        this.clazzService = clazzService;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }
}
