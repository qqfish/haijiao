/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.service.IClazzService;
import java.util.List;

public class ClazzServiceImpl implements IClazzService{

    @Override
    public List<Clazz> getTeacherFinishedClasses(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Clazz> getStudentFinishedClasses(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean bookTeacher(String studentAccount, String teacherAccount, Clazz clazz) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean dealWithReservation(String username, Clazz c, boolean accept) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
