package com.liar.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liar.server.constant.Status;
import com.liar.server.entity.UserEntity;
import com.liar.server.model.ResultModel;
import com.liar.server.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "User")
public class UserController {

	@Autowired
	private UserService userService;

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
}
