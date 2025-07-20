package com.kbstar.mbc.as.accountas;

import java.util.List;

import com.kbstar.mbc.dc.accountdc.IDCAccount;
import com.kbstar.mbc.dc.accountdc.Account;
import com.kbstar.mbc.dc.accountdc.dto.AccountDDTO;
import com.kbstar.mbc.pc.accountpc.IPCAccount;
import com.kbstar.mbc.pc.accountpc.PCAccount;
import com.kbstar.mbc.pc.dto.AccountPDTO;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewGenericDto;
import com.kbstar.ksa.infra.po.NewKBData;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.oltp.biz.NewIApplicationService;

/**
 * 계정 목록 조회 Application Service
 * 
 * @author KBSTAR
 * @version 1.0.0
 */
public class ASMBC72002 implements NewIApplicationService {

	protected NewIKesaLogger logger = NewKesaLogHelper.getBiz();

	public NewKBData execute(NewKBData reqData) throws NewBusinessException {
		// TODO 코드 구현 및 메서드 추가
		AccountPDTO accountPDTO = (AccountPDTO) reqData.getInputGenericDto().using(NewGenericDto.INDATA)
				.get("AccountPDTO");

		List<AccountPDTO> resAccountPDTOs = new PCAccount().getListAccount(accountPDTO);

		reqData.getOutputGenericDto().using(NewGenericDto.OUTDATA).add(resAccountPDTOs);

		return reqData;
	}

}
