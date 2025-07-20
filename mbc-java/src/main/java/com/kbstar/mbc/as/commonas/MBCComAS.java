package com.kbstar.mbc.as.commonas;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.infra.po.KBData;
import com.kbstar.mbc.fc.foundation.bzcrudbus.business.as.ASComExec;

public class MBCComAS extends ASComExec{

	public KBData execute(KBData reqData) throws BusinessException {
		//@TransactionAttribute(timeout=100, xa="false", type=TransactionAttribute.Type.JTA)
		return super.execute(reqData);
	}
	
}
