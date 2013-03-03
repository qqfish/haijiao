/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.haijiao.Domain.file.DataFile;
import com.haijiao.global.config;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

/**
 *
 * @author fish
 */
public class RoomFile extends DataFile {

    private List<RoomPage> pages;
    private String uuid;
    private Room room;
    private PDDocument doc;
    private RootBookmark bookmarks;
    private int lastPage;

    public RoomFile(DataFile file, Room room) {
        try {
            this.room = room;
            uuid = UUID.randomUUID().toString();
            name = file.getName();
            fileUrl = file.getFileUrl();
            doc = PDDocument.load(fileUrl);
            bookmarks = new RootBookmark(doc);
            pages = new ArrayList();
            for (int i = 0; i < doc.getNumberOfPages(); i++) {
                pages.add(new RoomPage(this));
            }
            lastPage = 0;
        } catch (IOException ex) {
            Logger.getLogger(RoomFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public RoomFile(Room room){
        try {
            this.room = room;
            uuid = UUID.randomUUID().toString();
            name = config.newDocumentName;
            fileUrl = null;
            doc = new PDDocument();
            bookmarks = new RootBookmark(doc);
            pages = new ArrayList();
            PDPage newPage = new PDPage();
            doc.addPage(newPage);
            for (int i = 0; i < doc.getNumberOfPages(); i++) {
                pages.add(new RoomPage(this));
            }
            lastPage = 0;
        } catch (IOException ex) {
            Logger.getLogger(RoomFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public boolean addBookmark(String indexName, int page) {
        //add in doc
        return true;
    }
    
    public void gotoBookmark(List<Integer> indexs){
        
    }

    public RoomPage getPage(int i) {
        if (i < doc.getNumberOfPages()) {
            lastPage = i;
            RoomPage result = pages.get(i);
            if (result.getOriginUrl() == null) {
                try {
                    PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(i);
                    BufferedImage image = page.convertToImage();
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    ImageIO.write(image, config.pageImageType, os);
                    byte[] bytes = os.toByteArray();
                    String url = config.pageDataUriPrefix + Base64.encode(bytes);
                    result.setOriginUrl(url);
                } catch (IOException ex) {
                    Logger.getLogger(RoomFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return result;
        } else {
            return null;
        }
    }

    public RoomPage getLastPage(){
        return getPage(lastPage);
    }

    public String getUrl() {
        //change the file into a pdf and return the URL of this pdf.
        return null;
    }

    public void addPage() {
        PDPage lastPage = (PDPage) doc.getDocumentCatalog().getAllPages().get(doc.getNumberOfPages() - 1);
        PDRectangle r = new PDRectangle(lastPage.getMediaBox().getWidth(),lastPage.getMediaBox().getHeight());
        PDPage newPage = new PDPage(r);
        doc.addPage(newPage);
    }

    public Room getRoom() {
        return room;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    public void save() throws IOException{
        try {
            doc.save(fileUrl);
        } catch (COSVisitorException ex) {
            Logger.getLogger(RoomFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void clearPage(int index) {
        if(index < doc.getNumberOfPages()){
            try {
                PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(index);
                page.getContents().getStream().clear();
            } catch (IOException ex) {
                Logger.getLogger(RoomFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int getPageNumber(RoomPage page) {
        return pages.indexOf(page);
    }

    public RootBookmark getBookmarks() {
        return bookmarks;
    }
    
}
