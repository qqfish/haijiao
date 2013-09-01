/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

//import com.haijiao.Domain.bean.Clazz;
//import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.bean.Demand;
import com.haijiao.Domain.bean.Student;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface IStudentService {
    public Student getStudentByEmail(String email);
    
    //修改基本资料
    public boolean changeInfo(String email, String name, String sex, Date birthday, String grade, String school, String tel, String telType, String province, String city, String district, String net);
    //充值
    public boolean topUpMoney(String email, int numberOfCoin);
    
    public Demand getStudentDemand(String email);
    public boolean publishDemand(String email, String lesson, String demand, boolean sprtOnline, boolean sprtSUnderline, boolean sprtTUnderline, String address, int duration, int total, int deadline);
    public boolean changeDemand(String email, String lesson,  String demand, boolean sprtOnline, boolean sprtSUnderline, boolean sprtTUnderline, String address, int duration, int total);
    public boolean cancelDemand(String email);
    public boolean confirmDemand(int billId, String email);
    
    public List<Student> searchStudentPage(List<String> strList, String lesson, String way, String net, String sex, String province, String city, String district, String status, int first, int pagesize, String extOrder, int desc);
    public int searchStudentNum(List<String> strList, String lesson, String way, String net, String sex, String province, String city, String district, String status, String extOrder, int desc);

//    //获取学生时间表
//    public List<Clazz> getClasses(String email);
//    
//    public List<Clazz> getTodayClasses(String email);
}
