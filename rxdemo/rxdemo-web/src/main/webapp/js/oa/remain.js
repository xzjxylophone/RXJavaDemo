

/**
 * 加载部门列表,部门列表是固定的
 * @returns
 */
function loadDepartmentList() {
	$.ajax({
        type:'POST',
        data:{
        },
        url: yyoa_context +'/ou/department/selectAll.do',
        dataType:'json',
        async:false,
        success:function(data) {
        		departments = data;
        		
        		var searchDep_select = $("#searchDep_select_id");
        		searchDep_select.empty();
        		searchDep_select.append("<option value=''>请选择</option>");
        		$.each(data, function(n, value) {
        			var option = "<option value='" + value.id + "'>" + value.name + "</option>";
        			searchDep_select.append(option);
        		});
        }
    });
}







/**
 * 更新员工休假编辑上面的标签名称
 * @param realName
 * @returns
 */
function refrehRemainEditTabName(realName) {
	var editRemain_span = $("#editRemain_span_id");
	editRemain_span.text(realName);
	editRemain_span.text("调整");
}


/**
 * 显示员工休假列表标签
 * @returns
 */
function showRemainListTab() {
	var editRemain_span = $("#editRemain_span_id");
	editRemain_span.hide();

	var detailRemain_span = $("#detailRemain_span_id");
	detailRemain_span.hide();
	
	var listRemain_span = $("#listRemain_span_id");
	listRemain_span.show();
	
	changeTab(listRemain_span, "listRemain_div_id");
}
/**
 * 显示员工休假修改标签
 * @returns
 */
function showRemainEditTab(realName) {
	var editRemain_span = $("#editRemain_span_id");
	editRemain_span.show();
	refrehRemainEditTabName(realName);
	changeTab(editRemain_span, "editRemain_div_id");
}
/**
 * 显示员工休假详情标签
 * @param realName
 * @returns
 */
function showRemainDetailTab(realName) {
	var detailRemain_span = $("#detailRemain_span_id");
	detailRemain_span.show();
	detailRemain_span.text(realName);
	detailRemain_span.text("员工休假详情");
	changeTab(detailRemain_span, "detailRemain_div_id");
}




/**
 * 清空添加标签下的输入框内容
 * @returns
 */
function clearRemainAddInput() {
	console.log("clearRemainAddInput");
	$("#addRemainName_input_id").val("");
	$("#addRealName_input_id").val("");
	$("#addMan_input_id").prop("checked",true);
	$("#addPwd_input_id").val("123456");
	$("#addPwdAgain_input_id").val("123456");
	$("#addBirthday_input_id").val("");
	$("#addMobile_input_id").val("");
	$("#addEmail_input_id").val("");
	$("#addEnable_input_id").prop("checked",true);
	$("#addRemainRole_span_id").text("请选择角色权限");
	$("#addRemainRoleIds_input_id").val("");
}

/**
 * 初始化页面时候加载数据
 * @returns
 */
function loadRemainPage() {
	$("#grid-data").bootgrid({
        sorting:false,
        navigation:3, // Default value is 3. 0 for none, 1 for header, 2 for footer and 3 for both.
        columnSelection: false, //启用或禁用下拉框隐藏/显示列。默认值为true
        ajax: true,
        ajaxSettings: {
            method: "POST",
            cache: false
        },
        requestHandler: function (request) { //自定义参数处理
            var jobNumber = $("#searchJobNumber_input_id").val();
            var realName = $("#searchRealName_input_id").val();
            var depId = 0;
        		if($("#searchDep_select_id").val()!=''){
                depId = $("#searchDep_select_id").val();
            }
        		console.log("jobNumber:", jobNumber, ",realName:", realName, ",depId:", depId)
            request.depId = depId;
            request.jobNumber = jobNumber;
            request.realName = realName;
            return request;
        },
        url: yyoa_context+"/oa/remain/searchRemainByPage.do",
        clickToSelect:true,
        rowCount: [10, 25, 50],
        labels : {
            infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
        },
        formatters: {
//	        	"checkbox_id": function(column, row) {
//	    			var htmlstr = "<input type='checkbox' name='checkbox' value=" +
//	    			row.id + " onchange='rowIndex_input_id_onchange()' />" + row.index;
//	    			return htmlstr;
//	    		},
        		"totalAnnualVacate": function(column, row) {
        			var htmlstr = row.totalAnnualVacate / 8;
        			return htmlstr;
        		},
        		"notUsedAnnualVacate": function(column, row)
            {
        			var htmlstr = row.notUsedAnnualVacate / 8;
        			return htmlstr;
            },
            "link": function(column, row)
            {
                var htmlstr = '<span id="detailRemain_span_id" onclick="detailRemain_span_onclick(' + row.id + ')">'+'详情'+'</span>'+'|'
                        +'<span id="modifyRemain_span_id" onclick="modifyRemain_span_onclick(' + row.id + ')">'+'调整'+'</span>';
                return htmlstr;
            }
        }
    });
}
/**
 * 根据输入的条件刷新页面
 * @returns
 */
function refreshRemainList () {
    $("#grid-data").bootgrid("reload", true);
}

/**
 * 员工休假列表标签事件
 * @returns
 */
function listRemain_span_onclick() {
	showRemainListTab();
}

/**
 * 查询按钮事件
 * @returns
 */
function search_button_onclick() {
    $("#grid-data").bootgrid("repage", true);
}

//function selectAll_input_id_onchange() {
//	var checked = $("#selectAll_input_id").is(':checked');
//	console.log("checked:", checked);
//	checkAll(checked);
//}
//function checkAll(checked) {
//	var code_Values = document.getElementsByTagName("input");
//	for(i = 0;i < code_Values.length;i++){
//		if(code_Values[i].type == "checkbox") {
//			code_Values[i].checked = checked;
//		}
//	}
//}
//function rowIndex_input_id_onchange() {
//	var table = $("#grid-data");
//	var rowLength = table.bootgrid("getRowCount");
//	var selectItems = getCheckboxItems();
//	var length = selectItems.length;
//
//	console.log("length:", length, ",rowLength:", rowLength);
//	var checked = rowLength == length;
//	console.log("checked:", checked);
//
//
//	var selectAllCheck = $("#selectAll_input_id");
//	console.log("selectAllCheck:", selectAllCheck);
//	console.log("selectAllCheck.val:", selectAllCheck.val());
//	selectAllCheck.prop("checked", checked);
////	selectAllCheck.checked22222 = checked;
//
//
////	var code_Values = document.getElementsByTagName("input");
////	for(i = 0;i < code_Values.length;i++){
////		if(code_Values[i].type == "checkbox" && code_Values[i].value==99999999) {
////			console.log("code_Values[i]:", code_Values[i]);
////			code_Values[i].checked = checked;
////		}
////	}
//
//}



function getCheckboxItems() {
	var chk_value = [];
	var table = $("#grid-data");
	var selectRows = table.bootgrid("getSelectedRows");
	console.log("selectRows:", selectRows);
	$.each(selectRows, function(n, value) {
		chk_value.push(value);
	});
	// 这种方法得到的结果跟selectRows是一样的
	return chk_value;
}

function deselectTableRows() {
	var selectItems = getCheckboxItems();
	var table = $("#grid-data");
	var selectRows = table.bootgrid("deselect", selectItems);
}

function setZero_button_onclick() {
	var selectItems = getCheckboxItems();
	console.log("selectItems:", selectItems);
	console.log("selectItems.length:", selectItems.length);
	if (selectItems.length == 0) {
		Modal.alert({ msg: "请先选择员工" });
		return;
	}
	var selectValues = selectItems.toString();
	console.log("selectValues:", selectValues);
	$.ajax({
        type:'POST',
        data:{
        		remainIds:selectValues
        },
        url: yyoa_context +'/oa/remain/updateBatchSetZeroLeaveRemainInfo.do',
        dataType:'json',
        async:false,
        success:function(data){
            if(data.state){
                Modal.alert({ msg: "剩余倒休归零成功" });
                deselectTableRows();
                refreshRemainList();
            } else {
                Modal.alert({ msg: data.msg });
            }
        }
    });
}

function revert_button_onclick() {
	var selectItems = getCheckboxItems();
	if (selectItems.length == 0) {
		Modal.alert({ msg: "请先选择员工" });
		return;
	}
	var selectValues = selectItems.toString();
	console.log("selectValues:", selectValues);
	$.ajax({
        type:'POST',
        data:{
        		remainIds:selectValues
        },
        url: yyoa_context +'/oa/remain/updateBatchRevertAnnualVacateRemainInfo.do',
        dataType:'json',
        async:false,
        success:function(data){
            if(data.state){
                Modal.alert({ msg: "剩余年假成功" });
                deselectTableRows();
                refreshRemainList();
            } else {
                Modal.alert({ msg: data.msg });
            }
        }
    });
}




/**
 * 保存更新员工休假
 * @returns
 */
function saveUpdateRemain_button_onclick() {
    var flag = $.html5Validate.isAllpass($("#editRemain_form_id"));
    if (flag) {


		var remainId = $("#editRemainId_input_id").val();
		var empId = $("#editEmpId_input_id").val();
		var empId = $("#editEmpId_input_id").val();
		var totalAnnualVacate = $("#editTotalAnnualVacate_input_id").val() * 8;
        var notUsedAnnualVacate = $("#editNotUsedAnnualVacate_input_id").val() * 8;
		var notUsedLeave = $("#editNotUsedLeave_input_id").val();
        
        $.ajax({
            type:'POST',
            data:{
            		remainId:remainId,
            		empId:empId,
            		totalAnnualVacate:totalAnnualVacate,
            		notUsedAnnualVacate:notUsedAnnualVacate,
            		notUsedLeave:notUsedLeave
            },
            url: yyoa_context +'/oa/remain/updateRemain.do',
            dataType:'json',
            async:false,
            success:function(data){
                Modal.alert({ msg: data.message });
                if(data.state){
                    refreshRemainList();
                    showRemainListTab();
                }
            }
        });
    }
}

/**
 * 取消更新员工休假
 * @returns
 */
function cancelUpdateRemain_button_onclick() {
	showRemainListTab();
}

function cancelDetailRemain_button_onclick() {
    showRemainListTab();
}

/**
 * 员工休假详情
 * @param id
 * @returns
 */
function detailRemain_span_onclick(id) {
	$.ajax({
		type:'POST',
		data:{id:id},
		url: yyoa_context +'/oa/remain/selectRemainVoById.do',
		dataType:'json',
		async:false,
		success:function(data){
			$("#detailJobNumber_input_id").val(data.jobNumber);
            $("#detailRealName_input_id").val(data.realName);
			$("#detailDepName_input_id").val(data.depName);
			$("#detailTotalAnnualVacate_input_id").val(data.totalAnnualVacate / 8);
            $("#detailNotUsedAnnualVacate_input_id").val(data.notUsedAnnualVacate / 8);
			$("#detailNotUsedLeave_input_id").val(data.notUsedLeave);
			showRemainDetailTab(data.realName);
		}
   });
}

/**
 * 更新员工休假事件
 * @param id
 * @returns
 */
function modifyRemain_span_onclick(id) {
	
	$.ajax({
		type:'POST',
		data:{id:id},
		url: yyoa_context +'/oa/remain/selectRemainVoById.do',
		dataType:'json',
		async:false,
		success:function(data) {
			$("#editRemainId_input_id").val(data.id);
			$("#editEmpId_input_id").val(data.empId);
			$("#editJobNumber_input_id").val(data.jobNumber);
            $("#editRealName_input_id").val(data.realName);
			$("#editDepName_input_id").val(data.depName);
			$("#editTotalAnnualVacate_input_id").val(data.totalAnnualVacate / 8);
            $("#editNotUsedAnnualVacate_input_id").val(data.notUsedAnnualVacate / 8);
			$("#editNotUsedLeave_input_id").val(data.notUsedLeave);
			showRemainEditTab(data.realName);
		}
   });
}



