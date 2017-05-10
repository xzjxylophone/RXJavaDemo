 /**
* 
* @param id 角色ID
* @returns
* @auth jiwenfang
* @createTime 2017年3月22日 下午9:13:54
* @desc 根据申请单id删除申请单
*
*/
 function deleteApplication(id,type){
       Modal.confirm(
           {
               msg: "确定要删除吗？"
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
                    	   if(type == 'vacate'){//刷新请假单列表
                    		   seachVacateList();
                    	   }else if(type == 'annualVacate'){//刷新年假列表
                    		   seachAnnualVacateList();
                    	   }else if(type == 'forgetPunch'){//刷新忘记打卡列表
                    		   seachForgetPunchList(); 
                    	   }else if(type == 'overTime'){//刷新加班单列表
                    		   seachOverTimeList();
                    	   }
                       }
                   }
               });
           }
       });
 }
 
 
 /**
  * 部门属性JSON
  * @type {{deptId: null, deptName: null}}
  */
  var deptInfo = {
	   deptId: null,
	   deptName: null,
	   entryTime : null,
       workTime : null,
	   leaderId : null,//部门负责人Id
	   supleaderId : null,//主管领导id
	   empId : null//员工id
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
              deptInfo.deptId = data.depId;
              deptInfo.deptName = data.depName;
              deptInfo.entryTime = data.entryTime;
              deptInfo.workTime = data.workTime;
              deptInfo.leaderId = data.leaderId;//部门负责人id
              deptInfo.supleaderId = data.supleaderId;//主管领导id
              deptInfo.empId = data.id;//员工id
          }
      });
  }
     