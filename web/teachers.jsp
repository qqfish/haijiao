<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Teachers</title>
        <meta charset="utf-8">
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/teachers.js"></script>
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
    <body style="background: url(images/background.jpg);">

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="wrapper container">
            <div class="span11">
                <div class="span11 module">
                    <div id="collapseOne" style="padding: 16px;">
                        <button type="button" class="btn btn-danger disabled">年级</button>
                        <div id="grade" class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button id="prischool" type="button" value="0" class="btn grade active" data-toggle="button">不限</button>
                            <button id="prischool" type="button" value="1" class="btn grade" data-toggle="button">小学</button>
                            <button id="grade6" type="button" value="2" class="btn grade" data-toggle="button">六年级</button>
                            <button id="grade7" type="button" value="3" class="btn grade" data-toggle="button">初一</button>
                            <button id="grade8" type="button" value="4" class="btn grade" data-toggle="button">初二</button>
                            <button id="grade9" type="button" value="5" class="btn grade" data-toggle="button">初三</button>
                            <button id="grade10" type="button" value="6" class="btn grade" data-toggle="button">高一</button>
                            <button id="grade11" type="button" value="7" class="btn grade" data-toggle="button">高二</button>
                            <button id="grade12" type="button" value="8" class="btn grade" data-toggle="button">高三</button>
                        </div>
                        <p></p>
                        <button type="button" class="btn btn-danger disabled">学科</button>
                        <div id="lesson" class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button id="chinese" type="button" value="0" class="lesson btn active" data-toggle="button">不限</button>
                            <button id="chinese" type="button" value="1" class="lesson btn " data-toggle="button">语文</button>
                            <button id="math" type="button" value="2" class="btn lesson" data-toggle="button">数学</button>
                            <button id="english" type="button" value="3" class="btn lesson" data-toggle="button">英语</button>
                            <button id="physics" type="button" value="4" class="btn lesson" data-toggle="button">物理</button>
                            <button id="chem" type="button" value="5" class="btn lesson" data-toggle="button">化学</button>
                            <button id="bio" type="button" value="6" class="btn lesson" data-toggle="button">生物</button>
                            <button id="poli" type="button" value="7" class="btn lesson" data-toggle="button">政治</button>
                            <button id="history" type="button" value="8" class="btn lesson" data-toggle="button">历史</button>
                            <button id="geo" type="button" value="9" class="btn lesson" data-toggle="button">地理</button>
                        </div>
                        <p></p>
                        <button type="button" class="btn btn-danger disabled">网络</button>
                        <div id="net" class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button type="button" value="0" class="btn active" data-toggle="button">不限</button>
                            <button type="button" value="1" class="btn" data-toggle="button">电信</button>
                            <button type="button" value="2" class="btn" data-toggle="button">网通</button>
                            <button type="button" value="3" class="btn" data-toggle="button">教育网</button>
                        </div> 
                        <p></p>
                        <button type="button" class="btn btn-danger disabled">性别</button>
                        <div id="sex" class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button type="button" value="0" class="btn active" data-toggle="button">不限</button>
                            <button type="button" value="1" class="btn" data-toggle="button">男</button>
                            <button type="button" value="2" class="btn" data-toggle="button">女</button>
                        </div> 
                        <p></p>
                    </div>
                </div>
                <div  id="resultPanel" class="span11 module">
                    <div style="margin:0px 12px;">
                        <h3>
                            老师列表
                            <s:select id="area" cssClass="span2 choosetext" list="{'请选择家教地区','北京市', '上海市', '天津市', '重庆市', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新壃', '香港', '澳门', '台湾'}"/>
                            <small>
                                <div class="btn-toolbar pull-right">
                                    <div class="btn-group" data-toggle="buttons-radio">
                                        <button class="btn btn-small btn-inverse" id="normal_button">默认<i class="icon-arrow-down icon-white"></i></button>
                                        <button class="btn btn-small btn-inverse" id="score_button">评分<i class="icon-arrow-down"></i></button>
                                        <button class="btn btn-small btn-inverse" id="price_button">价格<i class="icon-arrow-down"></i></button>
                                        <button class="btn btn-small btn-inverse" id="hot_button">人气<i class="icon-arrow-down"></i></button>
                                        <button class="btn btn-small btn-inverse" id="time_button">最后活跃时间<i class="icon-arrow-down"></i></button>
                                    </div>
                                    <button class="btn btn-small btn-danger" id="online_button">仅显示在线</button>
                                </div>
                            </small>
                        </h3>
                    </div>
                    <hr style="margin:12px 12px 0px 12px;"/>
                    <div id="resultdetail">
                        <div class="row-fluid" style="margin: 0px 28px;">
                            <ul class="thumbnails">
                                <s:iterator value="pb.list" id="list">
                                    <s:a action="getTeacherInfo.action" id="resultBar">
                                        <s:param name="teacherEmail"><s:property value="email"/></s:param>
                                        <li class="span3">
                                            <div class="thumbnail">
                                                <img src="<s:property value="picUrl"/>" width="100%" alt="">
                                                <div class="caption">
                                                    <h4><s:property value="name"/><small>
                                                            <s:if test="status==0"><label class="label">离线</label></s:if>
                                                            <s:elseif test="status==1"><label class="label label-success">在线</label></s:elseif>
                                                            <s:else><label class="label label-warning">忙碌</label></s:else></small>
                                                        <label class="label pull-right"><s:property value="wagePerhour"/>元/时</label>
                                                    </h4>
                                                    <p style="margin:0;"></p>
                                                    <label class="label label-important">评分：<s:property value="score"/></label>
                                                    <label class="label label-important"><s:property value="reserveNum"/>人预约</label>
                                                    <p style="margin:0;"></p> 
                                                    <s:if test="lessons.size()==0">
                                                        <label class="label label-info" >该老师还没有开课哦</label>
                                                    </s:if>
                                                    <s:else>
                                                        <label class="label label-info" >课程:</label>
                                                    </s:else>
                                                    <s:iterator value="lessons" status="st">
                                                        <s:if test="delete==false">
                                                            <label class="label label-info" ><s:property value="name"/></label>
                                                        </s:if>
                                                    </s:iterator>
                                                    <p style="margin:0;"></p>
                                                    <label><p class="muted pull-right" style="margin-bottom: 5px;"><small>上次登陆时间<s:property value="lastActiveDate" default="null"/></small></p></label>
                                                </div>
                                            </div>
                                        </li>
                                    </s:a>
                                </s:iterator>
                            </ul>
                        </div>
                        <div class="pagination pagination-right">
                            <ul>        
                                <s:if test="pb.currentPage == 1">
                                    <li class="disabled"><a href="javascript:;">Prev</a></li>
                                </s:if>
                                <s:else>
                                    <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage -1});">
                                            Prev</s:a></li>
                                    </s:else>
                                    <s:if test="pb.totalPage > 7">
                                        <s:if test="pb.currentPage < 5">
                                            <s:iterator value="new int[pb.currentPage +1]" status="i">
                                                <s:if test="pb.currentPage == #i.index+1">
                                                <li class="disabled"><a href="javascript:;"><s:property value="#i.index+1"/></a></li>
                                            </s:if>
                                            <s:else>
                                                <li><s:a href="javascript:;" onclick="gotopage(%{#i.index +1});">
                                                        <s:property value="#i.index+1"/>
                                                    </s:a></li>
                                                </s:else>
                                            </s:iterator>
                                        </s:if>
                                        <s:elseif test="pb.currentPage > totalPage - 5">
                                        <li><s:a href="javascript:;" onclick="gotopage(1);">1</s:a></li>
                                        <li><s:a href="javascript:;" onclick="gotopage(2);">2</s:a></li>
                                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                                        <s:iterator  value="new int[pb.totalPage - pb.currentPage +1]" status="i">
                                            <s:if test="#i.index == 1">
                                                <li class="disabled"><s:a href="javascript:;">
                                                        <s:property value="pb.currentPage"/>
                                                    </s:a></li>
                                                </s:if>
                                                <s:else>
                                                <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage + #i.index - 1});">
                                                        <s:property value="pb.currentPage + #i.index -1"/>
                                                    </s:a></li>
                                                </s:else>
                                            </s:iterator>
                                        </s:elseif>
                                        <s:else>
                                        <li><s:a href="javascript:;" onclick="gotopage(1);">1</s:a></li>
                                        <li><s:a href="javascript:;" onclick="gotopage(2);">2</s:a></li>
                                        <li class="disabled"><s:a href="javascript:;">...</s:a></li>
                                        <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage - 1});">
                                                <s:property value="pb.currentPage -1"/>
                                            </s:a></li>
                                        <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage});">
                                                <s:property value="pb.currentPage"/>
                                            </s:a></li>
                                        <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage + 1});">
                                                <s:property value="pb.currentPage +1"/>
                                            </s:a></li>
                                        <li><s:a href="javascript:;">...</s:a></li>
                                        <li><s:a href="javascript:;" onclick="gotopage(%{pb.totalPage - 1});">
                                                <s:property value="pb.totalPage -1"/>
                                            </s:a></li>
                                        <li><s:a href="javascript:;" onclick="gotopage(%{pb.totalpage});">
                                                <s:property value="pb.totalPage"/>
                                            </s:a></li>
                                        </s:else>
                                    </s:if>
                                    <s:else>
                                        <s:iterator value="new int[pb.totalPage]" status="i">
                                            <s:if test="pb.currentPage == #i.index+1">
                                            <li class="disabled"><a href="javascript:;"><s:property value="#i.index+1"/></a></li>
                                        </s:if>
                                        <s:else>
                                            <li><s:a href="javascript:;" onclick="gotopage(%{#i.index +1});">
                                                    <s:property value="#i.index+1"/>
                                                </s:a></li>
                                            </s:else>
                                        </s:iterator>
                                    </s:else>
                                    <s:if test="pb.currentPage == pb.totalPage">
                                    <li class="disabled"><a href="javascript:;">Next</a></li>
                                </s:if>
                                <s:else>
                                    <li><s:a href="javascript:;" onclick="gotopage(%{pb.currentPage +1});">
                                            Next
                                        </s:a></li>
                                    </s:else>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>  

        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
