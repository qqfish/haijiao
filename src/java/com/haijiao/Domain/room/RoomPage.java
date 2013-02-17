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
    private String originUrl;       //data url
    private String tmpUrl;
    private List<Shape> shapes;
    private int shapeNum;

    public RoomPage(RoomFile file) {
        this.file = file;
        this.originUrl = null;
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
        tmpUrl = dataUri;
    }

    public String getOriginUrl() {
        return originUrl;
    }

    public void setOriginUrl(String originUrl) {
        this.originUrl = originUrl;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }
}
