/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.dao.IBillDAO;
import com.haijiao.SupportService.dao.ICommentDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import com.haijiao.SupportService.service.IBillService;
import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hp
 */

@Service
@Transactional
public class BillServiceImpl implements IBillService{
    @Resource
    IBillDAO billDAO;
    @Resource
    IStudentDAO studentDAO;
    @Resource
    ITeacherDAO teacherDAO;
    @Resource
    ICommentDAO commentDAO;

    public void setBillDAO(IBillDAO billDAO) {
        this.billDAO = billDAO;
    }

    public void setStudentDAO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public void setCommentDAO(ICommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }
    
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public Bill getBillById(int id) {
        return billDAO.findById(id);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Bill> getBill(String email, String userType) {
        return billDAO.getBillByEmail(email, userType);
    }

    @Override
    public boolean produceBill(String studentEmail, String teacherEmail, int money, String message) {
        Student s = studentDAO.getStudentByEmail(studentEmail);
        Teacher t = teacherDAO.getTeacherByEmail(teacherEmail);
        Bill bill = new Bill();
        java.util.Date datetime = new java.util.Date();
        Date time = new Date(datetime.getTime());
        bill.setCreateTime(time);
        bill.setDelete(false);
        bill.setMessage(message);
        bill.setMoney(money);
        bill.setStudent(s);
        bill.setTeacher(t);
        boolean bbill = billDAO.makePersistent(bill);
        return bbill;
    }

    @Override
    public boolean commentBill(int billId, String content, int score, String userType) {
        Bill b = billDAO.findById(billId);
        if(userType.equals("teacher")){
            if(b.getTtos()==null){
                Comment c = new Comment();
                c.setContent(content);
                c.setScore(score);
                b.setTtos(c);
                Student s = b.getStudent();
                int num = s.getScoreNum();
                int newScore = (num == 0) ? score : ((num * s.getScore() + score) / (num +1)); 
                s.setScoreNum(num + 1);
                s.setScore(newScore);
                commentDAO.makePersistent(c);
                billDAO.update(b);
                return true;
            } else {
                return false;
            }
        } else {
            if(b.getStot()==null){
                Comment c = new Comment();
                c.setContent(content);
                c.setScore(score);
                b.setStot(c);
                Teacher t = b.getTeacher();
                int num = t.getScoreNum();
                int newScore = (num == 0) ? score : ((num * t.getScore() + score) / (num +1)); 
                t.setScoreNum(num + 1);
                t.setScore(newScore);
                commentDAO.makePersistent(c);
                billDAO.update(b);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean replyComment(int billId, String reply, String userType) {
        Bill b = billDAO.findById(billId);
        if(userType.equals("teacher")){
            if(b.getStot().getReply()==null || b.getStot().getReply().isEmpty()){
                b.getStot().setReply(reply);
                billDAO.update(b);
                return true;
            } else {
                return false;
            }
        } else {
            if(b.getTtos().getReply()==null || b.getTtos().getReply().isEmpty()){
                b.getTtos().setReply(reply);
                billDAO.update(b);
                return true;
            } else {
                return false;
            }
        }
    }
    
}
