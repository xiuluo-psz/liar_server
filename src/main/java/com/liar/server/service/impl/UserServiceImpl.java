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
	public UserEntity findByKeyAndPassword(String password, String phoneNumber, String email) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("password", password);
		if (phoneNumber != null) {
			params.put("phoneNumber", phoneNumber);
		}
		if (email != null) {
			params.put("email", email);
		}
		UserEntity uu = userMapper.findByKeyAndPassword(params);
		return uu;
	}

	@Override
	public UserEntity findById(String userId) {
		return userMapper.findById(userId);
	}

}
