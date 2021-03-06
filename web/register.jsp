<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Register</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/datepicker.css">
        <link rel="stylesheet" href="css/validate.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/jquery.autocomplete.css">
        <!--js-->
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/reg.js"></script>
        <script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="js/validate.js"></script>
        <script type="text/javascript" src="js/ling.chinaArea.sort-1.0.js"></script>
        <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script>
        <script type="text/javascript" src="js/schoolSelect.js"></script>

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
            window.onload = function() {
                $ling.chinaArea.init("selProvince", "selCity", "selDistrict");
            }
        </script>

    </head>
    <body>

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="container wrapper">
            <div class="container-fluid">
                <div class="span11 module">
                    <s:form action="registerThen.action">
                        <s:if test="#session.userType == 'teacher'">
                            <h2 class="offset2">老师您好，请填写您的详细信息^ ^</h2>
                            <hr/>
                            <dl class="dl-horizontal offset1">
                                <dt style='margin-top:5px'>姓名</dt>
                                <dd><s:textfield id="TAName" cssClass="span4" onchange="validate_required(this,name_tip); validate_lengthLimit(this, name_tip2, 8);" type="text" name="name" placeholder="请输入您的全名" autofocus="autofocus"/></dd>
                                <div id="name_tip" class="validateTip"></div>
                                <div id="name_tip2" class="validateTip"></div>
                                <dd><small class="text-warning">请填写真实姓名，须与提现银行卡开户名一致</small></dd>
                                <br/>
                                <dt style='margin-top:5px'>手机</dt>
                                <dd><s:textfield id="phoneNum" cssClass="span4" onchange="validate_phoneNum(this,phone_tip);" name="tel" placeholder="请输入您的手机号"/></dd>
                                <dd><small class="text-warning">请填写有效手机号，以便学生与您取得联系</small></dd>
                                <dd><span id="phone_tip" class="validateTip"></span></dd>
                                <br/>
                                <dt style='margin-top:5px'>网络环境</dt>
                                <dd>
                                    <s:select cssClass="span4" name="net" list="{'电信', '联通(网通)', '教育网', '移动(铁通)', '有线通', '其他'}"/>
                                </dd>
                                <dd>
                                    <small class="text-warning">同样网络下视频效果更佳，请选择常用网络环境</small>
                                </dd>
                                <br/>
                                <dt style='margin-top:5px'>性别</dt>
                                <dd style="display:none;"><s:radio list="{'男', '女'}" name="sex"/></dd>
                                <dd>
                                    <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio" style="margin-top:-3px;">
                                        <button id="maleButton" type="button" value="0" class="btn btn-small" data-toggle="button">男</button>
                                        <button id="femaleButton" type="button" value="1" class="btn btn-small" data-toggle="button">女</button>
                                    </div>
                                </dd>
                                <br/>
                                <dt>目前身份</dt>
                                <dd><s:select name="studyStatus" list="{'在职教师','大专学生','大一学生','大二学生','大三学生','大四学生','在读硕士','在读博士','海归/外教','其他'}"></s:select></dd>
                                <dt>所在地</dt>
                                <dd>
                                    <s:select id="selProvince" name="province" list="{ }"></s:select>
                                    <s:select id="selCity" name="city" list="{ }"></s:select>
                                    <s:select id="selDistrict" name="district" list="{ }"></s:select>
                                </dd>
                                <s:if test="@com.haijiao.global.config@domain == 0">
                                <dt>学校信息</dt>
                                <dd><s:textfield id="schoolSelect" cssClass="span4" name="school" placeholder="请输入您就读的大学" /></dd>
                                </s:if>
                                <s:elseif test="@com.haijiao.global.config@domain == 1">
                                    <s:textfield id="schoolSelect" cssStyle="display:none;" name="school" value="上海交通大学"/>
                                </s:elseif>
                                <dt>所学专业</dt>
                                <dd><s:textfield cssClass="span4" type="text" name="major" placeholder="请输入您就读的专业" /></dd>
                                <dt>高中毕业地</dt>
                                <dd><s:textfield cssClass="span4" type="text" name="origin" placeholder="请输入您高中毕业地（生源地）" /></dd>
                                <br/>
                                <br/>
                                <dd>
                                    <input class="btn btn-primary" style="width:300px;" type="button" value="提交" onclick="testSubmit();"/>
                                    <s:submit id="form_submit" cssClass="btn btn-primary" cssStyle="width:300px; display:none;" value="提交" method="teacherRegister"/>
                                </dd>
                            </dl>
                        </s:if>
                        <s:if test="#session.userType == 'student'">
                            <h2 class="offset2">同学您好，修改你的详细信息吧^ ^</h2>
                            <hr/>
                            <dl class="dl-horizontal offset1">
                                <dt style='margin-top:5px'>姓名</dt>
                                <dd><s:textfield id="SAName" cssClass="span4" onchange="validate_required(this,name_tip); validate_lengthLimit(this, name_tip2, 8);" type="text" name="name" placeholder="请输入您的全名" autofocus="autofocus"/></dd>
                                <div id="name_tip" class="validateTip"></div>
                                <div id="name_tip2" class="validateTip"></div>
                                <br/>
                                <dt style='margin-top:5px'>手机</dt>
                                <dd><s:textfield id="phoneNum" cssClass="span4" onchange="validate_phoneNum(this,phone_tip);" name="tel" placeholder="请输入您的手机号"/></dd>
                                <span id="phone_tip" class="validateTip" style="text-align: left;"></span>
                                <dd><small class="text-warning">请填写有效手机号，以便老师与您取得联系</small></dd>
                                <br/>
                                <dd style="display: none;"><s:radio list="{'student', 'parent'}" name="telType"/></dd>
                                <dd>
                                    <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                        <button id="meButton" type="button" value="0" class="btn" data-toggle="button">我的手机</button>
                                        <button id="parentButton" type="button" value="1" class="btn" data-toggle="button">爸妈的手机</button>
                                    </div>
                                </dd>
                                <br/>
                                <dt style='margin-top:5px'>网络环境</dt>
                                <dd>
                                    <s:select cssClass="span4" name="net" list="{'电信', '联通(网通)', '教育网', '移动(铁通)', '有线通', '其他'}"/>
                                </dd>
                                <dd>
                                    <small class="text-warning">同样网络下视频效果更佳，请选择常用网络环境</small>
                                </dd>
                                <br/>
                                <dt style='margin-top:5px'>性别</dt>
                                <dd style="display:none;"><s:radio list="{'男', '女'}" name="sex"/></dd>
                                <dd>
                                    <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                        <button id="maleButton" type="button" value="0" class="btn" data-toggle="button">男</button>
                                        <button id="femaleButton" type="button" value="1" class="btn" data-toggle="button">女</button>
                                    </div>
                                </dd>
                                <br/>
                                <dt>所在地</dt>
                                <dd>
                                    <s:select id="selProvince" name="province" list="{ }"></s:select>
                                    <s:select id="selCity" name="city" list="{ }"></s:select>
                                    <s:select id="selDistrict" name="district" list="{ }"></s:select>
                                </dd>
                                <br/>
                                <dt style='margin-top:5px'>年级</dt>
                                <dd><s:select cssClass="span4" headerValue="请选择你的年级" name="grade" list="{'小学', '六年级', '初一', '初二', '初三', '高一', '高二', '高三'}"/></dd>
                                <br/>
                                <dt style='margin-top:5px'>就读学校</dt>
                                <dd><s:textfield cssClass="span4" type="text" name="school" placeholder="请输入您就读的学校" /></dd>
                                <br/>
                                <br/>
                                <dd><input class="btn btn-primary" style="width:300px;" type="button" value="提交" onclick="testStudentSubmit();"/>
                                    <s:submit id="form_submit" cssClass="btn btn-primary" style="width:300px; display:none;" value="提交" method="studentRegister"/></dd>
                            </dl>
                        </s:if>
                    </s:form>
                </div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
