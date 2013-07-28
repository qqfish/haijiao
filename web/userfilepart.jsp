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
                                    <a class="btn btn-mini" href="javascript:;" data-toggle="modal" data-target="#movefileModal" onclick="$('#movefileName').val('<s:property value="name" />');$('#movefileSrc').val('<s:property value="groupName" />');"><i class="icon-retweet"></i>移动</a>
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
                                    <a class="btn btn-mini" data-target="#shareModal" data-toggle="modal" onclick="$('#shareFilename').val('<s:property value="name" />');$('#shareSrc').val('<s:property value="groupName" />');"><i class="icon-share-alt"></i>发布</a>
                                    </div>
                                </td>
                            </tr>
                    </s:iterator>
                </tbody>
            </table>
            <div class="modal fade hide" id="shareModal">
                <div class="modal-header">
                    <h4>发布到公共课件<small class="text-warning">(*发布的课件必须通过审核)</small></h4>
                </div>
                <s:form cssClass="form-horizontal" action="file">
                    <div class="modal-body">
                        <div class="control-group">
                            <label class="control-label">书名全称</label>
                            <div class="controls">
                                <s:textfield name="name" placeholder="书名全称" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">作者</label>
                            <div class="controls">
                                <s:textfield name="author" placeholder="作者" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">出版社</label>
                            <div class="controls">
                                <s:textfield name="publisher" placeholder="出版社" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">类型</label>
                            <div class="controls">
                                <s:select name="type" list="{'课本教材','教辅材料','试卷真题','课堂课件'}" />
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="controls">
                            <s:textfield id="shareFilename" name="name" cssStyle="display:none" />
                            <s:textfield id="shareSrc" name="src" cssStyle="display:none" />
                            <s:submit method="share" cssClass="btn btn-success" value="发布"></s:submit>
                            <button type="submit" class="btn" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                </s:form>
            </div>
        </s:else>
    </body>
</html>
