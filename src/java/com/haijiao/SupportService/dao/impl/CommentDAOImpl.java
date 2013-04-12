/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.dao.impl;

import com.haijiao.Domain.bean.Comment;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.ICommentDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl extends GenericHibernateDAO<Comment,Integer> implements ICommentDAO {

    @Override
    public List<Comment> getComment(String email) {
        String hql = "select distinct c from Comment c left join c.commentee u where u.email='" + email + "'";
        return findByQuery(hql);
    }

    @Override
    public List<Comment> getCommentMade(String email) {
        String hql = "select distinct c from Comment c left join c.commenter u where u.email='" + email + "'";
        return findByQuery(hql);
    }

}
