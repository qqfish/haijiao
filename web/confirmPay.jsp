<%-- 
    Document   : confirmPay
    Created on : 2013-7-25, 18:39:19
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        ////////////////////////////////////请求参数//////////////////////////////////////

        //支付类型
        String payment_type = "1";
        //必填，不能修改
        //服务器异步通知页面路径
        //String notify_url = "http://www.xxx.com/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp";
        //需http://格式的完整路径，不能加?id=123这类自定义参数
        //页面跳转同步通知页面路径
        String return_url = "http://127.0.0.1:8080/haijiao/test.jsp";
        //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
        //卖家支付宝帐户
        //String seller_email = new String(request.getParameter("WIDseller_email").getBytes("ISO-8859-1"), "UTF-8");
        //必填
        //商户订单号
        String out_trade_no = String.valueOf(request.getAttribute("billId"));
        String subject = (String)request.getAttribute("message");
        String total_fee = String.valueOf(request.getAttribute("money"));
        
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", payment_type);
        //sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        //sParaTemp.put("seller_email", seller_email);
        sParaTemp.put("seller_id", AlipayConfig.partner);
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", total_fee);
        //sParaTemp.put("body", body);
        //sParaTemp.put("show_url", show_url);
        //sParaTemp.put("anti_phishing_key", anti_phishing_key);
        //sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        out.println(sHtmlText);
    %>
    <body>
    </body>
</html>
