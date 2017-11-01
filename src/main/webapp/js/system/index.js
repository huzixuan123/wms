/**静态菜单**/
var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback:{
				onClick:function(event,treeId,treeNode){
					if(treeNode.action){
						$("#rightMain").prop("src",treeNode.action+".do");
						$("#here_area").html("当前位置："+treeNode.getParentNode().name+"&nbsp;>&nbsp;"+treeNode.name);
					}
				}
			},
			async: {
				enable: true,
				url:"/systemMenu/queryMenuByParentSn.do",
				autoParam:["sn=parentSn"],
			}
		};

		var zNode1 =[
			{id:1,pId:0,name:"业务模块",isParent:true,sn:"business"}
		];
		var zNode2 =[
		 			{id:2,pId:0,name:"系统模块",isParent:true,sn:"system"}
		 		];
		var zNode3 =[
		 			{id:3,pId:0,name:"报表模块",isParent:true,sn:"chart"}
		 		];
		var zNodes = {
				business:zNode1,
				system:zNode2,
				charts:zNode3
		};
		//加载菜单树
		var loadMenus = function(sn){
			//渲染菜单
			$.fn.zTree.init($("#dleft_tab1"), setting,zNodes[sn]);
		}
$(function(){
	loadMenus("business");
});
//-----------------------------------------------------
/** 切换面板**/
$(function(){
	$("#TabPage2 li").click(function(){
		
		//删除原来被选中的样式
		$("#TabPage2 li").removeClass("selected");
		//删除被选中的图片
		$.each($("#TabPage2 li"),function(index,item){
			$(item).children("img").prop("src","images/common/"+(index+1)+".jpg");
		});
		
		//给当前点击的li元素添加被选中的样式
		$(this).addClass("selected");
		//修改被选中的图片
		$(this).children("img").prop("src","images/common/"+($(this).index()+1)+"_hover.jpg");
		$("#nav_module").children("img").prop("src","/images/common/module_"+($(this).index()+1)+".png");
		//加载控制面板的静态菜单
		loadMenus($(this).data("rootmenu"));
	});
});

//------------------------------------------------------
//加载当前日期
function loadDate(){
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 **/
function switchSysBar(flag){
	var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
	if( flag==true ){	// flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({width:'280px'});
		$('#top_nav').css({width:'77%', left:'304px'});
    	$('#main').css({left:'280px'});
	}else{
        if ( left_menu_cnt.is(":visible") ) {
			left_menu_cnt.hide(10, 'linear');
			side.css({width:'60px'});
        	$('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
        	$('#main').css({left:'60px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_show.png');
        } else {
			left_menu_cnt.show(500, 'linear');
			side.css({width:'280px'});
			$('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
        	$('#main').css({left:'280px'});
        	$("#show_hide_btn").find('img').attr('src', 'images/common/nav_hide.png');
        }
	}
}

$(function(){
	loadDate();
	
	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
        switchSysBar();
    });
});