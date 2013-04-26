/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.room.Room;

/**
 *
 * @author fish
 */
public interface IRoomService {
    public Room checkAndApplyRoom(int clazzId);
    public void removeRoom(int clazzId);
    public void clearUnuseRoom();
}
