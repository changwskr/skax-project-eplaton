package com.kbstar.mbc.as.usermgtas;

import java.util.List;
import java.util.Map;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.infra.po.ApplicationContext;
import com.kbstar.ksa.infra.po.GenericDto;
import com.kbstar.ksa.infra.po.KBData;
import com.kbstar.ksa.logger.IKesaLogger;
import com.kbstar.ksa.logger.KesaLogHelper;
import com.kbstar.ksa.oltp.biz.IApplicationService;
import com.kbstar.mbc.dc.usermgtdc.DCUser;
import com.kbstar.mbc.dc.usermgtdc.IDCUser;
import com.kbstar.mbc.dc.usermgtdc.Page;
import com.kbstar.mbc.dc.usermgtdc.dto.PageDDTO;
import com.kbstar.mbc.pc.dto.UserPilotPDTO;

public class ASMBC75Z03 implements IApplicationService {

	protected IKesaLogger logger = KesaLogHelper.getBiz();

	public KBData execute(KBData reqData) throws BusinessException {

		IDCUser idcuser = null;
		PageDDTO pageDdto = new PageDDTO();
		List<Page> resPage = null;

		GenericDto outDto = reqData.getOutputGenericDto().using(
				GenericDto.OUTDATA);
		GenericDto inDto = reqData.getInputGenericDto()
				.using(GenericDto.INDATA);

		String TransactionId = ApplicationContext
				.get(ApplicationContext.Key.StndTelgmRecvTranCd);
		String rsux = TransactionId.substring(8, 10);

		// Transaction code R/S/U/X inquiry
		if (rsux != null && rsux.equals("R0")) {

			Map<String, String> attrMap = inDto.getAttributeMap();

			// Request page number, page line count to DDTO
			pageDdto.setDmndPageNo(attrMap.get("dmndPageNo"));
			pageDdto.setPageLineCnt(attrMap.get("pageLineCnt"));

			logger.debug("TotalLineCount = " + attrMap.get("totalLineCnt"));

			idcuser = new DCUser();

			// DC call
			resPage = idcuser.getListPage(pageDdto);

			// Output DDTO total line count and output line count setting
			if (resPage.size() > 0) {
				outDto.addAttribute("totalLineCnt", resPage.get(0)
						.getTotalLineCnt());
				outDto.addAttribute("outptLineCnt", resPage.get(0)
						.getOutptLineCnt());
			}

			outDto.addAttribute("dmndPageNo", attrMap.get("dmndPageNo"));

			// Page information list data output
			reqData.getOutputGenericDto().using(GenericDto.OUTDATA)
					.add(resPage);

		}

		return null;
	}

}
