package com.liar.server.interceptor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liar.server.model.ResultModel;

@ControllerAdvice
public class GloablExceptionHandler {
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public Object handleException(Exception e) {
		String msg = e.getMessage();
		if (msg == null || msg.equals("")) {
			msg = "服务器出错";
		}
		ResultModel result = new ResultModel();
		result.setCode("599");
		result.setMsg(msg);
		return result;
	}
}
