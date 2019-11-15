package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.Act;
import cn.tedu.cloud_note.entity.Activity;
import cn.tedu.cloud_note.util.NoteResult;

public interface ActService {
	public NoteResult<List<Activity>> loadAct();
	public NoteResult<List<Act>> loadActNote(String ActivityId,int page);
	public NoteResult<Act> showNoteBody(String ActId);
	public NoteResult<Act> addActNote(String ActivityId,String noteId);
}
