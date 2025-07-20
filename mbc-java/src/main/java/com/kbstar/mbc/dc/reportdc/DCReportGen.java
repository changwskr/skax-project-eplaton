package com.kbstar.mbc.dc.reportdc;

import java.util.List;

import com.kbstar.mbc.dc.reportdc.IDCReportGen;
import com.kbstar.ksa.das.ibatis.NewSqlMapper;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.report.WordReportUtil;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;

public class DCReportGen implements IDCReportGen {

	public void generateReport(ICommonDTO commonDto) throws NewBusinessException {
		try {
			System.out.println("generateReport 1");

			List resultList = (List) NewSqlMapper.getSqlMapClient().queryForList("DCRptMeta.getListReporting",
					commonDto.getParameterMap());
			System.out.println("generateReport 2");
			System.out.println("generateReport 3 : " + resultList);
			String rtfTemplatePath = "C:/ReportTest/";
			String rtfReportPath = "C:/ReportTest/";
			WordReportUtil reportUtil = new WordReportUtil(rtfTemplatePath, rtfReportPath);
			reportUtil.makeReport(resultList, NewSqlMapper.getSqlMapClient());

		} catch (Exception e) {
			e.printStackTrace();
			throw new NewBusinessException("D9005105", e.toString());
		}
	}
}
