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
                    <s:iterator value="pb.list.size()" id="billList">
                        <tr>
                            <td>
                                <blockquote>
                                    <h4><s:property value="student.name" /><label class="label label-important pull-right">评分:<s:property value="score" /></label></h4>
                                    <small>
                                        <span><s:property value="content" /></span>
                                        <span class="pull-right">
                                            <s:if test="reply == null">
                                                <a href="#reply_<s:property value="id" />" type="button" class="btn btn-info btn-mini" data-toggle="modal">回复</a>
                                            </s:if>
                                            <s:else>
                                                <a type="button" class="btn btn-info btn-mini disabled" data-toggle="modal">回复</a>
                                            </s:else>
                                        </span>
                                        <br/><br/>
                                        <s:if test="reply != null">
                                            <span>您的回复：<s:property value="reply" /></span>
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
                <li><a href="#">Prev</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">Next</a></li>
            </ul>
        </div>
    </body>
</html>
