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
        <script type="text/javascript" src="js/fileUpload.js"></script>
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
                    <a href="toChangeInfo.action?tab=head"><img width="210px" height="210px" src="<s:property value="student.pic.content"/>" class="img-polaroid"/></a>
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
                        <li id="l3"><a href="#bill_area" data-toggle="tab" >订单处理</a></li>
                        <li id="l5"><a href="#file_area" data-toggle="tab" >我的课件</a></li>
                        <li id="l6" ><a href="#publicfile_area" data-toggle="tab">公共课件</a></li>
                        <li id="l4" ><a href="#comment_area" data-toggle="tab" >评论</a></li>
                    </ul>
                    <div class="tab-content">
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
                                </s:form>
                            </div>
                        </div>
                        <div class="tab-pane fade" id='comment_area'>

                        </div>
                        <div class="tab-pane fade" id='file_area'>
                            <button class="btn btn-mini btn-primary pull-right" data-toggle="button" onclick="$('#newgroup').toggle();">新建分组</button>
                            <button data-toggle="modal" data-target="#uploadmodal" class="btn btn-mini btn-primary pull-right" style="margin-right: 10px;">上传</button>
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
                                        <s:file name="upload" title="上传" id="fileid" onchange="testUpload(this.id);"/>
                                        <div id="upload_tip" class="validateTip"></div>
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
                                                    <s:select cssClass="span2" name="dest" list="student.fileGroups" listValue="groupName" listKey="groupName"/>
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
