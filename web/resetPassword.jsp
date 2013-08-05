<%-- 
    Document   : resetPassword
    Created on : 2013-5-8, 20:37:10
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>海角教育</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/validate.css">
        <!--js-->
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="js/validate.js"></script>
        <link rel="stylesheet" href="css/style.css"/>

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
        <div class="container wrapper" >
            <div class="row" style="margin:-5px;">
                <div class="span3 module" style="padding:12px">
                    <h3>修改信息</h3>
                    <hr/>
                    <ul class="nav nav-list bs-docs-sidenav">
                        <li class="active"><a href="#1" data-toggle="tab">修改密码<i class="icon-chevron-right pull-right"></i></a></li>
                    </ul>
                </div>
                <div class="span8 module" style="padding:12px;">
                    <div class="tab-content">
                        <div class="tab-pane fade active in"  id='1'>
                            <s:form action="resetChangePassword.action">
                                <h3>修改密码</h3>
                                <hr/>
                                <dl>
                                    <s:textfield name="id" value="%{id}" cssStyle="display:none;"/>
                                    <dt>新密码</dt>
                                    <dd><s:password cssClass="span4" onchange="validate_passwordlength(this,password_tip1);" type="text" name="newpwd" placeholder="请输入新密码" autofocus="autofocus"/></dd>
                                    <dd id="password_tip1" class="validateTip" style="text-align: left;"></dd>
                                    <dt>确认密码</dt>
                                    <dd><s:password cssClass="span4" onchange="validate_passwordequal(newpwd,this,password_tip2);" type="text" name="newpwd2" placeholder="请再输入一次新密码" autofocus="autofocus"/></dd>
                                    <dd id="password_tip2" class="validateTip" style="text-align: left;"></dd>
                                    <br/>
                                    <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="changePasswordById"/></dd>
                                </dl>
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
