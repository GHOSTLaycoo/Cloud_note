package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Act;
import cn.tedu.cloud_note.service.ActService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/activity")
public class LoadActNotesController {
	
	@Resource
	private ActService actService;
	
	@RequestMapping("/findNoteActivityDetail.do")
	@ResponseBody
	public NoteResult<Act> execute(String ActId){
		System.out.println("加载活动笔记内容");
		NoteResult<Act> result = actService.showNoteBody(ActId);
		return result;
		
	}

}
