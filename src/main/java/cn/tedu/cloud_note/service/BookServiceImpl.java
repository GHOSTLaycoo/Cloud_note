package cn.tedu.cloud_note.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("bookService")
public class BookServiceImpl implements BookService{
	@Resource
	BookDao bookDao;
	public NoteResult<List<Book>> loadUserBooks(String userId) {
		List<Book> list = bookDao.findByUserId(userId);
		
		//构建返回结果result
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		
		result.setStatus(0);
		result.setMsg("查询笔记本成功");
		result.setData(list);
		return result;
	}
	public NoteResult<Book> addBook(String userId, String bookName) {
		NoteResult<Book> result = new NoteResult<Book>();
		Book book=new Book();
		String bookId=NoteUtil.createId();
		Timestamp time=new Timestamp(
				System.currentTimeMillis());
		book.setCn_notebook_id(bookId);
		book.setCn_user_id(userId);
		book.setCn_notebook_type_id("5");
		book.setCn_notebook_name(bookName);
		book.setCn_notebook_createtime(time);
		bookDao.save(book);
		
		result.setStatus(0);
		result.setMsg("创建成功");
		result.setData(book);
		return result;
	}
	public NoteResult<Object> reBookName(String bookId, String name) {
		NoteResult<Object> result = new NoteResult<Object>();
		Map<String,Object> params = new HashMap();
	    params.put("bookId", bookId);
		params.put("name", name);
		
		int a = bookDao.rename(params);
		if(a==1) {
			result.setStatus(0);
			result.setMsg("修改成功");
		}
		return result;
		
	}
	public NoteResult<Object> deleteBook(String bookId) {
		NoteResult<Object> result = new NoteResult<Object>();
		int a = bookDao.deleteBookById(bookId);
		if(a!=0) {
			result.setStatus(0);
			result.setMsg("删除成功");
		}
		return result;
	}
	
}
