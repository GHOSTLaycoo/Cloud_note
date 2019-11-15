package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Activity;
import cn.tedu.cloud_note.service.ActService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/activity")
public class LoadActivityController {
	
	@Resource
	private ActService actService;
	
	@ResponseBody
	@RequestMapping("/findActivity.do")
	public NoteResult<List<Activity>> execute(){
		System.out.println("º”‘ÿªÓ∂Ø");
		NoteResult<List<Activity>> result = actService.loadAct();
		return result;
	}
}
