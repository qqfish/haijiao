/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.persist.Teacher;

public interface ITeacherService {
    public boolean confirmLogin(String account, String password);
    public Teacher getTeacherById(int userId);
}