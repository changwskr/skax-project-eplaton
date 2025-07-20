/**
 * 
 */
package com.kbstar.mbc.pc.user;

import java.util.List;

import com.kbstar.mbc.dc.usermgtdc.IDCUser;
import com.kbstar.mbc.dc.usermgtdc.User;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;

/**
 * @author S001849
 *
 */
public class PCUser {

	public List getUserList(ICommonDTO commonDto) throws NewBusinessException {
		// KBData reqData = commonDto.getKbData();

		IDCUser dcUser = new User();

		System.out.println("############ getUserList ############");

		List usrList = dcUser.getUserList(commonDto);

		/*
		 * if(usrList != null) {
		 * 
		 * reqData.getOutputGenericDto().using(GenericDto.OUTDATA).addNode("/UserList").
		 * add(usrList);
		 * 
		 * }
		 */

		return usrList;
	}

}
