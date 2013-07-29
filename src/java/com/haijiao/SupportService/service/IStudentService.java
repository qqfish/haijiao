/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

//import com.haijiao.Domain.bean.Clazz;
//import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.bean.Student;
import java.sql.Date;

/**
 *
 * @author Jerry
 */
public interface IStudentService {
    public Student getStudentByEmail(String email);
    
    //修改基本资料
    public boolean changeInfo(String email, String name, String sex, Date birthday, String grade, String school, String tel, String telType);
    //充值
    public boolean topUpMoney(String email, int numberOfCoin);
    
//    //获取学生时间表
//    public List<Clazz> getClasses(String email);
//    
//    public List<Clazz> getTodayClasses(String email);
}
