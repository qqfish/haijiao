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
    public int getMailNum(String email);
    public List<Mail> getUnreadMailByEmail(String email);
    public int getUnreadMailNum(String email);
    public boolean setAllMailStatus(String email);
}
