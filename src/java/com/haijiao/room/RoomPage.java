/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.room;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fish
 */
public class RoomPage {

    private int page;
    private String tmpUrl;
    private List<Shape> shapes;
    private int shapeNum;

    public RoomPage() {
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

    public void deleteShape(int id) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getId() == id) {
                shapes.remove(i);
                return;
            }
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTmpUrl() {
        return tmpUrl;
    }

    public void setTmpUrl(String tmpUrl) {
        this.tmpUrl = tmpUrl;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }
}
