package com.liar.server.controller;

import java.util.UUID;

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
import com.liar.server.model.LoginModel;
import com.liar.server.model.ResultModel;
import com.liar.server.service.TokenService;
import com.liar.server.service.UserService;
import com.liar.server.util.EncryptionUtils;

@CrossOrigin
@RestController
@RequestMapping(value = "User")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/registry")
	public ResultModel registry(@RequestBody LoginModel login) {
		ResultModel result = new ResultModel();

		String pwd = login.getPassword();
		String salt = UUID.randomUUID().toString();
		String md5DigestAsHex = EncryptionUtils.MD5digest(pwd, salt);
		login.setSalt(salt);
		login.setPasswordMD5(md5DigestAsHex);

		try {
			userService.addUser(login);
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
			@RequestBody LoginModel login) {
		ResultModel result = new ResultModel();
		String token = httpServletRequest.getHeader(Constants.AUTHORIZATION);
		String userId = tokenService.getUUID(token);
		if (!userId.equals(login.getUserId())) {
			result.setCode(Status.CODE_FAILED);
			result.setMsg(Status.MSG_DELFAILED);
			return result;
		}

		UserEntity user = new UserEntity();

		if (login.getPassword() != null) {
			user = userService.findById(userId);
			String pwd = login.getPassword();
			String salt = user.getSalt();
			String md5DigestAsHex = EncryptionUtils.MD5digest(pwd, salt);
			user.setPasswordMD5(md5DigestAsHex);
//			user.setSalt(user.getSalt());
		}

		user.setUserId(login.getUserId());
		user.setUserName(login.getUserName());
		user.setEmail(login.getEmail());
		user.setPhoneNumber(login.getPhoneNumber());
		user.setImage(login.getImage());
		user.setDeleteFlag(login.isDeleteFlag());

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
