/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.Domain.bean.Mail;
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
    
    //修改密码
    public boolean changePassword(String email, String password);
    //验证登陆
    public String confirmLogin(String email, String password);
    //注册为User
    public boolean register(String email, String password, String userType);
    //搜索老师（名字，年级，科目、网络状况）
    public List<Teacher> searchTeacher(List<String> strList);
    
    //获取本用户的账单
    public List<Bill> getBill(String email);
    //获取本用户收到的私信
    public List<Mail> getMail(String email);
    

    //文件系统相关操作
    //上传文件
    public boolean uploadFile(String account, Object file);
    //下载文件
    public Object download(String fileuri);
}
