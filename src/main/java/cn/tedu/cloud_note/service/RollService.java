package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;

public interface RollService {
	public NoteResult<List<Note>> findByUserId(String userId);
	public NoteResult<Object> replayNote(String noteId,String bookId);
	public NoteResult<Object> rollBack(String noteId);
}
