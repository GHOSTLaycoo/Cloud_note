 package cn.tedu.cloud_note.dao;

import java.util.Map;

import cn.tedu.cloud_note.entity.User;

public interface UserDao {
	//��½����
	public User findByName(String name);
	//ע�᷽��
	public void save(User user);
	//�޸�����
	public int updatepassword(Map paramter);
}
