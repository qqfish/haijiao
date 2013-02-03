<%-- 
    Document   : login.jsp
    Created on : 2013-1-12, 22:23:53
    Author     : Jerry Zou
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <s:form action="login">
            <s:label value="登录系统"></s:label>
            <s:textfield name="account" label="账号"></s:textfield>
            <s:password name="password" label="密码"></s:password>
            <s:submit value="登录"></s:submit>
        </s:form>
        <s:form action="toRegister">
            <s:submit value="立即注册"></s:submit>
        </s:form>
        备注：账号 zou 密码 123456
    </body>
</html>