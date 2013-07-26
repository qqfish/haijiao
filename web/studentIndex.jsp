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
        <script type="text/javascript" src="js/teachers.js"></script>
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
                        <li id="l3" class="active"><a href="#bill_area" data-toggle="tab" >交易记录</a></li>
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
                                    <s:iterator value="billList" id="billList">
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
                                            <s:elseif test="status == 5">
                                                <td><br /><label class="label label-success" style="font-size:9px;">确认完成</label></td>
                                                <td>
                                                    <br /><a class="btn btn-mini btn-link">举报</a>
                                                </td>
                                            </s:elseif>

                                            <s:if test="status >= 4">
                                                <td>
                                                    <s:if test="ttos==null">
                                                        <a id="<s:property value="id" />" onclick="$('#comment_id').val($(this).attr('id'))" href="#comment_modal" type="button" class="commentA btn btn-info btn-mini" data-toggle="modal">评论</a>
                                                    </s:if>
                                                    <s:else>
                                                        <a type="button" class="btn btn-info btn-mini disabled" data-toggle="modal">评论</a>
                                                    </s:else>
                                                </td>
                                            </s:if>
                                        </tr>
                                    </s:iterator>
                                </tbody>
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

                            <!--                                
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
                                    </tr>
                                </thead>
                                <tbody>        
                                <s:iterator value="billList" id="billList">
                                    <tr>
                                        <td><s:property value="teacher.name" /></td>
                                        <td><s:property value="lesson.name" /></td>
                                        <td><s:property value="getRealMoney('student')" /></td>
                                        <td><s:property value="createdateToString()" /></td>
                                        <td><s:property value="message" /></td>
                                        <td>
                                    <s:if test="stot==null">
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
                                        <s:textarea name="content" autofocus="autofocus" id="content"></s:textarea>
                                            <br/>
                                            评分<div id="rate_<s:property value="id" />" class="rateit" data-rateit-step="1" data-rateit-ispreset="true"></div>
                                        <s:textfield id="score_%{id}" name="score" cssStyle="display:none;"></s:textfield>
                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
                                        <s:submit cssClass="btn btn-primary" method="comment" value="提交" ></s:submit>
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
                                            })
                                        </script>
                                    </s:form>
                                </div>
                                </s:iterator>
                                </tbody>
                            </s:else>
                        </table>
                            -->

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
                                                        <s:if test="ttos != null">
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
                                                        </s:if>
                                                    </blockquote>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                    </s:else>
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
