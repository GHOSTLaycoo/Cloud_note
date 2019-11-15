package cn.tedu.cloud_note.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;


/*
 * 加密密码工具
 */

public class NoteUtil {
	/*
	 * 利用UUID算法生成主键
	 */
	public static String createId() {
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString();
		
		return id.replace("-", "");
	}
	
	public static String md5(String src) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			//MD5加密处理
			byte[] output = md.digest(src.getBytes());
			//Base64处理
			String ret = Base64.encodeBase64String(output);
			return ret;
		} catch (Exception e) {
			throw new NoteException("加密失败", e);
		}	
	}
	
	public static void main(String[] args) {
		System.out.println(createId());
     	System.out.println(md5(createId()));
	}
}
