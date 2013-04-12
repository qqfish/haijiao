/**
 *
 * @author Jerry Zou
 */
package com.haijiao.presentation.bean.schedule;

import com.haijiao.SupportService.service.IClassService;
import java.util.List;

public class ScheduleArray {

    

    IClassService classService;
    List<List<ClazzUpdate>> array;

    public ScheduleArray() {
    }

    public List<List<ClazzUpdate>> getArray() {
        return array;
    }

    public void setArray(List<List<ClazzUpdate>> array) {
        this.array = array;
    }

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }

    public void updateData(String teacherEmail, String studentEmail) {
        for (int i = 0; i < array.size(); i++) {
            List<ClazzUpdate> oneDay = array.get(i);
            for (int j = 0; j < oneDay.size(); j++) {
                switch (oneDay.get(j).getStatus()) {
                    case ClazzUpdate.Status.add:
                        classService.teacherAddClazz(teacherEmail, i, j);
                        break;
                    case ClazzUpdate.Status.remove:
                        classService.teacherRemoveClazz(teacherEmail, i, j);
                        break;
                    case ClazzUpdate.Status.studentBook:
                        classService.bookTeacher(teacherEmail, studentEmail, "tmp", i, j, oneDay.get(j).getNum());
                        break;
                }
            }
        }
    }
}
