<%-- 
    Document   : publicfilepart
    Created on : 2013-7-28, 20:43:32
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
        <div style="display:inline;">
            <input id="keyword" type="text" class="span2">
            <button class="btn btn-primary" style="margin-top:-10px;" onclick="getPublicFilelist(1, $('#keyword').value());">搜索</button>
        </div>

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
                        上传用户
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
                        <td><s:property value="createtime"/></td>
                        <td><s:property value="owner"/></td>
                        <td class="btn-toolbar">
                            <div class="btn-group">
                                <div class="dropdown">
                                    <a class="btn btn-mini" data-toggle="modal" data-target="#publicfileModal"><i class="icon-tag"></i>收藏</a>
                                </div>
                            </div>

                            <div class="modal hide fade" id="publicfileModal">
                                <div class="modal-body">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <s:form action="addFileFromPublic" cssStyle="margin:0px;">
                                        <dl class="dl-horizontal">
                                            <dt style="font-size: 18px; margin-top: 4px;">文件组:</dt>
                                            <dd>
                                                <div class="input-append">
                                                    <s:select cssClass="span2" id="classify" name="groupName" list="user.fileGroups" listValue="groupName" listKey="groupName"/>
                                                    <s:hidden name="publicFileId" value="id"/>
                                                    <s:submit id="add" cssClass="btn" value="add" cssStyle="margin-left:5px;"/>
                                                </div>
                                            </dd>
                                        </dl>
                                    </s:form>
                                </div>
                            </div>
                        </td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        <div class="pagination pagination-mini pull-right">
            <ul>
                <s:if test="pb.currentPage == 1">
                    <li class="disabled"><a href="javascript:;">Prev</a></li>
                    </s:if>
                    <s:else>
                    <li><s:a href="javascript:;" onclick="getPublicFilelist(%{pb.currentPage -1});">
                            Prev</s:a></li>
                    </s:else>
                    <s:if test="pb.totalPage > 7">
                        <s:if test="pb.currentPage < 5">
                            <s:iterator value="new int[pb.currentPage +1]" status="i">
                                <s:if test="pb.currentPage == #i.index+1">
                                <li class="disabled"><a href="javascript:;"><s:property value="#i.index+1"/></a></li>
                                </s:if>
                                <s:else>
                                <li><s:a href="javascript:;" onclick="getPublicFilelist(%{#i.index +1});">
                                        <s:property value="#i.index+1"/>
                                    </s:a></li>
                                </s:else>
                            </s:iterator>
                        </s:if>
                        <s:elseif test="pb.currentPage > totalPage - 5">
                        <li><s:a href="javascript:;" onclick="getPublicFilelist(1);">1</s:a></li>
                        <li><s:a href="javascript:;" onclick="getPublicFilelist(2);">2</s:a></li>
                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                            <s:iterator  value="new int[pb.totalPage - pb.currentPage +1]" status="i">
                                <s:if test="#i.index == 1">
                                <li class="disabled"><s:a href="javascript:;">
                                        <s:property value="pb.currentPage"/>
                                    </s:a></li>
                                </s:if>
                                <s:else>
                                <li><s:a href="javascript:;" onclick="getPublicFilelist(%{pb.currentPage + #i.index - 1});">
                                        <s:property value="pb.currentPage + #i.index -1"/>
                                    </s:a></li>
                                </s:else>
                            </s:iterator>
                        </s:elseif>
                        <s:else>
                        <li><s:a href="javascript:;" onclick="getPublicFilelist(1);">1</s:a></li>
                        <li><s:a href="javascript:;" onclick="getPublicFilelist(2);">2</s:a></li>
                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                        <li><s:a href="javascript:;" onclick="getPublicFilelist(%{pb.currentPage - 1});">
                                <s:property value="pb.currentPage -1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="getPublicFilelist(%{pb.currentPage});">
                                <s:property value="pb.currentPage"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="getPublicFilelist(%{pb.currentPage + 1});">
                                <s:property value="pb.currentPage +1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;">...</s:a></li>
                        <li><s:a href="javascript:;" onclick="getPublicFilelist(%{pb.totalPage - 1});">
                                <s:property value="pb.totalPage -1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="getPublicFilelist(%{pb.totalpage});">
                                <s:property value="pb.totalPage"/>
                            </s:a></li>
                        </s:else>
                    </s:if>
                    <s:else>
                        <s:iterator value="new int[pb.totalPage]" status="i">
                            <s:if test="pb.currentPage == #i.index+1">
                            <li class="disabled"><a href="javascript:;"><s:property value="#i.index+1"/></a></li>
                            </s:if>
                            <s:else>
                            <li><s:a href="javascript:;" onclick="getPublicFilelist(%{#i.index +1});">
                                    <s:property value="#i.index+1"/>
                                </s:a></li>
                            </s:else>
                        </s:iterator>
                    </s:else>
                    <s:if test="pb.currentPage == pb.totalPage || pb.totalPage == 0">
                    <li class="disabled"><a href="javascript:;">Next</a></li>
                    </s:if>
                    <s:else>
                    <li><s:a href="javascript:;" onclick="getPublicFilelist(%{pb.currentPage +1});">
                            Next
                        </s:a></li>
                    </s:else>
            </ul>
        </div>
    </body>
</html>
