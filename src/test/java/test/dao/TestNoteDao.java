package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import test.TestBase;

public class TestNoteDao extends TestBase{
	private NoteDao noteDao;
	
	@Before
	public void init() {
		noteDao = super.getContext().getBean("noteDao",NoteDao.class);
	}
	
	@Test
	public void testNoteDao() {
		List<Map> list=
		noteDao.findByBookId("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		for(Map note:list) {
			System.out.println(note.get("cn_note_id")+","+
								note.get("cn_note_title"));
		}
	}
	
	@Test
	public void testFind() {
		String noteId = "5565bda4-ddee-4f87-844e-2ba83aa4925f";
		
		Note note = 
		noteDao.findByNoteId(noteId);
		System.out.println(note.getCn_note_id());
		System.out.println(note.getCn_note_title());
	}
	
	@Test
	public void testUpdate() {
		Note note=new Note();
		String noteId = "5565bda4-ddee-4f87-844e-2ba83aa4925f";
		note.setCn_note_id(noteId);
		String title="传奇和克晶";
		note.setCn_note_title(title);
		String body="已经上了搜索引擎";
		note.setCn_note_body(body);
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		
		int num=noteDao.updateNote(note);
		System.out.println(num);
	}
	
	@Test
	public void testUpdateStatus() {
		Note note=new Note();
		String noteId = "5565bda4-ddee-4f87-844e-2ba83aa4925f";
		
		
		int num=noteDao.updateStatus(noteId);
		System.out.println(num);
	}
	
	@Test
	public void testUpadteNoteByMap() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("title", "java");
		map.put("body", "Hello World");
		map.put("noteId", "5565bda4-ddee-4f87-844e-2ba83aa4925f");
		noteDao.updateNoteByMap(map);
	}
	
	@Test
	public void testDeleteNotes() {
		Map<String,Object> map=new HashMap<String, Object>();
		String[] ids= {"id1","id2","id3"};
		map.put("ids", ids);
		map.put("status",2);
		int n = noteDao.deleteNotes(map);
		System.out.println(n);
	}
	
}
