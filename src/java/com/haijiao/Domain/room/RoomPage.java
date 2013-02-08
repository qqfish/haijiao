/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteShape(int id) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getId() == id) {
                shapes.remove(i);
                return;
            }
        }
    }

    public String getTmpUrl() {
        return tmpUrl;
    }
    
    public void saveTmp(){
        
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }
}
