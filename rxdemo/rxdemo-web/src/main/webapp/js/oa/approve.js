/**
 * 返回待办任务列表第一页
 */
function returnDaibanFirstPage() {
    $("#daibanGrid").bootgrid("repage", true);
} 
/**
 * 返回已办任务列表第一页
 */
function returnYibanFirstPage() {
    $("#yibanGrid").bootgrid("repage", true);
} 
/**
	* 
	* @param thisElement 被点击的按钮本身
	* @auth jiwenfang
	* @createTime 2017年3月21日 下午4:56:35
	* @desc 打开已办任务列表
	*
	*/
   function showFinishedTaskDiv(thisElement){
	   seachYiBanVacateList();
	  
	   //隐藏其他tab页
	  cancelTaskForm();//隐藏任务办理tab页
	  cancelDetailForm();//隐藏任务详情tab页
      changeTab(thisElement, "finishedListDiv");
   }

   /**
    * 显示审批请假页面
    */
   function showApproveVacateDiv(){
	   var detailVacateTab = $("#approveVacate");
	   detailVacateTab.show();
	   
		changeTab(detailVacateTab, "detailVacateDiv")
   }
   
   /**
    * 显示审批年假页
    */
   function showApproveAnnualVacateDiv(){
	   var detailVacateTab = $("#approveVacate");
	   detailVacateTab.show();
	   
	   changeTab(detailVacateTab, "approveAnnualVacateDiv")
	   
   }
   /**
    * 显示审批加班单页面
    */
   function showApproveOverTimeDiv(){
	   var approveVacateTab = $("#approveVacate");
	   approveVacateTab.show();
	   
	   changeTab(approveVacateTab, "approveOverTimeDiv")
   }
   
   /**
    * 显示审批忘记打卡页面
    */
   function showApproveForgetPunchDiv(){
	   var approveVacateTab = $("#approveVacate");
	   approveVacateTab.show();
	   
	   changeTab(approveVacateTab, "approveForgetPunchDiv")
   }
   
   /**
    * 显示请假单详情页
    */
   function showVacateDetailDiv(){
	   var detailVacateTab = $("#detailVacateTab");
	   detailVacateTab.show();
	   
	   changeTab(detailVacateTab, "detailVacateDiv")
   }
   
   /**
    * 显示年假详情页
    */
   function showAnnualVacateDetailDiv(){
	   var detailVacateTab = $("#detailVacateTab");
	   detailVacateTab.show();
	   
	   changeTab(detailVacateTab, "approveAnnualVacateDiv")
   }
   
   /**
    * 显示年假详情页
    */
   function showOverTimeDetailDiv(){
	   var detailVacateTab = $("#detailVacateTab");
	   detailVacateTab.show();
	   
	   changeTab(detailVacateTab, "approveOverTimeDiv")
   }
   /**
    * 显示忘记打卡详情页
    */
   function showForgetPunchDetailDiv(){
	   var detailVacateTab = $("#detailVacateTab");
	   detailVacateTab.show();
	   
	   changeTab(detailVacateTab, "approveForgetPunchDiv")
   }
   
   /**
    * 
    * @param id 申请单id
    * @auth jiwenfang
    * @createTime 2017年3月21日 下午7:40:40
    * @desc 点击修改按钮事件
    *
    */
   function showDetailsDiv(id, noteType, type){
	   //根据社申请单id显示不同的tab耶
	   if(noteType == putongjia){
		   //显示请假单详细信息
		   showVacateDetail(id,type);
	   }else if(noteType == nianjia){
		   //显示年假详细信息
		   showAnnualVacateDetail(id,type);
	   }else if(noteType == overTime){
		   //显示加班单详情
		   showOverTimeDetail(id,type);
	   }else if(noteType == forgetPunch){
		 //显示忘记打卡详情
		   showForgetPunchDetail(id,type);
	   }
   }
   /**
    * 设置请假类型
    */
   function setVacateType(vacteType){
	   //清空相关表单
	   $("#approve_vacate_time").empty();
	   var divValue  = "<input type='text' class='form-control' id='approve_vacate_vacateHours' readonly disabled style='background-color: #FFFFEE'>";
	   var divValue1 = "<span class='day-position'>天</span>";
		
	   if(vacteType == 1){
		   $("#approve_vacate_vacateType").val("事假");
	   }if(vacteType == 2){
		   $("#approve_vacate_vacateType").val("带薪病假");
	   }if(vacteType == 3){
		   $("#approve_vacate_vacateType").val("病假");
	   }if(vacteType == 4){
		   divValue1 = "<span class='day-position'>小时</span>";
		   $("#approve_vacate_applicationType").val(putongjia);//申请单类型:年假
		   $("#approve_vacate_vacateType").val("调休");
	   }if(vacteType == 5){
		   $("#approve_vacate_vacateType").val("婚假");
	   }if(vacteType == 6){
		   $("#approve_vacate_vacateType").val("产检假");
	   }if(vacteType == 7){
		   $("#approve_vacate_vacateType").val("产假");
	   }if(vacteType == 8){
		   $("#approve_vacate_vacateType").val("陪产假");
	   }if(vacteType == 9){
		   $("#approve_vacate_vacateType").val("丧假");
	   }if(vacteType == 10){
		   $("#approve_vacate_vacateType").val("其他");
	   }
	   
	   $("#approve_vacate_time").append(divValue + divValue1);
   }
   //显示年假详细信息
   function showAnnualVacateDetail(id,type){
	   $.ajax({
			type:'POST',
			data:{
				applicationId:id,
				type:'detail'//确保查出的数据包括审批流转信息
			},
			url: yyoa_context +'/oa/annualVacate/selectAnnualVacateVoByApplicationId.do',
			dataType:'json',
			success:function(data){
				$("#annual_vacate_option").empty();
				$("#annual_vacate_button").empty();
				if(data != null){
					$("#approve_annualVacate_applicationId").val(data.annualVacateVo.applicationId);
					$("#approve_annualVacate_userId").val(sessionUserId);
					$("#approve_annualVacate_step").val(data.annualVacateVo.step);
					$("#approve_annualVacate_empId").val(data.annualVacateVo.empId);//申请人员工id
					$("#approve_annualVacate_applicationType").val(nianjia);//申请单类型:年假
					
					$("#approve_annualVacate_orgId").val(data.annualVacateVo.orgId);
					$("#approve_annualVacate_realName").val(data.annualVacateVo.realName);
					$("#approve_annualVacate_orgName").val(data.annualVacateVo.orgName);
					$("#approve_annualVacate_workTime").val(data.annualVacateVo.workTimeStr);
					$("#approve_annualVacate_workAge").val(data.annualVacateVo.workAge);
					$("#approve_annualVacate_entryTime").val(data.annualVacateVo.entryTimeStr);
					$("#approve_annualVacate_totalHours").val(data.annualVacateVo.totalHours/8);
					$("#approve_annualVacate_usedHours").val(data.annualVacateVo.usedHours/8);
					$("#approve_annualVacate_vacateHours").val(data.annualVacateVo.vacateHours/8);
					$("#approve_annualVacate_notUsedHours").val(data.annualVacateVo.notUsedHours/8);
					$("#approve_annualVacate_contactorName").val(data.annualVacateVo.contactorName);
					$("#approve_annualVacate_contactorCall").val(data.annualVacateVo.contactorCall);
					$("#approve_annualVacate_vacateReason").val(data.annualVacateVo.vacateReason);
					$("#approve_annualVacate_workStatus").val(data.annualVacateVo.workStatus);
					$("#approve_annualVacate_startTime").val(data.annualVacateVo.startTimeStr);
					$("#approve_annualVacate_endTime").val(data.annualVacateVo.endTimeStr);
					var buttonDiv = '';
					if(type == 'task'){
						var optionDiv = "<label class='col-md-1 col-sm-1 control-label manage-lab'>"
									  + "<span style='color: red;'>*</span>"
									  + "审批意见：</label>"
									  + "<div class='col-md-2 col-sm-2 manage-wid'>"
						       		  + "<textarea required rows='4' cols='' class='form-control' name='opinion' >"
						       		  + "</textarea></div>";
						buttonDiv = "<button class='btn manage-save manage-left100' type='button' onclick='approveApplication(1,\"AnnualVacate\");'>同意</button>"
							          + "<button class='btn manage-save' type='button' onclick='approveApplication(2,\"AnnualVacate\");'>驳回</button>"
							          + "<button class='btn manage-cancel manage-left100' type='button' onclick='cancelDetailForm();'>返回</button>";
						       		  
						$("#annual_vacate_option").append(optionDiv);  
						
					}else if(type == 'detail'){
						buttonDiv += "<button class='btn manage-cancel manage-left100' type='button' onclick='returnYiBanTaskDiv();'>返回</button>";
					}
					
					$("#annual_vacate_button").append(buttonDiv); 
					
						var tableStr = "<h2>流转信息</h2>" 
									 + "<table class='table table-bordered table_baifenbai'>"
									 + "<thead>"
									 + "<tr>"
									 + "<th>执行环节</th>"
									 + "<th>执行人</th>"
									 + "<th>执行时间</th>"
									 + "<th>提交意见</th>"
									 + "</tr>"
									 + "</thead>"
									 + "<tbody>";
						
						var approvaVoList = data.approvedVo;
						//判断已审批流转信息集合是否为空,不为空,则拼接相关信息
						if(approvaVoList != null){
							//获取流转信息长度
							var len = approvaVoList.length; 
							for(var i=0; i<len; i++){
								var approveStep = approvaVoList[i].step;
								var approveUnit = "";
								if(approveStep == step_one){
									approveUnit = "人力资源部审核";
								}else if(approveStep == step_two){
									approveUnit = "部门负责人意见";
								}else if(approveStep == step_three){
									approveUnit = "人力资源负责人意见";
								}else if(approveStep == step_four){
									approveUnit = "主管领导意见";
								}else if(approveStep == step_five){
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
									approveUnit = "人力资源部审核";
								}else if(approveStep == step_two){
									approveUnit = "部门负责人意见";
								}else if(approveStep == step_three){
									approveUnit = "人力资源负责人意见";
								}else if(approveStep == step_four){
									approveUnit = "主管领导意见";
								}else if(approveStep == step_five){
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
						$("#approveAnnualVacateInfo").empty();
						$("#approveAnnualVacateInfo").append(tableStr);
						
						if(type == 'detail'){
							showAnnualVacateDetailDiv();
						}else if(type == 'task'){
							
							showApproveAnnualVacateDiv();
						}
						
				}
			}
	   });
   }
   
   //显示请假单详细信息
   function showVacateDetail(id,type){
	   //清空各div
	   $("#approveVacateInfo").empty();
	   
	   $.ajax({
			type:'POST',
			data:{
				applicationId:id,
				type:'detail'//确保查出的数据包括审批流转信息
			},
			url: yyoa_context +'/oa/vacate/selectVacateVoByApplicationId.do',
			dataType:'json',
			success:function(data){
				$("#vacate_option").empty(); 
				$("#vacate_button").empty(); 
				
				if(data != null){
					//设置隐藏域的值
					$("#approve_vacate_applicationId").val(data.vacateVo.applicationId);
					$("#approve_vacate_userId").val(sessionUserId);
					$("#approve_vacate_step").val(data.vacateVo.step);
					$("#approve_vacate_empId").val(data.vacateVo.empId);//申请人员工id
					$("#approve_vacate_applicationType").val(0);
					
					$("#approve_vacate_realName").val(data.vacateVo.realName);
					$("#approve_vacate_orgName").val(data.vacateVo.orgName);
					$("#approve_vacate_vacateReason").val(data.vacateVo.vacateReason);
					$("#approve_vacate_workStatus").val(data.vacateVo.workStatus);
					
					$("#approve_vacate_startTime").val(data.vacateVo.startTimeStr);
					$("#approve_vacate_endTime").val(data.vacateVo.endTimeStr);
					
					setVacateType(data.vacateVo.vacateType);//设置请假类型
					
					$("#hidden_vacate_vacateHours").val(data.vacateVo.vacateHours);//设置隐藏的休假时间
					$("#approve_vacate_vacateHours").val(data.vacateVo.vacateHours);//设置显示的休假时间
					//如果不为倒休，则需要 将小时转换成天来显示
					if(data.vacateVo.vacateType != 4){
						$("#approve_vacate_vacateHours").val(data.vacateVo.vacateHours/8);
					}
					
					var buttonDiv = '';
					if(type == 'task'){
						var optionDiv = "<label class='col-md-1 col-sm-1 control-label manage-lab'>"
									  + "<span style='color: red;'>*</span>"
									  + "审批意见：</label>"
									  + "<div class='col-md-2 col-sm-2 manage-wid'>"
						       		  + "<textarea required rows='4' cols='' class='form-control' name='opinion' >"
						       		  + "</textarea></div>";
						
						buttonDiv = "<button class='btn manage-save manage-left100' type='button' onclick='approveApplication(1,\"Vacate\");'>同意</button>"
					          			+ "<button class='btn manage-save' type='button' onclick='approveApplication(2,\"Vacate\");'>驳回</button>"
					          			+ "<button class='btn manage-cancel manage-left100' type='button' id='cancelVacateCancelBtn' onclick='cancelDetailForm()'>返回</button>";
						
						$("#vacate_option").append(optionDiv);  
					}else if(type == 'detail'){
						buttonDiv += "<button class='btn manage-cancel manage-left100' type='button' onclick='returnYiBanTaskDiv();'>返回</button>";
					}
					
					$("#vacate_button").append(buttonDiv); 
					
					var tableStr = "<h2>流转信息</h2>" 
						 + "<table class='table table-bordered table_baifenbai'>"
						 + "<thead>"
						 + "<tr>"
						 + "<th>执行环节</th>"
						 + "<th>执行人</th>"
						 + "<th>执行时间</th>"
						 + "<th>提交意见</th>"
						 + "</tr>"
						 + "</thead>"
						 + "<tbody>";
					var approvaVoList = data.approvedVo;
					//判断已审批流转信息集合是否为空,不为空,则拼接相关信息
					if(approvaVoList != null){
						//获取流转信息长度
						var len = approvaVoList.length; 
						for(var i=0; i<len; i++){
							var approveStep = approvaVoList[i].step;
							var approveUnit = "";
							if(approveStep == step_one){
								approveUnit = "人力资源部审核";
							}else if(approveStep == step_two){
								approveUnit = "部门负责人意见";
							}else if(approveStep == step_three){
								approveUnit = "人力资源负责人意见";
							}else if(approveStep == step_four){
								approveUnit = "主管领导意见";
							}else if(approveStep == step_five){
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
								approveUnit = "人力资源部审核";
							}else if(approveStep == step_two){
								approveUnit = "部门负责人意见";
							}else if(approveStep == step_three){
								approveUnit = "人力资源负责人意见";
							}else if(approveStep == step_four){
								approveUnit = "主管领导意见";
							}else if(approveStep == step_five){
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
					
					$("#approveVacateInfo").append(tableStr);
					
					if(type == 'detail'){
						showVacateDetailDiv();
					}else if(type == 'task'){
						showApproveVacateDiv();
					}
				}
			}
	   });
   }
  
   /**
    * 显示加班单详情
    * @param id
    * @param type
    */
   function showOverTimeDetail(id,type){
	   $.ajax({
			type:'POST',
			data:{
				applicationId:id,
				type:'detail'//确保查出的数据包括审批流转信息
			},
			url: yyoa_context +'/oa/overTime/selectOverTimeVoByApplicationId.do',
			dataType:'json',
			success:function(data){
				$("#overTime_option").empty();
				$("#overTime_button").empty();
				if(data != null){
					$("#approve_overTime_applicationId").val(data.overTimeVo.applicationId);
					$("#approve_overTime_userId").val(sessionUserId);
					$("#approve_overTime_step").val(data.overTimeVo.step);
					$("#approve_overTime_empId").val(data.overTimeVo.empId);//申请人员工id
					$("#approve_overTime_applicationType").val(overTime);//申请单类型
					
					
					
					$("#approve_overTime_realName").val(data.overTimeVo.submitUserName);
					$("#approve_overTime_orgName").val(data.overTimeVo.orgName);
					$("#approve_overTime_startTime").val(data.overTimeVo.startTimeStr);
					$("#approve_overTime_endTime").val(data.overTimeVo.endTimeStr);
					$("#approve_overTime_overtimeHour").val(data.overTimeVo.overtimeHour);
					$("#approve_overTime_overtimeReason").val(data.overTimeVo.overtimeReason);
					$("#approve_overTime_overtimeType").val(setOverTimeType(data.overTimeVo.overtimeType));
					
					var buttonDiv = '';
					if(type == 'task'){
						var optionDiv = "<label class='col-md-1 col-sm-1 control-label manage-lab'>"
									  + "<span style='color: red;'>*</span>"
									  + "审批意见：</label>"
									  + "<div class='col-md-2 col-sm-2 manage-wid'>"
						       		  + "<textarea required rows='4' cols='' class='form-control' name='opinion' >"
						       		  + "</textarea></div>";
						buttonDiv = "<button class='btn manage-save manage-left100' type='button' onclick='approveApplication(1,\"OverTime\");'>同意</button>"
							          + "<button class='btn manage-save' type='button' onclick='approveApplication(2,\"OverTime\");'>驳回</button>"
							          + "<button class='btn manage-cancel manage-left100' type='button' onclick='cancelDetailForm();'>返回</button>";
						       		  
						$("#overTime_option").append(optionDiv);  
						
					}else if(type == 'detail'){
						buttonDiv += "<button class='btn manage-cancel manage-left100' type='button' onclick='returnYiBanTaskDiv();'>返回</button>";
					}
					
					$("#overTime_button").append(buttonDiv); 
					
						var tableStr = "<h2>流转信息</h2>" 
									 + "<table class='table table-bordered table_baifenbai'>"
									 + "<thead>"
									 + "<tr>"
									 + "<th>执行环节</th>"
									 + "<th>执行人</th>"
									 + "<th>执行时间</th>"
									 + "<th>提交意见</th>"
									 + "</tr>"
									 + "</thead>"
									 + "<tbody>";
						
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
						$("#approveOverTimeInfo").empty();
						$("#approveOverTimeInfo").append(tableStr);
						
						if(type == 'detail'){
							 showOverTimeDetailDiv();
						}else if(type == 'task'){
							showApproveOverTimeDiv();
						}
						
				}
			}
	   });
   }
   
   /**
    * 显示忘记打卡详情页
    * @param id
    * @param type
    */
   function showForgetPunchDetail(id,type){
	   $.ajax({
			type:'POST',
			data:{
				applicationId:id,
				type:'detail'//确保查出的数据包括审批流转信息
			},
			url: yyoa_context +'/oa/forgetPunch/selectForgetPunchVoByApplicationId.do',
			dataType:'json',
			success:function(data){
				$("#forgetPunch_option").empty();
				$("#forgetPunch_button").empty();
				if(data != null){
					$("#approve_forgetPunch_applicationId").val(data.forgetPunchVo.applicationId);
					$("#approve_forgetPunch_userId").val(sessionUserId);
					$("#approve_forgetPunch_step").val(data.forgetPunchVo.step);
					
					
					$("#approve_forgetPunch_realName").val(data.forgetPunchVo.submitUserName);
					$("#approve_forgetPunch_orgName").val(data.forgetPunchVo.orgName);
					$("#approve_forgetPunch_time").val(data.forgetPunchVo.timeStr);
					$("#approve_forgetPunch_forgetpunchReason").val(data.forgetPunchVo.forgetpunchReason);
					
					//获取忘记打卡时间段
					var timeQuantum = data.forgetPunchVo.timeQuantum;
					//上午
					if(timeQuantum == am){
						$("#approve_forgetPunch_timeQuantum").val("上午");
					}else if(timeQuantum == pm){//下午
						$("#approve_forgetPunch_timeQuantum").val("下午");
					}else if(timeQuantum == allday){//全天
						$("#approve_forgetPunch_timeQuantum").val("全天");
					}
					
					var buttonDiv = '';
					if(type == 'task'){
						var optionDiv = "<label class='col-md-1 col-sm-1 control-label manage-lab'>"
									  + "<span style='color: red;'>*</span>"
									  + "审批意见：</label>"
									  + "<div class='col-md-2 col-sm-2 manage-wid'>"
						       		  + "<textarea required rows='4' cols='' class='form-control' name='opinion' >"
						       		  + "</textarea></div>";
						buttonDiv = "<button class='btn manage-save manage-left100' type='button' onclick='approveApplication(1,\"ForgetPunch\");'>同意</button>"
							          + "<button class='btn manage-save' type='button' onclick='approveApplication(2,\"ForgetPunch\");'>驳回</button>"
							          + "<button class='btn manage-cancel manage-left100' type='button' onclick='cancelDetailForm();'>返回</button>";
						       		  
						$("#forgetPunch_option").append(optionDiv);  
						
					}else if(type == 'detail'){
						buttonDiv += "<button class='btn manage-cancel manage-left100' type='button' onclick='returnYiBanTaskDiv();'>返回</button>";
					}
					
					$("#forgetPunch_button").append(buttonDiv); 
					
						var tableStr = "<h2>流转信息</h2>" 
									 + "<table class='table table-bordered table_baifenbai'>"
									 + "<thead>"
									 + "<tr>"
									 + "<th>执行环节</th>"
									 + "<th>执行人</th>"
									 + "<th>执行时间</th>"
									 + "<th>提交意见</th>"
									 + "</tr>"
									 + "</thead>"
									 + "<tbody>";
						
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
									approveUnit = "主管领导意见";
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
									approveUnit = "主管领导意见";
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
						$("#approveForgetPunchInfo").empty();
						$("#approveForgetPunchInfo").append(tableStr);
						
						if(type == 'detail'){
							 showForgetPunchDetailDiv();
						}else if(type == 'task'){
							showApproveForgetPunchDiv();
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
    * 审批申请单
    * @param status
    * @param applicationType
    */
   function approveApplication(status, applicationType){
	   var flag = $.html5Validate.isAllpass($("#approve" +applicationType+ "Form"));
	   //判断校验是否通过
	   if(!flag){
		   return;
	   }else{
		   $("#approve_"+applicationType+ "_status").val(status);
		   $("#approve_annualVacate_vacateHours").val( $("#approve_annualVacate_vacateHours").val()*8);
		   $.ajax({
				type:'POST',
				url : yyzc_pro_context + "/oa/approve/approveApplication.do",
				data : $('#approve'+applicationType+'Form').serialize(),
				dataType:'json',
				success:function(data){
					Modal.alert({msg:data.message});
					if(data.state){
						var listBtn = document.getElementById("listTabBtn");
						changeTab(listBtn,'listDiv');
						cancelTaskForm();//隐藏任务办理tab页
						cancelDetailForm();//隐藏任务详情tab页
						seachDaiBanVacateList();//刷新待办任务列表
					}
				}
		   });
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
    * @desc 隐藏任务办理tab页
    */
   function cancelTaskForm(){
	   resetForm("approveVacateForm");//重置请假单详情页面
	   resetForm("approveAnnualVacateForm");//重置年假单详情页面
       $("#approveVacate").hide();
   }
   
   /**
    * 隐藏任务详情tab页
    */
   function cancelDetailForm(){
	   resetForm("approveVacateForm");//重置请假单详情页面
	   resetForm("approveAnnualVacateForm");//重置年假单详情页面
       $("#detailVacateTab").hide();
       $("#approveVacate").hide();
       
       var listBtn = document.getElementById("listTabBtn");
       changeTab(listBtn, 'listDiv');
   }
    
   /**
    * 返回已办任务列表
    */
   function returnYiBanTaskDiv(){
	   resetForm("approveVacateForm");//重置请假单详情页面
	   resetForm("approveAnnualVacateForm");//重置年假单详情页面
	   $("#detailVacateTab").hide();
       $("#approveVacate").hide();
       
       var yibanBtn = document.getElementById("yibanTabBtn");
       changeTab(yibanBtn, 'finishedListDiv');
   }
  /**
   * 部门属性JSON
   * @type {{deptId: null, deptName: null}}
   */
   var deptInfo = {
       deptId: null,
	   deptName: null
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
           success: function (data) {
           		console.log(data);
               deptInfo.deptId = data.depId;
               deptInfo.deptName = data.depName;
           }
       });
   }
   
   $(function(){
       // 获取用户的部门信息
       getDeptInfo(sessionUserId);

		$("#listTabBtn").click(function(){
			changeTab(this,'listDiv');
			cancelTaskForm();//隐藏任务办理tab页
			cancelDetailForm();//隐藏任务详情tab页
		});
		seachDaiBanVacateList(1);
	})