package com.liar.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liar.server.constant.Status;
import com.liar.server.entity.UserEntity;
import com.liar.server.model.ResultModel;
import com.liar.server.service.TokenService;

@CrossOrigin
@RestController
@RequestMapping(value = "Token")
public class TokenController {
	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/refresh")
	public ResultModel refreshToken(UserEntity user) {
		ResultModel result = new ResultModel();
		result.setCode(Status.CODE_SUCCESS);
		result.setMsg(Status.MSG_SUCCESS);
		result.setData(tokenService.refreshToken(user));
		return result;
	}
}
