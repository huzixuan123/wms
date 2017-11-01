//禁用数组的序列化
jQuery.ajaxSettings.traditional = true

/** table鼠标悬停换色* */
$(function() {
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({background : "#CDDAEB"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#1D1E21"});
		});
	}).mouseout(function() {
		$(this).css({background : "#FFF"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#909090"});
		});
	});
});

$(function(){
	//删除操作
	$(".btn_delete").click(function(){
		var deleteUrl = $(this).data("url");
		$.dialog({
			title:"温馨提示",
			content:"确定要删除吗?",
			icon:"face-smile",
			cancel:true,
			ok:function(){
				var dg = $.dialog({
					title:"温馨提示",
					icon:"face-smile"
				});
				$.post(deleteUrl,function(data){
					if(data.success){
						dg.content("删除成功").button({
							name:"确定",
							callback:function(){
								window.location.reload();
							}
						});
					}
				});
			}
		});
	});
});

