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
        <script type="text/javascript" src="js/reserve.js"></script>
        <script type="text/javascript" src="js/teacherInfo.js"></script>
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
    <body>
        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <link rel="stylesheet" href="css/teaInfo.css" type="text/css">
        <link rel="stylesheet" href="css/teaLevel.css" type="text/css">
        <div class="container wrapper">
            <div class="row" style="margin:-5px;">
                <div id="sideInfo" class="span3 module" style="padding:12px;">
                    <img width="210px" height="210px" src="<s:property value="tea.picUrl"/>" class="img-polaroid"/>
                    <input id="myemail" type="hidden" value="<s:property value="#session.email"/>" />
                    <input id="teaemail" type="hidden" value="<s:property value="tea.email"/>" />
                    <h4 style="margin-left:5px"><s:property value="tea.name"/>
                        <s:if test="level==1">
                            <i class="icon-diomand"></i>
                        </s:if>
                        <s:if test="tea.status==0"><label class="label pull-right">离线</label></s:if>
                        <s:elseif test="tea.status==1"><label class="label label-success pull-right">在线</label></s:elseif>
                        <s:else><label class="label label-warning pull-right">忙碌</label></s:else></small>
                    </h4>
                    <s:if test="tea.status==1">
                        <s:if test="tea.audition">
                            <a class='btn btn-success' style="margin-left:20px" href="enterPublicRoom.action?teaEmail=<s:property value='tea.email' default='null' />">在线试讲</a>
                        </s:if>
                        <s:else>
                            <a class='btn btn-success disabled' style="margin-left:20px" disabled>暂停试讲</a>
                        </s:else>
                        <a class="btn btn-primary" style="margin-left:10px" href="getMail.action?toEmail=<s:property value="tea.email" />">发送私信</a>
                    </s:if>
                    <s:elseif test="tea.status == 2">
                        <a class="btn btn-success" style="margin-left:20px" data-toggle="modal" data-target="#publicRoom">在线试讲</a>
                        <a class="btn btn-primary" style="margin-left:10px" href="getMail.action?toEmail=<s:property value="tea.email" />">发送私信</a>
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
                        <a class='btn btn-success disabled' style="margin-left:20px" diabled>暂停试讲</a>
                        <a class="btn btn-primary" style="margin-left:10px" href="getMail.action?toEmail=<s:property value="tea.email" />">发送私信</a>
                    </s:elseif>
                    <s:else>
                        <a class='btn btn-success' style="margin-left:20px" data-toggle="modal" data-target="#loginInfo">在线试讲</a>
                        <a class="btn btn-primary" style="margin-left:10px" data-toggle="modal" data-target="#loginInfo">发送私信</a>
                    </s:else>
                    <hr style="margin-top:20px;"/>
                    <div style="margin-left: 10px;">
                        <p style="line-height:20px;">
                            <small><i class="icon-flag"></i>
                                <s:property value="tea.major"/>-<s:property value="tea.school"/>
                            </small>
                            <br />
                            <small><i class="icon-home"></i>
                                <s:property value="tea.getDirectProvince()"/> - 
                                <s:property value="tea.getDirectCity()"/> - 
                                <s:property value="tea.getDirectDistrict()"/>
                            </small>
                            <br />
                            <small><i class="icon-user"></i>
                                <s:property value="tea.sex" default="性别保密"/>
                            </small>
                            <br />
                            <small><i class="icon-calendar"></i>
                                <s:property value="tea.birthday" default="未知"/>出生
                            </small>
                            <br />
                            <small><i class="icon-envelope"></i>
                                <s:property value="tea.email" /></small>
                            <br />
                            <small><i class="icon-signal"></i>
                                18801902576</small>
                            <br />
                            <small><i class="icon-time"></i>
                                <s:property value="tea.createTime" />加入</small>
                        </p>
                    </div>
                    <hr />
                    <!--                    <table class="table table-hover table-striped">
                                            <tbody>
                                                <tr>
                                                    <td>浏览数</td>
                                                    <td><s:property value="tea.obNum"/></td>
                                                </tr>
                                                <tr>
                                                    <td>登录次数</td>
                                                    <td><s:property value="tea.loginNum"/></td>
                                                </tr>
                                                <tr>
                                                    <td>最近在线时间</td>
                                                    <td><s:property value="tea.lastActiveDate"/></td>
                                                </tr>
                                            </tbody>
                                        </table>-->
                    <table class="table">
                        <thead>
                            <tr>
                                <th style="text-align:center;">
                                    <strong style="font-size:20px; color:#222;"><s:property value="tea.obNum"/></strong>
                                    <br />
                                    <small class="muted">浏览数</small>
                                </th>
                                <th style="text-align:center;">
                                    <strong style="font-size:20px;color:#222;"><s:property value="tea.reserveNum" /></strong>
                                    <br />
                                    <small class="muted">预约数</small>
                                </th>
                                <th style="text-align:center;">
                                    <strong style="font-size:20px;color:#222;"><s:property value="tea.classNum"/></strong>
                                    <br />
                                    <small class="muted">完成数</small>
                                </th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="span8 module" style="padding:12px;font-size: 12px;">
                    <s:if test="tea.level==1">
                        <div class="pull-right"><i class="icon-gold"></i></div>
                    </s:if>
                    <div class="span5">
                        <dl class="dl-horizontal">
                            <dt class="muted" style="width:90px;">价格</dt>
                            <dd class="lead text-error" style="margin-left:110px;" id="perPrice">无</dd>
                        </dl>
                        <dl class="dl-horizontal">
                            <dt class="muted" style="width:90px;">评分</dt>
                            <dd style="margin-left:110px;">
                                <div class="rateit" data-rateit-value="<s:property value="tea.score" default="0" />" data-rateit-ispreset="true" data-rateit-readonly="true"></div>
                                | <a onclick="$('#scoreButton').click();"><s:property value="tea.scoreNum" default="0"/>条评价</a>
                            </dd>
                        </dl>
                        <dl class="dl-horizontal">
                            <dt class="muted" style="width:90px;">老师身份</dt>
                            <dd style="margin-left:110px;">
                                <s:property value="tea.studyStatus"/>
                            </dd>
                        </dl>
                        <hr/>
                        <dl class="dl-horizontal">
                            <dt class="muted" style="width:90px;">上课方式</dt>
                            <s:if test="!tea.sprtOnline && !tea.sprtTUnderline && !tea.sprtSUnderline">
                                <dd style="margin-left:110px;">暂未选择授课方式</dd>
                            </s:if>
                            <s:else>
                                <dd style="margin-left:110px;">
                                    <span  data-toggle-name="is_private" data-toggle="buttons-radio">
                                        <s:if test="tea.sprtOnline">
                                            <button type="button" class="btn btn-mini btn-choice" onclick="$('#offlineArea').hide();
                                                reserve.setType('线上授课');">线上授课</button>
                                        </s:if>
                                        <s:if test="tea.sprtSUnderline">
                                            <button type="button" class="btn btn-mini btn-choice" onclick="$('#offlineArea').show();
                                                reserve.setType('学生上门');">学生上门</button>
                                        </s:if>
                                        <s:if test="tea.sprtTUnderline">
                                            <button type="button" class="btn btn-mini btn-choice" onclick="$('#offlineArea').show();
                                                reserve.setType('老师上门');">老师上门</button>
                                        </s:if>
                                    </span>
                                </dd>
                            </s:else>  
                        </dl>  
                        <dl class="dl-horizontal" id="offlineArea" style="display:none;">
                            <dt class="muted" style="width:90px;">线下授课区域</dt>
                            <textarea id="tmp2" style="display:none"><s:property value="tea.underlineArea"/></textarea>
                            <dd id="teaArea" style="margin-left:110px;">
                                <script>
                                    $("#teaArea").html($("#tmp2").text());
                                </script>
                            </dd>
                        </dl>
                        <dl class="dl-horizontal" >
                            <dt class="muted" style="width:90px;">课程</dt>
                            <dd id="lesson_select" style="margin-left:110px;">
                                <s:if test="tea.lessons.size()==0"><p>这个老师暂时还没有开课哦</p></s:if>
                                <span  data-toggle-name="is_private" data-toggle="buttons-radio">
                                    <s:iterator value="tea.lessons" status="st">
                                        <s:if test="delete==false">
                                            <button type="button" class="btn btn-mini btn-choice"   data-toggle="tooltip" data-placement="bottom" onclick="reserve.setLesson('<s:property value="name"/>');"><s:property value="name"/></button>
                                            <script>
                                                reserve.addPrice('<s:property value="name"/>', <s:property value="price"/>);
                                            </script>
                                        </s:if>
                                    </s:iterator>
                                </span>
                            </dd>
                        </dl>
                        <dl class="dl-horizontal">
                            <dt class="muted" style="width:90px;">课时数</dt>
                            <dd style="margin-left:110px;">
                                <input type="number" class="span1" min="1" max="20" step="1" value="1" onchange="checkInput($(this), 1, 20);
                                    reserve.setNum($(this).val());">
                            </dd>
                        </dl>
                        <dl class="dl-horizontal">
                            <s:if test="#session.userType=='student'">
                                <s:if test="tea.reserve">
                                    <button id="reserveButton" class="btn offset1 span2 btn-danger" data-toggle="modal" data-target="#reserveWarning">立即预定</button>
                                </s:if>
                                <s:else>
                                    <button class="btn offset1 span2 disabled" disabled="true" data-toggle="modal" data-target="#reserveModal">预约已满</button>
                                </s:else>
                            </s:if>
                            <s:else>
                                <button class="btn offset1 span2 btn-danger" data-toggle="modal" data-target="#loginInfo">立即预定</button>
                            </s:else>
                        </dl>
                    </div>
                    <div class="modal fade hide" id="reserveModal">
                        <div class="modal-header">
                            <h4>预订确认</h4>
                        </div>
                        <div class="modal-body">
                            <div class="alert">
                                <i class="icon-info-sign"></i>下订单前请先与老师商定授课时间，如果是网络授课，建议先与老师在线试讲！
                            </div>
                            <s:form action="bookTeacher">
                                <dl class="dl-horizontal">
                                    <dt class="muted">上课方式</dt>
                                    <dd id="reserveShowType" style="margin-left: 200px;"></dd>
                                </dl>
                                <dl class="dl-horizontal">
                                    <dt class="muted">课程</dt>
                                    <dd id="reserveShowLesson" style="margin-left: 200px;"></dd>
                                </dl>
                                <dl class="dl-horizontal">
                                    <dt class="muted">课时数</dt>
                                    <dd id="reserveShowNum" style="margin-left: 200px;">1</dd>
                                </dl>
                                <dl class="dl-horizontal">
                                    <dt class="muted">总计</dt>
                                    <dd id="reserveTotal" class="lead text-error" style="margin-left: 200px;"></dd>
                                </dl>
                                <dl>
                                    <dt class="muted">备注</dt>
                                    <s:textarea name="message" placeholder="(可选时间、留言等)" cssStyle="width:500px;"/>
                                    <s:textfield name="teacherEmail" cssStyle="display:none;" value="%{tea.email}"/>
                                    <s:textfield id="reserveType" name="type" cssStyle="display:none;"/>
                                    <s:textfield id="reserveLesson" name="lessonName" cssStyle="display:none;"/>
                                    <s:textfield id="reserveNum" name="duration" value="1" cssStyle="display:none;"/>
                                </dl>

                            </div>
                            <div class="modal-footer">
                                <s:submit cssClass="btn btn-success" value="提交"/>
                            </s:form>
                            <button class="btn" data-dismiss="modal">取消</button>
                        </div>
                    </div>
                    <div class="modal fade hide" id="reserveWarning">
                        <div class="modal-body">
                            <h4>请选择<strong>上课方式</strong>和<strong>课程</strong></h4>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-success" data-dismiss="modal">确认</button>
                        </div>
                    </div>
                </div>
                <div class="span8 module" style="padding:12px;">
                    <ul class="nav nav-pills">
                        <li class="active"><a href="#info_area" data-toggle="tab">基本信息</a></li>
                        <li><a id="scoreButton" href="#comment_area" data-toggle="tab">用户评论</a></li>
                        <li><a href="#bill_area" data-toggle="tab">交易记录</a>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane row fade active in" id='info_area'>
                            <textarea id="tmp" style="display:none"><s:property value="tea.intro"/></textarea>
                            <div class="span6" id="teaintro">
                                <script>
                                    $("#teaintro").html($("#tmp").text());
                                </script>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='comment_area'>

                        </div>
                        <div class="tab-pane fade" id='bill_area'>

                        </div>
                    </div>
                    <div class="modal fade hide" id="loginInfo">
                        <div class="modal-body">
                            <h3>请先登陆学生账户</h3>
                        </div>
                        <div class="modal-footer">
                            <a class="btn btn-success" href="index.action">确认</a>
                            <button class="btn" data-dismiss="modal">取消</button>
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