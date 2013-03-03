/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response;

import com.haijiao.Domain.room.RoomFile;
import com.haijiao.Domain.room.webFc.message.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fish
 */
public class ResponseAddRoomFile extends ResponseData{
    
    private class file{
        private String fileName;
        private String uuid;

        public file() {
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 97 * hash + (this.uuid != null ? this.uuid.hashCode() : 0);
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
            final file other = (file) obj;
            if ((this.uuid == null) ? (other.uuid != null) : !this.uuid.equals(other.uuid)) {
                return false;
            }
            return true;
        }
        
        
    }
    private List<file> fileList;

    public ResponseAddRoomFile() {
        type = Response.AddRoomFile;
        fileList = new ArrayList();
    }
    
    public ResponseAddRoomFile(List<RoomFile> list){
        type = Response.AddRoomFile;
        fileList = new ArrayList();
        for(int i = 0; i < list.size(); i++){
            addFile(list.get(i));
        }
    }

    public List<file> getFileList() {
        return fileList;
    }

    public void setFileList(List<file> fileList) {
        this.fileList = fileList;
    }
    
    public void addFile(RoomFile theFile){
        file newFile = new file();
        newFile.setFileName(theFile.getName());
        newFile.setUuid(theFile.getUuid());
        
        if(!fileList.contains(newFile)){
            fileList.add(newFile);
        }
    }
    
}
