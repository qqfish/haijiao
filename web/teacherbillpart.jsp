<%-- 
    Document   : billpart
    Created on : 2013-7-29, 22:48:14
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
        <table class="table table-striped" style="font-size:12px;">
            <s:if test="pb.list.size()<=0">
                <tbody>
                    暂无交易记录哦~！
                </tbody>
            </s:if>
            <s:else>
                <thead>
                    <tr>
                        <th>#</th>
                        <th class="span2">学生</th>
                        <th>课程</th>
                        <th>总计(元)</th>
                        <th>状态</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>        
                    <s:iterator value="pb.list" id="billList">
                        <tr>
                            <td><br /><s:property value="id"/></td>
                            <td><strong><s:property value="student.name" /></strong><br />
                                <span class="muted" style="font-size:9px;">电话:<s:property value="student.tel" default="无"/><br />
                                    备注:<s:property value="message" default="无" /></span>
                            </td>
                            <td><strong><s:property value="lesson.name" /></strong><br />
                                <s:property value="duration" />课时
                            </td>
                            <td><strong class="text-error" style="font-size:14px;"><br /><s:property value="money" />.00</strong></td>
                                    <s:if test="status == 0">
                                <td><br /><label class="label label-important" style="font-size:9px;">新预约</label></td>
                                <td>
                                    <s:form action="dealWithReservation" cssStyle="margin:0px; padding:0px;">
                                        <s:textfield name="billId" value="%{id}" cssStyle="display:none;"/>
                                        <br /><s:submit value="接受" method="accept" cssClass="btn btn-info btn-mini"/>
                                        <br /><s:submit value="拒绝" method="deny" cssClass="btn btn-link btn-mini"/>
                                    </s:form>
                                </td>
                            </s:if>
                            <s:elseif test="status == 1">
                                <td><br /><label class="label" style="font-size:9px;">拒绝请求</label></td>
                                <td>
                                    <br /><a class="btn btn-mini btn-link">举报</a>
                                </td>
                            </s:elseif>
                            <s:elseif test="status == 2">
                                <td><br /><label class="label label-warning" style="font-size:9px;">等待支付</label></td>
                                <td>
                                    <br /><a class="btn btn-mini btn-link">举报</a>
                                </td>
                            </s:elseif>
                            <s:elseif test="status == 3">
                                <td><br /><label class="label label-info" style="font-size:9px;">正在上课</label></td>
                                <td>
                                    <s:form action="dealWithReservation" cssStyle="margin:0px; padding:0px;">
                                        <s:textfield name="billId" value="%{id}" cssStyle="display:none;"/>
                                        <br /><s:submit value="完成" method="teacherFinish" cssClass="btn btn-mini btn-success"/>
                                        <br /><a class="btn btn-mini btn-link">举报</a>
                                    </s:form>
                                </td>
                            </s:elseif>
                            <s:elseif test="status == 4">
                                <td><br /><label class="label label-info" style="font-size:9px;">完成授课</label></td>
                                <td>
                                    <br /><a class="btn btn-mini btn-link">举报</a>
                                </td>
                            </s:elseif>
                            <s:elseif test="status >= 5">
                                <td><br /><label class="label label-success" style="font-size:9px;">确认完成</label></td>
                                <td>
                                    <s:if test="ttos==null">
                                        <br /><a id="<s:property value="id" />" onclick="$('#comment_id').val($(this).attr('id'))" href="#comment_modal" type="button" class="commentA btn btn-info btn-mini" data-toggle="modal">评论</a>
                                    </s:if>
                                    <s:else>
                                        <br /><a type="button" class="btn btn-info btn-mini disabled" data-toggle="modal">评论</a>
                                    </s:else>
                                </td>
                            </s:elseif>
                        </tr>
                    </s:iterator>
                </tbody>
                <div id="comment_modal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">评论</h3>
                    </div>
                    <s:form action="makeCommentReply.action">
                        <div class="modal-body">
                            <s:textfield name="id" id="comment_id" cssStyle="display:none;"></s:textfield>
                            内容<s:textarea name="content" autofocus="autofocus" id="content"></s:textarea>
                                <br/>
                                评分<div id="comment_rate" class="rateit" data-rateit-step="1" data-rateit-ispreset="true"></div>
                            <s:textfield id="comment_score" name="score" cssStyle="display:none;"></s:textfield>
                            </div>
                            <div class="modal-footer">
                                <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                            <s:submit cssClass="btn btn-primary" method="comment" value="提交"></s:submit>
                            </div>
                            <script type="text/javascript">
                                $(".commentA").click(function() {
                                    var id = $(this).attr("id");
                                    $("#comment_rate").bind('rated', function(event, value) {
                                        $('#comment_score').val(value);
                                    });
                                    $("#comment_rate").bind('over', function(event, value) {
                                        $(this).attr('title', value);
                                    });
                                    $("#cmtsmt").click(function(event) {
                                        if (/^\s*$/.test($('score').val()) || /^\s*$/.test($("#content").val()))
                                            event.preventDefault();
                                    });
                                });
                            </script>
                    </s:form>
                </div>
            </s:else>
        </table>
        <div class="pagination pagination-mini pull-right">
            <ul>      
                <s:if test="pb.totalPage ==0"></s:if>
                <s:elseif test="pb.currentPage == 1">
                    <li class="disabled"><a href="javascript:;">Prev</a></li>
                    </s:elseif>
                    <s:else>
                    <li><s:a href="javascript:;" onclick="getBillList(%{pb.currentPage -1});">
                            Prev</s:a></li>
                    </s:else>
                    <s:if test="pb.totalPage > 7">
                        <s:if test="pb.currentPage < 5">
                            <s:iterator value="new int[pb.currentPage +1]" status="i">
                                <s:if test="pb.currentPage == #i.index+1">
                                <li class="disabled"><a href="javascript:;"><s:property value="#i.index+1"/></a></li>
                                </s:if>
                                <s:else>
                                <li><s:a href="javascript:;" onclick="getBillList(%{#i.index +1});">
                                        <s:property value="#i.index+1"/>
                                    </s:a></li>
                                </s:else>
                            </s:iterator>
                        </s:if>
                        <s:elseif test="pb.currentPage > totalPage - 5">
                        <li><s:a href="javascript:;" onclick="getBillList(1);">1</s:a></li>
                        <li><s:a href="javascript:;" onclick="getBillList(2);">2</s:a></li>
                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                            <s:iterator  value="new int[pb.totalPage - pb.currentPage +1]" status="i">
                                <s:if test="#i.index == 1">
                                <li class="disabled"><s:a href="javascript:;">
                                        <s:property value="pb.currentPage"/>
                                    </s:a></li>
                                </s:if>
                                <s:else>
                                <li><s:a href="javascript:;" onclick="getBillList(%{pb.currentPage + #i.index - 1});">
                                        <s:property value="pb.currentPage + #i.index -1"/>
                                    </s:a></li>
                                </s:else>
                            </s:iterator>
                        </s:elseif>
                        <s:else>
                        <li><s:a href="javascript:;" onclick="getBillList(1);">1</s:a></li>
                        <li><s:a href="javascript:;" onclick="getBillList(2);">2</s:a></li>
                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                        <li><s:a href="javascript:;" onclick="getBillList(%{pb.currentPage - 1});">
                                <s:property value="pb.currentPage -1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="getBillList(%{pb.currentPage});">
                                <s:property value="pb.currentPage"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="getBillList(%{pb.currentPage + 1});">
                                <s:property value="pb.currentPage +1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;">...</s:a></li>
                        <li><s:a href="javascript:;" onclick="getBillList(%{pb.totalPage - 1});">
                                <s:property value="pb.totalPage -1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="getBillList(%{pb.totalpage});">
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
                            <li><s:a href="javascript:;" onclick="getBillList(%{#i.index +1});">
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
                    <li><s:a href="javascript:;" onclick="getBillList(%{pb.currentPage +1});">
                            Next
                        </s:a></li>
                    </s:else>
            </ul>
        </div>
    </body>
</html>
