/**
 * 更新系统配置编辑上面的标签名称
 * @param realName
 * @returns
 */
function refrehConfigEditTabName(realName) {
	var editConfig_span = $("#editConfig_span_id");
	editConfig_span.text(realName);
	editConfig_span.text("系统配置编辑");
}

/**
 * 配置类型转换为字符串
 * @param configType
 * @returns
 */
function stringFromConfigType(configType) {
	var htmlStr = "";
	switch(configType) {
		case 1:
			htmlStr = "只读";
			break;
		case 2:
			htmlStr = "可维护";
			break;
		case 3:
		default:
			htmlStr = "可修改不可删除";
			break;
	}
	return htmlStr;
}


/**
 * 显示系统配置列表标签
 * @returns
 */
function showConfigListTab() {
	var editConfig_span = $("#editConfig_span_id");
	editConfig_span.hide();

	var detailConfig_span = $("#detailConfig_span_id");
	detailConfig_span.hide();
	
	var listConfig_span = $("#listConfig_span_id");
	listConfig_span.show();
	
	changeTab(listConfig_span, "listConfig_div_id");
}
/**
 * 显示系统配置添加标签
 * @returns
 */
function showConfigAddTab() {
	var editConfig_span = $("#editConfig_span_id");
	editConfig_span.hide();
	
	var detailConfig_span = $("#detailConfig_span_id");
	detailConfig_span.hide();
	
	var addConfig_span = $("#addConfig_span_id");
	addConfig_span.show();

	changeTab(addConfig_span, "addConfig_div_id");
}
/**
 * 显示系统配置修改标签
 * @returns
 */
function showConfigEditTab(realName) {
	var editConfig_span = $("#editConfig_span_id");
	editConfig_span.show();
	refrehConfigEditTabName(realName);
	changeTab(editConfig_span, "editConfig_div_id");
}
/**
 * 显示系统配置详情标签
 * @param realName
 * @returns
 */
function showConfigDetailTab(realName) {
	var detailConfig_span = $("#detailConfig_span_id");
	detailConfig_span.show();
	detailConfig_span.text(realName);
	detailConfig_span.text("系统配置详情");
	changeTab(detailConfig_span, "detailConfig_div_id");
}



/**
 * 清空添加标签下的输入框内容
 * @returns
 */
function clearConfigAddInput() {
	$("#addConfigName_input_id").val("");
	$("#addConfigType_select_id").val(1);
	$("#addConfigKey_input_id").val("");
	$("#addConfigValue_input_id").val("");
	$("#addConfigDesc_textarea_id").val("");
}

/**
 * 初始化页面时候加载数据
 * @returns
 */
function loadConfigPage() {
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
            var configName = $("#configName_input_id").val();
            request.configName = configName;

            var configKey = $("#configKey_input_id").val();
            request.configKey = configKey;
            return request;
        },
        url: yyoa_context+"/sys/conf/searchConfigByPage.do",
        rowCount: [10, 25, 50],
        labels : {
            infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
        },
        formatters: {
            "confType": function(column, row)
            {
            		return stringFromConfigType(row.confType);
            },
            "link": function(column, row)
            {
                var htmlstr = '';
                htmlstr = '<span id="detailConfig_span_id" onclick="detailConfig_span_onclick(' + row.id + ')">'+'详情'+'</span>';
                switch(row.confType) {
		        		case 1: // 只读
		        			break;
		        		case 2: // 可维护
		        		{
		        			htmlstr += '|';
		        			htmlstr += '<span id="modifyConfig_span_id" onclick="modifyConfig_span_onclick(' + row.id + ')">'+'修改'+'</span>'+'|';
		        			htmlstr += '<span id="deleteConfig_span_id" onclick="deleteConfig_span_onclick(' + row.id + ')">'+'删除'+'</span>';
		        		}
		        			break;
		        		case 3: // 可修改不可删除
		        		default:
		        		{
		        			htmlstr += '|';
		        			htmlstr += '<span id="modifyConfig_span_id" onclick="modifyConfig_span_onclick(' + row.id + ')">'+'修改'+'</span>';
		        		}
		        			break;
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
function refreshConfigList () {
    $("#grid-data").bootgrid("reload", true);
}

/**
 * 系统配置列表标签事件
 * @returns
 */
function listConfig_span_onclick() {
	showConfigListTab();
}
/**
 * 系统配置添加标签事件
 * @returns
 */
function addConfig_span_onclick() {
	showConfigAddTab();
}

/**
 * 查询按钮事件
 * @returns
 */
function search_button_onclick() {
    $("#grid-data").bootgrid("repage", true);
}


/**
 * 保存更新系统配置
 * @returns
 */
function saveUpdateConfig_button_onclick() {
    var flag = $.html5Validate.isAllpass($("#editConfig_form_id"));
    if (flag) {
        var id = $("#editConfigId_input_id").val();
        var confName = $("#editConfigName_input_id").val();
		var confType = $("#editConfigType_select_id").val();
        var confKey = $("#editConfigKey_input_id").val();
        var confValue = $("#editConfigValue_input_id").val();
        var confDesc = $("#editConfigDesc_textarea_id").val();
        
        $.ajax({
            type:'POST',
            data:{
                id:id,
                confName:confName,
                confType:confType,
                confKey:confKey,
                confValue:confValue,
                confDesc:confDesc
            },
            url: yyoa_context +'/sys/conf/updateConfig.do',
            dataType:'json',
            async:false,
            success:function(data){
                Modal.alert({ msg: data.message });
                if(data.state){
                    refreshConfigList();
                    showConfigListTab();
                }
            }
        });
    }
}

/**
 * 取消更新系统配置
 * @returns
 */
function cancelUpdateConfig_button_onclick() {
	showConfigListTab();
}

/**
 * 保存添加系统配置
 * @returns
 */
function saveAddConfig_button_onclick() {
	var flag = $.html5Validate.isAllpass($("#addConfig_form_id"));
    if (flag) {
        var confName = $("#addConfigName_input_id").val();
		var confType = $("#addConfigType_select_id").val();
        var confKey = $("#addConfigKey_input_id").val();
        var confValue = $("#addConfigValue_input_id").val();
        var confDesc = $("#addConfigDesc_textarea_id").val();
        
        $.ajax({
            type:'POST',
            data:{
            		confName:confName,
            		confType:confType,
            		confKey:confKey,
            		confValue:confValue,
            		confDesc:confDesc
            },
            url: yyoa_context +'/sys/conf/addConfig.do',
            dataType:'json',
            async:false,
            success:function(data){
                Modal.alert({ msg: data.message });
                if(data.state){
                		clearConfigAddInput();
                    refreshConfigList();
                    showConfigListTab();
                }
            }
        });
    }
}
/**
 * 取消添加系统配置
 * @returns
 */
function cancelAddConfig_button_onclick() {
    showConfigListTab();
	clearConfigAddInput();
}
function cancelDetailConfig_button_onclick() {
    showConfigListTab();
}

/**
 * 系统配置详情
 * @param id
 * @returns
 */
function detailConfig_span_onclick(id) {
	$.ajax({
		type:'POST',
		data:{id:id},
		url: yyoa_context +'/sys/conf/selectByConfigId.do',
		dataType:'json',
		async:false,
		success:function(data){
			$("#detailConfigName_input_id").val(data.confName);
			$("#detailConfigType_input_id").val(stringFromConfigType(data.confType));
			$("#detailConfigKey_input_id").val(data.confKey);
			$("#detailConfigValue_input_id").val(data.confValue);
			$("#detailConfigDesc_textarea_id").val(data.confDesc);
			showConfigDetailTab(data.confName);
		}
		
   });
}

/**
 * 更新系统配置事件
 * @param id
 * @returns
 */
function modifyConfig_span_onclick(id) {
	$.ajax({
		type:'POST',
		data:{id:id},
		url: yyoa_context +'/sys/conf/selectByConfigId.do',
		dataType:'json',
		async:false,
		success:function(data){
			$("#editConfigId_input_id").val(data.id);
			$("#editConfigName_input_id").val(data.confName);
			$("#editConfigType_select_id").val(data.confType);
			$("#editConfigKey_input_id").val(data.confKey);
			$("#editConfigValue_input_id").val(data.confValue);
			$("#editConfigDesc_textarea_id").val(data.confDesc);
			showConfigEditTab(data.confName);
		}
   });
}

/**
 * 删除系统配置
 * @param id
 * @returns
 */
function deleteConfig_span_onclick(id) {
    Modal.confirm(
    {
        msg: "确定要删除系统配置吗？"
    }).on( function (e) {
        if (e) {
            $.ajax({
                type: 'POST',
                data: {id: id},
                url: yyoa_context + '/sys/conf/deleteByConfigId.do',
                dataType: 'json',
                async: false,
                success: function (data) {
                    Modal.alert({ msg: data.message });
                    if (data.state) {
                        refreshConfigList();
                    }
                }
            });
        }
    });
}




