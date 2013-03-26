/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.service.ICommentService;
import java.util.List;

public class CommentServiceImpl implements ICommentService {

    @Override
    public List<Comment> getComment(String acount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean comment(User commenter, User commentee, String content, Integer score) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
