package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Note;

public interface NoteDao {
	public List<Map> findByBookId(String bookId);
	public Note findByNoteId(String noteId);
	public int updateNote(Note note);
	public void save(Note note);
	public int updateStatus(String noteId);
	public int moveNote(Map params);
	public int updateType(String noteId);
	
	//测试数据库中if用法
	public int updateNoteByMap(Map<String,Object> map);
	//测试数据库中for循环用法
	public int deleteNotes(Map<String,Object> map);
	//测试aop异常回滚
	public int deleteNote(String id);
}
