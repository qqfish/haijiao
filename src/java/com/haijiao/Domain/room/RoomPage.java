/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import com.haijiao.global.config;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author fish
 */
public class RoomPage {

    private RoomFile file;
    private String originUrl;
    private String tmpUrl;
    private List<Shape> shapes;
    private int shapeNum;

    public RoomPage(RoomFile file, String originUrl) {
        this.file = file;
        this.originUrl = originUrl;
        this.tmpUrl = null;
        shapeNum = 0;
        shapes = new ArrayList();
    }

    public int addShape(String json) {
        Shape newShape = new Shape();
        newShape.setId(shapeNum);
        newShape.setJson(json);
        shapes.add(newShape);
        return shapeNum++;

    }

    public void deleteShape(List<Integer> idList) {
        //the idList must be sorted before
        int current = 0;
        for (int i = 0; i < shapes.size() && current < idList.size(); i++) {
            if (shapes.get(i).getId() == idList.get(current)) {
                shapes.remove(i);
                return;
            } else if (shapes.get(i).getId() > idList.get(current)) {
                current++;
            }
        }
    }

    public String getTmpUrl() {
        return tmpUrl;
    }

    public void saveTmp(String dataUri) throws IOException {
        //when the current page is changed, save the former page 
        if (tmpUrl == null) {
            String fileName = UUID.randomUUID().toString();
            String dirUrl = config.roomFileUrl + "/" + file.getRoom().getRoomId() + "/"
                    + file.getUuid();
            File dirFile = new File(dirUrl);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            tmpUrl = config.roomFileUrl + "/" + file.getRoom().getRoomId() + "/"
                    + file.getUuid() + "/" + fileName + "." + config.pageFilePostfix;
            File realFile = new File(tmpUrl);
            while (realFile.exists()) {
                fileName = UUID.randomUUID().toString();
                tmpUrl = config.roomFileUrl + "/" + file.getRoom().getRoomId() + "/"
                        + file.getUuid() + "/" + fileName + "." + config.pageFilePostfix;
                realFile = new File(tmpUrl);
            }
            realFile.createNewFile();
        }

        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tmpUrl));
        byte[] buffer = Base64.decode(dataUri.substring(config.pageDataUriPrefix.length()));

        out.write(buffer);
        out.flush();
        out.close();
    }

    public String getOriginDataUri() throws FileNotFoundException, IOException {
        //return the data uri of th origin image
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(originUrl));
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);

        byte[] tmp = new byte[1024];
        int len = -1;
        while ((len = in.read(tmp)) != -1) {
            out.write(tmp, 0, len);
        }
        byte[] buffer = out.toByteArray();
        String result = config.pageDataUriPrefix + Base64.encode(buffer);
        return result;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }
}
