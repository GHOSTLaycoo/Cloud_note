package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Act;
import cn.tedu.cloud_note.entity.Activity;

public interface ActDao {
	public List<Activity> loadActivity();
	public List<Act> loadActNote(Map params);
	public Act checkBody(String ActId);
	public int addActNote(Act act);
	public List<Act> checkActNote(Map params);
}
