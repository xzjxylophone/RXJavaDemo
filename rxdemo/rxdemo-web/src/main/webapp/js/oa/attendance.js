


/**
 * 初始化页面时候加载数据
 * @returns
 */
function loadAttendancePage() {
	console.log("loadAttendancePage");
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
            var selectDate = $("#selectMonth_input_id").val();
            console.log("selectDate:", selectDate);
            request.dateString = selectDate;
            return request;
        },
        url: yyoa_context+'/oa/attendance/selectAttendanceByDate.do',
        rowCount: [10, 25, 50],
        labels : {
            infos : "显示 {{ctx.start}} 至 {{ctx.end}} 共 {{ctx.total}} 条"
        },
        formatters: {

        		"monthDate_formatter": function(column, row)
            {
        			return date2Str(row.monthDate, "yyyy-MM");
            }

        }
    });
}
/**
 * 根据输入的条件刷新页面
 * @returns
 */
function refreshAttendanceList () {
	console.log("refreshAttendanceList");
    $("#grid-data").bootgrid("reload", true);
}


function search_button_onclick() {
    $("#grid-data").bootgrid("repage", true);
}



function importAction() {
	var fileSelect = $("#fileSelect");
	fileSelect.trigger("click");
}

function fileSelect_input_onchange() {
	var fileName = $("#fileSelect").val();
	console.log("fileName:", fileName);
	if (strIsNotEmpty(fileName)) {
		showDefaultLoading();
		$.ajaxFileUpload({
	        url : yyoa_context +'/oa/attendance/importAction.do',
	        secureuri : false,// 一般设置为false
	        fileElementId : "fileSelect",// 文件上传表单的id <input type="file" id="fileUpload" name="file" />
	        dataType : 'json',// 返回值类型 一般设置为json
	        data: {},
	        timeout:50000,
	        success : function(data) // 服务器成功响应处理函数
	        {	        		
	        		closeDefaultLoading();
	        		if (data.state) {
	        			Modal.alert({ msg: "上传成功" });
	    				console.log("data.message:", data.message);
	    				// 更新日期
	    				$("#selectMonth_input_id").val(data.message);
	    				// 上面的代码更新日期,不会触发onchange事件,
	    				// 所以要手动调用一下onchange事件
	    				refreshAttendanceList();
	        		} else {
	        			Modal.alert({ msg: data.message });
	        		}
	        		// 清空文件选择框
    				$("#fileSelect").val("");
				
	        },
	        error : function(data)// 服务器响应失败处理函数
	        {
				Modal.alert({ msg: "上传失败" });
				closeDefaultLoading();
				$("#fileSelect").val("");
	        }
	    });
	}
	
}


/**
 * 日期选择变化的时候,更新a标签里的超链接地址
 * @returns
 */

function btn_onclick() {
	var dateString = $("#selectMonth_input_id").val();
	console.log("111dateString:", dateString);
	var url = yyoa_context + '/oa/attendance/export.do?dateString=' + dateString;
	var export_a = $("#export_a_id");
	export_a.attr("href", url);	
	document.getElementById("export_a_id").click()
	
	
	
}










