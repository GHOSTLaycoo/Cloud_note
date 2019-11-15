package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/book")
public class ReNameBookController {
	@Resource
	BookService bookservice;
	@RequestMapping("/rename.do")
	@ResponseBody
	public NoteResult<Object> execute(String bookId, String rename){
		System.out.println("笔记本改名");
		NoteResult<Object> result = bookservice.reBookName(bookId, rename);
		System.out.println(result);
		return result;
		
	}
}
