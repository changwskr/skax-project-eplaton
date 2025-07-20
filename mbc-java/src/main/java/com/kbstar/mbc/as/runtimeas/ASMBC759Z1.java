package com.kbstar.mbc.as.runtimeas;

import java.util.List;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewApplicationContext;
import com.kbstar.ksa.infra.po.NewGenericDto;
import com.kbstar.ksa.infra.po.NewKBData;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLoggerFactory;
import com.kbstar.ksa.oltp.biz.NewIApplicationService;
import com.kbstar.mbc.dc.runtimedc.DCResourceCopy;
import com.kbstar.mbc.dc.runtimedc.dto.ResourceCopyDDTO;

public class ASMBC759Z1 implements NewIApplicationService {

	protected NewIKesaLogger logger = NewKesaLoggerFactory.getLogger("log.ifrs.mbc");
	Process process = null;

	public NewKBData execute(NewKBData reqData) throws NewBusinessException {

		String tranCd = NewApplicationContext.get(NewApplicationContext.Key.StndTelgmRecvTranCd);
		String tranCdPost = tranCd.substring(8, 10);

		if (tranCdPost != null) {

			DCResourceCopy cdRsrcCopy = new DCResourceCopy();

			// Resource list inquiry
			if (tranCdPost.equals("S0")) {

				// Resource confirmation
				/*
				 * NewGenericDto subDto =
				 * reqData.getInputGenericDto().using("/KB-Message/Individual/InData/Params");
				 * Map<String, String> paramMap = subDto.getAttributeMap();
				 * String uapplDstcd = (String)paramMap.get("uapplDstcd");
				 * String cmpoIdnfr = (String)paramMap.get("cmpoIdnfr");
				 * String fileIdnfr = (String)paramMap.get("fileIdnfr");
				 * String kywrCtnt = (String)paramMap.get("kywrCtnt");
				 */
				ResourceCopyDDTO rsrcCopyDDTO = reqData.getInputGenericDto().using(NewGenericDto.INDATA + "/Params")
						.get(ResourceCopyDDTO.class);
				List<ResourceCopyDDTO> rsrcCopyDDTOs = cdRsrcCopy.getListResourceInfo(rsrcCopyDDTO);

				reqData.getOutputGenericDto().using(NewGenericDto.OUTDATA).addNode("/PageGridGroup").add(rsrcCopyDDTOs);
			}
			// Resource copy processing
			else if (tranCdPost.equals("R0")) {

				ResourceCopyDDTO[] rsrcCopyDDTOs = (ResourceCopyDDTO[]) reqData.getInputGenericDto()
						.using(NewGenericDto.INDATA + "/PageGridGroup").getArray(ResourceCopyDDTO.class);

				cdRsrcCopy.copyResource(rsrcCopyDDTOs);
			}
		}

		return reqData;
	}

}
