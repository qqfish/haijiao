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
                        <h1>意见反馈</h1>
                        <p>如果有任何意见以及建议，请及时联系我们</p>
                        <hr/>
                    </div>

                    <div class="span6 module" style="padding:12px;">

                        <s:form action="contactUs.action">
                            <dt style="display: none;"><label>姓名</label></dt>
                            <dd style="display: none;"><s:textfield name="name" placeholder="请输入您的姓名" /></dd>
                            <dt><label>邮箱</label></dt>
                            <dd>
                                <s:textfield id="emailInput" name="email" placeholder="请输入您的邮箱" />
                                <s:if test="#session.email!=null">
                                    <script>
                                        $("#emailInput").val("<s:property value='#session.email' />").attr("class", "uneditable-input");
                                    </script>
                                </s:if>
                            </dd>
                            <dt style="display: none;"><label>主题</label></dt>
                            <dd style="display: none;"><s:textfield name="subject" placeholder="请输入主题" /></dd>
                            <dt><label for="user_body">内容</label></dt>
                            <dd>
                                <s:textarea name="context" cssClass="span5" rows="5" value=""/>
                            </dd>
                            <s:submit cssClass="offset4 btn" type="button" value="提交" />
                        </s:form>
                    </div><!-- /.column.main -->
                    <div class="span4">
                        <h4>感谢您的宝贵意见</h4>
                        <ul class="checklist">
                            <li>如果我们需要更多的意见我们将联系您</li>
                            <li>在您的帮助下我们将做得更好</li>
                        </ul>

                        <div class="rule"></div>

                        <h4>其他联系方式</h4>
                        <p>
                        <ul class="checklist">
                            <li>电话:xxxxxxxx</li>
                            <li>地址:xxxxxxxx</li>
                            <li>邮箱:<a href="mailto:qqfishyx@gmail.com">qqfishyx@gmail.com</a></li>
                        </ul>
                        </p>
                        <hr/>
                        <p>如果你对<b>老师开设的课程</b>存在任何疑问，请直接联系课程老师。</p>

                    </div><!-- /.column.sidebar -->
                </div>
            </div>
        </div>
        <!--==============================footer=================================-->
        <%@ include file="WEB-INF/jspf/footer.jspf"%>
    </body>
</html>
