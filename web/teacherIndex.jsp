<%-- 
    Document   : teacherIndex
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
        <script type="text/javascript" src="TeacherIndex/js/addSchedule.js"></script>
        <script type="text/javascript" src="TeacherIndex/js/viewSchedule.js"></script>
        <script type="text/javascript" src="js/index.js"></script>
        <script type="text/javascript" src="js/teacherIndex.js"></script>
        <script type="text/javascript" src="js/jquery.rateit.min.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
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
    <body onload="addSchedule.drawSchedule(<s:property value='scheduleBean.toJson()' default='null'/>);
            viewSchedule.drawSchedule(<s:property value='scheduleBean.toJson()' default='null' />);"
          >

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <input type="hidden" id="nextPageMessage" value="<s:property value="nextPageMessage" />"/>
        <div class="container wrapper">
            <div class="row" style="margin:-5px;">
                <div id="sideInfo" class="span3 module" style="padding:12px;">
                    <img width="210px" height="210px" src="<s:property value="teacher.picUrl"/>" class="img-polaroid"/>
                    <h4 style="margin-left: 10px;"><s:property value="teacher.name"/></h4>     
                    <a class='btn btn-primary btn-small' style="margin-left: 15px;" href="enterPublicRoom.action?teaEmail=<s:property value='teacher.email' default='null' />"><i class="icon-user icon-white"></i>在线试讲</a>
                    <s:a action="toChangeInfo.action">
                        <button type="button" class="btn btn-small btn-primary pull-right" style="margin-right: 15px;"><i class="icon-pencil icon-white"></i>修改资料</button>
                    </s:a>
                    <hr/>
                    <div style="margin-left: 10px;">
                        <small>
                            <s:property value="teacher.getDirectProvince()"/> - 
                            <s:property value="teacher.getDirectCity()"/> - 
                            <s:property value="teacher.getDirectDistrict()"/><br/><br/>
                        </small>
                        <p><s:property value="teacher.email"/></p>
                        <p><s:property value="teacher.createdateToString()"/> 加入</p>
                    </div>
                    <table class="table table-hover table-striped">
                        <tbody>
                            <tr>
                                <td>浏览数</td>
                                <td><s:property value="teacher.obNum"/></td>
                            </tr>
                            <tr>
                                <td>预约数</td>
                                <td><s:property value="teacher.reserveNum"/></td>
                            </tr>
                            <tr>
                                <td>完成数</td>
                                <td><s:property value="teacher.classNum"/></td>
                            </tr>
                            <tr>
                                <td>评分</td>
                                <td><s:if test="teacher.score == 0">无评分</s:if>
                                    <s:else><s:property value="teacher.score"/></s:else></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="span8 module" id="tippanel" style="padding:12px;">
                        开课流程：①开设课程<i class=" icon-chevron-right"></i>②设定时间<i class=" icon-chevron-right"></i>③处理预约<i class=" icon-chevron-right"></i>④电子备课<a class="close pull-right" id="closetip">×</a>
                    </div>
                    <div class="span8 module" style="padding:12px;">
                        <ul class="nav nav-pills">
                            <li id="l3"  class="active"><a href="#lesson_area" data-toggle="tab" >开设课程</a></li>
                            <li id="l2"><a href="#schedule_area" data-toggle="tab" >设定时间</a></li>
                            <li id="l1"><a href="#student_area" data-toggle="tab">处理预约</a></li>
                            <li id="l6"><a href="#file_area" data-toggle="tab" >电子备课</a></li>
                            <li id="l4"><a href="#bill_area" data-toggle="tab" >交易记录</a></li>
                            <li id="l5"><a href="#comment_area" data-toggle="tab" >评论</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade" id='student_area'>
                                <table class="table table-hover table-striped">
                                    <tbody>
                                    <s:if test="classList.size()<=0">
                                        还没有学生选你哦~！
                                    </s:if>
                                    <s:else>
                                        <s:iterator value="classList" id="list">
                                            <s:if test="status>=2"> 
                                                <tr>
                                                    <td>
                                                        <blockquote>
                                                            <h4><s:property value="student.name"/>
                                                                <s:if test="student.status==0"><label class="label">离线</label></s:if>
                                                                <s:elseif test="student.status==1"><label class="label label-success">在线</label></s:elseif>
                                                                    <label class="label label-info pull-right">
                                                                    <s:if test="status==2">
                                                                        等待审批
                                                                    </s:if>
                                                                    <s:if test="status==3">
                                                                        已确认
                                                                    </s:if>
                                                                </label>
                                                            </h4>
                                                            <small>
                                                                <span>
                                                                    <s:property value="freeTime.strWeekday()"/>
                                                                    <s:property value="freeTime.strSliceIndex()"/>
                                                                </span>
                                                                <span style="margin-left: 10px">剩余<s:property value="remain"/>次课程</span>
                                                                <span style="margin-left: 10px">下次上课在<s:property value="remainWeek()"/></span>
                                                                <span style="margin-left: 10px"class="pull-right">
                                                                    <s:if test="status==3">
                                                                        <s:form id="dealApply_stop_%{id}" action="dealApply.action">
                                                                            <s:textfield style="display:none;" name="button" value="stop"></s:textfield>
                                                                            <s:textfield style="display:none;" name="classId" value="%{id}"></s:textfield>
                                                                            <s:textfield style="display:none;" name="toEmail" value="%{student.email}"></s:textfield>
                                                                            <button type="button" id="dealApply_stop_button_<s:property value="id"/>" class="btn btn-info btn-mini">顺延一周</button>
                                                                            <button type="button" id="dealApply_cancel_button_<s:property value="id"/>" class="btn btn-info btn-mini">取消一周</button>
                                                                            <a class="btn btn-info btn-mini" href="getMail.action?toEmail=<s:property value="student.email" />">私信</a>
                                                                        </s:form>
                                                                        <s:form id="dealApply_cancel_%{id}" action="dealApply.action">
                                                                            <s:textfield style="display:none;" name="button" value="cancel"></s:textfield>
                                                                            <s:textfield style="display:none;" name="classId" value="%{id}"></s:textfield>
                                                                            <s:textfield style="display:none;" name="toEmail" value="%{student.email}"></s:textfield>
                                                                        </s:form>
                                                                    </s:if>
                                                                    <s:if test="status==2">
                                                                        <button type="button" id="dealApply_accept_button_<s:property value="id"/>" class="btn btn-info btn-mini">接受</button>
                                                                        <button type="button" id="dealApply_decline_button_<s:property value="id"/>" class="btn btn-info btn-mini">拒绝</button>
                                                                        <button class="btn btn-info btn-mini" href="mail.jsp?id=<s:property value="student.email" />">私信</button>
                                                                        <s:form id="dealApply_accept_%{id}" action="dealApply.action">
                                                                            <s:textfield style="display:none;" name="button" value="accept"></s:textfield>
                                                                            <s:textfield style="display:none;" name="classId" value="%{id}"></s:textfield>
                                                                            <s:textfield style="display:none;" name="toEmail" value="%{student.email}"></s:textfield>
                                                                        </s:form>
                                                                        <s:form id="dealApply_decline_%{id}" action="dealApply.action">
                                                                            <s:textfield style="display:none;" name="button" value="decline"></s:textfield>
                                                                            <s:textfield style="display:none;" name="classId" value="%{id}"></s:textfield>
                                                                            <s:textfield style="display:none;" name="toEmail" value="%{student.email}"></s:textfield>
                                                                        </s:form>
                                                                    </s:if>
                                                                </span>
                                                                <br/>
                                                                <small>联系电话:<s:property value="student.tel"/></small>
                                                            </small>
                                                        </blockquote>
                                                    </td>
                                                </tr>
                                            </s:if>
                                        </s:iterator>
                                    </s:else>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id='schedule_area'>
                            <table class="static_schedule_table" width="100%" border="0" style="z-index: 1;text-align: center;table-layout: fixed;">
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
                            <a class="btn btn-primary pull-right" data-toggle="modal" data-target="#choosemodal">修改空闲时间</a><br/>
                            <div class="modal fade hide" id="choosemodal" style="height: auto;width:650px;margin-top:100px;">
                                <div class="modal-header" style="height: 50px">
                                    <a class="close" data-dismiss="modal">×</a>
                                    <h3>请选择课程时间</h3>
                                    <p id="chooseError" style="color:red;"></p>
                                </div>
                                <div class="modal-body" style="height: 400px">
                                    <div class="schedule_panel" id="schedule_panel">
                                        <div style="font-size: 9px">
                                            &nbsp;&nbsp;
                                            <div style="float:left;">
                                                <div  style="float:left;background: #CCF;height: 15px; width: 15px;"></div>已经添加的空闲时间&nbsp;
                                            </div>
                                            <div style="float:left;">
                                                <div  style="float:left;background: #CCC;height: 15px; width: 15px;"></div>未分配时间&nbsp;
                                            </div>
                                            <div style="float:left;">
                                                <div  style="float:left;background: #FC9;height: 15px; width: 15px;"></div>新添加空闲时间&nbsp;
                                            </div>
                                            <div style="float:left;">
                                                <div  style="float:left;background: #FAA;height: 15px; width: 15px;"></div>删除空闲时间&nbsp;
                                            </div>
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
                                        <s:form id="schedule_form" action="addFreeTime.action">
                                            <s:textfield id="schedule_json" name="json" style="display:none;"></s:textfield>
                                                <a class="btn btn-primary pull-right" id="upload" >完成</a>
                                        </s:form>
                                        <p></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade  active in" id='lesson_area'>
                            <a id="showtip"><i class="icon-question-sign"></i><small>如何开设课程</small></a>
                            <br/>
                            <br/>
                            开课导航：<br/>
                            <small><strong>第一步：①开设课程</strong><br/>提示：点击“课程列表”进行开课（点击“小叉”可取消）。</small><br/>
                            <small><strong>第二步：②设定时间</strong><br/>提示：设定空闲时间以接受学生预约（可以取消空闲时间）。</small><br/>
                            <small><strong>第三步：③处理预约</strong><br/>提示：查看处理学生预约订单（24小时必须确认预约订单）。</small><br/>
                            <small><strong>第四步：④电子备课</strong><br/>提示：有预约请提前备课（支持pdf、doc、ppt、jpg格式）。</small><br/>
                            <br/>
                            <h4>老师您好，您目前开设课程的情况如下：</h4>
                            <table>
                                <s:iterator value="teacher.lessons" id="ls">
                                    <s:if test="delete==false">
                                        <s:form action="dealLesson.action">
                                            <div style="display:none;">
                                                <s:textfield cssClass="span2" value="%{name}" type="text" name="lessonName"/>
                                                <s:submit cssClass="btn" id="delete_%{name}" value="delete" method="deleteLesson"/>
                                            </div>
                                            <span class="label label-info"><s:property value="name" /></span><a href="#" id="delete_click_<s:property value="name" />"><i class="icon-remove" ></i></a>  
                                            </s:form>
                                        </s:if>
                                    </s:iterator>
                                <br/><br/>
                                <hr/>
                                <h5>课程列表</h5>
                                <s:form action="dealLesson.action">
                                    <span style="display:none;"><s:textfield cssClass="span2" id="deal_lesson" name="lessonName"/></span>
                                    <ul class="nav nav-pills">
                                        <li id="l1" class="active"><a href="#p_school" data-toggle="tab">小学</a></li>
                                        <li id="l2"><a href="#j_school" data-toggle="tab" >初中</a></li>
                                        <li id="l3"><a href="#s_school" data-toggle="tab" >高中</a></li>
                                        <li id="l4"><a href="#eng" data-toggle="tab" >英语</a></li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane fade active in" id='p_school'>
                                            <label class="label label-info lessonName">小学语文</label>
                                            <label class="label label-info lessonName">小学数学</label>
                                            <label class="label label-info lessonName">小学英语</label>
                                            <label class="label label-info lessonName">小学奥数</label>
                                        </div>
                                        <div class="tab-pane fade" id='j_school'>
                                            <label class="label label-info lessonName">初一初二语文</label>
                                            <label class="label label-info lessonName">初一初二数学</label>
                                            <label class="label label-info lessonName">初一初二英语</label>
                                            <label class="label label-info lessonName">初三语文</label>
                                            <label class="label label-info lessonName">初三数学</label>
                                            <label class="label label-info lessonName">初三英语</label>
                                            <label class="label label-info lessonName">初中物理</label>
                                            <label class="label label-info lessonName">初中化学</label>
                                            <label class="label label-info lessonName">初中历史</label>
                                            <label class="label label-info lessonName">初中地理</label>
                                        </div>
                                        <div class="tab-pane fade" id='s_school'>
                                            <label class="label label-info lessonName">高一高二语文</label>
                                            <label class="label label-info lessonName">高一高二数学</label>
                                            <label class="label label-info lessonName">高一高二英语</label>
                                            <label class="label label-info lessonName">高三语文</label>
                                            <label class="label label-info lessonName">高三数学</label>
                                            <label class="label label-info lessonName">高三英语</label>
                                            <label class="label label-info lessonName">高中物理</label>
                                            <label class="label label-info lessonName">高中化学</label>
                                            <label class="label label-info lessonName">高中政治</label>
                                            <label class="label label-info lessonName">高中历史</label>
                                            <label class="label label-info lessonName">高中生物</label>
                                            <label class="label label-info lessonName">高中地理</label>
                                            <label class="label label-info lessonName">高考报考咨询</label>
                                        </div>
                                        <div class="tab-pane fade" id='eng'>
                                            <label class="label label-info lessonName">大学四级</label>
                                            <label class="label label-info lessonName">大学六级</label>
                                            <label class="label label-info lessonName">托福</label>
                                            <label class="label label-info lessonName">GRE</label>
                                            <label class="label label-info lessonName">雅思</label>
                                            <label class="label label-info lessonName">口语</label>
                                        </div>
                                    </div>
                                    <div style="display: none;"><s:submit id="add" cssClass="btn" value="add" method="addLesson"/></div>
                                    <div id="lesson_tip" style="font-size:9px; color: red; display: none;"></div>
                                </s:form>
                            </table>
                            <hr/>
                            <h5>在线试讲</h5>
                            <div class="btn-group" data-toggle="buttons-radio">
                            <s:if test="teacher.audition">
                                <button type="button" class="btn btn-primary active">是</button>
                                <a href="changeAudition.action" class="btn btn-primary">否</a>
                            </s:if>
                            <s:else>
                                <a href="changeAudition.action" class="btn btn-primary">是</a>
                                <button type="button" class="btn btn-primary active">否</button>
                            </s:else>
                            </div>
                            <hr/>
                            <h5>线下授课区域<a href="toChangeInfo.action?jump=area" class='btn btn-primary btn-small pull-right'><i class='icon-pencil icon-white'></i>编辑</a></h5>
                            <p><s:property value="teacher.underlineArea"/></p>
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
                                            <th>姓名</th>
                                            <th>课程</th>
                                            <th>金额</th>
                                            <th>时间</th>
                                            <th>信息</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>        
                                        <s:iterator value="billList" id="billList">
                                            <tr>
                                                <td><s:property value="student.name" /></td>
                                                <td><s:property value="lesson.name" /></td>
                                                <td><s:property value="getRealMoney('teacher')" /></td>
                                                <td><s:property value="createdateToString()" /></td>
                                                <td><s:property value="message" /></td>
                                                <td>
                                                    <s:if test="ttos==null">
                                                        <a id="<s:property value="id" />" href="#comment_<s:property value="id" />" type="button" class="commentA btn btn-info btn-mini" data-toggle="modal">评论</a>
                                                    </s:if>
                                                    <s:else>
                                                        <a type="button" class="btn btn-info btn-mini disabled" data-toggle="modal">评论</a>
                                                    </s:else>
                                                </td>
                                            </tr>
                                        <div id="comment_<s:property value="id" />" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                <h3 id="myModalLabel">评论</h3>
                                            </div>
                                            <s:form action="makeCommentReply.action">
                                                <div class="modal-body">
                                                    <s:textfield name="id" value="%{id}" cssStyle="display:none;"></s:textfield>
                                                    内容<s:textarea name="content" autofocus="autofocus" id="content"></s:textarea>
                                                        <br/>
                                                        评分<div id="rate_<s:property value="id" />" class="rateit" data-rateit-step="1" data-rateit-ispreset="true"></div>
                                                    <s:textfield id="score_%{id}" name="score" cssStyle="display:none;"></s:textfield>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                                                    <s:submit cssClass="btn btn-primary" method="comment" value="提交"></s:submit>
                                                    </div>
                                                    <script type="text/javascript">
        $(".commentA").click(function() {
            var id = $(this).attr("id");
            $("#rate_" + id).bind('rated', function(event, value) {
                $('#score_' + id).val(value);
            });
            $("#rate_" + id).bind('over', function(event, value) {
                $(this).attr('title', value);
            });
            $("#cmtsmt").click(function(event) {
                if (/^\s*$/.test($('score').val()) || /^\s*$/.test($("#content").val()))
                    event.preventDefault();
            });
        });
                                                    </script>
                                            </s:form>
                                        </div>
                                    </s:iterator>
                                    </tbody>
                                </s:else>
                            </table>
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
                                                            <h4><s:property value="student.name" /><label class="label label-important pull-right">评分:<s:property value="stot.score" /></label></h4>
                                                            <small>
                                                                <span><s:property value="stot.content" /></span>
                                                                <span class="pull-right">
                                                                    <s:if test="stot.reply == null">
                                                                        <a href="#reply_<s:property value="id" />" type="button" class="btn btn-info btn-mini" data-toggle="modal">回复</a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a type="button" class="btn btn-info btn-mini disabled" data-toggle="modal">回复</a>
                                                                    </s:else>
                                                                </span>
                                                                <br/><br/>
                                                                <s:if test="stot.reply != null">
                                                                    <span>您的回复：<s:property value="stot.reply" /></span>
                                                                </s:if>
                                                            </small>
                                                            <div id="reply_<s:property value="id" />" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                                    <h3 id="myModalLabel">回复</h3>
                                                                </div>
                                                                <s:form action="makeCommentReply.action">
                                                                    <div class="modal-body">
                                                                        <s:textfield name="id" value="%{id}" cssStyle="display:none;"></s:textfield>
                                                                        <s:textarea name="content" autofocus="autofocus" id="content"></s:textarea>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                                                                        <s:submit cssClass="btn btn-primary" method="reply" value="提交"></s:submit>
                                                                        </div>
                                                                </s:form>
                                                            </div>
                                                        </s:if>
                                                    </blockquote>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                    </s:else>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id='file_area'>
                            <div class="tabbable tabs-left">
                                <ul class="nav nav-tabs nav-pills">
                                    <li><a href="#userfile" data-toggle="tab">我的文件</a></li>
                                    <li><a href="#publicfile" data-toggle="tab">公共文件</a></li>
                                </ul> 
                                <div class="tab-content">
                                    <div id="userfile" class="tab-pane">
                                        <button class="btn btn-primary" data-toggle="button" onclick="$('#newgroup').toggle();">新建分组</button>
                                        <button data-toggle="modal" data-target="#uploadmodal" class="btn btn-primary">上传</button>
                                        <div id="newgroup" style="display:none;">
                                            <hr/>
                                            <s:form action="file">
                                                <s:textfield name="dest"/>
                                                <s:submit cssClass="btn btn-primary" value="新建" method="create"/>
                                                <button class="btn" onclick="$('#userfile').first('button').click();">取消</button>
                                            </s:form>
                                        </div>
                                        <hr/>
                                        <div class="modal fade hide" id="uploadmodal" style="margin-top:9%">
                                            <div class="modal-header">
                                                <a class="close" data-dismiss="modal">×</a>
                                                <h3>上传文件</h3>
                                            </div>
                                            <div class="modal-body">
                                                <s:form action="file" enctype="multipart/form-data" method="post">
                                                    <s:select cssClass="span2" id="classify" name="dest" list="teacher.fileGroups" listValue="groupName" listKey="groupName"/>
                                                    <script>
        $('#classify').append("<option value='新建分组'>新建分组</option>");
                                                    </script>
                                                    <s:file name="upload" title="选择文件" id="fileid"/>
                                                </div>
                                                <div class="modal-footer">
                                                    <s:submit cssClass="btn btn-primary" method="upload" value="上传" />
                                                </s:form>
                                                <a class="btn" data-dismiss="modal">取消</a>
                                            </div>
                                        </div>
                                        <div class="accordion" id="accordion2">
                                            <s:iterator value="teacher.fileGroups" status="s" id="filegroups">
                                                <div class="accordion-group">
                                                    <div class="accordion-heading">
                                                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#<s:property value="%{'group' + #s.index}"/>">
                                                            <s:property value="groupName"/>
                                                        </a>
                                                    </div>
                                                    <div id="<s:property value="%{'group' + #s.index}"/>" class="accordion-body collapse in">
                                                        <div class="accordion-inner">
                                                            <table class="table table-hover table-striped">
                                                                <table class="table table-hover table-striped">
                                                                    <tbody>
                                                                        <s:iterator value="#filegroups.files" id="files">
                                                                            <tr class="file_panel">
                                                                                <td>
                                                                                    <s:form action="download">
                                                                                        <div class="pull-left" id="filename">
                                                                                            <s:textfield name="downloadFileName" value="%{#files.name}" style="display:none;" />
                                                                                            <s:textfield name="src" value="%{teacher.fileGroups[0].groupName}" style="display:none;" />
                                                                                            <s:property value="#files.name"/>
                                                                                        </div>
                                                                                        <div class="pull-right">
                                                                                            <s:submit cssClass="btn btn-primary btn-mini" value="下载"/>
                                                                                        </s:form>
                                                                                        <s:form action="file">
                                                                                            <s:textfield name="dest" value="%{teacher.fileGroups[0].groupName}" style="display:none;" />
                                                                                            <s:textfield name="name" value="%{#files.name}" style="display:none;" />
                                                                                            <s:submit cssClass="btn btn-danger btn-mini" value="删除" method="deleteFile"/>
                                                                                        </div>
                                                                                        <br/>
                                                                                    </s:form>
                                                                                </td>
                                                                            </tr>
                                                                        </s:iterator>
                                                                    </tbody>
                                                                </table>
                                                        </div>
                                                    </div>
                                                </div>
                                            </s:iterator>
                                        </div>
                                    </div>
                                    <div id="publicfile" class="tab-pane">
                                        <div style="display:inline;">
                                            <input type="text" class="span2">
                                            <button class="btn btn-primary" style="margin-top:-10px;">搜索</button>
                                            <a data-toggle="modal" data-target="#uploadmodal" style="margin-top:-10px;" class="btn btn-primary">上传</a>
                                        </div>
                                        <div class="modal fade hide" id="uploadmodal" style="margin-top:9%">
                                            <div class="modal-header">
                                                <a class="close" data-dismiss="modal">×</a>
                                                <h3>上传文件</h3>
                                            </div>
                                            <div class="modal-body">
                                                <s:form action="file" enctype="multipart/form-data" method="post">
                                                    <s:select cssClass="span2" id="classify" name="dest" list="teacher.fileGroups" listValue="groupName" listKey="groupName"/>
                                                    <s:file name="upload" title="选择文件" id="fileid"/>
                                                </div>
                                                <div class="modal-footer">
                                                    <s:submit cssClass="btn btn-primary" method="upload" value="上传" />
                                                </s:form>
                                                <a class="btn" data-dismiss="modal">取消</a>
                                            </div>
                                        </div>
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

