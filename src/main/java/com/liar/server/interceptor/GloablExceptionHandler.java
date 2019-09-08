package com.liar.server.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liar.server.constant.Status;
import com.liar.server.model.ResultModel;

@ControllerAdvice
public class GloablExceptionHandler {
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResultModel handleException(Exception e) {
		String msg = e.getMessage();
		if (StringUtils.isEmpty(msg)) {
			msg = Status.MSG_UNKNOWN;
		}
		ResultModel result = new ResultModel();
		result.setCode(Status.CODE_UNKNOWN);
		result.setMsg(msg);
		return result;
	}
}
