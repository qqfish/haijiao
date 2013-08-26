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
            };
        </script>
        <script type="text/javascript" src="js/jquery.rateit.min.js"></script>
        <script type="text/javascript" src="js/reserve.js"></script>
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
        <link rel="stylesheet" href="css/teaLevel.css" type="text/css">
        <link rel="stylesheet" href="css/teachersResult.css" type="text/css">
        <div class="wrapper container">
            <div class="span11">
                <div class="span11 module">
                    <div id="collapseOne" style="padding: 16px;">
                        <button type="button" class="btn btn-small btn-danger disabled">年级</button>
                        <small class="pull-right"><i class="icon-exclamation-sign"></i>网络授课建议选同网用户</small>
                        <div id="grade" class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                            <button id="allGrade" type="button" value="0" class="btn btn-small active" data-toggle="button">不限</button>
                            <button id="pschool" type="button" value="1" class="btn btn-small" data-toggle="button">小学</button>
                            <button id="mschool" type="button" value="2" class="btn btn-small" data-toggle="button">初中</button>
                            <button id="hschool" type="button" value="8" class="btn btn-small" data-toggle="button">高中</button>
                            <button id="english" type="button" value="8" class="btn btn-small" data-toggle="button">外语</button>
                            <button id="graduate" type="button" value="8" class="btn btn-small" data-toggle="button">考研</button>
                        </div>
                        <br/>
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
                            <button type="button" value="30" class="btn btn-small graduate" data-toggle="button" style="display: none;">考研英语</button>
                            <button type="button" value="31" class="btn btn-small graduate" data-toggle="button" style="display: none;">考研数学</button>
                            <button type="button" value="32" class="btn btn-small graduate" data-toggle="button" style="display: none;">考研政治</button>
                            <button type="button" value="33" class="btn btn-small graduate" data-toggle="button" style="display: none;">考研专业课</button>
                        </div>
                        <p></p>
                        <button type="button" class="btn btn-small btn-danger disabled">方式</button>
                        <div id="sprt" class="btn-group" data-toggle="buttons-radio">
                            <button type="button" value="0" class="btn btn-small active" data-toggle="button">不限</button>
                            <button type="button" value="1" class="btn btn-small" data-toggle="button">网络授课</button>
                            <button type="button" value="2" class="btn btn-small" data-toggle="button">老师上门</button>
                            <button type="button" value="3" class="btn btn-small" data-toggle="button">学生上门</button>
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
                        <br/>
                        <s:if test="@com.haijiao.global.config@domain == 0">
                            <button type="button" style="margin-top: -10px;" class="btn btn-small btn-danger disabled">地区</button>
                            <div id="area" class="btn-group" style="margin-top: -2px;" data-toggle-name="is_private" data-toggle="buttons-radio">
                                <s:select id="selProvince" cssClass="span2 choosetext" cssStyle="margin-top:4px;" name="province" list="{}" value="%{tea.province}"></s:select>
                                <s:select id="selCity" cssClass="span2 choosetext" cssStyle="margin-top:4px;" name="city" list="{}" value="%{tea.city}"></s:select>
                                <s:select id="selDistrict" cssClass="span2 choosetext" cssStyle="margin-top:4px;" name="district" list="{}" value="%{tea.district}"></s:select>
                            </div>
                        </s:if>
                        <br/>
                    </div>
                </div>
                <div  id="resultPanel" class="span11 module">
                    <div style="margin:0px 12px;">
                        <h3>
                            需求列表
                            <small>
                                <div class="btn-toolbar pull-right">
                                    <div id="sort" class="btn-group" data-toggle="buttons-radio">
                                        <button class="btn btn-small btn-inverse" id="normal_button">时间<i class="icon-arrow-down icon-white"></i></button>
                                        <button class="btn btn-small btn-inverse" id="price_button">价格<i class="icon-arrow-down"></i></button>
                                    </div>
                                </div>
                            </small>
                        </h3>
                    </div>
                    <hr style="margin:12px 12px 0px 12px;"/>
                    <div id="resultdetail">
                        <div class="row-fluid" style="margin-top: 10px; margin-left: 10px;">
                            <ul class="thumbnails">
                                <div id="resultBar">
                                    <li class="span11">
                                        <div class="thumbnail">
                                            <label class="label pull-right">100元 / 5课时</label>
                                            <label class="label label-success pull-right" style="margin-right: 5px;">已有9人接受需求</label>
                                            <dl class="dl-horizontal" style="margin:0;">
                                                <dt class="lead" style="width:90px;margin:0;">张同学</dt>
                                                <dd class="muted" style="margin-left:110px;margin-top: 10px;" id="">上海市 - 上海市 - 闵行区，2013-3-14 12:00:00</dd>
                                            </dl>
                                            <dl class="dl-horizontal" style="margin:0;">
                                                <dt class="muted" style="width:90px;">需求描述</dt>
                                                <dd class="" style="margin-left:110px;" id="">啦啦啦啦啦</dd>
                                            </dl>
                                            <dl class="dl-horizontal" style="margin:0;">
                                                <dt class="muted" style="width:90px;">家教地址</dt>
                                                <dd class="" style="margin-left:110px;" id="">123123123</dd>
                                            </dl>
                                            <dl class="dl-horizontal" style="margin:0;">
                                                <dt class="muted" style="width:90px;">课程</dt>
                                                <dd class="" style="margin-left:110px;" id=""> 
                                                    <small>数学</small>
                                                    <small>语文</small>
                                                    <small>英语</small>
                                                </dd>
                                            </dl>
                                            <div class="pull-right">
                                                <button class="btn btn-mini btn-danger">接受需求</button>
                                            </div>
                                            <dl class="dl-horizontal" style="margin:0;">
                                                <dt class="muted" style="width:90px;">授课方式</dt>
                                                <dd class="" style="margin-left:110px;" id="">
                                                    <span data-toggle-name="is_private" data-toggle="buttons-radio">
                                                        <button type="button" class="btn btn-mini btn-choice">线上授课</button>
                                                        <button type="button" class="btn btn-mini btn-choice">学生上门</button>
                                                        <button type="button" class="btn btn-mini btn-choice">老师上门</button>
                                                    </span>
                                                </dd>
                                            </dl>
                                        </div>
                                    </li>
                                </div>
                            </ul>
                        </div>
                        <div class="pagination pagination-right">
                            <ul>        
                                <s:if test="pb.totalPage ==0"></s:if>
                                <s:elseif test="pb.currentPage == 1">
                                    <li class="disabled"><a href="javascript:;">Prev</a></li>
                                </s:elseif>
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
                                <s:elseif test="pb.totalPage == 0"></s:elseif>
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
