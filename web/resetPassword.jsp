<%-- 
    Document   : resetPassword
    Created on : 2013-5-8, 20:37:10
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
        <s:form action="changePassword.action">
            <s:textfield name="id" value="%{id}" cssStyle="display:none;"/>
            <s:textfield name=""/>
            <s:textfield name=""/>
        </s:form>
    </body>
</html>
