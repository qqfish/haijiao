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
        <script type="text/javascript" src="js/index.js"></script>
        <script type="text/javascript" src="js/teacherIndex.js"></script>
        <script type="text/javascript" src="js/jquery.rateit.min.js"></script>

        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/rateit.css" type="text/css">
        <link rel="stylesheet" href="css/kuaipanDir.css" type="text/css">

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
                    <div class="span8 module" style="padding:12px;">
                        <ul class="nav nav-pills">
                            <li id="l3"  class="active"><a href="#lesson_area" data-toggle="tab" >开设课程</a></li>

                            <li id="l6"><a href="#file_area" data-toggle="tab" onclick="getFileList(true, null)">个人文件</a></li>
                            <li id="17"><a href="#publicfile_area" data-toggle="tab">公共文件</a></li>
                            <li id="l4"><a href="#bill_area" data-toggle="tab" >交易记录</a></li>
                            <li id="l5"><a href="#comment_area" data-toggle="tab" >评论</a></li>
                        </ul>
                        <div class="tab-content">

                            <div class="tab-pane fade  active in" id='lesson_area'>
                                <a id="showtip" class="pull-right" data-html="true" data-toggle="popover" data-trigger="hover" data-placement="bottom" data-original-title="开设课程步骤" data-content="
                                   <small><strong>第一步：①开设课程</strong><br/>提示：点击“课程列表”进行开课（点击“小叉”可取消）。</small><br/>
                                   <small><strong>第二步：②设定时间</strong><br/>提示：设定空闲时间以接受学生预约（可以取消空闲时间）。</small><br/>
                                   <small><strong>第三步：③处理预约</strong><br/>提示：查看处理学生预约订单（24小时必须确认预约订单）。</small><br/>
                                   <small><strong>第四步：④电子备课</strong><br/>提示：有预约请提前备课（支持pdf、doc、ppt、jpg格式）。</small><br/>
                                   "><i class="icon-question-sign"></i><small>如何开设课程</small></a>
                                <br/>
                                <script>
                                    $("#showtip").popover();
                                </script>
                                <h4>老师您好，您目前开设课程的情况如下：</h4>
                                <table>
                                <s:iterator value="teacher.lessons" id="ls">
                                    <s:if test="delete==false">
                                        <s:form action="dealLesson.action">
                                            <div style="display:none;">
                                                <s:textfield cssClass="span2" value="%{name}" type="text" name="lessonName"/>
                                                <s:submit cssClass="btn" id="delete_%{name}" value="delete" method="deleteLesson"/>
                                            </div>
                                            <span class="label label-info" style="margin:5px;"><s:property value="name" /><a href="#" id="delete_click_<s:property value="name" />"><i class="icon-remove icon-white" ></i></a>
                                                <br/><s:property value="price"/>.00元 
                                            </span> 
                                        </s:form>
                                    </s:if>
                                </s:iterator>
                                <br/><br/>
                                <hr/>
                                <h5>课程列表</h5>

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
                                <div class="modal hide fade" id="addLessonModal">
                                    <div class="modal-body">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <s:form action="dealLesson.action">
                                            <span style="display:none;"><s:textfield cssClass="span2" id="deal_lesson" name="lessonName"/></span>
                                            <div class="input-append">
                                                <s:textfield placeholder="课程价格" name="price" onchange="checkPositiveNumber($(this));"/>
                                                <span class="add-on">.00</span>
                                                <s:submit id="add" cssClass="btn" value="add" method="addLesson" cssStyle="margin-left:5px;"/>
                                            </div>
                                            <div id="lesson_tip" style="font-size:9px; color: red; display: none;"></div>
                                        </s:form>
                                    </div>

                                </div>
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
                                            <th>订单号</th>
                                            <th>下单时间</th>
                                            <th>学生</th>
                                            <th>课程</th>
                                            <th>单价(元)</th>
                                            <th>小时数</th>
                                            <th>总计(元)</th>
                                            <th>状态</th>
                                            <th>备注</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>        
                                        <s:iterator value="billList" id="billList">
                                            <tr>
                                                <td><s:property value="id"/></td>
                                                <td><s:property value="createTime"/></td>
                                                <td><s:property value="student.name" /></td>
                                                <td><s:property value="lesson.name" /></td>

                                                <td><s:property value="lesson.price" /></td>
                                                <td><s:property value="duration" /></td>
                                                <td><s:property value="money" /></td>
                                                <s:if test="status == 0">
                                                    <td>等待教师处理</td>
                                                </s:if>
                                                <s:elseif test="status == 1">
                                                    <td>教师拒绝请求</td>
                                                </s:elseif>
                                                <s:elseif test="status == 2">
                                                    <td>等待学生支付</td>
                                                </s:elseif>
                                                <s:elseif test="status == 3">
                                                    <td>上课中</td>
                                                </s:elseif>
                                                <s:elseif test="status == 4">
                                                    <td>教师确认完成</td>
                                                </s:elseif>
                                                <s:if test="status == 0">
                                                    <td>
                                                        <s:form action="dealWithReservation">
                                                            <s:textfield name="billId" value="%{id}" cssStyle="display:none;"/>
                                                            <s:submit value="接受" method="accept" cssClass="btn btn-info btn-mini"/>
                                                            <s:submit value="拒绝" method="deny" cssClass="btn btn-info btn-mini"/>
                                                        </s:form>
                                                    </td>
                                                </s:if>
                                                <s:elseif test="status >= 4">
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
                                        </s:elseif>
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
                                    </s:form>
                                </div>
                            </div>

                            <div id="filelist">
                            </div>
                        </div>
                        <div id="publicfile_area" class="tab-pane fade">
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


                            <div class="dn" style="display: block;">                                          

                                <!-- 文件显示区域 -->
                                <div class="dirContent">
                                    <!-- 列表视图 -->
                                    <div class="list-view" id="file-list" style="height: 431px;">
                                        <!--[if lt ie 8]><div style="+zoom:1;"><![endif]-->
                                        <table class="file_list_table">
                                            <thead>
                                                <tr>
                                                    <td class="name">文件名</td>
                                                    <td style="width: 20%;"></td>
                                                    <td style="width: 10%;">大小</td>
                                                    <td class="w150 ">修改日期</td>
                                                </tr>
                                            </thead>

                                            <!-- 显示数据 -->
                                            <tbody class="list-data-container">
                                                <s:iterator value="teacher.fileGroups">
                                                    <tr>
                                                        <td class="file-name folder-icon">
                                                            <a href="javascript:;" class="name"><s:property value="groupName"/></a>
                                                        </td>
                                                    </tr>
                                                </s:iterator>
                                                <tr id="80305236916305944_0" data-file-id="80305236916305944" class="list-view-hover">
                                                    <td class="file-name pdf-icon">       
                                                        <a href="javascript:;" class="name" title="moive">moive</a>       
                                                        <input type="text" value="moive" class="rename-file-input">   
                                                    </td>   
                                                    <td></td>   
                                                    <td></td>    <td>2013-06-16 11:59:22</td></tr>
                                                <tr id="80305236916305928_0" data-file-id="80305236916305928" class="">   
                                                    <td>        <span class="dib-inline checkbox"></span>    </td>   
                                                    <td class="file-name ppt-icon">        <a href="javascript:;" class="name" title="homework">homework</a>      
                                                        <input type="text" value="homework" class="rename-file-input">   
                                                    </td>   
                                                    <td> </td>   
                                                    <td></td>    <td>2013-05-23 09:08:34</td></tr>
                                                <tr id="80305236916305925_0" data-file-id="80305236916305925" class="">   
                                                    <td>        <span class="dib-inline checkbox"></span>    </td>    
                                                    <td class="file-name folder-icon">        <a href="javascript:;" class="name" title="document">document</a>    
                                                        <input type="text" value="document" class="rename-file-input">  
                                                    </td>    
                                                    <td></td>   
                                                    <td></td>    <td>2013-05-16 13:27:03</td></tr>
                                                <tr id="80305236916305922_0" data-file-id="80305236916305922" class="">   
                                                    <td>        <span class="dib-inline checkbox"></span>    </td>   
                                                    <td class="file-name folder-icon">        <a href="javascript:;" class="name" title="code">code</a>   
                                                        <input type="text" value="code" class="rename-file-input">  
                                                    </td>   
                                                    <td></td> 
                                                    <td></td>    <td>2013-05-16 09:25:12</td></tr>
                                            </tbody>
                                        </table>
                                        <!--[if lt ie 8]></div><![endif]-->

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

