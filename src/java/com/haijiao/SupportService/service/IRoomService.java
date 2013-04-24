/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Clazz;

/**
 *
 * @author fish
 */
public interface IRoomService {
    public void checkAndApplyRoom(Clazz clazz);
    public void removeRoom(Clazz clazz);
}
