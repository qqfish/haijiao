/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Timeslice;
import com.haijiao.Domain.service.IStudentService;
import java.util.ArrayList;
import java.util.List;

public class BookTeacherAction extends SessionAction {
    IStudentService studentService;
    String teacherName;
    List<Timeslice> timeslices;
    
    public BookTeacherAction (){
        timeslices = new ArrayList<Timeslice>();
    }
    
    @Override
    public String execute(){
        if (studentService.bookTeacher((String)this.getValue("username"), teacherName, timeslices)) {
            return SUCCESS;
        } else {
            return "fail";
        }
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<Timeslice> getTimeslices() {
        return timeslices;
    }

    public void setTimeslices(List<Timeslice> timeslices) {
        this.timeslices = timeslices;
    }

}
