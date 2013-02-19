<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Contacts</title>
<meta charset="utf-8">
<!--css-->
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
<link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link href='http://fonts.googleapis.com/css?family=PT+Serif+Caption:400,400italic' rel='stylesheet' type='text/css'>
<!--js-->
<script type="text/javascript" src="js/jquery-1.7.1.min.js" ></script>
<script type="text/javascript" src="js/superfish.js"></script>
<script type="text/javascript" src="js/forms.js"></script>
<script type="text/javascript">
    $(function(){
	 $('#contact-form').forms({
	  ownerEmail:'#'
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
<section id="content"><div class="ic"></div>
  <div class="border-horiz"></div>
  <div class="main">
    <h3>联系信息</h3>
    <div class="box-address">
      <h4>Company Name Inc.</h4>
      <dl class="address">
        <dt>8901 Marmora Road,<br>
          Glasgow, D04 89GR.</dt>
        <dd> <span>Freephone:</span> +1 800 559 6580</dd>
        <dd> <span>Telephone:</span> +1 959 603 6035</dd>
        <dd> <span>FAX:</span> +1 504 889 9898</dd>
        <dd> E-mail: <a class="mail-1" href="#">mail@demolink.org</a> </dd>
      </dl>
    </div>
    <div class="map box-img">
      <iframe src="http://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=Brooklyn,+New+York,+NY,+United+States&amp;aq=0&amp;sll=37.0625,-95.677068&amp;sspn=61.282355,146.513672&amp;ie=UTF8&amp;hq=&amp;hnear=Brooklyn,+Kings,+New+York&amp;ll=40.649974,-73.950005&amp;spn=0.01628,0.025663&amp;z=14&amp;iwloc=A&amp;output=embed"> </iframe>
    </div>
    <div class="clear"></div>
  </div>
  <div class="box-contact">
    <h3>联系表格</h3>
    <form id="contact-form">
      <div class="success"> Contact form submitted! <strong>We will be in touch soon.</strong> </div>
      <fieldset>
        <div class="coll-1">
          <div>
            <div class="form-txt">Your Name:</div>
            <label class="name">
              <input type="text">
              <span class="error">*This is not a valid name.</span> <span class="empty">*This field is required.</span> </label>
            <div class="clear"></div>
          </div>
          <div>
            <div class="form-txt">Telephone:</div>
            <label class="phone">
              <input type="tel">
              <span class="error">*This is not a valid phone number.</span> <span class="empty">*This field is required.</span> </label>
            <div class="clear"></div>
          </div>
          <div>
            <div class="form-txt">Email:</div>
            <label class="email">
              <input type="email">
              <span class="error">*This is not a valid email address.</span> <span class="empty">*This field is required.</span> </label>
            <div class="clear"></div>
          </div>
        </div>
        <div class="coll-2">
          <div>
            <div class="form-txt">Message:</div>
            <label class="message">
              <textarea></textarea>
              <span class="error">*The message is too short.</span> <span class="empty">*This field is required.</span> </label>
            <div class="clear"></div>
          </div>
        </div>
        <div class="clear"></div>
        <div class="btns"> <a class="btn" data-type="reset">Clear</a> <a class="btn" data-type="submit">send</a> </div>
      </fieldset>
    </form>
  </div>
</section>

<!--==============================footer=================================-->
<%@ include file="WEB-INF/jspf/footer.jspf"%>
</body>
</html>
