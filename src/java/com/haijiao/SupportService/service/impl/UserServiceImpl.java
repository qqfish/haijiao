/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.dao.ICommentDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import com.haijiao.SupportService.dao.IUserDAO;
import com.haijiao.SupportService.service.IUserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
    @Resource
    IUserDAO userDAO;
    
    @Resource
    ITeacherDAO teacherDAO;
    
    @Resource
    IStudentDAO studentDAO;
    
    @Resource
    ICommentDAO commentDAO;

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public void setStudentDAO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public boolean confirmExist(String email) {
        return userDAO.confirmExist(email);
    }


    @Override
    public User getUserByEmail(String email) {
        User u = userDAO.getUserByEmail(email);
        String type = u.getUserType();
        if(type.equals("teacher")) {
            return teacherDAO.getTeacherByEmail(email);
        }
        else if(type.equals("student")) {
            return studentDAO.getStudentByEmail(email);
        }
        else {
            return null;
        }
    }
    
    @Override
    public boolean changePassword(String email, String password){
        Teacher t = teacherDAO.getTeacherByEmail(email);
        t.setPassword(password);
        teacherDAO.update(t);
        return true;
    }
    
    @Override
    public String confirmLogin(String email, String password) {
        return userDAO.confirmLogin(email, password);
    }

    @Override
    public boolean register(String account, String password, String userType) {
        if(userType.equals("student")){
            Student s = new Student();
            s.setEmail(account);
            s.setPassword(password);
            s.setUserType(userType);
            return studentDAO.makePersistent(s);
        }
        else if(userType.equals("teacher")){
            Teacher t = new Teacher();
            t.setEmail(account);
            t.setPassword(password);
            t.setUserType(userType);
            return teacherDAO.makePersistent(t);
        }
        else{
            return false;
        }
    }

    @Override
    public List<Teacher> searchTeacher(List<String> strList) {
        return teacherDAO.searchTeacher(strList);
    }

    @Override
    public List<Comment> getComment(String email) {
        return commentDAO.getComment(email);
    }
    
    @Override
    public List<Comment> getCommentMade(String email) {
        return commentDAO.getCommentMade(email);
    }

    @Override
    public boolean comment(String commenterEmail, String commenteeEmail, String content, Integer score) {
        User Commenter = userDAO.getUserByEmail(commenterEmail);
        User Commentee = userDAO.getUserByEmail(commenteeEmail);
        Comment c = new Comment();
        c.setCommentee(Commentee);
        c.setCommenter(Commenter);
        c.setContent(content);
        c.setScore(score);
        return commentDAO.makePersistent(c);
    }

    @Override
    public boolean uploadFile(String account, Object file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object download(String fileuri) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
