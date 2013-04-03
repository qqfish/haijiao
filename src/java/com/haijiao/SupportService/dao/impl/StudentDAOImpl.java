/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.dao.impl;
import com.haijiao.Domain.bean.Student;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDAOImpl extends GenericHibernateDAO<Student,Integer> implements IStudentDAO{ 

    @Override
    public Student getStudentByEmail(String email) {
        String hql = "from Student where email='"+ email + "'";
        List<Student> lt = findByQuery(hql);
        if(lt.size() == 1){
            return findByQuery(hql).get(0);
        } else {
            return null;
        }
    }
    
}
