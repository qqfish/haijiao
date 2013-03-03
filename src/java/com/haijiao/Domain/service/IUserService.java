/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.User;

public interface IUserService {
    //验证注册
    public boolean confirmRegister(String account, String password, String userType);
    //验证登陆
    public String confirmLogin(String account, String password);
    
    
    //对某用户进行评论
    public boolean comment(User commenter, User commentee, String content, Integer score);
    //搜索相关操作....
    
    //以及文件系统相关操作....
    
}