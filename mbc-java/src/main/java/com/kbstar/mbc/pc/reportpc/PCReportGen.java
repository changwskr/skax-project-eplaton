/**
 * 
 */
package com.kbstar.mbc.pc.reportpc;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.dc.reportdc.DCReportGen;
import com.kbstar.mbc.dc.reportdc.IDCReportGen;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;

/**
 * @author S001849
 *
 */
public class PCReportGen {

	public void generateReport(ICommonDTO commonDto) throws NewBusinessException {
		// KBData reqData = commonDto.getKbData();

		IDCReportGen dcReportGen = new DCReportGen();

		dcReportGen.generateReport(commonDto);

	}

}
