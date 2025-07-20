package com.kbstar.mbc.dc.usermgtdc;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.kbstar.ksa.infra.po.NewAbstractDTO;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.UserDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.PageDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.TreeDDTO;

/**
 * User class
 */
public class User extends NewAbstractDTO implements IDCUser {

	private String userId;
	private String userName;
	private String userEmail;

	public User() {
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

	public List<HashMap> getUserList(ICommonDTO commonDto) throws NewBusinessException {
		// Stub implementation
		return new java.util.ArrayList<>();
	}

	@Override
	public void crudUser(UserDDTO[] userDDTOs) throws NewBusinessException {
		// Stub implementation
	}

	@Override
	public List<User> getListUser(UserDDTO userDDTO) throws NewBusinessException {
		// Stub implementation
		return new ArrayList<>();
	}

	@Override
	public List<Page> getListPage(PageDDTO pageDDTO) throws NewBusinessException {
		// Stub implementation
		return new ArrayList<>();
	}

	@Override
	public List<Tree> getListTree(TreeDDTO treeDDTO) throws NewBusinessException {
		// Stub implementation
		return new ArrayList<>();
	}
}
