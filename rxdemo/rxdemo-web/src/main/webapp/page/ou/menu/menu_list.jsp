<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/login.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/menu.css"/>
<link href="${ctx }/css/login.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${ctx}/js/ztree/jquery.ztree.core.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/ou/menu.js"></script>
<script type="text/javascript">
	$(function(){
		ininTree(true, "menuTree");	//初始化，加载树型
        var treeNode = menuTreeObj.getNodeByParam("id",0,null);
        treeNode.hasExpand = false;
        treeBeforeExpand(0, treeNode);
	})
</script>
</head>
<body>
	<div class="index_right fl">
		<div class="index_right_main">
			<!-- 项部导航名称 start -->
			<div class="main_header">
				<div class="manage-tit clearfix">
					<span class="fl">菜单管理</span> <span class="fr">
						系统管理&nbsp;<<&nbsp;菜单管理 </span>
				</div>
			</div>
			<div class="main_bottom">
					<div class="manage-tab">
						<span class="menu-list manage-active" id="showListBtn" onclick="showMenuListTab()" >菜单列表</span>
						<span class="add-menu" id="showAddBtn" onclick="showMenuAddTab()">添加菜单</span>
						<span class="change-menu" id="showEditBtn">修改菜单</span>
						<span id="detailMenuBtn" class="manage-active" style="display:none">菜单详情</span>
						<!--<span>角色添加</span>-->
					</div>
					<div class="manage-tent">
						<div class="menu-choose-main clearfix">
							<div class="manage-nav2-left" id="manage-nav2-lef-id">
								<!--导航部分start-->
								<ul id="menuTree" class="ztree" ></ul>
								<!--导航部分end-->
							</div>
							<div class="empty">
								<!-- datalist start -->
								<div class="menu-main manage-nav2-right" id="menuListDiv">
									<div class="manage-table">
										<table id="grid-data"  class="table table-condensed table-hover table-striped menu-table-mb">
											<thead>
												<tr>
													<th data-column-id="name">菜单名称</th>
													<th data-column-id="code">菜单编号</th>
													<th data-column-id="status" data-formatter="status">菜单状态</th>
													<th data-column-id="link" data-formatter="link">操作</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
								<!-- datalist end -->
								<!-- add menu start -->
								<div class="menu-main menu-none" id="addMenuDiv">
									<form method="post" role="form" class="form-horizontal deapart-input-lable" id="addMenuForm" >
										<input type="hidden" name="pId" id="addPid" />
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>上级菜单名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="text" class="form-control" readonly="readonly" id="addParentName"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">菜单URL：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text" class="form-control" name="url" id="addUrl"  data-max="300"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>菜单名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="entityname" class="form-control" name="name" id="addName" required data-max="10"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>菜单排序：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="number" class="form-control" name="sort" id="addSort" value="0" required max="999" min="0"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>菜单编码：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="nickname" class="form-control" name="code" id="addCode" required data-max="10"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>菜单状态：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text manage-top10">
												<input type="radio" name="addMenuStatus" value="0" checked="checked"/>启用<div class="nbsp-hh"></div>
												<input type="radio" name="addMenuStatus" value="1"/>禁用
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">备注：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<textarea class="form-control" rows="6" name="remark" id="addRemark" data-max="50"></textarea>
											</div>
										</div>
										<button class="btn manage-save manage-left295" type="button" id="addMenuSaveBtn" onclick="saveAddMenu();">保存</button>
										<button class="btn manage-cancel " type="button" id="addMenuCancelBtn" onclick="cancelAddMenu()">取消</button>
									</form>
								</div>
								<!-- add menu end -->
								<!-- edit menu start -->
								<div class="menu-main menu-none" id="editMenuDiv">
									<form method="post" role="form" class="form-horizontal deapart-input-lable" id="editMenuForm" >
										<input type="hidden" name="id" id="editMenuId"/>
										<input type="hidden" name="pId" id="editPid" />
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>上级菜单名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="text" class="form-control" readonly="readonly" required id="editParentName"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">菜单URL：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text" class="form-control" name="url" id="editUrl"  data-max="300"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>菜单名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="entityname" class="form-control" name="name" id="editName" required data-max="10"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>菜单排序：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="number" class="form-control" name="sort" id="editSort" required value="0" required max="999" min="0"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>菜单编码：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="nickname" class="form-control" name="code" id="editCode" required data-max="10"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>菜单状态：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text manage-top10">
												<input type="radio" name="editMenuStatus" id="editEnableRadia" value="0" checked="checked"/>启用
												<div class="nbsp-hh"></div>
												<input type="radio" name="editMenuStatus" id="editDisableRadia" value="1"/>禁用
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">备注：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<textarea class="form-control" rows="6" name="remark" id="editRemark" data-max="50"></textarea>
											</div>
										</div>
										<button class="btn manage-save manage-left295" type="button" id="editMenuSaveBtn" onclick="saveUpdateMenu()">保存</button>
										<button class="btn manage-cancel " type="button" id="editMenuCancelBtn" onclick="cancelUpdateMenu()">取消</button>
									</form>
								</div>
								<!-- edit menu start -->
								<!--菜单详情start-->
								<div class="manage-none" id="detailMenu_div_id">
									<form class="form-horizontal deapart-input-lable" method="post" id="detailMenuForm">
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">上级菜单名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="text" class="form-control" readonly="readonly"  id="detailParentName"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">菜单URL：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text" class="form-control" name="url" id="detailUrl" readonly="readonly"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">菜单名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="text" class="form-control" name="name" id="detailName" readonly="readonly"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">菜单排序：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text" class="form-control" name="sort" id="detailSort" readonly="readonly"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">菜单编码：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text" class="form-control" name="code" id="detailCode" readonly="readonly"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">是否启用：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="text" class="form-control" name="" id="detailStatus" readonly="readonly">
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">备注：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<textarea class="form-control" rows="6" name="remark" id="detailRemark" readonly style="background-color: rgb(255, 255, 238);"></textarea>
											</div>
										</div>
										<button class="btn manage-cancel year-changeP-cancel manage-left350" type="button" id="detailMenuCancelBtn" onclick="cancelEditMenu()">返回</button>
									</form>
								</div>
								<!--菜单详情end-->
							</div>
						</div>
					</div>
				</div>
		</div> 
	</div>
</body>
<script type="application/javascript">
	showMenuListTab();
    $(function() {
        /**
         * 点击显示表格数据条数按钮
         *
         */
        function yy_select_pagesize() {
            $('ul.pull-right li').on('click', function () {
                var $yy_select = $(this).children('a').text();//输出对应的li下面a的内容
                var fiframe = $(window.parent.document);
                if ($yy_select == '10') {
                    fiframe.find('.index_main,.main_bottom,.index_right').css("height", "770px");
                } else if ($yy_select == '25') {
                    fiframe.find('.index_main,.main_bottom,.index_right').css("height", "1200px");
                } else {
                    fiframe.find('.index_main,.main_bottom,.index_right').css("height", "2000px");
                }
            })
        }
        yy_select_pagesize();
    });
</script>
</html>