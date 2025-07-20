package com.kbstar.mbc.as.commonas;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewKBData;
import com.kbstar.mbc.fc.foundation.bzcrudbus.business.as.ASComExec;

public class MBCComAS extends ASComExec {

	public NewKBData execute(NewKBData reqData) throws NewBusinessException {
		// @TransactionAttribute(timeout=100, xa="false",
		// type=TransactionAttribute.Type.JTA)
		return super.execute(reqData);
	}

}
