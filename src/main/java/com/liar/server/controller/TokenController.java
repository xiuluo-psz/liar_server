package com.liar.server.controller;

import java.util.HashMap;
import java.util.Map;

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

@CrossOrigin
@RestController
@RequestMapping(value = "Token")
public class TokenController {
	@Autowired
	private TokenService tokenService;

	@Authentication
	@PostMapping(value = "/refresh")
	public ResultModel refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			@RequestBody UserEntity user) {
		ResultModel result = new ResultModel();

		String token = httpServletRequest.getHeader(Constants.AUTHORIZATION);
		String userId = tokenService.getUUID(token);
		if (!userId.equals(user.getUserId())) {
			result.setCode(Status.CODE_FAILED);
			result.setMsg(Status.MSG_DELFAILED);
			return result;
		}
		String newToken = tokenService.refreshToken(user);
		Map<String, String> map = new HashMap<String, String>();
		map.put("token", newToken);
		result.setCode(Status.CODE_SUCCESS);
		result.setMsg(Status.MSG_SUCCESS);
		result.setData(map);
		return result;
	}
}
