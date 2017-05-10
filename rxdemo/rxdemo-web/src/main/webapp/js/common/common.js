
/**
 * 进入首页
 * @auth wangjing
 */
function toHome(){
	window.parent.location.href=yyoa_context + "/homePage.do"
}



/**
 * 全局ajax设置
 * @type {number}
 */
var pageTimeout=5000;
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    timeout:pageTimeout,
    cache:false,
    complete:function(XHR,TS){
        var resText=XHR.responseText;
        if(resText=="{sessionState:0}"){
            window.open(yyoa_context+'/loginPage.do','');
        }
    }
});

/**
 * @auth wangjing
 * @desc 点击系统管理下面的角色管理，进入角色管理列表页面事件
 */
function toRoleList(){
	$("#rightName").attr("src", yyoa_context + "/ou/userRole/showRoleListPage.do");
}

function toUserList(){
	$("#rightName").attr("src", yyoa_context + "/ou/user/showUserListPage.do");
}


/**
 * 打开指定页面
 * @param menuUrl
 */
function openPage(menuUrl,el){
	$("#rightName").attr("src", yyoa_context + menuUrl);
    $(el).parent().css({
        'background':'#E4E5E6'
    }).siblings().css({
        'background':'#fff'
    });
    $(el).css({
        "hide-focus": "expression(this.hideFocus=true)",
        "outline-style": "none",
        "border":"none"
    });
}

/**
 * 
 * @param thisElement 点击的按钮对象
 * @param showDivId 要显示的DIV的ID
 * @auth wangjing
 * @createTime 2017年3月21日 下午4:32:23
 * @desc 列表页面切换事件，用于对正在显示的DIV进行隐藏，对隐藏的DIV进行显示，并选中
 *
 */
function changeTab(thisElement, showDivId){
	$(thisElement).addClass('manage-active').siblings().removeClass('manage-active');
	$("#" + showDivId).show().siblings().hide();
}

/**
 * 对Date的扩展，将 Date 转化为指定格式的String
 * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 * 例子：
 * (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 * (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
 * @param fmt
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}




/**
 * 判断字符串是否不为空
 * @param str
 * @returns true表示不是空,false表示空
 */
function strIsNotEmpty(str) {
	return str != undefined && str != null && str != "";
}

/**
 * 标准化时间转字符串
 * @param date:  是一个long的数字
 * @param format: 格式模板类似于 yyyy-MM-dd
 * @returns
 */
function date2Str(date, format) {
	var dateString = "";
	if (strIsNotEmpty(date)) {
		dateString = new Date(date).Format(format);
	}
	return dateString;
}
/**
 * 
 * @param jspElement: jsp 元素
 * @param dataList: 数据源
 * @param max: 一排最大显示的数目
 * @param valueIdFunction: checkbox的value函数   
 * 						   参数是数据源里面的对象
 * @param textFunction: checkbox的文本函数 
 * 					 	参数是数据源里面的对象
 * @param selectedFunction: checkbox的是否选中函数 
 * 						    参数是数据源里面的对象, 返回1表示选中,0表示不选中
 * @author Rush.D.Xzj
 * @Description 目前已经成功在如下情况下使用:
 * 				1.添加用户选择角色
 * 				2.给角色分配用户
 * @returns
 */
function standartShowBatchCheckBox(jspElement, dataList, max, valueIdFunction, textFunction, selectedFunction) {
	jspElement.empty();
	var appendString = "";
	$.each(dataList, function(n, value) {
		var valueId = valueIdFunction(value);
		var text = textFunction(value);
		var selected = selectedFunction(value);
		
		var remain = n % max;
		var tmpAppendString = "<input type='checkbox'";
		if (selected == 1) {
			tmpAppendString += " checked='checked'";
		}
		tmpAppendString += " value='" + valueId + "' name='header'/>"
		tmpAppendString += text;

		if(remain == 0) {
			appendString += "<tr>"
		}
		appendString += "<td>" + tmpAppendString + "</td>";
		if (n != 0 && remain == max - 1) {
			appendString += "</tr>";
			jspElement.append(appendString);
			appendString = "";
		}
	});

	if(appendString != "") {
		appendString += "</tr>";
		jspElement.append(appendString);
		appendString = "";
	}
}


//回车提交
$(function(){
    function enter(obj) {
        $(document).keyup(function (event) {
            if (event.keyCode == 13) {
                $(obj).trigger("click");
            }
        });
    }
    enter("#seachBtn");
    enter("#addRoleSaveBtn");
    // enter("#year-confirm");
    enter("#okButton");
    enter("#changePwd");
    enter("#loginBtn");
    enter("#seachBtn");
   // enter("#addAnnualVacateSaveBtn");//年假保存
   // enter("#editVacateSaveBtn");//修改年假
    enter("#seach-Btn");
    enter("#export_a_id");
    //enter("#addVacateSaveBtn");//请假保存
   // enter("#editVacateSaveBtn");//修改请假
    enter("#addOrgSaveBtn");
    enter("#editOrgSaveBtn");
    enter("#addMenuSaveBtn");
    enter("#editMenuSaveBtn");

})

function showDefaultLoading() {
	var loading = $("#common_loading_div_id");
	loading.children().removeClass('absent-clickbox').addClass('absent-clickbox-loading');
	loading.fadeIn("slow");
}

function closeDefaultLoading() {
	var loading = $("#common_loading_div_id");
	loading.fadeOut("slow");
}

