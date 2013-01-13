/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.room;

import com.haijiao.file.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fish
 */
public class RoomFile {
    private String fileName;
    private int pageNum;
    private String postfix;
    private String fileUrl;
    private List<RoomIndex> indexs;
    private List<RoomPage> pages;

    public RoomFile(File file){
        fileName = file.getFileName();
        pageNum = file.getPageNum();
        postfix = file.getPostfix();
        fileUrl = file.getFileUrl();
        indexs = new ArrayList();
        for(int i = 0; i < file.getIndexs().size(); i++){
            indexs.add(new RoomIndex(file.getIndexs().get(i)));
        }
        pages = new ArrayList();
        for(int i = 0; i < pageNum; i++){
            RoomPage newPage = new RoomPage();
            newPage.setPage(i);
            newPage.setTmpUrl(fileUrl + "/" + i + "." + postfix );
        }
        
    }
    
    public boolean addIndex(String indexName, int page){
        RoomIndex index = new RoomIndex();
        index.setIndexName(indexName);
        index.setPage(page);
        if(indexs.contains(index)){
            System.out.println("duplicate index");
            return false;
        }
        indexs.add(index);
        return true;
    }

    public List<RoomIndex> getIndexs() {
        return indexs;
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setIndexs(List<RoomIndex> indexs) {
        this.indexs = indexs;
    }

    public void setPages(List<RoomPage> pages) {
        this.pages = pages;
    }
    
    
}
