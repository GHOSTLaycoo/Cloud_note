package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("userService") //ɨ���Spring����
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	public NoteResult<User> checkLogin(String name,String password){
		//���ս������
		NoteResult<User> result = new NoteResult<User>();
		//������name��ѯ���ݿ�
		User user = userDao.findByName(name);
		//�û������
		if(user==null) {
			result.setStatus(1);
			result.setMsg("�û���������");
			return result;
		}
		//�������
		//��������
		String md5Password = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)) {
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		//�û��������붼��ȷʱ
		result.setStatus(0);
		result.setMsg("��½�ɹ�");
		result.setData(user);
		return result;
	}
	
	public NoteResult<Object> addUser(String name,String password,String nick){
		NoteResult<Object> result = new NoteResult<Object>();
		//�û����
		User hasUser = userDao.findByName(name);
		if(hasUser!=null) {//�û�������
			result.setStatus(1);
			result.setMsg("�û��ѱ�ռ��");
			return result;
		}
		
		//�����û�
		User user = new User();
		//�����û���
		user.setCn_user_name(name);
		//�����û�����
		String md5Password = NoteUtil.md5(password);
		user.setCn_user_password(md5Password);
		//�����û��ǳ�
		user.setCn_user_nick(nick);
		//����ID
		String id = NoteUtil.createId();
		//�����û�ID
		user.setCn_user_id(id);
		//�����û�����
		userDao.save(user);
		
		//�������ؽ��
		result.setStatus(0);
		result.setMsg("ע��ɹ�");
		return result;
	}

	public NoteResult<Object> updatepassword(String userId,String password) {
		//�����û�����
	    String md5Password = NoteUtil.md5(password);
	    Map<String,Object> paramter = new HashMap();
	    paramter.put("userId", userId);
		paramter.put("md5Password", md5Password);
		int rows=userDao.updatepassword(paramter);
		NoteResult<Object> result = new NoteResult<Object>();
		if(rows==1) {
			result.setStatus(0);
			result.setMsg("�޸ĳɹ�,�����ص�½����");
			return result;
		}else {
			result.setStatus(1);
			result.setMsg("�޸�ʧ��");
			return result;
		}
		
	}
}