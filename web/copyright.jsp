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
                        <h1>版权声明</h1>
                        
                        <hr/>
                        <h3>海角教育（haijiaoedu.com）</h3>
                        <p style="text-indent: 8mm;line-height:20px">
                            海角公司对其发行的或与合作公司共同发行的包括但不限于产品或服务的全部内容及海角网站上的材料拥有版权等知识产权，受法律保护。
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            未经本公司书面许可，任何单位及个人不得以任何方式或理由对上述产品、服务、信息、材料的任何部分进行使用、复制、修改、抄录、传播或与其它产品捆绑使用、销售。
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                            凡侵犯本公司版权等知识产权的，本公司必依法追究其法律责任。
                        </p>
                        <p style="text-indent: 8mm;line-height:20px">
                           本公司法律事务部受本公司指示，特此郑重法律声明！
                        </p>
                        
                        <p style="text-indent: 8mm;line-height:20px;float:right">
                           声明单位：海角公司
                        </p>
                        <br/>
                        <br/><br/>
                        <br/>
                        
                        <div class="row"><img style="width:100%;margin-left: 15px" src="images/copyright.jpg"></div>
                        

                    </div>
                </div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
