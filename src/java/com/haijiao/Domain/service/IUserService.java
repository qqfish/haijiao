/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import java.util.List;

public interface IUserService extends Generic<User,Integer>{
    //验证是否存在用户名
    public boolean confirmExist(String account);
    //验证登陆
    public String confirmLogin(String account, String password);
    //注册成为学生
    public boolean addStudent(String account, String password, String grade, String school, String tel, String telType);
    //注册成为老师
    public boolean addTeacher(String account, String password, String school, String tel);
    //获取所有对本用户的评论
    public List<Comment> getComment(String acount);   
    //对某用户进行评论
    public boolean comment(User commenter, User commentee, String content, Integer score);
    
    //搜索相关操作
    //搜索老师（名字，年级，科目、网络状况）
    public List<Teacher> searchTeacher(List<String> strList); 
 
    //文件系统相关操作
    //上传文件
    public boolean uploadFile(String account, Object file);
    
    //下载文件
    public Object download(String fileuri);

}