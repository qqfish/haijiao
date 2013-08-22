<%-- 
    Document   : infobillpart
    Created on : 2013-7-31, 23:35:49
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
            <s:if test="pb.list.size()<=0">
                <tbody>
                    暂无交易记录哦~！
                </tbody>
            </s:if>
            <s:else>
                <thead>
                    <tr>
                        <th>时间</th>
                        <!--<th>学生姓名</th>-->
                        <th>课程</th>
                        <th>状态</th>
                        <th>金额</th>
                    </tr>
                </thead>
                <tbody>        
                    <s:iterator value="pb.list" id="billList">
                        <tr>
                            <td><s:property value="createdateToString()" /></td>
<!--                            <td><s:property value="student.name" /></td>-->
                            <td><s:property value="lesson"/>-<s:property value="duration" />课时</td>
                            <td><label class="label label-success" style="font-size:9px;">确认完成</label></td>
                            <td><strong class="text-error" style="font-size:14px;"><s:property value="money" />.00</strong></td>
                        </tr>
                    </s:iterator>
                </tbody>
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
