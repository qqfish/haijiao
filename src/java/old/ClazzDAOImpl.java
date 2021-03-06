/**
 *
 * @author Jerry Zou
 */

//package com.haijiao.SupportService.dao.impl;
//
//import com.haijiao.Domain.bean.Clazz;
//import com.haijiao.SupportService.dao.GenericHibernateDAO;
//import com.haijiao.SupportService.dao.IClazzDAO;
//import com.haijiao.global.timer;
//import java.util.List;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class ClazzDAOImpl extends GenericHibernateDAO<Clazz,Integer> implements IClazzDAO{
//
//    @Override
//    public Clazz getClazz(String temail, String lesson, int day, int index) {
//        String hql = "select distinct c from Clazz c where c.teacher.email='" + temail
//                + "' and c.lesson.name='" + lesson + "'and c.day = '" + day + "' and c.index = " + index + "'";
//        List<Clazz> lc = findByQuery(hql);
//        if(lc.size() == 1){
//            return lc.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<Clazz> getStudentClazz(String studentEmail) {
//        String hql = "select c from Clazz c where c.student.email = '" + studentEmail + "'";
//        return findByQuery(hql);
//    }
//
//    @Override
//    public int getStudentClazzNum(String studentEmail) {
//        String hql = "select count(c) from Clazz c where c.student.email = '" + studentEmail + "'";
//        return getNumber(hql).intValue();
//    }
//    
//    @Override
//    public List<Clazz> getTeacherClazz(String teacherEmail) {
//        String hql = "select distinct c from Clazz c left join c.freeTime ft where ft.teacher.email = '" + teacherEmail + "'";
//        return findByQuery(hql);
//    }
//
//    @Override
//    public int getTeacherClazzNum(String teacherEmail) {
//        String hql = "select count(distinct c) from Clazz c left join c.freeTime ft where ft.teacher.email = '" + teacherEmail + "'";
//        return getNumber(hql).intValue();
//    }
//
//    @Override
//    public List<Clazz> getStudentTodayClazz(String studentEmail) {
//        String hql = "select distinct c from Clazz c left join c.freeTime ft where c.student.email = '" + studentEmail + "' and ft.weekday = " + timer.getNowDay() + " and c.timeToBegin = 0 and c.status = 3";
//        return findByQuery(hql);
//    }
//
//    @Override
//    public List<Clazz> getTeacherTodayClazz(String teacherEmail) {
//        String hql = "select distinct c from Clazz c left join c.freeTime ft where ft.teacher.email = '" + teacherEmail + "' and ft.weekday = " + timer.getNowDay() + " and c.timeToBegin = 0 and c.status = 3";
//        return findByQuery(hql);
//    }
//    
//}
