/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.google.gson.Gson;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.file.UserFile;
import com.haijiao.Domain.room.webFc.FcMessageInbound;
import com.haijiao.Domain.room.webFc.message.response.ResponseAddRoomFile;
import com.haijiao.Domain.room.webFc.message.response.ResponseChangeBookmark;
import com.haijiao.Domain.room.webFc.message.response.ResponseChangePage;
import com.haijiao.Domain.room.webFc.message.response.ResponseDrawShape;
import com.haijiao.Domain.room.webFc.message.response.ResponseEraseShape;
import com.haijiao.Domain.room.webFc.message.response.ResponseUploadBackground;
import com.haijiao.SupportService.SpringContext;
import com.haijiao.SupportService.service.IRoomService;
import com.haijiao.SupportService.service.ITeacherService;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.exceptions.COSVisitorException;

/**
 *
 * @author fish
 */
public class Room {

    private Gson gson;
    private String id;
    private User holder;
    private List<User> attendance;
    private List<FcMessageInbound> roomSocket;
    private List<RoomFile> roomFile;
    private RoomPage currentPage;
    private RoomTimer timer;
    private int max;
    private boolean exitBit;
    private int pin;

    public Room(User holder, int price, int max) {
        try {
            gson = new Gson();
            id = UUID.randomUUID().toString();
            this.holder = holder;
            timer = new RoomTimer(this, price);
            attendance = new ArrayList();
            attendance.add(holder);
            roomSocket = new ArrayList();
            roomFile = new ArrayList();
            roomFile.add(new RoomFile(this));
            currentPage = roomFile.get(0).getPage(0);
            this.max = max;
            this.pin = 0;
        } catch (IOException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addAttendance(User user) {
        if (!attendance.contains(user)) {
            attendance.add(user);
        }
    }

    public void removeAttendance(User user) {
        if (!user.equals(holder)) {
            attendance.remove(user);
        }
    }

    public boolean checkAttendance(User user) {
        return attendance.contains(user);
    }

    public void enterRoom(FcMessageInbound socket) {
        roomSocket.add(socket);
    }

    public boolean checkInroomUser(User user) {
        for (int i = 0; i < roomSocket.size(); i++) {
            if (user.equals(roomSocket.get(i).getUser())) {
                roomSocket.get(i).closeIt();
                roomSocket.remove(roomSocket.get(i));
            }
        }
        if (attendance.size() == 1 && roomSocket.size() < max - 1) {
            return true;
        }
        for (int i = 0; i < attendance.size(); i++) {
            //System.out.println("att: " + attendance.get(i).getEmail());
            if (attendance.get(i).equals(user)) {
                //System.out.println("ok");
                return true;
            }
        }
        return false;
    }

    public void loadFile(String name, String url) {
        RoomFile newFile = new RoomFile(name, url, this);
        roomFile.add(newFile);
        List<RoomFile> list = new ArrayList();
        list.add(newFile);
        broadcast(gson.toJson(new ResponseAddRoomFile(list)));
    }

    public boolean changePage(String uuid, int page, String tmpUri) {
        try {
            currentPage.saveTmp(tmpUri);
            return choosePage(uuid, page);
        } catch (IOException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean choosePage(String uuid, int page) {
        if (uuid != null) {
            RoomFile file = null;
            for (int i = 0; i < roomFile.size(); i++) {
                if (roomFile.get(i).getUuid().equals(uuid)) {
                    file = roomFile.get(i);
                    break;
                }
            }
            if (file == null) {
                return false;
            }
            RoomPage result = null;
            try {
                if (page < 0) {
                    result = file.getLastPage();
                } else {
                    result = file.getPage(page);
                }
            } catch (IOException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (result == null) {
                return false;
            }
            currentPage = result;
        }
        broadcast(gson.toJson(getResponseChangePage()));
        return true;
    }

    public ResponseChangePage getResponseChangePage() {
        ResponseChangePage message = new ResponseChangePage();
        message.setTotalPage(currentPage.getFile().getTotalPageNumber());
        message.setPage(currentPage.getPageNumber());
        message.setShapeList(currentPage.getShapes());
        message.setUrl(currentPage.getOriginUrl());
        message.setUuid(currentPage.getFile().getUuid());
        return message;
    }

    public void drawShape(String shape) {
        int id = currentPage.addShape(shape);
        ResponseDrawShape result = new ResponseDrawShape();
        result.setId(id);
        result.setJson(shape);
        broadcast(gson.toJson(result));
    }

    public void eraseShape(List<Integer> idArray) {
        Collections.sort(idArray);
        currentPage.deleteShape(idArray);
        ResponseEraseShape result = new ResponseEraseShape();
        result.setIdArray(idArray);
        broadcast(gson.toJson(result));
    }

    public RoomFile getOneRoomFile(String uuid) {
        for (int i = 0; i < roomFile.size(); i++) {
            if (uuid.equals(roomFile.get(i).getUuid())) {
                return roomFile.get(i);
            }
        }
        return null;
    }

    public ResponseChangeBookmark getResponseChangeBookmark() {
        RootBookmark rootBookmark = currentPage.getFile().getBookmarks();

        ResponseChangeBookmark message = new ResponseChangeBookmark(rootBookmark);
        return message;
    }

    public void uploadFile(String type, String data, String name) {
        if (type.equals("image")) {
            currentPage.setOriginUrl(data);
            choosePage(null, 0);
        } else if (type.equals("pdf") || type.equals("doc") || type.equals("ppt") || type.equals("docx")
                || type.equals("pptx") || type.equals("xls") || type.equals("xlsx")) {
            int i = data.indexOf(",");
            String rawData = data.substring(i + 1);
            RoomFile newFile = new RoomFile(name, rawData, this, type);
            roomFile.add(newFile);
            List<RoomFile> list = new ArrayList();
            list.add(newFile);
            broadcast(gson.toJson(new ResponseAddRoomFile(list)));
        }
    }

    public void releaseFile() {
        if (roomSocket.size() == 0) {
            for (int i = 0; i < roomFile.size(); i++) {
                roomFile.get(i).release();
            }
            exitBit = false;
            if (attendance.size() < 2) {
                IRoomService roomService = (IRoomService) SpringContext.getContext().getBean("roomServiceImpl");
                roomService.removeRoom((Teacher) holder);
            }
        }
    }

    public void addPin() {
        pin++;
    }

    public boolean checkPin(int p) {
        return p == pin;
    }

    public int getPin() {
        return pin;
    }

    public void broadcast(String message) {
        for (int i = 0; i < roomSocket.size(); i++) {
            try {
                CharBuffer buffer = CharBuffer.wrap(message);
                roomSocket.get(i).getWsOutbound().writeTextMessage(buffer);
            } catch (IOException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void broadcastOther(String message, FcMessageInbound from) {
        for (int i = 0; i < roomSocket.size(); i++) {
            if (!roomSocket.get(i).equals(from)) {
                try {
                    CharBuffer buffer = CharBuffer.wrap(message);
                    roomSocket.get(i).getWsOutbound().writeTextMessage(buffer);
                } catch (IOException ex) {
                    Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void sentto(String message, String userEmail) throws IOException {
        CharBuffer buffer = CharBuffer.wrap(message);
        for (int i = 0; i < roomSocket.size(); i++) {
            if (roomSocket.get(i).getUser().getEmail().equals(userEmail)) {
                roomSocket.get(i).getWsOutbound().writeTextMessage(buffer);
            }
        }
    }

    public void exitRoom(FcMessageInbound socket) {
        roomSocket.remove(socket);
        if (attendance.size() < 2 && !socket.getUser().equals(holder)) {
            ITeacherService teacherService = (ITeacherService) SpringContext.getContext().getBean("teacherServiceImpl");
            teacherService.setRoomOccupied(holder.getEmail(), null);
        }
        releaseFile();
    }

    public List<RoomFile> getRoomFile() {
        return roomFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getAttendance() {
        return attendance;
    }

    public User getHolder() {
        return holder;
    }

    public RoomTimer getTimer() {
        return timer;
    }

    public boolean isReady() {
        return roomSocket.size() > 1;
    }

    public boolean isEmpty() {
        return roomSocket.size() == 0;
    }

    public boolean checkAndUpdateExitBit() {
        boolean result = exitBit;
        if (!exitBit) {
            exitBit = true;
        }
        return result;
    }

    public String prepareDownloadFile() {
        if (currentPage == null) {
            return null;
        }
        String result = null;
        try {
            result = currentPage.getFile().getDownloadUrl();
        } catch (Exception ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<FcMessageInbound> getRoomSocket() {
        return roomSocket;
    }
}
