<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/page/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>考勤</title>

<link href="${ctx }/css/login.css" rel="stylesheet" />
    <!--[if lt IE 9]>
      <script src="${ctx }/js/html5shiv.min.js"></script>
      <script src="${ctx }/js/respond.min.js"></script>
    <![endif]-->
    
</head>
<body>
<div class="log-in">
	<img src="${ctx }/images/s3.jpg" alt="">
	<div class="welcome">
		<h1>欢迎使用 | <small>OA管理系统</small></h1>
		<form role="form" id="loginForm">
			<div class="log-user">
				<label>用户名:</label>
				<div class="form-group">
	            	<input type="text" class="form-control" placeholder="请输入用户名" id="userName" required autocomplete="off">
	            </div>
            </div>
            <div class="log-password">
				<label>密&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
				<div class="form-group">
	            	<input type="password" class="form-control" placeholder="请输入密码" id="pwd" required autocomplete="off">
	            </div>
            </div>
            <div class="ar">
            	<%--<a href="">忘记密码?</a>--%>
            </div>
            <div class="log-btn">
            	<button class="btn btn-info" type="button" id="loginBtn" onclick="login();" >登录</button>
            </div>
			<div class="logo-llq">
				<div class="fl">
					<img src="${ctx }/images/hand.png" alt="">
					建议使用浏览器：
				</div>
				<div class="fr">
					<img src="${ctx }/images/firfox.png" alt="">
					<span>火狐v42版本以上</span>
					<img src="${ctx }/images/IE.png" alt="">
					<span>IE8+</span><br />
					<p>
						<img src="${ctx }/images/chrome.png" alt="">
						<span>谷歌v45版本以上</span>
					</p>

				</div>
			</div>
		</form>

	</div>
</div>
<script src="${ctx}/js/login/login.js"></script>
</body>
</html>