<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>海角教育</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/validate.css" />
        <link rel="stylesheet" href="css/index.css" />
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/index.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript" src="js/validate.js"></script>
        <style>
            .index-page-text{
                color:#FFFFFF;
                text-shadow: 3px 3px 3px #000;
                line-height:200%;
                font-family: Microsoft YaHei;
                vertical-align: middle;
            }
            
            .index-page-text a{
                color:#33ffff
            }
            
            .index-page-text a:hover{
                color:#ffffff
            }
        </style>

    </head>
    <body>
        <script>
            $(document).ready(function() {
                var h = $(window).height();
                var w = $(window).width();
                var c = document.getElementById("maincontainer");
                c.style.marginTop = h / 2 - 250 + "px";
                $(window).resize(function() {
                    var h = $(window).height();
                    var w = $(window).width();
                    var c = document.getElementById("maincontainer");
                    if(h<500){h=500;}
                    c.style.marginTop = h / 2 - 250 + "px";
                });
            });
        </script>

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="bg"><img class="bg_pic" id="bg_pic" src="#"/></div>
        <div id="maincontainer" class="container">
            <div class="row" style="overflow: hidden;">
                <div class="span8 index-page-text" style="margin-top:7%">
                    <h3>欢迎来到海角教育！</h3>
                    <br/>
                    <p style="font-size: 20px">海角教育专注<a href="about.jsp">1V1在线教育</a>，无论您身处天涯海角，通过互联网即可获得全国最优秀的教育资源。</p>
                </div>
                <div class="span3 pull-right" style="width:230px;height:375px;margin-top:5%">
                    <div class="accordion" id="accordion2">
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#log-panel">
                                    <img src="images/log-text.png" alt="">
                                </a>
                            </div>
                            <div id="log-panel" class="accordion-body collapse in">
                                <s:form action="login.action">
                                    <div id="login_tip" class="validateTip"></div>
                                    <s:textfield name="email" placeholder="请输入邮箱" style="margin: 20px 0px 0px 4px;"></s:textfield>
                                    <s:password name="password" placeholder="请输入密码"  style="margin: 20px 0px 0px 4px;"></s:password>
                                    <label class="checkbox pull-right" style='margin-right:10px'>
                                        <s:checkbox name="autologin" fieldValue="on" cssStyle="width:30px"/>十天内自动登录
                                    </label>
                                    <s:submit cssClass="btn btn-success " style="margin-top:20px;margin-left:3px;width:220px" value="登录"></s:submit>
                                </s:form>
                                <div style="text-align: center;"><a style="color:white;" data-toggle="modal" data-target="#forgetPwd" href="">忘记密码</a> <a style="color:white;" class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#reg-panel">注册账号</a></div>
                            </div>
                        </div>
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle" id="reg-button" data-toggle="collapse" data-parent="#accordion2" href="#reg-panel">
                                    <img src="images/reg-text.png" alt="">
                                </a>
                            </div>
                            <div id="reg-panel" class="accordion-body collapse">
                                <div id="getErrorMessage" style="display:none;"><s:property value="#session.errorMessage" /></div>
                                <s:set name="errorMessage" value="" scope="session"></s:set>
                                <s:form action="register.action">
                                    <s:textfield name="email"  onchange="validate_email(this,email_tip);" placeholder="请输入邮箱" autofocus="autofocus" style="margin: 20px 0px 0px 4px;" ></s:textfield>
                                    <div id="email_tip" class="validateTip"></div>
                                    <s:password name="password1"  onchange="validate_passwordlength(this,password_tip1);" placeholder="请输入密码"  style="margin: 20px 0px 0px 4px;"></s:password>
                                    <div id="password_tip1" class="validateTip"></div>
                                    <s:password name="password2" onchange="validate_passwordequal(password1,this,password_tip2);" placeholder="请重复密码"  style="margin: 20px 0px 0px 4px;"></s:password>
                                    <div id="password_tip2" class="validateTip"></div>
                                    <a id="reg-btn" data-toggle="modal" data-target="#choosemodal" class="btn btn-success" style="width:195px;margin-top:20px;margin-left: 3px">注册</a>
                                    <div class="modal fade hide" id="choosemodal" style="margin-top:9%">
                                        <div class="modal-header" >
                                            <a class="close" data-dismiss="modal">×</a>
                                            <h3>选择您的身份</h3>
                                        </div>       
                                        <div class="modal-body">
                                            <div style="width:50%;float:left">
                                                <a href="#" id="teacherbutton" onclick="$('#teachersubmit').click();"><img src="images/imteacher.png"/></a>
                                                    <s:submit value="老师" id="teachersubmit" cssStyle="display:none;" method="teacher"></s:submit>     
                                            </div>     
                                            <div style="width:50%;float:left">
                                                <a href="#" id="studentbutton" onclick="$('#studentsubmit').click();"><img src="images/imstudent.png"/></a>
                                                    <s:submit value="学生" id="studentsubmit" cssStyle="display:none;" method="student"></s:submit>
                                            </div> 
                                        </div>        
                                    </div>
                                </s:form>
                            </div>
                        </div>
                        <s:if test="tab=='register'">
                            <script>
                                $("#reg-button").click();
                            </script>
                        </s:if>
                    </div>
                </div>
            </div>
            <div class="modal fade hide"  style="margin-top:10%" id="forgetPwd">
                <div class="modal-header" >
                    <a class="close" data-dismiss="modal">×</a>
                    <h3>找回密码</h3>
                </div> 
                <div class="modal-body">
                    请输入您的注册邮箱：
                    <s:form action="forgetPassword.action">
                        <s:textfield name="email" cssStyle="margin-top:9px"></s:textfield>
                        <s:submit cssClass="btn btn-primary" value="提交"/>
                    </s:form>
                </div>    
            </div>
        </div>
        <div class="copyright" style="margin-top:20px">
            <p class="text-center"><a href="about.jsp">关于海角教育 ABOUT</a> | <a href="contact.jsp">联系我们 CONTACT</a> | <a href="copyright.jsp">版权声明 COPYRIGHT</a> | <a href="recruitment.jsp">招聘 RECRUITMENT</a> </p>
            <p class="text-center">Copyright <span style="font-family: Microsoft Yahei;">©</span> 2013 haijiaoedu.com <a target="_blank" href="#">All Rights Reserved</a>.</p>
            <p class="text-center">海角教育 <a href="#" target="_blank">版权所有</a> <a href="http://www.miitbeian.gov.cn">沪ICP备12023789-2号</a></p>
        </div>
    </body>
</html>
