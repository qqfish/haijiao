/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.request;

import com.haijiao.Domain.room.webFc.message.Request;
import java.util.List;

/**
 *
 * @author fish
 */
public class RequestEraseShape extends RequestData{
    List<Integer> idArray;

    public RequestEraseShape() {
        type = Request.EraseShape;
    }

    public List<Integer> getIdArray() {
        return idArray;
    }

    public void setIdArray(List<Integer> idArray) {
        this.idArray = idArray;
    }
    
    public void sort(){
        //sort the list
    }
    
}
