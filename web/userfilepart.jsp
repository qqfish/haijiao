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
                                <i class="icon-folder-close"></i><a href="javascript:;" onclick="getFileList(false, $(this).html());"><s:property value="groupName"/></a>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:else>
            <ul class="breadcrumb">
                <li><a href="javascript:;" onclick="getFileList(true, null);">我的文件</a><span class="divider">/</li>
                <li class="active"><s:property value="groupName"/></li>
            </ul>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th width="300px">
                            文件名
                        </th>
                        <th>
                            上传日期
                        </th>
                        <th>
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="pb.list">
                        <tr>
                            <td>
                                <i class="icon-file"></i><s:property value="name"/>
                            </td>
                            <td><s:property value="createdateToString()"/></td>
                            <td class="btn-toolbar">
                                <div class="btn-group">
                                    <div class="dropdown">
                                        <a class="btn btn-mini" href="javascript:;" data-toggle="modal" data-target="#movefileModal"><i class="icon-retweet"></i>移动</a>
<!--                                        <ul class="dropdown-menu">
                                            <s:iterator value="">
                                                <s:url id="moveurl" action="file" method="move">
                                                    <s:param name="src" value="%{groupName}"/>
                                                    <s:param name="dest" value=""/>
                                                    <s:param name="uploadFileName" value="%{name}"/>
                                                </s:url>
                                                <li><s:a href="%{moveurl}"><s:property value=""/></s:a></li>
                                            </s:iterator>
                                        </ul>-->
                                        <s:url id="removeurl" action="file" method="deleteFile">
                                            <s:param name="name" value="%{name}" />
                                            <s:param name="dest" value="%{groupName}" />
                                        </s:url>
                                        <s:a cssClass="btn btn-mini" href="%{removeurl}" data-toggle="tooltip" title="删除"><i class="icon-remove"></i>删除</s:a>
                                        <s:url id="downloadurl" action="download">
                                            <s:param name="downloadFileName" value="%{name}" />
                                            <s:param name="src" value="%{groupName}" />
                                        </s:url>
                                        <s:a cssClass="btn btn-mini" href="%{downloadurl}" data-toggle="tooltip" title="下载"><i class="icon-download-alt"></i>下载</s:a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </s:else>
    </body>
</html>
