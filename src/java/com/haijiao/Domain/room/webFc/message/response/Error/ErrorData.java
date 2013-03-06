/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room.webFc.message.response.Error;

import com.haijiao.Domain.room.webFc.message.Response;
import com.haijiao.Domain.room.webFc.message.response.ResponseData;

/**
 *
 * @author fish
 */
public class ErrorData extends ResponseData{
    protected int errorType;

    public ErrorData() {
        type = Response.Error;
    }

    public int getErrorType() {
        return errorType;
    }

    public void setErrorType(int errorType) {
        this.errorType = errorType;
    }
    
    
}
