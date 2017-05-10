var setting = 
{
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};
var zNodes = '';
function setCheck() {
	console.log("type:", type);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
				py = $("#py").attr("checked")? "p":"",
				sy = $("#sy").attr("checked")? "s":"",
				pn = $("#pn").attr("checked")? "p":"",
				sn = $("#sn").attr("checked")? "s":"",
				type = { "Y":py + sy, "N":pn + sn};
	console.log("type:", type);
	zTree.setting.check.chkboxType = type;
	showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
}
function showCode(str) {
	var code = $("#code");
	code.empty();
	code.append("<li>"+str+"</li>");
}

$(document).ready(function() {
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	setCheck();
	$("#py").bind("change", setCheck);
	$("#sy").bind("change", setCheck);
	$("#pn").bind("change", setCheck);
	$("#sn").bind("change", setCheck);
});


/**
 * 更新角色修改上面的标签名称
 * @param realName
 * @returns
 */
function refrehRoleEditTabName(roleName) {
	var editRole_span = $("#editRole_span_id");
	editRole_span.text(roleName);
	editRole_span.text("角色修改");
}


/**
 * 显示角色列表标签
 * @returns
 */
function showRoleListTab() {
	var editRole_span = $("#editRole_span_id");
	editRole_span.hide();

	var detailRole_span = $("#detailRole_span_id");
	detailRole_span.hide();
	
	var listRole_span = $("#listRole_span_id");
	listRole_span.show();
	
	changeTab(listRole_span, "listRole_div_id");
}
/**
 * 显示角色添加标签
 * @returns
 */
function showRoleAddTab() {
	var editRole_span = $("#editRole_span_id");
	editRole_span.hide();
	
	var detailRole_span = $("#detailRole_span_id");
	detailRole_span.hide();
	
	var addRole_span = $("#addRole_span_id");
	addRole_span.show();

	changeTab(addRole_span, "addRole_div_id");
}
/**
 * 显示角色修改标签
 * @returns
 */
function showRoleEditTab(realName) {
	var editRole_span = $("#editRole_span_id");
	editRole_span.show();
	refrehRoleEditTabName(realName);
	changeTab(editRole_span, "editRole_div_id");
}
/**
 * 显示角色详情标签
 * @param realName
 * @returns
 */
function showRoleDetailTab(realName) {
	var detailRole_span = $("#detailRole_span_id");
	detailRole_span.show();
	detailRole_span.text(realName);
	detailRole_span.text("角色详情");
	changeTab(detailRole_span, "detailRole_div_id");
}
function clearRoleAddInput() {
	
}

function showSetMenu() {
    $("#setMenu_div_id").fadeIn('slow');
}
function closeSetMenu() {
    $("#setMenu_div_id").fadeOut('slow');
}

function showSetUser() {
    $("#setUser_div_id").fadeIn('slow');
}
function closeSetUser() {
    $("#setUser_div_id").fadeOut('slow');
}

function loadRolePage() {
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
			var roleParam = $("#roleName").val();
			request.roleName = roleParam;
			return request;
		},
		url : yyoa_context + "/ou/userRole/searchRoleByPage.do",
		rowCount : [ 10, 25, 50 ],
		labels : {
			infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
		},
		formatters : {
			"link" : function(column, row) {
                var htmlstr = '';
                if(row.id != 1) {
					htmlstr = '<span id="manage-reset" onclick="detailRole_span_onclick('+row.id+')">'+'详情'+'</span>'+'|'
                        + '<span id="manage-reset" onclick="setMenu_span_onclick('+row.id+')">'+'设置菜单'+'</span>'+'|'
                        + '<span id="manage-modify" onclick="setUser_span_onclick('+row.id+')">'+'分配用户'+'</span>'+'|'
                        + '<span id="manage-delete" onclick="editRole_span_onclick('+row.id+')">'+'修改'+'</span>'+'|'
                        + '<span id="manage-use" onclick="deleteRole_span_onclick('+row.id+')">'+'删除'+'</span>';
				}
				return htmlstr;
			}
		}
	});
}
function refreshRoleList () {
	$("#grid-data").bootgrid("reload", true);
}

function seachRole_button_onclick() {
    $("#grid-data").bootgrid("repage", true);
}

function listRole_span_onclick() {
	showRoleListTab();
}
function addRole_span_onclick() {
	showRoleAddTab();
}

function saveAddRole_button_onclick() {
    var flag = $.html5Validate.isAllpass($("#addRoleForm"));
	   if(!flag){
        return;
	   }else{
        var roleName = $("#addRoleName").val();
        var sort = $("#addSort").val();
        var remark = $("#addRemark").val();
        $.ajax({
            type:'POST',
            data:{
                roleName:roleName,
                sort:sort,
                remark:remark
            },
            url: yyoa_context +'/ou/userRole/addRole.do',
            dataType:'json',
            async:false,
            success:function(data){
                Modal.alert({msg: data.message})
                if(data.state){
                		clearRoleAddInput();
                    refreshRoleList();
                    showRoleListTab();
                }
            }
        });
	   }

}
function cancelAddRole_button_onclick() {
	showRoleListTab();
}
function saveEditRole_button_onclick() {
	var flag = $.html5Validate.isAllpass($("#editRoleForm"));
    if(!flag){
        return;
    }else{
        var id = $("#editRoleId").val();
        var roleName = $("#editRoleName").val();
        var sort = $("#editSort").val();
        var remark = $("#editRoleRemark").val();
        $.ajax({
            type:'POST',
            data:{
                id:id,
                roleName:roleName,
                sort:sort,
                remark:remark
            },
            url: yyoa_context +'/ou/userRole/updateRole.do',
            dataType:'json',
            async:false,
            success:function(data){
                Modal.alert({msg: data.message})
                if(data.state){
                    refreshRoleList();
                    showRoleListTab();
                }
            }
        });
    }
}
function cancelEditRole_button_onclick() {
	showRoleListTab();
}
function cancelDetailRole_button_onclick () {
	showRoleListTab();
}

function saveSetMenu_button_onclick() {
	var roleId = $("#setMenuWithRoleId_input_id").val();
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var checkedNodes =  zTree.getCheckedNodes(true);
	var ids = '';
	for(var i=0;i<checkedNodes.length;i++){
		if(i==0){
			ids = checkedNodes[i].id;
		}
		else{
			ids = ids + ',' + checkedNodes[i].id
		}
		
	}
	
	

	console.log("okkkkkkkkkkkkkkkkk");
	
	$.ajax({
		type:'POST',
		data:{
			"roleId":roleId,
			"menuIds":ids
		},
		
		url: yyoa_context +'/ou/menu/setRoleMenu.do',
		dataType:'json',
		async:false,
		success:function(data){
            Modal.alert({ msg: "保存成功" });
            closeSetMenu();
			
		}
   });
}
function cancelSetMenu_button_onclick() {
	closeSetMenu();
}
function saveSetUser_button_onclick() {
	var chk_value =[]; 
	$.each($('input:checkbox:checked'),function() {
	   var selectValue = $(this).val();
	   chk_value.push(selectValue);
	});
   
   
	var roleId = $("#setUserWithRoleId_input_id").val();
   var selectValues = chk_value.toString();
   console.log("roleId:", roleId, ",selectValues", selectValues);
   
   $.ajax({
		type:'POST',
		data:{
			roleId:roleId,
			userIds:selectValues
		},
		url: yyoa_context +'/ou/userRole/updateRoleWithUserIds.do',
		dataType:'json',
		async:false,
		success:function(data){
			closeSetUser();
            Modal.alert({ msg: "保存成功" });
		}
   });   
	   
}
function cancelSetUser_button_onclick () {
	closeSetUser();
}







function detailRole_span_onclick(id){
    $.ajax({
        type:'POST',
        data:{id:id},
        url: yyoa_context +'/ou/userRole/findRoleById.do',
        dataType:'json',
        async:false,
        success:function(data){
            $("#detailRoleId").val(data.id);
            $("#detailRoleName").val(data.roleName);
            $("#detailSort").val(data.sort);
            $("#detailRoleRemark").val(data.remark);
            showRoleDetailTab(data.roleName);
        }
    });
}
function setUser_span_onclick(roleId) {
	   $("#setUserWithRoleId_input_id").val(roleId);
	   $.ajax({
			type:'POST',
			data:{
				roleId:roleId,
			},
			url: yyoa_context +'/ou/userRole/selectAllUserRoleVoListByRoleId.do',
			dataType:'json',
			async:false,
			success:function(data){
				console.log("selectAllUserRoleVoListByRoleId success");
				var userCheckbox_tbody = $("#userCheckbox_tbody");
				
				var valueIdFunction = function(value) {
					return value.userId;
				};
				
				var textFunction = function(value) {
					return value.realName + " " + value.depName;
				};
				var selectedFunction = function(value) {
					return value.exist;
				};
				
				var roleCheckbox_tbody = $("#roleCheckbox_tbody");
				var max = max_user_check_row;

				standartShowBatchCheckBox(userCheckbox_tbody, data, max, valueIdFunction, textFunction, selectedFunction);
				
				showSetUser();
				
			}
	   });
}


function setMenu_span_onclick (roleId){
	$("#setMenuWithRoleId_input_id").val(roleId);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	 zTree.destroy();
	 $.ajax({
			type:'POST',
			data:{
				pid:0,
				roleId:roleId
			},
			url: yyoa_context +'/ou/menu/findCheckMenuTreeList.do',
			dataType:'json',
			async:false,
			success:function(data){
				 $.fn.zTree.init($("#treeDemo"), setting, data);
				 
				 showSetMenu();
			}
	   });
	 
}
function editRole_span_onclick(id){
   var editTab = $("#editTabBTN").show();
   $.ajax({
		type:'POST',
		data:{id:id},
		url: yyoa_context +'/ou/userRole/findRoleById.do',
		dataType:'json',
		async:false,
		success:function(data){
			$("#editRoleId").val(data.id);
			$("#editRoleName").val(data.roleName);
			$("#editSort").val(data.sort);
			$("#editRoleRemark").val(data.remark);
			showRoleEditTab(data.roleName);
		}
   });
}
function deleteRole_span_onclick(id){
    Modal.confirm(
    {
        msg: "确定要删除角色吗？"
    })
    .on( function (e) {
        if(e) {
            $.ajax({
                type: 'POST',
                data: {id: id},
                url: yyoa_context + '/ou/userRole/deleteRoleById.do',
                dataType: 'json',
                async: false,
                success: function (data) {
                    Modal.alert({msg: data.message})
                    if (data.state) {
                 	   refreshRoleList();
                    }
                }
            });
        }
    });
}
/**
 * 角色权限-分配用户-右上角关闭按钮事件
 * @returns
 */
function close_x(){
	var role_user_x = $('.absent-layer');
    role_user_x.fadeOut('slow');
}














       


     
   
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   
   
	

	
	
	
	
	
	
	
	
	
	
	