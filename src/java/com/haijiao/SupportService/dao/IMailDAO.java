/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.bean.Mail;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IMailDAO extends GenericDAO<Mail,Integer>{
    public List<Mail> getMailByEmail(String email);
}