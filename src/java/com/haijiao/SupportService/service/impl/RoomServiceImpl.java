/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service.impl;

//import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.room.Room;
//import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.service.IRoomService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.global.config;
import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
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

    @Override
    public void removeRoom(Teacher teacher) {
        TeaAndStu aas = new TeaAndStu();
        aas.setTeaEmail(teacher.getEmail());
        Room room = roomTable.get(aas);
        if (room == null) {
            return;
        }
        File dir = new File(config.tmpRoomFile + "/" + room.getId());
        deleteDir(dir);
        roomTable.remove(aas);
    }

    private class RoomTimerTask extends TimerTask {

        @Override
        public void run() {
            Iterator iter = roomTable.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                TeaAndStu key = (TeaAndStu) entry.getKey();
                Room val = (Room) entry.getValue();
                if (val.isEmpty()) {
                    if (val.checkAndUpdateExitBit()) {
                        File dir = new File(config.tmpRoomFile + "/" + val.getId());
                        deleteDir(dir);
                        roomTable.remove(key);
                    }
                }
            }
        }

        private void deleteDir(File dir) {
            if (dir.exists()) {
                if (dir.isDirectory()) {
                    File delFile[] = dir.listFiles();
                    for (int i = 0; i < delFile.length; i++) {
                        deleteDir(delFile[i]);
                    }
                }
                dir.delete();
            }
        }
    }
    private static Map<TeaAndStu, Room> roomTable;
    private static Timer roomTimer;
//    @Resource
//    IClassService classService;
    @Resource
    ITeacherService teacherService;

    private void initialize() {
        roomTable = new Hashtable();
        roomTimer = new Timer();
        roomTimer.schedule(new RoomTimerTask(), 1000 * 60 * 60, 1000 * 60 * 60);
    }

    @Override
    public Room checkAndApplyRoom(Teacher tea, Student stu) {
        if (roomTable == null) {
            initialize();
        }
//        Clazz clazz = classService.getClazzById(clazzId);
//        if (clazz == null) {
//            return null;
//        }
//        if (clazz.getStudent() == null) {
//            return null;
//        }

        TeaAndStu aas = new TeaAndStu();
        aas.setStuEmail(stu.getEmail());
        aas.setTeaEmail(tea.getEmail());
        Room result = roomTable.get(aas);
        if (result == null) {
            result = new Room(tea, tea.getWagePerhour(), 2);
            result.addAttendance(stu);
            roomTable.put(aas, result);
        }
        return result;
    }

    @Override
    public Room enterPublicRoom(String teacherEmail) {
        if (roomTable == null) {
            initialize();
        }

        Teacher tea = teacherService.getTeacherByEmail(teacherEmail);

        if (tea == null) {
            return null;
        }

        TeaAndStu aas = new TeaAndStu();
        aas.setTeaEmail(teacherEmail);

        Room result = roomTable.get(aas);
        if (result == null) {
            result = new Room(tea, 0, 2);
            roomTable.put(aas, result);
        }
        return result;
    }

    @Override
    public void removeRoom(Teacher tea, Student stu) {
        if (roomTable == null) {
            initialize();
        }


        TeaAndStu aas = new TeaAndStu();
        aas.setStuEmail(stu.getEmail());
        aas.setTeaEmail(tea.getEmail());
        Room room = roomTable.get(aas);
        if (room == null) {
            return;
        }
        File dir = new File(config.tmpRoomFile + "/" + room.getId());
        deleteDir(dir);
        roomTable.remove(aas);
    }

    @Override
    public void clearUnuseRoom() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

//    public IClassService getClassService() {
//        return classService;
//    }
//
//    public void setClassService(IClassService classService) {
//        this.classService = classService;
//    }

    private void deleteRoomFile(Room room) {
    }

    private void deleteDir(File dir) {
        if (dir.exists()) {
            if (dir.isDirectory()) {
                File delFile[] = dir.listFiles();
                for (int i = 0; i < delFile.length; i++) {
                    deleteDir(delFile[i]);
                }
            }
            dir.delete();
        }
    }
}
