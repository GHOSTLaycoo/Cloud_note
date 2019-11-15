package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.dao.RollDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;

@Service("rollService")
public class RollServiceImpl implements RollService{

	@Resource
	private RollDao rollDao;
	
	@Resource
	private BookDao bookDao;
	
	public NoteResult<List<Note>> findByUserId(String userId) {
		NoteResult<List<Note>> result = new NoteResult<List<Note>>();
		List<Note> list = rollDao.findById(userId);
		if(list!=null) {
			result.setStatus(0);
			result.setMsg("加载成功");
			result.setData(list);
		}
		return result;
	}


	public NoteResult<Object> replayNote(String noteId, String bookId) {
		NoteResult<Object> result = new NoteResult<Object>();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("noteId", noteId);
		params.put("bookId", bookId);
		
		Book book = bookDao.findByBookId(bookId);
		
		String bookname = book.getCn_notebook_name();
		int a = rollDao.updateNoteStatus(params);

		if(a==1) {
			result.setStatus(0);
			result.setMsg("已恢复到"+bookname);
		}
		return result;
	}


	public NoteResult<Object> rollBack(String noteId) {
		NoteResult<Object> result = new NoteResult<Object>();
		int a = rollDao.deleteRollNote(noteId);
		if(a==1) {
			result.setStatus(0);
			result.setMsg("删除成功");
		}
		return result;
	}

}
