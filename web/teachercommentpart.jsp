<%-- 
    Document   : commentpart
    Created on : 2013-7-30, 0:48:06
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
        <table class="table table-hover table-striped">
            <tbody>
                <s:if test="pb.list.size()<=0">
                    还没有评论哦~！
                </s:if>
                <s:else>
                    <s:iterator value="pb.list" id="billList">
                        <tr>
                            <td>
                                <blockquote>
                                    <h4><s:property value="student.name" /><label class="label label-important pull-right">评分:<s:property value="stot.score" /></label></h4>
                                    <small>
                                        <span><s:property value="stot.content" /></span>
                                        <span class="pull-right">
                                            <s:if test="stot.reply == null">
                                                <a href="#reply_<s:property value="id" />" type="button" class="btn btn-info btn-mini" data-toggle="modal">回复</a>
                                            </s:if>
                                            <s:else>
                                                <a type="button" class="btn btn-info btn-mini disabled" data-toggle="modal">回复</a>
                                            </s:else>
                                        </span>
                                        <br/><br/>
                                        <s:if test="stot.reply != null">
                                            <span>您的回复：<s:property value="stot.reply" /></span>
                                        </s:if>
                                    </small>
                                    <div id="reply_<s:property value="id" />" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                            <h3 id="myModalLabel">回复</h3>
                                        </div>
                                        <s:form action="makeCommentReply.action">
                                            <div class="modal-body">
                                                <s:textfield name="id" value="%{id}" cssStyle="display:none;"></s:textfield>
                                                <s:textarea name="content" autofocus="autofocus" id="content"></s:textarea>
                                                </div>
                                                <div class="modal-footer">
                                                    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                                                <s:submit cssClass="btn btn-primary" method="reply" value="提交"></s:submit>
                                                </div>
                                        </s:form>
                                    </div>
                                </blockquote>
                            </td>
                        </tr>
                    </s:iterator>
                </s:else>
            </tbody>
        </table>
        <div class="pagination pagination-mini pull-right">
            <ul>
                <s:if test="pb.totalPage ==0"></s:if>
                <s:elseif test="pb.currentPage == 1">
                    <li class="disabled"><a href="javascript:;">Prev</a></li>
                    </s:elseif>
                    <s:else>
                    <li><s:a href="javascript:;" onclick="getCommentBillList(%{pb.currentPage -1});">
                            Prev</s:a></li>
                    </s:else>
                    <s:if test="pb.totalPage > 7">
                        <s:if test="pb.currentPage < 5">
                            <s:iterator value="new int[pb.currentPage +1]" status="i">
                                <s:if test="pb.currentPage == #i.index+1">
                                <li class="disabled"><a href="javascript:;"><s:property value="#i.index+1"/></a></li>
                                </s:if>
                                <s:else>
                                <li><s:a href="javascript:;" onclick="getCommentBillList(%{#i.index +1});">
                                        <s:property value="#i.index+1"/>
                                    </s:a></li>
                                </s:else>
                            </s:iterator>
                        </s:if>
                        <s:elseif test="pb.currentPage > totalPage - 5">
                        <li><s:a href="javascript:;" onclick="getCommentBillList(1);">1</s:a></li>
                        <li><s:a href="javascript:;" onclick="getCommentBillList(2);">2</s:a></li>
                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                            <s:iterator  value="new int[pb.totalPage - pb.currentPage +1]" status="i">
                                <s:if test="#i.index == 1">
                                <li class="disabled"><s:a href="javascript:;">
                                        <s:property value="pb.currentPage"/>
                                    </s:a></li>
                                </s:if>
                                <s:else>
                                <li><s:a href="javascript:;" onclick="getCommentBillList(%{pb.currentPage + #i.index - 1});">
                                        <s:property value="pb.currentPage + #i.index -1"/>
                                    </s:a></li>
                                </s:else>
                            </s:iterator>
                        </s:elseif>
                        <s:else>
                        <li><s:a href="javascript:;" onclick="getCommentBillList(1);">1</s:a></li>
                        <li><s:a href="javascript:;" onclick="getCommentBillList(2);">2</s:a></li>
                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                        <li><s:a href="javascript:;" onclick="getCommentBillList(%{pb.currentPage - 1});">
                                <s:property value="pb.currentPage -1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="getCommentBillList(%{pb.currentPage});">
                                <s:property value="pb.currentPage"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="getCommentBillList(%{pb.currentPage + 1});">
                                <s:property value="pb.currentPage +1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;">...</s:a></li>
                        <li><s:a href="javascript:;" onclick="getCommentBillList(%{pb.totalPage - 1});">
                                <s:property value="pb.totalPage -1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="getCommentBillList(%{pb.totalpage});">
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
                            <li><s:a href="javascript:;" onclick="getCommentBillList(%{#i.index +1});">
                                    <s:property value="#i.index+1"/>
                                </s:a></li>
                            </s:else>
                        </s:iterator>
                    </s:else>
                    <s:if test="pb.currentPage == pb.totalPage">
                    <li class="disabled"><a href="javascript:;">Next</a></li>
                    </s:if>
                <s:elseif test="pb.totalPage == 0"></s:elseif>
                    <s:else>
                    <li><s:a href="javascript:;" onclick="getCommentBillList(%{pb.currentPage +1});">
                            Next
                        </s:a></li>
                    </s:else>
            </ul>
        </div>
    </body>
</html>
