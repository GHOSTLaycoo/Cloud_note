package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Note;

public interface RollDao {
	public List<Note> findById(String userId);
	public int updateNoteStatus(Map params);
	public int deleteRollNote(String noteId);
}
