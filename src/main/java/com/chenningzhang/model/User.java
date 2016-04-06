package com.chenningzhang.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	@NotNull
	private int userId;
	
	@NotNull
	@Size(min=1, max=100)
	private String userName;
	
	@NotNull
	private int loggedin;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getLoggedin() {
		return loggedin;
	}

	public void setLoggedin(int loggedin) {
		this.loggedin = loggedin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", loggedin=" + loggedin + "]";
	}
	
	
}
