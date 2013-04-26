/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.room.Room;
import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.service.IRoomService;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author fish
 */
@Service
public class RoomServiceImpl implements IRoomService {

    class TeaAndStu {

        private String teaEmail;
        private String stuEmail;

        public String getTeaEmail() {
            return teaEmail;
        }

        public void setTeaEmail(String teaEmail) {
            this.teaEmail = teaEmail;
        }

        public String getStuEmail() {
            return stuEmail;
        }

        public void setStuEmail(String stuEmail) {
            this.stuEmail = stuEmail;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 79 * hash + (this.teaEmail != null ? this.teaEmail.hashCode() : 0);
            hash = 79 * hash + (this.stuEmail != null ? this.stuEmail.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final TeaAndStu other = (TeaAndStu) obj;
            if ((this.teaEmail == null) ? (other.teaEmail != null) : !this.teaEmail.equals(other.teaEmail)) {
                return false;
            }
            if ((this.stuEmail == null) ? (other.stuEmail != null) : !this.stuEmail.equals(other.stuEmail)) {
                return false;
            }
            return true;
        }
    }
    private static Map<TeaAndStu, Room> roomTable;
    @Resource
    IClassService classService;

    @Override
    public Room checkAndApplyRoom(int clazzId) {
        if (roomTable == null) {
            roomTable = new Hashtable();
        }
        Clazz clazz = classService.getClazzById(clazzId);
        if (clazz == null) {
            return null;
        }
        if (clazz.getStudent() == null) {
            return null;
        }
        
        TeaAndStu aas = new TeaAndStu();
        aas.setStuEmail(clazz.getStudent().getEmail());
        aas.setTeaEmail(clazz.getFreeTime().getTeacher().getEmail());
        Room result = roomTable.get(aas);
        if (result == null) {
            result = new Room(clazz.getFreeTime().getTeacher());
            result.addAttendance(clazz.getStudent());
            roomTable.put(aas, result);
        }
        return result;
    }

    @Override
    public void removeRoom(int clazzId) {
        if (roomTable == null) {
            roomTable = new TreeMap();
        }
        Clazz clazz = classService.getClazzById(clazzId);
        if (clazz == null) {
            return;
        }
        if (clazz.getStudent() == null) {
            return;
        }
        
        TeaAndStu aas = new TeaAndStu();
        aas.setStuEmail(clazz.getStudent().getEmail());
        aas.setTeaEmail(clazz.getFreeTime().getTeacher().getEmail());
        roomTable.remove(aas);
    }

    @Override
    public void clearUnuseRoom() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IClassService getClassService() {
        return classService;
    }

    public void setClassService(IClassService classService) {
        this.classService = classService;
    }
}