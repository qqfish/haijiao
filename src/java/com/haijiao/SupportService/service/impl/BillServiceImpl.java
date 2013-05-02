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
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
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
    public Bill getBillById(int id) {
        return billDAO.findById(id);
    }

    @Override
    public List<Bill> getBill(String email) {
        return billDAO.getBillByEmail(email);
    }

    @Override
    public boolean produceBill(String studentEmail, String teacherEmail, int money, String message) {
        Student s = studentDAO.getStudentByEmail(studentEmail);
        Teacher t = teacherDAO.getTeacherByEmail(teacherEmail);
        Bill studentBill = new Bill();
        studentBill.setEarn(false);
        studentBill.setFrom(t);
        studentBill.setMessage(message);
        studentBill.setMoney(money);
        Bill taecherBill = new Bill();
        taecherBill.setEarn(true);
        taecherBill.setFrom(s);
        taecherBill.setMessage(message);
        taecherBill.setMoney(money);
        boolean sbill = billDAO.makePersistent(studentBill);
        boolean tbill = billDAO.makePersistent(taecherBill);
        return (sbill && tbill);
    }

    @Override
    public boolean commentBill(int billId, String content, int score) {
        Bill b = billDAO.findById(billId);
        Comment c = new Comment();
        c.setContent(content);
        c.setScore(score);
        b.setComment(c);
        commentDAO.makePersistent(c);
        billDAO.update(b);
        return true;
    }

    @Override
    public boolean replyComment(int billId, String reply) {
        Bill b = billDAO.findById(billId);
        b.getComment().setReply(reply);
        billDAO.update(b);
        return true;
    }
    
}
