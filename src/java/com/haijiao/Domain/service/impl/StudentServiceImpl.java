/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Timeslice;
import com.haijiao.Domain.service.GenericService;
import com.haijiao.Domain.service.IStudentService;
import java.util.List;

public class StudentServiceImpl extends GenericService<Student,Integer> implements IStudentService{ 

    @Override
    public Student getStudentByAccount(String account) {
        String hql = "from Student where email='"+ account + "'";
        List<Student> lt = findByqQuery(hql);
        if(lt.size() == 1)
            return findByqQuery(hql).get(0);
        else
            return null;
    }

    @Override
    public boolean bookTeacher(String studentAccount, String teacherAccount, List<Timeslice> timeslices) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Schedule getStudentSchedule(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Clazz> getStudentFinishedClasses(String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
