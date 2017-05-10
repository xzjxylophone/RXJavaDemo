var treeNodes;  //左侧操作树节点
var treeSetting;  //左侧操作树设置
var isParent;   //是否是父结点

var OrgTreeObj;   //左侧主部门树对象

var currentCheckOrgId;  //当前被选中的部门ID
var currentCheckOrgName; //当前被选中的部门名称


var editDepartmentPId;

/**
 * 
 * @param isParent 是否是父结点
 * @param treeObjectId 要显示树型的元素的ID<比如Div或者UI的id>
 * @auth wangjing
 * @createTime 2017年3月22日 下午1:58:27
 * @desc 
 *
 */
function ininTree(isParentNode, treeObjectId){
	isParent = isParentNode;
	treeNodes =[{name:"壹壹科技", id:0, isParent:true, hasExpand:false, open:false}]; //初始化树
	treeSetting = {
		check: {
		  enable: true
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId"
			}
		},
	    callback: {
	       beforeExpand: treeBeforeExpand,
	       onClick: treeOnClick
	    }
	};
	var t = $("#" + treeObjectId);
	$.fn.zTree.init(t, treeSetting, treeNodes);
	OrgTreeObj = $.fn.zTree.getZTreeObj(treeObjectId);
	currentCheckOrgId = 0;  //当前被选中的部门ID
	currentCheckOrgName = "壹壹科技"; //当前被选中的部门名称
}

/**
 * 
 * @auth wangjing
 * @createTime 2017年3月29日 下午5:21:26
 * @desc 刷新树型
 *
 */
function refreshOrgTree(pid){
	if(currentCheckOrgId==undefined){
		currentCheckOrgId = 0;
	}
    var treeNode = OrgTreeObj.getNodeByParam("id",pid,null);
    treeNode.hasExpand = false;
    treeBeforeExpand(pid, treeNode);

	// treeNode = OrgTreeObj.getNodeByParam("id",pid,null);
	// treeNode.open = false;
	// treeBeforeExpand(pid, treeNode);
}
/**
 * @parame treeId 树节点ID
 * @parame treeNode 父节点对象
 * @auth wangjing
 * @createTime 2017年3月22日 下午2:00:40
 * @desc 父节点展开前回调函数
 *
 */
function treeBeforeExpand(treeId, treeNode){
    if(!treeNode.hasExpand){ //结果没有打开
		$.ajax({
			type:'POST',
			data:{pId:treeNode.id},
			url: yyoa_context +'/ou/department/getDepartmentTreeByPid.do',
			dataType:'json',
			success:function(data){
				loadTree(data, treeNode);
				treeNode.open = true;	//父节点打开状态
			}
	   });
	}
}

/**
 * 
 * @auth wangjing
 * @createTime 2017年3月22日 下午2:01:18
 * @desc 结点被点击事件
 *
 */
function treeOnClick(event,treeId,treeNode){
	currentCheckOrgId= treeNode.id;
	currentCheckOrgName = treeNode.name;
	$("#grid-data").bootgrid("repage", true);
}

/**
 * 
 * @param data 数据
 * @param treeNode 父节点
 * @auth wangjing
 * @createTime 2017年3月22日 下午2:36:19
 * @desc  逐级加载树
 *
 */
function loadTree(data, treeNode){
	//先清除父节点下的所有子节点
	OrgTreeObj.removeChildNodes(treeNode);
	//再重新展示出来
	if(data.length > 0){
		OrgTreeObj.addNodes(treeNode,data);
	}
	OrgTreeObj.updateNode(treeNode);
	OrgTreeObj.selectNode(treeNode);
}

/********************************左侧部门树与其它功能分隔线*****************************/
//OrgListDiv   addOrgDiv  editOrgDiv  showAddBtn showListBtn  showEditBtn
/**
 * 
 * @auth wangjing
 * @createTime 2017年3月22日 下午7:09:16
 * @desc 点击列表添加部门Tab页事件，显示添加页，带入父节点名称和ID
 *
 */
function showAddDiv(){
	$(".manage-nav2-left").hide();
	changeTab($("#showAddBtn")[0], "addOrgDiv");
	$("#addPid").val(currentCheckOrgId);
	$("#addParentName").val(currentCheckOrgName);
	$("#showEditBtn").hide();
	$("#showViewBtn").hide();
}
/**
 * 
 * @returns
 * @auth wangjing
 * @createTime 2017年3月27日 下午2:13:06
 * @desc 点击TAB页中的列表页面，显示列表页面
 *
 */
function showListPage(el){
	//初始化列表
	 $("#grid-data").bootgrid("reload", true);
	//initMenuList();
	//切换菜单列表显示
	changeTab($(el)[0], "orgListDiv");
	$("#showEditBtn").hide();
	$(".manage-nav2-left").show();
	$("#showViewBtn").hide();
	
}
/**
 * 
 * @returns
 * @auth wangjing
 * @createTime 2017年3月27日 下午2:13:10
 * @desc 保存机构（添加）
 *
 */
function saveAddOrg(){
	var flag = $.html5Validate.isAllpass($("#addOrgForm"));
	if(!flag){
        return;
	}else{
		var pId = $("#addPid").val();
		var name = $("#addName").val();
		var shortName = $("#addShortName").val();
		var code = $("#addCode").val();
		var leaderId = $("#addLeaderId").val();
		var supleaderId = $("#addSupLeaderId").val();
		var punchIn = $("#addPunchIn").val();
		var punchOut = $("#addPunchOut").val();
		var sort = $("#addSort").val();
		var remark = $("#addRemark").val();
		var parameVal = {};

		if(supleaderId==""){
			parameVal = {
				pId:pId,name:name,shortName:shortName,
				code:code,leaderId:leaderId,punchIn:punchIn,
				punchOut:punchOut,sort:sort,remark:remark
			};
		}else{
			parameVal = {
				pId:pId,name:name,shortName:shortName,
				code:code,leaderId:leaderId,punchIn:punchIn,
				punchOut:punchOut,sort:sort,remark:remark,
				supleaderId:supleaderId
			};
			
		}
        $.ajax({
            type:'POST',
            data:parameVal,
            url: yyoa_context +'/ou/department/add.do',
            dataType:'json',
            success:function(data){
                Modal.alert({msg: data.message})
                if(data.state){
                    // 清理表单
	                	cancleAddDiv();
	                	showListPage($("#orgListDiv")[0]);
	                	$(".manage-nav2-left").show();
	                	refreshOrgTree(pId);
	                	$("#grid-data").bootgrid("reload", true);
	                	// 只有成功后,才清空
		                $("#addLeaderId").val("");
		                $("#addSupLeaderId").val("");
	              }
   
            		}
        		});
	 }
}


/**
 * 
 * @param id 部门ID
 * @param 部门父ID
 * @returns
 * @auth wangjing
 * @createTime 2017年3月30日 下午6:00:39
 * @desc 查看部门详情
 *
 */
function viewOrgDetail(id,pid){
	var editTab = $("#showViewBtn").show();
	$(".manage-nav2-left").hide();
	changeTab($("#showViewBtn")[0], "viewOrgDiv");
	currentCheckOrgId = pid;
	$.ajax({
        type:'POST',
        data:{id:id},
        url: yyoa_context +'/ou/department/selectByDepId.do',
        dataType:'json',
        success:function(data){
            if(data){
            	if(!data.parentName || data.parentName==''){
                    $("#viewParentName").val(currentCheckOrgName);
				}
            	$("#viewName").val(data.name);
            	$("#viewShortName").val(data.shortName);
            	$("#viewCode").val(data.code);
            	$("#viewLearderName").val(data.leaderName);
            	$("#viewSupLearderName").val(data.supLeaderName);
            	$("#viewSort").val(data.sort);
            	$("#viewRemark").val(data.remark);
            	$("#viewPunchIn").val(data.punchIn);
            	$("#viewPunchOut").val(data.punchOut);
            	changeTab($("#showviewBtn")[0], "viewOrgDiv");
            }
        }
    });
}

/**
 * 
 * @auth wangjing
 * @createTime 2017年3月27日 下午9:34:16
 * @desc 保存修改后的机构
 *
 */
function saveEditOrg(){
	var flag = $.html5Validate.isAllpass($("#editOrgForm"));
	if(!flag){
        return;
	}else{
		var id = $("#editId").val();
		var pId = $("#editPid").val();
		var name = $("#editName").val();
		var shortName = $("#editShortName").val();
		var code = $("#editCode").val();
		var leaderId = $("#editLeaderId").val();
		var supleaderId = $("#editSupLeaderId").val();
		var punchIn = $("#editPunchIn").val();
		var punchOut = $("#editPunchOut").val();
		var sort = $("#editSort").val();
		var remark = $("#editRemark").val();
		var parameVal = {};
		if(supleaderId==""){
			parameVal = {
				id:id,pId:editDepartmentPId,name:name,shortName:shortName,
				code:code,leaderId:leaderId,punchIn:punchIn,
				punchOut:punchOut,sort:sort,remark:remark
			};
		}else{
			parameVal = {
				id:id,pId:editDepartmentPId,name:name,shortName:shortName,
				code:code,leaderId:leaderId,punchIn:punchIn,
				punchOut:punchOut,sort:sort,remark:remark,
				supleaderId:supleaderId
			};
			
		}

		$.ajax({
			type:'POST',
			data:parameVal,
			url: yyoa_context +'/ou/department/updateDepartment.do',
			dataType:'json',
			success:function(data){
				Modal.alert({msg: data.message})
				if(data.state){
					// 清理表单
					cancleEditDiv();
					showListPage($("#orgListDiv")[0]);
					refreshOrgTree(pId);
					$("#grid-data").bootgrid("reload", true);
					$(".manage-nav2-left").show();
					
	                $("#editLeaderId").val("");
	                $("#editSupLeaderId").val("");
				}
			}
		});
	}
}

/**
 * 
 * @auth wangjing
 * @createTime 2017年3月27日 下午9:30:06
 * @desc 确认弹出框选择人员
 *
 */
function confirmSelect(){
	var len = $("input[type='checkbox']:checked").length;
	if(len>1){
		Modal.alert({msg:"只能选择一个员工"});
		return;
	}
	var selectType = $("#selectType").val();
	var optTpye = $("#optType").val();
	var id = $("input[type='checkbox']:checked").val();
	var name = $("input[type='checkbox']:checked").parent("td").text();
	if(selectType=="leader"){
		if(optTpye=="add"){
			$("#addLeaderId").val(id);
			$("#addLearderName").val(name);
		}else{
			$("#editLeaderId").val(id);
			$("#editLearderName").val(name);
		}
	}else{
		if(optTpye=="add"){
			$("#addSupLeaderId").val(id);
			$("#addSupLearderName").val(name);
		}else{
			$("#editSupLeaderId").val(id);
			$("#editSupLearderName").val(name);
		}
	}
	cancelSelect();
}

/**
 * 
 * @auth wangjing
 * @createTime 2017年3月27日 下午9:29:19
 * @desc 清空弹出框
 *
 */
function cancelSelect(){
	$("#userCheckbox_tbody").html("");
	$("#selectType").val("");
	$("#optType").val("");
	$("#selectLeaderDiv").hide();
}
/**
 * 
 * @param selectType 选择类型  leader || supleader
 * @param optType 操作类型 add || edit
 * @auth wangjing
 * @createTime 2017年3月27日 下午4:12:38
 * @desc 选择主管或选择部门主管
 *
 */
function selectLeader(selectType, optType) {
	
	var selectId; // 获取选中的那个Id
	if(selectType=="leader"){
		if(optType=="add"){
			selectId = $("#addLeaderId").val();
		}else{
			selectId = $("#editLeaderId").val();
		}
	}else{
		if(optType=="add"){
			selectId = $("#addSupLeaderId").val();
		}else{
			selectId = $("#editSupLeaderId").val();
		}
	}
	
	if(selectType=="leader"){
		$("#winTitle").html("选择部门负责人");
		$("#selectType").val(selectType);
		$("#optType").val(optType);
	}else{
		$("#winTitle").html("选择部门主管");
		$("#selectType").val(selectType);
		$("#optType").val(optType);
	}
	$.ajax({
		type : 'POST',
		url : yyoa_context + '/ou/employee/loadAllEmployeeVo.do',
		success : function(data) {
			var userCheckbox_tbody = $("#userCheckbox_tbody");
			userCheckbox_tbody.empty();

			var max = 3;

			var appendString = "";
			$.each($.parseJSON(data),function(n, value) {

				var remain = n % max;
				var tmpAppendString = "<input type='checkbox' value='"
						+ value.userId
						+ "'  name='header'/>"
						+ value.userRealName + " " + value.depName;
				
				if (value.userId == selectId) {
					tmpAppendString = "<input type='checkbox' checked='checked' value='"
							+ value.userId
							+ "' name='userDispatch_name' />"
							+ value.userRealName + " " + value.depName;
				}

				if (remain == 0) {
					appendString += "<tr>"
				}
				appendString += "<td style='text-align:left;'>"
						+ tmpAppendString + "</td>";
				if (n != 0 && remain == max - 1) {
					appendString += "</tr>";
					userCheckbox_tbody
							.append(appendString);
					appendString = "";
				}

			});

			if (appendString != "") {
				appendString += "</tr>";
				userCheckbox_tbody.append(appendString);
				appendString = "";
			}
		}
	});
	$("#selectLeaderDiv").show();
}
/**
 * 
 * @param id 部门ID
 * @param pid 父id
 * @auth wangjing
 * @createTime 2017年3月22日 下午7:32:04
 * @desc 删除部门
 * 
 */
function delOrg(id, pid){
	currentCheckOrgId = pid;
	Modal.confirm({msg: "确定要删除部门吗？"}).on( function (e) {
		if(e) {
	        $.ajax({
	            type: 'POST',
	            data: {id: id},
	            url: yyoa_context + '/ou/department/delete.do',
	            dataType: 'json',
	            success: function (data) {
	            	Modal.alert({msg:data.message});
	                if (data.state) {
	                	currentCheckOrgId = 0;
	                	$("#grid-data").bootgrid("reload", true);
	                	refreshOrgTree(pid);
	                }
	            }
	        });
		}
	});
}

/**
 * @param id 部门ID
 * @param pid 父id
 * @auth wangjing 
 * @createTime 2017年3月22日 下午7:25:29
 * @desc 点击修改按钮，查询部门数据并展示到修改页面，显示修改页面
 *
 */
function showEditDiv(id, pid){
	var editTab = $("#showEditBtn").show();
	$(".manage-nav2-left").hide();
	currentCheckOrgId = pid;
	$.ajax({
        type:'POST',
        data:{id:id},
        url: yyoa_context +'/ou/department/selectByDepId.do',
        dataType:'json',
        success:function(data){
            if(data){
                if(!data.parentName || data.parentName==''){
                    $("#editParentName").val(currentCheckOrgName);
                }
                if(!data.pid || data.pid==0){
                    $("#editPid").val(currentCheckOrgId);
                }
                editDepartmentPId = data.pId;
            	$("#editId").val(data.id);

            	$("#editName").val(data.name);
            	$("#editShortName").val(data.shortName);
            	$("#editCode").val(data.code);
            	$("#editLearderName").val(data.leaderName);
            	$("#editLeaderId").val(data.leaderId);
            	$("#editSupLearderName").val(data.supLeaderName);
            	$("#editSupLeaderId").val(data.supleaderId);
            	$("#editSort").val(data.sort);
            	$("#editRemark").val(data.remark);
            	if(data.punchIn!=null && data.punchIn !=""){
            		$("#editPunchIn").val(data.punchIn);
            	}else{
            		$("#editPunchIn").val("9:00");
            	}
            	if(data.punchOut!=null && data.punchOut !=""){
            		$("#editPunchOut").val(data.punchOut);
            	}else{
            		$("#editPunchOut").val("18:00");
            	}
            	changeTab($("#showEditBtn")[0], "editOrgDiv");
            }
        }
    });
}

/**
 * 
 * @auth wangjing
 * @createTime 2017年3月22日 下午7:04:41
 * @desc 添加页面，取消按钮事件（关闭添加页面，显示列表页面，清空添加页面数据）
 *
 */
function cancleAddDiv(){
	$("#addOrgForm")[0].reset();
	$("#addPid").val(currentCheckOrgId);
	$("#addParentName").val(currentCheckOrgName);
	showListPage($("#showListBtn")[0]);
}

/**
 * 
 * @auth wangjing
 * @createTime 2017年3月22日 下午7:04:41
 * @desc 修改页面，取消按钮事件（关闭修改页面，显示列表页面，清空修改页面数据）
 *
 */
function cancleEditDiv(){
	$("#editOrgForm")[0].reset();
	showListPage($("#showListBtn")[0]);
}

function cancleDetailDiv(){
    $("#detailOrgForm")[0].reset();
    showListPage($("#showListBtn")[0]);
}
/**
 * 初始化列表及分页
 */
var initOrgList = function () {
    $("#grid-data").bootgrid({
        sorting:false,
        navigation:3,
        columnSelection: false, //启用或禁用下拉框隐藏/显示列。默认值为true
        ajax: true,
        ajaxSettings: {
            method: "POST",
            cache: false
        },
        requestHandler: function (request) { //自定义参数处理
        	if(currentCheckOrgId==undefined){
        		currentCheckOrgId = 0;
        	}
        	request.pId = currentCheckOrgId;
            return request;
        },
        url: yyoa_context+"/ou/department/findOrgPageByPid.do",
        rowCount: [10, 25, 50],
        labels : {
            infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
        },
        formatters: {
            "link": function(column, row){
                var htmlstr = '<span id="manage-modify" onclick="viewOrgDetail('
                		+ row.id + ',' + row.pId + ')">'+'详情'+'</span>'+'|'
                		+ '<span id="manage-modify" onclick="showEditDiv('
                        + row.id + ',' + row.pId + ')">'+'修改'+'</span>'+'|'
                        +'<span id="manage-delete" onclick="delOrg('
                        + row.id + ',' + row.pId +')">'+'删除'+'</span>';
                return htmlstr;
            }
        }

    });
}



$(function(){
	ininTree(true, "orgTree");	//初始化，加载树型
	currentCheckOrgId = 0;
	initOrgList();
	$("#showListBtn").click(function(){
		//初始化列表
		 $("#grid-data").bootgrid("reload", true);
		initOrgList();
		//切换部门列表显示
		changeTab($(this)[0], "OrgListDiv");
	})
	$("#showAddBtn").click(function(){
		showAddDiv();
	})
	refreshOrgTree(0);
});

