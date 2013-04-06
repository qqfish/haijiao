<%-- 
    Document   : redirect
    Created on : 2013-4-6, 22:06:55
    Author     : Jerry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redirecting...</title>
    </head>
    <body>
        <s:if test="#session.login">
            <s:if test="#session.userType == 'teacher'">
                <jsp:forward page="teacherInfo.jsp" />
            </s:if>
            <s:if test="#session.userType == 'student'">
                <jsp:forward page="studentInfo.jsp" />
            </s:if>
        </s:if>
        <s:else>
            <jsp:forward page="index.jsp" />
        </s:else>
    </body>
</html>
