package com.liar.server.model;

import com.liar.server.entity.UserEntity;

public class LoginModel extends UserEntity {

	private String password;
	private int accessToken;
	private String token;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
