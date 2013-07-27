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
                        <h1>招聘</h1>
                        
                        <hr/>
                        <h3>海角教育（haijiaoedu.com）</h3>
                        <h5>开发工程师</h5>
                        <p style="text-indent: 8mm;line-height:20px">
                            岗位职责：
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            1.&nbsp;维护网站
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            2.&nbsp;根据用户反馈，开发新功能
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            3.&nbsp;待遇面议
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            任职要求：
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            1. 本科及以上学历，有一定网站开发经验
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            2. 主动、积极、聪明
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            3. 简历发送至：haijiaoedu@163.com
                        </p>
                        <h5>市场推广人员</h5>
                        <p style="text-indent: 8mm;line-height:20px">
                            岗位职责：
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            1.&nbsp;在相关社交网络宣传
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            2.&nbsp;根据公司要求，做好业务推广
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            3.&nbsp;待遇面议
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            任职要求：
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            1. 高中及以上学历，有一定市场营销经验
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            2. 主动、积极、吃苦耐劳
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            3. 简历发送至：haijiaoedu@163.com
                        </p>
                                       

                    </div>
                </div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
