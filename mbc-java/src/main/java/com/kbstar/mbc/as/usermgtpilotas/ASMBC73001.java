package com.kbstar.mbc.as.usermgtpilotas;

import java.util.List;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewGenericDto;
import com.kbstar.ksa.infra.po.NewKBData;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.oltp.biz.NewIApplicationService;
import com.kbstar.mbc.dc.usermgtpilotdc.DCUserPilot;
import com.kbstar.mbc.dc.usermgtpilotdc.IDCUserPilot;
import com.kbstar.mbc.dc.usermgtpilotdc.UserPilot;
import com.kbstar.mbc.dc.usermgtpilotdc.dto.UserPilotDDTO;
import com.kbstar.mbc.pc.dto.UserPilotPDTO;
import com.kbstar.mbc.pc.usermanagementpilotpc.IPCUserManagementPilot;
import com.kbstar.mbc.pc.usermanagementpilotpc.PCUserManagementPilot;

public class ASMBC73001 implements NewIApplicationService {

	protected NewIKesaLogger logger = NewKesaLogHelper.getBiz();

	public NewKBData execute(NewKBData reqData) throws NewBusinessException {

		if (logger.isDebugEnabled())
			logger.debug(this.getClass().getName() + ", log test completed.");

		// Get transaction code txcd from KBData
		NewGenericDto gdto = reqData.getInputGenericDto().using("/KB-Message/Individual/DataHeader");

		int txcd = gdto.getInt("@txcd");

		System.out.println("###################### Transaction Code : " + txcd + "########################");

		List<UserPilot> resUsers = null;

		// Delegate based on txcd
		switch (txcd) {

			case 12:

				UserPilotPDTO userpilotPDTO = new UserPilotPDTO();
				userpilotPDTO = (UserPilotPDTO) reqData.getInputGenericDto().using(NewGenericDto.INDATA)
						.get("UserPilotPDTO");

				try {
					// Call PC
					IPCUserManagementPilot ipcusermgtpilot = new PCUserManagementPilot();
					resUsers = ipcusermgtpilot.getListUser(userpilotPDTO);
				} catch (NewBusinessException e) {
					// TODO Add proper catch handling
					e.printStackTrace();
				}
				// Add User element to /UserData in OutData
				NewGenericDto outputDto = reqData.getOutputGenericDto().using(NewGenericDto.OUTDATA);
				outputDto.addNode("/UserData");
				outputDto.add(resUsers);

				break;

			case 15:

				UserPilotDDTO[] userpilotDDTOs = (UserPilotDDTO[]) reqData.getInputGenericDto()
						.using("/KB-Message/Individual/InData/UserData").getArray(UserPilotDDTO.class);

				try {
					IDCUserPilot idcuserpilot = new DCUserPilot();
					idcuserpilot.crudGrid(userpilotDDTOs);
				} catch (NewBusinessException e) {
					// TODO Add proper catch handling
					e.printStackTrace();
				}

				// crudDAO cruddao = new crudDAO();
				// cruddao.excute(reqData);

				return reqData;

			case 17:

				UserPilotDDTO userpilotDDTO = (UserPilotDDTO) reqData.getInputGenericDto()
						.using("/KB-Message/Individual/InData/UserData").get("UserPilotDDTO");

				try {
					// Call DC
					IDCUserPilot idcuserpilot = new DCUserPilot();
					resUsers = idcuserpilot.getListDept(userpilotDDTO);
				} catch (NewBusinessException e) {
					// TODO Add proper catch handling
					e.printStackTrace();
				}

				NewGenericDto outputDto2 = reqData.getOutputGenericDto().using(NewGenericDto.OUTDATA);
				outputDto2.addNode("/UserData");
				outputDto2.add(resUsers);
				return reqData;
			// readDeptDAO readdeptdao = new readDeptDAO();
			// readdeptdao.excute(reqData);
			// break;

			default:
				break;

		}

		return reqData;
	}

}
