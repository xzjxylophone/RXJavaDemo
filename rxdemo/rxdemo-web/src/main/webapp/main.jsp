<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>壹壹在线办公系统</title>
<script type="text/javascript">var yyzc_pro_context = '<%= request.getContextPath() %>';</script>
<script type="text/javascript">
	$(function(){
		if(sessionUserId==undefined || sessionUserId=="undefined" || sessionUserId==""){
			window.location.href=" <c:url value="/login.jsp"/>";
		}
		$.ajax({
			type : 'POST',
			url :  yyzc_pro_context+'/loadMenu.do',
			data :{
				userId : sessionUserId
			},
			dataType : 'json',
			success : function(data) {
				if(data != null){
					 initeMenu(data);
				}
			},
		});
	}) 
	
</script>
</head>
<body>
	<!--header-start-->
	<div class="index_header">
		<div class="fl">
			<span class="ihf_1"><img src="${ctx }/images/s9.png" width="42" height="42"/></span>
			<span class="ihf_2">你好，
				<i>
					<%	if(!_userId.equals("")){ %>
						<c:out value="${_userRealName}"></c:out>
					<% }%>
				</i>
			</span>
			<span class="ihf_3">当前时间：<i  id="base-block"></i></span>
		</div>
		<div class="fr">
			<span class="ihr_1" onclick="showSetPwd();"><i></i>修改密码</span>
			<span class="ihr_2" onclick="logout();">退出<i></i></span>
		</div>
	</div>
	<!--header-end-->

	
	<!--main-start-->
	<div class="index_main clearfix" id="yy_manager">
		<div class="index_left fl">
			<a href="#" onclick="toHome();" class="index_homepage">
				<i></i>
				<span onclick="toHome();">首页</span>
			</a>
			<a class="index_navmenu">
				<i></i>
				<span>导航菜单</span>
				<s class="fr"></s>
			</a>
			<!--导航部分start-->
			<div id="menuDivId"></div>
			<!--导航部分end-->
		</div>
		<div class="index_right fl yy_height50 yy_height25" id="yy_iframe">
			<iframe width="100%" scrolling="no" height="154%"
				frameborder="false" allowtransparency="true"
				src="${ctx}/welcomePage.do"
				name="right" id="rightName">
			</iframe>
		</div>
		<img class="index_footer" src="${ctx }/images/s21.png" width="100%" height="56"/>
	</div>
	<!--main-end-->
	<script type="text/javascript" src="${ctx }/js/login/index.js" ></script>
	<script type="text/javascript" src="${ctx }/js/login/login.js" ></script>
</body>
</html>