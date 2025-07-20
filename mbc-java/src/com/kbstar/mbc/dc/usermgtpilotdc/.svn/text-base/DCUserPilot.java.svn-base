package com.kbstar.mbc.dc.usermgtpilotdc;

import java.util.List;

import com.kbstar.ksa.das.PersistenceException;
import com.kbstar.ksa.das.RecordNotFoundException;
import com.kbstar.ksa.das.ibatis.SqlMapper;
import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.exception.FrameworkException;
import com.kbstar.ksa.util.ObjectUtil;
import com.kbstar.mbc.dc.usermgtpilotdc.dto.UserPilotDDTO;


public class DCUserPilot implements IDCUserPilot {

	public List<UserPilot> getListUser(UserPilotDDTO userpilotDDTO) throws BusinessException{
		System.out.println("##########################In the getListUser process####################");
		
		try{
			List<UserPilot> UserList = SqlMapper.getSqlMapClient().queryForList("userpilot.getListUser", ObjectUtil.copyForClass(UserPilot.class, userpilotDDTO));
			return ObjectUtil.copyForList(UserPilot.class, UserList);
		} catch (RecordNotFoundException e) {
			throw new BusinessException("B0000001", "processCode", e);
		} catch (PersistenceException e) {
			throw new BusinessException("B0000002", "processCode", e);
		} catch (FrameworkException e) {
			throw new BusinessException("B0000002", "processCode", e);
		}
	}
	
public void crudGrid(UserPilotDDTO[] userpilotDDTOs) throws BusinessException {
	System.out.println("##########################In the crudGRID process####################");
		
		try {
			
			int i;
			
			for(i = 0 ; i < userpilotDDTOs.length; i++){
				if(userpilotDDTOs[i].getCrud().equals("c")){
					System.out.println("##########################In the create process####################");
					System.out.println(userpilotDDTOs[i].getCrud());
					SqlMapper.getSqlMapClient()
					.insert("userpilot.createUserList", ObjectUtil.copyForClass(UserPilot.class, userpilotDDTOs[i]));
					
				}else if(userpilotDDTOs[i].getCrud().equals("u")){
										System.out.println("##########################In the update process####################");
					System.out.println(userpilotDDTOs[i].getCrud());
					SqlMapper.getSqlMapClient()
					.update("userpilot.updateUser", ObjectUtil.copyForClass(UserPilot.class, userpilotDDTOs[i]));
					
				}else if(userpilotDDTOs[i].getCrud().equals("d")){
					System.out.println("##########################In the delete process####################");
					System.out.println(userpilotDDTOs[i].getCrud());
					SqlMapper.getSqlMapClient()
					.update("userpilot.deleteUser", ObjectUtil.copyForClass(UserPilot.class, userpilotDDTOs[i]));
					
				}
							
			}
		
		} catch (RecordNotFoundException e) {
			throw new BusinessException("B0000001", "processCode", e);
		} catch (PersistenceException e) {
			throw new BusinessException("B0000002", "processCode", e);
		} catch (FrameworkException e) {
			throw new BusinessException("B0000002", "processCode", e);
		}
	}


public List<UserPilot> getListDept(UserPilotDDTO userpilotDDTO) throws BusinessException{
	System.out.println("##########################In the getListDept process####################");
	
	try{
		return SqlMapper.getSqlMapClient().queryForList("userpilot.getListDept", ObjectUtil.copyForClass(UserPilot.class, userpilotDDTO));
	} catch (RecordNotFoundException e) {
		throw new BusinessException("B0000001", "processCode", e);
	} catch (PersistenceException e) {
		throw new BusinessException("B0000002", "processCode", e);
	} catch (FrameworkException e) {
		throw new BusinessException("B0000002", "processCode", e);
	}
}
}
