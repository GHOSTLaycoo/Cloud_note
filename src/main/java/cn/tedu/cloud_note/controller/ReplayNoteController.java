package cn.tedu.cloud_note.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.RollService;
import cn.tedu.cloud_note.util.NoteResult;


@Controller
@RequestMapping("/roll")
public class ReplayNoteController {
	@Resource
	private RollService rollService;
	
	@RequestMapping("/replay.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId,String bookId){
		System.out.println("»Ö¸´±Ê¼Ç");
		NoteResult<Object> result = rollService.replayNote(noteId, bookId);
		return result;
		
	}
}
