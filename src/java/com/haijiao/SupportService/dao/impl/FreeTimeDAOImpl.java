/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IFreeTimeDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hp
 */

@Repository
public class FreeTimeDAOImpl extends GenericHibernateDAO<FreeTime,Integer> implements IFreeTimeDAO{

    @Override
    public FreeTime getTeacherFreeTime(String email, int day, int index) {
        String hql = "select ft form techer t join t.schedule.freeTimes ft where t.email = '"
                + email + "' and ft.day = '" + day + "' and ft.index = '" + index + "'";
        List<FreeTime> lf = findByQuery(hql);
        if(lf.size() != 1)
            return null;
        else
            return lf.get(0);
    }
    
}
