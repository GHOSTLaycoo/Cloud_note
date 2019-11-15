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
public class AddNoteActController {
	@Resource
	ActService actservice;
	@RequestMapping("/addNoteActivity.do")
	@ResponseBody
	public NoteResult<Act> execute(String ActivityId, String noteId){
		System.out.println("Ìí¼Ó±Ê¼Ç±¾");
		NoteResult<Act> result = actservice.addActNote(ActivityId, noteId);
		return result;
		
	}
}
