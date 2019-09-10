package com.liar.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liar.server.annotation.Authentication;
import com.liar.server.constant.Status;
import com.liar.server.entity.UserEntity;
import com.liar.server.model.LoginModel;
import com.liar.server.model.ResultModel;
import com.liar.server.service.TokenService;
import com.liar.server.service.UserService;

@CrossOrigin
@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/login")
//	@Authentication
	public ResultModel login(@RequestBody LoginModel login) {
		ResultModel result = new ResultModel();

		String pwd = login.getPassword();
		String email = login.getEmail();
		String phoneNumber = login.getPhoneNumber();

		if (StringUtils.isEmpty(email) && StringUtils.isEmpty(phoneNumber)) {
			result.setCode(Status.CODE_FAILED);
			result.setMsg(Status.MSG_NEEDPARAMETERS);
			return result;
		}
		UserEntity user = new UserEntity();
		try {
			user = userService.findByKeyAndPassword(pwd, phoneNumber, email);
			login.setUserId(user.getUserId());
			login.setUserName(user.getUserName());
			login.setPhoneNumber(user.getPhoneNumber());
			login.setEmail(user.getEmail());
			login.setDeleteFlag(user.isDeleteFlag());
			login.setImage(user.getImage());
			login.setVersion(user.getVersion());
			login.setToken(tokenService.getToken(user));

			result.setCode(Status.CODE_SUCCESS);
			result.setMsg(Status.MEG_SUCCESS);
			result.setData(login);
		} catch (Exception e) {
			result.setCode(Status.CODE_DB);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	@PostMapping(value = "/test")
	@Authentication
	public String test() {
		return "test";
	}
}
