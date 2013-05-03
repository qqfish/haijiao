<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Teachers</title>
        <meta charset="utf-8">
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/teachers.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">

        <!--[if lt IE 8]>
              <div style=' clear: both; text-align:center; position: relative;'>
                <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
                  <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
               </a>
             </div>
             
            <![endif]-->

        <!--[if lt IE 9]>
                        <script src="js/html5.js"></script>
                        <link rel="stylesheet" href="css/ie.css"> 
                 <link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400italic' rel='stylesheet' type='text/css'>
               <link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400' rel='stylesheet' type='text/css'>
                <![endif]-->

    </head>
    <body>

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="container" style="background-color: white;">
            <div class="span9 offset1">
                <div class="accordion" id="choice">
                    <div class="accordion-heading">
                        <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                        <button type="button" class="btn btn-navbar btn-primary" href="" data-toggle="button"><i class="icon-arrow-down icon-white"></i>评分</button>
                        <button type="button" class="btn btn-navbar btn-primary" href="" data-toggle="button"><i class="icon-arrow-down icon-white"></i>价格</button>
                        <button type="button" class="btn btn-navbar btn-primary" href="" data-toggle="button"><i class="icon-arrow-down icon-white"></i>人气</button>
                        </div>
                        <button class="btn btn-primary pull-right" data-toggle="collapse"  href="#collapseOne">搜索筛选<i class="icon-arrow-down icon-white"></i></button>
                    </div>
                    <div id="collapseOne" class="collapse">
                        <div class="well">
                            <button type="button" class="btn btn-danger">年级</button>
                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                <button type="button" value="0" class="btn" data-toggle="button">小学</button>
                                <button type="button" value="1" class="btn" data-toggle="button">六年级</button>
                                <button type="button" value="2" class="btn" data-toggle="button">初一</button>
                                <button type="button" value="3" class="btn" data-toggle="button">初二</button>
                                <button type="button" value="4" class="btn" data-toggle="button">初三</button>
                                <button type="button" value="5" class="btn" data-toggle="button">高一</button>
                                <button type="button" value="6" class="btn" data-toggle="button">高二</button>
                                <button type="button" value="7" class="btn" data-toggle="button">高三</button>
                            </div>
                            <p></p>
                            <button type="button" class="btn btn-danger">学科</button>
                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                <button type="button" value="0" class="btn" data-toggle="button">语文</button>
                                <button type="button" value="1" class="btn" data-toggle="button">数学</button>
                                <button type="button" value="2" class="btn" data-toggle="button">英语</button>
                                <button type="button" value="3" class="btn" data-toggle="button">物理</button>
                                <button type="button" value="4" class="btn" data-toggle="button">化学</button>
                                <button type="button" value="5" class="btn" data-toggle="button">生物</button>
                                <button type="button" value="6" class="btn" data-toggle="button">政治</button>
                                <button type="button" value="7" class="btn" data-toggle="button">历史</button>
                                <button type="button" value="8" class="btn" data-toggle="button">地理</button>
                            </div>
                            <p></p>
                            <button type="button" class="btn btn-danger">网络</button>
                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                <button type="button" value="0" class="btn" data-toggle="button">电信</button>
                                <button type="button" value="1" class="btn" data-toggle="button">网通</button>
                                <button type="button" value="2" class="btn" data-toggle="button">教育网</button>
                            </div>                
                        </div>
                    </div>
                </div>
            </div>
            <div  id="resultPanel" class="span9 offset1">
                <div class="row-fluid">
                    <ul class="thumbnails">
                        <s:iterator value="teacherlist" id="list">
                            <s:a action="getTeacherInfo.action" id="resultBar">
                                <s:param name="teacherEmail"><s:property value="email"/></s:param>
                                <li class="span3">
                                    <div class="thumbnail">
                                        <img src="<s:property value="picUrl"/>" alt="">
                                        <div class="caption">
                                            <h4><s:property value="name"/><label class="label label-important pull-right">评分：<s:property value="score"/></label></h4>
                                                <p>
                                                    <label class="label label-info">每小时报酬<s:property value="wagePerhour"/>元</label>
                                                    <s:iterator value="lessons" status="st">
                                                        <label class="label label-info" ><s:property value="name"/></label>
                                                    </s:iterator>
                                                </p>
                                        </div>
                                    </div>
                                </li>
                            </s:a>
                        </s:iterator>
                    </ul>
                </div>
            </div>
        </div>  

        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
