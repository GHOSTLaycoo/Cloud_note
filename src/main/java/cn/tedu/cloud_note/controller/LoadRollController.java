package cn.tedu.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.RollService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class LoadRollController {
	@Resource
	private RollService rollService;
	@RequestMapping("/roll.do")
	@ResponseBody
	public NoteResult<List<Note>> execute(String userId){
		System.out.println("º”‘ÿªÿ ’’æ");
		NoteResult<List<Note>> result = rollService.findByUserId(userId);
		return result;
		
	}
}
