/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.Teacher;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface IUserService {
    //验证是否存在用户名
    public boolean confirmExist(String email);
    //验证登陆
    public String confirmLogin(String email, String password);
    //注册为User
    public boolean register(String account, String password, String userType);
    //搜索老师（名字，年级，科目、网络状况）
    public List<Teacher> searchTeacher(List<String> strList);
    //获取所有对本用户的评论
    public List<Comment> getComment(String email);   
    //对某用户进行评论
    public boolean comment(String commenterEmail, String commenteeEmail, String content, Integer score);

    //文件系统相关操作
    //上传文件
    public boolean uploadFile(String account, Object file);
    //下载文件
    public Object download(String fileuri);
}
