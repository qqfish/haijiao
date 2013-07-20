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
        <div class="container wrapper">
            <div class="row" style="margin:-5px;">
                <div id="sideInfo" class="span3 module" style="padding:12px;">
                    <img width="210px" height="210px" src="<s:property value="tea.picUrl"/>" class="img-polaroid"/>
                    <input id="myemail" type="hidden" value="<s:property value="#session.email"/>" />
                    <input id="teaemail" type="hidden" value="<s:property value="tea.email"/>" />
                    <h4 style="margin-left:5px"><s:property value="tea.name"/>
                        <s:if test="tea.status==0"><label class="label pull-right">离线</label></s:if>
                        <s:elseif test="tea.status==1"><label class="label label-success pull-right">在线</label></s:elseif>
                        <s:else><label class="label label-warning pull-right">忙碌</label></s:else></small>
                    </h4>
                    <s:if test="tea.status==1">
                        <a class='btn btn-success' href="enterPublicRoom.action?teaEmail=<s:property value='tea.email' default='null' />">在线试讲</a>
                    </s:if>
                    <s:elseif test="tea.status == 2">
                        <a class="btn btn-success" style="margin-left:10px" data-toggle="modal" data-target="#publicRoom">在线试讲</a>
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
                    </s:elseif>
                    <s:else>
                        <a class="btn btn-primary" style="margin-left:5px" data-toggle="modal" data-target="#loginInfo">发送私信</a>
                    </s:else>
                    <hr/>
                    <div style="margin-left: 10px">
                        <small>
                            <s:property value="tea.getDirectProvince()"/> - 
                            <s:property value="tea.getDirectCity()"/> - 
                            <s:property value="tea.getDirectDistrict()"/><br/><br/>
                        </small>
                        <p><s:property value="tea.email" /></p>
                        <p><s:property value="tea.createTime" /> 加入</p>
                    </div>
                    <table class="table table-hover table-striped">
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
                    </table>
                </div>
                <div class="span8 module" style="padding:12px;">
                    <dl class="dl-horizontal">
                        <dt class="muted" style="width:90px;">预约数</dt>
                        <dd style="margin-left:110px;"><s:property value="tea.reserveNum"/></dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt class="muted" style="width:90px;">完成数</dt>
                        <dd style="margin-left:110px;"><s:property value="tea.classNum"/>
                            <span class="offset2">
                                评分
                            </span>
                            <div class="rateit" data-rateit-value="<s:property value="tea.score" default="0" />" data-rateit-ispreset="true" data-rateit-readonly="true"></div>
                        </dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <dt class="muted" style="width:90px;">价格</dt>
                        <dd class="lead text-error" style="margin-left:110px;" id="perPrice">无</dd>
                    </dl>
                    <hr/>
                    <dl class="dl-horizontal">
                        <dt class="muted" style="width:90px;">上课方式</dt>
                        <s:if test="tea.sprtOnline==false && tea.sprtTUnderline==false && tea.sprtSUnderline==false">
                            <dd style="margin-left:110px;">暂未选择授课方式</dd>
                        </s:if>
                        <s:else>
                            <dd style="margin-left:110px;">
                                <span  data-toggle-name="is_private" data-toggle="buttons-radio">
                                    <s:if test="tea.sprtOnline">
                                        <button type="button" class="btn btn-mini" onclick="$('#offlineArea').hide();reserve.setType('线上授课');">线上授课</button>
                                    </s:if>
                                    <s:if test="tea.sprtSUnderline">
                                        <button type="button" class="btn btn-mini" onclick="$('#offlineArea').show();reserve.setType('学生上门');">学生上门</button>
                                    </s:if>
                                    <s:if test="tea.sprtTUnderline">
                                        <button type="button" class="btn btn-mini" onclick="$('#offlineArea').show();reserve.setType('老师上门');">老师上门</button>
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
                                        <button type="button" class="btn btn-mini"   data-toggle="tooltip" data-placement="bottom" onclick="reserve.setLesson('<s:property value="name"/>');"><s:property value="name"/></button>
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
                            <input type="number" class="span1" min="1" max="8" step="1" value="1" onchange="checkInput($(this),1,8);reserve.setNum($(this).val());">
                        </dd>
                    </dl>
                    <dl class="dl-horizontal">
                        <s:if test="#session.userType=='student'">
                            <button id="reserveButton" class="btn offset1 span2 btn-danger disabled" disabled="true" data-toggle="modal" data-target="#reserveModal">立即预定</button>
                        </s:if>
                        <s:else>
                            <button class="btn offset1 span2 btn-danger" data-toggle="modal" data-target="#loginInfo">立即预定</button>
                        </s:else>
                    </dl>

                    <div class="modal fade hide" id="reserveModal">
                        <div class="modal-header">
                            <h4>预订确认</h4>
                        </div>
                        <div class="modal-body">
                        <s:form action="bookTeacher">
                                <dl>
                                    <dt class="muted">上课方式</dt>
                                    <dd id="reserveShowType" style="margin-left:100px"></dd>
                                </dl>
                                <dl>
                                    <dt class="muted">课程</dt>
                                    <dd id="reserveShowLesson" style="margin-left:100px"></dd>
                                </dl>
                                <dl>
                                    <dt class="muted">课时数</dt>
                                    <dd id="reserveShowNum" style="margin-left:100px"></dd>
                                </dl>
                                <dl>
                                    <dt class="muted">总计</dt>
                                    <dd id="reserveTotal" class="lead text-error" style="margin-left:100px"></dd>
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
                        <div class="modal fade hide" id="loginInfo">
                            <div class="modal-body">
                                <h3>请先登陆</h3>
                            </div>
                            <div class="modal-footer">
                                <a class="btn btn-success" href="index.action">登陆</a>
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