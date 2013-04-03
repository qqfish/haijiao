/**
 *
 * @author Jerry
 */
package com.haijiao.SupportService.dao;
import com.haijiao.Domain.bean.Student;


public interface IStudentDAO extends GenericDAO<Student,Integer> {
    public Student getStudentByEmail(String email);
}
