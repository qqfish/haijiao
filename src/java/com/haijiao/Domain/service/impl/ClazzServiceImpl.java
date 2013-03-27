/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.service.GenericService;
import com.haijiao.Domain.service.IClazzService;
import java.sql.Date;

public class ClazzServiceImpl extends GenericService<Clazz,Integer> implements IClazzService{

    @Override
    public boolean bookTeacher(String teacherEmail, String studentEmail, String lesson, Date date, Integer week, String day, Integer beginIndex, Integer endIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean dealWithReservation(int id, boolean accept) {
        Clazz c = findById(id);
        c.setAccept(accept);
        update(c);
        return true;
    }
}
