//初始化字典列表
function loadDicPage() {
	$("#grid-data").bootgrid({
		sorting : false,
		navigation : 3, // Default value is 3. 0 for none, 1 for header, 2 for footer and 3 for both.
		columnSelection : false, //启用或禁用下拉框隐藏/显示列。默认值为true
		ajax : true,
		ajaxSettings : {
			method : "POST",
			cache : false
		},
		requestHandler : function(request) { //自定义参数处理
			var dicName = $("#dicName").val();
			var dicStatus =  document.getElementById("search_dicStatus").value; 
			
			request.dicName = dicName;
			request.dicStatus = dicStatus;
			return request;
		},
		url : yyoa_context + "/sys/dic/searchDicByPage.do",
		rowCount : [ 10, 25, 50 ],
		labels : {
			infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
		},
		formatters : {
			"isExternal" : function(column, row) {
                var htmlstr = '';
                if(row.isExternal == interiorDic){
                	htmlstr = '否';
                }else if(row.isExternal == externalDic){
                	htmlstr = '是';
                }
                
				return htmlstr;
			},
			"displayType" : function(column, row) {
                var htmlstr = '';
                if(row.displayType == liebiao){
                	htmlstr = '列表';
                }else if(row.displayType == shuxing){
                	htmlstr = '树形';
                }
                
				return htmlstr;
			},
			"dicStatus" : function(column, row) {
                var htmlstr = '';
                if(row.dicStatus == enable){
                	htmlstr = '启用';
                }else if(row.dicStatus == unenable){
                	htmlstr = '禁用';
                }
                
				return htmlstr;
			},
			"link" : function(column, row) {
				//字典状态
				var dicStatus = "";
				var temp = "";
				if(row.dicStatus == enable){
					temp = "禁用";
					dicStatus = unenable;
				}else if(row.dicStatus == unenable){
					temp = "启用";
					dicStatus = enable;
				}
                var htmlstr = '';
                htmlstr = '<span id="manage-reset" onclick="showDicInfo('+row.id+',\'detail\')">'+'详情'+'</span>'+'|'
                + '<span id="manage-delete" onclick="showDicInfo('+row.id+',\'edit\')">'+'修改'+'</span>'+'|'
                + '<span id="manage-use" onclick="deleteById('+row.id+')">'+'删除'+'</span>'+'|'
                + '<span id="manage-use" onclick="updateDicStatus('+row.id+','+dicStatus+')">'+ temp +'</span>'+'|'
                + '<span id="manage-use" onclick="showDicItemList('+row.id+')">'+'查看字典项'+'</span>';
				return htmlstr;
			}
		}
	});
}

//按条件查询字典列表
function seachList() {
    $("#grid-data").bootgrid("repage", true);
}

//显示外部字典添加项 type:add、edit、detail
function showExternalDiv(type){
	//先清空div
	$("#" + type + "_external_div1").empty();
	$("#" + type + "_external_div2").empty();
	
	var external = "";
	//如果external的值为空，则为添加或修改操作，需要动态获取选中的外部字典值
	if(type == 'add' || type == 'edit'){
		external = document.getElementById(type + "_isExternal").value;
	}else{
		external = $("#"+type+"_isExternal").val();
	}
	//根据选中的字典类型确定是否显示外部字典的添加项
	 if(external == externalDic){//外部字典，显示外部添加项
		 var flag = "";
		 var st = "";
		 if(type == 'detail'){
			 flag = "readonly";
			 st = "style='background-color: #FFFFEE'";
		 }
		var divStr1 = "<label class='col-md-1 col-sm-1 control-label manage-lab'><span style='color: red;'>*</span>字典表编号：</label>"
			        + "<div class='col-md-2 col-sm-2 manage-wid manage-text'>"
			        + "<input type='text' class='form-control' name='dicTableCode' id='"+type+"_dicTableCode' id= required data-max='10' "+flag+" "+st+">"
					+ "</div>"
					+ "<label class='col-md-1 col-sm-1 control-label manage-lab'><span style='color: red;'>*</span>外部字典名称：</label>"
					+ "<div class='col-md-2 col-sm-2 manage-wid manage-text'>"
					+ "<input type='text' class='form-control' name='dicNameField' id='"+type+"_dicNameField' required data-max='10' "+flag+" "+st+">"
					+ "</div>";
		
		var divStr2 = "<label class='col-md-1 col-sm-1 control-label manage-lab'><span style='color: red;'>*</span>外部字典编号：</label>"
					+ "<div class='col-md-2 col-sm-2 manage-wid manage-text'>"
					+ "<input type='text' class='form-control' name='dicCodeField' id='"+type+"_dicCodeField' required data-max='10' "+flag+" "+st+">"
					+ "</div>";
		
		//拼接外部字典需要填的信息
		$("#" + type + "_external_div1").append(divStr1);
		$("#" + type + "_external_div2").append(divStr2);
	}
}

//根据字典id 启用、禁用字典
function updateDicStatus(dicId, dicStatus){
	 $.ajax({
	        type: 'POST',
	        data: {
	        	dicId : dicId,
	        	dicStatus : dicStatus
	        },
	        url: yyoa_context + '/sys/dic/updateDicStatus.do',
	        dataType: 'json',
	        async: false,
	        success: function (data) {
	            Modal.alert({msg: data.message})
	            if (data.state) {
	            	// 刷新字典列表
	            	$("#grid-data").bootgrid("reload", true);
	            }
	         }
	     });
}
//删除字典
function deleteById(dicId){
	 Modal.confirm(
		{  msg: "删除字典会关联删除字典下的字典项，确定删除吗？"
		}) .on( function (e) {
			if(e) {
			   $.ajax({
			        type: 'POST',
			        data: {
			        	dicId: dicId
			        },
			        url: yyoa_context + '/sys/dic/deleteByDicId.do',
			        dataType: 'json',
			        async: false,
			        success: function (data) {
			            Modal.alert({msg: data.message})
			            if (data.state) {
			            	// 刷新字典列表
			            	$("#grid-data").bootgrid("reload", true);
			            }
			         }
			     });
			 }
		});
}
//保存添加的字典
function saveAddDic(){
	var flag = $.html5Validate.isAllpass($("#addDicForm"));
	   if(!flag){
		   return;
	   }else{
		$("#add_createUser").val(sessionUserId);
		   $.ajax({
				type:'POST',
				url : yyzc_pro_context + "/sys/dic/addDic.do",
				data : $('#addDicForm').serialize(),
				dataType:'json',
				async: false,
				success:function(data){
					Modal.alert({msg: data.message});
					if(data.state){
						// 清理表单
						cancelSaveForm();
						// 刷新字典列表
						$("#grid-data").bootgrid("reload", true);
					}
				}
		   });
	   }
}

//保存修改后的字典信息
function saveEditDic(){
	var flag = $.html5Validate.isAllpass($("#editDicForm"));
	   if(!flag){
		   return;
	   }else{
		   $.ajax({
				type:'POST',
				url : yyzc_pro_context + "/sys/dic/updateDic.do",
				data : $('#editDicForm').serialize(),
				dataType:'json',
				async: false,
				success:function(data){
					Modal.alert({msg: data.message});
					if(data.state){
						// 清理表单
						cancelSaveForm();
						//隐藏修改页面
		            	hideEditDiv();
						// 刷新字典列表
						$("#grid-data").bootgrid("reload", true);
					}
				}
		   });
	   }
}

//显示字典详细信息：详情及修改时用
function showDicInfo(dicId,option){
	//显示详情或修改div
	showSpecificDiv(option);
	
	 $.ajax({
			type:'POST',
			url : yyzc_pro_context + "/sys/dic/selectDicByDicId.do",
			data : {
				dicId : dicId
			},
			dataType:'json',
			async: false,
			success:function(data){
				//获取字典类型
				var dicType = data.displayType;
				//获取是否是外部字典
				var isExternal = data.isExternal;
				
				//设置表单中的值
				if(option == 'edit'){
					//修改人
					$("#edit_updateUser").val(sessionUserId);
					//字典id
					$("#edit_id").val(data.id);
					//拼接修改div并赋值
					jointEditDiv(isExternal, dicType);
				}else if(option == 'detail'){
					//是否是外部字典
					$("#detail_isExternal").val(isExternal);
					//拼接详情页面并赋值
					jointDetailDiv(isExternal, dicType);
				}
				//判断字典是否是外部字典
				if(isExternal == externalDic){//是外部字典，显示外部字典各项信息
					//显示显示外部字典div
					showExternalDiv(option);
					//设置外部字典信息
					$("#"+option+"_dicTableCode").val(data.dicTableCode);//字典表编号
					$("#"+option+"_dicNameField").val(data.dicNameField);//外部字典名称
					$("#"+option+"_dicCodeField").val(data.dicCodeField);//外部字典编号
				}
				//设置修改页面及详情页面的公共值
				$("#"+option+"_dicName").val(data.dicName);//字典名称
				$("#"+option+"_dicCode").val(data.dicCode);//字典编号
			}
	   });
	
}
//显示详情或修改页面
function showSpecificDiv(option){
	//根据当前操作显示不同div
	if(option == 'edit'){//显示修改div
		//先隐藏详情页
		hideDetailDiv();
		
		showEditDiv();
	}else if(option == 'detail'){//显示详情div
		//先隐藏修改页
		hideEditDiv();
		
		showDetailDiv();
	}
}
//拼接修改div并赋值
function jointEditDiv(isExternal, dicType){
	//清空div数据
	$("#edit_dicType").empty();
	$("#edit_external_div1").empty();
	$("#edit_external_div2").empty();
	
	var tempType = "";//字典类型
	var temp = "";//是否是外部字典
	//判断字典类型
	if(dicType == liebiao){
		tempType = "<option value='"+liebiao+"' selected='selected'>列表</option>"
				 + "<option value='"+shuxing+"'>树形</option>";
	}else if(dicType == shuxing){
		tempType = "<option value='"+liebiao+"'>列表</option>"
			     + "<option value='"+shuxing+"' selected='selected'>树形</option>";
	}
	//判断是否是外部字典
	if(isExternal == interiorDic){
		temp = "<option value='"+interiorDic+"' selected='selected'>否</option>"
		     + "<option value='"+externalDic+"'>是</option>";
	}else if(isExternal == externalDic){
		temp = "<option value='"+interiorDic+"'>否</option>"
			 + "<option value='"+externalDic+"' selected='selected'>是</option>";
	}
	
	var editDiv = "<label class='col-md-1 col-sm-1 control-label manage-lab'><span style='color: red;'>*</span>字典类型：</label>"
		        + "<div class='col-md-2 col-sm-2 manage-wid manage-text' >"
				+ "<select name='displayType' class='form-control'>"
				+ tempType
				+ "</select></div>"
				+ "<label class='col-md-1 col-sm-1 control-label manage-lab'><span style='color: red;'>*</span>外部字典：</label>"
				+ "	<div class='col-md-2 col-sm-2 manage-wid manage-text'>"
				+ "<select onchange='showExternalDiv(\"edit\");' id='edit_isExternal' name='isExternal' class='form-control'>"
				+ temp
				+ "</select></div>";
	//拼接数据
	$("#edit_dicType").append(editDiv);
}
//拼接详情div并赋值
function jointDetailDiv(isExternal, dicType){
	//清空div数据
	$("#detail_dicType").empty();
	$("#detail_external_div1").empty();
	$("#detail_external_div2").empty();
	
	var tempType = "";//字典类型
	var temp = "";//是否是外部字典
	//判断字典类型
	if(dicType == liebiao){
		tempType = "列表";
	}else if(dicType == shuxing){
		tempType = "树形";
	}
	//判断是否是外部字典
	if(isExternal == interiorDic){
		temp = "否";
	}else if(isExternal == externalDic){
		temp = "是";
	}
	var detailDiv = "<label class='col-md-1 col-sm-1 control-label manage-lab'><span style='color: red;'>*</span>字典类型：</label>"
				  + "<div class='col-md-2 col-sm-2 manage-wid manage-text' >"
				  + "<input type='text' class='form-control' readonly='readonly' required value='"+tempType+"' style='background-color: #FFFFEE'>"
				  + "</div>"
				  + "<label class='col-md-1 col-sm-1 control-label manage-lab'><span style='color: red;'>*</span>外部字典：</label>"
				  + "<div class='col-md-2 col-sm-2 manage-wid manage-text'>"
				  + " <input type='text' class='form-control'  readonly='readonly' required value='"+temp+"' style='background-color: #FFFFEE'>"
				  + "</div>";
	//拼接数据
	$("#detail_dicType").append(detailDiv);
}

//重置添加字典页面
function cancelSaveForm(){
	resetForm("addDicForm");
	resetForm("editDicForm");
	 // 切换回表格tab
    var listBtn = document.getElementById("listDic_span_id");
    changeTab(listBtn, 'dicListDiv');
}
//显示字典列表页
function showDicList(){
	//隐藏修改页面
	hideEditDiv();
	//隐藏详情页面
	hideDetailDiv();
	
	changeTab($("#listDic_span_id"), "dicListDiv");
	// 刷新字典列表
	$("#grid-data").bootgrid("reload", true);
	
}

//显示添加字典tab页
function showDicAddTab() {
	//清空相关div内容
	$("#add_external_div1").empty();
	$("#add_external_div2").empty();
	//隐藏修改页面
	hideEditDiv();
	//隐藏详情页面
	hideDetailDiv();
	//重置添加页面
	resetForm("addDicForm");
	
	var addDic_span = $("#addDic_span_id");
	addDic_span.show();

	changeTab(addDic_span, "addDicDiv");
}

//隐藏修改页面
function hideEditDiv(){
	var editDic_span = $("#editDic_span_id");
	editDic_span.hide();
}

//隐藏详情页面
function hideDetailDiv(){
	var detailDic_span = $("#detailDic_span_id");
	detailDic_span.hide();
}

//显示修改页面
function showEditDiv(){
	var editDic_span = $("#editDic_span_id");
	editDic_span.show();
	
	changeTab(editDic_span, "editDicDiv");
}

//显示详情页面
function showDetailDiv(){
	var detailDic_span = $("#detailDic_span_id");
	detailDic_span.show();
	
	changeTab(detailDic_span, "detailDicDiv");
}
