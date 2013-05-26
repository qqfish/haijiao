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
        <script>
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

            window.onload = function() {
                var a = document.getElementById("prov");
                a.onclick = function() {
                    citypicker.show({left: 545, top: 380}, function(data) {
                        a.value = data;
                    });
                };
            }

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
                        <li id="l1" class="active"><a href="#1" data-toggle="tab">修改资料<i class="icon-chevron-right pull-right"></i></a></li>
                        <li id="l2" ><a href="#2" data-toggle="tab">修改密码<i class="icon-chevron-right pull-right"></i></a></li>
                        <li id="l3" ><a href="#3" data-toggle="tab">修改头像<i class="icon-chevron-right pull-right"></i></a></li>
                        <li id="l4"><a href="#4" data-toggle="tab">修改个人介绍<i class="icon-chevron-right pull-right"></i></a></li>
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
                                        <dt>省份</dt>
                                        <dd><div class="info">
                                                <div>
                                                    <select class="span2" id="s_province" name="s_province"></select>
                                                    <select id="s_city" name="s_city" ></select>
                                                    <select id="s_county" name="s_county"></select>
                                                    <script type="text/javascript" src="js/area.js"></script>
                                                    <script type="text/javascript">_init_area();</script>
                                                </div>
                                                <div id="show"></div>
                                            </div>
                                            <s:select cssClass="span4" headerKey="" headerValue="请选择你出生的省份" name="province" value="%{tea.province}" list="{'北京市', '上海市', '天津市', '重庆市', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '四川', '贵州', '云南', '西藏', '陕西', '甘肃', '青海', '宁夏', '新壃', '香港', '澳门', '台湾'}"/></dd>
                                        <dt>大学</dt>
                                        <dd> <s:textfield cssClass="span4" type="text" name="school" placeholder="请输入您就读的大学" value="%{tea.school}"/></dd>
                                        <dt>网络环境</dt>
                                        <dd><s:select cssClass="span4" name="net" list="{'电信', '联通', '教育网','网通','铁通','移动','有线通'}" value="%{tea.net}"/></dd>
                                        <dt>手机</dt>
                                        <dd><s:textfield cssClass="span4" type="text" name="tel" placeholder="请输入您的手机号" value="%{tea.tel}"/></dd>                                
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
                            <h3>修改个人介绍</h3>
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
