package cn.tedu.cloud_note.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class AddBookController {
	@Resource
	BookService bookservice;
	@RequestMapping("/add.do")
	@ResponseBody
	public NoteResult<Book> execute(String userId, String bookName){
		System.out.println("Ìí¼Ó±Ê¼Ç±¾");
		NoteResult<Book> result = bookservice.addBook(userId, bookName);
		System.out.println(result);
		return result;
		
	}
}
