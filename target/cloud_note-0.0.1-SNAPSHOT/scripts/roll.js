//选中效果
function check(){
	$("#roll_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
};

//删除笔记
function deleteNote(){
	var $li=$("#roll_ul a.checked").parent();
	var noteId=$li.data("noteId");
	
	$.ajax({
		  url:path+"/roll/rollback.do",
		  type:"post",
		  data:{"noteId":noteId},
		  dataType:"json",
		  success:function(result){
			  if(result.status==0){
				  $li.remove();
				  alert(result.msg);
			  }
		  },
		  error:function(){
			  alert("恢复失败");
		  }
	  });
};


//恢复笔记
function replayDeleteNote(){
	var $li=$("#replaySelect option:selected");
	var bookId = $li.data("bookId");
	var $li=$("#roll_ul a.checked").parent();
	var noteId=$li.data("noteId");
	
	$.ajax({
		  url:path+"/roll/replay.do",
		  type:"post",
		  data:{"noteId":noteId,"bookId":bookId},
		  dataType:"json",
		  success:function(result){
			  if(result.status==0){
				  $li.remove();
				  alert(result.msg);
			  }
		  },
		  error:function(){
			  alert("恢复失败");
		  }
	  });
	
};

//加载回收站
function rollback(){
	var userId=getCookie("userId");
	 $("#roll_ul li").remove();
	$.ajax({
		  url:path+"/note/roll.do",
		  type:"post",
		  data:{"userId":userId},
		  dataType:"json",
		  success:function(result){
			  if(result.status==0){
				//获取笔记本集合
					var rolls=result.data;
					for(var i=0;i<rolls.length;i++){
						//获取笔记本ID
						var noteId=rolls[i].cn_note_id;
						//获取笔记本名称
						var title=rolls[i].cn_note_title;
						//创建一个笔记本列表的li元素
						  var sli="";
						  sli+='<li class="disable">';
						  sli+='<a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
						  sli+=title;
						  sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_delete">';
						  sli+='<i class="fa fa-times"></i></button>';
						  sli+='<button type="button" class="btn btn-default btn-xs btn_position_2 btn_replay"><i class="fa fa-reply"></i>';
						  sli+='</button></a></li>';
						  
						  var $li=$(sli);
						  //绑定shareId
						  $li.data("noteId",noteId);
						  //将li对象添加到ul当中
						  $("#roll_ul").append($li);
						  //切换显示区
						  $("#pc_part_2").hide();
						  $("#pc_part_6").hide();
						  $("#pc_part_4").show();
					}
				  
			  }
		  },
		  error:function(){
			  alert("加载失败");
		  }
	  });
};