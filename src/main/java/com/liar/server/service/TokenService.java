package com.liar.server.service;

import com.liar.server.entity.UserEntity;

public interface TokenService {
	public String getToken(UserEntity user);

	public boolean verifyToken(String token);

	public String getUUID(String token);
}
