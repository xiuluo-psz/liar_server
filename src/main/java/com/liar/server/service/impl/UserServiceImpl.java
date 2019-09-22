package com.liar.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liar.server.entity.UserEntity;
import com.liar.server.mapper.UserMapper;
import com.liar.server.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserEntity findByKeyAndPassword(String passwordMD5, String phoneNumber, String email) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("passwordMD5", passwordMD5);
		params.put("phoneNumber", phoneNumber);
		params.put("email", email);
		return userMapper.findByKeyAndPassword(params);
	}

	@Override
	public UserEntity findByEmailOrPhone(String email, String phoneNumber) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", phoneNumber);
		params.put("email", email);
		return userMapper.findByEmailOrPhone(params);
	}

	@Override
	public UserEntity findById(String userId) {
		return userMapper.findById(userId);
	}

	@Override
	public UserEntity findByPhone(String phoneNumber) {
		return userMapper.findByPhone(phoneNumber);
	}

	@Override
	public int deleUser(String userId) {
		return userMapper.deleUser(userId);
	}

	@Override
	public int addUser(UserEntity user) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userName", user.getUserName());
		params.put("email", user.getEmail());
		params.put("passwordMD5", user.getPasswordMD5());
		params.put("salt", user.getSalt());
		return userMapper.addUser(params);
	}

	public int updateUser(UserEntity user) {
		return userMapper.updateUser(user);
	}
}
