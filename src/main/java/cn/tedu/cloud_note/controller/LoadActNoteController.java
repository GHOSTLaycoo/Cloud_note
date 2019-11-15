package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Act;
import cn.tedu.cloud_note.service.ActService;
import cn.tedu.cloud_note.util.NoteResult;


@Controller
@RequestMapping("/activity")
public class LoadActNoteController {
	@Resource
	private ActService actService;
	
	@ResponseBody
	@RequestMapping("/findNoteActivity.do")
	public NoteResult<List<Act>> execute(String ActivityId,int page){
		System.out.println("加载活动笔记");
		NoteResult<List<Act>> result = actService.loadActNote(ActivityId, page);
		return result;
	}
}
