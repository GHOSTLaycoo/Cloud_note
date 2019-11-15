package test.dao;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.EmpDao;
import cn.tedu.cloud_note.entity.Emp;
import test.TestBase;

public class TestEmpDao extends TestBase{
	private EmpDao empDao;	
	@Before
	public void init() {
		empDao=getContext().getBean("empDao",EmpDao.class);
		
	}
	
	@Test
	public void test() {
		Emp emp=new Emp();
		emp.setName("zhangfei");
		emp.setAge(88);
		empDao.save(emp);
		int id = emp.getId();
		System.out.println("¸Õ²åÈëµÄid:"+id);
		
	}
}
