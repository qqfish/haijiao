/**
 *
 * @author Jerry
 */
package com.haijiao.SupportService.dao;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.file.UserFileGroup;
import java.util.List;

public interface IUserDAO extends GenericDAO<User,Integer>{
    //验证是否存在用户名
    public boolean confirmExist(String email);
    
    public User getUserByEmail(String email);
    //验证登陆
    public String confirmLogin(String email, String password);

    //文件系统相关操作______以后要移到文件系统的Service
    //上传文件
    public boolean uploadFile(String email, Object file);
    //下载文件
    public Object download(String fileuri);

}