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
    <body style="background: url(images/background.jpg);">

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="wrapper container">
            <div class="span11">
                <div class="span11 module">
                    <div class="accordion-heading" data-toggle="collapse"  href="#collapseOne" style="margin: 0px 12px;">
                        <h3>过滤器</h3>
                    </div>
                    <hr style="margin:12px 12px 0px 12px;"/>
                    <div id="collapseOne" class="collapse" style="padding-left: 16px;">
                        <button type="button" class="btn btn-danger">年级</button>
                        <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button id="prischool" type="button" value="0" class="btn grade" data-toggle="button">小学</button>
                            <button id="grade6" type="button" value="1" class="btn grade" data-toggle="button">六年级</button>
                            <button id="grade7" type="button" value="2" class="btn grade" data-toggle="button">初一</button>
                            <button id="grade8" type="button" value="3" class="btn grade" data-toggle="button">初二</button>
                            <button id="grade9" type="button" value="4" class="btn grade" data-toggle="button">初三</button>
                            <button id="grade10" type="button" value="5" class="btn grade" data-toggle="button">高一</button>
                            <button id="grade11" type="button" value="6" class="btn grade" data-toggle="button">高二</button>
                            <button id="grade12" type="button" value="7" class="btn grade" data-toggle="button">高三</button>
                        </div>
                        <p></p>
                        <button type="button" class="btn btn-danger">学科</button>
                        <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button id="chinese" type="button" value="0" class="lesson btn " data-toggle="button">语文</button>
                            <button id="math" type="button" value="1" class="btn lesson" data-toggle="button">数学</button>
                            <button id="english" type="button" value="2" class="btn lesson" data-toggle="button">英语</button>
                            <button id="physics" type="button" value="3" class="btn lesson" data-toggle="button">物理</button>
                            <button id="chem" type="button" value="4" class="btn lesson" data-toggle="button">化学</button>
                            <button id="bio" type="button" value="5" class="btn lesson" data-toggle="button">生物</button>
                            <button id="poli" type="button" value="6" class="btn lesson" data-toggle="button">政治</button>
                            <button id="history" type="button" value="7" class="btn lesson" data-toggle="button">历史</button>
                            <button id="geo" type="button" value="8" class="btn lesson" data-toggle="button">地理</button>
                        </div>
                        <p></p>
                        <button type="button" class="btn btn-danger">网络</button>
                        <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button type="button" value="0" class="btn" data-toggle="button">电信</button>
                            <button type="button" value="1" class="btn" data-toggle="button">网通</button>
                            <button type="button" value="2" class="btn" data-toggle="button">教育网</button>
                        </div> 
                        <p></p>
                        <button type="button" class="btn btn-danger">地址</button>
                    </div>
                </div>
                <div  id="resultPanel" class="span11 module">
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
                            <s:textfield  name="lessonGet" cssClass=" span3"></s:textfield>
                            <s:textfield  name="gradeGet" cssClass=" span3"></s:textfield>
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
                                <li><s:a href="searchTeacher.action?currentPage=%{pb.currentPage -1}">
                                        Prev</s:a></li>
                                </s:else>
                                <s:iterator value="new int[pb.totalPage]" status="i">
                                    <s:if test="pb.currentPage == #i.index+1">
                                    <li class="disabled"><a href="javascript:;"><s:property value="#i.index+1"/></a></li>
                                </s:if>
                                <s:else>
                                    <li><s:a href="searchTeacher.action?currentPage=%{#i.index +1}">
                                            <s:property value="#i.index+1"/>
                                        </s:a></li>
                                    </s:else>
                                </s:iterator>
                                <s:if test="pb.currentPage == pb.totalPage">
                                <li class="disabled"><a href="javascript:;">Next</a></li>
                            </s:if>
                            <s:else>
                                <li><s:a href="searchTeacher.action?currentPage=%{pb.currentPage +1}">
                                        Next
                                    </s:a></li>
                                </s:else>
                        </ul>
                    </div>
                </div>
            </div>
        </div>  

        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
