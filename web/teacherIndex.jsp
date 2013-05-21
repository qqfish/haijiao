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
          style="background: url(images/background.jpg);">

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <input type="hidden" id="nextPageMessage" value="<s:property value="nextPageMessage" />"/>
        <div class="container wrapper">
            <div class="row" style="margin:-5px;">
                <div id="sideInfo" class="span3 module" style="padding:12px;">
                    <img width="210px" height="210px" src="<s:property value="teacher.picUrl"/>" class="img-polaroid"/>
                    <h1><s:property value="teacher.name"/>
                    <a class='btn btn-primary btn-small' href="enterPublicRoom.action?teaEmail=<s:property value='teacher.email' default='null' />">在线试讲</a>
                    </h1>          
                    <hr/>
                    <p><s:property value="teacher.province"/></p>
                    <p><s:property value="teacher.email"/></p>
                    <p><s:property value="teacher.createdateToString()"/> 加入</p>
                    <table class="table table-hover table-striped">
                        <tbody>
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
                    <div class="span8 module" style="padding:12px;">
                    <s:a action="toChangeInfo.action">
                        <button type="button" class="btn btn-primary pull-right"><i class="icon-pencil icon-white"></i>修改资料</button>
                    </s:a>
                    <ul class="nav nav-pills">
                        <li id="l1" class="active"><a href="#student_area" data-toggle="tab">学生列表</a></li>
                        <li id="l2"><a href="#schedule_area" data-toggle="tab" >课程表</a></li>
                        <li id="l3"><a href="#lesson_area" data-toggle="tab" >添加/删除课程</a></li>
                        <li id="l4"><a href="#bill_area" data-toggle="tab" >交易记录</a></li>
                        <li id="l5"><a href="#comment_area" data-toggle="tab" >评论</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id='student_area'>
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
                                                                            <button type="button" id="dealApply_stop_button_<s:property value="id"/>" class="btn btn-info btn-mini">暂停</button>
                                                                            <a class="btn btn-info btn-mini" href="getMail.action?toEmail=<s:property value="student.email" />">私信</a>
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
                            <a class="btn btn-primary pull-right" data-toggle="modal" data-target="#choosemodal">增加空闲时间</a><br/>
                            <div class="modal fade hide" id="choosemodal" style="height: auto;width:650px;margin-top:100px;">
                                <div class="modal-header" style="height: 50px">
                                    <a class="close" data-dismiss="modal">×</a>
                                    <h3>请选择课程时间</h3>
                                    <p id="chooseError"></p>
                                </div>
                                <div class="modal-body" style="height: 320px">
                                    <div class="schedule_panel" id="schedule_panel">
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
                                        <s:form id="schedule_form" action="addFreeTime.action">
                                            <s:textfield id="schedule_json" name="json" style="display:none;"></s:textfield>
                                                <a class="btn btn-primary pull-right" id="upload" >添加</a>
                                        </s:form>
                                        <p></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='lesson_area'>
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
                                <p style="font-size: 9px;">
                                    * 小提示①：点击课程后面的小叉可以取消开设该课程哦！<br/>
                                    * 小提示②：您可以通过下面的输入框开设新的课程哦！
                                </p>
                                <br/><br/>
                                <s:form action="dealLesson.action">
                                    <s:textfield cssClass="span2" type="text" value="" name="lessonName" placeholder="请输入新的课程名"/>
                                    <a href="#" id="add_click"><i class="icon-plus" style='margin-left:5px;margin-top: -4px'></i></a>
                                    <div style="display: none;"><s:submit id="add" cssClass="btn" value="add" method="addLesson"/></div>
                                </s:form>
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
                                                        <a href="#comment" type="button" class="btn btn-info btn-mini" data-toggle="modal">评论</a>
                                                    </s:if>
                                                    <s:else>
                                                        <a type="button" class="btn btn-info btn-mini disabled" data-toggle="modal">评论</a>
                                                    </s:else>
                                                </td>
                                            </tr>
                                        <div id="comment" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                <h3 id="myModalLabel">评论</h3>
                                            </div>
                                            <s:form action="makeCommentReply.action">
                                                <div class="modal-body">
                                                    <s:textfield name="id" value="%{id}" cssStyle="display:none;"></s:textfield>
                                                    内容<s:textarea name="content" autofocus="autofocus" id="content"></s:textarea>
                                                        <br/>
                                                        评分<div id="rate" class="rateit" data-rateit-step="1" data-rateit-ispreset="true"></div>
                                                    <s:textfield id="score" name="score" cssStyle="display:none;"></s:textfield>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                                                    <s:submit cssClass="btn btn-primary" method="comment" value="提交"></s:submit>
                                                    </div>
                                                    <script type="text/javascript">
                                                        $("#rate").bind('rated', function (event, value){ $('#score').val(value);});
                                                        $("#rate").bind('over', function(event,value){ $(this).attr('title', value);});                                                
                                                        $("#cmtsmt").click(function(event){
                                                            if(/^\s*$/.test($('score').val()) || /^\s*$/.test($("#content").val()))
                                                                event.preventDefault();
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
                                    <tr>
                                        <td>
                                            <blockquote>
                                                <s:if test="billList.size()<=0">
                                                    还没有评论哦~！
                                                </s:if>
                                                <s:else>
                                                    <s:iterator value="billList" id="billList">
                                                        <s:if test="stot != null">
                                                            <h4><s:property value="student.name" /><label class="label label-important pull-right">评分:<s:property value="stot.score" /></label></h4>
                                                            <small>
                                                                <span><s:property value="stot.content" /></span>
                                                                <span class="pull-right">
                                                                    <s:if test="stot.reply == null">
                                                                        <a href="#reply" type="button" class="btn btn-info btn-mini" data-toggle="modal">回复</a>
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
                                                            <div id="reply" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
        </div>

        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>

