<%-- 
    Document   : studentspart
    Created on : 2013-8-29, 14:29:10
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
        <div class="row-fluid" style="margin-top: 10px; margin-left: 10px;">
            <ul class="thumbnails">
                <s:iterator value="pb.list" id="list">
                    <div id="resultBar">
                        <li class="span11">
                            <div class="thumbnail">
                                <label class="label pull-right"><s:property value="demand.total"/>元 / <s:property value="demand.duration"/>课时</label>
                                <label class="label label-success pull-right" style="margin-right: 5px;">已有<s:property value="demand.reserved"/>/<s:property value="demand.reserveMax"/>人接受需求</label>
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="lead" style="width:90px;margin:0;"><s:property value="getSecretName()"/></dt>
                                    <dd class="muted" style="margin-left:110px;margin-top: 10px;" id=""><s:property value="province" default="未填写省份"/> - <s:property value="city" default="未填写城市"/> - <s:property value="district" default="未填写地区"/>，<s:date name="demand.publishTime" nice="true"/></dd>
                                </dl>
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="muted" style="width:90px;">需求描述</dt>
                                    <dd class="" style="margin-left:110px;" id=""><s:property value="demand.demand"/></dd>
                                </dl>
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="muted" style="width:90px;">家教地址</dt>
                                    <dd class="" style="margin-left:110px;" id=""><s:property value="demand.address"/></dd>
                                </dl>
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="muted" style="width:90px;">课程</dt>
                                    <dd class="" style="margin-left:110px;" id=""><s:property value="demand.lesson"/></dd>
                                </dl>
                                <div class="pull-right">
                                    <button class="btn btn-mini btn-danger">接受需求</button>
                                </div>
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="muted" style="width:90px;">授课方式</dt>
                                    <dd class="" style="margin-left:110px;" id="">
                                        <span data-toggle-name="is_private" data-toggle="buttons-radio">
                                            <button type="button" class="btn btn-mini btn-choice">线上授课</button>
                                            <button type="button" class="btn btn-mini btn-choice">学生上门</button>
                                            <button type="button" class="btn btn-mini btn-choice">老师上门</button>
                                        </span>
                                    </dd>
                                </dl>
                            </div>
                        </li>
                    </div>
                </s:iterator>
            </ul>
        </div>
        <div class="pagination pagination-right">
            <ul>        
                <s:if test="pb.totalPage ==0"></s:if>
                <s:elseif test="pb.currentPage == 1">
                    <li class="disabled"><a href="javascript:;">Prev</a></li>
                    </s:elseif>
                    <s:else>
                    <li><s:a href="javascript:;" onclick="gotopage( % {pb.currentPage - 1});">
                            Prev</s:a></li>
                    </s:else>
                    <s:if test="pb.totalPage > 7">
                        <s:if test="pb.currentPage < 5">
                            <s:iterator value="new int[pb.currentPage +1]" status="i">
                                <s:if test="pb.currentPage == #i.index+1">
                                <li class="disabled"><a href="javascript:;"><s:property value="#i.index+1"/></a></li>
                                </s:if>
                                <s:else>
                                <li><s:a href="javascript:;" onclick="gotopage( % {#i.index + 1});">
                                        <s:property value="#i.index+1"/>
                                    </s:a></li>
                                </s:else>
                            </s:iterator>
                        </s:if>
                        <s:elseif test="pb.currentPage > totalPage - 5">
                        <li><s:a href="javascript:;" onclick="gotopage(1);">1</s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage(2);">2</s:a></li>
                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                            <s:iterator  value="new int[pb.totalPage - pb.currentPage +1]" status="i">
                                <s:if test="#i.index == 1">
                                <li class="disabled"><s:a href="javascript:;">
                                        <s:property value="pb.currentPage"/>
                                    </s:a></li>
                                </s:if>
                                <s:else>
                                <li><s:a href="javascript:;" onclick="gotopage( % {pb.currentPage + #i.index - 1});">
                                        <s:property value="pb.currentPage + #i.index -1"/>
                                    </s:a></li>
                                </s:else>
                            </s:iterator>
                        </s:elseif>
                        <s:else>
                        <li><s:a href="javascript:;" onclick="gotopage(1);">1</s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage(2);">2</s:a></li>
                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage( % {pb.currentPage - 1});">
                                <s:property value="pb.currentPage -1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage( % {pb.currentPage});">
                                <s:property value="pb.currentPage"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage( % {pb.currentPage + 1});">
                                <s:property value="pb.currentPage +1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;">...</s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage( % {pb.totalPage - 1});">
                                <s:property value="pb.totalPage -1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage( % {pb.totalpage});">
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
                            <li><s:a href="javascript:;" onclick="gotopage( % {#i.index + 1});">
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
                    <li><s:a href="javascript:;" onclick="gotopage( % {pb.currentPage + 1});">
                            Next
                        </s:a></li>
                    </s:else>
            </ul>
        </div>
    </body>
</html>
