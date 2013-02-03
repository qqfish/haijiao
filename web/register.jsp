<%-- 
    Document   : register
    Created on : 2013-1-25, 22:13:07
    Author     : Jerry
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <s:form action="register">
            <s:label value="注册系统"></s:label>
            <s:textfield name="account" label="账号"></s:textfield>
            <s:password name="password1" label="密码（第一次）"></s:password>
            <s:password name="password2" label="密码（第二次）"></s:password>
            <s:submit value="注册"></s:submit>
        </s:form>
    </body>
</html>
