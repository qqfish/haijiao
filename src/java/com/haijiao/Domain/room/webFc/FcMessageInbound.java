/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc;

import com.google.gson.Gson;
import com.haijiao.Domain.room.Room;
import com.haijiao.Domain.room.Shape;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.file.DataFile;
import com.haijiao.Domain.file.UserFile;
import com.haijiao.Domain.room.webFc.message.Request;
import com.haijiao.Domain.room.webFc.message.request.*;
import com.haijiao.Domain.room.webFc.message.response.*;
import com.haijiao.Domain.room.webFc.message.response.Error.ErrorData;
import com.haijiao.Domain.room.webFc.message.response.Error.ErrorType;
import com.haijiao.Domain.room.webFc.message.response.Info.InfoData;
import com.haijiao.Domain.room.webFc.message.response.Info.InfoType;
import com.haijiao.SupportService.SpringContext;
import com.haijiao.SupportService.service.IUserService;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;

/**
 *
 * @author fish
 */
public class FcMessageInbound extends MessageInbound {

    User user;
    Room room;
    Gson gson;
    IUserService userService;

    public FcMessageInbound(User user, Room room) {
        this.user = user;
        this.room = room;
        gson = new Gson();
        room.enterRoom(this);
        userService = (IUserService) SpringContext.getContext().getBean("userServiceImpl");
        this.setCharBufferMaxSize(10 * 1024 * 1024);
    }

    @Override
    protected void onOpen(WsOutbound outbound) {
        System.out.println(this.toString() + " ,new connection created");

        //info other someone enter
        InfoData enterInfo = new InfoData();
        enterInfo.setInfoType(InfoType.SomeoneEnter);
        enterInfo.setMessage(user.getName());

        for (int i = 0; i < room.getRoomSocket().size(); i++) {
            ResponseUserEnter rue = new ResponseUserEnter();
            User u = room.getRoomSocket().get(i).getUser();
            if (!u.equals(user)) {
                rue.setEmail(u.getEmail());
                rue.setName(u.getName());
                sendtoUser(gson.toJson(rue));
            } else {
                rue.setEmail(u.getEmail());
                rue.setName(u.getName());
                room.broadcastOther(gson.toJson(rue), this);
            }
        }

        room.broadcastOther(gson.toJson(enterInfo), this);
        //room file list
        sendtoUser(gson.toJson(new ResponseAddRoomFile(room.getRoomFile())));

        sendtoUser(gson.toJson(room.getResponseChangePage()));
        sendtoUser(gson.toJson(room.getResponseChangeBookmark()));
        sendPin();

        userService.setStatus(user.getEmail(), User.Status.onlineAndBusy);

        room.getTimer().toggle();

        //send to user their user file
        //sendtoUser(gson.toJson(new ResponseSetUserFile(userService.getUserFile(user.getEmail()))));
    }

    @Override
    protected void onClose(int status) {
        if(room == null) return;
        ResponseVideoChat bye = new ResponseVideoChat();
        bye.setFrom(user.getEmail());
        bye.setFromName(user.getName());
        bye.setData("{\"type\":\"bye\"}");
        room.broadcastOther(gson.toJson(bye), this);
        InfoData exitInfo = new InfoData();
        exitInfo.setInfoType(InfoType.SomeoneExit);
        exitInfo.setMessage(user.getName());
        room.broadcastOther(gson.toJson(exitInfo), this);
        room.exitRoom(this);
        room.getTimer().pause();
        System.out.println(this.toString() + "closed");
        userService.setStatus(user.getEmail(), User.Status.onlineAndAvailable);
        room = null;
    }

    @Override
    protected void onBinaryMessage(ByteBuffer bb) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void onTextMessage(CharBuffer cb) throws IOException {
        if (room == null) {
            return;
        }
        String str = cb.toString();
        System.out.println(str);
        if (str == null || str.isEmpty()) {
            return;
        }
        RequestData textData = gson.fromJson(str, RequestData.class);
        //System.out.println(textData.getType());
        //System.out.println(idRoom + " " + username);
        switch (textData.getType()) {
            case Request.TmpShape:
                RequestTmpShape rts = gson.fromJson(str, RequestTmpShape.class);
                ResponseTmpShape tmpResult = new ResponseTmpShape(rts);
                room.broadcastOther(gson.toJson(tmpResult), this);
                break;
            case Request.DrawShape:
                room.addPin();
                RequestDrawShape shape = gson.fromJson(str, RequestDrawShape.class);
                room.drawShape(shape.getJson());
                break;
            case Request.EraseShape:
                room.addPin();
                RequestEraseShape erase = gson.fromJson(str, RequestEraseShape.class);
                room.eraseShape(erase.getIdArray());
                break;
            case Request.VideoChat:
                RequestVideoChat rvc = gson.fromJson(str, RequestVideoChat.class);
                ResponseVideoChat videoResult = new ResponseVideoChat(rvc);
                videoResult.setFrom(user.getEmail());
                videoResult.setFromName(user.getName());
                if (videoResult.getTo() == null) {
                    room.broadcastOther(gson.toJson(videoResult), this);
                } else {
                    room.sentto(gson.toJson(videoResult), videoResult.getTo());
                }
                break;
            case Request.TextChat:
                RequestTextChat chat = gson.fromJson(str, RequestTextChat.class);
                ResponseTextChat chatResult = new ResponseTextChat(chat);
                chatResult.setFrom(user.getName());
                room.broadcast(gson.toJson(chatResult));
                break;
            case Request.ChangePage:
                room.addPin();
                RequestChangePage page = gson.fromJson(str, RequestChangePage.class);
                if (page.getTmpUrl() != null) {
                    room.changePage(page.getFileUuid(), page.getPage(), page.getTmpUrl());
                } else {
                    room.choosePage(page.getFileUuid(), page.getPage());
                }
                break;
            case Request.ChangeFile:
                room.addPin();
                RequestChangeFile file = gson.fromJson(str, RequestChangeFile.class);
                if (file.getTmpUrl() != null) {
                    room.changePage(file.getUuid(), -1, file.getTmpUrl());
                } else {
                    room.choosePage(file.getUuid(), -1);
                }
                sendtoUser(gson.toJson(room.getResponseChangeBookmark()));
                break;
            case Request.AddFileFromUser:
                RequestAddFileFromUser addFile = gson.fromJson(str, RequestAddFileFromUser.class);
                room.loadFile(addFile.getName(), addFile.getUrl());
//                    ErrorData error = new ErrorData();
//                    error.setErrorType(ErrorType.AddFileFromUser);
//                    sendtoUser(gson.toJson(error));
                break;
            case Request.UploadFile:
                RequestUploadFile upload = gson.fromJson(str, RequestUploadFile.class);
                room.uploadFile(upload.getPostfix(), upload.getData(), upload.getName());
                break;
            case Request.ToggleTimer:
                if (user.equals(room.getHolder())) {
                    if (!room.getTimer().toggle()) {
                        ErrorData error = new ErrorData();
                        error.setErrorType(ErrorType.NoStudentEnter);
                        sendtoUser(gson.toJson(error));
                    }
                } else {
                    ErrorData error = new ErrorData();
                    error.setErrorType(ErrorType.TimerNoPermission);
                    sendtoUser(gson.toJson(error));
                }
                break;
            case Request.StopTimer:
                if (user.equals(room.getHolder())) {
                    room.getTimer().stop();
                    InfoData info = new InfoData();
                    info.setInfoType(InfoType.ClazzFinish);
                    room.broadcast(gson.toJson(info));
                } else {
                    ErrorData error = new ErrorData();
                    error.setErrorType(ErrorType.TimerNoPermission);
                    sendtoUser(gson.toJson(error));
                }
                break;
            case Request.DownloadPdf:
                RequestDownloadPdf rdp = gson.fromJson(str, RequestDownloadPdf.class);
                room.changePage(null, -1, rdp.getTmpUrl());
                String downloadUrl = room.prepareDownloadFile();
                ResponseDownloadPDF downloadResult = new ResponseDownloadPDF();
                downloadResult.setPath(downloadUrl);
                sendtoUser(gson.toJson(downloadResult));
                break;
            case Request.CheckPin:
                RequestCheckPin rcp = gson.fromJson(str, RequestCheckPin.class);
                if (!room.checkPin(rcp.getPin())) {
                    sendtoUser(gson.toJson(room.getResponseChangePage()));
                    sendtoUser(gson.toJson(room.getResponseChangeBookmark()));
                    sendPin();
                }
                break;
        }


    }

    public User getUser() {
        return user;
    }

    public void sendtoUser(String message) {
        CharBuffer buffer = CharBuffer.wrap(message);
        try {
            getWsOutbound().writeTextMessage(buffer);
        } catch (IOException ex) {
            Logger.getLogger(FcMessageInbound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void closeIt() {
        ErrorData close = new ErrorData();
        close.setErrorType(ErrorType.Closed);
        sendtoUser(gson.toJson(close));
        ResponseVideoChat bye = new ResponseVideoChat();
        bye.setFrom(user.getEmail());
        bye.setFromName(user.getName());
        bye.setData("{\"type\":\"bye\"}");
        room.broadcastOther(gson.toJson(bye), this);
        InfoData exitInfo = new InfoData();
        exitInfo.setInfoType(InfoType.SomeoneExit);
        exitInfo.setMessage(user.getName());
        room.broadcastOther(gson.toJson(exitInfo), this);
        room.getTimer().pause();
        System.out.println(this.toString() + "closed");
        room = null;
    }

    public void sendPin() {
        ResponseSetPin rsp = new ResponseSetPin();
        rsp.setPin(room.getPin());
        sendtoUser(gson.toJson(rsp));
    }
}
