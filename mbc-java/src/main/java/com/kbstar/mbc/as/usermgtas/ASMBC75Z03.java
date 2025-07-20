package com.kbstar.mbc.as.usermgtas;

import java.util.List;
import java.util.Map;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewApplicationContext;
import com.kbstar.ksa.infra.po.NewGenericDto;
import com.kbstar.ksa.infra.po.NewKBData;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.oltp.biz.NewIApplicationService;
import com.kbstar.mbc.dc.usermgtdc.IDCUser;
import com.kbstar.mbc.dc.usermgtdc.Page;
import com.kbstar.mbc.dc.usermgtdc.dto.PageDDTO;
import com.kbstar.mbc.pc.dto.UserPilotPDTO;

public class ASMBC75Z03 implements NewIApplicationService {

	protected NewIKesaLogger logger = NewKesaLogHelper.getBiz();

	public NewKBData execute(NewKBData reqData) throws NewBusinessException {

		IDCUser idcuser = null;
		PageDDTO pageDdto = new PageDDTO();
		List<Page> resPage = null;

		NewGenericDto outDto = reqData.getOutputGenericDto().using(
				NewGenericDto.OUTDATA);
		NewGenericDto inDto = reqData.getInputGenericDto()
				.using(NewGenericDto.INDATA);

		String TransactionId = (String) NewApplicationContext
				.get(NewApplicationContext.StndTelgmRecvTranCd);
		String rsux = TransactionId.substring(8, 10);

		// Transaction code R/S/U/X inquiry
		if (rsux != null && rsux.equals("R0")) {

			Map<String, Object> attrMap = inDto.getAttributeMap();

			// Request page number, page line count to DDTO
			pageDdto.setDmndPageNo((String) attrMap.get("dmndPageNo"));
			pageDdto.setPageLineCnt((String) attrMap.get("pageLineCnt"));

			logger.debug("TotalLineCount = " + attrMap.get("totalLineCnt"));

			idcuser = new com.kbstar.mbc.dc.usermgtdc.DCUser();

			// DC call
			try {
				resPage = idcuser.getListPage(pageDdto);
			} catch (Exception e) {
				throw new NewBusinessException("getListUser", "B0000001", e);
			}

			// Output DDTO total line count and output line count setting
			if (resPage.size() > 0) {
				outDto.addAttribute("totalLineCnt", String.valueOf(resPage.get(0)
						.getTotalLineCnt()));
				outDto.addAttribute("outptLineCnt", String.valueOf(resPage.get(0)
						.getOutptLineCnt()));
			}

			outDto.addAttribute("dmndPageNo", (String) attrMap.get("dmndPageNo"));

			// Page information list data output
			reqData.getOutputGenericDto().using(NewGenericDto.OUTDATA)
					.add(resPage);

		}

		return reqData;
	}

}
