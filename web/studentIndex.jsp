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
        <script type="text/javascript" src="js/index.js"></script>
        <script type="text/javascript" src="js/jquery.rateit.min.js"></script>
        <script type="text/javascript" src="js/studentIndex.js"></script>
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
    <body>

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <input type="hidden" id="nextPageMessage" value="<s:property value="nextPageMessage" />"/>
        <div class="container wrapper">
            <div class="row" style="margin:-5px;">
                <div id="sideInfo" class="span3 module" style="padding:12px;">
                    <img width="210px" height="210px" src="<s:property value="student.picUrl"/>" class="img-polaroid"/>
                    <h4 style="margin-left:10px;"><s:property value="student.name"/></h4> 
                    <s:a action="toChangeInfo.action">
                        <button type="button" class="btn btn-mini btn-primary" style="margin-left:10px"><i class="icon-pencil icon-white"></i>修改资料</button>
                    </s:a>
                    <hr/>
                    <div style="margin-left:10px">
                        <p><s:property value="student.grade"/></p>
                        <p><s:property value="student.email"/></p>
                        <p><s:property value="student.createdateToString()"/> 加入</p>
                    </div>
                </div>
                <div class="span8 module" style="padding:12px">
                    <ul class="nav nav-pills">
                        <li id="l3"><a href="#bill_area" data-toggle="tab" >交易记录</a></li>
                        <li id="l5"><a href="#file_area" data-toggle="tab" >个人文件</a></li>
                        <li id="l6" ><a href="#publicfile_area" data-toggle="tab">公共文件</a></li>
                        <li id="l4" ><a href="#comment_area" data-toggle="tab" >评论</a></li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane fade" id='bill_area'>

                            <div class="btn-group" style="float:right">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                                    订单选择
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu pull-right">
                                    <li><a tabindex="-1" href="#">所有订单</a></li>
                                    <li><a tabindex="-1" href="#">已完成</a></li>
                                    <li><a tabindex="-1" href="#">未完成</a></li>
                                </ul>
                            </div>

                            <table class="table table-striped table-hover" >
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th class="span2">老师</th>
                                        <th>课程</th>
                                        <th>总计(元)</th>
                                        <th>状态</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>        
                                    <s:iterator value="student.billList" id="billList">
                                        <tr>
                                            <td><br /><s:property value="id"/></td>
                                            <td><strong><s:property value="teacher.name" /></strong><br />
                                                <span class="muted" style="font-size:9px;">电话:<s:property value="teacher.tel" default="无"/><br />
                                                    备注:<s:property value="ps" default="无" /></span>

                                            </td>
                                            <td><strong><s:property value="lesson.name" /></strong><br />
                                                <s:property value="duration" />课时
                                            </td>
                                            <td><strong class="text-error" style="font-size:14px;"><br /><s:property value="money" />.00</strong></td>

                                            <s:if test="status == 0">
                                                <td><br /><label class="label label-warning" style="font-size:9px;">等待确认</label></td>
                                                <td>
                                                    <br /><a class="btn btn-link btn-mini">取消</a>
                                                </td>
                                            </s:if>
                                            <s:elseif test="status == 1">
                                                <td><br /><label class="label" style="font-size:9px;">拒绝请求</label></td>
                                                <td>
                                                    <br /><a class="btn btn-mini btn-link">举报</a>
                                                </td>
                                            </s:elseif>
                                            <s:elseif test="status == 2">
                                                <td><br /><label class="label label-important" style="font-size:9px;">等待支付</label></td>
                                                <td>
                                                    <br /><a class="btn btn-mini btn-success">支付</a>
                                                    <br /><a class="btn btn-mini btn-link">取消</a>
                                                </td>
                                            </s:elseif>
                                            <s:elseif test="status == 3">
                                                <td><br /><label class="label label-info" style="font-size:9px;">正在上课</label></td>
                                                <td>
                                                    <br /><a class="btn btn-mini btn-link">举报</a>
                                                </td>
                                            </s:elseif>
                                            <s:elseif test="status == 4">
                                                <td><br /><label class="label label-important" style="font-size:9px;">完成授课</label></td>
                                                <td>
                                                    <br /><a class="btn btn-mini btn-success">完成</a>
                                                    <br /><a class="btn btn-mini btn-link">举报</a>
                                                </td>
                                            </s:elseif>
                                            <s:elseif test="status >= 5">
                                                <td><br /><label class="label label-success" style="font-size:9px;">确认完成</label></td>
                                                <td>
                                                    <s:if test="ttos==null">
                                                        <br /><a id="<s:property value="id" />" onclick="$('#comment_id').val($(this).attr('id'))" href="#comment_modal" type="button" class="commentA btn btn-info btn-mini" data-toggle="modal">评论</a>
                                                    </s:if>
                                                    <s:else>
                                                        <br /><a type="button" class="btn btn-info btn-mini disabled" data-toggle="modal">评论</a>
                                                    </s:else>
                                                </td>
                                            </s:elseif>
                                        </tr>
                                    </s:iterator>
                                </tbody>

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
                                                $("#comment_rate" ).bind('rated', function(event, value) {
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
                            </table>

                            <div class="pagination pagination-mini pull-right">
                                <ul>
                                    <li><a href="#">Prev</a></li>
                                    <li><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">Next</a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='comment_area'>
                            <table class="table table-hover table-striped">
                                <tbody>
                                    <s:if test="student.billList.size()<=0">
                                        还没有评论哦~！
                                    </s:if>
                                    <s:else>
                                        <s:iterator value="student.billList" id="billList">
                                            <s:if test="ttos != null">
                                                <tr>
                                                    <td>
                                                        <blockquote>                                                        
                                                            <h4><s:property value="teacher.name" /><label class="label label-important pull-right">评分:<s:property value="ttos.score" /></label></h4>
                                                            <small>
                                                                <span><s:property value="ttos.content" /></span>
                                                                <span class="pull-right">
                                                                    <s:if test="ttos.reply==null">
                                                                        <a href="#reply_<s:property value="id" />" type="button" class="btn btn-info btn-mini" data-toggle="modal">回复</a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a type="button" class="btn btn-info btn-mini disabled" data-toggle="modal">回复</a>
                                                                    </s:else>
                                                                </span>
                                                                <br/><br/>
                                                                <span>您的回复：<s:property value="ttos.reply" /></span>
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
                                                        </blockquote>
                                                    </td>
                                                </tr>
                                            </s:if>
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
                                    <s:form action="file!upload.action" id="fileUpload" enctype="multipart/form-data" method="post">
                                        <s:select cssClass="span2" id="classify" name="dest" list="student.fileGroups" listValue="groupName" listKey="groupName"/>
                                        <script>
                                            $('#classify').append("<option value='新建分组'>新建分组</option>");
                                        </script>
                                        <s:file name="upload" title="上传" id="fileid" onchange="$('#fileUpload').submit();"/>
                                    </s:form>
                                </div>
                            </div>

                            <div id="filelist">
                            </div>
                            <div class="modal hide fade" id="movefileModal">
                                <div class="modal-body">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <s:form action="" cssStyle="margin:0px;">
                                        <dl class="dl-horizontal">
                                            <dt style="font-size: 18px; margin-top: 4px;">文件组:</dt>
                                            <dd>
                                                <div class="input-append">
                                                    <s:select cssClass="span2" id="classify" name="dest" list="student.fileGroups" listValue="groupName" listKey="groupName"/>
                                                    <s:submit id="add" cssClass="btn" value="add" method="addLesson" cssStyle="margin-left:5px;"/>
                                                </div>
                                            </dd>
                                        </dl>
                                    </s:form>
                                </div>
                            </div>
                        </div>
                        <div id="publicfile_area" class="tab-pane fade">
                            <div style="display:inline;">
                                <input type="text" class="span2">
                                <button class="btn btn-primary" style="margin-top:-10px;">搜索</button>
                            </div>

                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th width="300px">
                                            文件名
                                        </th>
                                        <th>
                                            上传日期
                                        </th>
                                        <th>
                                            上传用户
                                        </th>
                                        <th>
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <i class="icon-file"></i>我是pdf.pdf
                                        </td>
                                        <td>2013-1-1</td>
                                        <td>张三</td>
                                        <td class="btn-toolbar">
                                            <div class="btn-group">
                                                <div class="dropdown">
                                                    <a class="btn btn-mini" data-toggle="modal" data-target="#publicfileModal"><i class="icon-tag"></i>收藏</a>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="modal hide fade" id="publicfileModal">
                                <div class="modal-body">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                    <s:form action="" cssStyle="margin:0px;">
                                        <dl class="dl-horizontal">
                                            <dt style="font-size: 18px; margin-top: 4px;">文件组:</dt>
                                            <dd>
                                                <div class="input-append">
                                                    <s:select cssClass="span2" id="classify" name="dest" list="student.fileGroups" listValue="groupName" listKey="groupName"/>
                                                    <s:submit id="add" cssClass="btn" value="add" method="addLesson" cssStyle="margin-left:5px;"/>
                                                </div>
                                            </dd>
                                        </dl>
                                    </s:form>
                                </div>
                            </div>
                            <div class="pagination pagination-mini pull-right">
                                <ul>
                                    <li><a href="#">Prev</a></li>
                                    <li><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">Next</a></li>
                                </ul>
                            </div>

                        </div>
                        <div>
                            <s:if test="tab=='publicfile'">
                                <script>
                                    $("#publicfile_area").addClass("active in");
                                    $("#l6").addClass("active")
                                </script>
                            </s:if>
                            <s:elseif test="tab=='file'">
                                <script>
                                    $("#file_area").addClass("active in");
                                    $("#l5").addClass("active")
                                </script>
                            </s:elseif>
                            <s:elseif test="tab=='comment'">
                                <script>
                                    $("#comment_area").addClass("active in");
                                    $("#l4").addClass("active")
                                </script>
                            </s:elseif>
                            <s:else>
                                <script>
                                    $("#bill_area").addClass("active in");
                                    $("#l3").addClass("active")
                                </script>
                            </s:else>
                        </div>
                    </div>
                </div>
            </div>         
        </div>

        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
