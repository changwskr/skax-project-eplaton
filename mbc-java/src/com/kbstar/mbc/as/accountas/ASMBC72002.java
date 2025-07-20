package com.kbstar.mbc.as.accountas;

import java.util.List;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.infra.po.GenericDto;
import com.kbstar.ksa.infra.po.KBData;
import com.kbstar.ksa.logger.IKesaLogger;
import com.kbstar.ksa.logger.KesaLogHelper;
import com.kbstar.ksa.oltp.biz.IApplicationService;
import com.kbstar.mbc.pc.accountpc.PCAccount;
import com.kbstar.mbc.pc.dto.AccountPDTO;

/**
 * 계정 목록 조회 Application Service
 * 
 * @author KBSTAR
 * @version 1.0.0
 */
public class ASMBC72002 implements IApplicationService {

	protected IKesaLogger logger = KesaLogHelper.getBiz();

	public KBData execute(KBData reqData) throws BusinessException {
		// TODO 코드 구현 및 메서드 추가
		AccountPDTO accountPDTO = reqData.getInputGenericDto().using(GenericDto.INDATA).get(AccountPDTO.class);

		List<AccountPDTO> resAccountPDTOs = new PCAccount().getListAccount(accountPDTO);

		reqData.getOutputGenericDto().using(GenericDto.OUTDATA).add(resAccountPDTOs);

		return reqData;
	}

}
