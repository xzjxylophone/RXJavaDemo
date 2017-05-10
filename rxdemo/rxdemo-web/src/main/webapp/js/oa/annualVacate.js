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
	   //清空表单
	   resetForm("addAnnualVacateForm");
	   $.ajax({
			type:'POST',
			url : yyzc_pro_context + "/oa/remain/selectByEmpId.do",
			data : {
				empId : deptInfo.empId
			},
			dataType:'json',
			success:function(data){
				 //设置用户已休年假天数
				$("#add_usedDays").val(data.totalAnnualVacate/8-data.notUsedAnnualVacate/8);
				//设置应休年假天数
				$("#add_totalDays").val(data.totalAnnualVacate/8);
				$("#add_notUsedDays").val(data.notUsedAnnualVacate/8);
				
				 //设置用户已休年假时间：以小时为单位
				$("#add_usedHours").val(data.totalAnnualVacate-data.notUsedAnnualVacate);
				//设置应休年假时间：以小时为单位
				$("#add_totalHours").val(data.totalAnnualVacate);
				$("#add_notUsedHours").val(data.notUsedAnnualVacate);
			   
				//设置申请人及申请人的ID
				$("#add_realName").val(sessionUserRealName);
				$("#add_userId").val(sessionUserId);
				$("#add_orgName").val(deptInfo.deptName);
				$("#add_orgId").val(deptInfo.deptId);
				   
				$("#add_workTime").val(date2Str(deptInfo.workTime,"yyyy-MM-dd") );
				$("#add_entryTime").val(date2Str(deptInfo.entryTime,"yyyy-MM-dd"));
			   
			   //隐藏其他tab页
			  cancelEditForm();
			  cancelDetailForm();
		      changeTab(thisElement, "addAnnualVacateDiv");
			}
	   });
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
  
  //检查休假天数是否合法
  function checkDays(shengyu, shiji, totalDays, usedDays, startTime, endTime){
	  var flag = false;
	  	//检查应休天数
	   if(checkDayNum(totalDays, "本年度应休天数应为0.5的倍数")){
		   flag = true;
		}
	   //检查申请天数
	   if(checkDayNum(shiji, "请假天数应为0.5的倍数")){
		   flag = true;
		}
	   //判断请假结束时间是否大于请假开始时间
		if(checkVacateDays(startTime,endTime)){
			 flag = true;
		}
      return flag;
  }
   /**
    * @auth jiwenfang
    * @desc 保存新添加的年假
    */
   function saveAddVacate(status){
	   //校验所填信息是否合法
	   if(checkInfo('add')){
		   return;
	   }
       
	   var flag = $.html5Validate.isAllpass($("#addAnnualVacateForm"));
	   if(!flag){
		   return;
	   }else{
		   
		   addVacate(status);
	   }
   }  
  /**
   * 保存年假信息
   * @param status
   */
   function addVacate(status){
	 //根据请假单状态,判断是否设置审批人
	   if(status == approveing){//提交状态,设置审批人
		   //隐藏弹出层
		   closeApproveUsers();
	   }
	   //设置年假单状态值
	   $("#add_status").val(status);
	   $("#add_orgId").val(deptInfo.deptId);
	   $("#add_createUser").val(sessionUserId);
	   $("#add_empId").val(deptInfo.empId);//员工id
	   $("#add_vacateHours").val($("#add_vacateDays").val() * 8);//申请时间
	   
	  $.ajax({
			type:'POST',
			url : yyzc_pro_context + "/oa/annualVacate/addAnnualVacate.do",
			data : $('#addAnnualVacateForm').serialize(),
			dataType:'json',
			async: false,
			success:function(data){
				Modal.alert({msg: data.message});
				if(data.state){
					// 清理表单
					cancelSaveForm();
					// 刷新分页
					seachAnnualVacateList();
				}
			}
	   });
   }
   
   /**
    * 修改年假
    * @param status
    */
   function saveEditVacate(status){
	   //校验所填信息是否合法
	   if(checkInfo('edit')){
		   return;
	   }
	   
	   var flag = $.html5Validate.isAllpass($("#editAnnualVacateForm"));
	   if(!flag){
		   return;
	   }else{
		   updateAnnualVacate(status);
	   }
   }   
   
   /**
    * 保存修改后的信息
    * @param status
    */
   function updateAnnualVacate(status){
	 //根据请假单状态,判断是否设置审批人
	   if(status == approveing){//提交状态,设置审批人
		 //隐藏弹出层
		  closeApproveUsers();
	   }
	   //设置年假单状态值
	   $("#edit_status").val(status);
	   $("#edit_vacateHours").val($("#edit_vacateDays").val()*8);
	   
	   $.ajax({
			type:'POST',
			url : yyzc_pro_context + "/oa/annualVacate/updateAnnualVacate.do",
			data : $('#editAnnualVacateForm').serialize(),
			dataType:'json',
			success:function(data){
				Modal.alert({msg: data.message});
				if(data.state){
					// 清理表单
					cancelSaveForm();
					//隐藏修改表单
					cancelEditForm();
					// 刷新分页
					seachAnnualVacateList();
				}
			}
	   });
   }
   /**
    * @auth wangjing
    * @desc 添加角色页面取消按钮触发事件
    */
   function cancelSaveForm(){
       resetForm("addAnnualVacateForm");
	   // 切换回表格tab
       var listBtn = document.getElementById("listTabBtn");
       changeTab(listBtn, 'listDiv');
   }
   
   /**
    * 显示修改请假单页面
    */
   function showEditDiv(){
	   var detailVacateTab = $("#detailAnnualVacateTab");
	   detailVacateTab.hide();
		
	   var editVacateTab = $("#editAnnualVacateTab");
		editVacateTab.show();
		
		changeTab(editVacateTab, "editAnnualVacateDiv")
   }
   
   /**
    * 显示详情页面
    */
   function showDetailDiv(){
	   var detailAnnualVacateTab = $("#detailAnnualVacateTab");
	   detailAnnualVacateTab.show();
		
	   var editVacateTab = $("#editAnnualVacateTab");
		editVacateTab.hide();
		
		changeTab(detailAnnualVacateTab, "detailAnnualVacateDiv")
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
			url: yyoa_context +'/oa/annualVacate/selectAnnualVacateVoByApplicationId.do',
			dataType:'json',
			success:function(data){
				if(data != null){
					if(type == 'edit'){
						$("#edit_createTime").val(data.annualVacateVo.createTimeStr);
						$("#edit_createUser").val(data.annualVacateVo.createUser);
						$("#edit_annualVacateId").val(data.annualVacateVo.id);//年假id
						$("#edit_applicationId").val(data.annualVacateVo.applicationId);//申请单id
						$("#edit_userId").val(data.annualVacateVo.createUser);
						$("#edit_orgId").val(data.annualVacateVo.orgId);
						$("#edit_empId").val(deptInfo.empId);
					}
					$("#"+type+"_realName").val(data.annualVacateVo.realName);
					$("#"+type+"_orgName").val(data.annualVacateVo.orgName);
					$("#"+type+"_workTime").val(data.annualVacateVo.workTimeStr);
					$("#"+type+"_workAge").val(data.annualVacateVo.workAge);
					$("#"+type+"_entryTime").val(data.annualVacateVo.entryTimeStr);
					$("#"+type+"_contactorName").val(data.annualVacateVo.contactorName);
					$("#"+type+"_contactorCall").val(data.annualVacateVo.contactorCall);
					$("#"+type+"_vacateReason").val(data.annualVacateVo.vacateReason);
					$("#"+type+"_workStatus").val(data.annualVacateVo.workStatus);
					$("#"+type+"_startTime").val(data.annualVacateVo.startTimeStr);
					$("#"+type+"_endTime").val(data.annualVacateVo.endTimeStr);
					
					$("#"+type+"_totalHours").val(data.annualVacateVo.totalAnnualVacate);//应休年假时间
					$("#"+type+"_vacateHours").val(data.annualVacateVo.vacateHours);//申请时间
					$("#"+type+"_totalDays").val(data.annualVacateVo.totalAnnualVacate/8);//应休年假天数
					$("#"+type+"_vacateDays").val(data.annualVacateVo.vacateHours/8);//申请天数
					
					//如果年假单处于已保存或驳回状态，则已使用天数、未使用天数使用用户剩余假期表中的数据
					if(data.annualVacateVo.status == saved ||data.annualVacateVo.status == overrule){
						//天
						var shenyu = data.annualVacateVo.notUsedAnnualVacate/8;
						$("#"+type+"_usedDays").val($("#"+type+"_totalDays").val() - shenyu);//已使用天数
						$("#"+type+"_notUsedDays").val($("#"+type+"_totalDays").val() - $("#"+type+"_usedDays").val() - $("#"+type+"_vacateDays").val());//剩余天数:应休天数-已使用天数-本次申请天数
						//小时
						$("#"+type+"_usedHours").val($("#"+type+"_totalHours").val() - data.annualVacateVo.notUsedAnnualVacate);//已使用小时
						$("#"+type+"_notUsedHours").val($("#"+type+"_totalHours").val() - $("#"+type+"_usedHours").val() - $("#"+type+"_vacateHours").val());//剩余小时:应休小时-已使用小时-本次申请小时
					}else{//如果年假单处于已提交或审批通过状态，则已年假单中记录的数据
						//小时
						$("#"+type+"_usedHours").val(data.annualVacateVo.usedHours);
						$("#"+type+"_notUsedHours").val(data.annualVacateVo.notUsedHours);
						
						//天
						$("#"+type+"_usedDays").val(data.annualVacateVo.usedHours/8);
						$("#"+type+"_notUsedDays").val(data.annualVacateVo.notUsedHours/8);
					}
					
					if(type == 'detail'){
						var tableStr = "<h2>流转信息</h2>" 
									 + "<table class='table table-bordered '>"
									 + "<thead>"
									 + "<tr>"
									 + "<th>执行环节</th>"
									 + "<th>执行人</th>"
									 + "<th>执行时间</th>"
									 + "<th>提交意见</th>"
									 + "</tr>"
									 + "</thead>"
									 + "<tbody class='shenpi'>";
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
									approveUnit = "人力资源部门审核";
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
									approveUnit = "人力资源部门审核";
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
						$("#approveInfo").empty();
						$("#approveInfo").append(tableStr);
						
					}
				}
			}
	   });
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
	   resetForm("editAnnualVacateForm");
       $("#editAnnualVacateTab").hide();
   }
   
   /**
    * 隐藏详情tab页
    */
   function cancelDetailForm(){
	   resetForm("detailAnnualVacateForm");
       $("#detailAnnualVacateTab").hide();
       
       var listBtn = document.getElementById("listTabBtn");
       changeTab(listBtn, 'listDiv');
   }
   /**
    * 校验所填信息
    * @param option
    */
   function checkInfo(option){
	   var flag = false;
	   var startTime = $("#"+option+"_startTime").val();//休假开始时间
	   var endTime = $("#"+option+"_endTime").val();//休假结束时间
	   
	   var shengyu = $("#"+option+"_notUsedDays").val();//剩余休假天数
	   var shiji = $("#"+option+"_vacateDays").val();//申请休假天数
	   var totalHours =  $("#"+option+"_totalDays").val();//应休天数
	   var usedHours = $("#"+option+"_usedDays").val();//已休天数
	   
	   //检查休假天数是否合法
	   if(checkDays(shengyu, shiji, totalHours, usedHours, startTime, endTime)){
		   flag = true;
	   }
	   return flag;
   }
   
   //显示弹出层
   function showApproveUsers(type){
	   //检验所填信息
	   if(checkInfo(type)){
		   return;
	   }
       
	   var flag = $.html5Validate.isAllpass($("#"+type+"AnnualVacateForm"));
	   if(!flag){
		   return;
	   }else{
		   $.ajax({
               type: 'POST',
               url: yyoa_context + '/ou/department/selectApproveByDepId.do',
               dataType: 'json',
               data: {
            	   userDepId : deptInfo.deptId,
            	   userId: sessionUserId
               },
               async: false,
               success: function (data) {
                   if (data != null) {
                	   var approveUsers = data["renli"].userId + "," +data["leader"].userId + "," + data["renli"].userId + "," + data["supleader"].userId + "," + data["ceo"].userId;
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
                	   	//拼接行---人力资源部审核   步数：1
                	   	tableStr += "<tr>"
		                         + "<td>人力资源审批</td>"
		                         + "<td >"+data["renli"].userRealName+"</td></tr>";
                	 
                	    //拼接行---部门负责人意见   步数：2
	               	    tableStr += "<tr>"
		                         + "<td>部门负责人审批</td>"
		                         + "<td >" + data["leader"].userRealName + "</td></tr>";
                	   
                	  //拼接行---人力资源部负责人意见   步数：4
	               	    tableStr += "<tr>"
	               	    		 + "<td>人力资源审批</td>"
	               	    		 + "<td >"+data["renli"].userRealName+"</td></tr>";
                	    
                	  //拼接行---主管领导意见   步数：5
	               	    tableStr += "<tr>"
                                 + "<td>主管领导审批</td>"
                                 + "<td >"+data["supleader"].userRealName+"</td></tr>";
                	    
                	    //拼接行---CEO意见  步数：6
	               	    tableStr += "<tr>"
	               	    		 + "<td>CEO</td>"
	               	    		 + "<td >"+data["ceo"].userRealName+"</td></tr>";
                	    
                	    var temp = "";
                	    if(type == "add"){
                	    	temp = "<button class='btn absent-addto manage-cancel' type='button' onclick='addVacate(" + approveing + ")'>";
                	    }else if(type == "edit"){
                	    	temp = "<button class='btn absent-addto manage-cancel' type='button' onclick='updateAnnualVacate(" + approveing + ")'>";
                	    }
                	   tableStr += "</tbody></table>"
                		     + "<div class='absent-con'>"
                		     + temp
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
	
   /**
    * 检查请假天数
    * @param num
    * @returns {boolean}
    */
    function checkDayNum(num, msg){
        var flag = false;
        if(!(num>0 && num%0.5==0)){ //是否为0.5的倍数
            Modal.alert({msg: msg});
            flag = true;
        }
        return flag;
    }
    
    
   //关闭弹出层
   function closeApproveUsers(){
       $("#approveUsers").fadeOut('slow');
   }

   /**
    * 设置年假剩余天数
    */
   function setNotUsedHours(type){
	   var totalHours = $("#" + type + "_totalDays").val();//应休天数
	   var usedHours = $("#" + type + "_usedDays").val();//本年度已休天数
	   var vacateHours = $("#" + type + "_vacateDays").val();//本次申请的天数
	   
	   if(!(totalHours == null || totalHours == 0) 
			   && !(vacateHours == null)){
		 //计算本年度剩余天数
		   var notUsedHours = totalHours - usedHours - vacateHours;
		   //判断申请的年假数是否合法
		   if(notUsedHours < 0){
			   Modal.alert({msg: "申请的年假数超过了本年度剩余天数！"});
			   $("#" + type + "_notUsedHours").val(""); 
			   $("#" + type + "_notUsedDays").val(""); 
		   }else{//设置年假剩余天数
			   $("#" + type + "_notUsedHours").val($("#" + type + "_totalHours").val() - $("#" + type + "_usedHours").val() -$("#" + type + "_vacateHours").val()); 
			   $("#" + type + "_notUsedDays").val(notUsedHours); 
		   }
		   
	   }
	   
	   
	   //判断是否填写了应休天数
	   if((totalHours == null || totalHours == 0) 
			   && !(vacateHours == null || vacateHours == 0)){
		   Modal.alert({msg: "请填写应休天数,以便计算剩余天数"});
		   return;
	   }
   }
   

   $(function(){
        // 获取用户的部门信息
   		getDeptInfo(sessionUserId);
	   //添加:保存为草稿
		$("#addAnnualVacateSaveBtn").click(function(){
			saveAddVacate(saved);
		});
		
		//添加:保存并提交
		$("#addsubmitAnnualVacateBtn").click(function(){
			showApproveUsers('add');
		});
		
		$("#addAnnualVacateCancelBtn").click(function(){
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
			changeTab(this,'listDiv');
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