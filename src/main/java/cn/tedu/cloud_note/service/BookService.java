package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;

public interface BookService {
	public NoteResult<List<Book>> loadUserBooks(String userId);
	public NoteResult<Book> addBook(String userId,String bookName);
	public NoteResult<Object> reBookName(String bookId,String name);
	public NoteResult<Object> deleteBook(String bookId);
} 
