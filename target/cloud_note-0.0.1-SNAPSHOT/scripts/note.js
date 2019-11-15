
//创建笔记
function sure_addNote(){
   	  		  var title=$("#input_note").val().trim();
   	  		  var userId=getCookie("userId");
   	  		  var $li=$("#book_ul a.checked").parent();
   	  		  var bookId=$li.data("bookId");
   	  		var ok=true;
			if(userId==null){
				ok=false;
				window.location.href="log_in.html";
			}
			if(title==""){
				ok=false;
				$("#name_span").html("笔记名不能为空");
			}
			if(ok){
				$.ajax({
					url:path+"/note/add.do",
					type:"post",
					data:{"userId":userId,"bookId":bookId,"title":title},
					dataType:"json",
					success:function(result){
						var note=result.data;
						if(result.status==0){
							var id=note.cn_note_id;
							var title=note.cn_note_title;
							createNoteLi(id,title);
							alert(result.msg);
						}
					},
					error:function(){
						alert("创建笔记失败!");
					}
				});
			}
			
   	  	  };



//更新笔记信息
function updateNote(){
   			  //获取参数
   			  var $li=$("#note_ul a.checked").parent();
   			  //获取笔记Id
   			  var noteId=$li.data("noteId");
   			  //获取笔记的标题和内容
   			  var noteTitle=$("#input_note_title").val().trim();
   			  var noteBody=um.getContent();
   			  
   			 // alert(noteId+","+noteTitle+","+noteBody);
   			 //发送ajax请求
   			 $.ajax({
   				 url:path+"/note/update.do",
   				 type:"post",
   				 data:{"noteId":noteId,"title":noteTitle,"body":noteBody},
   				 dataType:"json",
   				 success:function(result){
   					 if(result.status==0){
   						 var str="";
   						str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
   						str+=noteTitle;
   						str+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
   						//将str替换到li的a元素里
   						$li.find("a").html(str);
   						//提示成功
   						alert(result.msg);
   					 }
   				 },
   				 error:function(){
   					 alert("保存笔记失败");
   				 }
   			 });
   		  };


//显示笔记信息
function loadnote(){
   			  //设置选中效果
   			  $("#note_ul a").removeClass("checked");
   			  $(this).find("a").addClass("checked");
   			  //获取请求参数
   			  var noteId=$(this).data("noteId");
   			  //alert(noteId);
   			  //发送ajax请求
   			  $.ajax({
   				  url:path+"/note/load.do",
   				  type:"post",
   				  data:{"noteId":noteId},
   				  dataType:"json",
   				  success:function(result){
   					  if(result.status==0){
   					  //获取返回的笔记标题
   					  var title=result.data.cn_note_title;
   				  	  //获取返回笔记内容
   				  	  var body=result.data.cn_note_body;
   				  	  //设置笔记标题
   				  	  $("#input_note_title").val(title);
   				  	  //设置笔记内容
   				  	  um.setContent(body);
   					  }
   				  },
   			  	  error:function(){
   			  		  alert("加载笔记信息失败");
   			  	  }
   			  });
   			
   			  
   		  };
   		 




//加载笔记本笔记
function loadBookNotes(){
   			  //设置选中效果
   			  $("#book_ul a").removeClass("checked");
   			  $(this).find("a").addClass("checked");
   			  //获取参数 
   			  var bookId=$(this).data("bookId");
   			  //alert(bookId);
   			  //发送ajax请求
   			  $.ajax({
   				  url:path+"/note/loadnotes.do",
   				  type:"post",
   				  data:{"bookId":bookId},
   				  dataType:"json",
   				  success:function(result){
   					  //获取笔记信息
   					  var notes=result.data;
   					  //清除原列表信息
   					  $("#note_ul").empty();
   					  //$("#note_ul li").remove();
   					  //循环添加li
   					  for(var i=0;i<notes.length;i++){
   						//获取笔记ID
   						var noteId=notes[i].cn_note_id;
   						//获取笔记标题
   						var noteTitle=notes[i].cn_note_title;
   						//生成笔记li
   						createNoteLi(noteId,noteTitle);
   					  }
   				  },
   				  error:function(){
   					  
   				  }
   			  });
   			//切换显示区
   			  $("#pc_part_4").hide();
			  $("#pc_part_6").hide();
			  $("#pc_part_2").show();
   		  };
   		  

function createNoteLi(noteId,noteTitle){
	var sli="";
	sli+='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
	sli+=noteTitle;
	sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli+='</a>';
	sli+='<div class="note_menu" tabindex="-1">';
	sli+='<dl>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
	sli+='</dl>';
	sli+='</div>';
	sli+='</li>';
	//转换成jquery对象
	var $li=$(sli);
	//保持noteId
	$li.data("noteId",noteId);
	//将li追加到ul中
	$("#note_ul").append($li);
};