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
    <body onload="addSchedule.drawSchedule(<s:property value='scheduleBean.toJson()' default='null'/>);
            viewSchedule.drawSchedule(<s:property value='scheduleBean.toJson()' default='null' />);">

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="container">
            <div class="row">
                <div id="sideInfo" class="span3">
            <!--              <img src="<s:property value="#session.teacher.picUrl"/>"/>-->
                    <img width="210px" height="210px" src="images/1.jpg" class="img-polaroid"/>
                    <h1>邹润阳<s:property value="#session.teacher.name"/></h1>          
                    <hr/>
                    <p>中国 上海</p>
                    <p>woshishabi@22.com</p>
                    <p>2012-1-1 加入</p>
                    <table class="table table-hover table-striped">
                        <tbody>
                            <tr>
                                <td>预约数</td>
                                <td>1</td>
                            </tr>
                            <tr>
                                <td>完成数</td>
                                <td>1</td>
                            </tr>
                            <tr>
                                <td>评分</td>
                                <td>1</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="span9">
                    <button type="button" class="btn btn-primary pull-right"><i class="icon-pencil icon-white"></i>修改资料</button>
                    <ul class="nav nav-pills">
                        <li class="active"><a href="#student_area" data-toggle="tab">学生列表</a></li>
                        <li><a href="#schedule_area" data-toggle="tab" >课程表</a></li>
                        <li><a href="#bill_area" data-toggle="tab" >交易记录</a></li>
                        <li><a href="#comment_area" data-toggle="tab" >评论</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id='student_area'>
                            <table class="table table-hover table-striped">
                                <tbody>
                                    <tr>
                                        <td>
                                            <blockquote>
                                                <h4>邹润阳<label class="label label-info pull-right">等待审批</label></h4>
                                                <small>
                                                    <span>周二19:00-20:00</span>
                                                    <span style="margin-left: 10px">剩余 12 次课程</span>
                                                    <span style="margin-left: 10px">下次上课在 3 天后</span>
                                                    <span style="margin-left: 10px"class="pull-right">
                                                        <button type="button" class="btn btn-info btn-mini">暂停</button>
                                                        <button type="button" class="btn btn-info btn-mini">确认</button>
                                                        <button type="button" class="btn btn-info btn-mini">取消</button>
                                                    </span>
                                                </small>
                                            </blockquote>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <blockquote>
                                                <h4>邹润阳<label class="label label-info pull-right">等待审批</label></h4>
                                                <small>
                                                    <span>周二19:00-20:00</span>
                                                    <span style="margin-left: 10px">剩余 12 次课程</span>
                                                    <span style="margin-left: 10px">下次上课在 3 天后</span>
                                                    <span class="pull-right">
                                                        <button type="button" class="btn btn-info btn-mini">暂停</button>
                                                        <button type="button" class="btn btn-info btn-mini">确认</button>
                                                        <button type="button" class="btn btn-info btn-mini">取消</button>
                                                    </span>
                                                </small>
                                            </blockquote>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <blockquote>
                                                <h4>邹润阳<label class="label label-info pull-right">等待审批</label></h4>
                                                <small>
                                                    <span>周二19:00-20:00</span>
                                                    <span style="margin-left: 10px">剩余 12 次课程</span>
                                                    <span style="margin-left: 10px">下次上课在 3 天后</span>
                                                    <span class="pull-right">
                                                        <button type="button" class="btn btn-info btn-mini">暂停</button>
                                                        <button type="button" class="btn btn-info btn-mini">确认</button>
                                                        <button type="button" class="btn btn-info btn-mini">取消</button>
                                                    </span>
                                                </small>
                                            </blockquote>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id='schedule_area'>
                            <table class="static_schedule_table" width="100%" border="0" style="z-index: 1">
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
                            <a class="btn btn-primary pull-right" data-toggle="modal" data-target="#choosemodal">增加空闲时间</a>
                            <div class="modal fade hide" id="choosemodal" style="height: auto;width:650px;margin-top:100px;">
                                <div class="modal-header" style="height: 50px">
                                    <a class="close" data-dismiss="modal">×</a>
                                    <h3>请选择课程时间</h3>
                                    <p id="chooseError"></p>
                                </div>
                                <div class="modal-body" >
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
                                        <s:form id="schedule_form" action="addFreeTime.action">
                                            <s:textfield id="schedule_json" name="json" style="display:none;"></s:textfield>
                                                <a class="btn btn-primary pull-right" id="upload" >添加</a>
                                        </s:form>
                                        <p></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='bill_area'>
                            <table class="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>姓名</th>
                                        <th>课程</th>
                                        <th>金额</th>
                                        <th>时间</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>邹润阳</td>
                                        <td>打架</td>
                                        <td>￥2</td>
                                        <td>昨天</td>
                                        <td><button type="button" class="btn btn-info btn-mini">评论</button></td>
                                    </tr>
                                    <tr>
                                        <td>邹润阳</td>
                                        <td>打架</td>
                                        <td>￥2</td>
                                        <td>昨天</td>
                                        <td><button type="button" class="btn btn-info btn-mini">评论</button></td>
                                    </tr>
                                    <tr>
                                        <td>邹润阳</td>
                                        <td>打架</td>
                                        <td>￥2</td>
                                        <td>昨天</td>
                                        <td><button type="button" class="btn btn-info btn-mini">评论</button></td>
                                    </tr>   
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id='comment_area'>
                            <table class="table table-hover table-striped">
                                <tbody>
                                    <tr>
                                        <td>
                                            <blockquote>
                                                <h4>邹润阳<label class="label label-important pull-right">评分:0.0</label></h4>
                                                <small>
                                                    <span>这老师是傻逼，大家千万不要选他</span>
                                                    <span class="pull-right">
                                                        <button type="button" class="btn btn-info btn-mini">回复</button>
                                                    </span>
                                                </small>
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

