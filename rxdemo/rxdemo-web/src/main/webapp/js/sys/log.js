
function initSetBaseDate() {
//	var endDate = new Date();
//	var startDate = new Date(endDate.getTime() - 31 * 24 * 3600 * 1000);  
//	$("#searchAnnualVacateStartTime_input_id").val(startDate.Format("yyyy-MM-dd"));
//	$("#searchAnnualVacateEndTime_input_id").val(endDate.Format("yyyy-MM-dd"));
//	$("#searchLeaveStartTime_input_id").val(startDate.Format("yyyy-MM-dd"));
//	$("#searchLeaveEndTime_input_id").val(endDate.Format("yyyy-MM-dd"));
}

/**
 * 显示员工休假列表标签
 * @returns
 */
function showAnnualVacateListTab() {
	
	var listAnnualVacate_span = $("#listAnnualVacate_span_id");
	listAnnualVacate_span.show();
	
	changeTab(listAnnualVacate_span, "listAnnualVacate_div_id");
}
/**
 * 
 * @returns
 */
function showLeaveListTab() {
	var listLeave_span = $("#listLeave_span_id");
	listLeave_span.show();
	
	
	changeTab(listLeave_span, "listLeave_div_id");
}


/**
 * 初始化页面时候加载数据
 * @returns
 */
function loadAnnualVacatePage() {
	$("#grid-data-AnnualVacate").bootgrid({
        sorting:false,
        navigation:3, // Default value is 3. 0 for none, 1 for header, 2 for footer and 3 for both.
        columnSelection: false, //启用或禁用下拉框隐藏/显示列。默认值为true
        ajax: true,
        ajaxSettings: {
            method: "POST",
            cache: false
        },
        requestHandler: function (request) { //自定义参数处理
            var realName = $("#searchAnnualVacateRealName_input_id").val();
            var startTime = $("#searchAnnualVacateStartTime_input_id").val();
            var endTime = $("#searchAnnualVacateEndTime_input_id").val();
        		console.log("realName:", realName, ",startTime:", startTime, ",endTime:", endTime);
            request.realName = realName;
            request.endTime = endTime;
            request.startTime = startTime;
            return request;
        },
        url: yyoa_context+"/sys/log/selectAnnualVacateLogVoListByName.do",
        clickToSelect:true,
        rowCount: [10, 25, 50],
        labels : {
            infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
        },
        formatters: {
        		
        }
    });
}
/**
 * 
 * @returns
 */
function loadLeavePage() {
	$("#grid-data-Leave").bootgrid({
        sorting:false,
        navigation:3, // Default value is 3. 0 for none, 1 for header, 2 for footer and 3 for both.
        columnSelection: false, //启用或禁用下拉框隐藏/显示列。默认值为true
        ajax: true,
        ajaxSettings: {
            method: "POST",
            cache: false
        },
        requestHandler: function (request) { //自定义参数处理
        	var realName = $("#searchLeaveRealName_input_id").val();
            var startTime = $("#searchLeaveStartTime_input_id").val();
            var endTime = $("#searchLeaveEndTime_input_id").val();
        		console.log("realName:", realName, ",startTime:", startTime, ",endTime:", endTime);
            request.realName = realName;
            request.endTime = endTime;
            request.startTime = startTime;
            return request;
        },
        url: yyoa_context+"/sys/log/selectLeaveLogVoListByName.do",
        clickToSelect:true,
        rowCount: [10, 25, 50],
        labels : {
            infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
        },
        formatters: {
        		
        }
    });
}
/**
 * 根据输入的条件刷新页面
 * @returns
 */
function refreshAnnualVacateList () {
    $("#grid-data-AnnualVacate").bootgrid("reload", true);
}
/**
 * 
 * @returns
 */
function refreshLeaveList () {
    $("#grid-data-Leave").bootgrid("reload", true);
}
/**
 * 员工休假列表标签事件
 * @returns
 */
function listAnnualVacate_span_onclick() {
	showAnnualVacateListTab();
}
/**
 * 
 * @returns
 */
function listLeave_span_onclick() {
	showLeaveListTab();
}

/**
 * 查询按钮事件
 * @returns
 */
function searchAnnualVacate_button_onclick() {
    $("#grid-data-AnnualVacate").bootgrid("repage", true);
}
/**
 * 
 * @returns
 */
function searchLeave_button_onclick() {
    $("#grid-data-Leave").bootgrid("repage", true);
}


