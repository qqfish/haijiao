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
public class TeacherDAOImpl extends GenericHibernateDAO<Teacher, Integer> implements ITeacherDAO {

    @Override
    public Teacher getTeacherByEmail(String email) {
        String hql = "from Teacher where email='" + email + "'";
        List<Teacher> lt = findByQuery(hql);
        if (lt.size() == 1) {
            return lt.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Teacher> searchTeacher(List<String> strList, List<String> strList2) {
        String hql = "select distinct t from Teacher t left join t.lessons l";
        String where = " where ";
        String or = " or ";
        String and = " and ";
        for (int i = 0; i < strList.size(); i++) {
            String keyword = strList.get(i);
            where += "(l.name like '%" + keyword + "%'";
            where += or;
            where += "t.name like '%" + keyword + "%')";
            if (i + 1 < strList.size()) {
                where += and;
            }
        }
        if(!strList2.isEmpty() && !strList.isEmpty())
            where += and;
        for(int i =0; i < strList2.size();i ++){
            String keyword = strList2.get(i);
            where += "l.name like '%" + keyword + "%'";
            if (i + 1 < strList2.size()) {
                where += and;
            }
        }
        hql += where;
        List<Teacher> t = findByQuery(hql);
        return t;
    }

    @Override
    public List<Teacher> searchTeacherPage(List<String> strList, String lesson, String origin, String way, String net, String sex, String role, String school, String province, String city, String district, String status, int first, int pagesize, String extOrder, int desc) { //desc->降序
        String hql = "select distinct t from Teacher t left join t.lessons l";       
        String where = " where ";
        String or = " or ";
        String and = " and ";
        for (int i = 0; i < strList.size(); i++) {
            String keyword = strList.get(i);
            if(keyword.equals(""))
                continue;
            where += "(l.name like '%" + keyword + "%'";
            where += or;
            where += "t.name like '%" + keyword + "%')";
            where += and;
        }
        if(lesson != null && !lesson.equals(""))
            where += "l.name like '%" + lesson + "%' and ";
        if(origin != null && !origin.equals(""))
            where += "t.origin = '" + origin + "' and ";
        if(way != null && !way.equals("")){
            if(way.equals("网络授课"))
                where += "t.sprtOnline = true and ";
            if(way.equals("老师上门"))
                where += "t.sprtTUnderline = true and ";
            if(way.equals("学生上门"))
                where += "t.sprtSUnderline = true and ";
        }
        if(net != null && !net.equals(""))
            where += "t.net = '" + net + "' and ";
        if(sex != null && !sex.equals(""))
            where += "t.sex ='" + sex + "' and ";
        if(role != null && !role.equals(""))
            where += "t.studyStatus = '" + role + "' and ";
        if(school != null && !school.equals(""))
            where += "t.school = '" + school + "' and ";
        if(province != null && !province.equals(""))
            where += "t.province = '" + province + "' and ";
        if(city != null && !city.equals(""))
            where += "t.city = '" + city + "' and ";
        if(district != null && !district.equals(""))
            where += "t.district = '" + district + "' and ";
        if(status != null && !status.equals(""))
            where += "t.status = '" + status + "' and ";
        if(!where.equals(" where ")){
            //where += "l.delete=0";
            hql += where.substring(0, where.length() -4);
        }
        if (extOrder!=null){
            hql += " order by t." + extOrder;
            if (desc==1) {
                hql += " desc"; //降序
            } else if (desc==-1) {
                hql += " asc"; //升序
            }
        }
        List<Teacher> t = findPageByQuery(hql, first, pagesize);
        return t;
    }

    @Override
    public int getTeacherNum(List<String> strList,  String lesson, String origin, String way, String net, String sex, String role, String school, String province, String city, String district, String status, String extOrder, int desc){
        String hql = "select count(distinct t) from Teacher t left join t.lessons l";
        String where = " where ";
        String or = " or ";
        String and = " and ";
        for (int i = 0; i < strList.size(); i++) {
            String keyword = strList.get(i);
            if(keyword.equals(""))
                continue;
            where += "(l.name like '%" + keyword + "%'";
            where += or;
            where += "t.name like '%" + keyword + "%')";
            where += and;
        }
        if(lesson != null && !lesson.equals(""))
            where += "l.name like '%" + lesson + "%' and ";
        if(origin != null && !origin.equals(""))
            where += "t.origin = '" + origin + "' and ";
        if(way != null && !way.equals("")){
            if(way.equals("网络授课"))
                where += "t.sprtOnline = true and ";
            if(way.equals("老师上门"))
                where += "t.sprtTUnderline = true and ";
            if(way.equals("学生上门"))
                where += "t.sprtSUnderline = true and ";
        }
        if(net != null && !net.equals(""))
            where += "t.net = '" + net + "' and ";
        if(sex != null && !sex.equals(""))
            where += "t.sex ='" + sex + "' and ";
        if(role != null && !role.equals(""))
            where += "t.studyStatus = '" + role + "' and ";
        if(school != null && !school.equals(""))
            where += "t.school = '" + school + "' and ";
        if(province != null && !province.equals(""))
            where += "t.province = '" + province + "' and ";
        if(city != null && !city.equals(""))
            where += "t.city = '" + city + "' and ";
        if(district != null && !district.equals(""))
            where += "t.district = '" + district + "' and ";
        if(status != null && !status.equals(""))
            where += "t.status = '" + status + "' and ";
        if(!where.equals(" where ")){
            //where += "l.delete=0";
            hql += where.substring(0, where.length() -4);
        }
        if (extOrder!=null && !extOrder.isEmpty()){
            hql += " order by t." + extOrder;
            if (desc==1) {
                hql += " desc"; //降序
            } else if (desc==-1) {
                hql += " asc"; //升序
            }
        }
        return getNumber(hql).intValue();
    }
}
