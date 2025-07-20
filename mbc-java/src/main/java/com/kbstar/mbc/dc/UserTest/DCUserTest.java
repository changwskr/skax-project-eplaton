package com.kbstar.mbc.dc.UserTest;

import java.util.HashMap;
import java.util.List;

import com.kbstar.mbc.dc.UserTest.IDCUserTest;
import com.kbstar.mbc.dc.UserTest.UserTest;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.das.ibatis.NewSqlMapper;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSCommonDTO;

public class DCUserTest implements IDCUserTest {

	public List<HashMap> getUserList(IFRSCommonDTO commonDto) throws NewBusinessException {
		List result = null;
		try {
			result = (List) NewSqlMapper.getSqlMapClient().queryForList("userpilot2.getListUser",
					commonDto.getParameterMap());
		} catch (Exception e) {
			throw new NewBusinessException("D9005105", e);
		}
		return result;
	}
}
