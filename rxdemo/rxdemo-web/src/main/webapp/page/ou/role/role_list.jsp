<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>


<link rel="stylesheet" href="${ctx}/css/login.css"/>
<link rel="stylesheet" href="${ctx }/css/laypage.css"/>
<link rel="stylesheet" href="${ctx}/css/ztree/zTreeStyle.css" />
<script type="text/javascript" src="${ctx}/js/common/laypage.js"></script>
<script type="text/javascript" src="${ctx}/js/ztree/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ou/role.js"></script>


<script type="text/javascript">	
	var max_user_check_row = 3;
</script>

</head>
<body>
	<div class="index_right fl">
		<div class="index_right_main">
			<div class="main_header">
				<div class="manage-tit clearfix">
					<span class="fl">角色权限</span> <span class="fr">
						系统管理&nbsp;<<&nbsp;角色管理 </span>
				</div>
			</div>
			<div class="main_bottom">
				<div class="manage-tab">
					<span id="listRole_span_id" class="manage-active" onclick="listRole_span_onclick()">角色列表</span>
					<span id="addRole_span_id" class="manage-active" onclick="addRole_span_onclick()">角色添加</span>
					<span id="editRole_span_id" class="manage-active" style="display:none">角色修改</span>
					<span id="detailRole_span_id" class="manage-active" style="display:none">角色详情</span>
				</div>
				<div class="manage-tent">
					<!-- 角色列表start -->
					<div id="listRole_div_id">
						<form role="form" class="form-horizontal">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">角色名:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="roleName">
								</div>
								<button class="btn manage-sea" type="button" id="seachBtn" onclick="seachRole_button_onclick()">查询</button>
							</div>
						</form>
						<div class="absent-opera absent-table role-table-last">
							<table id="grid-data" class="table table-condensed table-hover table-striped">
								<thead>
								<tr>
									<th data-column-id="roleName">角色名称</th>
									<th data-column-id="remark" >角色描述</th>
									<th data-column-id="link" data-formatter="link">操作</th>
								</tr>
								</thead>
							</table>
						</div>
					</div>
					<!-- 角色列表end -->
					
					<!-- 添加角色start -->
					<div class="manage-none role-paly" id="addRole_div_id">
						<form method="post" role="form" class="form-horizontal" id="addRoleForm">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">角色名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="roleName" id="addRoleName" required data-max="10">
								</div>
							</div>
                            <div class="form-group">
                                <label class="col-md-1 col-sm-1 control-label manage-lab">角色排序：</label>
                                <div class="col-md-2 col-sm-2 manage-wid">
                                    <input type="number" class="form-control" name="sort" id="addSort" value="0" required max="999" min="0">
                                </div>
                            </div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">备注：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-text">
									<textarea class="form-control" rows="6" name="remark" id="addRemark" data-max="50"></textarea>
								</div>
							</div>
							<button class="btn manage-save manage-left93" type="button" onclick="saveAddRole_button_onclick()">保存</button>
							<button class="btn manage-cancel " type="button" onclick="cancelAddRole_button_onclick()">取消</button>
						</form>
					</div>
					<!-- 添加角色end -->
					
					<!-- 编辑角色start -->
					<div class="manage-none role-paly" id="editRole_div_id">
						<form method="post" role="form" class="form-horizontal" id="editRoleForm">
							<input type="hidden" name="id" id="editRoleId"/>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">角色名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="entityname" class="form-control" name="roleName" id="editRoleName" required data-max="10">
								</div>
							</div>
                            <div class="form-group">
                                <label class="col-md-1 col-sm-1 control-label manage-lab">角色排序：</label>
                                <div class="col-md-2 col-sm-2 manage-wid">
                                    <input type="number" class="form-control" name="sort" id="editSort" required max="999" min="0">
                                </div>
                            </div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">备注：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-text">
									<textarea class="form-control" rows="6" name="remark" id="editRoleRemark" data-max="50"></textarea>
								</div>
							</div>
							<button class="btn manage-save manage-left93" type="button" onclick="saveEditRole_button_onclick()">保存</button>
							<button class="btn manage-cancel " type="button" onclick="cancelEditRole_button_onclick()">取消</button>
						</form>
					</div>
					<!-- 编辑角色end -->
					
					<!-- 角色详情start -->
					<div class="manage-none role-paly" id="detailRole_div_id">
						<form method="post" role="form" class="form-horizontal" id="detailRoleForm">
							<input type="hidden" name="id" id="detailRoleId"/>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">角色名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="roleName" id="detailRoleName" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">角色排序：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="sort" id="detailSort" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">备注：</label>
								<div class="col-md-2 col-sm-2 manage-wid manage-text">
									<textarea class="form-control" rows="6" name="remark" id="detailRoleRemark" readonly></textarea>
								</div>
							</div>
							<button class="btn manage-cancel manage-left156" type="button" onclick="cancelDetailRole_button_onclick()">返回</button>
						</form>
					</div>
					<!-- 角色详情end -->
				</div>
			</div>
		</div>
		</div>


	<!--设置菜单start-->
	<div class="absent-layer-changeP absent-layer" id="setMenu_div_id" style="display: none;">
		<input type="hidden" name="id" id="setMenuWithRoleId_input_id"/>
	
	
	
	<div class="absent-clickbox role-user">
			<div class="role-close" onclick="close_x()";><img src="${ctx}/images/s40.png" alt=""></div>
			<h2>菜单选择</h2>
			<table class="role-check table table-bordered table-condensed rol_center">
				
				<tbody>
				<tr> <td>
				<div class="zTreeDemoBackground">
				<ul id="treeDemo" class="ztree"></ul>
				</div>
				</td> </tr>
				</tbody>
			</table>
			
			<div class="manage-left170">
				
            <button class="btn absent-addto manage-cancel " type="button"  onclick="saveSetMenu_button_onclick()">确定</button>
			<button class="btn manage-cancel year-changeP-cancel" type="button"  onclick="cancelSetMenu_button_onclick()">取消</button>
			</div>
		</div>
	
	
	
	</div>
	<!--设置菜单end-->
	
	<!--分配用户start-->
	<div class="absent-layer-changeP absent-layer" id="setUser_div_id" style="display: none;">
		<input type="hidden" name="id" id="setUserWithRoleId_input_id"/>
		<div class="absent-clickbox role-user">
			<div class="role-close" onclick="close_x()";><img src="${ctx}/images/s40.png" alt=""></div>
			<table class="role-check table table-bordered table-condensed">
				<thead>
					<h2>分配用户</h2>
				</thead>
				<tbody id="userCheckbox_tbody">
				</tbody>
			</table>
			
			<div class="manage-left170">
				<button class="btn absent-addto manage-cancel" type="button" onclick="saveSetUser_button_onclick()">确定</button>
				<button class="btn manage-cancel year-changeP-cancel" type="button" onclick="cancelSetUser_button_onclick()">取消</button>
			</div>
		</div>
	</div>
	<!--分配用户end-->
</body>
<script type="text/javascript">

	loadRolePage();
	/*禁用回车事件*/
	$(document).keydown(function(event){
	    switch(event.keyCode) {
			case 13:
				return false;
			default:
				return true;   
		}
	})
	showRoleListTab();
</script>

<script>

	
</script>
</html>