/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.global;

import com.haijiao.SupportService.SpringContext;
import com.haijiao.SupportService.service.IBillService;
import java.util.TimerTask;
import javax.annotation.Resource;

/**
 *
 * @author fish
 */
public class timerTask extends TimerTask {

    @Resource
    IBillService billService;
    
    public timerTask(){
        billService = (IBillService) SpringContext.getContext().getBean("billServiceImpl");
    }

    @Override
    public void run() {
        billService.oneDayPass();
    }

}
