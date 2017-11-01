$(function(){
	//左右移动
	$("#selectAll").click(function(){
		$(".all_permissions option").appendTo($(".selected_permissions"));
	});
	$("#deselectAll").click(function(){
		$(".selected_permissions option").appendTo($(".all_permissions"));
	});
	//被选中的左右移动
	$("#select").click(function(){
		$(".all_permissions option:selected").appendTo($(".selected_permissions"));
	});
	$("#deselect").click(function(){
		$(".selected_permissions option:selected").appendTo($(".all_permissions"));
	});
	
	//删除编辑时右边下拉框已经存在的数据
	var ids = [];
	//遍历右边默认选中的权限
	$.each($(".selected_permissions option"),function(index,item){
		ids[index]=item.value;
	});
	//遍历所有权限的选项
	$.each($(".all_permissions option"),function(index,item){
		//判断该权限的Id是否在该数组中
		if($.inArray(item.value,ids)>=0){
			$(item).remove();
		}
	});
	
	//在提交表单之前选中右边所有权限
	$("#editForm").submit(function(){
		$(".selected_permissions option").prop("selected",true);
	});
})
$(function(){
	//左右移动
	$("#mselectAll").click(function(){
		$(".all_menus option").appendTo($(".selected_menus"));
	});
	$("#mdeselectAll").click(function(){
		$(".selected_menus option").appendTo($(".all_menus"));
	});
	//被选中的左右移动
	$("#mselect").click(function(){
		$(".all_menus option:selected").appendTo($(".selected_menus"));
	});
	$("#mdeselect").click(function(){
		$(".selected_menus option:selected").appendTo($(".all_menus"));
	});
	
	//删除编辑时右边下拉框已经存在的数据
	var ids = [];
	//遍历右边默认选中的权限
	$.each($(".selected_menus option"),function(index,item){
		ids[index]=item.value;
	});
	//遍历所有权限的选项
	$.each($(".all_menus option"),function(index,item){
		//判断该权限的Id是否在该数组中
		if($.inArray(item.value,ids)>=0){
			$(item).remove();
		}
	});
	
	//在提交表单之前选中右边所有权限
	$("#editForm").submit(function(){
		$(".selected_menus option").prop("selected",true);
	});
})