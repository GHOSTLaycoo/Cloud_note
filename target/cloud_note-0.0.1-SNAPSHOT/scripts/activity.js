/***
 * 获得活动列表
 */
function getActivityList(){
	$.ajax({
		type:"post",
		url:path+"/activity/findActivity.do",
		dataType:"json",
		data:{},
		success:function(result) {
			if(result.status==0) {
				var list = result.data;
				$(list).each(function(i){
					var color;
					if(i%4==0){
						color='bg-primary';
					}else if(i%4==1){
						color='bg-danger';
					}
					else if(i%4==2){
						color='bg-inverse';
					}else{
						color='bg-warning';
					};
					
					var column=i%3;
					$('#col_'+column).append('<div id="contentfeeds'+i+'" class="panel panel-animated panel-default animated fadeInUp" style="visibility: visible;"><div class="panel-body bordered-bottom"><div class="no-padding jumbotron '+color+'"><p class="lead"><a href="activity_detail.html#'+this.cn_activity_id+'">'+this.cn_activity_title+'</a></p></div><p class="text-muted">'+this.cn_activity_body+'</p><div class="text-muted"><small style="color:red;">活动结束时间:'+this.cn_activity_end_time+'</small></div></div></div>');
					
				});
			} else {
				alert(result.message);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}

/***
 * 查询指定活动下已参加活动的笔记列表
 */
function getNoteActivitys(){
	var param=window.location.hash;
	ActivityId = param.replace(/#/,'');
	page = 1;
	loadActNote(ActivityId,page);	
};

function getMoreActNote(){
	var param=window.location.hash;
	var ActivityId = param.replace(/#/,'');
	page = page+1;
	loadActNote(ActivityId,page);
};


function loadActNote(ActivityId,page){
	$.ajax({
	type:"post",
	url:path+"/activity/findNoteActivity.do",
	dataType:"json",
	data:{"ActivityId":ActivityId,"page":page},
	success:function(result) {
		if(result.status==0) {
			var list = result.data;
			for(var i=0;i<list.length;i++){
				
				  var noteId=list[i].cn_note_id;
				  var title=list[i].cn_note_activity_title;
				  var ActId=list[i].cn_note_activity_id;
				  //获取li对象
				  var sli="";
				  sli+='<li class="online"><a >';
				  sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				  sli+=title;
				  sli+='<button type="button" class="btn btn-default btn-xs btn_position_3 btn_up"><i class="fa fa-thumbs-o-up"></i></button>';
				  sli+='<button type="button" class="btn btn-default btn-xs btn_position_2 btn_down"><i class="fa fa-thumbs-o-down"></i></button>';
				  sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_like"><i class="fa fa-star-o"></i></button>';
				  sli+='</a></li>';
		          var $li=$(sli);
				  $li.data("noteId",noteId);
				  $li.data("ActId",ActId);
				  //将li对象添加到ul当中
				  $("#Act_ul").append($li);
			}
		}
	},
	error:function(xhr,status,error) {
		alert("请求失败.");
	}
});
};

/***
 * 查询活动笔记内容
 */
function getNoteActivityDetail(){
	$("#content_body").empty();
	$("#Act_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	
	var ActId=$(this).data("ActId");
	
	$.ajax({
		type:"post",
		url:path+"/activity/findNoteActivityDetail.do",
		dataType:"json",
		data:{"ActId":ActId},
		success:function(result) {
			if(result.status==0) {
				var Act = result.data;
				
				var sli="";
				sli+='<h4><strong>笔记标题: </strong>';
				sli+=Act.cn_note_activity_title;
				sli+='</h4>';
				sli+=Act.cn_note_activity_body;
				
				var $li=$(sli);
				$("#content_body").append($li);
			} 
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}

/***
 * 查询可选择的笔记本
 */
function getSelectNoteBook(){
	var userId=getCookie("userId");
	
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
					var sli="";
					sli+='<li class="online"><a ><i class="fa fa-book" title="online" rel="tooltip-bottom"></i>';
					sli+=bookName;
					sli+='</a></li>';
					
					var $li = $(sli);
					$li.data("bookId",bookId);
					$("#joinBook_ul").append($li);
				}
			}
		},
		error:function(){
			alert("笔记本加载失败");
		}
	});
}

/***
 * 查询可选择的笔记
 */
function getSelectNoteList(){
	$("#joinNote_ul").empty();
	 //设置选中效果
		  $("#joinBook_ul a").removeClass("checked");
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
					var sli="";
					sli+='<li class="online"><a ><i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli+=noteTitle;
					sli+='</a></li>';
					var $li = $(sli);
					$li.data("noteId",noteId);
					$("#joinNote_ul").append($li);
				  }
			  },
			  error:function(){
				  
			  }
		  });
}


function checkNote() {
	$("#joinNote_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
}
/***
 *	将用户选择的笔记参加活动
 */
function createNoteActivity(){
	var $li=$("#joinNote_ul a.checked").parent();
	var noteId=$li.data("noteId");
	$.ajax({
		type:"post",
		url:path+"/activity/addNoteActivity.do",
		dataType:"json",
		data:{"ActivityId":ActivityId,"noteId":noteId},
		success:function(result) {
			if(result.status==0) {
				var act = result.data;
				var noteId=act.cn_note_id;
				  var title=act.cn_note_activity_title;
				  var ActId=act.cn_note_activity_id;
				  //获取li对象
				  var sli="";
				  sli+='<li class="online"><a >';
				  sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
				  sli+=title;
				  sli+='<button type="button" class="btn btn-default btn-xs btn_position_3 btn_up"><i class="fa fa-thumbs-o-up"></i></button>';
				  sli+='<button type="button" class="btn btn-default btn-xs btn_position_2 btn_down"><i class="fa fa-thumbs-o-down"></i></button>';
				  sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_like"><i class="fa fa-star-o"></i></button>';
				  sli+='</a></li>';
		          var $li=$(sli);
				  $li.data("noteId",noteId);
				  $li.data("ActId",ActId);
				  //将li对象添加到ul当中
				  $("#Act_ul").append($li);
				  alert(result.msg);
			}else if(result.status==1) {
				alert(result.msg);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}

/***
 *	分享活动笔记
 */
function likeActivityNote(noteActivityId, dom) {
	$.ajax({
		type:"post",
		url:basePath+"note/likeActivityNote.do",
		dataType:"json",
		data:{"noteActivityId":noteActivityId},
		success:function(result) {
			if(result.status==0) {
				dom.remove();
			} else {
				alert(result.message);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
	$('#modalBasic_14 .cancle').trigger('click');
}

/***
 *	顶笔记
 */
function up(noteActivityId, dom) {
	$.ajax({
		type:"post",
		url:basePath+"activity/upNoteActivity.do",
		dataType:"json",
		data:{"noteActivityId":noteActivityId},
		success:function(result) {
			if(result.status==0) {
				dom.remove();
			} else {
				alert(result.message);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}

/***
 *	踩笔记
 */
function down(noteActivityId, dom) {
	$.ajax({
		type:"post",
		url:basePath+"activity/downNoteActivity.do",
		dataType:"json",
		data:{"noteActivityId":noteActivityId},
		success:function(result) {
			if(result.status==0) {
				dom.remove();
			} else {
				alert(result.message);
			}
		},
		error:function(xhr,status,error) {
			alert("请求失败.");
		}
	});
}
