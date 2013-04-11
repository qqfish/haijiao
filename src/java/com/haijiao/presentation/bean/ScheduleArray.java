/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.bean;

import com.haijiao.SupportService.service.IClassService;
import java.util.List;

public class ScheduleArray {
    static final int add = 0;
    static final int remove = 1;
    static final int addStable = 2;
    static final int addTmp = 3;
    static final int studentCancel = 4;
    static final int teacherPause = 5;
    static final int studentPause = 6;
    
    IClassService classService;
    List<List<Integer>> array;

    public ScheduleArray() {
    }

    public List<List<Integer>> getArray() {
        return array;
    }

    public void setArray(List<List<Integer>> array) {
        this.array = array;
    }

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }

    public void updateData(String teacherEmail, String studentEmail) {
        for(int i = 0; i < array.size(); i++){
            List<Integer> oneDay = array.get(i);
            for(int j = 0; j < oneDay.size(); j++){
                switch(oneDay.get(j)){
                    case add:
                        classService.teacherAddClazz(teacherEmail, i, j);
                        break;
                    case remove:
                        classService.teacherRemoveClazz(teacherEmail, i, j);
                        break;
                    case addStable:
                        classService.bookStableTeacher(teacherEmail, studentEmail, "tmp", i, j);
                        break;
                    case addTmp:
                        classService.bookTmpTeacher(teacherEmail, studentEmail, "tmp", i, j);
                        break;
                    case studentCancel:
                        classService.cancelBook(teacherEmail, studentEmail, i, j);
                        break;
                    case teacherPause:
                        classService.teacherPauseBook(teacherEmail, studentEmail, i, j);
                        break;
                    case studentPause:
                        classService.studentPauseBook(teacherEmail, studentEmail, i, j);
                        break;
                }
            }
        }
    }
}
