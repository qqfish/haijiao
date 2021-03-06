/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.bean.Comment;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface ICommentDAO extends GenericDAO<Comment,Integer>{
    //获取所有对本用户的评论
    public List<Comment> getComment(String email, int first, int pageSize);
    public int getCommentNum(String email);
    //获取所有本用户做出的评论
    public List<Comment> getCommentMade(String email);
    public int getCommentMadeNum(String email);
}
