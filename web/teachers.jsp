<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Teachers</title>
        <meta charset="utf-8">
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/teachers.js"></script>
        <script type="text/javascript" src="js/my.ling.chinaArea.sort-1.0.js"></script>
        <script type="text/javascript">
            window.onload = function() {
                $ling.chinaArea.init("selProvince", "selCity", "selDistrict");
            }
        </script>
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
    <body>

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="wrapper container">
            <div class="span11">
                <div class="span11 module">
                    <div id="collapseOne" style="padding: 16px;">
                        <button type="button" class="btn btn-small btn-danger disabled">年级</button>
                        <div id="grade" class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button id="allGrade" type="button" value="0" class="btn btn-small active" data-toggle="button">不限</button>
                            <button id="pschool" type="button" value="1" class="btn btn-small" data-toggle="button">小学</button>
                            <button id="mschool" type="button" value="2" class="btn btn-small" data-toggle="button">初中</button>
                            <button id="hschool" type="button" value="8" class="btn btn-small" data-toggle="button">高中</button>
                            <button id="english" type="button" value="8" class="btn btn-small" data-toggle="button">英语</button>
                        </div><br/>
                        <div id="lesson" class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio" style="border:1px; border-color: #CCC; border-style: solid;  padding: 5px 10px 5px 10px; margin-top: 7px; margin-left: 50px; border-radius:9px; display: none;">
                            <button id="allLesson" type="button" value="0" class="btn btn-small active" data-toggle="button">不限</button>
                            <button type="button" value="1" class="btn btn-small pschool" data-toggle="button" style="display: none;">小学语文</button>
                            <button type="button" value="2" class="btn btn-small pschool" data-toggle="button" style="display: none;">小学数学</button>
                            <button type="button" value="3" class="btn btn-small pschool" data-toggle="button" style="display: none;">小学英语</button>
                            <button type="button" value="4" class="btn btn-small pschool" data-toggle="button" style="display: none;">小学奥数</button>
                            <button type="button" value="5" class="btn btn-small mschool" data-toggle="button" style="display: none;">初一初二语文</button>
                            <button type="button" value="6" class="btn btn-small mschool" data-toggle="button" style="display: none;">初一初二数学</button>
                            <button type="button" value="7" class="btn btn-small mschool" data-toggle="button" style="display: none;">初一初二英语</button>
                            <button type="button" value="8" class="btn btn-small mschool" data-toggle="button" style="display: none;">初三语文</button><span class="mschool"style="display: none;"><br/></span>
                            <button type="button" value="9" class="btn btn-small mschool" data-toggle="button" style="display: none;">初三数学</button>
                            <button type="button" value="10" class="btn btn-small mschool" data-toggle="button" style="display: none;">初三英语</button>
                            <button type="button" value="11" class="btn btn-small mschool" data-toggle="button" style="display: none;">初中物理</button>
                            <button type="button" value="12" class="btn btn-small mschool" data-toggle="button" style="display: none;">初中化学</button>
                            <button type="button" value="13" class="btn btn-small mschool" data-toggle="button" style="display: none;">初中历史</button>
                            <button type="button" value="14" class="btn btn-small mschool" data-toggle="button" style="display: none;">初中地理</button>
                            <button type="button" value="15" class="btn btn-small hschool" data-toggle="button" style="display: none;">高一高二语文</button>
                            <button type="button" value="16" class="btn btn-small hschool" data-toggle="button" style="display: none;">高一高二数学</button>
                            <button type="button" value="17" class="btn btn-small hschool" data-toggle="button" style="display: none;">高一高二英语</button>
                            <button type="button" value="18" class="btn btn-small hschool" data-toggle="button" style="display: none;">高三语文</button>
                            <button type="button" value="19" class="btn btn-small hschool" data-toggle="button" style="display: none;">高三数学</button>
                            <button type="button" value="20" class="btn btn-small hschool" data-toggle="button" style="display: none;">高三英语</button><span class="hschool"style="display: none;"><br/></span>
                            <button type="button" value="21" class="btn btn-small hschool" data-toggle="button" style="display: none;">高中物理</button>
                            <button type="button" value="22" class="btn btn-small hschool" data-toggle="button" style="display: none;">高中化学</button>
                            <button type="button" value="23" class="btn btn-small hschool" data-toggle="button" style="display: none;">高中生物</button>
                            <button type="button" value="24" class="btn btn-small hschool" data-toggle="button" style="display: none;">高中历史</button>
                            <button type="button" value="25" class="btn btn-small hschool" data-toggle="button" style="display: none;">高中政治</button>
                            <button type="button" value="26" class="btn btn-small hschool" data-toggle="button" style="display: none;">高中地理</button>
                            <button type="button" value="27" class="btn btn-small hschool" data-toggle="button" style="display: none;">高中报考咨询</button>
                            <button type="button" value="28" class="btn btn-small english" data-toggle="button" style="display: none;">大学四级</button>
                            <button type="button" value="29" class="btn btn-small english" data-toggle="button" style="display: none;">大学六级</button>
                            <button type="button" value="30" class="btn btn-small english" data-toggle="button" style="display: none;">托福</button>
                            <button type="button" value="31" class="btn btn-small english" data-toggle="button" style="display: none;">雅思</button>
                            <button type="button" value="32" class="btn btn-small english" data-toggle="button" style="display: none;">GRE</button>
                            <button type="button" value="33" class="btn btn-small english" data-toggle="button" style="display: none;">口语</button>
                        </div>
                        <p></p>
                        <button type="button" class="btn btn-small btn-danger disabled">网络</button>
                        <div id="net" class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button type="button" value="0" class="btn btn-small active" data-toggle="button">不限</button>
                            <button type="button" value="1" class="btn btn-small" data-toggle="button">电信</button>
                            <button type="button" value="2" class="btn btn-small" data-toggle="button">联通(网通)</button>
                            <button type="button" value="3" class="btn btn-small" data-toggle="button">教育网</button>
                            <button type="button" value="4" class="btn btn-small" data-toggle="button">移动(铁通)</button>
                            <button type="button" value="5" class="btn btn-small" data-toggle="button">有线通</button>
                            <button type="button" value="6" class="btn btn-small" data-toggle="button">其他</button>
                        </div> 
                        <p></p>
                        <button type="button" class="btn btn-small btn-danger disabled">性别</button>
                        <div id="sex" class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button type="button" value="0" class="btn btn-small active" data-toggle="button">不限</button>
                            <button type="button" value="1" class="btn btn-small" data-toggle="button">男</button>
                            <button type="button" value="2" class="btn btn-small" data-toggle="button">女</button>
                        </div>
                        <br/>
                        <button type="button" class="btn btn-small btn-danger disabled">身份</button>
                        <s:select cssClass="span2 choosetext" cssStyle="margin-top:10px;" name="studyStatus" list="{'不限','在职教师','大专学生','大一学生','大二学生','大三学生','大四学生','在读硕士','在读博士','海归/外教','其他'}" value="%{tea.studyStatus}"></s:select>
                            <br/>
                            <button type="button" style="margin-top: -10px;" class="btn btn-small btn-danger disabled">地区</button>
                            <div id="area" class="btn-group" style="margin-top: -2px;" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <s:select id="selProvince" cssClass="span2 choosetext" cssStyle="margin-top:4px;" name="province" list="{}" value="%{tea.province}"></s:select>
                            <s:select id="selCity" cssClass="span2 choosetext" cssStyle="margin-top:4px;" name="city" list="{}" value="%{tea.city}"></s:select>
                            <s:select id="selDistrict" cssClass="span2 choosetext" cssStyle="margin-top:4px;" name="district" list="{}" value="%{tea.district}"></s:select>
                            </div>
                            <br/>
                        </div>
                    </div>
                    <div  id="resultPanel" class="span11 module">
                        <div style="margin:0px 12px;">
                            <h3>
                                老师列表
                                <small>
                                    <div class="btn-toolbar pull-right">
                                        <div id="sort" class="btn-group" data-toggle="buttons-radio">
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
                                            <li class="span11" style="margin-top: 15px;">
                                                <div class="thumbnail">
                                                    <img class="pull-left" style="margin: 0px 10px 0px 0px;" src="<s:property value="picUrl"/>" width="110px"/>
                                                <b style="font-size: 20px;"><s:property value="name"/>&nbsp;</b>
                                                <s:if test="status==0"><label class="label">离线</label></s:if>
                                                <s:elseif test="status==1"><label class="label label-success">在线</label></s:elseif>
                                                <s:else><label class="label label-warning">忙碌</label></s:else>
                                                <small class="muted" style="margin-bottom: 5px;">&nbsp;&nbsp;上次登陆时间<s:property value="lastActiveDate" default="null"/></small>
                                                <label class="label pull-right"><s:property value="wagePerhour"/>元/时</label>
                                                <span class="pull-right">&nbsp;</span>
                                                <label class="label label-info pull-right"><s:property value="reserveNum"/>人预约</label>
                                                <br/>
                                                <small style="color:black;" >
                                                    <s:property value="school"/> | <s:property value="major"/>
                                                    <div class="rateit pull-right" data-rateit-value="<s:property value="score" default="0" />" data-rateit-ispreset="true" data-rateit-readonly="true"></div>
                                                    <br/>
                                                    <s:if test="lessons.size()==0">
                                                        开设课程：该老师还没有开课。
                                                    </s:if>
                                                    <s:else>
                                                        开设课程：
                                                        <s:iterator value="lessons" status="st">
                                                            <s:if test="delete==false">
                                                                <s:property value="name"/> | 
                                                            </s:if>
                                                        </s:iterator>
                                                    </s:else>
                                                    <br/>
                                                    <s:if test="#session.email != null&&tea.status!=1">
                                                        <button class="btn btn-danger btn-mini pull-right" style="margin-left:10px" data-toggle="modal" data-target="#publicRoom">在线试讲</button>
                                                        <div class="modal fade hide" id="publicRoom">
                                                            <div class="modal-body">
                                                                <h3>老师不在线或忙碌，可能无法与您交流，仍要进入房间吗？</h3>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <a class="btn btn-success" href="enterPublicRoom.action?teaEmail=<s:property value='tea.email' default='null' />">确定</a>
                                                                <button class="btn" data-dismiss="modal">取消</button>
                                                            </div>
                                                        </div>
                                                    </s:if>
                                                    <s:elseif test="#session.email != null">
                                                        <button class='btn btn-primary btn-mini pull-right' href="enterPublicRoom.action?teaEmail=<s:property value='tea.email' default='null' />">在线试讲</button>
                                                    </s:elseif>
                                                    <s:else>
                                                        <button class="btn btn-danger btn-mini pull-right" style="margin-left:5px" data-toggle="modal" data-target="#publicRoom">在线试讲</button>
                                                        <div class="modal fade hide" id="publicRoom">
                                                            <div class="modal-body">
                                                                <h3>请先登陆</h3>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <a class="btn btn-success" href="index.action">登陆</a>
                                                                <button class="btn" data-dismiss="modal">取消</button>
                                                            </div>
                                                        </div>
                                                    </s:else>
                                                    线下授课区域：<s:property value="underlineArea" default="这个老师还没设置线下授课区域！"/><br/>
                                                    个人经历：<s:property value="experience" default="这个老师很懒，还没写好个人经历！"/><br/>
                                                </small>
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
