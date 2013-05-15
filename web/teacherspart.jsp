<%-- 
    Document   : teacherspart
    Created on : 2013-5-13, 22:43:46
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
        <div style="margin:0px 12px;">
            <h3>
                老师列表
                <small>
                    <a id="normal_button">默认</a>/<a id="score_button">评分</a>/<a id="price_button">价格</a>/<a id="hot_button">人气</a>
                    <button class="btn btn-small pull-right">仅显示在线</button>
                </small>
            </h3>
            <s:form id="search2" action="searchTeacher.action" cssStyle="display:none;" method="get">
                <s:textfield name="searchContent" value="%{searchContent}"/>
                <s:textfield name="desc" />
                <s:textfield name="lessonGet" cssClass=" span3"/>
                <s:textfield name="gradeGet" cssClass=" span3"/>
                <s:submit id="normal_submit"/>
                <s:submit id="score_submit" method="score" />
                <s:submit id="hot_submit" method="hot" />
                <s:submit id="price_submit" method="price" />
            </s:form>
        </div>
        <hr style="margin:12px 12px 0px 12px;"/>
        <div class="row-fluid" style="margin: 0px 28px;">
            <ul class="thumbnails">
                <s:iterator value="pb.list" id="list">
                    <s:a action="getTeacherInfo.action" id="resultBar">
                        <s:param name="teacherEmail"><s:property value="email"/></s:param>
                            <li class="span3">
                                <div class="thumbnail">
                                    <img src="<s:property value="picUrl"/>" width="100%" alt="">
                                <div class="caption">
                                    <h4><s:property value="name"/><small>
                                            <s:if test="status==0"><label class="label">离线</label></s:if>
                                            <s:elseif test="status==1"><label class="label label-success">在线</label></s:elseif>
                                            <s:else><label class="label label-warning">忙碌</label></s:else></small>
                                        <label class="label label-important pull-right">评分：<s:property value="score"/></label></h4>
                                    <p>
                                        <label class="label label-info"><s:property value="wagePerhour"/>元/时</label>
                                        <s:iterator value="lessons" status="st">
                                            <s:if test="delete==false">
                                                <label class="label label-info" ><s:property value="name"/></label>
                                            </s:if>
                                        </s:iterator>
                                    </p>
                                </div>
                            </div>
                        </li>
                    </s:a>
                </s:iterator>
            </ul>
        </div>
        <div class="pagination pagination-right">
            <ul>        
                <s:if test="pb.currentPage == 1">
                    <li class="disabled"><a href="javascript:;">Prev</a></li>
                    </s:if>
                    <s:else>
                    <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage -1});">
                            Prev</s:a></li>
                    </s:else>
                    <s:if test="pb.totalPage > 7">
                        <s:if test="pb.currentPage < 5">
                            <s:iterator value="new int[pb.currentPage +1]" status="i">
                                <s:if test="pb.currentPage == #i.index+1">
                                <li class="disabled"><a href="javascript:;"><s:property value="#i.index+1"/></a></li>
                                </s:if>
                                <s:else>
                                <li><s:a href="javascript:;" onclick="gotopage(%{#i.index +1});">
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
                                <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage + #i.index - 1});">
                                        <s:property value="pb.currentPage + #i.index -1"/>
                                    </s:a></li>
                                </s:else>
                            </s:iterator>
                        </s:elseif>
                        <s:else>
                        <li><s:a href="javascript:;" onclick="gotopage(1);">1</s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage(2);">2</s:a></li>
                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage - 1});">
                                <s:property value="pb.currentPage -1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage});">
                                <s:property value="pb.currentPage"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage + 1});">
                                <s:property value="pb.currentPage +1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;">...</s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage(%{pb.totalPage - 1});">
                                <s:property value="pb.totalPage -1"/>
                            </s:a></li>
                        <li><s:a href="javascript:;" onclick="gotopage(%{pb.totalpage});">
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
                            <li><s:a href="javascript:;" onclick="gotopage(%{#i.index +1});">
                                    <s:property value="#i.index+1"/>
                                </s:a></li>
                            </s:else>
                        </s:iterator>
                    </s:else>
                    <s:if test="pb.currentPage == pb.totalPage">
                    <li class="disabled"><a href="javascript:;">Next</a></li>
                    </s:if>
                    <s:else>
                    <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage +1});">
                            Next
                        </s:a></li>
                    </s:else>
            </ul>
        </div>
    </body>
</html>