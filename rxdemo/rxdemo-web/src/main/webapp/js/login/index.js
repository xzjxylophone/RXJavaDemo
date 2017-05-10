//时间显示部分-start
$(function(){

   /* function checkTime(i){
        i=i<10?"0"+i:i;
        return i;
    }*/

	function showtime() {
				var now = new Date();
				var year = now.getFullYear();
				var month = now.getMonth() + 1;
				var day = now.getDate();
				var hours = now.getHours()<10?'0'+now.getHours():now.getHours();
				var minutes = now.getMinutes()<10?'0'+now.getMinutes():now.getMinutes();
				var seconds = now.getSeconds()<10?'0'+now.getSeconds():now.getSeconds();
				time = year + '年' + month + '月' + day + '日' +' '+ hours + ':' + minutes + ':' + seconds;
				var base_block = document.getElementById('base-block');
				//添加判断，有的页面没有时间框，所以导致找不到这个对象，设置innerHTML一直定时报错  midify by wangjing 2017-3-21
				if(base_block){
					base_block.innerHTML = time;
				}
			}

			function letstart() {
				taskId = setInterval(showtime, 500);
			}

			window.onload = function() {
				letstart();
			}

})
//时间显示部分-end

/*TAB选项卡*/
/*$(document).ready(function(){
	$('.manage-tab span').click(function(){
		var manage_index = $('.manage-tab span').index(this);
		$(this).addClass('manage-active').siblings().removeClass('manage-active');
		$('.manage-tent>div').eq(manage_index).show().siblings().hide();
	});
});*/