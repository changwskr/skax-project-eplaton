package com.kbstar.mbc.pc.UserTest;

import java.util.List;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSCommonDTO;

public interface IPCUserTest {

	public List getUserList(IFRSCommonDTO commonDto) throws NewBusinessException;
}
