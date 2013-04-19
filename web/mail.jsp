<%-- 
    Document   : mail
    Created on : 2013-4-17, 17:23:41
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>海角教育</title>
        <meta charset="utf-8">
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
    </head>
    <body>
        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <link rel="stylesheet" href="css/tooltik.css">
        <!--==============================content=================================-->
        <div class="container">
            <div class="row">
                <div class="span3">
                    <ul class="nav nav-list bs-docs-sidenav">
                        <li class="active"><a href="#newMsg" data-toggle="tab">新消息<i class="icon-chevron-right pull-right"></i></a></li>
                        <li><a href="#allMsg" data-toggle="tab">所有消息<i class="icon-chevron-right pull-right"></i></a></li>
                    </ul>
                </div>
                <div class="span9">
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id='newMsg'>
                            <div class="well">
                                <h5>大菊花侠</h5>
                                <p>邹润阳是大白痴</p>                            
                                <button class="btn btn-primary btn-mini pull-right" style="margin-top:-10px">回复</button>
                            </div>
                            <div class="well">
                                <h5>菊花侠</h5>
                                <p>邹润阳是大白痴</p>                            
                                <button class="btn btn-primary btn-mini pull-right" style="margin-top:-10px">回复</button>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='allMsg'>
                            <div class="well">
                                <h5>菊花侠</h5>
                                <p>邹润阳是大白痴</p>                            
                                <button class="btn btn-primary btn-mini pull-right" style="margin-top:-10px">回复</button>
                            </div>
                            <div class="well">
                                <h5>菊花侠</h5>
                                <p>邹润阳是大白痴</p>                            
                                <button class="btn btn-primary btn-mini pull-right" style="margin-top:-10px">回复</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
