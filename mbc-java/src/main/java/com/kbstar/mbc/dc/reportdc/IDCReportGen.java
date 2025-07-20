package com.kbstar.mbc.dc.reportdc;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;

public interface IDCReportGen {

	public void generateReport(ICommonDTO commonDto) throws NewBusinessException;
}
