/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface IUserService {
    //验证是否存在用户名
    public boolean confirmExist(String email);
    public User getUserByEmail(String email);

    public void setPicUrl(String email, String url);
    public boolean setActiveDate(String email);
    
    //修改用户当前状态
    public boolean setStatus(String email, int status);
    
    //修改密码
    public boolean changePassword(String email, String password);
    public boolean changePasswordById(String id, String password);
    
    //修改详细资料
    public boolean changeIntro(String email, String intro);
    
    //验证登陆
    public String confirmLogin(String email, String password);
    
    //注册为User
    public boolean register(String email, String password, String userType);
    
    //搜索老师（名字，年级，科目、网络状况）
    public List<Teacher> searchTeacher(List<String> strList, List<String> strList2);
    public List<Teacher> searchTeacherPage(List<String> strList, String lesson, String net, String sex, String role, String province, String city, String district, String status, int first, int pagesize, String extOrder, int desc);
    public int getTeacherNum(List<String> strList, String lesson, String net, String sex, String role, String province, String city, String district, String status, String extOrder, int desc);
    
    //文件系统相关操作
    public boolean createGroup(String email, String name);
    public boolean deleteGroup(String email, String name);
    public boolean moveFile(String email, String srcName, String destName, String fileName);
    //上传文件
    
    public boolean uploadFile(String email, String groupName, String name, String fileuri);
    //下载文件

    public String download(String email, String groupName, String fileName);
    
    public boolean validateCheckcode(int id, String checkCode);
    public void saveResetInfo(int id, String checkCode);
}
