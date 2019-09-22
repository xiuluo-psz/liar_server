package com.liar.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@RequestMapping(value = "Login")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@PostMapping(value = "/login")
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

		UserEntity user = userService.findByEmailOrPhone(email, phoneNumber);
		if (user == null) {
			result.setCode(Status.CODE_UNAUTHORIZED);
			result.setMsg(Status.MSG_NOUSER);
			return result;
		}

		String salt = user.getSalt();
		String md5DigestAsHex = EncryptionUtils.MD5digest(pwd, salt);
		if (!md5DigestAsHex.equals(user.getPasswordMD5())) {
			result.setCode(Status.CODE_UNCORRECT);
			result.setMsg(Status.MSG_UNCORRECT);
			return result;
		}

		login.setUserId(user.getUserId());
		login.setUserName(user.getUserName());
		login.setPhoneNumber(user.getPhoneNumber());
		login.setEmail(user.getEmail());
//		login.setPasswordMD5(user.getPasswordMD5());
//		login.setSalt(user.getSalt());
		login.setImage(user.getImage());
		login.setDeleteFlag(user.isDeleteFlag());
		login.setVersion(user.getVersion());
		login.setToken(tokenService.getToken(user));
		login.setAccessToken(Constants.ACCESS_TOKEN);

		result.setCode(Status.CODE_SUCCESS);
		result.setMsg(Status.MSG_SUCCESS);
		result.setData(login);
		return result;
	}

	@PostMapping(value = "/logdel")
	@Authentication
	public ResultModel logdel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody UserEntity user) {
		ResultModel result = new ResultModel();

		String token = httpServletRequest.getHeader(Constants.AUTHORIZATION);
		String userId = tokenService.getUUID(token);

		if (!userId.equals(user.getUserId())) {
			result.setCode(Status.CODE_FAILED);
			result.setMsg(Status.MSG_DELFAILED);
			return result;
		}

		int deleUser = userService.deleUser(userId);
		if (deleUser == 1) {
			result.setCode(Status.CODE_SUCCESS);
			result.setMsg(Status.MSG_SUCCESS);
		} else {
			result.setCode(Status.CODE_FAILED);
			result.setMsg(Status.MSG_USERDELETED);
		}
		return result;
	}
}
