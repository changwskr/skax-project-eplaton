package com.kbstar.mbc.as.runtimeas;

import java.util.List;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.infra.po.ApplicationContext;
import com.kbstar.ksa.infra.po.GenericDto;
import com.kbstar.ksa.infra.po.KBData;
import com.kbstar.ksa.logger.IKesaLogger;
import com.kbstar.ksa.logger.KesaLoggerFactory;
import com.kbstar.ksa.oltp.biz.IApplicationService;
import com.kbstar.mbc.dc.runtimedc.DCResourceCopy;
import com.kbstar.mbc.dc.runtimedc.dto.ResourceCopyDDTO;

public class ASMBC759Z1 implements IApplicationService {
	
	protected IKesaLogger logger = KesaLoggerFactory.getLogger("log.ifrs.mbc");
	Process process = null;
	

	public KBData execute(KBData reqData) throws BusinessException {
		
		String tranCd = ApplicationContext.get(ApplicationContext.Key.StndTelgmRecvTranCd);
		String tranCdPost = tranCd.substring(8, 10);
		
		if (tranCdPost != null) {
			
			DCResourceCopy cdRsrcCopy = new DCResourceCopy();
			
			// 파일 리스트 조회
			if(tranCdPost.equals("S0")) {
				
				// 조건 확인
				/*
				GenericDto subDto = reqData.getInputGenericDto().using("/KB-Message/Individual/InData/Params");
				Map<String, String> paramMap = subDto.getAttributeMap();
				String uapplDstcd = (String)paramMap.get("uapplDstcd");
				String cmpoIdnfr = (String)paramMap.get("cmpoIdnfr");
				String fileIdnfr = (String)paramMap.get("fileIdnfr");
				String kywrCtnt = (String)paramMap.get("kywrCtnt");
				*/
				ResourceCopyDDTO rsrcCopyDDTO = reqData.getInputGenericDto().using(GenericDto.INDATA + "/Params").get(ResourceCopyDDTO.class);
				List<ResourceCopyDDTO> rsrcCopyDDTOs = cdRsrcCopy.getListResourceInfo(rsrcCopyDDTO);
				
				reqData.getOutputGenericDto().using(GenericDto.OUTDATA).addNode("/PageGridGroup").add(rsrcCopyDDTOs);
			}
			// 파일 복사 처리
			else if(tranCdPost.equals("R0")) {
				
				ResourceCopyDDTO [] rsrcCopyDDTOs = (ResourceCopyDDTO[])reqData.getInputGenericDto().using(GenericDto.INDATA + "/PageGridGroup").getArray(ResourceCopyDDTO.class);
				
				cdRsrcCopy.copyResource(rsrcCopyDDTOs);
			}
		}
		
		return reqData;
	}
	
}


