package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Book;

public interface BookDao {
	public void save(Book book);
	public List<Book> findByUserId(String userId);
	public int rename(Map params);
	public int deleteBookById(String bookId);
	
	public Book findByBookId(String bookId);
}
