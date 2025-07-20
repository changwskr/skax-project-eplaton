package com.kbstar.mbc.pc.UserTest;

import java.util.List;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.mbc.dc.UserTest.DCUserTest;
import com.kbstar.mbc.dc.UserTest.IDCUserTest;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSCommonDTO;

public class PCUserTest implements IPCUserTest {

	public List getUserList(IFRSCommonDTO commonDto) throws BusinessException {
				
		IDCUserTest dcUser = new DCUserTest();
		List usrList = dcUser.getUserList(commonDto);
			
		return usrList;
	}
}
