package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("noteService")
public class NoteServiceImpl implements NoteService{

	@Resource
	private NoteDao noteDao;
	public NoteResult<List<Map>> loadBookNotes(String bookId) {
		//�������ݼ���
		List<Map> list= noteDao.findByBookId(bookId);
		//����Result
		NoteResult<List<Map>> result = new NoteResult<List<Map>>();
		result.setStatus(0);
		result.setMsg("���رʼǳɹ�");
		result.setData(list);
		return result;
	}
	
	public NoteResult<Note> loadNote(String noteId) {
		Note note = noteDao.findByNoteId(noteId);
		NoteResult<Note> result = new NoteResult<Note>();
		if(note==null) {
			result.setStatus(1);
			result.setMsg("δ�ҵ�����");
			return result;
		}else {
			result.setStatus(0);
			result.setMsg("��ѯ�ɹ�");
			result.setData(note);
			return result;
		}
		
	}
	public NoteResult<Object> updateNote(String noteId, String title, String body) {
		//����note����
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_body(body);
		Long time=System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		//�������ݿ��¼
		int rows=noteDao.updateNote(note);
		//����result
		NoteResult<Object> result=new NoteResult<Object>();
		if(rows==1) {
			result.setStatus(0);
			result.setMsg("����ʼǳɹ�");
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("����ʼ�ʧ��");
			return result;
		}
		
	}
	public NoteResult<Note> addNote(String userId, String bookId, String title) {
		Note note = new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(title);
		String noteId=NoteUtil.createId();
		note.setCn_note_id(noteId);
		note.setCn_note_body("");
		Long time = System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_last_modify_time(time);
		//״̬:1-normal 2-delete
		note.setCn_note_status_id("1");
		//����:1-normal 2-favor 3-share
		note.setCn_note_type_id("1");
		
		noteDao.save(note);
		
		NoteResult<Note> result=new NoteResult<Note>();
		result.setStatus(0);
		result.setMsg("�����ʼǳɹ�");
		result.setData(note);
		return result;
	}
	public NoteResult<Object> updateStatus(String noteId) {
		int rows = noteDao.updateStatus(noteId);
		NoteResult<Object> result = new NoteResult<Object>();
		if(rows==1) {
			result.setStatus(0);
			result.setMsg("ɾ���ɹ�");
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("ɾ��ʧ��");
			return result;
		}
		
	}
	
	//����aop�쳣�ع�
	@Transactional
	public void deleteNotes(String... ids) {
		for (String id : ids) {
			int n = noteDao.deleteNote(id);
			if(n!=1) {
				//�׳��쳣�����ع�
				throw new RuntimeException("ɾ���ˣ�");
			}
		}
		
	}

	public NoteResult<Object> updateBookId(String noteId, String bookId) {
		NoteResult<Object> result = new NoteResult<Object>();
		Map<String,Object> params = new HashMap();
	    params.put("bookId", bookId);
		params.put("noteId", noteId);
		int a = noteDao.moveNote(params);
		if(a==1) {
			result.setStatus(0);
			result.setMsg("�ƶ��ɹ�");
		}
		return result;
	}
	
}
