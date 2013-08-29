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

    @Override
    public List<Student> searchStudentPage(List<String> strList, String lesson, String way, String net, String sex, String province, String city, String district, String status, int first, int pagesize, String extOrder, int desc) {
        String hql = "select distinct s from Student s right join s.demand d";       
        String where = " where ";
        String or = " or ";
        String and = " and ";
        for (int i = 0; i < strList.size(); i++) {
            String keyword = strList.get(i);
            if(keyword.equals(""))
                continue;
            where += "(d.lesson like '%" + keyword + "%'";
            where += or;
            where += "s.name like '%" + keyword + "%')";
            where += and;
        }
        if(lesson != null && !lesson.equals(""))
            where += "d.lesson like '%" + lesson + "%' and ";
        if(way != null && !way.equals(""))
            where += "d.way = '" + way + "' and ";
        if(net != null && !net.equals(""))
            where += "s.net = '" + net + "' and ";
        if(sex != null && !sex.equals(""))
            where += "s.sex ='" + sex + "' and ";
        if(province != null && !province.equals(""))
            where += "s.province = '" + province + "' and ";
        if(city != null && !city.equals(""))
            where += "s.city = '" + city + "' and ";
        if(district != null && !district.equals(""))
            where += "s.district = '" + district + "' and ";
        if(status != null && !status.equals(""))
            where += "s.status = '" + status + "' and ";
        if(!where.equals(" where ")){
            //where += "l.delete=0";
            hql += where.substring(0, where.length() -4);
        }
        if (extOrder!=null){
            hql += " order by d." + extOrder;
            if (desc==1) {
                hql += " desc"; //降序
            } else if (desc==-1) {
                hql += " asc"; //升序
            }
        }
        List<Student> ls = findPageByQuery(hql, first, pagesize);
        return ls;
    }

    @Override
    public int searchStudentNum(List<String> strList, String lesson, String way, String net, String sex, String province, String city, String district, String status, String extOrder, int desc) {
        String hql = "select count(distinct s) from Student s right join s.demand d";       
        String where = " where ";
        String or = " or ";
        String and = " and ";
        for (int i = 0; i < strList.size(); i++) {
            String keyword = strList.get(i);
            if(keyword.equals(""))
                continue;
            where += "(d.lesson like '%" + keyword + "%'";
            where += or;
            where += "d.address like '%" + keyword + "%'";
            where += or;
            where += "s.name like '%" + keyword + "%')";
            where += and;
        }
        if(lesson != null && !lesson.equals(""))
            where += "d.lesson like '%" + lesson + "%' and ";
        if(way != null && !way.equals(""))
            where += "d.way like '% '" + way + "%' and ";
        if(net != null && !net.equals(""))
            where += "s.net = '" + net + "' and ";
        if(sex != null && !sex.equals(""))
            where += "s.sex ='" + sex + "' and ";
        if(province != null && !province.equals(""))
            where += "s.province = '" + province + "' and ";
        if(city != null && !city.equals(""))
            where += "s.city = '" + city + "' and ";
        if(district != null && !district.equals(""))
            where += "s.district = '" + district + "' and ";
        if(status != null && !status.equals(""))
            where += "s.status = '" + status + "' and ";
        if(!where.equals(" where ")){
            //where += "l.delete=0";
            hql += where.substring(0, where.length() -4);
        }
        if (extOrder!=null){
            hql += " order by d." + extOrder;
            if (desc==1) {
                hql += " desc"; //降序
            } else if (desc==-1) {
                hql += " asc"; //升序
            }
        }
        return getNumber(hql).intValue();
    }
    
}
