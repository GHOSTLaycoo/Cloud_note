package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.ActDao;
import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Act;
import cn.tedu.cloud_note.entity.Activity;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("actService")
public class ActServiceImpl implements ActService{
	
	@Resource
	ActDao actDao;
	
	@Resource
	NoteDao noteDao;

	public NoteResult<List<Activity>> loadAct() {
		NoteResult<List<Activity>> result = new NoteResult<List<Activity>>();
		List<Activity> list = actDao.loadActivity();
		if(list!=null) {
			result.setStatus(0);
			result.setMsg("加载成功");
			result.setData(list);
		}
		return result;
	}

	public NoteResult<List<Act>> loadActNote(String ActivityId, int page) {
		NoteResult<List<Act>> result = new NoteResult<List<Act>>();
		Map<String,Object> params = new HashMap<String,Object>();
		int begin=(page-1)*3;
		params.put("ActivityId", ActivityId);
		params.put("begin", begin);
		
		List<Act> list = actDao.loadActNote(params);
		if(list!=null) {
			result.setStatus(0);
			result.setMsg("加载成功");
			result.setData(list);
		}
		return result;
	}

	public NoteResult<Act> showNoteBody(String ActId) {
		NoteResult<Act> result = new NoteResult<Act>();
		Act act = actDao.checkBody(ActId);
		
		if(act!=null) {
			result.setStatus(0);
			result.setMsg("查询成功");
			result.setData(act);
		}
		return result;
	}

	public NoteResult<Act> addActNote(String ActivityId, String noteId) {
		NoteResult<Act> result = new NoteResult<Act>();
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ActivityId", ActivityId);
		params.put("noteId", noteId);
		
		List<Act> list = actDao.checkActNote(params);
		System.out.println(list);
		if(list.size()<=0) {
			String ActId=NoteUtil.createId();
			Act act = new Act();
			Note note = noteDao.findByNoteId(noteId);
			act.setCn_note_activity_id(ActId);
			act.setCn_activity_id(ActivityId);
			act.setCn_note_id(noteId);
			act.setCn_note_activity_up(0);
			act.setCn_note_activity_down(0);
			act.setCn_note_activity_title(note.getCn_note_title());
			act.setCn_note_activity_body(note.getCn_note_body());
			
			int a = actDao.addActNote(act);
			if(a==1) {
				noteDao.updateType(noteId);
				result.setStatus(0);
				result.setMsg("参加成功");
				result.setData(act);
			}
		}else {
			result.setStatus(1);
			result.setMsg("该笔记已参加");
		}
		return result;
	}

}
