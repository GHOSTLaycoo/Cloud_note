 package cn.tedu.cloud_note.dao;

import java.util.Map;

import cn.tedu.cloud_note.entity.User;

public interface UserDao {
	//登陆方法
	public User findByName(String name);
	//注册方法
	public void save(User user);
	//修改密码
	public int updatepassword(Map paramter);
}
