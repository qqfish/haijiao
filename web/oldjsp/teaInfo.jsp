<%-- 
    Document   : teaIndex
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
        <script type="text/javascript" src="js/jquery.rateit.min.js"></script>
        <link rel="stylesheet" href="css/rateit.css" type="text/css">

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
    <body onload="schedule.drawSchedule(<s:property value='scheduleBean.toJson()' default='null' />, <s:property value='studentScheduleBean.toJson()' default='null' />);"
          <!--==============================header=================================-->
          <%@ include file="WEB-INF/jspf/header.jspf"%>
          <!--==============================content=================================-->
          <div class="container wrapper">
            <div class="row" style="margin:-5px;">
                <div id="sideInfo" class="span3 module" style="padding:12px;">
                    <img width="210px" height="210px" src="<s:property value="tea.pic.content"/>" class="img-polaroid"/>
                    <input id="myemail" type="hidden" value="<s:property value="#session.email"/>" />
                    <input id="teaemail" type="hidden" value="<s:property value="tea.email"/>" />
                    <h4 style="margin-left:5px"><s:property value="tea.name"/>
                        <s:if test="tea.status==0"><label class="label pull-right">离线</label></s:if>
                        <s:elseif test="tea.status==1"><label class="label label-success pull-right">在线</label></s:elseif>
                        <s:else><label class="label label-warning pull-right">忙碌</label></s:else></small>
                    </h4>
                    <s:if test="tea.status==1">
                        <a class='btn btn-success' href="enterPublicRoom.action?teaEmail=<s:property value='tea.email' default='null' />">在线试讲</a>
                        <s:if test="#session.userType=='student'">
                            <a class="btn btn-primary" data-toggle="modal" data-target="#choosemodal">我要预约</a>
                        </s:if>
                    </s:if>
                    <s:elseif test="tea.status == 2">
                        <a class="btn btn-success" style="margin-left:10px" data-toggle="modal" data-target="#publicRoom">在线试讲</a>
                        <s:if test="#session.userType=='student'">
                            <a class="btn btn-primary" data-toggle="modal" data-target="#choosemodal">我要预约</a>
                        </s:if>
                        <div class="modal fade hide" id="publicRoom">
                            <div class="modal-body">
                                <h3>老师正在忙碌，可能无法与您交流，仍要进入房间吗？</h3>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-success" href="enterPublicRoom.action?teaEmail=<s:property value='tea.email' default='null' />">确定</a>
                                <button class="btn" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </s:elseif>
                    <s:elseif test="#session.email != null">
                        <a class="btn btn-primary" style="margin-left:5px" href="getMail.action?toEmail=<s:property value="tea.email" />">发送私信</a>
                        <s:if test="#session.userType=='student'">
                            <a class="btn btn-primary" data-toggle="modal" data-target="#choosemodal">我要预约</a>
                        </s:if>
                    </s:elseif>
                    <s:else>
                        <a class="btn btn-success" style="margin-left:5px" data-toggle="modal" data-target="#publicRoom">发送私信</a>
                        <!--<a class="btn btn-primary btn-mini" style="margin-left:5px" data-toggle="modal" data-target="#publicRoom">发送私信</a>-->
                        <!--<a class="btn btn-primary" style="margin-left:5px" data-toggle="modal" data-target="#publicRoom">我要预约</a>-->
                        <div class="modal fade hide" id="publicRoom">
                            <div class="modal-body">
                                <h3>请先登陆</h3>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-success" href="index.action">登陆</a>
                                <button class="btn" data-dismiss="modal">取消</button>
                            </div>
                        </div>
                    </s:else>
                    <hr/>
                    <div style="margin-left: 10px">
                        <small>
                            <s:property value="tea.getDirectProvince()"/> - 
                            <s:property value="tea.getDirectCity()"/> - 
                            <s:property value="tea.getDirectDistrict()"/><br/><br/>
                        </small>
                        <p><s:property value="tea.email" /></p>
                        <p><s:date name="tea.createTime" format="yyyy-MM-dd"/> 加入</p>
                    </div>
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
                            <tr>
                                <td>浏览数</td>
                                <td><s:property value="tea.obNum"/></td>
                            </tr>
                            <tr>
                                <td>登录次数</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>最近在线时间</td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="span8 module" style="padding:12px;">
                    <dl class="dl-horizontal" >
                        <dt>课程</dt>
                        <dd id="lesson_select">
                            <s:if test="tea.lessons.size()==0"><p>这个老师暂时还没有开课哦</p></s:if>
                            <span  data-toggle-name="is_private" data-toggle="buttons-radio">
                                <s:iterator value="tea.lessons" status="st">
                                    <s:if test="delete==false">
                                        <button type="button" class="btn btn-mini"  class="label label-info" data-toggle="tooltip" data-placement="bottom" onclick="$('#schedule_lesson').val($(this).text())"><s:property value="name"/></button>
                                    </s:if>
                                </s:iterator>
                            </span>
                        </dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt>支持</dt>
                        <s:if test="tea.sprtOnline==false && tea.sprtTUnderline==false && tea.sprtSUnderline==false">
                            <dd>暂未选择授课方式</dd>
                        </s:if>
                        <s:else>
                            <s:if test="tea.sprtOnline">
                                <dd class="label label-info" >线上授课</dd>
                            </s:if>
                            <s:if test="tea.sprtSUnderline">
                                <dd class="label label-info" >学生上门</dd>
                            </s:if>
                            <s:if test="tea.sprtTUnderline">
                                <dd class="label label-info" >老师上门</dd>
                            </s:if>
                        </s:else>  
                    </dl>  
                    <dl class="dl-horizontal">
                        <dt>线下授课区域</dt>
                        <textarea id="tmp2" style="display:none"><s:property value="tea.underlineArea"/></textarea>
                        <dd id="teaArea">
                            <script>
                                $("#teaArea").html($("#tmp2").text());
                            </script>
                        </dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt>选择上课时间</dt>
                        <dd>
                            <input type="text" placeholder="Type something…">
                        </dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <button class="btn span2 btn-danger">立即预定</button>
                    </dl>
                </div>
                <div class="span8 module" style="padding:12px;">
                    <ul class="nav nav-pills">
                        <li class="active"><a href="#info_area" data-toggle="tab">基本信息</a></li>
                        <li><a href="#comment_area" data-toggle="tab">评论</a></li>
                        <li><a href="#bill_area" data-toggle="tab">交易记录</a>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane row fade active in" id='info_area'>
                            <div class="span4">
                                <table class="table table-hover table-striped ">
                                    <tbody>
                                        <tr>
                                            <td class="span2">
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
                                                <strong>授课方式</strong>
                                            </td>
                                            <td>
                                                <s:if test="tea.sprtOnline==false && tea.sprtTUnderline==false && tea.sprtSUnderline==false">
                                                    暂未选择授课方式
                                                </s:if>
                                                <s:else>
                                                    <s:if test="tea.sprtOnline">
                                                        线上授课
                                                    </s:if>
                                                    <s:if test="tea.sprtSUnderline">
                                                        学生上门
                                                    </s:if>
                                                    <s:if test="tea.sprtTUnderline">
                                                        老师上门
                                                    </s:if>
                                                </s:else>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="span4">
                                <table class="table table-hover table-striped ">
                                    <tbody>
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
                                                <strong>专业</strong>
                                            </td>
                                            <td>
                                                <s:property value="tea.major"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="span2">
                                                <strong>目前身份</strong>
                                            </td>
                                            <td>
                                                <s:property value="tea.studyStatus"/>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <hr class="span8"/>
                            <div class="span8">
                                <h5>个人简介</h5>
                                <textarea id="tmp" style="display:none"><s:property value="tea.intro"/></textarea>
                                <div class="span6" id="teaintro">
                                    <script>
                                        $("#teaintro").html($("#tmp").text());
                                    </script>
                                </div>
                            </div>
                            <hr class="span8"/>
                            <div class="span8">
                                <h5>证书</h5>
                                <div class="span6" id="teaintro">
                                    <s:property value="tea.cert"/>
                                </div>
                            </div>
                            <hr class="span8"/>
                            <div class="span8">
                                <h5>家教经历</h5>
                                <div class="span6" id="teaintro">
                                    <s:property value="tea.experience"/>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='comment_area'>
                            <table class="table table-hover table-striped">
                                <tbody>

                                    <s:if test="billList.size()<=0">
                                        还没有评论哦~！
                                    </s:if>
                                    <s:else>
                                        <s:iterator value="billList" id="billList">
                                            <tr>
                                                <td>
                                                    <blockquote>
                                                        <s:if test="stot != null">
                                                            <h4><s:property value="student.name" /><div class="rateit pull-right" data-rateit-value="<s:property value="stot.score" default="0" />" data-rateit-ispreset="true" data-rateit-readonly="true"></div>
                                                                <small>
                                                                    <span><s:property value="stot.content" /></span>
                                                                    <br/><br/>
                                                                    <s:if test="stot.reply != null">
                                                                        <span>老师的回复：<s:property value="stot.reply" /></span>
                                                                    </s:if>
                                                                </small>
                                                            </s:if>
                                                    </blockquote>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                    </s:else>

                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id='bill_area'>
                            <table class="table table-hover table-striped">
                                <s:if test="billList.size()<=0">
                                    <tbody>
                                        暂无交易记录哦~！
                                    </tbody>
                                </s:if>
                                <s:else>
                                    <thead>
                                        <tr>
                                            <th>时间</th>
                                            <th>学生姓名</th>
                                            <th>金额</th>
                                        </tr>
                                    </thead>
                                    <tbody>        
                                        <s:iterator value="billList" id="billList">
                                            <tr>
                                                <td><s:property value="createdateToString()" /></td>
                                                <td><s:property value="student.name" /></td>
                                                <td><s:property value="getRealMoney('teacher')" /></td>
                                            </tr>
                                        </s:iterator>
                                    </tbody>
                                </s:else>
                            </table>
                        </div>
                    </div>
                </div>
            </div>  
            <div class="modal fade hide" id="choosemodal" style="height: auto;width:650px;margin-top:100px;">
                <div class="modal-header" style="height: 50px">
                    <a class="close" data-dismiss="modal">×</a>
                    <h3>请选择课程时间</h3>
                    <p id="schedule_error" style="color: red;"></p>
                </div>
                <div id="modalpanel" class="modal-body" style="height:400px">
                    <div class="schedule_panel" id="schedule_panel" >
                        <div style="font-size: 9px">
                            &nbsp;&nbsp;
                            <img src="images/colorCCF.png" height="15px" width="15px">&nbsp;老师忙碌时段&nbsp;
                            <img src="images/colorCCC.png" height="15px" width="15px">&nbsp;老师空闲时段&nbsp;
                            <img src="images/colorFC9.png" height="15px" width="15px">&nbsp;您已选中时段&nbsp;
                        </div>
                        <table class="schedule_table" style="margin-left:10px" width="100%" border="0" style="z-index: 1">
                            <tbody>
                                <tr>
                                    <th ></th>
                                    <th>Mon.</th>
                                    <th>Tue.</th>
                                    <th>Wed.</th>
                                    <th>Thu.</th>
                                    <th>Fri.</th>
                                    <th>Sat.</th>
                                    <th>Sun.</th>
                                </tr>
                                <tr class="class_1">
                                    <th rowspan="2">8: 00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_2">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_3">
                                    <th rowspan="2">10: 00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_4">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_5">
                                    <th rowspan="2">12: 00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
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
                                    <th rowspan="2">18：00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_12">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_13">
                                    <th rowspan="2">20：00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_14">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_15">
                                    <th rowspan="2">22：00</th><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                                <tr class="class_16">
                                    <td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                </tr>
                            </tbody>
                        </table>
                        <a class="btn btn-info btn-small pull-right" id="next">确认</a> 
                        <p></p>
                    </div>
                </div>
            </div>
        </div>

        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>