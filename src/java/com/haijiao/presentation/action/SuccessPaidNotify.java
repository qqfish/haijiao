/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.alipay.util.AlipayNotify;
import com.haijiao.Domain.bean.Bill;
import com.haijiao.SupportService.service.IBillService;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Action("successPaidNotify")
public class SuccessPaidNotify extends RequestAction{
    @Resource
    IBillService billService;

    @Override
    public String execute(){
        Map<String,String> params = new HashMap<String,String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                        valueStr = (i == values.length - 1) ? valueStr + values[i]
                                        : valueStr + values[i] + ",";
                }
                params.put(name, valueStr);
	}
        boolean verify_result = AlipayNotify.verify(params);
        if(verify_result){
            //int billId = Integer.parseInt((String) this.getOutRequest("out_trade_no"));
            //String trade_status = (String) this.getOutRequest("trade_status");
            int billId = Integer.parseInt(params.get("out_trade_no"));
            String trade_status = params.get("trade_status");
            if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
                Bill bill = billService.getBillById(billId);
                if(bill.getStatus() == Bill.Status.accept)
                    billService.changeBillStatus(billId,Bill.Status.paid);
                HttpServletResponse response = ServletActionContext.getResponse();
                try {
                    response.getWriter().write("success");
                } catch (IOException ex) {
                    Logger.getLogger(SuccessPaidNotify.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    
}