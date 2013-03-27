/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.service.GenericService;
import com.haijiao.Domain.service.ICommentService;
import java.util.List;

public class CommentServiceImpl extends GenericService<Comment,Integer> implements ICommentService {

    @Override
    public List<Comment> getComment(String acount) {
        String hql = "select distinct c from Comment c left join c.commenter u where u.email='" + acount + "'";
        return findByqQuery(hql);
    }

    @Override
    public boolean comment(User commenter, User commentee, String content, Integer score) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
