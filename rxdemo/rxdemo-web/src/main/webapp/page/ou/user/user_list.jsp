<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="${ctx}/css/login.css"/>
<link rel="stylesheet" href="${ctx}/css/laypage.css"/>
<script type="text/javascript" src="${ctx}/js/common/laypage.js"></script>
<script type="text/javascript" src="${ctx}/js/ou/user.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
<!--重置密码start-->
<div class="absent-layer-changeP absent-layer" id="resetPwd_div_id" style="display: none;">
	<div class="absent-clickbox table-responsive">
		<h2>重置密码</h2>
		<input type="hidden" name="id" id="resetPwdUserId_input_id"/>
		<form action="" method="post" id="resetPwdForm">
			<div class="form-group">
				<label for="resetPwd_input_id" class="col-md-5 col-sm-5 control-label manage-lab"><span style="color: red;">*</span>新密码：</label>
				<input type="password" class="form-control" id="resetPwd_input_id" required/>
			</div>
			<div class="form-group">
				<label for="resetPwdAgain_input_id" class="col-md-5 col-sm-5 control-label manage-lab"><span style="color: red;">*</span>确认新密码：</label>
				<input type="password" class="form-control" id="resetPwdAgain_input_id" required/>
			</div>
		</form>
		<div class="absent-con">
			<button class="btn absent-addto manage-cancel " type="button" onclick="okResetPwd_button_onclick()">确定</button>
			<button class="btn manage-cancel year-changeP-cancel" type="button" onclick="cancelResetPwd_button_onclick()">取消</button>
		</div>
	</div>		
</div>
<!-- 重置密码over -->

<!--分配角色权限start-->
<div class="absent-layer-changeP absent-layer" id="setRoleForUser_div_id" style="display: none;">
	<div class="absent-clickbox role-user">
		<table class="role-check table table-bordered table-condensed">
			<thead>
				<h2>分配角色权限</h2>
			</thead>
			<tbody id="roleCheckbox_tbody_id">
			</tbody>
		</table>
		<div class="manage-left170">
			<button class="btn absent-addto manage-cancel " type="button" onclick="saveRoleFroUser_button_onclick()">确定</button>
			<button class="btn manage-cancel year-changeP-cancel" type="button" onclick="closeRoleFroUser_button_onclick()">取消</button>
		</div>
	</div>
</div>
<!--分配用户end-->



	<!--main-begin-->
	<div class="index_right fl">
		<div class="index_right_main">
			<div class="main_header">
				<div class="manage-tit clearfix">
					<span class="fl">用户管理</span>
					<span class="fr">系统管理&lt;&lt;用户管理</span>
				</div>
			</div>
			<div class="main_bottom">
				<div class="manage-tab">
					<span id="listUser_span_id" class="manage-active" onclick="listUser_span_onclick()">用户列表</span>
					<span id="addUser_span_id" class="manage-active" onclick="addUser_span_onclick();">用户添加</span>
					<span id="editUser_span_id" class="manage-active" style="display:none"></span>
					<span id="detailUser_span_id" class="manage-active" style="display:none"></span>
				</div>
				<div class="manage-tent">
					<!--用户列表start-->
					<div id="listUser_div_id">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">登录名:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="userName_input_id">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">姓名:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="realName_input_id" onkeyup="if(event.keyCode==13){refreshUserList()}">
								</div>
								<button class="btn manage-sea" type="button" onclick="search_button_onclick()">查询</button>
							</div>
						</form>


						<div class="absent-opera absent-table maxwid">
							<table id="grid-data" class="table table-condensed table-hover table-striped">
								<thead>
									<tr>
										<th data-column-id="userName">登录名</th>
										<th data-column-id="realName" data-width="150">姓名</th>
										<th data-column-id="mobile">手机号</th>
										<th data-column-id="email">邮箱</th>
										<th data-column-id="status" data-formatter="status">状态</th>
										<th data-column-id="link" data-formatter="link" data-width="500">操作</th>
									</tr>
								</thead>

							</table>
						</div>
					</div>
					<!--用户列表end-->

					<!--用户添加start-->
					<div class="manage-none" id="addUser_div_id">
						<form class="form-horizontal" method="post" id="addUser_form_id" >
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>登录名：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="nickname" class="form-control" id="addUserName_input_id" required data-max="15">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>姓名：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="entityname" class="form-control" id="addRealName_input_id" required data-max="5">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>密码：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="password" class="form-control" id="addPwd_input_id" required data-min="6" data-max="20" value="123456">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>确认密码：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="password" class="form-control" id="addPwdAgain_input_id" required data-min="6" data-max="20" value="123456">
								</div>
							</div>
							<div class="form-group">
								<span style="color: red;margin-left: 120px">默认密码是:123456</span>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>性别：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-top10">
									<input type="radio" name="addUserSex_input_name" id="addMan_input_id" value="0"
										checked="checked" /> 男<div class="nbsp-hh"></div><input type="radio"
										name="addUserSex_input_name" id="addWoman_input_id" value="1" /> 女
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">出生日期：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-fat">
									<input type="text" class="form-control" name="" id="addBirthday_input_id" readonly="readonly" onclick="WdatePicker({el:'addBirthday_input_id'})" placeholder="请选择日期">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">手机号码：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="tel" class="form-control" id="addMobile_input_id">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">邮箱：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="email" class="form-control" id="addEmail_input_id">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>是否启用：</label>
								<div class="col-md-2 col-sm-2 manage-wid  manage-top10">
									<input type="radio" name="addUserStatus_input_name" id="addEnable_input_id" value="0"
										checked="checked" /> 启用<div class="nbsp-hh"></div><input type="radio"
										name="addUserStatus_input_name" id="addDisable_input_id" value="1" /> 禁用
								</div>
								<input type="hidden" name="id" id="addUserRoleIds_input_id"/>
								<label class="col-md-1 col-sm-1 control-label manage-lab">选择角色：</label>
								<div class="col-md-2 col-sm-2 user-role-input">
									<input type="text" class="form-control cursor" id="addUserRole_input_id" onclick="addUserRole_input_id_onclick()" placeholder="请选择角色权限" readonly="readonly">
								</div>
							</div>
							<button class="btn manage-save manage-left280" type="button" onclick="saveAddUser_button_onclick()">保存</button>
							<button class="btn manage-cancel" type="button" onclick="cancelAddUser_button_onclick()">取消</button>
						</form>
					</div>
					<!--用户添加end-->
					
					
					<!--用户编辑start-->
					<div class="manage-none" id="editUser_div_id">
						<form class="form-horizontal" method="post" id="editUser_form_id">
							<input type="hidden" name="id" id="editUserId_input_id"/>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>登录名：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="nickname" class="form-control" id="editUserName_input_id" required data-max="30">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>姓名：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="entityname" class="form-control" id="editRealName_input_id" name="" required data-max="5">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>性别：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-top10">
									<input type="radio" name="editUserSex_input_name" id="editMan_input_id" value="0"
										checked="checked" /> 男<div class="nbsp-hh"></div><input type="radio"
										name="editUserSex_input_name" id="editWoman_input_id" value="1" /> 女
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">出生日期：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-fat">
									<input type="text" class="form-control" name="" id="editBirthday_input_id" readonly="readonly" onclick="WdatePicker({el:'editBirthday_input_id'})" placeholder="请选择日期">	
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">手机号码：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="tel" class="form-control" id="editMobile_input_id">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">邮箱：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="email" class="form-control" id="editEmail_input_id">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>是否启用：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-top10">
									<input type="radio" name="editUserStatus_input_name" id="editEnable_input_id" value="0"
										checked="checked" /> 启用<div class="nbsp-hh"></div><input type="radio"
										name="editUserStatus_input_name" id="editDisable_input_id" value="1" /> 禁用
								</div>
								<input type="hidden" name="id" id="editUserRoleIds_input_id"/>
								<label class="col-md-1 col-sm-1 control-label manage-lab">选择角色：</label>
								<div class="col-md-2 col-sm-2 user-role-input">
									<input type="text" class="form-control cursor" id="editUserRole_input_id" onclick="editUserRole_input_id_onclick()" placeholder="请选择角色权限" readonly="readonly">
								</div>
							</div>
							<button class="btn manage-save manage-left280" type="button" onclick="saveUpdateUser_button_onclick()">保存</button>
							<button class="btn manage-cancel" type="button" onclick="cancelUpdateUser_button_onclick()">取消</button>
						</form>
					</div>
					<!--用户编辑end-->
					
					
					<!--用户详情start-->
					<div class="manage-none" id="detailUser_div_id">
						<form class="form-horizontal" method="post" id="detailUser_form_id">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">登录名：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="" id="detailUserName_input_id" readonly="readonly">	
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">姓名：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="" id="detailRealName_input_id" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">性别：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="" id="detailSex_input_id" readonly="readonly">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">出生日期：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="" id="detailBirthdate_input_id" readonly="readonly">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">手机号码：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="" id="detailMobile_input_id" readonly="readonly">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">邮箱：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="" id="detailEmail_input_id" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">是否启用：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="" id="detailStatus_input_id" readonly="readonly">
								</div>
								<input type="hidden" name="id" id="detailUserRoleIds_input_id"/>
								<label class="col-md-1 col-sm-1 control-label manage-lab">角色：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailUserRole_input_id" onchange="rolDetal();" readonly="readonly">
								</div>
							</div>

								<button class="btn manage-cancel manage-left295" type="button" onclick="cancelDetailUser_button_onclick()">返回</button>
						</form>
					</div>
					<!--用户详情end-->

					
				</div>
			</div>
		</div>
	</div>
	<!--main-end-->



</body>


<script type="application/javascript">
	loadUserPage();
	showUserListTab();
</script>



</html>