/**
 *
 * @author Jerry
 */
package com.haijiao.SupportService.dao;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import java.util.List;

public interface ITeacherDAO extends GenericDAO<Teacher,Integer> {
    public Teacher getTeacherByEmail(String email);
    
    //注册成为老师
    public boolean addTeacher(String email, String password, String school, String tel);
     //搜索老师（名字，年级，科目、网络状况）
    public List<Teacher> searchTeacher(List<String> strList);
}
