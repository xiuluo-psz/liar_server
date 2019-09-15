package com.liar.server.model;

import com.liar.server.entity.UserEntity;

public class LoginModel extends UserEntity {

	private int accessToken;
	private String token;

	public int getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(int accessToken) {
		this.accessToken = accessToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
