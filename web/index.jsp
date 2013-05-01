<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>海角教育</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/main.css" />
        <link rel="stylesheet" href="css/validate.css" />
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/index.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
        <script type="text/javascript" src="js/validate.js"></script>
    </head>
    <body>

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="container">
            <div class="row">
                <!--<div id ="dispalypanel" style="width:600px;height: 400px;"><img src="images/1.jpg" width=600 height=400></div>-->
                <div class="span8"style="overflow: hidden;"><img alt="" src="images/1.jpg" width=700 height=400></div>
                <div class="span4">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#reg-panel" data-toggle="tab">注册</a></li>
                        <li><a href="#log-panel" data-toggle="tab" >登录</a></li>
                    </ul>  
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id='reg-panel'>
                            <s:form action="register.action">
                                <s:textfield name="email"  onchange="validate_email(this,email_tip);" placeholder="请输入邮箱" autofocus="autofocus" style="margin: 20px 0px 0px 4px;height: 30px;width: 280px;font-size: 20px;" ></s:textfield>
                                    <div id="email_tip" class="validateTip"></div>
                                <s:password name="password1"  onchange="validate_passwordlength(this,password_tip1);" placeholder="请输入密码"  style="margin: 20px 0px 0px 4px;height: 30px;width: 280px;font-size: 20px;"></s:password>
                                    <div id="password_tip1" class="validateTip"></div>
                                <s:password name="password2" onchange="validate_passwordequal(password1,this,password_tip2);" placeholder="请重复密码"  style="margin: 20px 0px 0px 4px;height: 30px;width: 280px;font-size: 20px;"></s:password>
                                    <div id="password_tip2" class="validateTip"></div>
                                    <a id="reg-btn" data-toggle="modal" data-target="#choosemodal" class="btn btn-danger btn-large" style="width:252px;margin-top:40px;margin-left: 4px">注册</a>
                                    <div class="modal fade hide" id="choosemodal">
                                        <div class="modal-header" >
                                            <a class="close" data-dismiss="modal">×</a>
                                            <h3>选择您的身份</h3>
                                        </div>       
                                        <div class="modal-body">
                                            <div style="width:49%;float:left;border:0px solid #CCC">
                                                <a href="#" id="teacherbutton"><img src="images/imteacher.png"/></a>
                                                <s:submit value="老师" id="teachersubmit" cssStyle="display:none;" method="teacher"></s:submit>     
                                            </div>     
                                            <div style="width:49%;float:left;border:0px solid #CCC">
                                                <a href="#" id="studentbutton"><img src="images/imstudent.png"/></a>
                                                <s:submit value="学生" id="studentsubmit" cssStyle="display:none;" method="student"></s:submit>
                                            </div> 
                                        </div>        
                                    </div>
                            </s:form>
                        </div>
                        <div class="tab-pane fade"  id='log-panel'>
                            <s:form action="login.action">
                                <s:textfield name="email" placeholder="请输入邮箱" style="margin: 20px 0px 0px 4px;height: 30px;width: 280px;font-size: 20px;"></s:textfield>
                                <s:password name="password" placeholder="请输入密码"  style="margin: 20px 0px 0px 4px;height: 30px;width: 280px;font-size: 20px;"></s:password>
                                <s:submit cssClass="btn btn-primary btn-large" style="margin-top:100px;margin-left:4px;width:292px" value="登录"></s:submit>
                            </s:form>
                        </div>
                    </div>
                </div>
            </div>



            <div class="row">
                <h3 class="text-center">欢迎来到海角家教！</h3>
                <p class="text-info text-center">在线面对面的家教体验，为偏远地区学生获得优秀教学资源提供了便利。（详细介绍待定）</p>
            </div>
            <div class="row">
                <div class="span3">aaaaaaaaaaaaaaaaaaaaaaa</div>
                <div class="span3 offset1">aaaaaaaaaaaaaaaaaaaaaaaaaaaa</div>
                <div class="span3 offset1">aaaaaaaaaaaaaaaaaaaaaaaaa</div>
            </div>

        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
