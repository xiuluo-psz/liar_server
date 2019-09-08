package com.liar.server.service.impl;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.liar.server.entity.UserEntity;
import com.liar.server.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {
	@Override
	public String getToken(UserEntity user) {
		String token = "";
		token = JWT.create().withAudience(user.getPhoneNumber())// 将 phone number 保存到 token 里面
				.sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
		return token;
	}
}
