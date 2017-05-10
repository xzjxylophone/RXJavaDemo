<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/page/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/ztree/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/login.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/menu.css"/>
<link href="${ctx }/css/login.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${ctx}/js/ztree/jquery.ztree.core.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/ou/department.js"></script>
<script type="text/javascript">
</script>
</head>
<body>
	<div class="index_right fl">
		<div class="index_right_main">
			<!-- 项部导航名称 start -->
			<div class="main_header">
				<div class="manage-tit clearfix">
					<span class="fl">部门管理</span> <span class="fr">
						系统管理&nbsp;<<&nbsp;部门管理 </span>
				</div>
			</div>
			<div class="main_bottom">
					<div class="manage-tab">
						<span class="manage-active" id="showListBtn" onclick="showListPage(this);">部门列表</span>
						<span id="showAddBtn">添加部门</span>
						<span style="display: none;" id="showEditBtn">修改部门</span>
						<span style="display: none;" id="showViewBtn">部门详情</span>
					</div>
					<div class="manage-tent">
							<div class="menu-choose-main  clearfix">
								<div class="manage-nav2-left">
									<!--导航部分start-->
									<ul id="orgTree" class="ztree" ></ul>
									<!--导航部分end-->
								</div>
								<!-- datalist start -->
								<div class="menu-main manage-nav2-right" id="orgListDiv">
									<div class="manage-table">
										<table id="grid-data"  class="table table-condensed table-hover table-striped menu-table-mb">
											<thead>
												<tr>
													<th data-column-id="name" data-width="300">部门名称</th>
													<th data-column-id="code">部门编号</th>
													<th data-column-id="punchIn">上班打卡时间</th>
													<th data-column-id="punchOut">下班打卡时间</th>
													<th data-column-id="link" data-formatter="link"  data-width="300">操作</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
								<!-- datalist end -->
								<!-- add menu start -->
								<div class="menu-main menu-none" id="addOrgDiv">
									<form role="form" class="form-horizontal deapart-input-lable" id="addOrgForm" >
										<input type="hidden" name="pId" id="addPid" />
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>上级部门名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="text" class="form-control" readonly="readonly" disabled="disabled" id="addParentName"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>部门负责人：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="hidden" name="leaderId"  id="addLeaderId"/>
												<input type="text" class="form-control depart-leader" readonly="readonly" required id="addLearderName" value=""/>
												<button type="button" class="btn manage-sea depart-leader-btn" onclick="selectLeader('leader','add');">选择</button>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>部门名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="entityname" class="form-control" name="name" id="addName" required data-max="10"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>主管领导：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="hidden" name="supleaderId"  id="addSupLeaderId" value=""/>
												<input type="text" class="form-control depart-leader" readonly="readonly" required id="addSupLearderName"/>
												<button type="button" class="btn manage-sea depart-leader-btn" onclick="selectLeader('supleader','add');">选择</button>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>部门简称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="entityname" class="form-control" name="shortName" id="addShortName" required data-max="10"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>上班打卡时间：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<select name="punchIn" id="addPunchIn" style="width:100px;" class="form-control" required>
													<option value="8:00">8：00</option>
													<option value="8:30">8：30</option>
													<option value="9:00"  selected="selected">9：00</option>
													<option value="9:30">9：30</option>
													<option value="10:00">10：00</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>部门编码：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="loginname" class="form-control" name="code" id="addCode" required data-max="20"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>下班打卡时间：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<select name="punchOut" id="addPunchOut" style="width:100px;" class="form-control" required>
													<option value="17:00" >17：00</option>
													<option value="17:30">17：30</option>
													<option value="18:00"  selected="selected">18：00</option>
													<option value="18:30">18：30</option>
													<option value="19:00">19：00</option>
													<option value="19:30">19：30</option>
													<option value="20:00">20：00</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>部门排序：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="number" class="form-control" value="0" name="sort" id="addSort" required min="0"  max="999"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">备注：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<textarea class="form-control" rows="3"  name="remark" id="addRemark" data-max="50"></textarea>
											</div>
										</div>
										<button class="btn manage-save manage-left295" type="button" id="addOrgSaveBtn" onclick="saveAddOrg();">保存</button>
										<button class="btn manage-cancel " type="button" id="addOrgCancelBtn" onclick="cancleAddDiv();">取消</button>
									</form>
								</div>
								<!-- add Org end -->
								<!-- edit Org start -->
								<div class="menu-main menu-none" id="editOrgDiv" style="display: none;">
									<form role="form" class="form-horizontal deapart-input-lable" id="editOrgForm" >
										<input type="hidden" name="pId" id="editPid" />
										<input type="hidden" name="id" id="editId" />
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>上级部门名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="text" class="form-control" readonly="readonly" disabled="disabled" id="editParentName"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>部门负责人：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text" style="width:260px;">
												<input type="hidden" name="leaderId" id="editLeaderId"/>
												<input type="text" class="form-control depart-leader" required readonly="readonly"  id="editLearderName"/>
												<button type="button" class="btn manage-sea depart-leader-btn" onclick="selectLeader('leader','edit');">选择</button>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>部门名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="entityname" required data-max="10" class="form-control" name="name" id="editName"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>主管领导：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text" style="width:260px;">
												<input type="hidden" name="supleaderId"  id="editSupLeaderId"/>
												<input type="text"  class="form-control depart-leader" readonly="readonly" required id="editSupLearderName"/>
												<button type="button" class="btn manage-sea depart-leader-btn" onclick="selectLeader('supleader','edit');">选择</button>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>部门简称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="entityname" required data-max="10" class="form-control" name="shortName" id="editShortName"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>上班打卡时间：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<select name="punchIn" id="editPunchIn" style="width:100px;" class="form-control">
													<option value="8:00">8：00</option>
													<option value="8:30">8：30</option>
													<option value="9:00">9：00</option>
													<option value="9:30">9：30</option>
													<option value="10:00">10：00</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>部门编码：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="loginname" required data-max="20" class="form-control" name="code" id="editCode"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>下班打卡时间：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<select name="punchOut" id="editPunchOut" style="width:100px;" class="form-control">
													<option value="17:00" >17：00</option>
													<option value="17:30">17：30</option>
													<option value="18:00">18：00</option>
													<option value="18:30">18：30</option>
													<option value="19:00">19：00</option>
													<option value="19:30">19：30</option>
													<option value="20:00">20：00</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>部门排序：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="number" class="form-control" max="99" value="0" min="0" required name="sort" id="editSort"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">备注：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<textarea class="form-control" rows="3" name="remark" id="editRemark" data-max="50"></textarea>
											</div>
										</div>
										<button class="btn manage-save manage-left295" type="button" id="editOrgSaveBtn" onclick="saveEditOrg();">保存</button>
										<button class="btn manage-cancel " type="button" id="editOrgCancelBtn" onclick="cancleEditDiv();">取消</button>
									</form>
								</div>
								<!-- edit menu start -->
								<!-- view Org start -->
								<div class="menu-main menu-none" id="viewOrgDiv" style="display: none;">
									<form role="form" id="detailOrgForm" class="form-horizontal deapart-input-lable" >
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">上级部门名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="text" class="form-control" readonly="readonly" disabled="disabled" id="viewParentName"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">部门负责人：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text" class="form-control depart-leader2"  readonly="readonly"  id="viewLearderName"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">部门名称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="text"  readonly="readonly" class="form-control"  id="viewName"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">主管领导：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text"  class="form-control depart-leader2" readonly="readonly"  id="viewSupLearderName"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">部门简称：</label>
											<div class="col-md-2 col-sm-2 manage-wid">
												<input type="text"  readonly="readonly" class="form-control"  id="viewShortName"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">上班打卡时间：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text" class="form-control"  id="viewPunchIn"  class="form-control" readonly="readonly"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">部门编码：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text" readonly="readonly" class="form-control"  id="viewCode"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">下班打卡时间：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text" class="form-control"  id="viewPunchOut"  class="form-control" readonly="readonly"/>
											</div>
										</div>
										<div class="form-group">
											<label class="col-md-1 col-sm-1 control-label manage-lab">部门排序：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<input type="text" class="form-control" readonly="readonly"  id="viewSort"/>
											</div>
											<label class="col-md-1 col-sm-1 control-label manage-lab">备注：</label>
											<div class="col-md-2 col-sm-2 manage-wid manage-text">
												<textarea class="form-control" rows="3"  id="viewRemark" readonly></textarea>
											</div>
										</div>
										<button class="btn manage-cancel manage-left350" type="button" id="detailOrgCancelBtn" onclick="cancleDetailDiv();">返回</button>
									</form>
								</div>
								<!-- view Org end -->
							</div>
					</div>
				</div>
		</div>
	</div>
	<!-- 选择部门负责人弹窗   start -->
	<div class="absent-layer-changeP absent-layer" id="selectLeaderDiv" style="display: none;">
		<input type="hidden" name="type" id="selectType"/>
		<input type="hidden" name="optType" id="optType"/>
		<div class="absent-clickbox role-user">

			<table class="role-check table table-bordered table-condensed">
				<thead>
					<h2 id="winTitle"></h2>
				</thead>
				<tbody id="userCheckbox_tbody">
				</tbody>
			</table>


			<div class="manage-left170">
				<button class="btn absent-addto manage-cancel " type="button"  onclick="confirmSelect();">确定</button>
				<button class="btn manage-cancel year-changeP-cancel" type="button" onclick="cancelSelect();">取消</button>
			</div>
		</div>
	</div>
	<!-- 选择部门负责人弹窗   start -->
</body>
<script>
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