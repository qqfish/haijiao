<%-- 
    Document   : fileList.jsp
    Created on : 2013-3-6, 23:57:01
    Author     : Jerry
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
        <s:iterator value="fileList" id="list">
            ********<br/>
            ** 文件名：<s:property value="name"/> <br/>
            ** URL：<s:property value="url"/> <br/>
        </s:iterator>
    </body>
</html>
