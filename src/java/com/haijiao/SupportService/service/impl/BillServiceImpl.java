/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.Lesson;
import com.haijiao.Domain.bean.Payment;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.dao.IBillDAO;
import com.haijiao.SupportService.dao.ICommentDAO;
import com.haijiao.SupportService.dao.ILessonDAO;
import com.haijiao.SupportService.dao.IPaymentDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import com.haijiao.SupportService.dao.IUserDAO;
import com.haijiao.SupportService.service.IBillService;
import com.haijiao.global.TeacherLevel;
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
public class BillServiceImpl implements IBillService {

    @Resource
    IBillDAO billDAO;
    @Resource
    IUserDAO userDAO;
    @Resource
    ILessonDAO lessonDAO;
    @Resource
    IStudentDAO studentDAO;
    @Resource
    ITeacherDAO teacherDAO;
    @Resource
    ICommentDAO commentDAO;
    @Resource
    IPaymentDAO paymentDAO;

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
    @Transactional(propagation = Propagation.SUPPORTS)
    public Bill getBillById(int id) {
        return billDAO.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Bill> getBill(String email, String userType) {
        return billDAO.getBillByEmail(email, userType);
    }

    @Override
    public List<Bill> getBillList(String email, String userType, int status, int first, int pageSize) {
        return billDAO.getBillList(email, userType, status, first, pageSize);
    }

    @Override
    public List<Bill> getUnfinishedBill(String email, String userType) {
        return billDAO.getBillByEmailStatus(email, userType, Bill.Status.paid);
    }

    @Override
    public int getBillNum(String email, String userType, int status) {
        return billDAO.getBillNum(email, userType, status);
    }

    @Override
    public boolean produceBill(String studentEmail, String teacherEmail, int hour, String lessonName, String message) {
        Student s = studentDAO.getStudentByEmail(studentEmail);
        Teacher t = teacherDAO.getTeacherByEmail(teacherEmail);
        Lesson l = lessonDAO.getLessonByName(teacherEmail, lessonName);
        Bill bill = new Bill();
        bill.setDelete(false);
        bill.setMessage(message);
        bill.setDuration(hour);
        bill.setLesson(l.getName());
        bill.setMoney(l.getPrice() * hour);
        bill.setStudent(s);
        bill.setTeacher(t);
        bill.setStatus(Bill.Status.pending);
        boolean bbill = billDAO.makePersistent(bill);
        t.setNewReserveNum(t.getNewReserveNum() + 1);
        t.setReserveNum(t.getReserveNum() + 1);
        teacherDAO.update(t);
        return bbill;
    }

    @Override
    public boolean commentBill(int billId, String content, int score, String userType) {
        Bill b = billDAO.findById(billId);
        if (userType.equals("teacher")) {
            if (b.getTtos() == null) {
                Comment c = new Comment();
                c.setContent(content);
                c.setScore(score);
                b.setTtos(c);
                Student s = b.getStudent();
                int num = s.getScoreNum();
                int newScore = (num == 0) ? score : ((num * s.getScore() + score) / (num + 1));
                s.setScoreNum(num + 1);
                s.setScore(newScore);
                commentDAO.makePersistent(c);
                billDAO.update(b);
                return true;
            } else {
                return false;
            }
        } else {
            if (b.getStot() == null) {
                Comment c = new Comment();
                c.setContent(content);
                c.setScore(score);
                b.setStot(c);
                Teacher t = b.getTeacher();
                int num = t.getScoreNum();
                int newScore = (num == 0) ? score : ((num * t.getScore() + score) / (num + 1));
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
        if (userType.equals("teacher")) {
            if (b.getStot().getReply() == null || b.getStot().getReply().isEmpty()) {
                b.getStot().setReply(reply);
                billDAO.update(b);
                return true;
            } else {
                return false;
            }
        } else {
            if (b.getTtos().getReply() == null || b.getTtos().getReply().isEmpty()) {
                b.getTtos().setReply(reply);
                billDAO.update(b);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean changeBillStatus(int billId, int status) {
        Bill b = billDAO.findById(billId);

        if (status == Bill.Status.accept || status == Bill.Status.teacherFinish) {
            Student stu = b.getStudent();
            stu.setUndealBill(stu.getUndealBill() + 1);
            studentDAO.update(stu);
        } else if (status == Bill.Status.studentCancel || status == Bill.Status.paid || status == Bill.Status.studentFinish) {
            Student stu = b.getStudent();
            stu.setUndealBill(stu.getUndealBill() - 1);
            if (status == Bill.Status.studentCancel || b.getStatus() == Bill.Status.pending) {
                Teacher tea = b.getTeacher();
                tea.setNewReserveNum(tea.getNewReserveNum() - 1);
                teacherDAO.update(tea);
            } else if (status == Bill.Status.studentCancel){
                stu.setUndealBill(stu.getUndealBill() + 1);
            }
            studentDAO.update(stu);
            if (status == Bill.Status.studentFinish) {
                billFinish(b);
            }
        }
        if (status == Bill.Status.accept || status == Bill.Status.notAccept) {
            Teacher tea = b.getTeacher();
            tea.setNewReserveNum(tea.getNewReserveNum() - 1);
            teacherDAO.update(tea);
        }
        b.setStatus(status);
        if (status == Bill.Status.teacherFinish) {
            b.setDay(Bill.CommitDay);
        }
        billDAO.update(b);
        return true;
    }

    @Override
    public List<Bill> getCommentBillList(String email, String userType, int first, int pageSize) {
        return billDAO.getCommentBillList(email, userType, first, pageSize);
    }

    @Override
    public int getCommentNum(String email) {
        return userDAO.getUserByEmail(email).getScoreNum();
    }

    @Override
    public List<Payment> getPaymentList(String email, int first, int pageSize) {
        return paymentDAO.getPaymentList(email, first, pageSize);
    }

    @Override
    public int getPaymentNum(String email) {
        return paymentDAO.getPaymentNum(email);
    }

    @Override
    public boolean changeRemark(int id, String remark) {
        Bill b = billDAO.findById(id);
        b.setMessage(remark);
        billDAO.update(b);
        return true;
    }

    @Override
    public void oneDayPass() {
        List<Bill> bList = billDAO.getBillByStatus(Bill.Status.teacherFinish);
        for (int i = 0; i < bList.size(); i++) {
            int day = bList.get(i).getDay();
            if (day <= 1) {
                billFinish(bList.get(i));
            } else {
                bList.get(i).setDay(day - 1);
                billDAO.update(bList.get(i));
            }
        }
    }

    public void billFinish(Bill b) {
        b.setStatus(Bill.Status.studentFinish);
        Teacher tea = b.getTeacher();
        int coin = tea.getCoin();

        tea.setClassNum(tea.getClassNum() + 1);
        Payment stuPay = new Payment(b);
        Payment charge = new Payment();
        charge.setMoney((int) (-stuPay.getMoney() * TeacherLevel.getPercentage(tea.getLevel())));
        charge.setType(Payment.Type.charge);
        tea.addPayment(stuPay);
        tea.addPayment(charge);
        tea.setCoin(coin + stuPay.getMoney() + charge.getMoney());
        paymentDAO.makePersistent(stuPay);
        paymentDAO.makePersistent(charge);
        teacherDAO.update(tea);
        billDAO.update(b);
        //可生成内部账单
    }
}
