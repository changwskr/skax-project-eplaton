package com.kbstar.mbc.pc.usermanagementpilotpc;

import java.util.List;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.util.NewObjectUtil;
import com.kbstar.mbc.dc.usermgtpilotdc.DCUserPilot;
import com.kbstar.mbc.dc.usermgtpilotdc.IDCUserPilot;
import com.kbstar.mbc.dc.usermgtpilotdc.UserPilot;
import com.kbstar.mbc.dc.usermgtpilotdc.dto.UserPilotDDTO;
import com.kbstar.mbc.pc.dto.UserPilotPDTO;

public class PCUserManagementPilot implements IPCUserManagementPilot {

	public List<UserPilot> getListUser(UserPilotPDTO userpilotPDTO) throws NewBusinessException {

		IDCUserPilot idcuserpilot = new DCUserPilot();

		return idcuserpilot.getListUser(NewObjectUtil.copyForClass(UserPilotDDTO.class, userpilotPDTO));
	}

}
