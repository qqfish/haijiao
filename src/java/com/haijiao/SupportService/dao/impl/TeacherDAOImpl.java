/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.dao.impl;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDAOImpl extends GenericHibernateDAO<Teacher,Integer> implements ITeacherDAO {

    @Override
    public Teacher getTeacherByEmail(String email) {
        String hql = "from Teacher where email='"+ email + "'";
        List<Teacher> lt = findByQuery(hql);
        if(lt.size() == 1){
            return lt.get(0);
        } else {
            return null;
        }
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
            if(i +1< strList.size()){
                where += or;
            }
        }
        hql += where;
        List<Teacher> t = findByQuery(hql);
        return t;
    }
   
}
