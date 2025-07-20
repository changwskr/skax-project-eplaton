package com.kbstar.mbc.dc.user;

import java.util.HashMap;
import java.util.List;

import com.kbstar.ksa.das.PKDuplicationException;
import com.kbstar.ksa.das.PersistenceException;
import com.kbstar.ksa.das.TooManyRowsException;
import com.kbstar.ksa.das.ibatis.SqlMapper;
import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.exception.FrameworkException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;

public class DCUser implements IDCUser {

	public List<HashMap> getUserList(ICommonDTO commonDto) throws BusinessException {
		List result = null;
		try {
			result = (List) SqlMapper.getSqlMapClient().queryForList("userpilot2.getListUser", commonDto.getParameterMap());
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
