<%-- 
    Document   : about
    Created on : 2013-5-26, 17:21:03
    Author     : Bx
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Contacts</title>
        <meta charset="utf-8">
        <!--css-->
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="icon" href="images/favicon.ico" type="image/x-icon">
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
        <!--js-->
        <script type="text/javascript" src="js/jquery-1.7.1.min.js" ></script>
        <script type="text/javascript" src="js/superfish.js"></script>
        <script type="text/javascript" src="js/forms.js"></script>
        <script type="text/javascript">
            $(function() {
                $('#contact-form').forms({
                    ownerEmail: '#'
                })
            })
        </script>
        <!--[if lt IE 8]>
              <div style=' clear: both; text-align:center; position: relative;'>
                <a href="http://windows.microsoft.com/en-US/internet-explorer/products/ie/home?ocid=ie6_countdown_bannercode">
                  <img src="http://storage.ie6countdown.com/assets/100/images/banners/warning_bar_0000_us.jpg" border="0" height="42" width="820" alt="You are using an outdated browser. For a faster, safer browsing experience, upgrade for free today." />
               </a>
             </div>
              <link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400italic' rel='stylesheet' type='text/css'>
              <link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400' rel='stylesheet' type='text/css'>
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
        <div class="container wrapper">
            <div class="container-fluid">
                <div class="span11 module" style="padding:12px;">
                    <div class="span10">
                        <h1>关于我们</h1>
                        <hr/>
                        <h3>海角教育（haijiaoedu.com）</h3>
                        <p>
                            海角教育（haijiaoedu.com），中国领先的在线1V1互动教育网站，2013年6月1日正式推出。海角教育以在线教学服务为主要服务产品，充分满足初一至高三年级的在线学习需求及体验，现在正逐渐成为中国互联网领域具有影响力，最受用户欢迎的在线教育平台。
                            海角教育核心团队均来自中国著名学府，位于上海市西南角的上海交通大学，团队希望通过互联网技术将优质的教学资源服务辐射到天涯海角，利用视频通讯技术和双向互动白板技术开创“在线互动教学”新模式，力争成为中国互联网教育领域最佳体验的代表，成为在线学习的第一选择。
                        </p>
                        <div class="row"><img class="span6 offset2" src="images/bkg.jpg"></div>
                        <br/>
                        <div class="row">
                            <p class="text-info text-center"><strong>自从互联网诞生之日起，它就注定要改变人们的生活方式；</strong></p>
                            <p class="text-info text-center"><strong>自从海角教育诞生之日起，我们就立志要让它改变人们在互联网上的学习方式！</strong></p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
