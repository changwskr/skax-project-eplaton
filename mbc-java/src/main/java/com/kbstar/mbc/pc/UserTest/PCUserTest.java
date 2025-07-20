package com.kbstar.mbc.pc.UserTest;

import java.util.List;

import com.kbstar.mbc.dc.UserTest.IDCUserTest;
import com.kbstar.mbc.dc.UserTest.UserTest;
import com.kbstar.mbc.dc.UserTest.DCUserTest;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSCommonDTO;

public class PCUserTest implements IPCUserTest {

	public List getUserList(IFRSCommonDTO commonDto) throws NewBusinessException {

		IDCUserTest dcUser = new DCUserTest();
		List usrList = dcUser.getUserList(commonDto);

		return usrList;
	}
}
