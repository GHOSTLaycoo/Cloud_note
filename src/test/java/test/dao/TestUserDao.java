package test.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;

public class TestUserDao {
	@Test
	public void testUserDao() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		UserDao dao = ctx.getBean("userDao",UserDao.class);
		
		User user = dao.findByName("demo");
		System.out.println(user);
	}
	
	@Test
	public void testSave() {
		String[] conf = {"conf/spring-mvc.xml","conf/spring-mybatis.xml"};
		//实例化ctx对象
		ApplicationContext ctx = new ClassPathXmlApplicationContext(conf);
		//获取UserDao对象
		UserDao dao=ctx.getBean("userDao",UserDao.class);
		User user = new User();
		user.setCn_user_id("1315343");
		user.setCn_user_name("张峰");
		user.setCn_user_password("123456");
		user.setCn_user_nick("小gay斌");
		
		
		dao.save(user);
		System.out.print(user);
	}

}
