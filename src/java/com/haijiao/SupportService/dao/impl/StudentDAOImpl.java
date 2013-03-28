/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.dao.impl;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import java.util.List;

public class StudentDAOImpl extends GenericHibernateDAO<Student,Integer> implements IStudentDAO{ 

    @Override
    public Student getStudentByAccount(String account) {
        String hql = "from Student where email='"+ account + "'";
        List<Student> lt = findByqQuery(hql);
        if(lt.size() == 1){
            return findByqQuery(hql).get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean addStudent(String account, String password, String grade, String school, String tel, String telType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean topUpMoney(String username, int numberOfCoin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean changeInfo(String username, Teacher tc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Clazz> getFinishedClasses(String username) {
        Student s = getStudentByAccount(username);
        return s.getClassList();
    }

    @Override
    public Schedule getSchedule(String username) {
        Student s = getStudentByAccount(username);
        return s.getSchedule();
    }
}
