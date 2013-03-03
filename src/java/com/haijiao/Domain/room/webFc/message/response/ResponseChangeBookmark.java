/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response;

import com.haijiao.Domain.room.Bookmark;
import com.haijiao.Domain.room.RootBookmark;
import com.haijiao.Domain.room.webFc.message.Response;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author fish
 */
public class ResponseChangeBookmark extends ResponseData{
    
    private List<Bookmark> bookmarkList;
    
    public ResponseChangeBookmark(RootBookmark root){
        this.type = Response.ChangeBookmark;
        bookmarkList = root.getChildren();
    }

    public List<Bookmark> getBookmarkList() {
        return bookmarkList;
    }

    public void setBookmarkList(List<Bookmark> bookmarkList) {
        this.bookmarkList = bookmarkList;
    }
    
}
