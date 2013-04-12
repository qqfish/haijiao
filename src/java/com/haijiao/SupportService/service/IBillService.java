/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Bill;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface IBillService {
    //以Id来获取Bill
    public Bill getBillById(int id);
    //获取本用户的账单
    public List<Bill> getBill(String email);
    //生成账单（其实是两张账单，学生有一张，老师有一张）
    public boolean produceBill(String studentEmail, String teacherEmail, int money, boolean earn, String message);
    //评论账单（即评论本次课程）
    public boolean commentBill(int billId, String content, int score);
    //回复评论
    public boolean replyComment(int billId, String reply);
}
