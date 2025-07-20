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
		} catch (NewTooManyRowsException e) {
			throw new NewBusinessException("D9005101", e.toString());
		} catch (NewPKDuplicationException e) {
			throw new NewBusinessException("D9005102", e.toString());
		} catch (NewPersistenceException e) {
			throw new NewBusinessException("D9005103", e.toString());
		} catch (NewFrameworkException e) {
			throw new NewBusinessException("D9005104", e.toString());
		} catch (Exception e) {
			throw new NewBusinessException("D9005105", e.toString());
		}
		return result;
	}

}
