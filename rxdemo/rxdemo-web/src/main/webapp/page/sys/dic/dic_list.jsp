<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cn.yiyizuche.common.sys.util.SysConstants"%>
<%@ include file="/page/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典管理</title>

<script type="text/javascript">
	var yyzc_pro_context = '<%= request.getContextPath() %>';
	
	var interiorDic = <%=SysConstants.DicType.ISINTERIOR%>;//内部字典
	var externalDic = <%=SysConstants.DicType.ISEXTERNAL%>;//外部字典
	
	var liebiao =  <%=SysConstants.DicDisplayType.LIST%>;//列表类型的字典
	var shuxing =  <%=SysConstants.DicDisplayType.TREE%>;//树形的字典
	
	var enable =  <%=SysConstants.ENABLE_FLAG%>;//启用标识
	var unenable =  <%=SysConstants.UNENABLE_FLAG%>;//禁用标识
</script>

<link rel="stylesheet" href="${ctx}/css/login.css"/>
<link rel="stylesheet" href="${ctx }/css/laypage.css"/>
<script type="text/javascript" src="${ctx}/js/common/laypage.js"></script>
<script type="text/javascript" src="${ctx}/js/sys/dic.js"></script>
<script type="text/javascript" src="${ctx}/js/sys/dicItem.js"></script>

</head>
<body>
	<div class="index_right fl">
		<div class="index_right_main">
			<div class="main_header">
				<div class="manage-tit clearfix">
					<span class="fl">字典管理</span> <span class="fr">
						系统管理&nbsp;<<&nbsp;字典管理 </span>
				</div>
			</div>
			<div class="main_bottom">
				<div class="manage-tab">
					<span id="listDic_span_id" class="manage-active" onclick="showDicList()">字典列表</span>
					<span id="addDic_span_id" onclick="showDicAddTab();">字典添加</span>
					<span id="editDic_span_id" class="manage-active" style="display:none">字典修改</span>
					<span id="detailDic_span_id" class="manage-active" style="display:none">字典详情</span>
				</div>
				<div class="manage-tent">
					<!-- 字典列表start -->
					<div id="dicListDiv">
						<form  class="form-horizontal">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">字典名称:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="dicName">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">状态:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<select id="search_dicStatus" class="form-control">
										<option value="-1">全部</option>
										<option value="0">启用</option>
										<option value="1">禁用</option>
									</select>
								</div>
								<button class="btn manage-sea" type="button" id="seachBtn" onclick="seachList()">查询</button>
							</div>
						</form>
						<div class="absent-opera absent-table role-table-last dicitem-table">
							<table id="grid-data" class="table table-condensed table-hover table-striped">
								<thead>
								<tr>
									<th data-column-id="dicName" data-width="107">字典名称</th>
									<th data-column-id="dicCode" data-width="107">字典编号</th>
									<th data-column-id="isExternal" data-formatter="isExternal">是否是外部字典</th>
									<th data-column-id="displayType" data-formatter="displayType">字典类型</th>
									<th data-column-id="dicStatus" data-formatter="dicStatus">状态</th>
									<th data-column-id="link" data-formatter="link" data-width="400">操作</th>
								</tr>
								</thead>
							</table>
						</div>
					</div>
					<!-- 字典列表end -->
					
					<!-- 添加字典start -->
					<div class="manage-none role-paly dic-label" id="addDicDiv">
						<form method="post" role="form" class="form-horizontal" id="addDicForm">
							<!-- 隐藏域 -->
							<input type="hidden" name="createUser" id="add_createUser"><!-- 创建人 -->
						
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="dicName" id="add_dicName" data-max="10" required>
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典编号：</label>
                                <div class="col-md-2 col-sm-2 manage-wid">
                                    <input type="text" class="form-control" name="dicCode" id="add_dicCode" data-max="10" required>
                                </div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典类型：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-text" id="add_displayType">
									<select name="displayType" class="form-control">
										<option value="1">列表</option>
										<option value="2">树形</option>
									</select>
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>外部字典：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-text">
									<select onchange="showExternalDiv('add');" id="add_isExternal" name="isExternal" class="form-control">
										<option value="0">否</option>
										<option value="1">是</option>
									</select>
								</div>
							</div>
							<div class="form-group" id="add_external_div1"></div>
							<div class="form-group" id="add_external_div2"></div>
							
							
							<button class="btn manage-save manage-left280" type="button" onclick="saveAddDic();">保存</button>
							<button class="btn manage-cancel " type="button" onclick="cancelSaveForm();">取消</button>
						</form>
					</div>
					<!-- 添加字典end -->
					
					<!-- 修改字典start -->
					<div class="manage-none role-paly dic-label" id="editDicDiv">
						<form method="post" role="form" class="form-horizontal" id="editDicForm">
							<!-- 隐藏域 -->
							<input type="hidden" name="updateUser" id="edit_updateUser"><!-- 修改人 -->
							<input type="hidden" name="id" id="edit_id"><!-- 修改人 -->
						
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="dicName" id="edit_dicName" data-max="10" required>
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典编号：</label>
                                <div class="col-md-2 col-sm-2 manage-wid">
                                    <input type="text" class="form-control" name="dicCode" id="edit_dicCode" data-max="10" required>
                                </div>
							</div>
							<div class="form-group" id="edit_dicType"></div>
							<div class="form-group" id="edit_external_div1"></div>
							<div class="form-group" id="edit_external_div2"></div>
							
							<button class="btn manage-save manage-left280" type="button" onclick="saveEditDic();">保存</button>
							<button class="btn manage-cancel " type="button" onclick="showDicList()">取消</button>
						</form>
					</div>
					<!-- 修改字典end -->
					
					<!-- 字典详情start -->
					<div class="manage-none role-paly dic-label" id="detailDicDiv">
						<form method="post" role="form" class="form-horizontal" id="detailDicForm">
							<!-- 隐藏域 -->
							<input type="hidden" name="isExternal" id="detail_isExternal"><!-- 是否是外部字典 -->
							
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="detail_dicName" readonly="readonly">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典编号：</label>
                                <div class="col-md-2 col-sm-2 manage-wid">
                                    <input type="text" class="form-control" id="detail_dicCode" readonly="readonly">
                                </div>
							</div>
							<div class="form-group" id="detail_dicType"></div>
							<div class="form-group" id="detail_external_div1"></div>
							<div class="form-group" id="detail_external_div2"></div>
							
							<button class="btn manage-cancel manage-left350" type="button" onclick="showDicList()">返回</button>
						</form>
					</div>
					<!-- 字典详情end -->
				</div>
			</div>
		</div>
		</div>
	<!--字典项列表start-->
	<div class="absent-layer-changeP absent-layer" style="display: none;" id="dicItemList" >
		<%@ include file="dicItem_list.jsp"%>
	</div>
	<!--字典项列表end-->

	
	
</body>
<script type="text/javascript">

	loadDicPage();
	/*禁用回车事件*/
	$(document).keydown(function(event){
	    switch(event.keyCode) {
			case 13:
				return false;
			default:
				return true;   
		}
	})
</script>
</html>

