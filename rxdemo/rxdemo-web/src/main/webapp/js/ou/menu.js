var treeNodes;  //左侧操作树节点
var treeSetting;  //左侧操作树设置
var isParent;   //是否是父结点

var menuTreeObj;   //左侧主菜单树对象

var currentCheckMenuId;  //当前被选中的菜单ID
var currentCheckMenuName; //当前被选中的菜单名称


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
	treeNodes =[{name:"菜单", id:0, isParent:true, hasExpand:false, open:false}]; //初始化树
	treeSetting = {
		check: {
		  enable: true
		},
		data: {
			simpleData: {
				enable:true,
				idKey: "id",
				pIdKey: "pId",
				rootPId: ""
			}
		},
	    callback: {
	       beforeExpand: treeBeforeExpand,
	       onClick: treeOnClick
	    }
	};
	var t = $("#" + treeObjectId);
	$.fn.zTree.init(t, treeSetting, treeNodes);
	menuTreeObj = $.fn.zTree.getZTreeObj(treeObjectId);
	currentCheckMenuId = 0;  //当前被选中的菜单ID
	currentCheckMenuName = "菜单"; //当前被选中的菜单名称
	
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
// console.log("treeid:",treeId);
//	console.dir(treeNode);
	if(!treeNode.hasExpand){ //结果没有打开
		$.ajax({
			type:'POST',
			data:{pid:treeNode.id},
			url: yyoa_context +'/ou/menu/findMenuTreeList.do',
			dataType:'json',
			success:function(data){
				// console.log("data",data);
				loadTree(data, treeNode);
				treeNode.hasExpand = true;	//父节点打开状态
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
	currentCheckMenuId= treeNode.id;
	currentCheckMenuName = treeNode.name;
	 $("#grid-data").bootgrid("repage", true);
	//initMenuList();
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
	menuTreeObj.removeChildNodes(treeNode);
	//再重新展示出来
	if(data.length > 0){
		menuTreeObj.addNodes(treeNode,data);
	}
	menuTreeObj.updateNode(treeNode);
	menuTreeObj.selectNode(treeNode);
}

/********************************左侧菜单树与其它功能分隔线*****************************/
//menuListDiv   addMenuDiv  editMenuDiv  showAddBtn showListBtn  showEditBtn

/**
 * 
 * @auth wangjing
 * @createTime 2017年3月22日 下午7:09:16
 * @desc 点击列表添加菜单Tab页事件，显示添加页，带入父节点名称和ID
 *
 */
function showAddDiv(){
	changeTab($("#showAddBtn")[0], "addMenuDiv");
	$("#addPid").val(currentCheckMenuId);
	$("#addParentName").val(currentCheckMenuName);
}

/**
 * 保存添加菜单
 * @returns
 */
function saveAddMenu() {
	var flag = $.html5Validate.isAllpass($("#addMenuForm"));
    if (flag) {
        var addName = $("#addName").val();
        var addCode = $("#addCode").val();
        var addMenuStatus = $("input[name='addMenuStatus']:checked").val(); 
        var addUrl = $("#addUrl").val();
        var addSort = $("#addSort").val();
        var addRemark = $("#addRemark").val();
        var addPid = $("#addPid").val();

        $.ajax({
            type:'POST',
            data:{
            	pId:addPid,
            	name:addName,
            	code:addCode,
            	sort:addSort,
                url:addUrl,
                status:addMenuStatus,
                remark:addRemark
            },
            url: yyoa_context +'/ou/menu/addMenu.do',
            dataType:'json',
            success:function(data){
                Modal.alert({ msg: data.message });
                if(data.state){
                	clearMenuAddInput();
                    refreshMenuList();
                    showMenuListTab();
                    // ininTree(true, "menuTree");
                    refreshMenuTree(addPid);
                }
            }
        });
    }
}
/**
 * 取消添加用户
 * @returns
 */
function cancelAddMenu() {
	showMenuListTab();
	clearMenuAddInput();
}

/**
 * 清空添加标签下的输入框内容
 * @returns
 */
function clearMenuAddInput() {
	console.log("clearMenuAddInput");
	$("#addName").val("");
	$("#addCode").val("");
	$("#addMenuStatus").prop("checked",true);
	$("#addUrl").val("");
	$("#addSort").val("");
	$("#addRemark").val("");
	$("#addPid").val("");
}

/**
 * 显示菜单列表标签
 * @returns
 */
function showMenuListTab() {
	var editMenuTab = $("#showEditBtn");
	editMenuTab.hide();
	
	var detailMenuTab = $("#detailMenuBtn");
	detailMenuTab.hide();
	
	var listMenuTab = $("#showListBtn");
	listMenuTab.show();
	
	changeTab(listMenuTab, "menuListDiv");
	
	var leftView = $("#manage-nav2-lef-id");
	leftView.show();
}

/**
 * 显示菜单添加标签
 * @returns
 */
function showMenuAddTab() {
	var editMenuTab = $("#showEditBtn");
	editMenuTab.hide();
	
	var detailMenuTab = $("#detailMenuBtn");
	detailMenuTab.hide();
	
	var listMenuTab = $("#showListBtn");
	listMenuTab.show();
	
	changeTab(listMenuTab, "menuListDiv");
	
	var leftView = $("#manage-nav2-lef-id");
	leftView.hide();
}

/**
 * 更新菜单事件
 * @param id
 * @returns
 */
function modifyMenu(menuId) {
	$.ajax({
		type:'POST',
		data:{id:menuId},
		url: yyoa_context +'/ou/menu/selectMenuById.do',
		dataType:'json',
		success:function(data){
			$("#editMenuId").val(data.id);
			$("#editParentName").val(data.parentName);
	    	 $("#editName").val(data.name);
	         $("#editCode").val(data.code);
	         $("#editUrl").val(data.url);
	         $("#editSort").val(data.sort);
	         $("#editRemark").val(data.remark);
	         $("#editPid").val(data.pId);
			
			var status = data.status;
			var statusStringShow = "";
			var statusStringHide = "";
			if (status == 1) {
				var statusStringShow = "editEnableRadio";
				var statusStringHide = "editDisableRadio";
			} else {
				var statusStringShow = "editDisableRadio";
				var statusStringHide = "editEnableRadio";
			}
			$(statusStringShow).prop("checked",true);
			
			showMenuEditTab();
//			refreshMenuList();
//			ininTree(true, "menuTree");
		}
   });
}

/**
 * 显示菜单修改标签
 * @returns
 */
function showMenuEditTab() {
	var editMenuTab = $("#showEditBtn");
	editMenuTab.show();
//	refrehMenuEditTabName(realName);
	changeTab(editMenuTab, "editMenuDiv");
	var leftView = $("#manage-nav2-lef-id");
	leftView.hide();
}


/**
 * 保存更新菜单
 * @returns
 */
function saveUpdateMenu() {
    var flag = $.html5Validate.isAllpass($("#editMenuForm"));
    if (flag) {
    	 var menuId = $("#editMenuId").val();
    	 var editName = $("#editName").val();
         var editCode = $("#editCode").val();
         var editMenuStatus = $("input[name='editMenuStatus']:checked").val(); 
         var editUrl = $("#editUrl").val();
         var editSort = $("#editSort").val();
         var editRemark = $("#editRemark").val();
         var editPid = $("#editPid").val();
        
        $.ajax({
            type:'POST',
            data:{
                id:menuId,
                pId:editPid,
            	name:editName,
            	code:editCode,
            	sort:editSort,
                url:editUrl,
                status:editMenuStatus,
                remark:editRemark
            },
            url: yyoa_context +'/ou/menu/updateMenu.do',
            dataType:'json',
            success:function(data){
                Modal.alert({ msg: data.message });
                if(data.state){
                    refreshMenuList();
//                    refreshMenuEditTabName(realName);
                    showMenuListTab();
        			// ininTree(true, "menuTree");
                    refreshMenuTree(editPid);
                }
            }
        });
    }
}

/**
 * 取消更新菜单
 * @returns
 */
function cancelUpdateMenu() {
	showMenuListTab();
}

/**
 * 取消菜单详情页面
 * @returns
 */
function cancelEditMenu() {
    showMenuListTab();
}

/**
 * 更新名称
 * @param realName
 * @returns
 */
function refreshMenuEditTabName(realName) {
	var editMenuTab = $("#editMenuTab");
	editMenuTab.text(realName);
	editMenuTab.text("菜单编辑");
}



/**
 * 根据输入的条件刷新页面
 * @returns
 */
function refreshMenuList () {
    $("#grid-data").bootgrid("reload", true);
}

/**
 * 
 * @param id 菜单ID
 * @auth wangjing
 * @createTime 2017年3月22日 下午7:32:04
 * @desc 删除菜单
 *
 */
function deleteMenu(id, pid){
    Modal.confirm(
        {
            msg: "确定要删除菜单吗？"
        })
        .on( function (e) {
            if(e) {
                $.ajax({
                    type: 'POST',
                    data: {id: id},
                    url: yyoa_context + '/ou/menu/deleteMenu.do',
                    dataType: 'json',
                    success: function (data) {
                        if (data.state) {
                            currentCheckMenuId = pid;
//                    initRolePage();
                            refreshMenuList();
                            showMenuListTab();
                            // ininTree(true, "menuTree");
                            refreshMenuTree(pid);
                        }
                        Modal.alert({msg:data.message});
                    }
                });
            }
        });
}

/**
 * 刷新指定节点下内容
 * @param pid
 */
function refreshMenuTree(pid){
    var treeNode = menuTreeObj.getNodeByParam("id",pid,null);
    treeNode.hasExpand = false;
    treeBeforeExpand(pid, treeNode);
}

/**
 * 
 * @param id 菜单ID
 * @auth wangjing 
 * @createTime 2017年3月22日 下午7:25:29
 * @desc 点击修改按钮，查询菜单数据并展示到修改页面，显示修改页面
 *
 */
function showEditDiv(id){
	var editTab = $("#showEditBtn").show();
	$.ajax({
        type:'POST',
        data:{id:id},
        url: yyoa_context +'/ou/menu/selectMenuById.do',
        dataType:'json',
        success:function(data){
            if(data){
            	$("#editPid").val(data.pid);
            	$("#editParentName").val(data.pid);
            	$("#editName").val(data.name);
            	$("#editCode").val(data.code);
            	$("#editSort").val(data.sort);
            	$("#editRemark").val(data.remark);
            	$("#editPid").val(data.pid);
            	$("#editMenuForm").find("input[name='sort']").each(function(){
            		if($(this).val()==data.status){
            			$(this).attr("checked",true);
            		}
            	});
            	changeTab($("#showEditBtn")[0], "editMenuDiv");
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
	$("#addMenuForm")[0].reset();
	changeTab($("#showListBtn")[0], "menuListDiv");
}

/**
 * 
 * @auth wangjing
 * @createTime 2017年3月22日 下午7:04:41
 * @desc 修改页面，取消按钮事件（关闭修改页面，显示列表页面，清空修改页面数据）
 *
 */
function cancleEditDiv(){
	$("#editMenuForm")[0].reset();
	changeTab($("#showListBtn")[0], "menuListDiv");
}
/**
 * 初初化列表及分页
 */
var initMenuList = function () {
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
        	request.pid = currentCheckMenuId;
        	/*if(currentCheckMenuId || currentCheckMenuId==0){
        	}*/
            return request;
        },
        url: yyoa_context+"/ou/menu/findMenuListByPid.do",
        rowCount: [10, 25, 50],
        labels : {
            infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
        },
        formatters: {
            "link": function(column, row){
            	 var status = row.status;
                 var statusString = (status == 1) ? '启用' : '禁用';
                 var destStatus = (status == 1) ? 0 : 1;
                var htmlstr = 
	                	'<span id="detailMenu" onclick="detailMenu('
	                    + row.id + ')">'+'详情'+'</span>'+'|'
                		+'<span id="manage-modify" onclick="modifyMenu('
                        + row.id + ')">'+'修改'+'</span>'+'|'
                        +'<span id="manage-delete" onclick="deleteMenu('
                        + row.id + ','+row.pId+')">'+'删除'+'</span>'+'|'
                		+'<span id="manage-use" onclick="enableMenu(' + row.id + ',' + destStatus + ')">'+statusString+'</span>';
                return htmlstr;
            },
	        "status": function(column, row){
	        	if(row.status==0){
	        		return "启用";
	        	}else{
	        		return "禁用";
	        	}
	        }
        }
    });
}

$(function(){
	currentCheckMenuId = 0;
	initMenuList();
	$("#showListBtn").click(function(){
		//初始化列表
		 $("#grid-data").bootgrid("reload", true);
		//initMenuList();
		//切换菜单列表显示
		changeTab($(this)[0], "menuListDiv");
	})
	$("#showAddBtn").click(function(){
		showAddDiv();
	})
});

// 查询方法
var seachRoleList = function () {
    //重新加载表格
    $("#grid-data").bootgrid("reload", true);
}

/**
 * 设置用户启用禁用状态
 * @param id
 * @param boolValue
 * @returns
 */
function enableMenu(id, boolValue) {
    var tips = "启用";
    if(boolValue){
        tips = "禁用";
    }
    Modal.confirm(
        {
            msg: "确定要"+tips+"菜单吗？"
        })
        .on( function (e) {
            if(e){
                $.ajax({
                    type:'POST',
                    data:{
                        menuId:id,
                        status:boolValue
                    },
                    url: yyoa_context +'/ou/menu/updateMenuStatus.do',
                    dataType:'json',
                    success:function(data){
                        if(data.state){
                            refreshMenuList();
                            showMenuListTab();
                        }
                        Modal.alert({msg: data.message});
                    }
                });
            }
        });

}

/**
 * 菜单详情
 * @param menuId
 * @returns
 */
function detailMenu(menuId) {
	$.ajax({
		type:'POST',
		data:{id:menuId},
		url: yyoa_context +'/ou/menu/selectMenuById.do',
		dataType:'json',
		async:false,
		success:function(data){
			$("#detailParentName").val(data.parentName);
	    	 $("#detailName").val(data.name);
	         $("#detailCode").val(data.code);
	         $("#detailUrl").val(data.url);
	         $("#detailSort").val(data.sort);
	         $("#detailRemark").val(data.remark);
			var status = data.status;
			$("#detailStatus").val(status == 0 ? "启用" : "禁用");
			showMenuDetailTab();
		}
   });
}

/**
 * 显示菜单详情标签
 * @param realName
 * @returns
 */
function showMenuDetailTab(realName) {
	var detailMenuTab = $("#detailMenuBtn");
	detailMenuTab.show();
	detailMenuTab.text(realName);
	detailMenuTab.text("菜单详情");
	changeTab(detailMenuTab, "detailMenu_div_id");
	var leftView = $("#manage-nav2-lef-id");
	leftView.hide();
	
}


