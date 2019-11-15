package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class DeleteBookController {
		@Resource
		BookService bookservice;
		@RequestMapping("/delete.do")
		@ResponseBody
		public NoteResult<Object> execute(String bookId){
			System.out.println("É¾³ý±Ê¼Ç±¾");
			NoteResult<Object> result = bookservice.deleteBook(bookId);
			System.out.println(result);
			return result;
			
		}
	}
