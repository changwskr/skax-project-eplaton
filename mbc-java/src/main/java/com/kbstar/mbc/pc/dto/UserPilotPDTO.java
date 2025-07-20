package com.kbstar.mbc.pc.dto;

import com.kbstar.ksa.infra.po.NewAbstractDTO;

/**
 * UserPilotPDTO class
 */
public class UserPilotPDTO extends NewAbstractDTO {

	private String userId;
	private String userName;
	private String userEmail;

	public UserPilotPDTO() {
		// Default constructor
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
