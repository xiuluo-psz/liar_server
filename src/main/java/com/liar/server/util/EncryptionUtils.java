package com.liar.server.util;

import org.springframework.util.DigestUtils;

public class EncryptionUtils {
	public static String MD5digest(String pwd, String salt) {
		String saltPwd = salt.concat(pwd);
		String md5DigestAsHex = DigestUtils.md5DigestAsHex(saltPwd.getBytes());
		return md5DigestAsHex;
	}
}
