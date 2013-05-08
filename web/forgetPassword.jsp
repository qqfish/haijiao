<%-- 
    Document   : forgetPassword
    Created on : 2013-5-8, 18:37:43
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <s:form action="forgetPassword.action">
            <s:textfield name="email"></s:textfield>
            <s:submit value="提交"/>
        </s:form>
    </body>
</html>
