/**
 * 
 */
package com.kbstar.mbc.pc.user;

import java.util.List;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.mbc.dc.user.DCUser;
import com.kbstar.mbc.dc.user.IDCUser;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;

/**
 * @author S001849
 *
 */
public class PCUser {

	public List getUserList(ICommonDTO commonDto) throws BusinessException {
		//KBData reqData = commonDto.getKbData();
		
		IDCUser dcUser = new DCUser();
		
		System.out.println("############ getUserList ############");
		
		List usrList = dcUser.getUserList(commonDto);
		
		/*
		if(usrList != null) {
			
			reqData.getOutputGenericDto().using(GenericDto.OUTDATA).addNode("/UserList").add(usrList);
			
		}
		*/
		
		return usrList;
	}

}
