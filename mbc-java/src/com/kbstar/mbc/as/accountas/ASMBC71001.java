package com.kbstar.mbc.as.accountas;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.infra.po.GenericDto;
import com.kbstar.ksa.infra.po.KBData;
import com.kbstar.ksa.logger.IKesaLogger;
import com.kbstar.ksa.logger.KesaLogHelper;
import com.kbstar.ksa.oltp.biz.IApplicationService;
import com.kbstar.mbc.pc.accountpc.PCAccount;
import com.kbstar.mbc.pc.dto.AccountPDTO;

/**
 * 계정 생성 Application Service
 * 
 * @author KBSTAR
 * @version 1.0.0
 */
public class ASMBC71001 implements IApplicationService {

	protected IKesaLogger logger = KesaLogHelper.getBiz();

	public KBData execute(KBData reqData) throws BusinessException {
		// TODO 코드 구현 및 메서드 추가

		AccountPDTO accountPDTO = (AccountPDTO) reqData.getInputGenericDto().using(GenericDto.INDATA)
				.get(AccountPDTO.class);

		new PCAccount().createAccount(accountPDTO);
		return null;
	}

}
