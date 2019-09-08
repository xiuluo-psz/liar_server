package com.liar.server.model;

import com.liar.server.entity.UserEntity;

public class LoginModel extends UserEntity {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
