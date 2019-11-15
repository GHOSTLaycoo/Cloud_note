

//删除笔记本
function deleteBookNoteAction(){
	var $li=$("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	
	$.ajax({
		url:path+"/book/delete.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result){
				if(result.status==0){
					$li.remove();
					alert(result.msg);
				}
				
		
		},
		error:function(){
			alert("修改笔记本失败");
		}	
	});
};

//双击修改名字
function Bookrename(){
	//获取参数
	var $li=$("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	var rename = $("#input_notebook_rename").val().trim();
	
	$.ajax({
		url:path+"/book/rename.do",
		type:"post",
		data:{"bookId":bookId,"rename":rename},
		dataType:"json",
		success:function(result){
				if(result.status==0){
				$li.remove();
				createBookLi(bookId,rename);
				alert(result.msg);
				}
		},
		error:function(){
			alert("修改笔记本失败");
		}	
	});
	
};

//创建笔记本
function addBookAction(){
				
					//获取参数
					var userId=getCookie("userId");
					var bookName=$("#input_notebook").val() || '';
					//alert(userId);
					//校验参数格式
					var ok=true;
					if(userId==null){
						ok=false;
						window.location.href="log_in.html";
					}
					if(bookName==""){
						ok=false;
						$("#name_span").html("笔记本名不能为空");
					}
					//发送请求
					if(ok){
						$.ajax({
							url:path+"/book/add.do",
							type:"post",
							data:{"userId":userId,"bookName":bookName},
							dataType:"json",
							success:function(result){
								var book = result.data;
									if(result.status==0)
									
									var id=book.cn_notebook_id;
									var name=book.cn_notebook_name; 
									//刷新笔记本列表
									createBookLi(id,name);
									//提示消息
									alert(result.msg);
							
							},
							error:function(){
								alert("创建笔记本失败");
							}	
						});
					}
				};



//根据用户id显示笔记本列表
function loadUserBooks(){
	//获取userId
	var userId=getCookie("userId");
	//alert(userId);
	//判断是否取到有效userId
	if(userId==null){
		window.location.href("log_in.html");
	}else{  //发送ajax请求
		$.ajax({
			url:path+"/book/loadbooks.do",
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				//判断查询是否成功
				if(result.status==0){
					//获取笔记本集合
					var books=result.data;
					for(var i=0;i<books.length;i++){
						//获取笔记本ID
						var bookId=books[i].cn_notebook_id;
						//获取笔记本名称
						var bookName=books[i].cn_notebook_name;
						//创建一个笔记本列表的li元素
						createBookLi(bookId,bookName);
					}
				}
			},
			error:function(){
				alert("笔记本加载失败");
			}
		});
	}
	
	
};

function createBookLi(bookId,bookName){
	    var sli="";
		sli+='<li class="online">';
		sli+='<a>';
		sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
		sli+='</i>' ;
		sli+=bookName;
		sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
		sli+='</a>'
		sli+='<div class="note_menu" tabindex="-1">';
		sli+='<dl>';
		sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
		sli+='</dl>';
		sli+='</div>';
		sli+='</li>';
	//将sli字符串转成jQuery对象li元素
	var $li=$(sli); 
	//将bookId绑定到元素中
	$li.data("bookId",bookId);
	//将li元素添加到笔记本列表book_ul
	$("#book_ul").append($li);
};

