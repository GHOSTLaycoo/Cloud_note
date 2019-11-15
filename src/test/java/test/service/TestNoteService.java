package test.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;
import test.TestBase;

public class TestNoteService extends TestBase{
	private NoteService noteService;
	@Before
	public void init() {
		noteService = super.getContext().getBean("noteService",NoteService.class);
	}
	
	@Test
	public void testNoteService() {
		NoteResult<List<Map>> result = noteService.loadBookNotes("fa8d3d9d-2de5-4cfe-845f-951041bcc461");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
	}
	
	@Test
	public void testShow() {
		NoteResult<Note> result = noteService.loadNote("5565bda4-ddee-4f87-844e-2ba83aa4925f");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	
	@Test
	public void testUpdate() {
		String id="5565bda4-ddee-4f87-844e-2ba83aa4925f";
		String title="Java±Ê¼Ç";
		String body="JDBC¿ª·¢";
		NoteResult<Object> result=
		noteService.updateNote(id, title, body);
		System.out.println(result);
	}
	
	@Test
	public void testDeleteNotes() {
		noteService.deleteNotes("5565bda4-ddee-4f87-844e-2ba83aa4925f","id2","id3");
	}
}
