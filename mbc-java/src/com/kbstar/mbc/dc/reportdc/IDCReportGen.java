package com.kbstar.mbc.dc.reportdc;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;

public interface IDCReportGen {

	public void generateReport(ICommonDTO commonDto) throws BusinessException;
}
