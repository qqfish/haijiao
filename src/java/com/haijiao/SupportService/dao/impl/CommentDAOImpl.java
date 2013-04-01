/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.ICommentDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl extends GenericHibernateDAO<Comment,Integer> implements ICommentDAO {

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
