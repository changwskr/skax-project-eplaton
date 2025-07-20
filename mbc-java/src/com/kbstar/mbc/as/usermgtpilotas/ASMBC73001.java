package com.kbstar.mbc.as.usermgtpilotas;

import java.util.List;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.infra.po.GenericDto;
import com.kbstar.ksa.infra.po.KBData;
import com.kbstar.ksa.logger.IKesaLogger;
import com.kbstar.ksa.logger.KesaLogHelper;
import com.kbstar.ksa.oltp.biz.IApplicationService;
import com.kbstar.mbc.dc.usermgtpilotdc.DCUserPilot;
import com.kbstar.mbc.dc.usermgtpilotdc.IDCUserPilot;
import com.kbstar.mbc.dc.usermgtpilotdc.UserPilot;
import com.kbstar.mbc.dc.usermgtpilotdc.dto.UserPilotDDTO;
import com.kbstar.mbc.pc.dto.UserPilotPDTO;
import com.kbstar.mbc.pc.usermanagementpilotpc.IPCUserManagementPilot;
import com.kbstar.mbc.pc.usermanagementpilotpc.PCUserManagementPilot;


public class ASMBC73001 implements IApplicationService {

	protected IKesaLogger logger = KesaLogHelper.getBiz();
	
	public KBData execute(KBData reqData) throws BusinessException {

		if(logger.isDebugEnabled())logger.debug(this.getClass().getName() + ", log test입니다.");
		
		
		//KBData 의 트랜잭션 코드 txcd 읽는다
		GenericDto  gdto  =  reqData.getInputGenericDto().using("/KB-Message/Individual/DataHeader");
		
		int  txcd = gdto.getInt("@txcd") ;
		
		System.out.println("###################### Transaction Code : " + txcd +"########################");
		
		List<UserPilot> resUsers = null;
		
		
		//txcd에 따라 Delegation
		switch (txcd) {
		
			case 12:

				UserPilotPDTO userpilotPDTO = new UserPilotPDTO();
				userpilotPDTO = reqData.getInputGenericDto().using(GenericDto.INDATA).get(UserPilotPDTO.class);			
				
				
				try {
					// PC 호출
					IPCUserManagementPilot ipcusermgtpilot = new PCUserManagementPilot();
					resUsers = ipcusermgtpilot.getListUser(userpilotPDTO);
				} catch (BusinessException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
				//OutData 에 /UserData 노드에 User element 추가
				reqData.getOutputGenericDto().using(GenericDto.OUTDATA).addNode("/UserData").add(resUsers);
				
	
				break;
				
			case 15:
				

				UserPilotDDTO [] userpilotDDTOs = (UserPilotDDTO[])reqData.getInputGenericDto()
				.using("/KB-Message/Individual/InData/UserData").getArray(UserPilotDDTO.class);
				
				try {
					IDCUserPilot idcuserpilot  = new DCUserPilot();
					idcuserpilot.crudGrid(userpilotDDTOs);
				} catch (BusinessException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
				
				
				//crudDAO cruddao = new crudDAO();
				//cruddao.excute(reqData);
				
				return reqData;
								
			case 17:
				

				UserPilotDDTO userpilotDDTO = (UserPilotDDTO)reqData.getInputGenericDto()
				.using("/KB-Message/Individual/InData/UserData").get(UserPilotDDTO.class);
				
				try {
					//DC 호출
					IDCUserPilot idcuserpilot = new DCUserPilot();
					resUsers = idcuserpilot.getListDept(userpilotDDTO);
				} catch (BusinessException e) {
					// TODO 자동 생성된 catch 블록
					e.printStackTrace();
				}
				
				reqData.getOutputGenericDto().using(GenericDto.OUTDATA).addNode("/UserData").add(resUsers);
				return reqData;
				//readDeptDAO readdeptdao = new readDeptDAO();
			//	readdeptdao.excute(reqData);
				//break;

			default:
				break;
		
		}
		
		return reqData;
	}

}
