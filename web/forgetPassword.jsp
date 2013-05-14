<%-- 
    Document   : forgetPassword
    Created on : 2013-5-8, 18:37:43
    Author     : hp
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Teachers</title>
        <meta charset="utf-8">
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/teachers.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">

        <!--[if lt IE 8]>
              <div style=' clear: both; text-align:center; position: relative;'>
                <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
                  <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
               </a>
             </div>
             
            <![endif]-->

        <!--[if lt IE 9]>
                        <script src="js/html5.js"></script>
                        <link rel="stylesheet" href="css/ie.css"> 
                 <link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400italic' rel='stylesheet' type='text/css'>
               <link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400' rel='stylesheet' type='text/css'>
                <![endif]-->

    </head>
    <body style="background: url(images/background.jpg);">

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="wrapper container">
            <div class="span11">
                <div class="span11 module">
                    <div class="accordion-heading" style="margin: 0px 12px;">
                        <h3>找回密码</h3>
                        <hr/>
                        请输入您注册的邮箱：
                        <s:form action="forgetPassword.action">
                            <s:textfield name="email"></s:textfield>
                            <s:submit value="提交"/>
                        </s:form>
                    </div>
                </div>
            </div>
        </div>  

        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>