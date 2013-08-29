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
        <link rel="stylesheet" href="css/jquery.autocomplete.css">

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
        <script type="text/javascript" src="js/jquery.autocomplete.min.js"></script>
        <script type="text/javascript" src="js/schoolSelect.js"></script>
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
                $('#myModal').modal("show", true);
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
                } 
                else {
                    $('#upload').submit();
                }
            }

            var width;

            function getSize(img)
            {
                if (typeof (img) != 'object')
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
                        <li id="l1"><a href="#basicInfo" data-toggle="tab">修改基本资料<i class="icon-chevron-right pull-right"></i></a></li>
                                <s:if test="#session.userType=='teacher'">
                            <li id="l5"><a href="#detailInfo" data-toggle="tab">修改详细资料<i class="icon-chevron-right pull-right"></i></a></li>
                                </s:if>
                        <li id="l2" ><a href="#modifyPassword" data-toggle="tab">修改密码<i class="icon-chevron-right pull-right"></i></a></li>
                        <li id="l3" ><a href="#headpic" data-toggle="tab">修改头像<i class="icon-chevron-right pull-right"></i></a></li>
                                <s:if test="#session.userType=='student'">
                            <li id="l4"><a href="#personIntro" data-toggle="tab">修改个人介绍<i class="icon-chevron-right pull-right"></i></a></li>
                                </s:if>                  
                    </ul>
                </div>
                <div class="span8 module" style="padding:12px;">
                    <div class="tab-content">
                        <div class="tab-pane fade" id='basicInfo'>
                            <s:form action="changeInfo.action" cssClass="form-horizontal">
                                <s:if test="#session.userType == 'teacher'">
                                    <h3>老师您好，修改你的详细信息吧^ ^</h3>
                                    <hr/>
                                    <div class="control-group">
                                        <label class="control-label" for="TAName"><strong>姓名*</strong></label>
                                        <div class="controls">
                                            <s:textfield id="TAName" cssClass="span2" onchange="validate_required(this,name_tip); validate_lengthLimit(this, name_tip, 8);" type="text" name="name" placeholder="请输入您的大名" value="%{tea.name}" autofocus="autofocus" />
                                            <span id="name_tip" class="validateTip" style="text-align: left;"></span>
                                            <span id="name_tip2" class="validateTip" style="text-align: left;"></span>
                                            <br/>
                                            <small class="text-warning">请填写真实姓名，须与提现银行卡开户名一致</small>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="phoneNum"><strong>手机*</strong></label>
                                        <div class="controls">
                                            <s:textfield id="phoneNum" cssClass="span2" onchange="validate_phoneNum(this,phone_tip);" type="text" name="tel" placeholder="请输入您的手机号" value="%{tea.tel}"/>
                                            <span id="phone_tip" class="validateTip" style="text-align: left;"></span>
                                            <br/>
                                            <small class="text-warning">请填写有效手机号，以便学生与您取得联系</small>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="gender"><strong>性别</strong></label>
                                        <div class="controls" id="gender">
                                            <div style="display:none;"><s:radio list="{'男', '女'}" name="sex" value="%{tea.sex}"/></div>
                                            <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                <button id="maleButton" type="button" value="0" class="btn" data-toggle="button">男</button>
                                                <button id="femaleButton" type="button" value="1" class="btn" data-toggle="button">女</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="datepicker"><strong>生日</strong></label>
                                        <div class="controls">
                                            <s:textfield cssClass="span2" type="text" id="datepicker" name="birthday" readOnly="readOnly" placeholder="请输入您的生日" value="%{tea.birthday}"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="place"><strong>所在地</strong></label>
                                        <div class="controls" id="place">
                                            <s:select id="selProvince" cssClass="span2" name="province" list="{ }" value="%{tea.province}"></s:select>
                                            <s:select id="selCity" cssClass="span2" name="city" list="{ }" value="%{tea.city}"></s:select>
                                            <s:select id="selDistrict" cssClass="span2" name="district" list="{ }" value="%{tea.district}"></s:select>
                                            <input type="hidden" id="txtProvince" value="<s:property value="tea.province"/>"/>
                                            <input type="hidden" id="txtCity" value="<s:property value="tea.city"/>"/>
                                            <input type="hidden" id="txtDistrict" value="<s:property value="tea.district"/>"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="currentRole"><strong>目前身份</strong></label>
                                        <div class="controls">
                                            <s:select id="currentRole" cssClass="span2" name="studyStatus" value="%{tea.studyStatus}" list="{'在职教师','大专学生','大一学生','大二学生','大三学生','大四学生','在读硕士','在读博士','海归/外教','其他'}"></s:select>
                                            </div>
                                        </div>
                                    <s:if test="@com.haijiao.global.config@domain == 0">
                                        <div class="control-group">
                                            <label class="control-label" for="college"><strong>大学</strong></label>
                                            <div class="controls">
                                                <s:textfield id="schoolSelect" cssClass="span4" name="school" value="%{tea.school}" placeholder="请输入您就读的大学"/>
                                            </div>
                                        </div>
                                    </s:if>
                                    <s:elseif test="@com.haijiao.global.config@domain == 1">
                                        <s:textfield id="schoolSelect" cssStyle="display:none;" name="school" value="上海交通大学"/>
                                    </s:elseif>
                                    <div class="control-group">
                                        <label class="control-label" for="major"><strong>专业</strong></label>
                                        <div class="controls">
                                            <s:textfield id="major" cssClass="span2" type="text" name="major" placeholder="请输入您就读的专业" value="%{tea.major}"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="major"><strong>生源地</strong></label>
                                        <div class="controls">
                                            <s:textfield id="origin" cssClass="span2" type="text" name="origin" placeholder="请输入您高中毕业地（生源地）" value="%{tea.origin}"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="network"><strong>网络环境</strong></label>
                                        <div class="controls">
                                            <s:select id="network" cssClass="span2" name="net" list="{'电信', '联通(网通)', '教育网', '移动(铁通)', '有线通', '其他'}" value="%{tea.net}"/>
                                            <br /><small class="text-warning">同样网络下视频效果更佳，请选择常用网络环境</small>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <div class="controls">
                                            <input class="btn btn-primary" type="button" value="提交" onclick="testSubmit();"/>
                                            <s:submit id="form_submit" cssClass="btn btn-primary" style="display:none;" value="提交" method="teacherChange"/>
                                        </div>
                                    </div>
                                </s:if>
                                <s:elseif test="#session.userType == 'student'">
                                    <h3>同学您好，修改你的详细信息吧^ ^</h3>
                                    <hr/>
                                    <div class="control-group">
                                        <label class="control-label" for="TAName"><strong>姓名</strong></label>
                                        <div class="controls">
                                            <s:textfield id="TAName" cssClass="span4" onchange="validate_required(this,name_tip); validate_lengthLimit(this, name_tip2, 8);" type="text" name="name" placeholder="请输入您的大名" value="%{stu.name}" autofocus="autofocus" />
                                            <dd id="name_tip" class="validateTip" style="text-align: left;"></dd>
                                            <dd id="name_tip2" class="validateTip" style="text-align: left;"></dd>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for=""><strong>性别</strong></label>
                                        <div class="controls">
                                            <dd style="display:none;"><s:radio list="{'男', '女'}" name="sex" value="%{stu.sex}"/></dd>
                                            <dd>
                                                <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                    <button id="maleButton" type="button" value="0" class="btn" data-toggle="button">男</button>
                                                    <button id="femaleButton" type="button" value="1" class="btn" data-toggle="button">女</button>
                                                </div>
                                            </dd>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for=""><strong>生日</strong></label>
                                        <div class="controls">
                                            <dd><s:textfield cssClass="span4" type="text" id="datepicker" name="birthday" placeholder="请输入您的生日" value="%{stu.birthday}"/></dd>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="school"><strong>学校</strong></label>
                                        <div class="controls">
                                            <dd><s:textfield cssClass="span4" id="school" type="text" name="school" placeholder="请输入您就读的学校" value="%{stu.school}"/></dd>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="grade"><strong>年级</strong></label>
                                        <div class="controls">
                                            <dd><s:select cssClass="span4" id="grade" headerValue="请选择你的年级" name="grade" list="{'小学', '六年级', '初一', '初二', '初三', '高一', '高二', '高三'}" value="%{stu.grade}"/></dd>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="phoneNum"><strong>手机</strong></label>
                                        <div class="controls">
                                            <dd><s:textfield cssClass="span4" type="text" id="phoneNum" name="tel" placeholder="请输入您的手机号" value="%{stu.tel}"/></dd>  
                                            <dd style="display: none;"><s:radio list="{'student', 'parent'}" name="telType" value="%{stu.telType}"/></dd>
                                            <br/>
                                            <dd>
                                                <div class="btn-group" data-toggle-name="is_private" data-toggle="buttons-radio">
                                                    <button id="meButton" type="button" value="0" class="btn" data-toggle="button">我的手机</button>
                                                    <button id="parentButton" type="button" value="1" class="btn" data-toggle="button">家长的手机</button>
                                                </div>
                                            </dd>
                                            <span id="phone_tip" class="validateTip" style="text-align: left;"></span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="place"><strong>所在地</strong></label>
                                        <div class="controls" id="place">
                                            <s:select id="selProvince" cssClass="span2" name="province" list="{ }" value="%{tea.province}"></s:select>
                                            <s:select id="selCity" cssClass="span2" name="city" list="{ }" value="%{tea.city}"></s:select>
                                            <s:select id="selDistrict" cssClass="span2" name="district" list="{ }" value="%{tea.district}"></s:select>
                                            <input type="hidden" id="txtProvince" value="<s:property value="tea.province"/>"/>
                                            <input type="hidden" id="txtCity" value="<s:property value="tea.city"/>"/>
                                            <input type="hidden" id="txtDistrict" value="<s:property value="tea.district"/>"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="network"><strong>网络环境</strong></label>
                                        <div class="controls">
                                            <s:select id="network" cssClass="span2" name="net" list="{'电信', '联通(网通)', '教育网', '移动(铁通)', '有线通', '其他'}" value="%{tea.net}"/>
                                            <br /><small class="text-warning">同样网络下视频效果更佳，请选择常用网络环境</small>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <div class="controls">
                                            <input class="btn btn-primary" style="width:300px;" type="button" value="提交" onclick="testSubmit();"/>
                                            <s:submit id="form_submit" cssClass="btn btn-primary" style="width:300px; display:none;" value="提交" method="studentChange"/>
                                        </div>
                                    </div>
                                </s:elseif>
                            </s:form>
                        </div>
                        <div class="tab-pane fade"  id='detailInfo'>
                            <s:form action="changeInfo.action">
                                <h3>修改详细资料</h3>
                                <hr/>
                                <dl>
                                    <dt>授课方式<small class="text-warning">*请务必选择一种授课方式，否则学生无法预约</small></dt>
                                    <dd style="margin-bottom: 5px; margin-top: 5px;">
                                        <s:checkbox name="sprtOnline" value="%{tea.sprtOnline}" cssStyle="margin-top:-5px"/> 线上授课
                                        <s:checkbox name="sprtTUnderline" value="%{tea.sprtTUnderline}" cssStyle="margin-top:-5px"/> 老师上门
                                        <s:checkbox name="sprtSUnderline" value="%{tea.sprtSUnderline}" cssStyle="margin-top:-5px"/> 学生上门
                                    </dd>
                                    <dt>线下授课区域<small class="text-warning">*选择“老师上门”和“学生上门”请填写该项</small></dt>
                                    <dd><s:textarea cssStyle="width:520px;height:50px;" cssClass="span5" name="underlineArea" value="%{tea.underlineArea}" autofocus="autofocus"/></dd>
                                    <dt>个人简介</dt>
                                    <dd><s:textarea cssStyle="width:520px;height:150px;" cssClass="span5" name="intro" value="%{tea.intro}" autofocus="autofocus"/></dd>
                                    <!--                                    <dt>获奖证书</dt>
                                                                        <dd><s:textarea cssStyle="width:520px;height:150px;" cssClass="span5" name="cert" value="%{tea.cert}" autofocus="autofocus"/></dd>
                                                                        <dt>家教经历</dt>
                                                                        <dd><s:textarea cssStyle="width:520px;height:150px;" cssClass="span5" name="experience" value="%{tea.experience}" autofocus="autofocus"/></dd>-->
                                    <dd><s:submit cssClass="btn btn-primary pull-right" value="提交" method="teacherMoreChange"/></dd>
                                </dl>
                            </s:form>
                        </div>
                        <div class="tab-pane fade"  id='modifyPassword'>
                            <s:form action="changeInfo.action" cssClass="form-horizontal">
                                <h3>修改密码</h3>
                                <hr/>
                                <div class="control-group">
                                    <label class="control-label" for="oldpwd"><strong>旧密码</strong></label>
                                    <div class="controls">
                                        <s:password cssClass="span4" type="text" id="oldpwd" name="oldpwd" placeholder="请输入旧密码" autofocus="autofocus"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="newpwd"><strong>新密码</strong></label>
                                    <div class="controls">
                                        <s:password cssClass="span4" onchange="validate_passwordlength(this,password_tip1);" type="text" id="newpwd" name="newpwd" placeholder="请输入新密码" autofocus="autofocus"/>
                                        <dd id="password_tip1" class="validateTip" style="text-align: left;"></dd>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="newpwd2"><strong>确认密码</strong></label>
                                    <div class="controls">
                                        <s:password cssClass="span4" onchange="validate_passwordequal(newpwd,this,password_tip2);" type="text" id="newpwd2" name="newpwd2" placeholder="请再输入一次新密码" autofocus="autofocus"/>
                                        <dd id="password_tip2" class="validateTip" style="text-align: left;"></dd>
                                    </div>
                                </div>
                                <br/>
                                <div class="control-group">
                                    <div class="controls">
                                        <s:submit cssClass="btn btn-primary" style="width:300px;" value="提交" method="changePassword"/>
                                    </div>
                                </div>
                            </s:form>
                        </div>
                        <div class="tab-pane fade"  id='headpic'>
                            <h3>修改头像</h3>
                            <hr/>
                            <p style="font-size: 9px;">请先选择图片上传，再在上传图片中截取作为头像的部分，按上传文件完成上传。<br/>
                            <div id="pic_tip1">注意①：请确保图片小于2MB<br/></div>
                            <div id="pic_tip2">注意②：目前只支持上传JPG类型的图片哦</div></p>

                            <s:if test="#session.userType=='student'">
                                <img id="pic_org" src="<s:property value="stu.pic.content"/>" style="height: 230px;width: 230px;"/>
                            </s:if>
                            <s:if test="#session.userType=='teacher'">
                                <img id="pic_org" src="<s:property value="tea.pic.content"/>" style="height: 230px;width: 230px;"/>
                            </s:if>
                            <s:form action="uploadPic" id="upload" enctype="multipart/form-data">
                                <s:textfield id="pic_x" name="x" style="display:none;"/>
                                <s:textfield id="pic_y" name="y" style="display:none;"/>
                                <s:textfield id="pic_rate" name="rate" style="display:none;"/>
                                <s:textfield id="pic_w" name="w" style="display:none;"/>
                                <s:textfield id="pic_h" name="h" style="display:none;"/>
                                <s:file name="upload" title="选择文件" id="fileid" onchange="preImg(this.id,preimg);"/>

                                <div id="myModal" class="modal hide fade">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <h3>预览</h3>
                                    </div>
                                    <div class="modal-body">
                                        <div id="pre_area" style="display:none;">
                                            <div id="preview" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);">
                                                <img id="preimg" onload="getSize(this)"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <a href="#" class="btn" data-dismiss="modal">关闭</a>
                                        <button type="button" id="uploadButton" class="btn disabled" data-toggle="button" onclick="checkSubmit();" >上传文件</button>
                                    </div>
                                </div>
                            </s:form>
                            <div class="progress"><div class="bar"></div></div>
                        </div>
                        <div class="tab-pane fade"  id='personIntro'>
                            <h3>修改主页介绍</h3>
                            <hr/>
                            <s:form action="changeIntro.action">
                                <s:textarea cssStyle="height:400px" name="intro" value="%{stu.intro}" autofocus="autofocus" cssClass="span7"/>
                                <s:submit cssClass="btn btn-primary btn-small pull-right" value="提交"/>
                            </s:form>
                        </div>
                        <div>
                            <s:if test="tab=='detail' && #session.userType=='teacher'">
                                <script>
                                    $("#detailInfo").addClass("active in");
                                    $("#l5").addClass("active");
                                </script>
                            </s:if>
                            <s:elseif test="tab=='password'">
                                <script>
                                    $("#modifyPassword").addClass("active in");
                                    $("#l2").addClass("active")
                                </script>
                            </s:elseif>
                            <s:elseif test="tab=='head'">
                                <script>
                                    $("#headpic").addClass("active in");
                                    $("#l3").addClass("active")
                                </script>
                            </s:elseif>
                            <s:elseif test="tab=='person' && #session.userType=='student'">
                                <script>
                                    $("#personIntro").addClass("active in");
                                    $("#l4").addClass("active")
                                </script>
                            </s:elseif>
                            <s:else>
                                <script>
                                    $("#basicInfo").addClass("active in");
                                    $("#l1").addClass("active")
                                </script>
                            </s:else>
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
