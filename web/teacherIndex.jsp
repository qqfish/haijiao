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
        <script type="text/javascript" src="js/bootstrap.file-input.js"></script>


        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/rateit.css" type="text/css">
        <link rel="stylesheet" href="css/kuaipanDir.css" type="text/css">
        <link rel="stylesheet" href="css/indexFile.css" type="text/css">
        <link rel="stylesheet" href="css/payment.css" type="text/css">

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

        <script type="text/javascript">
            var flag = 1;
            function edit_share_info() {
                document.getElementById("share_info").readOnly = false;
                document.getElementById("share_info").readonly = false;
            }
            function save_share_info() {
                document.getElementById("share_info").setAttribute("readOnly", "true");
                document.getElementById("share_info").readonly = true;
                document.getElementById("share_iframe").src = "http://service.weibo.com/staticjs/weiboshare.html?" +
                        "url=http://haijiaoedu.com&amp;appkey=&amp;type=3&amp;language=zh_cn&amp;dpc=1&title=海角教育--" +
                        document.getElementById("share_info").value;
            }
            function share_iframe_load() {
                if (flag) {
                    flag = 0;
                    document.getElementById("share_iframe").src = "http://service.weibo.com/staticjs/weiboshare.html?" +
                            "url=http://haijiaoedu.com&amp;appkey=&amp;type=3&amp;language=zh_cn&amp;dpc=1&title=海角教育--" +
                            document.getElementById("share_info").value;
                }
            }
        </script>
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
                    <s:if test="teacher.coin>500">
                        <a class='btn btn-primary btn-small' style="margin-left: 15px;" data-toggle="modal" href="#moneyRequestModal"><i class="icon-user icon-white"></i>余额提现</a>
                    </s:if>
                    <s:else>
                        <a class='btn btn-primary btn-small disabled' id="moneyRequestButton" style="margin-left: 15px;" data-toggle="popover" data-trigger="hover" disabled data-content="高于500元才能提现" data-placement="bottom"><i class="icon-user icon-white"></i>余额提现</a>
                        <script>
                            $("#moneyRequestButton").popover();
                        </script>
                    </s:else>
                    <div class="modal fade hide" id="moneyRequestModal">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h3>提现确认<small class="text-warning">*请认真检查填写内容，请求会在两个工作日内完成</small></h3>
                        </div>
                        <div class="modal-body">
                            <form class="form-inline">
                                <label class="span1">姓名</label>
                                <label class="span5"><s:property value="teacher.name" /><small class="text-warning">(*请确认与银行卡信息匹配)</small></label>
                            </form>
                            <form class="form-inline">
                                <label class="span1">当前余额</label>
                                <label class="span5"><strong style="color:#fc491e;"><s:property value="teacher.coin" />.00</strong>元</label>
                            </form>
                            <label class="span1">银行卡号</label>
                            <s:if test="teacher.bankname!=null&&teacher.bankcard!=null">
                                <label class="radio offset1">
                                    <input type="radio" name="option1" onclick="$('#moneyRequestbc').val('<s:property value="teacher.bankcard" />');
                                            $('#moneyRequestbank').val('<s:property value="teacher.bankname" />');" checked>
                                    <span class="span2"><s:property value="teacher.bankname" /></span>
                                    <span><s:property value="teacher.bankcard" /></span>
                                </label>
                                <label class="radio offset1">
                                    <input type="radio" name="option1" value="option2" onclick="$('#moneyRequestbc').val($('#newBankcard').val());
                                            $('#moneyRequestbank').val($('#newBankname').val());">
                                    <select id="newBankname" class="span2">
                                        <option>中国工商银行</option>
                                        <option>中国银行</option>
                                        <option>招商银行</option>
                                        <option>交通银行</option>
                                        <option>中国农业银行</option>
                                        <option>中国建设银行</option>
                                    </select>
                                    <input type="text" id="newBankcard"placeholder="新的银行卡号" onchange="$('#moneyRequestbc').val($(this).val());"/>
                                </label>
                            </s:if>
                            <s:else>
                                <script>
                                    $(document).ready(function() {
                                        $('#moneyRequestbank').val("中国工商银行");
                                    });
                                </script>
                                <select id="newBankname" onchange="$('#moneyRequestbank').val($(this).val());">
                                    <option>中国工商银行</option>
                                    <option>中国银行</option>
                                    <option>招商银行</option>
                                    <option>交通银行</option>
                                    <option>中国农业银行</option>
                                    <option>中国建设银行</option>
                                </select>
                                <input type="text" id="newBankcard"placeholder="新的银行卡号" onchange="$('#moneyRequestbc').val($(this).val());"/>
                            </s:else>
                        </div>
                        <div class="modal-footer">
                            <s:form action="moneyRequest" cssStyle="padding:0px; margin:0px">
                                <s:textfield id="moneyRequestbank" name="bankname" value="%{teacher.bankname}" cssStyle="display:none;"/>
                                <s:textfield id="moneyRequestbc" name="bankcard" value="%{teacher.bankcard}" cssStyle="display:none;" />
                                <s:submit method="temp" cssClass="btn btn-success" value="确认"></s:submit>
                                <s:submit method="save" cssClass="btn" value="确认并设为默认"></s:submit>
                            </s:form>
                        </div>
                    </div>
                    <s:a action="toChangeInfo.action">
                        <button type="button" class="btn btn-small btn-primary pull-right" style="margin-right: 15px;"><i class="icon-pencil icon-white"></i>修改资料</button>
                    </s:a>
                    <!--                    <hr/>
                                        <div style="margin-left: 10px;">
                                            <small>
                    <s:property value="teacher.getDirectProvince()"/> - 
                    <s:property value="teacher.getDirectCity()"/> - 
                    <s:property value="teacher.getDirectDistrict()"/><br/><br/>
                </small>
                <p><s:property value="teacher.email"/></p>
                <p><s:property value="teacher.createdateToString()"/> 加入</p>
            </div>-->
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
                            <li id="l3"><a href="#lesson_area" data-toggle="tab" >开设课程</a></li>
                            <li id="l6"><a href="#file_area" data-toggle="tab">我的课件</a></li>
                            <li id="l7"><a href="#publicfile_area" data-toggle="tab">公共课件</a></li>
                            <li id="l4"><a href="#bill_area" data-toggle="tab" >订单处理</a></li>
                            <li id="l8"><a href="#payment_area" data-toggle="tab" >账单明细</a></li>
                            <li id="l5"><a href="#comment_area" data-toggle="tab" >评论</a></li>

                            <!--  add a button for previewing Profile -->
                            <li style="float:right;margin-right: 20px"> <button class="btn" data-toggle="modal" data-target="#personal_profile">分享个人主页</button></li>
                        </ul>

                        <!--   personal proflie   -->
                        <div id="personal_profile" class="modal fade hide" style="margin-top: 80px">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h3>主页预览</h3>
                                </div>
                                <div class="modal-body">
                                    <textarea id="share_info" style="width:95%" rows="6" readonly="readonly">
    姓名：<s:property value="teacher.name" /> 
    学校：<s:property value="teacher.school" />
    专业：<s:property value="teacher.major" />
    线下授课区域：<s:property value="teacher.underlineArea" default="暂无"/>
    在线试讲页面：http://haijiaoedu.com/getTeacherInfo.action?teacherEmail=<s:property value="teacher.email" />
                                </textarea>

                                <p><button class="btn" onclick ="edit_share_info()">编辑</button><button class="btn" onclick ="save_share_info()">保存</button>&nbsp;【分享主页信息】                                                        
                                    <iframe id="share_iframe" onload="share_iframe_load()"allowtransparency="true" frameborder="0" scrolling="no"
                                            src="http://service.weibo.com/staticjs/weiboshare.html?url=http://haijiaoedu.com&amp;appkey=&amp;type=3&amp;language=zh_cn&amp;dpc=1" width="72" height="33">
                                    </iframe>
                            </div>
                            <div style="margin-left: 20px">
                                <p style="color:#999999">推广建议：链接可以转发以下网址获取更多的预约</p>
                                <p style="color:#999999">1.&nbsp;58同城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.&nbsp;赶集网</p>
                                <p style="color:#999999">3.&nbsp;百姓网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4.&nbsp;高校bbs</p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn" style="margin-left:30px" data-dismiss="modal">关闭</button>
                            </div>
                        </div>
                    </div>

                    <div class="tab-content">

                        <div class="tab-pane fade" id='lesson_area'>
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
                                                <br/><s:property value="price"/>.00元/45分钟
                                            </span> 
                                        </s:form>
                                    </s:if>
                                </s:iterator>
                                <br/><br/>
                                <hr/>
                                <h5>可开设课程列表</h5>

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
                                                <s:textfield placeholder="课程价格" name="price" value="1" onchange="checkNatureNumber($(this));"/>
                                                <span class="add-on">.00元/45分钟</span>
                                                <s:submit id="add" cssClass="btn" value="添加" method="addLesson" cssStyle="margin-left:5px;"/>
                                            </div>
                                            <div id="lesson_tip" style="font-size:9px; color: red; display: none;"></div>
                                        </s:form>
                                    </div>

                                </div>
                            </table>
                            <hr/>
                            <div class="span3">
                                <h5>在线试讲</h5>
                                <div class="btn-group" data-toggle="buttons-radio">
                                    <s:if test="teacher.audition">
                                        <button type="button" class="btn btn-primary active">是</button>
                                        <s:a action="setTeacherStatus" method="audition" cssClass="btn btn-primary">否</s:a>
                                    </s:if>
                                    <s:else>
                                        <s:a action="setTeacherStatus" method="audition" cssClass="btn btn-primary">是</s:a>
                                            <button type="button" class="btn btn-primary active">否</button>
                                    </s:else>
                                </div>
                            </div>
                            <div class="span3">
                                <h5>接受预约</h5>
                                <div class="btn-group" data-toggle="buttons-radio">
                                    <s:if test="teacher.reserve">
                                        <button type="button" class="btn btn-primary active">是</button>
                                        <s:a action="setTeacherStatus" method="reserve" cssClass="btn btn-primary">否</s:a>
                                    </s:if>
                                    <s:else>
                                        <s:a action="setTeacherStatus" method="reserve" cssClass="btn btn-primary">是</s:a>
                                            <button type="button" class="btn btn-primary active">否</button>
                                    </s:else>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='bill_area'>
                            <div id="billlist"></div>
                            <div id="comment_modal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <h3 id="myModalLabel">评论</h3>
                                </div>
                                <s:form action="makeCommentReply.action">
                                    <div class="modal-body">
                                        <s:textfield name="id" id="comment_id" cssStyle="display:none;"></s:textfield>
                                        内容<s:textarea name="content" autofocus="autofocus" id="content"></s:textarea>
                                            <br/>
                                            评分<div id="comment_rate" class="rateit" data-rateit-step="1" data-rateit-ispreset="true"></div>
                                        <s:textfield id="comment_score" name="score" value="0" cssStyle="display:none;"></s:textfield>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                                        <s:submit cssClass="btn btn-primary" method="comment" value="提交"></s:submit>
                                        </div>
                                        <script type="text/javascript">
                                            $(".commentA").click(function() {
                                                var id = $(this).attr("id");
                                                $("#comment_rate").bind('rated', function(event, value) {
                                                    $('#comment_score').val(value);
                                                });
                                                $("#comment_rate").bind('over', function(event, value) {
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
                        </div>
                        <div class="tab-pane fade" id='comment_area'>

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
                                    <p class="text-warning">*上传的文件会被自动转换为pdf格式</p>
                                </div>
                                <div class="modal-body">
                                    <s:form action="file!upload.action" id="fileUpload" enctype="multipart/form-data" method="post">
                                        <div class="control-group">
                                            <s:select cssClass="span2" id="classify" name="dest" list="teacher.fileGroups" listValue="groupName" listKey="groupName"/>
                                            <s:file name="upload" title="上传" id="fileid" onchange="$('#fileUpload').submit();"/>
                                        </div>
                                    </s:form>
                                </div>
                            </div>

                            <div id="filelist">
                            </div>
                            <div class="modal hide fade" id="movefileModal">
                                <div class="modal-body">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <s:form action="file" cssStyle="margin:0px;">
                                        <dl class="dl-horizontal">
                                            <dt style="font-size: 18px; margin-top: 4px;">文件组:</dt>
                                            <dd>
                                                <div class="input-append">
                                                    <s:select cssClass="span2" name="dest" list="teacher.fileGroups" listValue="groupName" listKey="groupName"/>
                                                    <s:textfield cssStyle="display: none;" id="movefileSrc" name="src" />                                                    
                                                    <s:textfield cssStyle="display: none;" id="movefileName" name="uploadFileName" />
                                                    <s:submit cssClass="btn" value="移动" method="move" cssStyle="margin-left:5px;"/>
                                                </div>
                                            </dd>
                                        </dl>
                                    </s:form>
                                </div>
                            </div>
                        </div>
                        <div id="publicfile_area" class="tab-pane fade">
                            <div style="display:inline;">
                                <input id="keyword" type="text" class="span2"/>
                                <button class="btn btn-primary" style="margin-top:-10px;" onclick="getPublicFilelist(1, $('#keyword').val());">搜索</button>
                            </div>
                            <div id="publicFileList"></div>
                            <div class="modal hide fade" id="publicfileModal">
                                <div class="modal-body">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <s:form action="addFileFromPublic" cssStyle="margin:0px;">
                                        <dl class="dl-horizontal">
                                            <dt style="font-size: 18px; margin-top: 4px;">文件组:</dt>
                                            <dd>
                                                <div class="input-append">
                                                    <s:select cssClass="span2" id="classify" name="groupName" list="user.fileGroups" listValue="groupName" listKey="groupName"/>
                                                    <s:hidden name="publicFileId" id="publicFileModalId"/>
                                                    <s:submit cssClass="btn" value="添加" cssStyle="margin-left:5px;"/>
                                                </div>
                                            </dd>
                                        </dl>
                                    </s:form>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="payment_area">
                            <div class="money-header title-border">
                                <strong>账单记录</strong>
                                <small class="text-warning">您目前为普通用户，您的手续费为10%,详细请见(<a class="btn btn-link btn-mini">*</a>)</small>
                                <span class="pull-right mute">余额:
                                    <strong style="color:#53a000"><s:property value="teacher.coin" /></strong>
                                </span>
                            </div>
                            <div id="paymentlist"></div>
                        </div>
                        <div>
                            <s:if test="tab=='bill'">
                                <script>
                                    $("#bill_area").addClass("active in");
                                    $("#l4").addClass("active");
                                </script>
                            </s:if>
                            <s:elseif test="tab=='publicfile'">
                                <script>
                                    $("#publicfile_area").addClass("active in");
                                    $("#l7").addClass("active")
                                </script>
                            </s:elseif>
                            <s:elseif test="tab=='file'">
                                <script>
                                    $("#file_area").addClass("active in");
                                    $("#l6").addClass("active")
                                </script>
                            </s:elseif>
                            <s:elseif test="tab=='comment'">
                                <script>
                                    $("#comment_area").addClass("active in");
                                    $("#l5").addClass("active")
                                </script>
                            </s:elseif>
                                <s:elseif test="tab=='payment'">
                                <script>
                                    $("#payment_area").addClass("active in");
                                    $("#l8").addClass("active")
                                </script>
                            </s:elseif>
                            <s:else>
                                <script>
                                    $("#lesson_area").addClass("active in");
                                    $("#l3").addClass("active")
                                </script>
                            </s:else>
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

