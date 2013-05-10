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
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
    </head>
    <body style="background: url(images/background.jpg);">
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
                        <li><a href="#newMsg" data-toggle="tab">新消息<i class="icon-chevron-right pull-right"></i></a></li>
                        <li><a href="#allMsg" data-toggle="tab">所有消息<i class="icon-chevron-right pull-right"></i></a></li>
                    </ul>
                </div>
                <div class="span8 module" style="padding: 12px;">
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id='sendMsg'>
                            <h3>写信息</h3>
                            <hr/>
                            <div class="span7">
                                <s:form class="form-horizontal" action="sendMail.action" method="post">
                                    <h5>收件人:</h5>
                                    <s:textfield cssClass="span7" name="name" id="inputEmail" value="%{#parameters.id}"/>
                                    <h5>内容:</h5>
                                    <s:textarea cssClass="span7" name="content" rows="10"></s:textarea>
                                        <button type="submit" class="btn btn-primary pull-right span1">取消</button>
                                        <button type="submit" class="btn btn-primary pull-right span1">发送</button>
                                </s:form>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='newMsg'>
                            <h3>新信息</h3>
                            <hr/>
                            <s:iterator value="unreadMailList">
                                <div class="well">
                                    <h5><s:property value="from.name"/></h5>
                                    <p><s:property value="message"/></p>                            
                                    <a href="mail.jsp?id=<s:property value="from.id" />" class="btn btn-primary btn-mini pull-right" style="margin-top:-10px">回复</a>
                                </div>
                            </s:iterator>
                        </div>
                        <div class="tab-pane fade" id='allMsg'>
                            <h3>所有信息</h3>
                            <hr/>
                            <s:iterator value="unreadMailList">
                                <div class="well">
                                    <h5><s:property value="from.name"/></h5>
                                    <p><s:property value="message"/></p>                         
                                    <a href="mail.jsp?id=<s:property value="from.id" />" class="btn btn-primary btn-mini pull-right" style="margin-top:-10px">回复</a>
                                </div>
                            </s:iterator>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
