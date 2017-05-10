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
<script type="text/javascript" src="${ctx}/js/sys/log.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>年休假操作日志</title>
</head>
<body>
	<!--main-begin-->
	<div class="index_right fl">
		<div class="index_right_main">
			<div class="main_header">
				<div class="manage-tit clearfix">
					<span class="fl">年休假操作日志</span>
					<span class="fr">在线办公&lt;&lt;年休假操作日志</span>
				</div>
			</div>
			<div class="main_bottom">
				<div class="manage-tab">
					<span id="listLeave_span_id" class="manage-active" onclick="listLeave_span_onclick()">倒休日志</span>
					<span id="listAnnualVacate_span_id" class="manage-active" onclick="listAnnualVacate_span_onclick()">年假日志</span>
				</div>
				<div class="manage-tent">
					<!--倒休列表start-->
					
					<div id="listLeave_div_id">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">被操作用户:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="searchLeaveRealName_input_id" onkeyup="if(event.keyCode==13){refreshLeaveList()}">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">开始时间：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-fat">
									<input type="text" class="form-control" name="" id="searchLeaveStartTime_input_id" readonly="readonly" onclick="WdatePicker({el:'searchLeaveStartTime_input_id'})" placeholder="请选择日期">
								</div><br><br>
								<label class="col-md-1 col-sm-1 control-label manage-lab manage-top20 lable-style2" style="margin-top: 10px">结束时间：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-fat">
									<input type="text" class="form-control manage-top20" name="" id="searchLeaveEndTime_input_id" readonly="readonly" onclick="WdatePicker({el:'searchLeaveEndTime_input_id'})" placeholder="请选择日期">
								</div>
								<br>
								<button class="btn manage-sea " type="button" onclick="searchLeave_button_onclick()" data-width="50px">查询</button>
							</div>
						</form>


						<div class="absent-opera absent-table maxwid">
							<table id="grid-data-Leave" class="table table-condensed table-hover table-striped">
								<thead>
									<tr>
										<th data-column-id="operRealName"  data-width="90">操作用户</th>
										<th data-column-id="realName" data-width="70">被操作用户</th>
										<th data-column-id="createTimeString" data-width="230">操作时间</th>
										<th data-column-id="typeString"  data-width="138">操作类型</th>
										<th data-column-id="oldValue"  data-width="90">操作前(小时)</th>
										<th data-column-id="changeValue"  data-width="90">变化量(小时)</th>
										<th data-column-id="newValue"  data-width="90">操作后(小时)</th>
										<th data-column-id="operDesc"  data-width="270">操作说明</th>
									</tr>
								</thead>

							</table>
						</div>
					</div>
					<!--倒休列表end-->
					
					
					<!--年假列表start-->
					<div id="listAnnualVacate_div_id">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">被操作用户:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="searchAnnualVacateRealName_input_id" onkeyup="if(event.keyCode==13){refreshAnnualVacateList()}">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">开始时间：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-fat">
									<input type="text" class="form-control" name="" id="searchAnnualVacateStartTime_input_id" readonly="readonly" onclick="WdatePicker({el:'searchAnnualVacateStartTime_input_id'})" placeholder="请选择日期">
								</div><br><br>
								<label class="col-md-1 col-sm-1 control-label manage-lab lable-style2 manage-top10" style="margin-top: 10px">结束时间：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-fat">
									<input type="text" class="form-control  manage-top20" name="" id="searchAnnualVacateEndTime_input_id" readonly="readonly" onclick="WdatePicker({el:'searchAnnualVacateEndTime_input_id'})" placeholder="请选择日期">
								</div>
								<button class="btn manage-sea manage-top20" type="button" onclick="searchAnnualVacate_button_onclick()">查询</button>
							</div>
						</form>


						<div class="absent-opera absent-table ">
							<table id="grid-data-AnnualVacate" class="table table-condensed table-hover table-striped">
								<thead>
									<tr>
										<th data-column-id="operRealName" data-formatter="operRealName">操作用户</th>
										<th data-column-id="realName"  data-formatter="realName">被操作用户</th>
										<th data-column-id="createTimeString" data-formatter="createTimeString" data-width="400">操作时间</th>
										<th data-column-id="typeString" data-formatter="typeString">操作类型</th>
										<th data-column-id="oldValue" data-formatter="oldValue">操作前(小时)</th>
										<th data-column-id="changeValue" data-formatter="changeValue"  data-width="100">变化量(小时)</th>
										<th data-column-id="newValue" data-formatter="newValue">操作后(小时)</th>
										<th data-column-id="operDesc" data-formatter="operDesc">操作说明</th>
									</tr>
								</thead>

							</table>
						</div>
					</div>
					<!--年假列表end-->
					
				</div>
			</div>
		</div>
	</div>
	<!--main-end-->



</body>


<script type="application/javascript">
	initSetBaseDate();
	loadLeavePage();
	loadAnnualVacatePage();
	showLeaveListTab();
</script>



</html>