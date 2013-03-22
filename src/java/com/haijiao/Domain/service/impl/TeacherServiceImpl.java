/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.GenericService;
import com.haijiao.Domain.service.ITeacherService;
import java.util.List;

public class TeacherServiceImpl extends GenericService<Teacher,Integer> implements ITeacherService {

    @Override
    public Teacher getTeacherByAccount(String account) {
        String hql = "from Teacher where email='"+ account + "'";
        List<Teacher> lt = findByqQuery(hql);
        if(lt.size() == 1)
            return findByqQuery(hql).get(0);
        else
            return null;
    }
    
    @Override
    public boolean changeSchedule(String username, Schedule s) {
        Teacher t = getTeacherByAccount(username);
        t.setSchedule(s);
        update(t);
        return true;
    }

    @Override
    public boolean changeInfo(String username, Teacher tc) {
        update(tc);
        return true;
    }

    @Override
    public boolean changeAudition(String username) {
        Teacher t = getTeacherByAccount(username);
        boolean audition = t.isAudition();
        t.setAudition(!audition);
        update(t);
        return true;
    }

    @Override
    public boolean takeMoney(String username, int numberOfCoin) {
        Teacher t = getTeacherByAccount(username);
        int coin = t.getCoin();
        t.setCoin(coin + numberOfCoin);
        update(t);
        return true;
    }

    @Override
    public boolean dealWithReservation(String username, Clazz c, boolean accept) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
