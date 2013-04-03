/**
 *
 * @author Jerry
 */
package com.haijiao.SupportService.dao;
import com.haijiao.Domain.bean.Teacher;
import java.util.List;

public interface ITeacherDAO extends GenericDAO<Teacher,Integer> {
    public Teacher getTeacherByEmail(String email);
     //搜索老师（名字，年级，科目、网络状况）
    public List<Teacher> searchTeacher(List<String> strList);
}
