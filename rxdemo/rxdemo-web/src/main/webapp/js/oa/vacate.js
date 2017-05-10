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
	   $("#add_overTimeLeave").empty();//清空剩余倒休时间
	   resetForm("addVacateForm");
	   $.ajax({
			type:'POST',
			url : yyzc_pro_context + "/oa/remain/selectByEmpId.do",
			data : {
				empId : deptInfo.empId
			},
			dataType:'json',
			success:function(data){
				  //设置剩余倒休时间
				  $("#add_overTimeLeave").append("<label style='margin-left: 50px;'><span style='color: red;'>剩余倒休时间为"+data.notUsedLeave+"小时</span></label>");
				  $("#add_notUsedLeave").val(data.notUsedLeave);
				  //设置申请人及申请人的ID
				   $("#add_realName").val(sessionUserRealName);
				   $("#add_orgName").val(deptInfo.deptName);
				   $("#add_userId").val(sessionUserId);
				   $("#add_empId").val(deptInfo.empId);
				   
				   //隐藏其他tab页
				  cancelEditForm();
				  cancelDetailForm();
			      changeTab(thisElement, "addVacateDiv");
			}
	   });
	   
   }

   /**
    * 根据请假单类型显示不同的时间记录框
    * @param vacateType 申请单类型
    * @param divType div类型
    */
   function showVacateTimeDiv(vacateType, divType){
	   var divValue = "";
	   var option = "";
	   var style = "";
	   //清空相关属性
	   $("#"+divType+"_vacateTime").empty();
	   //如果为详情页面，则需要加只读属性
	   if(divType == "detail"){
		   option = "readonly ";
		   style = " style='background-color: rgb(255, 255, 238)'";
	   }
	   //判断请假类型是否是倒休
	   if(vacateType == 4){//倒休：以小时为单位
		   divValue  = "<input type='number'  class='form-control' id='"+divType+"_vacateDays' required step='1' min='1' placeholder='(以1小时为单位计算)' " + option + style +">"
			   		 + "<span class='year-position'>小时</span>";
	   }else{//请假：以天为单位
		   divValue  = "<input type='number'  class='form-control' id='"+divType+"_vacateDays' required step='0.5' min='0.5' placeholder='(以0.5天为单位计算)'" + option + style +">"
	   		 		 + "<span class='year-position'>天</span>";
	   }
	   //拼接属性
	   $("#"+divType+"_vacateTime").append(divValue);
   }
   
   /**
    * 判断倒休时间是否超过剩余的倒休时间
    */
   function checkVacateHours(type){
	   var flag = false;
	   var vacateHours = $("#"+type+"_vacateHours").val();
	   var notUsedLeave = $("#"+type+"_notUsedLeave").val();
       //判断请假开始时间是否大于等于请假结束时间
       if((notUsedLeave - vacateHours) < 0 ){ 
           Modal.alert({msg:"倒休时间超过了剩余倒休值"});
           flag = true;
       }
       return flag;
   }
   /**
    * 检查请假时间
    * @param num
    * @returns {Boolean}
    */
   function checkVacateDays(startTime,endTime){
       var flag = false;
       //判断请假开始时间是否大于等于请假结束时间
       if(startTime >= endTime){ 
           Modal.alert({msg:"请假结束时间必须大于请假开始时间!"});
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
	   var vacateType =  $("input[name='"+option+"_vacateType']:checked").val();//请假单类型
	   
	   //根据请假单类型校验请假时间
	   if(vacateType == 4){
		   //判断倒休时间是否超过剩余的倒休时间
		   if(checkVacateHours(option)){
				flag = true;
			}
	   }
		
		//判断请假结束时间是否大于请假开始时间
		if(checkVacateDays(startTime,endTime)){
			flag = true;
		}
		
		return flag;
   }
   /**
    * @auth jiwenfang
    * @desc 添加申请单页面保存按钮触发事件
    */
   function saveAddVacate(status){
	   $("#add_vacateType").val($("input[name='add_vacateType']:checked").val());
       
       //获取请假单类型
       var vacateType =  $("#add_vacateType").val();
       //如果请假单类型不为倒休，则时间需要乘8后将天换算成小时传到后台
       if(vacateType != 4){
    	   $("#add_vacateHours").val($("#add_vacateDays").val() * 8);
       }else{
    	   $("#add_vacateHours").val($("#add_vacateDays").val());
       }
	   //校验所填信息
	   if(checkInfo('add')){
		   return;
	   }
       
	   var flag = $.html5Validate.isAllpass($("#addVacateForm"));
	   if(!flag){
		   return;
	   }else{
		   addVacate(status);
	   }
   }     
   
   /**
    * 保存添加的请假单
    * @param status
    */
   function addVacate(status){
	 //根据请假单状态,判断是否设置审批人
       if(status == approveing){//提交状态,设置审批人
           //隐藏弹出层
           closeApproveUsers();
       }
       //设置请假单属性
       
       $("#add_orgId").val(deptInfo.deptId);
       $("#add_status").val(status);
       $("#add_vacateType").val($("input[name='add_vacateType']:checked").val());
       
	   $.ajax({
			type:'POST',
			url : yyzc_pro_context + "/oa/vacate/addVacate.do",
			data : $('#addVacateForm').serialize(),
			dataType:'json',
			success:function(data){
				Modal.alert({msg:data.message});
				if(data.state){
					// 清理表单
					cancelSaveForm();
					// 刷新分页
					seachVacateList();
				}
			}
	   });
   }
   /**
    * 修改请假单
    * @param status
    */
   function saveEditVacate(status){
	   $("#edit_vacateType").val( $("input[name='edit_vacateType']:checked").val()); 
	   
	  //获取请假单类型
       var vacateType =  $("#edit_vacateType").val();
	   //如果请假单类型不为倒休，则时间需要乘8后将天换算成小时传到后台
       if(vacateType != 4){
    	   $("#edit_vacateHours").val($("#edit_vacateDays").val() * 8);
       }else{
    	   $("#edit_vacateHours").val($("#edit_vacateDays").val());
       }
	   //校验所填信息是否合法
	   if(checkInfo('edit')){
		   return;
	   }
	   
	   var flag = $.html5Validate.isAllpass($("#editVacateForm"));
	   if(!flag){
		   return;
	   }else{
		   updateVacate(status);
	   }
   }  
   
   /**
    * 保存修改信息
    * @param status
    */
   function updateVacate(status){
	   $("#edit_empId").val(deptInfo.empId); 
	   $("#edit_status").val(status); 
	   
	 //根据请假单状态,判断是否设置审批人
	   if(status == approveing){//提交状态,设置审批人
		 //隐藏弹出层
		  closeApproveUsers();
	   }
	   
	   $.ajax({
			type:'POST',
			url : yyzc_pro_context + "/oa/vacate/updateVacate.do",
			data : $('#editVacateForm').serialize(),
			dataType:'json',
			success:function(data){
				Modal.alert({msg:data.message});
				if(data.state){
					// 清理表单
					cancelSaveForm();
					//隐藏修改表单
					cancelEditForm();
					// 刷新分页
					seachVacateList();
				}
			}
	   });
   }
   /**
    * @auth wangjing
    * @desc 添加角色页面取消按钮触发事件
    */
   function cancelSaveForm(){
       resetForm("addVacateForm");

	   // 切换回表格tab
       var listBtn = document.getElementById("listTabBtn");
       changeTab(listBtn, 'listDiv');
   }
   
   /**
    * 显示修改请假单页面
    */
   function showEditDiv(){
	   var detailVacateTab = $("#detailVacateTab");
	   detailVacateTab.hide();
		
	   var editVacateTab = $("#editVacateTab");
		editVacateTab.show();
		
		changeTab(editVacateTab, "editVacateDiv")
   }
   
   /**
    * 显示详情页面
    */
   function showDetailDiv(){
	   var detailVacateTab = $("#detailVacateTab");
	   detailVacateTab.show();
		
	   var editVacateTab = $("#editVacateTab");
		editVacateTab.hide();
		
		changeTab(detailVacateTab, "detailVacateDiv")
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
			url: yyoa_context +'/oa/vacate/selectVacateVoByApplicationId.do',
			dataType:'json',
			success:function(data){
				if(data != null){
					$("#"+type+"_realName").val(data.vacateVo.realName);
					$("#"+type+"_orgName").val(data.vacateVo.orgName);
					
					$("#"+type+"_vacateReason").val(data.vacateVo.vacateReason);
					$("#"+type+"_workStatus").val(data.vacateVo.workStatus);
					$("#"+type+"_startTime").val(data.vacateVo.startTimeStr);
					$("#"+type+"_endTime").val(data.vacateVo.endTimeStr);
					
					//显示计算方式
					showVacateTimeDiv(data.vacateVo.vacateType, type);
					//休假时间
					$("#"+type+"_vacateDays").val(data.vacateVo.vacateHours);
					//如果休假类型为倒休，则值需要整除4
					if(data.vacateVo.vacateType != 4){
						$("#"+type+"_vacateDays").val(data.vacateVo.vacateHours/8);
					}
					
					//设置请假类型
					if(type == 'edit'){
						//设置剩余倒休时间
						$("#edit_overTimeLeave").empty();
						$("#edit_overTimeLeave").append("<label style='margin-left: 50px;'><span style='color: red;background-color: rgb(255 255 238);'>剩余倒休时间为"+data.vacateVo.notUsedLeave+"小时</span></label>");
						$("#edit_notUsedLeave").val(data.vacateVo.notUsedLeave);//剩余倒休时间
						$("#edit_empId").val(data.vacateVo.empId);//员工id
						$("#edit_applicationId").val(data.vacateVo.applicationId);
						$("#edit_vacateId").val(data.vacateVo.id);
						$("#edit_userId").val(data.vacateVo.createUser);
						$("#edit_orgId").val(data.vacateVo.orgId);
						$("input[type=radio][name=edit_vacateType][value="+data.vacateVo.vacateType+"]").attr("checked","checked"); //设置请假类型为选中状态
					   }else if(type == 'detail'){
						   setVacateType(data.vacateVo.vacateType);
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
									approveUnit = "人力资源中心意见";
								}else if(approveStep == step_three){
									approveUnit = "主管领导意见";
								}else if(approveStep == step_four){
									approveUnit = "CEO意见";
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
									approveUnit = "人力资源中心意见";
								}else if(approveStep == step_three){
									approveUnit = "主管领导意见";
								}else if(approveStep == step_four){
									approveUnit = "CEO意见";
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

   /**
    * 设置请假类型
    */
   function setVacateType(vacteType){
	   if(vacteType == 1){
		   $("#detail_vacateType").val("事假");
	   }if(vacteType == 2){
		   $("#detail_vacateType").val("带薪病假");
	   }if(vacteType == 3){
		   $("#detail_vacateType").val("病假");
	   }if(vacteType == 4){
		   $("#detail_vacateType").val("调休");
	   }if(vacteType == 5){
		   $("#detail_vacateType").val("婚假");
	   }if(vacteType == 6){
		   $("#detail_vacateType").val("产检假");
	   }if(vacteType == 7){
		   $("#detail_vacateType").val("产假");
	   }if(vacteType == 8){
		   $("#detail_vacateType").val("陪产假");
	   }if(vacteType == 9){
		   $("#detail_vacateType").val("丧假");
	   }
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
	   resetForm("editVacateForm");
       $("#editVacateTab").hide();

       // 切换回表格tab
       var listBtn = document.getElementById("listTabBtn");
       changeTab(listBtn, 'listDiv');
   }
   
   /**
    * 隐藏详情tab页
    */
   function cancelDetailForm(){
	   resetForm("detailVacateForm");
       $("#detailVacateTab").hide();

       // 切换回表格tab
       var listBtn = document.getElementById("listTabBtn");
       changeTab(listBtn, 'listDiv');
   }
     
   //显示弹出层
   function showApproveUsers(type){
	   $("#"+type+"_vacateType").val( $("input[name='"+type+"_vacateType']:checked").val()); 
	   
	  //获取请假单类型
       var vacateType =  $("#"+type+"_vacateType").val();
	   //如果请假单类型不为倒休，则时间需要乘8后将天换算成小时传到后台
       if(vacateType != 4){
    	   $("#"+type+"_vacateHours").val($("#"+type+"_vacateDays").val() * 8);
       }else{
    	   $("#"+type+"_vacateHours").val($("#"+type+"_vacateDays").val());
       }
	 //检验所填信息
	   if(checkInfo(type)){
		   return;
	   }
	   
	   var days = $("#"+type+"_vacateDays").val();
       var flag = $.html5Validate.isAllpass($("#"+type+"VacateForm"));
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
                	   var approveUsers = data["leader"].userId + "," + data["renli"].userId + "," + data["supleader"].userId ;
                       //获取用户请假天数
                       var vacateHours = $("#"+type+"_vacateHours").val();

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


                       //拼接行---部门负责人     步数：2
                       tableStr += "<tr>"
                           + "<td>部门负责人审批</td>"
                           + "<td >" + data["leader"].userRealName + "</td></tr>";

                       //拼接行---人事  步数：3
                       tableStr += "<tr>"
                           + "<td>人力资源审批</td>"
                           + "<td >"+data["renli"].userRealName+"</td></tr>";

                       //拼接行---主管领导  步数：4
                       tableStr += "<tr>"
                           + "<td>主管领导审批</td>"
                           + "<td >"+data["supleader"].userRealName+"</td></tr>";
                       
					   if(days>=3){
						   approveUsers += "," + data["ceo"].userId;
                           //拼接行---CEO  步数：5
                           tableStr += "<tr>"
                               + "<td>CEO</td>"
                               + "<td >"+data["ceo"].userRealName+"</td></tr>";
					   }

					   var buttonStr = "";
					   if(type == "add"){
                           buttonStr = "<button class='btn absent-addto manage-cancel' type='button' onclick='addVacate(" + approveing + ")'>";
					   }else{
                           buttonStr = "<button class='btn absent-addto manage-cancel' type='button' onclick='updateVacate(" + approveing + ")'>";
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

   $(function(){
       // 获取用户的部门信息
       getDeptInfo(sessionUserId);
	   //添加:保存为草稿
		$("#addVacateSaveBtn").click(function(){
			saveAddVacate(saved);
		});
		
		//添加:保存并提交
		$("#addsubmitVacateBtn").click(function(){
			showApproveUsers('add');
		});
		
		$("#addVacateCancelBtn").click(function(){
			cancelSaveForm();
		});
		
		//修改:保存为草稿
		$("#editVacateSaveBtn").click(function(){
			saveEditVacate(saved);
		});
		//修改:保存并提交
		$("#editsubmitVacateBtn").click(function(){
			showApproveUsers('edit');
		});
		
		$("#editVacateCancelBtn").click(function(){
			cancelEditForm();
		});

       $("#cancelVacateCancelBtn").click(function(){
           cancelDetailForm();
       });

		$("#listTabBtn").click(function(){
			changeTab(this,'listDiv');
			cancelEditForm();
			$("#editVacateTab").hide();
			$("#detailVacateTab").hide();
		});
		$("#editVacateTab").hide();
		$("#editVacateTab").hide();
	})