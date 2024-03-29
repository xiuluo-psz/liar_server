package com.liar.server.constant;

public class Status {
	// code
	public static final String CODE_SUCCESS = "200";
	public static final String CODE_FAILED = "299";
	public static final String CODE_DB = "301";
	public static final String CODE_UNAUTHORIZED = "401";
	public static final String CODE_UNCORRECT = "402";
	public static final String CODE_UNKNOWN = "500";

	// message
	public static final String MSG_SUCCESS = "success";
	public static final String MSG_FAILED = "failed";
	public static final String MSG_NEEDPARAMETERS = "need parameters";
	public static final String MSG_UNAUTHORIZED = "unauthorized";
	public static final String MSG_NOUSER = "no user";
	public static final String MSG_UNCORRECT = "password is wrong";
	public static final String MSG_USERDELETED = "user has deleted";
	public static final String MSG_UPDATEFALIED = "user update failed";
	public static final String MSG_EMAILEXIST = "email has been registed";
	public static final String MSG_UNKNOWN = "unknown problem on server";
	public static final String MSG_DELFAILED = "token does not match current user";

}
