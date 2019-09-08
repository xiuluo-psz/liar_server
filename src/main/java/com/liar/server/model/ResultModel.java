package com.liar.server.model;

import com.liar.server.constant.Constants;
import com.liar.server.constant.Message;

public class ResultModel {
	String code = Constants.CODE_FAILED;
	String msg = Message.MSG_FAILED;
	Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
