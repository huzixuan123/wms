$(function(){
	$("#editForm").validate({
		rules:{
			name:{
				rangelength:[5,10]
			},
			password:{
				required:true,
				minlength:4
			},
			repassword:{
				equalTo:"#password"
			},
			email:{
				required:true,
				email:true
			},
			age:{
				required:true,
				range:[18,70]
			}
		},
		messages:{
			name:{
				rangelength:"账户名必须是4-16位"
			},
			password:{
				required:"密码不能为空",
				minlength:"密码长度至少为4位"
			},
			repassword:{
				equalTo:"两次输入密码不一致"
			},
			email:{
				required:"邮箱不能为空",
				email:"邮箱格式不正确"
			},
			age:{
				required:"年龄不能为空",
				range:"年龄为18-70之间"
			}
		}
	})
})