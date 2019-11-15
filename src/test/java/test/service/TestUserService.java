package test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestUserService {
	UserService service;
	@Before
	public void init() {
		String[] conf= {"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-transaction.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		service = ctx.getBean("userService",UserService.class);	
	}
	@Test
	public void test1() {
		NoteResult<User> result=service.checkLogin("李大诗人","123");
		System.out.println(service.getClass().getName());
		//System.out.println(result.getStatus());
		//System.out.println(result.getMsg());
		//System.out.println(result.getData());
	}
	@Test
	public void test2() {
		String name="苍老师";
		String password="123456";
		String nick="二蛋";
		NoteResult<Object> result = service.addUser(name, password, nick);
		
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
 	}
	public void test3() {
	
}
}
