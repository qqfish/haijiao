<%-- 
    Document   : mail
    Created on : 2013-4-17, 17:23:41
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            .container-narrow {
                margin: 0 auto;
                max-width: 700px;
            }
        </style>
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
    </head>
    <body>
        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="container-narrow">
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span3">
                        <ul class="nav nav-list">
                            <li class="active"><a href="#">未读消息</a></li>
                            <li><a href="#">所有消息</a></li>
                        </ul>
                    </div>
                    <div class="span9 well">
                        <div class="row-fluid">
                            好消息
                            <div class="row-fluid">
                                <div class = "span3 pull-right">
                                    <div class="span4">name</div>
                                    <button class="btn pull-right">回复</button>
                                </div>
                            </div>
                        </div>
                        <hr/>
                        <div class="row-fluid">
                            好消息
                            <div class="row-fluid">
                                <div class = "span3 pull-right">
                                    <div class="span4">name</div>
                                    <button class="btn pull-right">回复</button>
                                </div>
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
