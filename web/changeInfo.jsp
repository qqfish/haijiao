<%-- 
    Document   : changeInfo
    Created on : 2013-4-10, 16:19:38
    Author     : bx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>海角教育</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/datepicker.css">
        <link rel="stylesheet" href="kindeditor/themes/default/default.css" />
        <link rel="stylesheet" href="css/validate.css">
        <!--js-->
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="js/changeInfo.js"></script>
        <script type="text/javascript" src="js/validate.js"></script>
        <script type="text/javascript" src="js/bootstrap.file-input.js"></script>
        <script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
        <script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
        <script>
            var editor;
            KindEditor.ready(function(K) {
                editor = K.create('textarea[name="intro"]', {
                    resizeType : 1,
                    allowPreviewEmoticons : false,
                    allowImageUpload : false,
                    items : [
                        'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
                    });
            });

            jQuery(window).load(function() {
                $("#upload").submit(function(){
                    var wait = setInterval(function(){
                        $.post("uploadProcess.action",
                            function (data){
                                console.log("in");
                                if(data == "100")
                                    clearInterval(wait);
                            });
                    },1000);
                });
            
                /*jQuery('.flexslider').flexslider({
                    animation: "fade",
                    slideshow: true,
                    slideshowSpeed: 7000,
                    animationDuration: 600,
                    prevText: "",
                    nextText: "",
                    controlNav: false
                });*/
                
            });
            
            
        </script>



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
        <link rel="stylesheet" href="css/tooltik.css">
        <div class="container">
            <div class="row">
                <div class="span3">
                    <h3>修改信息</h3>
                    <hr/>
                    <ul class="nav nav-list bs-docs-sidenav">
                        <li class="active"><a href="#1" data-toggle="tab">修改资料<i class="icon-chevron-right pull-right"></i></a></li>
                        <li><a href="#2" data-toggle="tab">修改密码<i class="icon-chevron-right pull-right"></i></a></li>
                        <li><a href="#3" data-toggle="tab">修改头像<i class="icon-chevron-right pull-right"></i></a></li>
                        <li><a href="#4" data-toggle="tab">修改个人介绍<i class="icon-chevron-right pull-right"></i></a></li>
                    </ul>
                </div>
                <div class="span9">
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id='1'>
                            <s:form action="changeInfo.action">
                                <s:if test="#session.userType == 'teacher'">
                                    <h3>老师您好，修改你的详细信息吧^ ^</h3>
                                    <hr/>
                                    <dl>
                                        <dt>姓名</dt>
                                        <dd><s:textfield cssClass="span4" onchange="validate_required(this,name_tip);" type="text" name="name" placeholder="请输入您的大名" value="%{tea.name}" autofocus="autofocus" /></dd>
                                        <dd id="name_tip" class="validateTip" style="text-align: left;"></dd>
                                        <dt>性别</dt>
                                        <dd style="display:none;"><s:radio list="{'男', '女'}" name="sex" value="%{tea.sex}"/></dd>
                                        <dd>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button id="maleButton" type="button" value="0" class="btn" data-toggle="button">男</button>
                                                <button id="femaleButton" type="button" value="1" class="btn" data-toggle="button">女</button>
                                            </div>
                                        </dd>
                                        <dt>生日</dt>
                                        <dd><s:textfield cssClass="span4" type="text" id="datepicker" name="birthday" placeholder="请输入您的生日" value="%{tea.birthday}"/></dd>
                                        <dt>省份</dt>
                                        <dd><s:select cssClass="span4" headerKey="" headerValue="请选择你出生的省份" name="province" value="%{tea.province}" list="{'北京市', '上海市', '天津市', '重庆市', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新壃', '香港', '澳门', '台湾'}"/></dd>
                                        <dt>大学</dt>
                                        <dd> <s:textfield cssClass="span4" type="text" name="school" placeholder="请输入您就读的大学" value="%{tea.school}"/></dd>
                                        <dt>手机</dt>
                                        <dd><s:textfield cssClass="span4" type="text" name="tel" placeholder="请输入您的手机号" value="%{tea.tel}"/></dd>                                
                                        <br/>
                                        <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="teacherChange"/></dd>
                                    </dl>
                                </s:if>
                                <s:if test="#session.userType == 'student'">
                                    <h3>同学您好，修改你的详细信息吧^ ^</h3>
                                    <hr/>
                                    <dl>
                                        <dt>姓名</dt>
                                        <dd><s:textfield cssClass="span4" onchange="validate_required(this,name_tip);" type="text" name="name" placeholder="请输入您的大名" value="%{stu.name}" autofocus="autofocus" /></dd>
                                        <dd id="name_tip" class="validateTip" style="text-align: left;"></dd>
                                        <dt>性别</dt>
                                        <dd style="display:none;"><s:radio list="{'男', '女'}" name="sex" value="%{stu.sex}"/></dd>
                                        <dd>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button id="maleButton" type="button" value="0" class="btn" data-toggle="button">男</button>
                                                <button id="femaleButton" type="button" value="1" class="btn" data-toggle="button">女</button>
                                            </div>
                                        </dd>
                                        <dt>生日</dt>
                                        <dd><s:textfield cssClass="span4" type="text" id="datepicker" name="birthday" placeholder="请输入您的生日" value="%{stu.birthday}"/></dd>
                                        <dt>学校</dt>
                                        <dd><s:textfield cssClass="span4" type="text" name="school" placeholder="请输入您就读的学校" value="%{stu.school}"/></dd>
                                        <dt>年级</dt>
                                        <dd><s:textfield cssClass="span4" type="text" name="grade" placeholder="请输入您就读的年级" value="%{stu.grade}"/></dd>
                                        <dt>手机</dt>
                                        <dd><s:textfield cssClass="span4" type="text" name="tel" placeholder="请输入您的手机号" value="%{stu.tel}"/></dd>                                
                                        <dd style="display: none;"><s:radio list="{'student', 'parent'}" name="telType" value="%{stu.telType}"/></dd>
                                        <dd>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button id="meButton" type="button" value="0" class="btn" data-toggle="button">我的手机</button>
                                                <button id="parentButton" type="button" value="1" class="btn" data-toggle="button">爸妈的手机</button>
                                            </div>
                                        </dd>
                                        <br/>
                                        <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="studentChange"/></dd>
                                    </dl>
                                </s:if>
                            </s:form>
                        </div>
                        <div class="tab-pane fade"  id='2'>
                            <s:form action="changeInfo.action">
                                <h3>修改密码</h3>
                                <hr/>
                                <dl>
                                    <dt>旧密码</dt>
                                    <dd><s:password cssClass="span4" type="text" name="oldpwd" placeholder="请输入旧密码" autofocus="autofocus"/></dd>
                                    <dt>新密码</dt>
                                    <dd><s:password cssClass="span4" onchange="validate_passwordlength(this,password_tip1);" type="text" name="newpwd" placeholder="请输入新密码" autofocus="autofocus"/></dd>
                                    <dd id="password_tip1" class="validateTip" style="text-align: left;"></dd>
                                    <dt>确认密码</dt>
                                    <dd><s:password cssClass="span4" onchange="validate_passwordequal(newpwd,this,password_tip2);" type="text" name="newpwd2" placeholder="请再输入一次新密码" autofocus="autofocus"/></dd>
                                    <dd id="password_tip2" class="validateTip" style="text-align: left;"></dd>
                                    <br/>
                                    <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="changePassword"/></dd>
                                </dl>
                            </s:form>
                        </div>
                        <div class="tab-pane fade"  id='3'>
                            <h3>修改头像</h3>
                            <hr/>
                            <s:if test="#session.userType=='student'">
                                <img src="<s:property value="stu.picUrl"/>" style="height: 230px;width: 230px;"/>
                            </s:if>
                            <s:if test="#session.userType=='teacher'">
                                <img src="<s:property value="tea.picUrl"/>" style="height: 230px;width: 230px;"/>
                            </s:if>
                            <s:form action="uploadPic" id="upload" enctype="multipart/form-data">
                                <s:file name="upload" title="上传" onchange="$('#upload').submit();"/>
                            </s:form>
                        </div>
                        <div class="tab-pane fade"  id='4'>
                            <h3>修改个人介绍</h3>
                            <hr/>
                            <s:form action="changeIntro.action">
                                <s:if test="#session.userType == 'teacher'">
                                    <s:textarea name="intro" value="%{tea.intro}" autofocus="autofocus"/><br/>
                                </s:if>
                                <s:if test="#session.userType == 'student'">
                                    <s:textarea name="intro" value="%{stu.intro}" autofocus="autofocus"/><br/>
                                </s:if>
                                <s:submit cssClass="btn btn-primary btn-small pull-right" value="提交"/>
                            </s:form>
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
