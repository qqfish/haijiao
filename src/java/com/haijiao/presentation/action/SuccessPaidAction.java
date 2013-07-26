/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.alipay.util.AlipayNotify;
import com.haijiao.Domain.bean.Bill;
import com.haijiao.SupportService.service.IBillService;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Action("successPaid")
@Results({
    @Result(name = "success", type="redirect", location = "index.action"),
    @Result(name = "input", type="redirect", location = "index.action")
})
public class SuccessPaidAction extends RequestSessionAction{
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
                this.sessionPutIn("nextPageMessage", "支付成功");
                return SUCCESS;
            }
        }
        return INPUT;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }
    
}
