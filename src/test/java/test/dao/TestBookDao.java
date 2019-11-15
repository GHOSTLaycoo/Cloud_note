package test.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.entity.User;

public class TestBookDao {
	String[] conf= {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
	ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);

	@Test
	public void testDao() {
		BookDao dao = ctx.getBean("bookDao",BookDao.class);
		List<Book> list = dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		for(Book book:list) {
			System.out.println(book.getCn_notebook_id()+","+book.getCn_notebook_name());
		}
	}
	
	@Test
	public void testSave() {
		//获取UserDao对象
		BookDao dao=ctx.getBean("bookDao",BookDao.class);
		Book book = new Book();
		book.setCn_notebook_id("3");
		book.setCn_user_id("6");
		book.setCn_notebook_type_id("5");
		book.setCn_notebook_name("你好");
		
		
		dao.save(book);
		System.out.print(book);
}
}
