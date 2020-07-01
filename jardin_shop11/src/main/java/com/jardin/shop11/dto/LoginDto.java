package com.jardin.shop11.dto;

public class LoginDto {

	private String memId;
	private String memPw;
	private int loginCheck;

	public int getLoginCheck() {
		return loginCheck;
	}

	public void setLoginCheck(int loginCheck) {
		this.loginCheck = loginCheck;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

}
