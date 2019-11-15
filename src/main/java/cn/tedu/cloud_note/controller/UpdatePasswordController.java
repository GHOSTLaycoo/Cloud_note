package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/password")
public class UpdatePasswordController {
   @Resource
   private UserService userService;
   
   @RequestMapping("/modify.do")
   @ResponseBody
   public NoteResult<Object> execute(String userId,String password){
	   System.out.println("ÐÞ¸ÄÃÜÂë");
	   NoteResult<Object> result = userService.updatepassword(userId, password);
	   System.out.println(result);
	   return result;
	   
   }
}
