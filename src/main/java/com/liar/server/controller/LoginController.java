package com.liar.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.liar.server.constant.Status;
import com.liar.server.entity.UserEntity;
import com.liar.server.model.LoginModel;
import com.liar.server.model.ResultModel;
import com.liar.server.service.UserService;

@CrossOrigin
@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/login")
	public ResultModel login(@RequestBody LoginModel login) {
		ResultModel result = new ResultModel();

		String pwd = login.getPassword();
		String email = login.getEmail();
		String phoneNumber = login.getPhoneNumber();

		if (email == null && phoneNumber == null) {
			result.setMsg("email and phone must not be null!");
			result.setCode("299");
			return result;
		}
		UserEntity user = new UserEntity();
		try {
			user = userService.findByKeyAndPassword(pwd, phoneNumber, email);
			result.setCode(Status.CODE_SUCCESS);
			result.setMsg(Status.MEG_SUCCESS);
			result.setData(user);
		} catch (Exception e) {
			result.setCode(Status.CODE_DB);
			result.setMsg(e.getMessage());
		}
		return result;
	}

}
