<%@page import="cn.yiyizuche.common.ou.user.entity.UserVo"%>
<%@page import="cn.yiyizuche.common.sys.util.SysConstants"%>
<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%	
	response.setHeader("Pragma","No-Cache");
	response.setHeader("Cache-Control","No-Cache");
	response.setDateHeader("Expires", 0);	
    Object pageSessionUser = session.getAttribute(SysConstants.USER_INFO);
    UserVo userVo = (UserVo)pageSessionUser;
    String _userId = "";//在页面上记录session中的userId。在通过页面请求其他功能时(点击按钮)，通过过滤器判断是否已在别处用其他用户登录
    String _userRealName = "";
    String _userName = "";
    Date _birthdate = null;
    if (userVo != null){
        _userId =  userVo.getUserId()+"";
        _userRealName =  userVo.getUserRealName();
        _userName = userVo.getUserName();
        _birthdate = userVo.getBirthdate();
    }
%> 
<%@ include file="/page/common/taglibs.jsp" %>
<script type="text/javascript">
	var sessionUserId = '<%=_userId%>';
	var sessionUserName = '<%=_userName%>';
	var sessionUserRealName = '<%=_userRealName%>';
	var sessionBirthday = '<%=_birthdate%>';
</script>
<c:set var="_userId" value="<%=_userId %>"/>
<c:set var="_userName" value="<%=_userName%>"/>
<c:set var="_userRealName" value="<%=_userRealName%>"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="stylesheet" type="text/css" href="${ctx}/css/base.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/loading.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.css" />
<link href="${ctx }/css/login.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/foundation-datepicker.min.css" />
<!--[if lt IE 9]>
      <script src="${ctx }/js/common/html5shiv.min.js"></script>
      <script src="${ctx }/js/common/respond.min.js"></script>
      <script src="${ctx }/js/h5-c3/ie-css3.js"></script>
      <script src="${ctx }/js/h5-c3/DOMAssistantCompressed-2.7.4.js"></script>
      <link href="${ctx }/css/ie8base.css" rel="stylesheet" />
      <link href="${ctx }/css/ie8index.css" rel="stylesheet" />
    <![endif]-->

<!-- bootgrid -->
<link href="${ctx }/css/bootgrid/jquery.bootgrid.css" rel="stylesheet" />
<script language="javascript" type="text/javascript" src="${ctx}/js/bootgrid/moderniz.2.8.1.js"></script>
<script language="javascript" type="text/javascript" src="${ctx }/js/bootgrid/jquery.bootgrid.js"></script>
<script language="javascript" type="text/javascript" src="${ctx }/js/bootgrid/jquery.bootgrid.fa.js"></script>
<!-- html5Validate -->
<script language="javascript" type="text/javascript" src="${ctx}/js/html5Validate/jquery-html5Validate.js"></script>

<script language="javascript" type="text/javascript" src="${ctx}/js/common/bootstrap.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/common/html5shiv.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/common/respond.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/login/index.js"></script>

<script language="javascript" type="text/javascript" src="${ctx}/js/common/foundation-datepicker.min.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/js/common/foundation-datepicker.zh-CN.js"></script>

<input type="hidden" value="${_userId}" id="_userId"></input>

<!-- alert和confirm 弹出框 -->
<script language="javascript" type="text/javascript" src="${ctx}/js/common/alert-confirm.js" ></script>
<!-- system modal start -->
<div id="ycf-alert" class="modal">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                <h5 class="modal-title"><i class="fa fa-exclamation-circle"></i> [Title]</h5>
            </div>
            <div class="modal-body small">
                <p>[Message]</p>
            </div>
            <div class="modal-footer" >
                <button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>
                <button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>
            </div>
        </div>
    </div>
</div>
<!-- system modal end -->

<script type="text/javascript">
    /**
     * 显示重置密码弹出框
     * @returns
     */
    function showSetPwd (){
        $("#changePwdDivInCommmon").fadeIn('slow');
    }
    /**
     * 关闭重置密码弹出框
     * @returns
     */
    function closeSetPwd (){
        $("#changePwdForm")[0].reset();
        $("#changePwdDivInCommmon").fadeOut('slow');
    }

    /**
     * 修改密码
     */
    function okChangePwd(){
        var oldPwd =  $("#oldPwdInput").val();
        var newPwd =  $("#changePwd").val();
        var againNewPwd =  $("#changePwdAgain").val();

        var result = false;
        var msg = "";

        if (oldPwd == null || oldPwd == undefined || oldPwd == "") {
            msg = "旧密码不能为空";
        } else if (newPwd == null || newPwd == undefined || newPwd == "") {
            msg = "新密码不能为空";
        } else if (newPwd != againNewPwd) {
            msg = "两次密码不一致";
        } else {
            result = true;
        }
        if (result == false) {
            Modal.alert({msg:msg});
            return;
        }

        $.ajax({
            type : 'POST',
            url :  yyoa_context+'/changePwd.do',
            data :{
                oldPwd :  oldPwd,
                newPwd : newPwd
            },
            dataType : 'json',
            success : function(data) {
                if(data.state){
                    Modal.alert({msg:"密码修改成功，请重新登录系统"}).on( function (e) {
                        logout();
                    });
                    closeSetPwd();
                }else{
                    Modal.alert({msg:data.message});
                }
            },
        });
    }

    /**
     * 登出系统
     */
    function logout() {
        $.ajax({
            type : 'POST',
            url :  yyoa_context+'/logout.do',
            dataType : 'json',
            success : function(data) {
                if(data.state == false){
                    alert(data.message);
                } else {
                    window.location.href = yyoa_context+'/loginPage.do';
                }
            },
        });
    }

    /**
     * 重置表单
     * @param formName
     */
    function resetForm(formName) {
        $('#'+formName)[0].reset();
    }


    $(function(){
        /**
         * 点击显示表格数据条数按钮
         *
         */
        function yy_select_pagesize(){
            $('ul.pull-right li').on('click',function(){
                var $yy_select=$(this).children('a').text();//输出对应的li下面a的内容
                var fiframe=$(window.parent.document);
                //初始样式为10的状态
//                $(window.frames["rightName"].document).find('.main_bottom,.index_right').css('height','1000px');
//                fiframe.find('.main_bottom').css('height','1000px');
//                fiframe.find('.index_main,.main_bottom,.index_right').css("height","770px");
                if($yy_select=='10'){
                    fiframe.find('.index_main,.main_bottom,.index_right').css("height","770px");
                }else if($yy_select=='25'){
                    fiframe.find('.index_main,.main_bottom,.index_right').css("height","1250px");
                }else{
                    fiframe.find('.index_main,.main_bottom,.index_right').css("height","2100px");
                }
            })
        }
         yy_select_pagesize();
        /**
         * 获取readonly属性为对应的标签设置背景属性
         * $("input[name=new]")
         * disabled="disabled"
         */
        function input_readyonly(){
            $('input[readonly="readonly"][readonly][placeholder!="请选择日期"][placeholder!="请选择角色权限"],input[disabled="disabled"],textarea[readonly="readonly"][readonly][disabled="disabled"]').css({'background-color':'#FFFFEE'});
            $('textarea').css('padding','5px');
            $('.depart-leader').css({'background-color':'#eee'});
        }
        input_readyonly();
    });




</script>
<!--修改密码start-->
<div class="absent-layer-changeP absent-layer" id="changePwdDivInCommmon" style="display: none;">
    <div class="absent-clickbox">
        <h2>修改密码</h2>
        <form action="" method="post" id="changePwdForm">
            <div class="form-group">
                <label for="changePwd" class="col-md-5 col-sm-5 control-label manage-lab"><span style="color: red;">*</span>旧密码：</label>
                <input type="password"  class="form-control" name="changePwd" id="oldPwdInput" placeholder="请输入旧密码"/>
            </div>
            <div class="form-group">
                <label for="changePwd" class="col-md-5 col-sm-5 control-label manage-lab"><span style="color: red;">*</span>新密码：</label>
                <input type="password"  class="form-control" name="changePwd" id="changePwd" placeholder="请输入新密码"/>
            </div>
            <div class="form-group">
                <label for="changePwdAgain" class="col-md-5 col-sm-5 control-label manage-lab"><span style="color: red;">*</span>确认新密码：</label>
                <input type="password"  class="form-control" name="changePwdAgain" id="changePwdAgain" placeholder="请再次输入新密码"/>
            </div>
        </form>
        <div class="absent-con">
            <button class="btn absent-addto manage-cancel " type="button" id="year-confirm" onclick="okChangePwd()">确定</button>
            <button class="btn manage-cancel year-changeP-cancel" type="button" id="year-del" onclick="closeSetPwd()">取消</button>
        </div>
    </div>
</div>
<!-- 修改密码over -->


<!-- loading begin -->
<div class="absent-layer" id="common_loading_div_id" style="display: none;">
	<div class="absent-clickbox table-responsive">
		<img class="yy_loading_img" src="${ctx}/images/loading.jpg" alt=""/>
	</div>
</div>
<!-- loading end -->