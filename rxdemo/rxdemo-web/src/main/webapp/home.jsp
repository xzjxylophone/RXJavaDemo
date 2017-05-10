<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>壹壹在线办公系统</title>
<script src="${ctx}/js/login/login.js"></script>
<script type="text/javascript">
	$(function(){
		//判断是否是用户生日
		if(date2Str(sessionBirthday, "MM-dd") == new Date().Format("MM-dd")){
			$("#welcomDiv").hide();
			$("#birthdateDiv").show();
           	timedCount();
		}
		if(sessionUserId==undefined || sessionUserId=="undefined" || sessionUserId==""){
			window.location.href=" <c:url value="/login.jsp"/>";
		}
	});

    /**
     * happy生日快乐音频视频循环播放
     */
    function timedCount() {
        $('#audio-play').attr('autoplay','autoplay');
        $('#happy-img').attr('src', 'images/new-happy.jpg');
        setTimeout("timedCount()", 8000);
    }
</script>
</head>
<body>
	<!--header-start-->
	<div class="index_header">
		<div class="fl">
			<span class="ihf_1"><img src="${ctx }/images/s9.png" width="42" height="42"/></span>
			<span class="ihf_2">你好，<i>
			<%	if(!_userId.equals("")){ %>
				<c:out value="${_userRealName}"></c:out>
			<% }%>
			</i></span>
			<span class="ihf_3">当前时间：<i  id="base-block"></i></span>
		</div>
		<div class="fr">
			<span id='changePwd' class="ihr_1" onclick="showSetPwd();"><i></i>修改密码</span>
			<span id='logout' class="ihr_2" onclick="logout();">退出<i></i></span>
		</div>
	</div>
	<!--header-end-->
	
	<!--main-start-->
	<div class="index_mad">
		<div class="index_left fl">
			<a href="#"><img src="${ctx }/images/s6.png" alt="">首页</a>
			<a href="${ctx}/mainPage.do"><img src="${ctx }/images/s5.png" alt="">导航菜单</a>
		</div>
		<div class="index_right fl col-sm-12" id="welcomDiv">
			<h1>welcome</h1>
			<h2>欢迎使用壹壹在线办公管理系统</h2>
			<img src="${ctx }/images/s4.png" alt="">
		</div>
		<div class="index_right fl col-sm-12" style="display: none;" id="birthdateDiv">
			<%--生日部分，用百分比来显示图片的大小--%>
			<%--<img src="${ctx }/images/happy_Birthday.jpg" alt="">--%>
				<img src="${ctx }/images/new-happy.jpg" id="happy-img"/>
				<audio src="${ctx }/images/happy-birthday.mp3" preload="auto" id="audio-play" loop="loop"></audio>
		</div>
		<img class="index_footer" src="${ctx }/images/s21.png" width="100%" height="56"style="margin-top: 35px;"/>
	</div>
	<!--main-end-->
	<script type="text/javascript" src="${ctx }/js/login/index.js" ></script>

</body>
</html>