/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import java.util.List;

public interface IUserService {
    //验证注册
    public boolean confirmRegister(String account, String password, String userType);
    //验证登陆
    public String confirmLogin(String account, String password);
    
    
    //对某用户进行评论
    public boolean comment(User commenter, User commentee, String content, Integer score);
    
    //搜索相关操作
    //搜索老师（名字，年级，科目、网络状况）
    public List<Teacher> searchTeacher(String name, Integer grade, String subject, String net); 
    
    //文件系统相关操作
    //上传文件
    public boolean uploadFile(String account, Object file);
    
    //下载文件
    public Object download(String fileuri);
    
}