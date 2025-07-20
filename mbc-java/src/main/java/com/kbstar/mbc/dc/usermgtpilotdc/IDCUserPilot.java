package com.kbstar.mbc.dc.usermgtpilotdc;

import java.util.List;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.dc.usermgtpilotdc.dto.UserPilotDDTO;

public interface IDCUserPilot {

	public List<UserPilot> getListUser(UserPilotDDTO userpilotDDTO) throws NewBusinessException;

	public void crudGrid(UserPilotDDTO[] userpilotDDTOs) throws NewBusinessException;

	public List<UserPilot> getListDept(UserPilotDDTO userpilotDDTO) throws NewBusinessException;
}
