package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/share")
public class ShareNoteController {
	@Resource
	private ShareService shareService;
	
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Object> execute(String noteId){
		System.out.println("���������ʼ�");
		NoteResult<Object> result=shareService.shareNote(noteId);
		return result;
		
	}
}
