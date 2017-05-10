<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ include file="/page/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>考勤</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/login.css" rel="stylesheet" />
<link href="css/base.css" rel="stylesheet" />
    <!--[if lt IE 9]>
      <script src="js/html5shiv.min.js"></script>
      <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<script type="text/javascript">var yyzc_pro_context = '<%= request.getContextPath() %>';</script>

<body>
<div class="log-in">
	<img src="images/s3.jpg" alt="">
	<div class="welcome">
		<h1>修改密码</h1>
		<form role="form" style="height:395px;">
			<div class="log-old-pwd">
				<label>旧密码:</label>
				<div class="form-group">
	            	<input type="password" class="form-control" placeholder="请输入旧密码" id=oldPwdInput>
	            </div>
            </div>
			<div class="log-new-pwd">
				<label>新密码:</label>
				<div class="form-group">
	            	<input type="password" class="form-control" placeholder="请输入新密码" id=newPwdInput>
	            </div>
            </div>
            <div class="log-new-pwd-again">
				<label>新密码确认:</label>
				<div class="form-group">
	            	<input type="password" class="form-control" placeholder="请再次输入新密码" id=againNewPwdInput>
	            </div>
           </div>
            <div class="log-btn">
            	<button id='okButton' class="btn btn-info" type="button" onclick="changePwd();">修改密码</button>
            	
            	<script type='text/javascript'>
				document.getElementById('okButton').onclick=function(){
					var oldPwd =  $("#oldPwdInput").val();
					var newPwd =  $("#newPwdInput").val();
					var againNewPwd =  $("#againNewPwdInput").val();
					
					var result = false;
					var msg = "";
					
					if (oldPwd == null || oldPwd == undefined || oldPwd == "") {
						msg = "旧密码不能为空";
					} else if (newPwd == null || newPwd == undefined || newPwd == "") {
						msg = "新密码不能为空";
					} else if (newPwd != againNewPwd) {
						msg = "两次密码不一致";
					} else {
						result = true;
					}
					if (result == false) {
						alert(msg);
						return;
					}
					
					$.ajax({
						type : 'POST',
						url :  yyoa_context+'/changePwd.do',
						data :{
							oldPwd :  oldPwd,
							newPwd : newPwd
						},
						dataType : 'json',
						success : function(data) {
							if(data.state == false){
								alert(data.message);
							} else {
				                window.location.href = yyoa_context+'/homePage.do';
							}
						},
					});
				
				    
					
				}
			</script>
            	
            	
            	
            </div>
		</form>
	</div>
</div>

</body>
</html>

