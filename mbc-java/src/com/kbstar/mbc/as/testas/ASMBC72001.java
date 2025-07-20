package com.kbstar.mbc.as.testas;

import java.util.Iterator;
import java.util.List;

import com.kbstar.kji.bc.commonutilbc.BCRate;
import com.kbstar.kji.bc.commonutilbc.dto.MaktExrtBDTO;
import com.kbstar.kji.bc.commonutilbc.dto.MaktExrtListBDTO;
import com.kbstar.kji.oltp.transaction.impl.KjiCommonAreaDTO;
import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.infra.po.ApplicationContext;
import com.kbstar.ksa.infra.po.GenericDto;
import com.kbstar.ksa.infra.po.KBData;
import com.kbstar.ksa.logger.IKesaLogger;
import com.kbstar.ksa.logger.KesaLoggerFactory;
import com.kbstar.ksa.oltp.biz.IApplicationService;

public class ASMBC72001 implements IApplicationService {

	protected IKesaLogger logger = KesaLoggerFactory.getLogger("log.ifrs.mbc");

	public KBData execute(KBData reqData) throws BusinessException {

		KjiCommonAreaDTO kjiCommonArea = new KjiCommonAreaDTO();
		ApplicationContext.set("kjiCommonArea", kjiCommonArea);

		MaktExrtBDTO reqMaktExrtBDTO = new MaktExrtBDTO();

		// 파라메터 설정
		reqMaktExrtBDTO.setInGroupCoCd("001");
		reqMaktExrtBDTO.setInDstcd("1");
		reqMaktExrtBDTO.setInBaseYmd("20081214");
		reqMaktExrtBDTO.setInCncycd("USD");
		reqMaktExrtBDTO.setInExrtAplyNth(1);

		MaktExrtBDTO resMaktExrtBDTO = (new BCRate()).getMarketExchangeRate(reqMaktExrtBDTO);

		String acno = "#";
		
		// 4.공통util 프로그램 결과값 가져오기
		if ("07".equals(resMaktExrtBDTO.getRtnStat())) {

			// 1회차 환율 고시 미완료 경우의 처리를 수행한다.

		} else {

			// 시장환율List
			List<MaktExrtListBDTO> maktExrtListBDTOs = resMaktExrtBDTO.getOutMaktExrtList();

			for (Iterator iter = maktExrtListBDTOs.iterator(); iter.hasNext();) {
				MaktExrtListBDTO maktExrtListBDTO = (MaktExrtListBDTO) iter.next();
				acno = maktExrtListBDTO.getOutCncycd();

			}

		}

		/*
		 * MgtNoNbringBDTO reqMgtNoNbringBDTO = new MgtNoNbringBDTO();
		 * 
		 * logger.debug("ASMBC72001 #1");
		 *  // 가수가지급 환 수신번호의 파라메터 설정 reqMgtNoNbringBDTO.setInGroupCoCd("001");
		 * reqMgtNoNbringBDTO.setInUapplDstcd("KJC");
		 * reqMgtNoNbringBDTO.setInNbringIdnfiCd("003");
		 * reqMgtNoNbringBDTO.setInBrncd("4004");
		 * 
		 * logger.debug("ASMBC72001 #2");
		 * 
		 * MgtNoNbringBDTO resMgtNoNbringBDTO = (new
		 * BCNumbering()).getMgtNoNumbering (reqMgtNoNbringBDTO);
		 * 
		 * logger.debug("ASMBC72001 #3");
		 * 
		 * String acno = resMgtNoNbringBDTO.getOutEdtMgtNo();
		 * 
		 * logger.debug("ASMBC72001 #4");
		 */
		reqData.getOutputGenericDto().using(GenericDto.OUTDATA).addNode("TEST");

		reqData.getOutputGenericDto().using(GenericDto.OUTDATA + "/TEST").addAttribute("result", "SUCCESS!! (" + acno + ")");

		logger.debug("TEST SUCCESS!!! (" + acno + ")");

		return reqData;
	}

}
