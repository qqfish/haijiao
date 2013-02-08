/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response;

import com.haijiao.Domain.room.webFc.message.Response;
import java.util.List;

/**
 *
 * @author fish
 */
public class ResponseEraseShape extends ResponseData{
    private List<Integer> idArray;

    public ResponseEraseShape() {
        type = Response.EraseShape; 
    }

    public List<Integer> getIdArray() {
        return idArray;
    }

    public void setIdArray(List<Integer> idArray) {
        this.idArray = idArray;
    }
    
    
}
