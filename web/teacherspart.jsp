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
        <script type="text/javascript" src="js/jquery.rateit.min.js"></script>
        <link rel="stylesheet" href="css/rateit.css" type="text/css">
    </head>
    <body>
        <div class="row-fluid" style="margin-top: 10px; margin-left: 10px;">
            <ul class="thumbnails">
                <s:iterator value="pb.list" id="list">
                    <div id="resultBar">
                        <li class="span11">
                            <div class="thumbnail">
                                <img class="pull-left" style="margin: 0px 10px 0px 0px;width: 110px;height: 110px;" src="<s:property value="pic.content"/>"/>
                                <a href="${id}" style="text-decoration: none;">
                                    <b style="font-size: 20px;"><s:property value="getSecretName()"/>&nbsp;</b>
                                </a>
                                <s:if test="level==1">
                                    <i class="icon-diomand"></i>
                                </s:if>
                                <s:else>
                                    <i class="icon-"></i>
                                </s:else>
                                <s:if test="status==0"><label class="label">离线</label></s:if>
                                <s:elseif test="status==1"><label class="label label-success">在线</label></s:elseif>
                                <s:else><label class="label label-warning">忙碌</label></s:else>
                                <small class="muted" style="margin-bottom: 5px;">&nbsp;&nbsp;上次登陆时间<s:date name="lastActiveDate" nice="true"/></small>
                                <s:if test="lessons.size()==0">
                                    <label class="label pull-right">暂时没有定价</label>
                                </s:if>
                                <s:else>
                                    <label class="label pull-right"><s:property value="wagePerhour"/>元/时</label>
                                </s:else>                                                <span class="pull-right">&nbsp;</span>
                                <label class="label label-info pull-right"><s:property value="reserveNum"/>人预约</label>
                                <br/>
                                <div class="resultInfo" >
                                    <div class="rateit pull-right" data-rateit-value="<s:property value="score" default="0" />" data-rateit-ispreset="true" data-rateit-readonly="true"></div>
                                    <p><small><s:property value="school" default="暂无大学"/> | <s:property value="major" default="暂无专业"/></small></p>
                                    <p><small>身份：<s:if test="studyStatus == null">无</s:if><s:else><s:property value="studyStatus"/></s:else></small></p>
                                    <s:if test="lessons.size()==0">
                                        <p><small>开设课程：该老师还没有开课。</small></p>
                                    </s:if>
                                    <s:else>
                                        <p>
                                            <small>开设课程：
                                                <s:iterator value="lessons" status="st">
                                                    <s:if test="delete==false">
                                                        <s:property value="name"/> | 
                                                    </s:if>
                                                </s:iterator>
                                            </small>
                                        </p>
                                    </s:else>

                                    <s:if test="status==1">
                                        <s:if test="audition">
                                            <a class='btn btn-success btn-small  pull-right' href="enterPublicRoom.action?teaEmail=<s:property value='email' default='null' />">在线试讲</a>
                                        </s:if>
                                        <s:else>
                                            <a class='btn btn-success btn-small  pull-right disabled' disabled>暂停试讲</a>
                                        </s:else>
                                    </s:if>
                                    <s:elseif test="status==2">
                                        <a class="btn btn-success btn-small pull-right" style="margin-left:10px" data-toggle="modal" data-target="#publicRoom">在线试讲</a>
                                        <div class="modal fade hide" id="publicRoom">
                                            <div class="modal-body">
                                                <h3>老师忙碌，可能无法与您交流，仍要进入房间吗？</h3>
                                            </div>
                                            <div class="modal-footer">
                                                <a class="btn btn-success" href="enterPublicRoom.action?teaEmail=<s:property value='email' default='null' />">确定</a>
                                                <button class="btn" data-dismiss="modal">取消</button>
                                            </div>
                                        </div>
                                    </s:elseif>
                                    <s:elseif test="#session.email != null">
                                        <a class="btn btn btn-small pull-right" style="margin-left:5px" href="getMail.action?toEmail=<s:property value="email" />">发送私信</a>
                                    </s:elseif>
                                    <s:else>
                                        <a class="btn btn-small  pull-right" style="margin-left:5px" data-toggle="modal" data-target="#loginNote">发送私信</a>
                                        <!--<a class="btn btn-primary btn-mini" style="margin-left:5px" data-toggle="modal" data-target="#publicRoom">发送私信</a>-->
                                        <div class="modal fade hide" id="loginNote">
                                            <div class="modal-body">
                                                <h3>请先登陆</h3>
                                            </div>
                                            <div class="modal-footer">
                                                <a class="btn btn-success" href="index.action">登陆</a>
                                                <button class="btn" data-dismiss="modal">取消</button>
                                            </div>
                                        </div>
                                    </s:else>
                                    <p><small>线下授课区域：<s:property value="underlineArea" default="这个老师还没设置线下授课区域！"/></small></p>
                                </div>
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
                <s:elseif test="pb.totalPage == 0"></s:elseif>
                <s:else>
                    <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage +1});">
                            Next
                        </s:a></li>
                    </s:else>
            </ul>
        </div>
    </body>
</html>
