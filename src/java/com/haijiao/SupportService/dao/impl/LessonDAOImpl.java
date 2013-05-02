/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Lesson;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.ILessonDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */
@Repository
public class LessonDAOImpl extends GenericHibernateDAO<Lesson,Integer> implements ILessonDAO{

    @Override
    public Lesson getLessonByName(String email, String lesson) {
        String hql = "select l from Teacher t right join t.lessons l where l.name = '" + lesson + "' and t.email='" + email +"'";
        List<Lesson> ll = findByQuery(hql);
        if(ll.size() == 1)
            return ll.get(0);
        else
            return null;
    }
    
}
