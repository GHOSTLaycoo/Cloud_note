//点击关闭返回上一级
function back(){
	     window.location.href=document.referrer;
	};

//修改密码
function modifypassword(){
		
		$("#warning_1 span").html("");
    	$("#warning_2 span").html("");	
    	$("#warning_3 span").html("");
		//用户id和原密码
		var userId=getCookie("userId");
		var oldpassword=getCookie("password");
		//alert(userId+","+oldpassword);
		//获取新旧密码参数
		var lastpassword=$("#last_password").val().trim();
		var newpassword=$("#new_password").val().trim();
		var finalpassword=$("#final_password").val().trim();
	
		
		var ok=true;
		if(lastpassword==""){
			$("#warning_1 span").html("用户不能为空");
    		$("#warning_1").show();
    		ok=false;
		}else if(lastpassword!=oldpassword){
			$("#warning_1 span").html("原始密码错误");
			$("#warning_1").show();
			ok=false;
		}
		
		if(newpassword==""){
    		
    		$("#warning_2 span").html("密码不能为空");
    		$("#warning_2").show();
    		ok=false;
    	}
		if(newpassword.length>0&&newpassword.length<6){
			$("#warning_2 span").html("新密码长度过短");
    		$("#warning_2").show();
    		ok=false;
    	}
		
		if(finalpassword!=newpassword){
    		
    		$("#warning_3 span").html("输入密码不一致");
    		$("#warning_3").show();
    		ok=false;
    	}
		
		if(ok){  //数据校验通过
    		$.ajax({
    			url:path+"/password/modify.do",
    			type:"post",
    			data:{"userId":userId,"password":newpassword},
    			dataType:"json",
    			success:function(result){
    				if(result.status==0){
    					alert(result.msg);
    					window.location.href="log_in.html";
    				}
    			},
    			error:function(){
    				alert("修改失败");
    			}
    		});
    		}
	};