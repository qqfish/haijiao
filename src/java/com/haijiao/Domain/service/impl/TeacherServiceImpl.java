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
        if(lt.size() == 1){
            return findByqQuery(hql).get(0);
        } else {
            return null;
        }
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
    public List<Teacher> searchTeacher(List<String> strList ) {
        String hql = "select distinct t from Teacher t left join t.lessons l";
        String where = " where "; 
        String or = " or ";
        for(int i=0; i<strList.size(); i++){
            String keyword = strList.get(i);
            where += "l.name like '%" + keyword + "%'";
            where += or;
            where += "t.name like '%" + keyword + "%'";
            if(i +1< strList.size())
                where += or;
            System.out.println("Search for:" + strList.get(i) + "\n");
        }
        hql += where;
        List<Teacher> t = findByqQuery(hql);
        System.out.println(hql);
        System.out.println(t.size());
        return t;
}

    @Override
    public boolean addTeacher(String account, String password, String school, String tel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Clazz> getFinishedClasses(String username) {
        Teacher t = getTeacherByAccount(username);
        return t.getClasslist();
    }

    @Override
    public Schedule getSchedule(String username) {
        Teacher t = getTeacherByAccount(username);
        return t.getSchedule();
    }

    @Override
    public boolean changeSchedule(String username, Schedule s) {
        Teacher t = getTeacherByAccount(username);
        t.setSchedule(s);
        update(t);
        return true;
    }
   
}
