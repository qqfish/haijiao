/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.haijiao.Domain.file.DataFile;
import com.haijiao.Domain.file.UserFile;
import com.haijiao.global.Converter;
import com.haijiao.global.config;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

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
    private String downloadUrl;

    public RoomFile(DataFile file, Room room) {
        this.room = room;
        uuid = UUID.randomUUID().toString();
        name = file.getName();
        url = file.getUrl();


    }

    public RoomFile(String name, String url, Room room) {
        this.room = room;
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.url = url;
    }

    public RoomFile(Room room) {
        this.room = room;
        uuid = UUID.randomUUID().toString();
        name = config.newDocumentName;
        url = null;
    }

    public RoomFile(String name, String data, Room room, String type) {
        try {
            this.room = room;
            uuid = UUID.randomUUID().toString();
            this.name = name;
            url = config.tmpRoomFile + "/" + this.room.getId();
            File dir = new File(url);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String tmpurl = url + "/" + uuid + "." + type;
            url = url + "/" + uuid + ".pdf";

            File newFile = new File(tmpurl);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(newFile);
            fos.write(Base64.decode(data));
            fos.close();

            if (!type.equals("pdf")) {
                File pdfFile = new File(url);
                Converter.getDocumentConverter().convert(newFile, pdfFile);
                newFile.delete();
            }
        } catch (IOException ex) {
            Logger.getLogger(RoomFile.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void checkAndOpenFile() {
        if (doc == null) {
            try {
                if (url == null) {
                    doc = new PDDocument();
                    for (int i = 0; i < 10; i++) {
                        PDPage newPage = new PDPage();
                        doc.addPage(newPage);
                    }
                } else {
                    doc = PDDocument.load(url);
                }
                if (bookmarks == null) {
                    bookmarks = new RootBookmark(doc);
                }
                if (pages == null) {
                    pages = new ArrayList();
                    for (int i = 0; i < doc.getNumberOfPages(); i++) {
                        pages.add(new RoomPage(this));
                    }
                    lastPage = 0;
                }
            } catch (IOException ex) {
                Logger.getLogger(RoomFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void checkAndSetPages() {
        if (pages == null) {
            checkAndOpenFile();
        }
    }

    public boolean addBookmark(String indexName, int page) {
        //add in doc
        return true;
    }

    public void gotoBookmark(List<Integer> indexs) {
    }

    public RoomPage getPage(int i) throws IOException {
        checkAndSetPages();
        if (i >= pages.size()) {
            i = pages.size() - 1;
        }
        lastPage = i;
        RoomPage result = pages.get(i);
        if (result.getOriginUrl() == null) {
            checkAndOpenFile();
            PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(i);
            BufferedImage image = page.convertToImage();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, config.pageImageType, os);
            byte[] bytes = os.toByteArray();
            String url = config.pageDataUriPrefix + Base64.encode(bytes);
            result.setOriginUrl(url);
        }
        return result;
    }

    public RoomPage getLastPage() throws IOException {
        return getPage(lastPage);
    }

    public String getDownloadUrl() throws IOException, COSVisitorException {
        if (downloadUrl != null) {
            return downloadUrl;
        }
        downloadUrl = config.tmpRoomFile + "/" + this.room.getId() + "/" + config.downloadDir;
        File dir = new File(downloadUrl);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        checkAndOpenFile();
        for (int i = 0; i < pages.size(); i++) {
            if (pages.get(i).getTmpUrl() != null) {
                System.out.println("page : " + pages.get(i).getTmpUrl() + "\n aaa:" + i);
                PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(i);
                if (page.getContents() != null && page.getContents().getStream() != null) {
                    page.getContents().getStream().clear();
                }
                byte[] decodedBytes = Base64.decode(pages.get(i).getTmpUrl().split("^data:image/(png|jpg);base64,")[1]);
                BufferedImage imag = ImageIO.read(new ByteArrayInputStream(decodedBytes));
                //ImageIO.write(imag, "PNG", new File("/Users/fish/test.png"));
                PDXObjectImage ximage = new PDPixelMap(doc, imag);
                PDPageContentStream contentStream = new PDPageContentStream(doc, page);
                contentStream.drawXObject(ximage, 0, 0, page.getMediaBox().getWidth(), page.getMediaBox().getHeight());
                contentStream.close();
            }
        }


        File file;
        file = new File(downloadUrl + "/" + name);
        int i = 0;
        while (file.exists()) {
            i++;
            file = new File(downloadUrl + "/" + i + name);
        }
        downloadUrl = downloadUrl + "/" + i + name;
        doc.save(downloadUrl);
        release();
        return downloadUrl;
    }

    public void addPage() {
        checkAndOpenFile();
        PDPage lastPage = (PDPage) doc.getDocumentCatalog().getAllPages().get(doc.getNumberOfPages() - 1);
        PDRectangle r = new PDRectangle(lastPage.getMediaBox().getWidth(), lastPage.getMediaBox().getHeight());
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

    public void save() throws IOException {
        if (doc != null) {
            try {
                doc.save(url);
            } catch (COSVisitorException ex) {
                Logger.getLogger(RoomFile.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void clearPage(int index) {
        checkAndOpenFile();
        if (index < doc.getNumberOfPages()) {
            try {
                PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(index);
                page.getContents().getStream().clear();




            } catch (IOException ex) {
                Logger.getLogger(RoomFile.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void release() {
        if (doc != null) {
            try {
                doc.close();
            } catch (IOException ex) {
                Logger.getLogger(RoomFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        doc = null;
    }

    public int getPageNumber(RoomPage page) {
        return pages.indexOf(page);
    }

    public int getTotalPageNumber() {
        return pages.size();
    }

    public RootBookmark getBookmarks() {
        return bookmarks;
    }

    public void pageUpdate() {
        downloadUrl = null;
    }
}
