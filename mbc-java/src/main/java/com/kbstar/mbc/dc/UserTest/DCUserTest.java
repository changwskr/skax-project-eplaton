package com.kbstar.mbc.dc.UserTest;

import java.util.HashMap;
import java.util.List;

import com.kbstar.mbc.dc.UserTest.IDCUserTest;
import com.kbstar.mbc.dc.UserTest.UserTest;
import com.kbstar.ksa.das.NewPKDuplicationException;
import com.kbstar.ksa.das.NewPersistenceException;
import com.kbstar.ksa.das.NewTooManyRowsException;
import com.kbstar.ksa.das.ibatis.NewSqlMapper;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.exception.NewFrameworkException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSCommonDTO;

public class DCUserTest implements IDCUserTest {

	public List<HashMap> getUserList(IFRSCommonDTO commonDto) throws BusinessException {
		List result = null;
		try {
			result = (List) SqlMapper.getSqlMapClient().queryForList("userpilot2.getListUser",
					commonDto.getParameterMap());
		} catch (TooManyRowsException e) {
			throw new BusinessException("D9005101", e.toString());
		} catch (PKDuplicationException e) {
			throw new BusinessException("D9005102", e.toString());
		} catch (PersistenceException e) {
			throw new BusinessException("D9005103", e.toString());
		} catch (FrameworkException e) {
			throw new BusinessException("D9005104", e.toString());
		} catch (Exception e) {
			throw new BusinessException("D9005105", e.toString());
		}
		return result;
	}
}
