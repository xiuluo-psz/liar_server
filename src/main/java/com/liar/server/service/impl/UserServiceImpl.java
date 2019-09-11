package com.liar.server.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
		if (StringUtils.isNotEmpty(phoneNumber)) {
			params.put("phoneNumber", phoneNumber);
		}
		if (StringUtils.isNotEmpty(email)) {
			params.put("email", email);
		}
		return userMapper.findByKeyAndPassword(params);
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
}
