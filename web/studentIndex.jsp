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
        <title><s:property value="student.name"/>的主页</title>
        <meta charset="utf-8">
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/index.js"></script>
        <script type="text/javascript" src="js/jquery.rateit.min.js"></script>
        <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script>
        <script type="text/javascript" src="js/studentIndex.js"></script>
        <script type="text/javascript" src="js/fileUpload.js"></script>
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/rateit.css" type="text/css">
        <link rel="stylesheet" href="css/jquery.autocomplete.css">
        <link rel="stylesheet" href="css/validate.css" type="text/css">

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
                        <li id="l7"><a href="#require_area" data-toggle="tab">发布需求</a></li>
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
                            <div id="newgroup" style="display:none;">
                                <hr/>
                                <s:form action="file">
                                    <s:textfield id="newGroupName" name="dest"/>
                                    <s:submit id="newGroup" cssStyle="display:none;" value="新建" method="create"/>
                                    <span class="btn btn-primary" onclick="testAddGroup();">新建</span>
                                    <button class="btn" onclick="$('#userfile').first('button').click();">取消</button>
                                    <div id="newGroup_tip" class="validateTip" style="text-align:left;"></div>
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

                        <div class="tab-pane fade" id="require_area">
                            <div class="pull-right">
                                <button class="btn btn-mini btn-inverse">取消需求</button>
                                <s:if test="student.demand.publish">
                                    <s:if test="!student.demand.bills.isEmpty()">
                                        <button class="btn btn-mini btn-danger disable">更改需求</button>
                                    </s:if>
                                    <s:else>
                                        <button class="btn btn-mini btn-danger" data-toggle="modal" data-target="#requireModal">更改需求</button>
                                    </s:else>
                                </s:if>
                                <s:else>    
                                    <button class="btn btn-mini btn-danger" data-toggle="modal" data-target="#requireModal">发布需求</button>
                                </s:else>
                            </div>
                            <dl class="dl-horizontal" style="margin:0;">
                                <dt class="lead" style="width:90px;margin:0;">当前需求</dt>
                                <dd class="muted" style="margin-left:110px;margin-top: 10px;" id=""></dd>
                            </dl>
                            <hr />
                            <s:if test="student.demand.publish">
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="muted" style="width:90px;">发布时间</dt>
                                    <dd style="margin-left:110px;"><s:date name="student.demand.publishTime" format="MM/dd/yy hh:mm:ss"/></dd>
                                </dl>
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="muted" style="width:90px;">价格</dt>
                                    <dd style="margin-left:110px;"><s:property value="student.demand.total"/>元 / <s:property value="student.demand.duration"/>课时</dd>
                                </dl>
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="muted" style="width:90px;">需求描述</dt>
                                    <dd style="margin-left:110px;"><s:property value="student.demand.demand"/></dd>
                                </dl>
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="muted" style="width:90px;">家教地址</dt>
                                    <dd style="margin-left:110px;"><s:property value="student.demand.address"/></dd>
                                </dl>
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="muted" style="width:90px;">课程</dt>
                                    <dd style="margin-left:110px;"> 
                                        <small><s:property value="student.demand.lesson"/></small>
                                    </dd>
                                </dl>
                                <dl class="dl-horizontal" style="margin:0;">
                                    <dt class="muted" style="width:90px;">授课方式</dt>
                                    <dd style="margin-left:110px;">
                                        <s:if test="student.sprtOnline">
                                            线上授课
                                        </s:if>
                                        <s:if test="student.sprtSUnderline">
                                            学生上门
                                        </s:if>
                                        <s:if test="student.sprtTUnderline">
                                            老师上门
                                        </s:if>
                                    </dd>
                                </dl>
                            </s:if>
                            <s:else>
                                还没有发布需求哦~！
                            </s:else>
                            <hr />
                            <h4>抢单老师</h4>
                            <s:if test="student.demand.bills.isEmpty()">
                                暂无抢单老师
                            </s:if>
                            <s:else>
                                <s:iterator value="student.demand.bills">
                                    <div class="thumbnail" style="line-height: 15px">
                                        <img class="pull-left" style="margin: 0px 10px 0px 0px;width: 110px;height: 110px;" src="<s:property value="teacher.pic.content"/>"/>
                                        <a href="${teacher.id}" style="text-decoration: none;">
                                            <b style="font-size: 20px;"><s:property value="teacher.getSecretName()"/>&nbsp;</b>
                                        </a>
                                        <s:if test="teacher.level==1">
                                            <i class="icon-diomand"></i>
                                        </s:if>
                                        <s:else>
                                            <i class="icon-"></i>
                                        </s:else>
                                        <s:if test="teacher.status==0"><label class="label">离线</label></s:if>
                                        <s:elseif test="teacher.status==1"><label class="label label-success">在线</label></s:elseif>
                                        <s:else><label class="label label-warning">忙碌</label></s:else>
                                        <small class="muted" style="margin-bottom: 5px;">&nbsp;&nbsp;上次登陆时间<s:date name="teacher.lastActiveDate" nice="true"/></small>
                                        <label class="label label-info pull-right"><s:property value="teacher.reserveNum"/>人预约</label>
                                        <br/>
                                        <div class="resultInfo" >
                                            <div class="rateit pull-right" data-rateit-value="<s:property value="teacher.score" default="0" />" data-rateit-ispreset="true" data-rateit-readonly="true"></div>
                                            <p><small><s:property value="teacher.school" default="暂无大学"/> | <s:property value="teacher.major" default="暂无专业"/></small></p>
                                            <p><small>生源地：<s:property value="teacher.origin" default="暂无生源地"/></small></p>
                                            <p><small>上课方式：<s:property value="message"/></small></p>
                                            <a class="btn btn-mini pull-right">接受</a>
                                            <p><small>身份：<s:if test="teacher.studyStatus == null">无</s:if><s:else><s:property value="teacher.studyStatus"/></s:else></small></p>
                                            </div>
                                        </div>
                                </s:iterator>
                            </s:else>
                            <div id="requireModal" class="modal fade hide">
                                <s:form action="dealDemand">
                                    <div class="modal-header">
                                        <s:if test="student.demand.publish">
                                            <h4>需求修改</h4>
                                        </s:if>
                                        <s:else>
                                            <h4>需求发布</h4>
                                        </s:else>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-horizontal">
                                            <div class="control-group">
                                                <label class="control-label" for="demand"><strong>需求描述</strong></label>
                                                <div class="controls">
                                                    <s:textarea id="demand" name="demand" cssClass="span4" type="text" placeholder="请输入需求的具体要求" value="%{student.demand.demand}" autofocus="autofocus" />
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="home"><strong>家庭地址</strong></label>
                                                <div class="controls">
                                                    <s:textfield id="home" name="address" cssClass="span4" type="text" placeholder="请输入您的家庭地址" value="%{student.demand.address}"/>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="way"><strong>授课方式</strong></label>
                                                <div class="controls">
                                                    <s:checkbox name="sprtOnline" value="%{tea.sprtOnline}" cssStyle="margin-top:-5px"/> 线上授课
                                                    <s:checkbox name="sprtTUnderline" value="%{tea.sprtTUnderline}" cssStyle="margin-top:-5px"/> 老师上门
                                                    <s:checkbox name="sprtSUnderline" value="%{tea.sprtSUnderline}" cssStyle="margin-top:-5px"/> 学生上门
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="lessonSelect"><strong>课程</strong></label>
                                                <div class="controls">
                                                    <s:textfield id="lessonSelect" name="lesson" cssClass="span4" placeholder="请输入课程名称" value="%{student.demand.lesson}"/>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="price"><strong>课程单价</strong></label>
                                                <div class="controls">
                                                    <div class="input-append">
                                                        <s:if test="student.demand.duration == 0">
                                                            <s:textfield placeholder="课程价格" id="price" name="price" value="%{student.demand.total / student.demand.duration}" onchange="checkNatureNumber($(this));"/>
                                                        </s:if>
                                                        <s:else>
                                                            <s:textfield placeholder="课程价格" id="price" name="price" value="%{student.demand.total / student.demand.duration}" onchange="checkNatureNumber($(this));"/>
                                                        </s:else>
                                                        <span class="add-on">.00元/45分钟</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="datepicker"><strong>课时数</strong></label>
                                                <div class="controls">
                                                    <input id="num" type="number" class="span1" min="1" max="20" step="1" value="%{student.demand.duration}" onchange="$('#reserveNum').val(this.value);">
                                                    <s:textfield id="reserveNum" name="duration" value="1" cssStyle="display:none;"/>
                                                </div>
                                            </div>           
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <div class="control-group">
                                            <div class="controls">
                                                <s:if test="student.demand.publish">
                                                    <s:submit method="changeDemand" cssClass="btn btn-primary pull-right" value="修改需求"/>
                                                </s:if>
                                                <s:else>
                                                    <s:submit method="publishDemand" cssClass="btn btn-primary pull-right" value="发布需求"/>
                                                </s:else>
                                            </div>
                                        </div>
                                    </div>
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
