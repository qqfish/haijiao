/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.haijiao.Domain.file.File;
import com.haijiao.Domain.file.Index;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author fish
 */
public class RoomFile extends File{
    private List<RoomPage> pages;

    public RoomFile(File file){
        uuid = UUID.randomUUID().toString();
        fileName = file.getFileName();
        pageNum = file.getPageNum();
        postfix = file.getPostfix();
        fileUrl = file.getFileUrl();
        indexs = new ArrayList();
        for(int i = 0; i < file.getIndexs().size(); i++){
            indexs.add(new Index(file.getIndexs().get(i)));
        }
        pages = new ArrayList();
        for(int i = 0; i < pageNum; i++){
            RoomPage newPage = new RoomPage();
            newPage.setPage(i);
            newPage.setTmpUrl(fileUrl + "/" + i + "." + postfix );
        }
        
    }
    
    public boolean addIndex(String indexName, int page){
        Index index = new Index();
        index.setIndexName(indexName);
        index.setPage(page);
        if(indexs.contains(index)){
            System.out.println("duplicate index");
            return false;
        }
        indexs.add(index);
        return true;
    }
    

    public void setPages(List<RoomPage> pages) {
        this.pages = pages;
    }
    
    public RoomPage getPage(int i){
        if(i < pageNum){
            return pages.get(i);
        } else {
            return null;
        }
    }
    
    public String getUrl(){
        //change the file into a pdf and return the URL of this pdf.
        return null;
    }
    
}
