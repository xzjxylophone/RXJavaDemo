<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/common.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="cn.yiyizuche.common.ou.department.entity.Department"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="${ctx}/css/login.css"/>
<link rel="stylesheet" href="${ctx}/css/laypage.css"/>
<script type="text/javascript" src="${ctx}/js/common/laypage.js"></script>
<script type="text/javascript" src="${ctx}/js/ou/employee.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
	var departments = '<%=new ArrayList<Department>()%>';
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>
</head>

<body>

	<!--main-begin-->
	<div>
	<div class="index_right fl">
		<div class="index_right_main">
			<div class="main_header">
				<div class="manage-tit clearfix">
					<span class="fl">员工管理</span>
					<span class="fr">系统管理&lt;&lt;员工管理</span>
				</div>
			</div>
			<div class="main_bottom">
				<div class="manage-tab">
					<span id="listEmployee_span_id" class="manage-active" onclick="listEmployee_span_onclick()">员工列表</span>
					<span id="addEmployee_span_id" class="manage-active" onclick="addEmployee_span_action();">员工添加</span>
					<span id="editEmployee_span_id" class="manage-active" style="display:none"></span>
					<span id="detailEmployee_span_id" class="manage-active" style="display:none"></span>
				</div>
				<div class="manage-tent">
					<div id="listEmployee_div_id">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">登录名:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="userName_input_id">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">姓名:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="realName_input_id"  onkeyup="if(event.keyCode==13){refreshEmployeeList()}">
								</div>
								<br><br>
								<label class="col-md-1 col-sm-1 control-label manage-lab lable-style2" style="margin-top: 10px">部门：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<select id="dep_select_id" class="form-control manage-top20">
									</select>
								</div>
								<button class="btn manage-sea manage-top20" type="button" onclick="searchEmployee_span_onclick()">查询</button>
							</div>
						</form>


						<div class="absent-opera absent-table employee-gyhu">
							<table id="grid-data" class="table table-condensed table-hover table-striped">
								<thead>
									<tr>
										<th data-column-id="userRealName">姓名</th>
										<th data-column-id="depName">部门</th>
										<th data-column-id="mobile">手机号</th>
										<th data-column-id="email">邮箱</th>
										<th data-column-id="link" data-formatter="link">操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>


					<!--员工添加start-->
					<div class="manage-none" id="addEmployee_div_id">
						<form class="form-horizontal" method="post" id="addEmployee_form_id" >
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">部门：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-top10">
									<select id="addDep_select_id" class="form-control">
									</select>
								</div>
								
								
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>用户：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-top10">
									<select id="addUser_select_id" class="form-control">
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>工号：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="number" class="form-control" id="addJobNumber_input_id" required data-max="5" data-min="1">
								</div>
								
								<label class="col-md-1 col-sm-1 control-label manage-lab">参加工作：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-fat">
									<input type="text" class="form-control" name="" id="addWorkTime_input_id" readonly="readonly" onclick="WdatePicker({el:'addWorkTime_input_id'})" placeholder="请选择日期">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">入职时间：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-fat">
									<input type="text" class="form-control" name="" id="addEntryTime_input_id" readonly="readonly" onclick="WdatePicker({el:'addEntryTime_input_id'})" placeholder="请选择日期">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">家庭地址：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="addAddress_input_id" data-max="20">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">户籍地址：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="addRegister_input_id" data-max="20">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">身份证号：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="idcard" class="form-control" id="addIdNumber_input_id" data-max="18">
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">应休年假：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="number" class="form-control" id="addAnnualVacate_input_id" min="0" step="1" max="15">
								</div>
								<span>天</span>
							</div>
							
							<!-- <div class="form-group"> -->
							
								
								<!-- <label class="col-md-1 col-sm-1 control-label manage-lab">是否启用：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-top10">
									<input type="radio" name="addEmployeeStatus_input_name" id="addEnable_input_id" value="0"
										checked="checked" />启用<div class="nbsp-hh"></div><input type="radio"
										name="addEmployeeStatus_input_name" id="addDisable_input_id" value="1" />禁用
								</div> -->
							<!-- </div> -->
							<button class="btn manage-save manage-left280" type="button" onclick="saveAddEmployee_button_onclick()">保存</button>
							<button class="btn manage-cancel" type="button" onclick="cancelAddEmployee_button_onclick()">取消</button>
						</form>
					</div>
					<!--员工添加end-->
					
					
					<!--员工编辑start-->
					<div class="manage-none" id="editEmployee_div_id">
						<form class="form-horizontal" method="post" id="editEmployee_form_id" >
							<div class="form-group">
								<input type="hidden" name="id" id="editId_input_id"/>
								<label class="col-md-1 col-sm-1 control-label manage-lab">部门：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<select id="editDep_select_id" class="form-control">
									</select>
								</div>
								
								
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>用户：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
										<select id="editUser_select_id" class="form-control">
										      
										</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>工号：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="number" class="form-control" id="editJobNumber_input_id" required data-max="5" data-min="1">
								</div>
								<%--<label class="col-md-1 col-sm-1 control-label manage-lab">职务：</label>--%>
								<%--<div class="col-md-2 col-sm-2 manage-wid">--%>
									<%--<input type="text" class="form-control" id="editDuty_input_id">--%>
								<%--</div>--%>
								<label class="col-md-1 col-sm-1 control-label manage-lab">参加工作：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-fat">
									<input type="text" class="form-control" name="" id="editWorkTime_input_id" readonly="readonly" onclick="WdatePicker({el:'editWorkTime_input_id'})" placeholder="请选择日期">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">入职时间：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-fat">
									<input type="text" class="form-control" name="" id="editEntryTime_input_id" readonly="readonly" onclick="WdatePicker({el:'editEntryTime_input_id'})" placeholder="请选择日期">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">家庭地址：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="editAddress_input_id" data-max="20">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">户籍地址：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="editRegister_input_id" data-max="20">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">身份证号：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="idcard" class="form-control" id="editIdNumber_input_id" data-max="18">
								</div>
							</div>
							<!-- <div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">是否启用：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-top10">
									<input type="radio" name="editEmployeeStatus_input_name" id="editEnable_input_id" value="0" checked="checked" />启用<div class="nbsp-hh"></div>
								   	<input type="radio" name="editEmployeeStatus_input_name" id="editDisable_input_id" value="1" /> 禁用
								</div>
							</div> -->
							<button class="btn manage-save manage-left280" type="button" onclick="saveEditEmployee_button_onclick()">保存</button>
							<button class="btn manage-cancel" type="button" onclick="cancelEditEmployee_button_onclick()">取消</button>
						</form>
					</div>
					<!--员工编辑end-->
					
					
					<!--员工详情start-->
					<div class="manage-none" id="detailEmployee_div_id">
						<form class="form-horizontal" method="post" id="detailEmployee_form_id">
							<div class="form-group">
								
								<label class="col-md-1 col-sm-1 control-label manage-lab">部门：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailDepName_input_id" readonly="readonly">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">姓名：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailUserRealName_input_id" readonly="readonly">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">手机号码：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailMobile_input_id" readonly="readonly">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">邮箱：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailEmail_input_id" readonly="readonly">
								</div>
							</div>
							
							
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">性别：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailSex_input_id" readonly="readonly">
								</div>
								
								
								
								<label class="col-md-1 col-sm-1 control-label manage-lab">工号：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailJobNumber_input_id" readonly="readonly">
								</div>
							</div>
							
							
							<div class="form-group">
								
								<label class="col-md-1 col-sm-1 control-label manage-lab">参加工作：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailWorkTime_input_id" readonly="readonly">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">入职时间：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailEntryTime_input_id" readonly="readonly">
								</div>
							</div>
							
							<div class="form-group">
								
								
								<label class="col-md-1 col-sm-1 control-label manage-lab">家庭住址：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailAddress_input_id" readonly="readonly">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">户籍地址：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailRegister_input_id" readonly="readonly">
								</div>
								
							</div>
							
							
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">身份证号：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailIdNumber_input_id" readonly="readonly">
								</div>
							</div>

							<button class="btn manage-cancel manage-left295" type="button" onclick="cancelDetailEmployee_button_onclick()">返回</button>
						</form>
					</div>
					<!--员工详情end-->
					
					
				</div>
			</div>
		</div>
	</div>
	</div>
	<!--main-end-->








</body>





<script type="application/javascript">

	$("#addAnnualVacate_input_id").val("5");
	// 这个是需要在最前面
	loadDepartmentList();
	loadEmployeePage();
	showEmployeeListTab();
</script>


</html>