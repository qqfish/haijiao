<%-- 
    Document   : studentbillpart
    Created on : 2013-7-31, 13:10:06
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
        <ol class="bill_nav">
            <li><span class="first">1. 老师确认</span></li>
            <li><span>2. 付款到支付宝</span></li>
            <li><span>3. 双方上课</span></li>
            <li><span>4. 上课完毕</span></li>
            <li class="last"><span>5. 学生确认放款</span></li>
        </ol>
        <!--        <div class="btn-group" style="float:right">
                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                        订单选择
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu pull-right">
                        <li><a tabindex="-1" href="#">所有订单</a></li>
                        <li><a tabindex="-1" href="#">已完成</a></li>
                        <li><a tabindex="-1" href="#">未完成</a></li>
                    </ul>
                </div>-->

        <table class="table table-striped table-hover" >
            <thead>
                <tr>
                    <th>#</th>
                    <th class="span2">老师</th>
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
                        <td><strong>
                                <a href="getMail.action?toEmail=<s:property value="teacher.email" />"><s:property value="teacher.name" /></a>
                                <s:if test="student.status==0"><label class="label">离线</label></s:if>
                                <s:elseif test="student.status==1"><label class="label label-success">在线</label></s:elseif>
                                <s:else><label class="label label-warning">忙碌</label></s:else>
                                </strong><br />
                                <span class="muted" style="font-size:9px;">电话:<s:property value="teacher.tel" default="无"/><br />
                                备注:<s:property value="message" default="无" /></span>

                        </td>
                        <td><strong><s:property value="lesson.name" /></strong><br />
                            <s:property value="duration" />课时
                        </td>
                        <td><strong class="text-error" style="font-size:14px;"><br /><s:property value="money" />.00</strong></td>

                        <s:if test="status == 0">
                            <td><br /><label class="label label-warning" style="font-size:9px;">等待确认</label></td>
                            <td>
                                <s:form action="createBill" cssStyle="margin:0px; padding:0px;">
                                    <s:textfield name="billId" value="%{id}" cssStyle="display:none;"/>
                                    <br /><s:submit value="取消" method="deny" cssClass="btn btn-mini btn-link"/>
                                </s:form>
                            </td>
                        </s:if>
                        <s:elseif test="status == 1">
                            <td><br /><label class="label" style="font-size:9px;">拒绝请求</label></td>
                            <td>
                                <br /><a class="btn btn-mini btn-link" href="contact.jsp">举报</a>
                            </td>
                        </s:elseif>
                        <s:elseif test="status == 2">
                            <td><br /><label class="label label-important" style="font-size:9px;">等待支付</label></td>
                            <td>
                                <s:form action="createBill" cssStyle="margin:0px; padding:0px;">
                                    <s:textfield name="billId" value="%{id}" cssStyle="display:none;"/>
                                    <br /><s:submit value="支付" method="accept" cssClass="btn btn-mini btn-success"/>
                                    <br /><s:submit value="取消" method="deny" cssClass="btn btn-mini btn-link"/>
                                </s:form>
                            </td>
                        </s:elseif>
                        <s:elseif test="status == 3">
                            <td>
                                <br /><label class="label label-info" style="font-size:9px;">正在上课</label>
                                <br /><a class="btn btn-link btn-mini" href="enterRoom.action?teaEmail=<s:property value="teacher.email" />&stuEmail=<s:property value="student.email" />">去上课</a>
                            </td>
                            <td>
                                <br /><a class="btn btn-mini btn-link" href="contact.jsp">举报</a>
                            </td>
                        </s:elseif>
                        <s:elseif test="status == 4">
                            <td><br /><label class="label label-important" style="font-size:9px;">完成授课</label></td>
                            <td>
                                <s:form action="dealWithReservation" cssStyle="margin:0px; padding:0px;">
                                    <s:textfield name="billId" value="%{id}" cssStyle="display:none;"/>
                                    <br /><s:submit value="完成" method="studentFinish" cssClass="btn btn-mini btn-success"/>
                                </s:form>
                                <br /><a class="btn btn-mini btn-link" href="contact.jsp">举报</a>
                            </td>
                        </s:elseif>
                        <s:elseif test="status == 8">
                            <td><br /><label class="label" style="font-size:9px;">学生取消</label></td>
                            <td></td>
                        </s:elseif>

                        <s:elseif test="status >= 5">
                            <td><br /><label class="label label-success" style="font-size:9px;">确认完成</label></td>
                            <td>
                                <s:if test="stot==null">
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

            <script type="text/javascript">
                $(".commentA").click(function() {
                    var id = $(this).attr("id");
                    $("#comment_rate").bind('rated', function(event, value) {
                        $('#comment_score').attr('value',value);
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
