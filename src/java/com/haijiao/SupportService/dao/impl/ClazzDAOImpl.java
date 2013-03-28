/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Lesson;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IClazzDAO;
import java.sql.Date;

public class ClazzDAOImpl extends GenericHibernateDAO<Clazz,Integer> implements IClazzDAO{

    @Override
    public boolean bookTeacher(String teacherEmail, String studentEmail, String lesson, Date date, Integer week, String day, Integer beginIndex, Integer endIndex) {
        Clazz c = new Clazz();
        Lesson l = new Lesson();
        l.setName(lesson);
        c.setBeginIndex(beginIndex);
        c.setDate(date);
        c.setDay(day);
        c.setEndIndex(endIndex);
        c.setFinish(false);
        c.setLesson(l);
        c.setWeek(week);
        return makePersistent(c);
    }

    @Override
    public boolean dealWithReservation(int id, boolean accept) {
        Clazz c = findById(id);
        c.setAccept(accept);
        update(c);
        return true;
    }
}
