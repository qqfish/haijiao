/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.dao.impl;
import com.haijiao.Domain.bean.Student;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import java.util.List;

public class StudentDAOImpl extends GenericHibernateDAO<Student,Integer> implements IStudentDAO{ 

    @Override
    public Student getStudentByEmail(String email) {
        String hql = "from Student where email='"+ email + "'";
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
    
}
