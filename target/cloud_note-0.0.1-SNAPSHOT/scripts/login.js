//清除注册数据
function clear(){
			$("#warning_1 span").html("");
	    	$("#warning_2 span").html("");	
	    	$("#warning_3 span").html("");
			$("#regist_username").val("");
			$("#nickname").val("");
			$("#regist_password").val("");
			$("#final_password").val("");
		};


//注册
function userregist(){
	    	//测试单击事件的绑定
	    	//alert("dawdawaw");
	    	//获取参数
	    	var name=$("#regist_username").val().trim();
	    	var nick=$("#nickname").val().trim();
	    	var password=$("#regist_password").val().trim();
	    	var final_password=$("#final_password").val().trim();
	    	//alert(name+","+nick+","+password+","+final_password);
	    	//检查数据格式
	    	//检查用户数据
	    	$("#warning_1 span").html("");
	    	$("#warning_2 span").html("");	
	    	$("#warning_3 span").html("");
	    	
	    	var ok=true;
	    	if(name==""){
	    		
	    		$("#warning_1 span").html("用户不能为空");
	    		$("#warning_1").show();
	    		ok=false;
	    	}
	    	//检测密码:1-非空 2-不能小于6位
	    	if(password==""){
	    		
	    		$("#warning_2 span").html("密码不能为空");
	    		$("#warning_2").show();
	    		ok=false;
	    	}else if(password.length>0&&password.length<6){
	    		
	    		$("#warning_2 span").html("密码不能小于6位");
	    		$("#warning_2").show();
	    		ok=false;
	    	}
	    	//检测确认密码:1-非空 2-是否与密码一致
	    	if(final_password!=password){
	    		
	    		$("#warning_3 span").html("输入密码不一致");
	    		$("#warning_3").show();
	    		ok=false;
	    	}
	    	
	    	if(ok){  //数据校验通过
	    		$.ajax({
	    			url:path+"/user/add.do",
	    			type:"post",
	    			data:{"name":name,"nick":nick,"password":password},
	    			dataType:"json",
	    			success:function(result){
	    				//注册成功
	    				if(result.status==0){
	    					alert(result.msg);
	    					//返回到登陆页面
		    				$("#back").click();
	    				}else if(result.status==1){
	    					//用户名被占用
	    					//alert(result.msg);
	    					$("#warning_1 span").html(result.msg);
	    					$("#warning_1").show();
	    				}
	    				
	    			},
	    			error:function(){
	    				alert("注册失败");
	    			}
	    		});
	    	}
	    	
	    };


//登陆
function userLogin(){
			//alert("堵哪了？");
			//获取参数
			var name = $("#count").val().trim();
			var password=$("#password").val().trim();
			
			addCookie("password",password,2);
			//alert(name+","+password);
			//格式检测
			$("#count_span").html("");
			$("#password_span").html("");
			var ok=true;
			if(name==""){
				$("#count_span").html("用户不能为空");
				ok=false;
			}
			if(password==""){
				$("#password_span").html("密码不能为空");
				ok=false;
			}
			
			if(ok){//测试格式通过
				//发送ajax请求
				$.ajax({
					url:path+"/user/login.do",
					type:"post",
					data:{"name":name,"password":password},
					dataType:"json",
					success:function(result){
						//result是服务器返回的JSON结果
						if(result.status==0){
							//将用户信息保存到Cookie
							var userId=result.data.cn_user_id;
							addCookie("userId",userId,2);
							
							window.location.href="edit.html";
						}else if(result.status==1){ //用户名错误
							$("#count_span").html(result.msg);
						}else if(result.status==2){
							$("#password_span").html(result.msg);
						}
					},
					error:function(){
						alert("登陆失败!");
					}
				});
			}
		};