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
        <link rel="stylesheet" href="css/index.css" />
    </head>
    <body>

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="container">
            <div class="row" style="height:375px">
                <div class="span8">
                    <!--                    <div id="myCarousel" class="carousel slide" style="height:400px;overflow: hidden">
                                            <ol class="carousel-indicators">
                                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                                <li data-target="#myCarousel" data-slide-to="2"></li>
                                            </ol>
                                            <div class="carousel-inner">
                                                <div class="item active">
                                                    <img src="images/1.jpg" alt="">
                                                    <div class="carousel-caption">
                                                        <h4>First Thumbnail label</h4>
                                                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                                    </div>
                                                </div>
                                                <div class="item">
                                                    <img src="images/1.jpg" alt="">
                                                    <div class="carousel-caption">
                                                        <h4>Second Thumbnail label</h4>
                                                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                                    </div>
                                                </div>
                                                <div class="item">
                                                    <img src="images/1.jpg" alt="">
                                                    <div class="carousel-caption">
                                                        <h4>Third Thumbnail label</h4>
                                                        <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                                    </div>
                                                </div>
                                            </div>
                                            <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                                            <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
                                        </div>-->
                </div>
                <div class="span4" style="width:265px">
                    <div class="accordion" id="accordion2">
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#reg-panel">
                                    <img src="images/reg-text.png" alt="">
                                </a>
                            </div>
                            <div id="reg-panel" class="accordion-body collapse in">
                                <div class="accordion-inner">
                                    <s:form action="register.action">
                                        <s:textfield name="email"  onchange="validate_email(this,email_tip);" placeholder="请输入邮箱" autofocus="autofocus" style="margin: 20px 0px 0px 4px;" ></s:textfield>
                                            <div id="email_tip" class="validateTip"></div>
                                        <s:password name="password1"  onchange="validate_passwordlength(this,password_tip1);" placeholder="请输入密码"  style="margin: 20px 0px 0px 4px;"></s:password>
                                            <div id="password_tip1" class="validateTip"></div>
                                        <s:password name="password2" onchange="validate_passwordequal(password1,this,password_tip2);" placeholder="请重复密码"  style="margin: 20px 0px 0px 4px;"></s:password>
                                            <div id="password_tip2" class="validateTip"></div>
                                            <a id="reg-btn" data-toggle="modal" data-target="#choosemodal" class="btn btn-success" style="width:195px;margin-top:40px;margin-left: 4px">注册</a>
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
                            </div>
                        </div>
                        <div class="accordion-group">
                            <div class="accordion-heading">
                                <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#log-panel">
                                    <img src="images/log-text.png" alt="">
                                </a>
                            </div>
                            <div id="log-panel" class="accordion-body collapse">
                                <div class="accordion-inner">
                                    <s:form action="login.action">
                                        <s:textfield name="email" placeholder="请输入邮箱" style="margin: 20px 0px 0px 4px;"></s:textfield>
                                        <s:password name="password" placeholder="请输入密码"  style="margin: 20px 0px 0px 4px;"></s:password>
                                        <s:submit cssClass="btn btn-success " style="margin-top:90px;margin-left:8px;width:220px" value="登录"></s:submit>
                                    </s:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="span6"><img src="images/index-intro L.png" alt=""></div>
                <div class="span6"><img src="images/index-intro R.png" alt=""></div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
