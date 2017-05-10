//用户登录 
function login(){
    //先进行表单验证
    var flag = $.html5Validate.isAllpass($("#loginForm"));
    if(!flag){
        return;
    }else {
        //让提交按钮失效，以实现防止按钮重复点击
        $("#loginBtn").attr('disabled', 'disabled');
        $.ajax({
            type : 'POST',
            url :  yyoa_context +'/login.do',
            data :{
                userName :  $("#userName").val(),
                pwd : $("#pwd").val()
            },
            dataType : 'json',
            success : function(data) {
                if(data.state){
                    window.location.href = yyoa_context+'/homePage.do';
                }else {
                    Modal.alert({msg:data.message});
                }
                //让登陆按钮重新有效
                $("#loginBtn").removeAttr('disabled');
            }
        });
	}
 }


//初始化菜单列表
function initeMenu(data){
	//装载页面
	var menu; //外层循环变量
	var menuVo; //内层循环变量
	var menuDivStr = "<ul class='index_active_ul'>";
	
	for (var i = 0; i < data.length; i++) {
		menu = data[i];
		var pflag = false;
		var sflag = false;
		for (var j = 0; j < data.length; j++) {
		     menuVo = data[j];
		     flag = true;
		     //判断该节点是否有父节点，如果有父节点则对该节点设置为子节点样式
		     if(menu.pId == menuVo.id){
		    	 pflag = true;
		    	 flag = false;
		     }; 
		     //判断该节点是否有子节点，如果有子节点则对该节点设置为父节点样式
		     if(menu.id == menuVo.pId){
		    	 sflag = true;
		    	 flag = false;
		     }; 
		}
		
		//根据url是否为空 设置菜单样式,strUrlIsEmpty
		//strUrlIsEmpty = "<li><a href=\"javascript:;\" class=\"index_active_ul_ative\"><i class=\"active_i1\"></i>" + menu.name + "<s class=\"fr active_s1\"></s></a><ul>";//待修改,二级菜单样式
		strUrlIsEmpty = "<li><a href=\"javascript:;\" class=\"index_active_ul_ative\" hidefocus=\"hidefocus\" onclick=\"navChange(this);\"><i class=\"active_i1\"></i>" + menu.name + "<s class=\"fr active_s1\"></s></a>";



		if (sflag && !pflag) {//有子无父(一级菜单有子菜单)
				menuDivStr += strUrlIsEmpty;
		} else if (!sflag && pflag ) {//有父无子(二级菜单)
			//menuDivStr += "<li><a href=\"javascript:void(0)\" onclick=\"openPage('"+ menu.url +"');\"><i></i>" + menu.name + "</a></li></ul></li>";//待修改,二级菜单样式
			menuDivStr += "<li><a href=\"javascript:void(0)\" class=\"HideFocus\" onclick=\"openPage('"+ menu.url +"',this)\"><i></i>" + menu.name + "</a></li>";
		} else {//无父无子(一级菜单无子菜单)
			menuDivStr += strUrlIsEmpty;
			menuDivStr += "</li>";
		} 
	}
	menuDivStr += "</ul>";
	var menuDiv = $("#menuDivId");
	menuDiv.append(menuDivStr);
    //初始化导航展开样式
    $('[href="javascript:void(0)"]').parent().css("display","none");
    $('[href="javascript:;"]').parent().css("border-bottom","1px solid #fff");
    navChange($(".index_active_ul_ative")[0]);
    $(".HideFocus")[0].click();
}

function  navChange(el) {
		var elp=$(el).eq(0).parent();
        var lis=elp.siblings();
        var length=lis.length;
        var index=elp.index();

   		$(el).css({
            "hide-focus": "expression(this.hideFocus=true)",
            "outline-style": "none"
		});
        for(var i=index;i<length;i++){
            if($(lis[i].children[0]).attr('href')=="javascript:void(0)"){
                $(lis[i]).toggle();
            }else{
                break;
            }
        }

        var $last=$(el).children().last();
		if(elp.siblings().eq(index+1).css('display')=='none'){

            $last.css({
				"background": "url(images/s25.png)",
				"display": "inline-block",
				"width": "7px",
				"height": "12px",
				"background-size": "7px 12px",
				"margin-right": "20px",
				"margin-top": "10px"
			})
		}else{
            $last.css({
				"background": "url(images/s10.png)",
				"display": "inline-block",
				"width": "12px",
				"height": "7px",
				"background-size": "12px 7px",
				"margin-right": "20px",
				"margin-top": "15px"
			})
		}
}




