<%-- 
    Document   : mail
    Created on : 2013-4-17, 17:23:41
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>海角教育</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/validate.css" />
        <link rel="stylesheet" href="css/style.css"/>
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/mail.js"></script>
    </head>
    <body>
        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <link rel="stylesheet" href="css/tooltik.css">
        <!--==============================content=================================-->
        <div class="container wrapper">
            <div class="row" style="margin:-5px;">
                <div class="span3 module" style="padding: 12px;">
                    <h3>私信</h3>
                    <hr/>
                    <ul class="nav nav-list bs-docs-sidenav">
                        <li class="active"><a href="#sendMsg" data-toggle="tab">写信息<i class="icon-chevron-right pull-right"></i></a></li>
                        <li><a id="newMsgButton" href="#newMsg" data-toggle="tab">新消息<i class="icon-chevron-right pull-right"></i></a></li>
                        <li><a href="#allMsg" data-toggle="tab">所有消息<i class="icon-chevron-right pull-right"></i></a></li>
                    </ul>
                </div>
                <div class="span8 module" style="padding: 12px;">
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id="sendMsg">
                            <h3>写信息</h3>
                            <hr/>
                            <div class="span7">
                                <s:form class="form-horizontal" action="sendMail.action" method="post">
                                    <h5>收件人:</h5>
                                    <s:textfield cssClass="span7" name="name" id="inputEmail" onchange="validate_email(this,email_tip);" placeholder="请输入邮箱" value="%{#request.toEmail}"/>
                                    <div id="email_tip" class="validateTip"></div>
                                    <h5>内容:</h5>
                                    <s:textarea cssClass="span7" name="content" rows="10"></s:textarea>
                                        <button class="btn btn-primary pull-right span1">取消</button>
                                        <button type="submit" id="send" class="btn btn-primary pull-right span1">发送</button>
                                </s:form>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='newMsg'>
                            <s:if test="unreadMailList.size == 0">
                                <h3>新信息<small><a id="markAll" class="btn btn-mini pull-right disabled" type="button">标记所有为已读</a></small></h3>
                                <hr/>
                                暂无未读消息哦~！
                            </s:if>
                            <s:else>
                                <h3>新信息<small><a href="markMail!markAll.action" id="markAll" class="btn btn-mini pull-right" type="button">标记所有为已读</a></small></h3>
                                <hr/>
                                <s:iterator value="unreadMailList">
                                    <div class="well">
                                        <h5><s:property value="from.name"/><span class="muted pull-right"><s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/></span></h5>
                                        <p><s:property value="message"/></p>
                                        <a href="removeSingleMail.action?id=<s:property value="id" />" class="btn btn-danger btn-mini pull-right" style="margin-top:-10px">删除</a>
                                        <span class="pull-right">&nbsp;&nbsp;</span>
                                        <a href="javascript:;" onclick="mark(<s:property value="id" />);" class="btn btn-primary btn-mini pull-right" style="margin-top:-10px" data-loading-text="loading">标记已读</a>
                                        <span class="pull-right">&nbsp;&nbsp;</span>
                                        <a href="getMail.action?toEmail=<s:property value="from.email" />" class="btn btn-primary btn-mini pull-right" style="margin-top:-10px">回复</a>
                                    </div>
                                </s:iterator>
                            </s:else>
                        </div>
                        <div class="tab-pane fade" id='allMsg'>
                            <s:if test="allMailList.size == 0">
                                <h3>所有信息<small><a id="removeAll" class="btn btn-danger btn-mini pull-right disabled" type="button">删除所有</a></small></h3>
                                <hr/>
                                暂无消息哦~！
                            </s:if>
                            <s:else>
                                <h3>所有信息<small><a href="removeMail!removeAll.action" id="removeAll" class="btn btn-danger btn-mini pull-right" type="button">删除所有</a></small></h3>
                                <hr/>
                                <s:iterator value="allMailList">
                                    <div class="well">
                                        <h5><s:property value="from.name"/><span class="muted pull-right"><s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/></span></h5>
                                        <p><s:property value="message"/></p>
                                        <a href="removeSingleMail.action?id=<s:property value="id" />" class="btn btn-danger btn-mini pull-right" style="margin-top:-10px">删除</a>
                                        <span class="pull-right">&nbsp;&nbsp;</span>
                                        <a href="getMail.action?toEmail=<s:property value="from.email" />" class="btn btn-primary btn-mini pull-right" style="margin-top:-10px;margin-right:5px">回复</a>
                                    </div>
                                </s:iterator>
                            </s:else>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
        <s:if test="toEmail==null">
            <script>
                $("#newMsgButton").click();
            </script>
        </s:if>
    </body>
</html>
