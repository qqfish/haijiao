/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.Domain.bean.Comment;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface IBillService {
    //以Id来获取Bill
    public Bill getBillById(int id);
    //获取本用户的账单
    public List<Bill> getBill(String email, String userType);
    public List<Bill> getBillList(String email, String userType, int first, int pageSize);
    //获取账单数目
    public int getBillNum(String email, String userType);
    //获取上课中的账单
    public List<Bill> getUnfinishedBill(String email, String userType);
    //生成账单（其实是两张账单，学生有一张，老师有一张）并对账户余额进行相应操作
    public boolean produceBill(String studentEmail, String teacherEmail, int hour, String lessonName, String message);
    //评论账单（即评论本次课程）
    public boolean commentBill(int billId, String content, int score, String userType);
    //回复评论
    public boolean replyComment(int billId, String reply, String userType);
    //修改订单状态
    public boolean changeBillStatus(int billId, int status);
    //获取本用户的评论
    public List<Comment> getCommentList(String email, int first, int pageSize);
    //获取评论数
    public int getCommentNum(String email);
    //每日任务--减去确认天数
    public void oneDayPass();
}
