/**
 *
 * @author Jerry
 */
package com.haijiao.SupportService.dao;
import com.haijiao.Domain.bean.Student;
import java.util.List;


public interface IStudentDAO extends GenericDAO<Student,Integer> {
    public Student getStudentByEmail(String email);
    public List<Student> searchStudentPage(List<String> strList, String lesson, String way, String net, String sex, String province, String city, String district, String status, int first, int pagesize, String extOrder, int desc);
    public int searchStudentNum(List<String> strList, String lesson, String way, String net, String sex, String province, String city, String district, String status, String extOrder, int desc);
}
