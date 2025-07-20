package com.kbstar.mbc.dc.user;

import java.util.HashMap;
import java.util.List;

import com.kbstar.mbc.dc.user.IDCUser;
import com.kbstar.mbc.dc.user.User;
import com.kbstar.ksa.das.NewPKDuplicationException;
import com.kbstar.ksa.das.NewPersistenceException;
import com.kbstar.ksa.das.NewTooManyRowsException;
import com.kbstar.ksa.das.ibatis.NewSqlMapper;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.exception.NewFrameworkException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;

public class DCUser implements IDCUser {

	public List<HashMap> getUserList(ICommonDTO commonDto) throws NewBusinessException {
		List result = null;
		try {
			result = (List) NewSqlMapper.getSqlMapClient().queryForList("userpilot2.getListUser",
					commonDto.getParameterMap());
		} catch (Exception e) {
			throw new NewBusinessException("D9005105", e.toString());
		}
		return result;
	}

	@Override
	public User selectUser(String userId) throws Exception {
		// Stub implementation
		User user = new User();
		user.setUserId(userId);
		user.setUserName("Test User");
		user.setUserEmail("test@example.com");
		return user;
	}
}
