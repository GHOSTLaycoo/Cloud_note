package cn.tedu.cloud_note.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class LoadBooksController {
	@Resource
	private BookService bookService;
	
	@ResponseBody
	@RequestMapping("/loadbooks.do")
	public NoteResult<List<Book>> execute(String userId){
		System.out.println("º”‘ÿ± º«±æ");
		NoteResult<List<Book>> result = bookService.loadUserBooks(userId);
		
		return result;
	}
	
}
