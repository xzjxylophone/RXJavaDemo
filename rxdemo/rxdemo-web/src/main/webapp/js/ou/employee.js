
/**
 * 显示员工列表标签
 * @returns
 */
function showEmployeeListTab() {
	var editEmployee_span = $("#editEmployee_span_id");
	editEmployee_span.hide();

	var detailEmployee_span = $("#detailEmployee_span_id");
	detailEmployee_span.hide();
	
	var listEmployee_span = $("#listEmployee_span_id");
	listEmployee_span.show();
	
	changeTab(listEmployee_span, "listEmployee_div_id");
}
/**
 * 显示员工添加标签
 * @returns
 */
function showEmployeeAddTab() {
	var editEmployee_span = $("#editEmployee_span_id");
	editEmployee_span.hide();
	
	var detailEmployee_span = $("#detailEmployee_span_id");
	detailEmployee_span.hide();
	
	var addEmployee_span = $("#addEmployee_span_id");
	addEmployee_span.show();

	changeTab(addEmployee_span, "addEmployee_div_id");
}
/**
 * 显示员工修改标签
 * @returns
 */
function showEmployeeEditTab(realName) {
	var editEmployee_span = $("#editEmployee_span_id");
	editEmployee_span.show();
	refrehEmployeeEditTabName(realName);
	changeTab(editEmployee_span, "editEmployee_div_id");
}
/**
 * 显示员工详情标签
 * @param realName
 * @returns
 */
function showEmployeeDetailTab(realName) {
	var detailEmployee_span = $("#detailEmployee_span_id");
	detailEmployee_span.show();
	detailEmployee_span.text(realName);
	detailEmployee_span.text("员工详情");
	changeTab(detailEmployee_span, "detailEmployee_div_id");
}


/**
 * 更新编辑tab页上的名称
 * @param realName
 * @returns
 */
function refrehEmployeeEditTabName(realName) {
	var editEmployee_span = $("#editEmployee_span_id");
	editEmployee_span.text(realName);
	editEmployee_span.text("员工编辑");
}

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
        		var addDep_select = $("#dep_select_id");
        		addDep_select.empty();
        		addDep_select.append("<option value=''>请选择</option>");
        		$.each(departments, function(n, value) {
        			var option = "<option value='" + value.id + "'>" + value.name + "</option>";
        			addDep_select.append(option);
        		});
        }
    });
}

/**
 * 清空添加标签下的输入框内容
 * @returns
 */
function clearEmployeeAddInput() {
	$("#addJobNumber_input_id").val("");
	$("#addDuty_input_id").val("");
	$("#addEntryTime_input_id").val("");
	$("#addWorkTime_input_id").val("");
	$("#addAddress_input_id").val("");
	$("#addRegister_input_id").val("");
	$("#addIdNumber_input_id").val("");
	$("#addEnable_input_id").prop("checked",true);
	$("#addAnnualVacate_input_id").val("5");
}

/**
 * 初始化页面时候加载数据
 * @returns
 */
function loadEmployeePage() {
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
            var userName = $("#userName_input_id").val();
            var realName = $("#realName_input_id").val();
            var depId = 0;
            if($("#dep_select_id").val()!=''){
                depId = $("#dep_select_id").val();
            }
            console.log("depId:", depId);
            request.userName = userName;
            request.realName = realName;
            request.depId = depId;
            return request;
        },
        url: yyoa_context+"/ou/employee/searchEmployeeVoByPage.do",
        rowCount: [10, 25, 50],
        labels : {
            infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
        },
        formatters: {
            "link": function(column, row)
            {
                var htmlstr = '';
                if(row.id != 1 ){
                    var status = row.status;
                    var statusString = (status == 1) ? '启用' : '禁用';
                    var destStatus = (status == 1) ? 0 : 1;
                    htmlstr = '<span id="manage-detail" onclick="detailEmployee_span_onclick(' + row.id + ')">'+'详情'+'</span>'+'|'
                        +'<span id="manage-modify" onclick="editEmployee_span_onclick(' + row.id + ')">'+'修改'+'</span>';
                        // +'|'
                        // +'<span id="manage-delete" onclick="deleteEmployee_span_onclick(' + row.id + ')">'+'删除'+'</span>'+'|'
                        // +'<span id="manage-use" onclick="enableEmployee_span_onclick(' + row.id + ',' + destStatus + ')">'+statusString+'</span>';
                }
                return htmlstr;
            }
        }
    });
}

/**
 * 根据输入的条件刷新页面
 * @returns
 */
function refreshEmployeeList () {
    $("#grid-data").bootgrid("reload", true);
}


function searchEmployee_span_onclick() {
    $("#grid-data").bootgrid("repage", true);
}

/**
 * 用户列表标签事件
 * @returns
 */
function listEmployee_span_onclick() {
	showEmployeeListTab();
}

/**
 * 添加员工标签事件
 * @returns
 */
function addEmployee_span_action() {
	var addDep_select = $("#addDep_select_id");
	addDep_select.empty();
	addDep_select.append("<option value=''>请选择</option>");
	$.each(departments, function(n, value) {
		var option = "<option value='" + value.id + "'>" + value.name + "</option>";
		addDep_select.append(option);
	});

	$.ajax({
        type:'POST',
        data:{
        		id:1
        },
        url: yyoa_context +'/ou/user/selectUserListNotInEmployee.do',
        dataType:'json',
        async:false,
        success:function(data) {
        		var addUser_select = $("#addUser_select_id");
        		addUser_select.empty();
	        	$.each(data, function(n, value) {
	        		var option = "<option value='" + value.id + "'>" + value.realName + "</option>";
	        		addUser_select.append(option);
	        	});
        		showEmployeeAddTab();
        }
    });
}



/**
 * 详情
 * @param id
 * @returns
 */
function detailEmployee_span_onclick(id) {
	$.ajax({
		type:'POST',
		data:{employeeId:id},
		url: yyoa_context +'/ou/employee/selectEmployeeVoByEmployeeId.do',
		dataType:'json',
		async:false,
		success:function(data){
			$("#detailUserRealName_input_id").val(data.userRealName);
			$("#detailDepName_input_id").val(data.depName);

			$("#detailDuty_input_id").val(data.duty);
			var sex = data.sex;
			$("#detailSex_input_id").val(sex == 0 ? "男" : "女");

            $("#detailMobile_input_id").val(data.mobile);
			$("#detailEmail_input_id").val(data.email);
			
            $("#detailRegister_input_id").val(data.register);
			$("#detailIdNumber_input_id").val(data.idNumber);

			$("#detailAddress_input_id").val(data.address);
			

			$("#detailJobNumber_input_id").val(data.jobNumber);
			
			
			
			
			var status = data.status;
//			$("#detailStatus_input_id").val(status == 0 ? "启用" : "禁用");
            $("#detailEntryTime_input_id").val(data.entryTimeString);
            $("#detailWorkTime_input_id").val(data.workTimeString);

			showEmployeeDetailTab(data.userRealName);
		}
   });
}

/**
 * 删除员工
 * 已经废弃
 * @param id
 * @returns
 */
function deleteEmployee_span_onclick(id) {
	Modal.confirm({
		msg : "确定要删除员工吗？"
	}).on(
			function(e) {
				if (e) {
					$.ajax({
						type : 'POST',
						data : {
							id : id
						},
						url : yyoa_context + '/ou/employee/deleteById.do',
						dataType : 'json',
						async : false,
						success : function(data) {
							Modal.alert({
								msg : data.message
							});
							if (data.state) {
								refreshEmployeeList();
							}
						}
					});
				}
			});	
}

/**
 * 设置用户启用禁用状态
 * 已经废弃
 * @param id
 * @param boolValue
 * @returns
 */
function enableEmployee_span_onclick(id, boolValue) {
    var tips = "启用";
    if(boolValue){
        tips = "禁用";
    }
    Modal.confirm(
            {
                msg: "确定要"+tips+"用户吗？"
            })
            .on( function (e) {
                if(e){
                    $.ajax({
                        type:'POST',
                        data:{
                            id:id,
                            status:boolValue
                        },
                        url: yyoa_context +'/ou/employee/updateEmployeeStatusByEmployeeId.do',
                        dataType:'json',
                        async:false,
                        success:function(data){
                            if(data.state){
                                refreshEmployeeList();
                                showEmployeeListTab();
                            }
                        }
                    });
                }
            });
}

/**
 * 更新用户事件
 * @param id
 * @returns
 */
function editEmployee_span_onclick(id) {
	var editDep_select = $("#editDep_select_id");
	var editUser_select = $("#editUser_select_id");

	editDep_select.empty();
	editDep_select.append("<option value=''>请选择</option>");

	$.each(departments, function(n, value) {
		var option = "<option value='" + value.id + "'>" + value.name + "</option>";
		editDep_select.append(option);
	});

	
	console.log("edit id:", id);
	$.ajax({
        type:'POST',
        data:{
        		id:id
        },
        url: yyoa_context +'/ou/user/selectUserListNotInEmployee.do',
        dataType:'json',
        async:false,
        success:function(data) {
	        	$.each(data, function(n, value) {
	        		var option = "<option value='" + value.id + "'>" + value.realName + "</option>";
	        		editUser_select.append(option);
	        	});
        }
    });
	
	$.ajax({
		type:'POST',
		data:{employeeId:id},
		url: yyoa_context +'/ou/employee/selectEmployeeVoByEmployeeId.do',
		dataType:'json',
		async:false,
		success:function(data){
			$("#editId_input_id").val(data.id);
			var depId = data.depId;
			var userId = data.userId;
			editDep_select.val(depId);
			editUser_select.val(userId);
			$("#editJobNumber_input_id").val(data.jobNumber);
			$("#editDuty_input_id").val(data.duty);
			$("#editEntryTime_input_id").val(data.entryTimeString);
			$("#editWorkTime_input_id").val(data.workTimeString);
			$("#editAddress_input_id").val(data.address);
			$("#editRegister_input_id").val(data.register);
			$("#editIdNumber_input_id").val(data.idNumber);
			
			var status = data.status;
			var statusStringShow = "";
			var statusStringHide = "";
			if (status == 1) {
				var statusStringShow = "editEnable_input_id";
				var statusStringHide = "editDisable_input_id";
			} else {
				var statusStringShow = "editDisable_input_id";
				var statusStringHide = "editEnable_input_id";
			}
			$(statusStringShow).prop("checked",true);
			
			showEmployeeEditTab(data.realName);
		}
   });
}



function checkWorkTimeAndEntryTime(workTime, entryTime) {
	if (strIsNotEmpty(workTime) && strIsNotEmpty(entryTime)) {
		console.log("workTime:", workTime, ",entryTime:", entryTime);
	    if (entryTime < workTime) {
	    		return false;
	    }
	}
    return true;
}

/**
 * 保存添加员工
 * @returns
 */
function saveAddEmployee_button_onclick() {
	var flag = $.html5Validate.isAllpass($("#addEmployee_form_id"));
	if (!flag) {
		return;
	}
    var depId;
	if($("#addDep_select_id").val()!=''){
        depId = $("#addDep_select_id").val();
    }
    var userId = $("#addUser_select_id").val();
    if (!strIsNotEmpty(userId)) {
		Modal.alert({msg:"请先添加用户"});
    	 	return;
    }
    var jobNumber = $("#addJobNumber_input_id").val();
    var duty = $("#addDuty_input_id").val();
    var entryTime = $("#addEntryTime_input_id").val();
    var workTime = $("#addWorkTime_input_id").val();
    var address = $("#addAddress_input_id").val();
    var register = $("#addRegister_input_id").val();
    var idNumber = $("#addIdNumber_input_id").val();
    
    var check = checkWorkTimeAndEntryTime(workTime, entryTime);
    console.log("check:", check);
    if (!check) {
		Modal.alert({msg:"入职时间不能早于工作时间"});
    		return;
    }
    
    var annualVacate = $("#addAnnualVacate_input_id").val();
    
//    var status = $("input[name='addEmployeeStatus_input_name']:checked").val();
    $.ajax({
        type:'POST',
        data:{
        		depId:depId,
        		userId:userId,
        		jobNumber:jobNumber,
        		duty:duty,
        		entryTime:entryTime,
        		workTime:workTime,
        		address:address,
        		register:register,
        		idNumber:idNumber,
        		annualVacate:annualVacate
        },
        url: yyoa_context +'/oa/remain/addEmployeeAndRemain.do',
        dataType:'json',
        async:false,
        success:function(data){
            Modal.alert({ msg: data.message });
            if(data.state) {
            		clearEmployeeAddInput();
                refreshEmployeeList();
                showEmployeeListTab();
            }
        }
    });
}
/**
 * 取消添加员工
 * @returns
 */
function cancelAddEmployee_button_onclick() {
    showEmployeeListTab();
	clearEmployeeAddInput();
}
/**
 * 保存编辑用户
 * @returns
 */
function saveEditEmployee_button_onclick() {
    var flag = $.html5Validate.isAllpass($("#editEmployee_form_id"));
    if (flag) {
        var id = $("#editId_input_id").val();
        var depId;
        if($("#editDep_select_id").val()!=''){
            depId = $("#editDep_select_id").val();
        }
        var userId = $("#editUser_select_id").val();
        var jobNumber = $("#editJobNumber_input_id").val();
        var duty = $("#editDuty_input_id").val();
        var entryTime = $("#editEntryTime_input_id").val();
        var workTime = $("#editWorkTime_input_id").val();
        var address = $("#editAddress_input_id").val();
        var register = $("#editRegister_input_id").val();
        var idNumber = $("#editIdNumber_input_id").val();
//        var status = $("input[name='editEmployeeStatus_input_name']:checked").val(); 
        
   
        
        var check = checkWorkTimeAndEntryTime(workTime, entryTime);
        console.log("check:", check);
        if (!check) {
    			Modal.alert({msg:"入职时间不能早于工作时间"});
        		return;
        }
        
        $.ajax({
            type:'POST',
            data:{
                id:id,
                depId:depId,
                userId:userId,
                jobNumber:jobNumber,
                duty:duty,
                entryTime:entryTime,
                workTime:workTime,
                address:address,
                register:register,
                idNumber:idNumber
            },
            url: yyoa_context +'/ou/employee/updateEmployee.do',
            dataType:'json',
            async:false,
            success:function(data){
                Modal.alert({ msg: data.message });
                if(data.state){
                    refreshEmployeeList();
//                    refrehEmployeeEditTabName(realName);
                		showEmployeeListTab();
                }
            }
        });
    }
}

/**
 * 取消编辑用户
 * @returns
 */
function cancelEditEmployee_button_onclick() {
	showEmployeeListTab();
}
function cancelDetailEmployee_button_onclick () {
	showEmployeeListTab();
}
















