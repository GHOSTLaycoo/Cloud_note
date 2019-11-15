//显示参加活动选项
function alertJoinWindow(){
	$("#can").load("alert/alert_join_activity.html");	
	$(".opacity_bg").show();
}

//删除边框
function deleteWindow(){
	$("#can").load("alert/alert_delete_rollback.html");
	$(".opacity_bg").show();
};

//显示恢复框
function alertReplayWindow(){	
	$("#can").load("alert/alert_replay.html");
	$(".opacity_bg").show();
};

//显示move框
function alertMoveWindow(){
	$("#can").load("alert/alert_move.html");
	$(".opacity_bg").show();
};

//删除笔记本
function alertDeleteNoteBookWindow(){
	$("#can").load("alert/alert_delete_notebook.html");
	$(".opacity_bg").show();
};


//显示更名
function renameWindow(){
	$("#can").load("alert/alert_rename.html");
	$(".opacity_bg").show();
}

//删除框显示
function alertDeleteNoteWindow(){
	$("#can").load("alert/alert_delete_note.html");
	$(".opacity_bg").show();
};

//弹出新建笔记本对话框
function alertAddBookWindow(){
   			  //弹出新建笔记本对话框
   			  $("#can").load("alert/alert_notebook.html");
   			  //显示背景
   		  	  $(".opacity_bg").show();
};

//弹出笔记对话框
function alertAddNoteWindow(){
	//判断是否有笔记本被选中
	var $li=$("#book_ul a.checked").parent();
	if($li.length==0){
		alert("请选择笔记本");
	}else{
		$("#can").load("alert/alert_note.html");
		$(".opacity_bg").show();
	}
	
	
};

//关闭对话框
function closeAlertWindow(){
	//清空div内容
	$("#can").html("");
	//隐藏背景色
	$(".opacity_bg").hide();
};