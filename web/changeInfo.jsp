<%-- 
    Document   : changeInfo
    Created on : 2013-4-10, 16:19:38
    Author     : bx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>海角教育</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="css/datepicker.css">
        <link rel="stylesheet" href="kindeditor/themes/default/default.css" />
        <link rel="stylesheet" href="css/validate.css"/>
        <link rel="stylesheet" href="css/jquery.Jcrop.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <!--js-->
        <script type="text/javascript" src="js/jquery-1.8.3.min.js" ></script>
        <script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
        <script type="text/javascript" src="js/changeInfo.js"></script>
        <script type="text/javascript" src="js/validate.js"></script>
        <script type="text/javascript" src="js/bootstrap.file-input.js"></script>
        <script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
        <script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
        <script type="text/javascript" src="js/jquery.Jcrop.min.js"></script>
        <script type="text/javascript" src="js/ling.chinaArea.sort-1.0.js"></script>
        <script>
            window.onload = function() {
                $ling.chinaArea.init("selProvince", "selCity", "selDistrict");
                $("#selProvince").val($("#txtProvince").val());
                $("#selProvince").click();
                $("#selCity").val($("#txtCity").val());
                $("#selCity").click();
                $("#selDistrict").val($("#txtDistrict").val());
            }
            
            var editor;
            KindEditor.ready(function(K) {
                editor = K.create('textarea[name="intro"]', {
                    resizeType: 1,
                    allowPreviewEmoticons: false,
                    allowImageUpload: false,
                    width: '95%',
                    items: [
                        'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                        'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                        'insertunorderedlist', '|', 'emoticons', 'image', 'link']
                });
            });

            // 获取本地上传的照片路径  
            function getPath(obj) {
                if (obj) {
                    //ie  
                    if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
                        obj.select();
                        // IE下取得图片的本地路径  
                        return document.selection.createRange().text;
                    }
                    //firefox  
                    else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
                        if (obj.files) {
                            // Firefox下取得的是图片的数据  
                            return obj.files.item(0).getAsDataURL();
                        }
                        return obj.value;
                    }
                    return obj.value;
                }
            }

            var jcrop_api;

            //显示图片
            function previewPhoto() {
                var picsrc = getPath(document.all.fileid);
                var picpreview = document.getElementById("preview");
                if (!picsrc) {
                    return;
                }
                if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
                    if (picpreview) {
                        try {
                            picpreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = picsrc;
                        } catch (ex) {
                            alert("文件路径非法，请重新选择！");
                            return false;
                        }
                    } else {
                        $("#preimg").attr("src", picsrc);
                    }
                }
            }

            var uploadButton = false;

            function preImg(fileid, imgid) {
                var src = $("#fileid").val();
                $('#pic_tip2').css("color", "black");
                if (src.substr(src.length - 3, src.length) != "jpg") {
                    $('#pic_tip2').css("color", "red");
                    return;
                }
                $('#uploadButton').removeClass("disabled");
                uploadButton = true;
                $('#pic_org').fadeOut();
                $('#pre_area').fadeIn();
                if (typeof FileReader == 'undefined') {
                    var picsrc = getPath(document.all.fileid);
                    jcrop_api.setImage(picsrc);
                    //previewPhoto();
                }
                else {
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        jcrop_api.setImage(this.result);
                    };
                    reader.readAsDataURL(document.getElementById(fileid).files[0]);
                }
            }

            function showPreview(coords) {
                $('#pic_x').val(coords.x);
                $('#pic_y').val(coords.y);
                $('#pic_rate').val(this.getBounds()[0] / width);
                $('#pic_w').val(coords.w);
                $('#pic_h').val(coords.h);
            }

            function checkSubmit() {
                if (uploadButton === false) {
                    return;
                }
                if ($('#pic_w').val() == null || $('#pic_w').val() <= 10) {
                    alert("未选中区域或选中区域太小");
                } else if ($('#pic_h').val() == null || $('#pic_h').val() <= 10) {
                    alert("未选中区域或选中区域太小");
                } else {
                    $('#upload').submit();
                }
            }

            var width;

            function getSize(img)
            {
                if (typeof(img) != 'object')
                    img = document.getElementById(img);
                if (img == null)
                    return;
                var image = document.createElement("img");
                image.onload = function() {
                    width = this.width;
                };
                image.src = img.src;
            }

            jQuery(window).load(function() {
                $("#upload").submit(function() {
                    var wait = setInterval(function() {
                        var data = '<%=session.getAttribute("currentProcess")%>';
                        $(".bar").css("width", data + "%");
                        if (Number(data) == 100)
                            clearInterval(wait);
                    }, 100);
                });
                
                jcrop_api = $.Jcrop("#preimg", {
                    onChange: showPreview,
                    onSelect: showPreview,
                    aspectRatio: 1
                });
            });
        </script>



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
        <input type="hidden" id="nextPageMessage" value="<s:property value="nextPageMessage" />"/>
        <div class="container wrapper" >
            <div class="row" style="margin:-5px;">
                <div class="span3 module" style="padding:12px">
                    <h3>修改信息</h3>
                    <hr/>
                    <ul class="nav nav-list bs-docs-sidenav">
                        <li id="l1" class="active"><a href="#1" data-toggle="tab">修改基本资料<i class="icon-chevron-right pull-right"></i></a></li>
                        <s:if test="#session.userType=='teacher'">
                            <li id="l5"><a href="#5" data-toggle="tab">修改详细资料<i class="icon-chevron-right pull-right"></i></a></li>
                        </s:if>
                        <li id="l2" ><a href="#2" data-toggle="tab">修改密码<i class="icon-chevron-right pull-right"></i></a></li>
                        <li id="l3" ><a href="#3" data-toggle="tab">修改头像<i class="icon-chevron-right pull-right"></i></a></li>
                        <li id="l4"><a href="#4" data-toggle="tab">修改主页介绍<i class="icon-chevron-right pull-right"></i></a></li>
                    </ul>
                </div>
                <div class="span8 module" style="padding:12px;">
                    <div class="tab-content">
                        <div class="tab-pane fade active in" id='1'>
                            <s:form action="changeInfo.action">
                                <s:if test="#session.userType == 'teacher'">
                                    <h3>老师您好，修改你的详细信息吧^ ^</h3>
                                    <hr/>
                                    <dl>
                                        <dt>姓名</dt>
                                        <dd><s:textfield cssClass="span4" onchange="validate_required(this,name_tip);" type="text" name="name" placeholder="请输入您的大名" value="%{tea.name}" autofocus="autofocus" /></dd>
                                        <dd id="name_tip" class="validateTip" style="text-align: left;"></dd>
                                        <dt>性别</dt>
                                        <dd style="display:none;"><s:radio list="{'男', '女'}" name="sex" value="%{tea.sex}"/></dd>
                                        <dd>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button id="maleButton" type="button" value="0" class="btn" data-toggle="button">男</button>
                                                <button id="femaleButton" type="button" value="1" class="btn" data-toggle="button">女</button>
                                            </div>
                                        </dd>
                                        <dt>生日</dt>
                                        <dd><s:textfield cssClass="span4" type="text" id="datepicker" name="birthday" readOnly="readOnly" placeholder="请输入您的生日" value="%{tea.birthday}"/></dd>
                                        <dt>所在地</dt>
                                        <dd>
                                                <s:select id="selProvince" cssClass="span2" name="province" list="{ }" value="%{tea.province}"></s:select>
                                                <s:select id="selCity" cssClass="span2" name="city" list="{ }" value="%{tea.city}"></s:select>
                                                <s:select id="selDistrict" cssClass="span2" name="district" list="{ }" value="%{tea.district}"></s:select>
                                                <input type="hidden" id="txtProvince" value="<s:property value="tea.province"/>"/>
                                                <input type="hidden" id="txtCity" value="<s:property value="tea.city"/>"/>
                                                <input type="hidden" id="txtDistrict" value="<s:property value="tea.district"/>"/>
                                        </dd>
                                        <dt>目前身份</dt>
                                        <dd><s:select cssClass="span2" name="studyStatus" list="{'在职教师','大专学生','大一学生','大二学生','大三学生','大四学生','在读硕士','在读博士','海归/外教','其他'}"></s:select></dd>
                                        <dt>大学</dt>
                                        <dd><s:textfield cssClass="span2" type="text" name="school" placeholder="请输入您就读的大学" value="%{tea.school}"/></dd>
                                        <dt>专业</dt>
                                        <dd><s:textfield cssClass="span2" type="text" name="major" placeholder="请输入您就读的专业" value="%{tea.major}"/></dd>
                                        <dt>网络环境</dt>
                                        <dd><s:select cssClass="span2" name="net" list="{'电信', '联通(网通)', '教育网', '移动(铁通)', '有线通', '其他'}" value="%{tea.net}"/></dd>
                                        <dt>手机</dt>
                                        <dd><s:textfield cssClass="span2" type="text" name="tel" placeholder="请输入您的手机号" value="%{tea.tel}"/></dd>                                
                                        <br/>
                                        <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="teacherChange"/></dd>
                                    </dl>
                                </s:if>
                                <s:if test="#session.userType == 'student'">
                                    <h3>同学您好，修改你的详细信息吧^ ^</h3>
                                    <hr/>
                                    <dl>
                                        <dt>姓名</dt>
                                        <dd><s:textfield cssClass="span4" onchange="validate_required(this,name_tip);" type="text" name="name" placeholder="请输入您的大名" value="%{stu.name}" autofocus="autofocus" /></dd>
                                        <dd id="name_tip" class="validateTip" style="text-align: left;"></dd>
                                        <dt>性别</dt>
                                        <dd style="display:none;"><s:radio list="{'男', '女'}" name="sex" value="%{stu.sex}"/></dd>
                                        <dd>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button id="maleButton" type="button" value="0" class="btn" data-toggle="button">男</button>
                                                <button id="femaleButton" type="button" value="1" class="btn" data-toggle="button">女</button>
                                            </div>
                                        </dd>
                                        <dt>生日</dt>
                                        <dd><s:textfield cssClass="span4" type="text" id="datepicker" name="birthday" placeholder="请输入您的生日" value="%{stu.birthday}"/></dd>
                                        <dt>学校</dt>
                                        <dd><s:textfield cssClass="span4" type="text" name="school" placeholder="请输入您就读的学校" value="%{stu.school}"/></dd>
                                        <dt>年级</dt>
                                        <dd><s:select cssClass="span4" headerValue="请选择你的年级" name="grade" list="{'小学', '六年级', '初一', '初二', '初三', '高一', '高二', '高三'}" value="%{stu.grade}"/></dd>
                                        <dt>手机</dt>
                                        <dd><s:textfield cssClass="span4" type="text" name="tel" placeholder="请输入您的手机号" value="%{stu.tel}"/></dd>                                
                                        <dd style="display: none;"><s:radio list="{'student', 'parent'}" name="telType" value="%{stu.telType}"/></dd>
                                        <dd>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button id="meButton" type="button" value="0" class="btn" data-toggle="button">我的手机</button>
                                                <button id="parentButton" type="button" value="1" class="btn" data-toggle="button">爸妈的手机</button>
                                            </div>
                                        </dd>
                                        <br/>
                                        <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="studentChange"/></dd>
                                    </dl>
                                </s:if>
                            </s:form>
                        </div>
                        <div class="tab-pane fade"  id='5'>
                            <s:form action="changeInfo.action">
                                <h3>修改详细资料</h3>
                                <hr/>
                                <dl>
                                    <dt>授课方式</dt>
                                    <dd style="margin-bottom: 5px; margin-top: 5px;">
                                        <s:checkbox name="sprtOnline" value="%{tea.sprtOnline}"/> 线上授课
                                        <s:checkbox name="sprtUnderline" value="%{tea.sprtUnderline}"/> 线下授课
                                    </dd>
                                    <dt>线下授课区域</dt>
                                    <dd><s:textarea cssStyle="width:520px;height:150px;" cssClass="span5" name="underlineArea" autofocus="autofocus"/></dd>
                                    <dt>个人经历</dt>
                                    <dd><s:textarea cssStyle="width:520px;height:200px;" cssClass="span5" name="experience" autofocus="autofocus"/></dd>
                                    <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="teacherMoreChange"/></dd>
                                </dl>
                            </s:form>
                        </div>
                        <div class="tab-pane fade"  id='2'>
                            <s:form action="changeInfo.action">
                                <h3>修改密码</h3>
                                <hr/>
                                <dl>
                                    <dt>旧密码</dt>
                                    <dd><s:password cssClass="span4" type="text" name="oldpwd" placeholder="请输入旧密码" autofocus="autofocus"/></dd>
                                    <dt>新密码</dt>
                                    <dd><s:password cssClass="span4" onchange="validate_passwordlength(this,password_tip1);" type="text" name="newpwd" placeholder="请输入新密码" autofocus="autofocus"/></dd>
                                    <dd id="password_tip1" class="validateTip" style="text-align: left;"></dd>
                                    <dt>确认密码</dt>
                                    <dd><s:password cssClass="span4" onchange="validate_passwordequal(newpwd,this,password_tip2);" type="text" name="newpwd2" placeholder="请再输入一次新密码" autofocus="autofocus"/></dd>
                                    <dd id="password_tip2" class="validateTip" style="text-align: left;"></dd>
                                    <br/>
                                    <dd><s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="changePassword"/></dd>
                                </dl>
                            </s:form>
                        </div>
                        <div class="tab-pane fade"  id='3'>
                            <h3>修改头像</h3>
                            <hr/>
                            <p style="font-size: 9px;">请先选择图片上传，再在上传图片中截取作为头像的部分，按上传文件完成上传。<br/>
                            <div id="pic_tip1">注意①：请确保图片小于2MB<br/></div>
                            <div id="pic_tip2">注意②：目前只支持上传JPG类型的图片哦</div></p>
                            <div id="pre_area" style="display:none;">
                                <div id="preview" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);">
                                    <img id="preimg" onload="getSize(this)"/>
                                </div>
                            </div>
                            <s:if test="#session.userType=='student'">
                                <img id="pic_org" src="<s:property value="stu.picUrl"/>" style="height: 230px;width: 230px;"/>
                            </s:if>
                            <s:if test="#session.userType=='teacher'">
                                <img id="pic_org" src="<s:property value="tea.picUrl"/>" style="height: 230px;width: 230px;"/>
                            </s:if>
                            <s:form action="uploadPic" id="upload" enctype="multipart/form-data">
                                <s:textfield id="pic_x" name="x" style="display:none;"/>
                                <s:textfield id="pic_y" name="y" style="display:none;"/>
                                <s:textfield id="pic_rate" name="rate" style="display:none;"/>
                                <s:textfield id="pic_w" name="w" style="display:none;"/>
                                <s:textfield id="pic_h" name="h" style="display:none;"/>
                                <s:file name="upload" title="选择文件" id="fileid" onchange="preImg(this.id,preimg);"/>
                                <button type="button" id="uploadButton" class="btn disabled" data-toggle="button" onclick="checkSubmit();" >上传文件</button>
                            </s:form>
                            <div class="progress"><div class="bar"></div></div>
                        </div>
                        <div class="tab-pane fade"  id='4'>
                            <h3>修改主页介绍</h3>
                            <hr/>
                            <s:form action="changeIntro.action">
                                <s:if test="#session.userType == 'teacher'">
                                    <s:textarea name="intro" value="%{tea.intro}" autofocus="autofocus" cssClass="span7"/><br/>
                                </s:if>
                                <s:if test="#session.userType == 'student'">
                                    <s:textarea name="intro" value="%{stu.intro}" autofocus="autofocus" cssClass="span7"/><br/>
                                </s:if>
                                <s:submit cssClass="btn btn-primary btn-small pull-right" value="提交"/>
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
