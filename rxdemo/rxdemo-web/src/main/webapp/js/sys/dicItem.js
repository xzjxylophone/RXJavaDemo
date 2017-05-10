//初始化字典项列表
function initDicItemList(){
	$("#dicItem-grid-data").bootgrid({
		sorting : false,
		navigation : 3, // Default value is 3. 0 for none, 1 for header, 2 for footer and 3 for both.
		columnSelection : false, //启用或禁用下拉框隐藏/显示列。默认值为true
		ajax : true,
		ajaxSettings : {
			method : "POST",
			cache : false
		},
		requestHandler : function(request) { //自定义参数处理
			var itemName = $("#itemName").val();
			var itemStatus = document.getElementById("search_itemStatus").value; 
			request.itemName = itemName;
			request.dicId = $('#add_itemDicId').val()==""?0:$('#add_itemDicId').val();
			request.itemStatus = itemStatus;
			return request;
		},
		url : yyoa_context + "/sys/dicItem/searchDicItemByPage.do",
		rowCount : [ 10, 25, 50 ],
		labels : {
			infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
		},
		formatters : {
			"itemStatus" : function(column, row) {
	            var htmlstr = '';
	            if(row.itemStatus == 0){
	            	htmlstr = '启用';
	            }else if(row.itemStatus == 1){
	            	htmlstr = '禁用';
	            }
	            
				return htmlstr;
			},
			"link" : function(column, row) {
				//字典状态
				var itemStatus = "";
				var temp = "";
				if(row.itemStatus == enable){
					temp = "禁用";
					itemStatus = unenable;
				}else if(row.itemStatus == unenable){
					temp = "启用";
					itemStatus = enable;
				}
	            var htmlstr = '';
	            htmlstr = '<span id="manage-reset" onclick="showDicItemInfo('+row.id+',\'detail\')">'+'详情'+'</span>'+'|'
	            + '<span id="manage-delete" onclick="showDicItemInfo('+row.id+',\'edit\')">'+'修改'+'</span>'+'|'
	            + '<span id="manage-use" onclick="deleteByDicItemId('+row.id+')">'+'删除'+'</span>' +'|'
	            + '<span id="manage-use" onclick="updateItemStatus('+row.id+','+itemStatus+')">'+temp+'</span>';
				return htmlstr;
			}
		}
	});
}

//显示字典项列表
function showDicItemList(dicId){
	//设置当前操作的字典id
	$('#add_itemDicId').val(dicId);
	//显示字典项列表页
	$("#dicItemList").fadeIn('slow');
	
	//设置显示列表页
	var listDicItem = $("#listDicItem_span_id");
	listDicItem.show();
	
	changeTab(listDicItem, "dicItemListDiv");
	
	//刷新列表
	$("#dicItem-grid-data").bootgrid("reload", true);
}
//启用、禁用字典项
function updateItemStatus(itemId, itemStatus){
	 $.ajax({
	        type: 'POST',
	        data: {
	        	itemId : itemId,
	        	itemStatus : itemStatus
	        },
	        url: yyoa_context + '/sys/dicItem/updateDicItemStatus.do',
	        dataType: 'json',
	        async: false,
	        success: function (data) {
	            Modal.alert({msg: data.message})
	            if (data.state) {
	            	// 刷新字典项列表
	            	$("#dicItem-grid-data").bootgrid("reload", true);
	            }
	         }
	     });
}
//根据条件查询字典项
function seachDicItemList(){
	 $("#dicItem-grid-data").bootgrid("repage", true);
}
//显示字典项详细信息
function showDicItemInfo(dicItemId,option){
	//根据option显示不同的div：修改div或详情div
	showDicItemDiv(option);
	 $.ajax({
			type:'POST',
			url : yyzc_pro_context + "/sys/dicItem/selectByDicItemId.do",
			data : {
				dicItemId : dicItemId
			},
			dataType:'json',
			async: false,
			success:function(data){
				if(option == 'edit'){
					$("#edit_itemUpdateUser").val(sessionUserId);//修改人
					$("#edit_itemId").val(data.id);//字典项id
				}
				//设置字典项属性值
				$("#" + option + "_itemName").val(data.itemName);
				$("#" + option + "_itemCode").val(data.itemCode);
				$("#" + option + "_itemSort").val(data.itemSort);
			}
	   });
	 
}

//保存添加字典项
function saveAddDicItem(){
	var flag = $.html5Validate.isAllpass($("#addDicItemForm"));
	   if(!flag){
		   return;
	   }else{
		$("#add_itemCreateUser").val(sessionUserId);
		   $.ajax({
				type:'POST',
				url : yyzc_pro_context + "/sys/dicItem/addDicItem.do",
				data : $('#addDicItemForm').serialize(),
				dataType:'json',
				async: false,
				success:function(data){
					Modal.alert({msg: data.message});
					if(data.state){
						// 清理表单
						cancelSaveDicItemForm();
						// 刷新字典项列表
						$("#dicItem-grid-data").bootgrid("reload", true);
					}
				}
		   });
	   }
}

//保存修改的字典项
function saveEditDicItem(){
	var flag = $.html5Validate.isAllpass($("#editDicItemForm"));
	   if(!flag){
		   return;
	   }else{
		   $.ajax({
				type:'POST',
				url : yyzc_pro_context + "/sys/dicItem/updateDicItem.do",
				data : $('#editDicItemForm').serialize(),
				dataType:'json',
				async: false,
				success:function(data){
					Modal.alert({msg: data.message});
					if(data.state){
						// 清理表单
						cancelSaveDicItemForm();
						// 刷新字典列表
						$("#dicItem-grid-data").bootgrid("reload", true);
					}
				}
		   });
	   }
}

//根据字典项id删除字典项
function deleteByDicItemId(itemId){
	Modal.confirm(
			{  msg: "确定删除字典项吗？"
			}) .on( function (e) {
				if(e) {
				   $.ajax({
				        type: 'POST',
				        data: {
				        	dicItemId: itemId
				        },
				        url: yyoa_context + '/sys/dicItem/deleteByDicItemId.do',
				        dataType: 'json',
				        async: false,
				        success: function (data) {
				            Modal.alert({msg: data.message})
				            if (data.state) {
				            	// 刷新字典项列表
				            	$("#dicItem-grid-data").bootgrid("reload", true);
				            }
				         }
				     });
				 }
			});
}

//重置添加字典页面
function cancelSaveDicItemForm(){
	resetForm("addDicItemForm");
	resetForm("editDicItemForm");
	//隐藏修改页面
	hideEditItemDiv();
	//隐藏详情页面
	hideDetailItemDiv();
	
	 // 切换回表格tab
    var listBtn = document.getElementById("listDicItem_span_id");
    changeTab(listBtn, 'dicItemListDiv');
}

//返回字典项列表页
function returnDicItemList(){
	//隐藏修改页面
	hideEditItemDiv();
	//隐藏详情页面
	hideDetailItemDiv();
	//重置添加页面内容
	resetForm("addDicItemForm");
	
	changeTab($("#listDicItem_span_id"), "dicItemListDiv");
	// 刷新字典项列表
	$("#dicItem-grid-data").bootgrid("reload", true);
	
}
//显示添加字典项tab页
function showDicItemAddTab() {
	//隐藏修改页面
	hideEditItemDiv();
	//隐藏详情页面
	hideDetailItemDiv();
	
	var addDic_span = $("#addDicItem_span_id");
	addDic_span.show();

	changeTab(addDic_span, "addDicItemDiv");
}

//显示详情或修改页面
function showDicItemDiv(option){
	if(option == 'edit'){//显示修改页面
		resetForm("editDicItemForm");//重置修改页面
		hideDetailItemDiv();//隐藏详情页面
		showEditItemDiv();//显示修改页面
	}else if(option == 'detail'){//显示详情页面
		resetForm("detailDicItemForm");//重置详情页面
		hideEditItemDiv();//隐藏修改页面
		showDetailItemDiv();//显示详情页面
	}
}


//隐藏修改页面
function hideEditItemDiv(){
	var editDic_span = $("#editDicItem_span_id");
	editDic_span.hide();
}

//隐藏详情页面
function hideDetailItemDiv(){
	var detailDic_span = $("#detailDicItem_span_id");
	detailDic_span.hide();
}

//显示修改页面
function showEditItemDiv(){
	var editDic_span = $("#editDicItem_span_id");
	editDic_span.show();
	
	changeTab(editDic_span, "editDicItemDiv");
}

//显示详情页面
function showDetailItemDiv(){
	var detailDic_span = $("#detailDicItem_span_id");
	detailDic_span.show();
	
	changeTab(detailDic_span, "detailDicItemDiv");
}

//关闭字典项列表页
function closeDicItemPage(){
	$("#dicItemList").fadeOut('slow');
}
