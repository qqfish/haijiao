/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.dao.impl;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import java.util.List;

public class TeacherDAOImpl extends GenericHibernateDAO<Teacher,Integer> implements ITeacherDAO {

    @Override
    public Teacher getTeacherByEmail(String email) {
        String hql = "from Teacher where email='"+ email + "'";
        List<Teacher> lt = findByqQuery(hql);
        if(lt.size() == 1){
            return findByqQuery(hql).get(0);
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
        List<Teacher> t = findByqQuery(hql);
        return t;
}

    @Override
    public boolean addTeacher(String email, String password, String school, String tel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
