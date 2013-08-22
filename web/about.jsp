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
        <!--js-->
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
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

        <script type="text/javascript">
            function control_size() {
                while(document.getElementById("_parent_div").offsetHeight*0.95 >document.getElementById("_need_div").offsetHeight) {
                    obj = document.createElement("br");
                         
                    document.getElementById("_addp").appendChild(obj);
                }
            }
        </script>
    </head>
    <body onload="control_size()">

        <!--==============================header=================================-->
        <%@ include file="WEB-INF/jspf/header.jspf"%>
        <!--==============================content=================================-->
        <div class="container wrapper" id="_parent_div">
            <div class="container-fluid" >

                <div class="span11 module" style="padding:12px;"  id="_need_div">

                    <div class="span10">
                        <h1>关于我们</h1>
                        <hr/>
                        <h3>海角教育（haijiaoedu.com）</h3>
                        <p style="text-indent: 8mm;line-height:20px">
                            海角教育（haijiaoedu.com），中国领先的在线1V1互动教育网站，2013年6月1日正式推出。海角教育以在线教学服务为主要服务产品，充分满足初一至高三年级的在线学习需求及体验，现在正逐渐成为中国互联网领域具有影响力，最受用户欢迎的在线教育平台。
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            海角教育核心团队均来自位于上海市西南角的中国著名学府——上海交通大学与华东师范大学，团队希望通过互联网技术将优质的教学资源服务辐射到天涯海角，利用视频通讯技术和双向互动白板技术开创“在线互动教学”新模式，力争成为中国互联网教育领域最佳体验的代表，成为在线学习的第一选择。
                        </p>
                        <!--                        <div class="row"><img class="span6 offset2" src="images/bkg.jpg"></div>-->
                        <div class="row">
                            <video class="span6 offset2"id="video" controls="controls" preload="auto" x-webkit-airplay="true" class="playing" poster="http://p1.v.iask.com/264/84/111823592_2.jpg">
                                <source id="video-hlv" src="http://v.iask.com/v_play_ipad.php?vid=111824104" type="video/mp4">
                            </video>
                        </div>
                        <br/>
                        <div class="row" id="_addp">
                            <p class="text-center"><a href="http://video.sina.com.cn/v/b/111924519-3707916701.html">海角1V1互动视频教学录像 老师在上海市 学生在四川省成都市</a></p>
<!--                            <p class="text-center"><strong>自从互联网诞生之日起，它就注定要改变人们的生活方式；</strong></p>
                            <p class=" text-center"><strong>自从海角教育诞生之日起，我们就立志要让它改变人们在互联网上的学习方式！</strong></p>-->
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
