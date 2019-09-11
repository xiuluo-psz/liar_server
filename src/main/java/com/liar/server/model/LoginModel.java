package com.liar.server.model;

import com.liar.server.entity.UserEntity;

public class LoginModel extends UserEntity {

	private int allowDay;
	private String token;

	public int getAllowDay() {
		return allowDay;
	}

	public void setAllowDay(int allowDay) {
		this.allowDay = allowDay;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
