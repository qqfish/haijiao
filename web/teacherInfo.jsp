<%-- 
    Document   : test
    Created on : 2013-3-4, 21:08:31
    Author     : Jerry
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <s:form action="changeTeacherInfo">
            <s:submit value="修改基本信息"></s:submit>
        </s:form>
        姓名：<s:property value="tea.name" /> <br/>
        简单介绍：<s:property value="tea.brief_intro" /> <br/>
        每小时收费：<s:property value="tea.wagePerhour" /> <br/>
        是否接受试听：<s:property value="tea.audition" />
        <s:form action="changeAudition">
            <s:submit value="改变试听状态"></s:submit>
        </s:form>
        现有智慧币：<s:property value="tea.coin" /> <br/>
        介绍视频地址：<s:property value="t·ea.videoUrl" /> <br/>
        时刻表：<br/>
        <s:iterator value="tea.schedule.slices" id="s">
            ********<br/>
            ** 星期<s:property value="day"/> <br/>
            ** 开始时间<s:property value="begintime"/> <br/>
            ** 结束时间<s:property value="endtime"/> <br/>
            ** 状态：<s:property value="type"/> <br/>
        </s:iterator>
        <s:form action="changeSchedule">
            <!--此处通过某种方式修改时刻表。。。-->
            <s:submit value="修改时刻表"></s:submit>
        </s:form>
    </body>
</html>
