package com.liar.server.service;

import com.liar.server.entity.UserEntity;

public interface UserService {
	public UserEntity findByKeyAndPassword(String password, String phoneNumber, String email);
	public UserEntity findById(String userId);

}
