/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.bean.upload;

import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.fileupload.ProgressListener;

/**
 *
 * @author hp
 */
public class UploadProcessListener implements ProgressListener{

    UploadProcessListener() {
    }

    @Override
    public void update(long readedBytes, long totalBytes, int currentItem) {
        String process = readedBytes * 1.0 / totalBytes * 100 + "";
        ActionContext.getContext().getSession().put("currentProcess", process);
    }
    
}
