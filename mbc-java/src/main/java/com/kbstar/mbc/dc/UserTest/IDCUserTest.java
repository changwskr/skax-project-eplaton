package com.kbstar.mbc.dc.UserTest;

import java.util.HashMap;
import java.util.List;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSCommonDTO;

public interface IDCUserTest {

	public List<HashMap> getUserList(IFRSCommonDTO commonDto) throws NewBusinessException;
}
