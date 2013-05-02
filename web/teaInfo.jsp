<%-- 
    Document   : studentIndex
    Created on : 2013-3-26, 22:11:27
    Author     : Bx Yang
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Teachers</title>
        <meta charset="utf-8">
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/teachers.js"></script>
        <script type="text/javascript" src="js/index.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <script type="text/javascript" src="SearchTeacher/js/schedule.js"></script>

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
    <body onload="schedule.drawSchedule(<s:property value='scheduleBean.toJson()' default='null' />, null);">

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="container">
            <div class="row">
                <div id="sideInfo" class="span3">
                    <img width="210px" height="210px" src="<s:property value="tea.picUrl"/>" class="img-polaroid"/>
                    <h1><s:property value="tea.name"/></h1>          
                    <hr/>
                    <p><s:property value="tea.province"/></p>
                    <p><s:property value="tea.email" /></p>
                    <p><s:property value="tea.createTime" /> 加入</p>
                    <table class="table table-hover table-striped">
                        <tbody>
                            <tr>
                                <td>预约数</td>
                                <td><s:property value="tea.reserveNum"/></td>
                            </tr>
                            <tr>
                                <td>完成数</td>
                                <td><s:property value="tea.classNum"/></td>
                            </tr>
                            <tr>
                                <td>评分</td>
                                <td><s:property value="tea.score"/></td>
                            </tr>
                        </tbody>
                    </table>
                    <s:if test="#session.userType=='teacher'">
                        <a class="btn btn-primary" href="mail.jsp?id=<s:property value="tea.id" />">发送私信</a>
                    </s:if>
                    <s:if test="#session.userType=='student'">
                        <a class="btn btn-primary" data-toggle="modal" data-target="#choosemodal">我要预约</a>
                        <a class="btn btn-primary" href="mail.jsp?id=<s:property value="tea.id" />">发送私信</a>
                        <s:if test="tea.status=1">
                            <a class="btn btn-primary" href="enterPublicRoom.action?teaEmail=<s:property value='tea.email' default='null' />">公共课程</a>
                        </s:if>
                    </s:if>
                </div>
                <div class="span9">
                    <ul class="nav nav-pills">
                        <li class="active"><a href="#info_area" data-toggle="tab">基本信息</a></li>
                        <li><a href="#detail_area" data-toggle="tab">详细信息</a></li>
                        <li><a href="#comment_area" data-toggle="tab">评论</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane row fade active in" id='info_area'>
                            <div class="span5 well">

                            </div>
                            <div class="span3">
                                <table class="table table-hover table-striped ">
                                    <tbody>
                                        <tr>
                                            <td class="span1">
                                                <strong>性别</strong>
                                            </td>
                                            <td>
                                                <s:property value="tea.sex"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <strong>生日</strong>
                                            </td>
                                            <td>
                                                <s:property value="tea.birthday"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <strong>大学</strong>
                                            </td>
                                            <td>
                                                <s:property value="tea.school"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <strong>手机</strong>
                                            </td>
                                            <td>
                                                <s:property value="tea.tel"/>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='detail_area'>
                            <textarea id="tmp" style="display:none"><s:property value="tea.intro"/></textarea>
                            <div id="teaintro">
                                <script>
                                    $("#teaintro").html($("#tmp").text());
                                </script>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='comment_area'>
                            <table class="table table-hover table-striped">
                                <tbody>
                                    <tr>
                                        <td>
                                            <blockquote>
                                                <s:if test="billList.size()<=0">
                                                    还没有评论哦~！
                                                </s:if>
                                                <s:else>
                                                    <s:iterator value="billList" id="billList">
                                                        <h4><s:property value="from.name" /><label class="label label-important pull-right">评分:<s:property value="comment.score" /></label></h4>
                                                        <small>
                                                            <span><s:property value="from.content" /></span>
                                                            <span class="pull-right">
                                                                <button type="button" class="btn btn-info btn-mini">回复</button>
                                                            </span>
                                                        </small>
                                                    </s:iterator>
                                                </s:else>
                                            </blockquote>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="modal fade hide" id="choosemodal" style="height: auto;width:650px;margin-top:100px;">
                <div class="modal-header" style="height: 50px">
                    <a class="close" data-dismiss="modal">×</a>
                    <h3>请选择课程时间</h3>
                </div>
                <div class="modal-body" >
                    <div class="schedule_panel" id="schedule_panel" >
                        <table class="schedule_table" style="margin-left:10px" width="100%" border="0" style="z-index: 1">
                            <tbody>
                                <tr>
                                    <th ></th>
                                    <th>Mon.</th>
                                    <th>Tue.</th>
                                    <th>Wed.</th>
                                    <th>Thu.</th>
                                    <th>Fri.</th>
                                    <th style="width:24px">Sat.</th>
                                    <th style="width:24px">Sun.</th>
                                </tr>
                                <tr class="class_1">
                                    <th rowspan="2">8:00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_2">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_3">
                                    <th rowspan="2">10:00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_4">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_5">
                                    <th rowspan="2">中 午</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_6">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_7">
                                    <th rowspan="2">14：00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_8">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_9">
                                    <th rowspan="2">16：00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_10">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_11">
                                    <th rowspan="3">18：00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_12">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_13">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                            </tbody>
                        </table>
                        <a class="btn btn-info btn-small pull-right" id="next">下一步</a> 
                        <p></p>
                    </div>
                    <div id="confirm_panel" style="display:none">
                        <s:form id="schedule_form" action="bookTeacher.action">
                            <s:textfield id="schedule_json" name="json" style="display:none;"></s:textfield>
                            <s:textfield id="schedule_lesson" name="lesson" style="display:none;"></s:textfield>
                            <s:textfield name="teacherEmail" style="display:none;" value="%{tea.email}"></s:textfield>
                            <s:textfield cssClass="span4" id='schedule_times' name="times" placeholder="请输入上课次数" autofocus="autofocus"></s:textfield>
                            <session id="schedule_error"></session>
                            <div id="lesson_select">
                                <s:if test="tea.lessons.size()==0"><p>这个老师暂时还没有开课哦</p></s:if>
                                <s:iterator value="tea.lessons" status="st">
                                    <a href="#" class="label label-info" onclick="$('#schedule_lesson').val('<s:property value="name"/>')"><s:property value="name"/></a>
                                </s:iterator>
                            </div>
                            <a class="btn btn-info btn-small pull-right" id="pre">上一步</a>
                            <a class="btn btn-primary btn-small pull-right" id="upload">提交</a>
                        </s:form>
                        <br/>
                        <p></p>
                    </div>
                </div>


            </div>
        </div>

        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>