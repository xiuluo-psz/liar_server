package com.liar.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liar.server.annotation.Authentication;
import com.liar.server.constant.Constants;
import com.liar.server.constant.Status;
import com.liar.server.entity.UserEntity;
import com.liar.server.model.ResultModel;
import com.liar.server.service.TokenService;
import com.liar.server.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "User")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/registry")
	public ResultModel registry(@RequestBody UserEntity user) {
		ResultModel result = new ResultModel();
		try {
			userService.addUser(user);
			result.setCode(Status.CODE_SUCCESS);
			result.setMsg(Status.MSG_SUCCESS);
		} catch (Exception e) {
			result.setCode(Status.CODE_FAILED);
			result.setMsg(Status.MSG_EMAILEXIST);
		}
		return result;
	}

	@PostMapping(value = "/update")
	@Authentication
	public ResultModel updateUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody UserEntity user) {
		ResultModel result = new ResultModel();
		String token = httpServletRequest.getHeader(Constants.AUTHORIZATION);
		String userId = tokenService.getUUID(token);
		if (!userId.equals(user.getUserId())) {
			result.setCode(Status.CODE_FAILED);
			result.setMsg(Status.MSG_DELFAILED);
			return result;
		}
		int updateUser = userService.updateUser(user);
		if (updateUser == 0) {
			result.setCode(Status.CODE_FAILED);
			result.setMsg(Status.MSG_UPDATEFALIED);
		}
		result.setCode(Status.CODE_SUCCESS);
		result.setMsg(Status.MSG_SUCCESS);
		return result;
	}

}
