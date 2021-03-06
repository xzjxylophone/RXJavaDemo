/**
 * 返回列表第一页
 */
function returnFirstPage() {
    $("#grid-data").bootgrid("repage", true);
}  

/**
	* 
	* @param thisElement 被点击的按钮本身
	* @auth jiwenfang
	* @createTime 2017年3月21日 下午4:56:35
	* @desc 添加Tab标签，进行选中并展示添加DIV页
	*
	*/
   function showAddDiv(thisElement){
	   //清空其他项
	   resetForm("addOverTimeForm");
	   
	  //设置申请人及申请人所属部门
	   $("#add_realName").val(sessionUserRealName);
	   $("#add_orgName").val(deptInfo.deptName);
	  
	   //隐藏其他tab页
	  cancelEditForm();
	  cancelDetailForm();
      changeTab(thisElement, "addOverTimeDiv");
   }

  /**
   * 检查加班时间
   * @param num
   * @returns {boolean}
   */
   function checkOverTime(num){
       var flag = false;
       if(!(num>0 && num%1==0)){ //是否为0.5的倍数
           Modal.alert({msg:"加班时间应为1的倍数"});
           flag = true;
       }
       return flag;
   }
   /**
    * 检查加班时间
    * @param num
    * @returns {Boolean}
    */
   function checkTime(startTime,endTime){
       var flag = false;
       //判断加班开始时间是否大于等于加班结束时间
       if(startTime >= endTime){ 
           Modal.alert({msg:"加班结束时间必须大于加班开始时间!"});
           flag = true;
       }
       return flag;
   }
   /**
    * 校验所填信息
    * @param option
    */
   function checkInfo(option){
	   var flag = false;
	   var startTime = $("#"+option+"_startTime").val();
	   var endTime = $("#"+option+"_endTime").val();
	   
	   //判断加班是否是1的倍数
		if(checkOverTime($("#"+option+"_overtimeHour").val())){
			flag = true;
		}
		//判断请假结束时间是否大于请假开始时间
		if(checkTime(startTime,endTime)){
			flag = true;
		}
		
		return flag;
   }
   /**
    * @auth jiwenfang
    * @desc 添加申请单页面保存按钮触发事件
    */
   function saveAddOverTime(status){
	   //校验所填信息
	   if(checkInfo('add')){
		   return;
	   }
       
	   var flag = $.html5Validate.isAllpass($("#addOverTimeForm"));
	   if(!flag){
		   return;
	   }else{
		   addOverTime(status);
	   }
   }     
   
   /**
    * 保存添加的加班单
    * @param status
    */
   function addOverTime(status){
	 //根据请假单状态,判断是否设置审批人
       if(status == approveing){//提交状态,设置审批人
           //隐藏弹出层
           closeApproveUsers();
       }
       //设置加班单属性
       $("#add_status").val(status);
       $("#add_overtimeType").val($("input[name='add_overtimeType']:checked").val());//加班类型
       $("#add_createUser").val(sessionUserId);//创建人id
       $("#add_depId").val(deptInfo.deptId);//用户所属部门id
       
	   $.ajax({
			type:'POST',
			url : yyzc_pro_context + "/oa/overTime/addOverTime.do",
			data : $('#addOverTimeForm').serialize(),
			dataType:'json',
			success:function(data){
				Modal.alert({msg:data.message});
				if(data.state){
					// 清理表单
					cancelSaveForm();
					// 刷新分页
					seachOverTimeList();
				}
			}
	   });
   }
   /**
    * 修改请假单
    * @param status
    */
   function saveOverTimeVacate(status){
	   //校验所填信息是否合法
	   if(checkInfo('edit')){
		   return;
	   }
	   
	   var flag = $.html5Validate.isAllpass($("#editOverTimeForm"));
	   if(!flag){
		   return;
	   }else{
		   updateOverTime(status);
	   }
   }  
   
   /**
    * 保存修改信息
    * @param status
    */
   function updateOverTime(status){
	 //根据请假单状态,判断是否设置审批人
	   if(status == approveing){//提交状态,设置审批人
		 //隐藏弹出层
		  closeApproveUsers();
	   }
	   
	  	$("#edit_updateUser").val(sessionUserId);
	  	$("#edit_status").val(status);
		$("#edit_overtimeType").val($("input[name='edit_overtimeType']:checked").val());
	  	
	   $.ajax({
			type:'POST',
			url : yyzc_pro_context + "/oa/overTime/updateOverTime.do",
			data : $('#editOverTimeForm').serialize(),
			dataType:'json',
			success:function(data){
				Modal.alert({msg:data.message});
				if(data.state){
					// 清理表单
					cancelSaveForm();
					//隐藏修改表单
					cancelEditForm();
					// 刷新分页
					seachOverTimeList();
				}
			}
	   });
   }
   
   /**
    * @auth wangjing
    * @desc 添加角色页面取消按钮触发事件
    */
   function cancelSaveForm(){
       resetForm("addOverTimeForm");

	   // 切换回表格tab
       var listBtn = document.getElementById("listTabBtn");
       changeTab(listBtn, 'listDiv');
   }
   
   /**
    * 显示修改请假单页面
    */
   function showEditDiv(){
	   var detailOverTimeTab = $("#detailOverTimeTab");
	   detailOverTimeTab.hide();
		
	   var editOverTimeTab = $("#editOverTimeTab");
	   editOverTimeTab.show();
		
		changeTab(editOverTimeTab, "editOverTimeDiv")
   }
   
   /**
    * 显示详情页面
    */
   function showDetailDiv(){
	   var detailOverTimeTab = $("#detailOverTimeTab");
	   detailOverTimeTab.show();
		
	   var editOverTimeTab = $("#editOverTimeTab");
	   editOverTimeTab.hide();
		
		changeTab(detailOverTimeTab, "detailOverTimeDiv")
   }
   
   /**
    * 
    * @param id 申请单id
    * @auth jiwenfang
    * @createTime 2017年3月21日 下午7:40:40
    * @desc 点击修改按钮事件
    *
    */
   function showDetailsDiv(id, type){
	   if(type == 'edit'){
		   showEditDiv();
	   }else if(type == 'detail'){
		   showDetailDiv();
	   }
	   $.ajax({
			type:'POST',
			data:{
				applicationId:id,
				type:type
			},
			url: yyoa_context +'/oa/overTime/selectOverTimeVoByApplicationId.do',
			dataType:'json',
			success:function(data){
				if(data != null){
					//设置修改页面及详情页面的公共属性
					$("#" + type + "_realName").val(data.overTimeVo.submitUserName);
					$("#" + type + "_orgName").val(data.overTimeVo.orgName);
					$("#" + type + "_startTime").val(data.overTimeVo.startTimeStr);
					$("#" + type + "_endTime").val(data.overTimeVo.endTimeStr);
					$("#" + type + "_overtimeHour").val(data.overTimeVo.overtimeHour);
					$("#" + type + "_overtimeReason").val(data.overTimeVo.overtimeReason);
					
					//设置请假类型
					if(type == 'edit'){
						$("#edit_createUser").val(data.overTimeVo.createUser);//创建人
						$("#edit_applicationId").val(data.overTimeVo.applicationId);
						$("#edit_id").val(data.overTimeVo.id);
						$("input[type=radio][name=edit_overtimeType][value="+data.overTimeVo.overtimeType+"]").attr("checked",true); //设置加班类型为选中状态
					   }else if(type == 'detail'){
						   $("#detail_overtimeType").val(setOverTimeType(data.overTimeVo.overtimeType));
					   }
					
					if(type == 'detail'){
						var tableStr = "<h2>流转信息</h2>" 
									 + "<table class='table table-bordered'>"
									 + "<thead>"
									 + "<tr>"
									 + "<th>执行环节</th>"
									 + "<th>执行人</th>"
									 + "<th>执行时间</th>"
									 + "<th>提交意见</th>"
									 + "</tr>"
									 + "</thead>"
									 + "<tbody>";
						//流转信息(已审批)
						var approvaVoList = data.approvedVo;
						//判断已审批流转信息集合是否为空,不为空,则拼接相关信息
						if(approvaVoList != null){
							//获取流转信息长度
							var len = approvaVoList.length; 
							for(var i=0; i<len; i++){
								var approveStep = approvaVoList[i].step;
								var approveUnit = "";
								if(approveStep == step_one){
									approveUnit = "部门负责人意见";
								}else if(approveStep == step_two){
									approveUnit = "CEO意见";
								}else if(approveStep == step_three){
									approveUnit = "人力资源意见";
								}
							var userName = approvaVoList[i].userName == null ? '': approvaVoList[i].userName;	
							var option = approvaVoList[i].opinion == null ? '': approvaVoList[i].opinion;
							
								tableStr += "<tr>"
								          + "<td>"+approveUnit+"</td>"
								          + "<td>"+userName+"</td>"
								          + "<td>"+approvaVoList[i].approveTimeStr+"</td>"
								          + "<td>"+option+"</td>"
								          + "</tr>";
							}
						}
						//获取未审批流转信息集合
						var notapprovaVoList = data.notapproveVo;
						//判断未审批流转信息集合是否为空,不为空,则拼接相关信息
						if(notapprovaVoList != null){
							//获取流转信息长度
							var len = notapprovaVoList.length; 
							for(var i=0; i<len; i++){
								var approveStep = notapprovaVoList[i].step;
								var approveUnit = "";
								if(approveStep == step_one){
									approveUnit = "部门负责人意见";
								}else if(approveStep == step_two){
									approveUnit = "CEO意见";
								}else if(approveStep == step_three){
									approveUnit = "人力资源意见";
								}
							var userName = notapprovaVoList[i].userName == null ? '': notapprovaVoList[i].userName;	
							
								tableStr += "<tr>"
								          + "<td>"+approveUnit+"</td>"
								          + "<td>"+userName+"</td>"
								          + "<td></td>"
								          + "<td></td>"
								          + "</tr>";
							}
						}
						tableStr += "</tbody></table>";
						$("#approveInfo").empty();
						$("#approveInfo").append(tableStr);
					}
				}
			}
	   });
   }
   //设置加班单类型
   function setOverTimeType(ottype){
	   var  overTimeType = "";
	   if(ottype == pingshi){
		   overTimeType = "平时";
	   }else if(ottype == gongxiuri){
		   overTimeType = "公休日";
	   }else if(ottype == fadingjiari){
		   overTimeType = "法定假日";
	   }
	   return overTimeType;
   }
   
    /**
	 * 重置表单
     * @param formName
     */
	function resetForm(formName) {
		$('#'+formName)[0].reset();
	}

   /**
    * @auth jiwenfang
    * @desc 修改申请单页面取消按钮触发事件
    */
   function cancelEditForm(){
	   resetForm("editOverTimeForm");
       $("#editOverTimeTab").hide();

       // 切换回表格tab
       var listBtn = document.getElementById("listTabBtn");
       changeTab(listBtn, 'listDiv');
   }
   
   /**
    * 隐藏详情tab页
    */
   function cancelDetailForm(){
	   resetForm("detailOverTimeForm");
       $("#detailOverTimeTab").hide();

       // 切换回表格tab
       var listBtn = document.getElementById("listTabBtn");
       changeTab(listBtn, 'listDiv');
   }
   /**
    * 
    * @param id 角色ID
    * @returns
    * @auth jiwenfang
    * @createTime 2017年3月22日 下午9:13:54
    * @desc 根据申请单id删除申请单
    *
    */
   function deleteApplication(id){
       Modal.confirm(
           {
               msg: "确定要删除请假单吗？"
           })
           .on( function (e) {
               if(e) {
                   $.ajax({
                       type: 'POST',
                       data: {applicationId: id},
                       url: yyoa_context + '/oa/application/deleteByApplicationId.do',
                       dataType: 'json',
                       async: false,
                       success: function (data) {
                           Modal.alert({msg:data.message});
                           if (data.state) {
                               seachOverTimeList(1);
                           }
                       }
                   });
               }
           });
   }
     
   //显示弹出层
   function showApproveUsers(type){
	 //检验所填信息
	   if(checkInfo(type)){
		   return;
	   }
	   
       var flag = $.html5Validate.isAllpass($("#"+type+"OverTimeForm"));
       if(!flag){
           return;
       }else{
           $.ajax({
               type: 'POST',
               url: yyoa_context + '/ou/department/selectApproveByDepId.do',
               data: {
            	   userDepId: deptInfo.deptId,
            	   userId: sessionUserId
               },
               dataType: 'json',
               async: false,
               success: function (data) {
                   if (data != null) {
                	   var approveUsers = data["leader"].userId + "," + data["ceo"].userId + "," + data["renli"].userId ;

                       var tableStr = "<div class='absent-clickbox'>"
                           + "<h2>流转信息</h2>"
                           + "<table class='table table-bordered'>"
                           + "<thead>"
                           + "<tr>"
                           + "<th>执行环节</th>"
                           + "<th>执行人</th>"
                           + "</tr>"
                           + "</thead>"
                           + "<tbody>";


                       //拼接行---部门负责人     步数：1
                       tableStr += "<tr>"
                           + "<td>部门负责人审批</td>"
                           + "<td >" + data["leader"].userRealName + "</td></tr>";

                       //拼接行---人事  步数：2
                       tableStr += "<tr>"
                           + "<td>CEO审批</td>"
                           + "<td >"+data["ceo"].userRealName+"</td></tr>";

                       //拼接行---主管领导  步数：3
                       tableStr += "<tr>"
                           + "<td>人力资源签收</td>"
                           + "<td >"+data["renli"].userRealName+"</td></tr>";

					   var buttonStr = "";
					   if(type == "add"){
                           buttonStr = "<button class='btn absent-addto manage-cancel' type='button' onclick='addOverTime(" + approveing + ")'>";
					   }else{
                           buttonStr = "<button class='btn absent-addto manage-cancel' type='button' onclick='updateOverTime(" + approveing + ")'>";
					   }
                       tableStr += "</tbody></table>"
                           + "<div class='absent-con'>"
                           + buttonStr
                           + "确定"
                           + "</button>"
                           + "<button class='btn  absent-addto manage-cancel' type='button' onclick='closeApproveUsers()'>"
                           + "取消"
                           + "</button>"
                           + "</div></div>";
                       $("#approveUsers").empty();
                       $("#approveUsers").append(tableStr);

                       $("#approveUsers").fadeIn('slow');
                       
                       //设置审批人的值
                       $("#" + type + "_approverUserIds").val(approveUsers);
                       
                   }
               }
           });
       }

   }
			
   //关闭弹出层
   function closeApproveUsers(){
       $("#approveUsers").fadeOut('slow');
   }

  /**
   * 部门属性JSON
   * @type {{deptId: null, deptName: null}}
   */
   var deptInfo = {
       deptId: null,
	   deptName: null,
	   leaderId : null,//部门负责人Id
	   supleaderId : null//主管领导id
   };
  /**
   * 获取部门信息
   * @param userId
   */
   function getDeptInfo(userId){
       $.ajax({
           type: 'POST',
           data: {userId: userId},
           url: yyoa_context + '/ou/employee/selectEmployeeVoByUserId.do',
           dataType: 'json',
           async: false,
           success: function (data) {
           		console.log(data);
               deptInfo.deptId = data.depId;
               deptInfo.deptName = data.depName;
               deptInfo.leaderId = data.leaderId;//部门负责人id
               deptInfo.supleaderId = data.supleaderId;//主管领导id
           }
       });
   }
   
   $(function(){
       // 获取用户的部门信息
       getDeptInfo(sessionUserId);
	   //添加:保存为草稿
		$("#addOverTimeSaveBtn").click(function(){
			saveAddOverTime(saved);
		});
		
		//添加:保存并提交
		$("#addsubmitOverTimeBtn").click(function(){
			showApproveUsers('add');
		});
		
		$("#addOverTimeCancelBtn").click(function(){
			cancelSaveForm();
		});
		
		//修改:保存为草稿
		$("#editOverTimeSaveBtn").click(function(){
			saveOverTimeVacate(saved);
		});
		//修改:保存并提交
		$("#editSubmitOverTimeBtn").click(function(){
			showApproveUsers('edit');
		});
		
		$("#editOverTimeCancelBtn").click(function(){
			cancelEditForm();
		});

		$("#listTabBtn").click(function(){
			changeTab(this,'listDiv');
			cancelEditForm();
			$("#editOverTimeTab").hide();
			$("#detailOverTimeTab").hide();
		});
		
	})