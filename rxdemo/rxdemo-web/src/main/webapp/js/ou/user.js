
// 添加/编辑的时候需要更新的元素(处理用户角色需要用到的)
var m_roleIds_input;
var m_roleNames_input;

/**
 * 设置添加角色权限input的宽度
 * editUserRole_input_id
 */
function addRolChoose(){
    var testLength = document.getElementById('editUserRole_input_id').value.length;
    if(testLength>=12){
        $("#editUserRole_input_id").css({
            "width":testLength*10 + 'px',
            "font-size":"14px"
        });
    }else{
        $("#editUserRole_input_id").css({
            "width":"190px"
        });
	}
}


function addRol(){
    var testLength = document.getElementById('addUserRole_input_id').value.length;
    if(testLength>=10){
        $("#addUserRole_input_id").css({
            "width":testLength*10 + 'px',
            "font-size":"14px"
        });
    }else{
        $("#addUserRole_input_id").css({
            "width":"200px"
        });
    }
}

function rolDetal(){
    var testLength = document.getElementById('detailUserRole_input_id').value.length;
    if(testLength>=10){
        $("#detailUserRole_input_id").css({
            "width":testLength*10 + 'px',
            "font-size":"14px"
        });
    }else{
        $("#detailUserRole_input_id").css({
            "width":"200px"
        });
    }
}


/*function addRolChoose(id){
    var testLength = document.getElementById('id').value.length;
    if(testLength>=12){
        $('#'+id+'').css({
            "width":testLength*15 + 'px',
            "font-size":"14px"
        });
    }else{
        $('#'+id+'').css({
            "width":"190px"
        });
    }
}*/

/**
 * 更新用户编辑上面的标签名称
 * @param realName
 * @returns
 */
function refrehUserEditTabName(realName) {
	var editUser_span = $("#editUser_span_id");
	editUser_span.text(realName);
	editUser_span.text("用户编辑");
}


/**
 * 显示用户列表标签
 * @returns
 */
function showUserListTab() {
	var editUser_span = $("#editUser_span_id");
	editUser_span.hide();

	var detailUser_span = $("#detailUser_span_id");
	detailUser_span.hide();
	
	var listUser_span = $("#listUser_span_id");
	listUser_span.show();
	
	changeTab(listUser_span, "listUser_div_id");
}
/**
 * 显示用户添加标签
 * @returns
 */
function showUserAddTab() {
	var editUser_span = $("#editUser_span_id");
	editUser_span.hide();
	
	var detailUser_span = $("#detailUser_span_id");
	detailUser_span.hide();
	
	var addUser_span = $("#addUser_span_id");
	addUser_span.show();

	changeTab(addUser_span, "addUser_div_id");
}
/**
 * 显示用户修改标签
 * @returns
 */
function showUserEditTab(realName) {
	var editUser_span = $("#editUser_span_id");
	editUser_span.show();
	refrehUserEditTabName(realName);
	changeTab(editUser_span, "editUser_div_id");
}
/**
 * 显示用户详情标签
 * @param realName
 * @returns
 */
function showUserDetailTab(realName) {
	var detailUser_span = $("#detailUser_span_id");
	detailUser_span.show();
	detailUser_span.text(realName);
	detailUser_span.text("用户详情");
	changeTab(detailUser_span, "detailUser_div_id");
}



/**
 * 显示重置密码弹出框
 * @returns
 */
function showResetPwd (){
	var resetPwd_div = $("#resetPwd_div_id");
	resetPwd_div.fadeIn('slow');
}
/**
 * 关闭重置密码弹出框
 * @returns
 */
function closeResetPwd (){
	var resetPwd_div = $("#resetPwd_div_id");
	resetPwd_div.fadeOut('slow');
}

/**
 * 显示配置用户角色弹出框
 * @returns
 */
function showSetRoleForUser () {
	var setRoleForUser_div = $("#setRoleForUser_div_id");
	setRoleForUser_div.fadeIn('slow');
}
/**
 * 关闭配置用户角色弹出框
 * @returns
 */
function closeSetRoleForUser () {
	var setRoleForUser_div = $("#setRoleForUser_div_id");
	setRoleForUser_div.fadeOut('slow');
}

/**
 * 清空添加标签下的输入框内容
 * @returns
 */
function clearUserAddInput() {
	console.log("clearUserAddInput");
	$("#addUserName_input_id").val("");
	$("#addRealName_input_id").val("");
	$("#addMan_input_id").prop("checked",true);
	$("#addPwd_input_id").val("123456");
	$("#addPwdAgain_input_id").val("123456");
	$("#addBirthday_input_id").val("");
	$("#addMobile_input_id").val("");
	$("#addEmail_input_id").val("");
	$("#addEnable_input_id").prop("checked",true);
	$("#addUserRole_span_id").text("请选择角色权限");
	$("#addUserRoleIds_input_id").val("");
}

/**
 * 初始化页面时候加载数据
 * @returns
 */
function loadUserPage() {
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
            request.userName = userName;

            var realName = $("#realName_input_id").val();
            request.realName = realName;
            return request;
        },
        url: yyoa_context+"/ou/user/searchUserByPage.do",
        rowCount: [10, 25, 50],
        labels : {
            infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
        },
        formatters: {
            "status": function(column, row)
            {
                var htmlstr = (row.status == 0) ? '启用' : '禁用';
                return htmlstr;
            },
            "link": function(column, row)
            {
                var htmlstr = '';
                if(row.id != 1 ){
                    var status = row.status;
                    var statusString = (status == 1) ? '启用' : '禁用';
                    var destStatus = (status == 1) ? 0 : 1;
                    htmlstr = '<span id="detailUser_span_id" onclick="detailUser_span_onclick(' + row.id + ')">'+'详情'+'</span>'+'|'
                        +'<span id="resetUserPwd_span_id" onclick="resetUserPwd_span_onclick(' + row.id + ')">'+'重置'+'</span>'+'|'
                        +'<span id="modifyUser_span_id" onclick="modifyUser_span_onclick(' + row.id + ')">'+'修改'+'</span>'+'|'
                        +'<span id="deleteUser_span_id" onclick="deleteUser_span_onclick(' + row.id + ')">'+'删除'+'</span>'+'|'
                        +'<span id="enableUser_span_id" onclick="enableUser_span_onclick(' + row.id + ',' + destStatus + ')">'+statusString+'</span>';
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
function refreshUserList () {
    $("#grid-data").bootgrid("reload", true);
}

/**
 * 用户列表标签事件
 * @returns
 */
function listUser_span_onclick() {
	showUserListTab();
}
/**
 * 用户添加标签事件
 * @returns
 */
function addUser_span_onclick() {
	// 添加用户的时候,选中角色,需要更新的元素
	m_roleIds_input = $("#addUserRoleIds_input_id");
	m_roleNames_input = $("#addUserRole_input_id");
	showUserAddTab();
}

/**
 * 查询按钮事件
 * @returns
 */
function search_button_onclick() {
    $("#grid-data").bootgrid("repage", true);
}


/**
 * 加载选择角色的数据
 * @param 
 * @returns
 */
function loadSetRoleForUser() {
	// 已经选中的数据
	var chk_valueStr = m_roleIds_input.val();
	var chk_values = chk_valueStr.split(",");

	console.log("chk_valueStr:", chk_valueStr);
	console.log("chk_values:", chk_values);
	$.ajax({
		type:'POST',
		data:{
			
		},
		url: yyoa_context +'/ou/userRole/selectAllRole.do',
		dataType:'json',
		async:false,
		success:function(data) {
			var valueIdFunction = function(value) {
				return value.id;
			};
			var textFunction = function(value) {
				return value.roleName;
			};
			var selectedFunction = function(value) {
				var result = 0;
				$.each(chk_values, function(n, chk_value) {
					if (value.id == chk_value) {
						result = 1;
						// 	中断循环
						//	在each代码块内不能使用break和continue,
						//  要实现break和continue的功能的话，要使用其它的方式 
						//	break----用return false; 
						//	continue --用return ture; 
						//	所以在循环中不能直接return.
						return false;
					}
				});
				return result;
			};
			var roleCheckbox_tbody = $("#roleCheckbox_tbody_id");
			standartShowBatchCheckBox(roleCheckbox_tbody, data, 3, valueIdFunction, textFunction, selectedFunction);
			showSetRoleForUser();
		}
   });
}

/**
 * 添加用户的时候,选择角色
 * @returns
 */
function addUserRole_input_id_onclick() {
	console.log("addUserRole_input_id_onclick");
	loadSetRoleForUser();
	addRol();
}

/**
 * 编辑用户的时候,选择角色
 * @returns
 */
function editUserRole_input_id_onclick() {
	console.log("editUserRole_input_id_onclick");
	loadSetRoleForUser();
}


/**
 * 保存用户角色选择
 * @returns
 */
function saveRoleFroUser_button_onclick () {
	var chk_value = []; 
	var chk_text = [];
	$.each($('input:checkbox:checked'),function() {
		   var selectValue = $(this).val();
		   chk_value.push(selectValue);
		   var selectText = $(this).parent().text();
		   console.log("value:", selectValue, ",text:", selectText);
		   chk_text.push(selectText);
    });
	   
	var selectValues = chk_value.toString();
	var selectTexts = chk_text.toString();
	
	m_roleIds_input.val(selectValues);
	m_roleNames_input.val(selectTexts);
	console.log("selectValues:", selectValues);
	console.log("selectTexts:", selectTexts);
	closeSetRoleForUser();
    addRolChoose();
    addRol();
}

/**
 * 取消用户角色选择
 * @returns
 */
function closeRoleFroUser_button_onclick () {
	closeSetRoleForUser();
}


/**
 * 确认重置密码
 * @returns
 */
function okResetPwd_button_onclick() {
    var flag = $.html5Validate.isAllpass($("#resetPwdForm"));
    if (flag) {
        var userId = $("#resetPwdUserId_input_id").val();
        var pwd = $("#resetPwd_input_id").val();
        var pwdAgain = $("#resetPwdAgain_input_id").val();
        if (pwd != pwdAgain) {
            Modal.alert({ msg: "两次密码输入不一致" });
            return;
        }
        $.ajax({
            type:'POST',
            data:{
                id:userId,
                pwd:pwd
            },
            url: yyoa_context +'/ou/user/updateResetPwdByUserId.do',
            dataType:'json',
            async:false,
            success:function(data){
                if(data.state){
                    closeResetPwd();
                    Modal.alert({ msg: "重置密码成功" });
                    $("#resetPwd_input_id").val("");
                    $("#resetPwdAgain_input_id").val("");
                } else {
                    Modal.alert({ msg: data.msg });
                }
            }
        });
    }
}
/**
 * 取消重置密码
 * @returns
 */
function cancelResetPwd_button_onclick() {
	closeResetPwd();
}


/**
 * 保存更新用户
 * @returns
 */
function saveUpdateUser_button_onclick() {
    var flag = $.html5Validate.isAllpass($("#editUser_form_id"));
    if (flag) {
        var userId = $("#editUserId_input_id").val();
        var userName = $("#editUserName_input_id").val();
        var realName = $("#editRealName_input_id").val();
        var sex = $("input[name='editUserSex_input_name']:checked").val(); 

        var birthday = $("#editBirthday_input_id").val();
        var mobile = $("#editMobile_input_id").val();
        var email = $("#editEmail_input_id").val();
        
        var status = $("input[name='editUserStatus_input_name']:checked").val(); 
        console.log("sex:" , sex, ",status:", status);


		var chk_values = m_roleIds_input.val();
		console.log("chk_values:", chk_values);
        
        $.ajax({
            type:'POST',
            data:{
                roleIds:chk_values,
                id:userId,
                userName:userName,
                realName:realName,
                sex:sex,
                birthdate:birthday,
                mobile:mobile,
                email:email,
                status:status
            },
            url: yyoa_context +'/ou/user/updateUserAndRoleIds.do',
            dataType:'json',
            async:false,
            success:function(data){
                Modal.alert({ msg: data.message });
                if(data.state){
                    refreshUserList();
                    showUserListTab();
                }
            }
        });
    }
}

/**
 * 取消更新用户
 * @returns
 */
function cancelUpdateUser_button_onclick() {
	showUserListTab();
}

/**
 * 保存添加用户
 * @returns
 */
function saveAddUser_button_onclick() {
	var flag = $.html5Validate.isAllpass($("#addUser_form_id"));
    if (flag) {
        var userName = $("#addUserName_input_id").val();
        var realName = $("#addRealName_input_id").val();
        var sex = $("input[name='addUserSex_input_name']:checked").val(); 

        var birthday = $("#addBirthday_input_id").val();
        var mobile = $("#addMobile_input_id").val();
        var email = $("#addEmail_input_id").val();
        
        var pwd = $("#addPwd_input_id").val();
        var pwdAgain = $("#addPwdAgain_input_id").val();
        
        var status = $("input[name='addUserStatus_input_name']:checked").val(); 

        if (pwd != pwdAgain) {
            Modal.alert({ msg: "两次密码输入不一致" });
            return;
        }
        
    		var chk_values = m_roleIds_input.val();
    		console.log("chk_values:", chk_values);
        
        $.ajax({
            type:'POST',
            data:{
                roleIds:chk_values,
            		userName:userName,
                realName:realName,
                sex:sex,
                birthdate:birthday,
                mobile:mobile,
                email:email,
                pwd:pwd,
                status:status
            },
            url: yyoa_context +'/ou/user/addUser.do',
            dataType:'json',
            async:false,
            success:function(data){
                Modal.alert({ msg: data.message });
                if(data.state){
                		clearUserAddInput();
                    refreshUserList();
                    showUserListTab();
                }
            }
        });
    }
}
/**
 * 取消添加用户
 * @returns
 */
function cancelAddUser_button_onclick() {
    showUserListTab();
	clearUserAddInput();
}
function cancelDetailUser_button_onclick() {
    showUserListTab();
}

/**
 * 用户详情
 * @param id
 * @returns
 */
function detailUser_span_onclick(id) {
	

	// 用户详情的时候,选中角色
	m_roleIds_input = $("#detailUserRoleIds_input_id");
	m_roleNames_input = $("#detailUserRole_input_id");
	
	$.ajax({
		type:'POST',
		data:{userId:id},
		url: yyoa_context +'/ou/user/selectUser.do',
		dataType:'json',
		async:false,
		success:function(data){
			
			$("#detailUserName_input_id").val(data.userName);
			$("#detailRealName_input_id").val(data.realName);

			var sex = data.sex;
			$("#detailSex_input_id").val(sex == 0 ? "男" : "女");

			$("#detailBirthdate_input_id").val(data.birthdateString);

            $("#detailMobile_input_id").val(data.mobile);
			$("#detailEmail_input_id").val(data.email);
			
			var status = data.status;
			$("#detailStatus_input_id").val(status == 0 ? "启用" : "禁用");

			
			$.ajax({
				type:'POST',
				data:{userId:id},
				url: yyoa_context +'/ou/userRole/selectUserRoleVoListByUserId.do',
				dataType:'json',
				async:false,
				success:function(data){
					var chk_value = []; 
					var chk_text = [];
					$.each(data, function(n, value) {
						chk_value.push(value.roleId);
						chk_text.push(value.roleName);
					});
					
					var selectValues = chk_value.toString();
					var selectTexts = chk_text.toString();
					m_roleIds_input.val(selectValues);
					m_roleNames_input.val(selectTexts);
					showUserDetailTab(data.realName);
				}
		   });
			
		}
   });
}

/**
 * 重置用户密码
 * @param id
 * @returns
 */
function resetUserPwd_span_onclick(id) {
	$("#resetPwdUserId_input_id").val(id);
	showResetPwd();
}
/**
 * 更新用户事件
 * @param id
 * @returns
 */
function modifyUser_span_onclick(id) {

	// 编辑用户的时候,选中角色,需要更新的元素
	m_roleIds_input = $("#editUserRoleIds_input_id");
	m_roleNames_input = $("#editUserRole_input_id");
	
	$.ajax({
		type:'POST',
		data:{userId:id},
		url: yyoa_context +'/ou/user/selectUser.do',
		dataType:'json',
		async:false,
		success:function(data){
			
			$("#editUserId_input_id").val(data.id);
			$("#editUserName_input_id").val(data.userName);
			$("#editRealName_input_id").val(data.realName);
			
			var sex = data.sex;
			console.log("sex:", data.sex);
			var sexStringShow = "";
			var sexStringHide = "";
			if (sex == 0) {
				sexStringShow = "#editMan_input_id";
				sexStringHide = "#editWoman_input_id";
			} else {
				sexStringShow = "#editWoman_input_id";
				sexStringHide = "#editMan_input_id";
			}
			$(sexStringShow).prop("checked",true);
			$("#editBirthday_input_id").val(data.birthdateString);
			$("#editMobile_input_id").val(data.mobile);
			$("#editEmail_input_id").val(data.email);
			
			var status = data.status;
			console.log("status:", data.status);
			var statusStringShow = "";
			var statusStringHide = "";
			if (status == 0) {
				var statusStringShow = "editEnable_input_id";
				var statusStringHide = "editDisable_input_id";
			} else {
				var statusStringShow = "editDisable_input_id";
				var statusStringHide = "editEnable_input_id";
			}
			var  kk = $(statusStringShow);
			console.log("KK:", kk);
			$(statusStringShow).prop("checked",true);
			
			$.ajax({
				type:'POST',
				data:{userId:id},
				url: yyoa_context +'/ou/userRole/selectUserRoleVoListByUserId.do',
				dataType:'json',
				async:false,
				success:function(data){
					var chk_value = []; 
					var chk_text = [];
					$.each(data, function(n, value) {
						chk_value.push(value.roleId);
						chk_text.push(value.roleName);
					});
					
					var selectValues = chk_value.toString();
					var selectTexts = chk_text.toString();
					m_roleIds_input.val(selectValues);
					m_roleNames_input.val(selectTexts);
					
					showUserEditTab(data.realName);
				}
		   });
		}
   });
}

/**
 * 删除用户
 * @param id
 * @returns
 */
function deleteUser_span_onclick(id) {
    Modal.confirm(
        {
            msg: "确定要删除用户吗？"
        })
        .on( function (e) {
            if (e) {
                $.ajax({
                    type: 'POST',
                    data: {userId: id},
                    url: yyoa_context + '/ou/user/deleteUserByUserId.do',
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        Modal.alert({ msg: data.message });
                        if (data.state) {
                            refreshUserList();
                        }
                    }
                });
            }
        });
}
/**
 * 设置用户启用禁用状态
 * @param id
 * @param boolValue
 * @returns
 */
function enableUser_span_onclick(id, boolValue) {
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
                    url: yyoa_context +'/ou/user/updateUserStatusByUserId.do',
                    dataType:'json',
                    async:false,
                    success:function(data){
                        if(data.state){
                            refreshUserList();
                            showUserListTab();
                        }
                        // Modal.alert({msg: data.message});
                    }
                });
            }
        });
}



