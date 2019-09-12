package com.liar.server.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.liar.server.entity.UserEntity;

@Mapper
public interface UserMapper {

	public UserEntity findByKeyAndPassword(Map<String, String> params);

	public UserEntity findById(String userId);

	public UserEntity findByPhone(String phoneNumber);

	public int deleUser(String userId);
	
	public int addUser(Map<String, String> params);
}
