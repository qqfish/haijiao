/**
 *
 * @author Jerry Zou
 */
package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.ResetInfo;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.dao.ICommentDAO;
import com.haijiao.SupportService.dao.IResetInfoDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import com.haijiao.SupportService.dao.IUserDAO;
import com.haijiao.SupportService.service.IUserService;
import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    IUserDAO userDAO;
    @Resource
    ITeacherDAO teacherDAO;
    @Resource
    IStudentDAO studentDAO;
    @Resource
    ICommentDAO commentDAO;
    @Resource
    IResetInfoDAO resetInfoDAO;

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public void setStudentDAO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void setCommentDAO(ICommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public void setReserInfoDAO(IResetInfoDAO resetInfoDAO) {
        this.resetInfoDAO = resetInfoDAO;
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public boolean confirmExist(String email) {
        return userDAO.confirmExist(email);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public User getUserByEmail(String email) {
        User u = userDAO.getUserByEmail(email);
        if (u==null) {
            return null;
        }
        String type = u.getUserType();
        if (type.equals("teacher")) {
            return teacherDAO.getTeacherByEmail(email);
        } else if (type.equals("student")) {
            return studentDAO.getStudentByEmail(email);
        } else {
            return null;
        }
    }

    @Override
    public boolean setStatus(String email, int status) {
        User u = userDAO.getUserByEmail(email);
        u.setStatus(status);
        userDAO.update(u);
        return true;
    }

    @Override
    public boolean setActiveDate(String email){
        User u = userDAO.getUserByEmail(email);
        java.util.Date datetime = new java.util.Date();
        Date time = new Date(datetime.getTime());
        u.setLastActiveDate(time);
        u.setLoginNum(u.getLoginNum() +1);
        userDAO.update(u);
        return true;
    }
    
    @Override
    public boolean changePassword(String email, String password) {
        User u = userDAO.getUserByEmail(email);
        u.setPassword(password);
        userDAO.update(u);
        return true;
    }

    @Override
    public boolean changePasswordById(String id, String password) {
        User u = userDAO.findById(Integer.parseInt(id));
        u.setPassword(password);
        userDAO.update(u);
        return true;
    }
    
    @Override
    public boolean changeIntro(String email, String intro) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        t.setIntro(intro);
        teacherDAO.update(t);
        return true;
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public String confirmLogin(String email, String password) {
        return userDAO.confirmLogin(email, password);
    }

    @Override
    public boolean register(String account, String password, String userType) {
        if (userType.equals("student")) {
            Student s = new Student();
            s.setEmail(account);
            s.setPassword(password);
            s.setUserType(userType);
            java.util.Date datetime = new java.util.Date();
            Date time = new Date(datetime.getTime());
            s.setCreateTime(time);
            s.setPicUrl("images/figure-default.png"); //temp
            s.setScoreNum(0);
            s.setScore(0);
            s.setLoginNum(0);
            return studentDAO.makePersistent(s);
        } else if (userType.equals("teacher")) {
            Teacher t = new Teacher();
            t.setEmail(account);
            t.setPassword(password);
            t.setUserType(userType);
            java.util.Date datetime = new java.util.Date();
            Date time = new Date(datetime.getTime());
            t.setCreateTime(time);
            t.setPicUrl("images/page2-img1.jpg"); //temp
            t.setScoreNum(0);
            t.setScore(0);
            t.setLoginNum(0);
            t.setObNum(0);
            t.setClassNum(0);
            t.setStudentin(false);
            return teacherDAO.makePersistent(t);
        } else {
            return false;
        }
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Teacher> searchTeacher(List<String> strList, List<String> strList2) {
        return teacherDAO.searchTeacher(strList, strList2);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Teacher> searchTeacherPage(List<String> strList, String lesson, String net, String sex, String role, String province, String city, String district, String status, int first, int pagesize, String extOrder, int desc) {
        return teacherDAO.searchTeacherPage(strList, lesson, net, sex, role, province, city, district, status, first, pagesize, extOrder, desc);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public int getTeacherNum(List<String> strList, String lesson, String net, String sex, String role, String province, String city, String district, String status, String extOrder, int desc) {
        return teacherDAO.getTeacherNum(strList, lesson, net, sex, role, province, city, district, status, extOrder, desc);
    }

    @Override
    public boolean uploadFile(String account, Object file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object download(String fileuri) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPicUrl(String email, String url) {
        User user = userDAO.getUserByEmail(email);
        user.setPicUrl(url);
        userDAO.update(user);
    }

    @Override
    public boolean validateCheckcode(int id, String checkcode) {
        List<ResetInfo> lr = resetInfoDAO.getResetInfoByUser(id);
        if(lr.size() == 1 && lr.get(0).getCheckcode().equals(checkcode)){
            long between = new java.util.Date().getTime() - lr.get(0).getCreateTime().getTime() - 78360534;
            if((between / (60 * 60 * 1000)) < 1)
                return true;
            else{
                resetInfoDAO.makeTransient(lr.get(0));
                return false;
            }
        }
        else
            return false;
    }

    @Override
    public void saveResetInfo(int id, String checkCode) {
        List<ResetInfo> lr = resetInfoDAO.getResetInfoByUser(id);
        if(!lr.isEmpty()){
            for(int i =0;i < lr.size(); i ++)
                resetInfoDAO.makeTransient(lr.get(i));
        }
        ResetInfo r = new ResetInfo();
        r.setUserid(id);
        r.setCheckcode(checkCode);
        java.util.Date datetime = new java.util.Date();
        Date time = new Date(datetime.getTime());
        r.setCreateTime(time);
        resetInfoDAO.makePersistent(r);
    }
}
