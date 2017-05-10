<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="index_right fl" >
		<div class="index_right_main" >
			<div class="main_header">
				<div class="manage-tit clearfix">
					<span class="fl">字典项管理</span> <span class="fr">
						系统管理&nbsp;<<&nbsp;字项管理<<&nbsp;字典项管理 </span>
				</div>
			</div>
			<div class="main_bottom">
				<div class="manage-tab">
					<span id="listDicItem_span_id" class="manage-active" onclick="returnDicItemList()">字典项列表</span>
					<span id="addDicItem_span_id" class="manage-active" onclick="showDicItemAddTab();">字典项添加</span>
					<span id="editDicItem_span_id" class="manage-active" style="display:none">字典项修改</span>
					<span id="detailDicItem_span_id" class="manage-active" style="display:none">字典项详情</span>
					<img style="float:right;padding:10px;cursor: pointer;" alt="" src="${ctx}/images/s40.png" onclick="closeDicItemPage();">
				</div>
				<div class="manage-tent">
					<!-- 字典项项列表start -->
					<div id="dicItemListDiv">
						<form  class="form-horizontal">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab">字典项名称:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" id="itemName">
								</div>
								<label class="col-md-1 col-sm-1 control-label manage-lab">状态:</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<select id="search_itemStatus" class="form-control">
										<option value="-1">全部</option>
										<option value="0">启用</option>
										<option value="1">禁用</option>
									</select>
								</div>
								<button class="btn manage-sea" type="button" id="seachBtn" onclick="seachDicItemList()">查询</button>
							</div>
						</form>
						<div class="absent-opera absent-table role-table-last">
							<table id="dicItem-grid-data" class="table table-condensed table-hover table-striped">
								<thead>
								<tr>
									<th data-column-id="itemName">字典项名称</th>
									<th data-column-id="itemCode" >字典项编号</th>
									<th data-column-id="itemSort" >排序</th>
									<th data-column-id="itemStatus" data-formatter="itemStatus">状态</th>
									<th data-column-id="link" data-formatter="link" data-width="300">操作</th>
								</tr>
								</thead>
							</table>
						</div>
					</div>
					<!-- 字典项项列表end -->
					
					<!-- 添加字典项start -->
					<div class="manage-none role-paly" id="addDicItemDiv">
						<form method="post" role="form" class="form-horizontal" id="addDicItemForm">
							<!-- 隐藏域 -->
							<input type="hidden" name="dicId" id="add_itemDicId"><!-- 字典id -->
							<input type="hidden" name="createUser" id="add_itemCreateUser"><!-- 创建人 -->
						
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典项名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="itemName" id="add_itemName" required data-max="10">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典项编号：</label>
                                <div class="col-md-2 col-sm-2 manage-wid">
                                    <input type="text" class="form-control" name="itemCode" id="add_itemCode" required data-max="10">
                                </div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>排序：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="number" class="form-control" name="itemSort" id="add_itemSort" min='0' required>
								</div>
							</div>
							
							
							<button class="btn manage-save manage-left93" type="button" onclick="saveAddDicItem();">保存</button>
							<button class="btn manage-cancel " type="button" onclick="cancelSaveDicItemForm();">取消</button>
						</form>
					</div>
					<!-- 添加字典项end -->
					
					<!-- 修改字典项start -->
					<div class="manage-none role-paly" id="editDicItemDiv">
						<form method="post" role="form" class="form-horizontal" id="editDicItemForm">
							<!-- 隐藏域 -->
							<input type="hidden" name="updateUser" id="edit_itemUpdateUser"><!-- 修改人 -->
							<input type="hidden" name="id" id="edit_itemId"><!-- 字典项id -->
						
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典项名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="itemName" id="edit_itemName" required data-max="10">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典项编号：</label>
                                <div class="col-md-2 col-sm-2 manage-wid">
                                    <input type="text" class="form-control" name="itemCode" id="edit_itemCode" required data-max="10">
                                </div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>排序：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="number" class="form-control" name="itemSort" id="edit_itemSort" min='0' required>
								</div>
							</div>
							
							<button class="btn manage-save manage-left93" type="button" onclick="saveEditDicItem();">保存</button>
							<button class="btn manage-cancel " type="button" onclick="cancelSaveDicItemForm()">取消</button>
						</form>
					</div>
					<!-- 修改字典项end -->
					
					<!-- 字典项详情start -->
					<div class="manage-none role-paly" id="detailDicItemDiv">
						<form method="post" role="form" class="form-horizontal" id="detailDicItemForm">
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典项名称：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="text" class="form-control" name="itemName" id="detail_itemName" readonly="readonly">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>字典项编号：</label>
                                <div class="col-md-2 col-sm-2 manage-wid">
                                    <input type="text" class="form-control" name="itemCode" id="detail_itemCode" readonly="readonly">
                                </div>
							</div>
							<div class="form-group">
								<label class="col-md-1 col-sm-1 control-label manage-lab"><span style="color: red;">*</span>排序：</label>
								<div class="col-md-2 col-sm-2 manage-wid">
									<input type="number" class="form-control" name="itemSort" id="detail_itemSort" min='0' readonly="readonly">
								</div>
							</div>
							
							<button class="btn manage-cancel " type="button" onclick="cancelSaveDicItemForm()">返回</button>
						</form>
					</div>
				</div>
			</div>
		</div>
</div>

<script type="text/javascript">
	//初始化字典项列表
	initDicItemList();
</script>