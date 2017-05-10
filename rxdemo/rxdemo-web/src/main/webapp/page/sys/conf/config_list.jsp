<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="${ctx}/css/login.css"/>
<link rel="stylesheet" href="${ctx}/css/laypage.css"/>
<script type="text/javascript" src="${ctx}/js/common/laypage.js"></script>
<script type="text/javascript" src="${ctx}/js/sys/config.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统配置</title>
</head>
<body>
<!--main-begin-->
	<div class="index_right fl">
		<div class="index_right_main">
			<div class="main_header">
				<div class="manage-tit clearfix">
					<span class="fl">系统配置</span>
					<span class="fr">系统管理&lt;&lt;系统配置</span>
				</div>
			</div>
			<div class="main_bottom">
				<div class="manage-tab">
					<span id="listConfig_span_id" class="manage-active" onclick="listConfig_span_onclick()">系统配置列表</span>
					<span id="addConfig_span_id" class="manage-active" onclick="addConfig_span_onclick();">系统配置添加</span>
					<span id="editConfig_span_id" class="manage-active" style="display:none"></span>
					<span id="detailConfig_span_id" class="manage-active" style="display:none"></span>
				</div>
				<div class="manage-tent">
					<!--系统配置列表start-->
					<div id="listConfig_div_id">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">参数名:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="configName_input_id">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">参数键:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="configKey_input_id" onkeyup="if(event.keyCode==13){refreshConfigList()}">
								</div>
								<button class="btn manage-sea" type="button" onclick="search_button_onclick()">查询</button>
							</div>
						</form>


						<div class="absent-opera absent-table maxwid">
							<table id="grid-data" class="table table-condensed table-hover table-striped">
								<thead>
									<tr>
										<th data-column-id="confName">参数名称</th>
										<th data-column-id="confType" data-formatter="confType">参数类型</th>
										<th data-column-id="confKey">参数键</th>
										<th data-column-id="confValue">参数值</th>
										<th data-column-id="link" data-formatter="link">操作</th>
									</tr>
								</thead>

							</table>
						</div>
					</div>
					<!--系统配置列表end-->

					<!--系统配置添加start-->
					<div class="manage-none" id="addConfig_div_id">
						<form class="form-horizontal" method="post" id="addConfig_form_id" >
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>参数名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="addConfigName_input_id" required data-max="15">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>参数类型：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-text">
									<select name="punchIn" id="addConfigType_select_id" class="form-control" required>
										<option value="1">只读</option>
										<option value="2" selected="selected">可维护</option>
										<option value="3">可修改不可删除</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>参数键：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="entityname" class="form-control" id="addConfigKey_input_id" required data-min="6" data-max="20">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>参数值：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="addConfigValue_input_id" required data-min="6" data-max="20">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">参数描述：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<textarea class="form-control" rows="3"  name="remark" id="addConfigDesc_textarea_id" data-max="50"></textarea>
								</div>
							</div>
							<button class="btn manage-save manage-left280" type="button" onclick="saveAddConfig_button_onclick()">保存</button>
							<button class="btn manage-cancel" type="button" onclick="cancelAddConfig_button_onclick()">取消</button>
						</form>
					</div>
					<!--系统配置添加end-->
					
					
					<!--系统配置编辑start-->
					<div class="manage-none" id="editConfig_div_id">
						<form class="form-horizontal" method="post" id="editConfig_form_id">
							<input type="hidden" name="id" id="editConfigId_input_id"/>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>参数名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="editConfigName_input_id" required data-max="15">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>参数类型：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-text">
									<select name="punchIn" id="editConfigType_select_id" class="form-control" required>
										<option value="1">只读</option>
										<option value="2">可维护</option>
										<option value="3">可修改不可删除</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>参数键：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="entityname" class="form-control" id="editConfigKey_input_id" required data-min="6" data-max="20">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>参数值：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="editConfigValue_input_id" required data-min="6" data-max="20">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">参数描述：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<textarea class="form-control" rows="3"  name="remark" id="editConfigDesc_textarea_id" data-max="50"></textarea>
								</div>
							</div>
							<button class="btn manage-save manage-left280" type="button" onclick="saveUpdateConfig_button_onclick()">保存</button>
							<button class="btn manage-cancel" type="button" onclick="cancelUpdateConfig_button_onclick()">取消</button>
						</form>
					</div>
					<!--系统配置编辑end-->
					
					
					<!--系统配置详情start-->
					<div class="manage-none" id="detailConfig_div_id">
						<form class="form-horizontal" method="post" id="detailConfig_form_id">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">参数名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailConfigName_input_id" required data-max="15" readonly="readonly">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">参数类型：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-text">
									<input type="entityname" class="form-control" id="detailConfigType_input_id" required data-min="6" data-max="20" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">参数键：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="entityname" class="form-control" id="detailConfigKey_input_id" required data-min="6" data-max="20" readonly="readonly">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">参数值：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detailConfigValue_input_id" required data-min="6" data-max="20" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">参数描述：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<textarea class="form-control" rows="3"  name="remark" id="detailConfigDesc_textarea_id" data-max="50" readonly="readonly" style="background-color: rgb(255,255,238)"></textarea>
								</div>
							</div>

							<button class="btn manage-cancel manage-left295" type="button" onclick="cancelDetailConfig_button_onclick()">返回</button>
						</form>
					</div>
					<!--系统配置详情end-->

					
				</div>
			</div>
		</div>
	</div>
	<!--main-end-->


</body>


<script type="application/javascript">
 	loadConfigPage();
	showConfigListTab();
</script>



</html>