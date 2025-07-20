package com.kbstar.mbc.dc.reportdc;

import java.util.List;

import com.kbstar.ksa.das.ibatis.SqlMapper;
import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.report.WordReportUtil;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;

public class DCReportGen implements IDCReportGen {

	public void generateReport(ICommonDTO commonDto) throws BusinessException {
		try {
System.out.println("generateReport 1");			

			List resultList = (List) SqlMapper.getSqlMapClient().queryForList("DCRptMeta.getListReporting", commonDto.getParameterMap());
System.out.println("generateReport 2");
System.out.println("generateReport 3 : " + resultList);			
			String rtfTemplatePath = "C:/ReportTest/";
			String rtfReportPath = "C:/ReportTest/";
			WordReportUtil reportUtil = new WordReportUtil(rtfTemplatePath, rtfReportPath);
			reportUtil.makeReport(resultList, SqlMapper.getSqlMapClient());
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("D9005105", e.toString());
		}
	}
}
