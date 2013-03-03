/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Student;

public interface IStudentService extends IUserService {
    public Student getStudentById(int userId);
}
