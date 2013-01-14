/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.webFc.message.request;

import java.util.List;

/**
 *
 * @author fish
 */
public class RequestEraseShape extends RequestData{
    List<Integer> idArray;

    public RequestEraseShape() {
    }

    public List<Integer> getIdArray() {
        return idArray;
    }

    public void setIdArray(List<Integer> idArray) {
        this.idArray = idArray;
    }
    
}
