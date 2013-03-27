/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.service;

import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.User;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface ICommentService extends Generic<Comment,Integer>{
    //获取所有对本用户的评论
    public List<Comment> getComment(String acount);   
    //对某用户进行评论
    public boolean comment(User commenter, User commentee, String content, Integer score);
}
