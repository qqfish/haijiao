<%-- 
    Document   : userfilepart
    Created on : 2013-7-20, 21:50:12
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
        <s:if test="isDir">
            <ul class="breadcrumb">
                <li class="active">我的文件</li>
            </ul>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>
                            文件组名
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="pb.list">
                        <tr>
                            <td>
                                <i class="icon-folder-close"></i><a onclick="getFileList(false, $(this).html());"><s:property value="groupName"/></a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:else>
            <ul class="breadcrumb">
                <li><a onclick="getFileList(true, null);">我的文件</a><span class="divider">/</li>
                <li class="active"><s:property value="groupName"/></li>
            </ul>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>
                            文件名
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="pb.list">
                        <tr>
                            <td>
                                <i class="icon-file"></i><a href="javascript:;"><s:property value="name"/></a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:else>
    </body>
</html>
