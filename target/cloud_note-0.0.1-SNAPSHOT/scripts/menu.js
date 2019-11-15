

//移动笔记
function moveNote(){
	var $li=$("#moveSelect option:selected");
	var bookId = $li.data("bookId");
	var $li=$("#note_ul a.checked").parent();
	//获取笔记Id
	var noteId=$li.data("noteId");
	$.ajax({
		  url:path+"/note/move.do",
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
			  alert("移动失败");
		  }
	  });
	
	
};

//删除笔记
function deleteNoteAction(){
	       //获取参数
		  var $li=$("#note_ul a.checked").parent();
		  //获取笔记Id
		  var noteId=$li.data("noteId");
		  //ajax请求
		  alert("dahdah");
		  $.ajax({
			  url:path+"/note/delete.do",
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
				  alert("删除失败");
			  }
		  });
		  
};

//分享笔记
function shareNote(){
   	  		 //获取请求参数
   	  		 $li=$(this).parents("li");
   	  		 var noteId=$li.data("noteId");
   	  		 //发送ajax请求
   	  		 $.ajax({
   	  			url:path+"/share/add.do",
   	  			type:"post",
   	  			data:{"noteId":noteId},
   	  			dataType:"json",
   	  			success:function(result){
   	  				var noteTitle=$li.text();
   	  				var sli="";
   	  			    sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli+=noteTitle;
					sli+='<i class="fa fa-sitemap"></i>';
					sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					//将笔记li元素的<a>标记内容替换
					$li.find("a").html(sli);
					alert("笔记分享成功!")
   	  			},
   	  			error:function(){
   	  				alert("分享笔记失败!");
   	  			}
   	  		 });
   	  	  };
//隐藏菜单
   	  	function hidemenu(){
  	  		 //隐藏笔记菜单
  	  		 $("#note_ul div").hide();
  	  		  //显示点击菜单
  	  		 var note_menu = $(this).parents("li").find("div");
  	  		 note_menu.slideDown(1000);
  	  		 return false;
  	  	  };
  	  	function hideBody(){
   	  	 	$("#note_ul div").hide();	
   	  	  };
  	  	  
  	  	function hidemenu1(){
 	  		 //隐藏笔记菜单
 	  		 $("#book_ul div").hide();
 	  		  //显示点击菜单
 	  		 var note_menu = $(this).parents("li").find("div");
 	  		 note_menu.slideDown(1000);
 	  		 return false;
 	  	  };
  	  	  
  	  	function hideBody1(){
   	  	 	$("#book_ul div").hide();	
   	  	  };