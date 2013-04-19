<%-- 
    Document   : sendmail
    Created on : 2013-4-19, 11:24:48
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    </head>
    <body>
        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <s:form class="form-horizontal" action="sendMail.action" method="post">
            <div class="control-group">
                <label class="control-label" for="inputEmail">收件人</label>
                <div class="controls">
                    <s:textfield name="name" id="inputEmail" value="%{#parameters.id}"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputContent">内容</label>
                <div class="controls">
                    <s:textarea name="content" rows="3"></s:textarea>
                </div>
            </div>
            <div class="control-group">
                <div class="controls">
                    <button type="submit" class="btn btn-inverse">发送</button>
                </div>
            </div>
        </s:form>
    </body>
</html>
