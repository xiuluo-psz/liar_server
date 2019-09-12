package com.liar.server.service;

import com.liar.server.entity.UserEntity;

public interface UserService {

	public UserEntity findByKeyAndPassword(String password, String phoneNumber, String email);

	public UserEntity findById(String userId);

	public UserEntity findByPhone(String phoneNumber);

	public int deleUser(String userId);
	
	public int addUser(UserEntity user);

}
