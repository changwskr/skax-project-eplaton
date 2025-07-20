
package com.kbstar.mbc.as.usermgtas;

import java.math.BigDecimal;
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
import com.kbstar.mbc.dc.usermgtdc.Tree;

import com.kbstar.mbc.dc.usermgtdc.dto.TreeDDTO;

public class ASMBC75Z02 implements IApplicationService {

    protected IKesaLogger logger = KesaLogHelper.getBiz();

    public KBData execute(KBData reqData) throws BusinessException {

        IDCUser idcuser = null;
        List<Tree> resTrees = null;
        TreeDDTO treeDDTO = new TreeDDTO();

        String testString = null;
        int testInt = 0;
        Integer testInteger = 0;
        BigDecimal testDecimal = new BigDecimal(0);
        float testFloat = 0;
        double testDouble = 0;

        GenericDto input = reqData.getInputGenericDto().using(GenericDto.INDATA);
        GenericDto output = reqData.getOutputGenericDto().using(GenericDto.OUTDATA);

        Map<String, String> attrMap = input.getAttributeMap();

        logger.debug("testString = " + attrMap.get("testString"));
        logger.debug("testInt = " + attrMap.get("testInt"));
        logger.debug("testInteger = " + attrMap.get("testInteger"));
        logger.debug("testDecimal = " + attrMap.get("testDecimal"));
        logger.debug("testFloat = " + attrMap.get("testFloat"));
        logger.debug("testDouble = " + attrMap.get("testDouble"));
        logger.debug("nodeid = " + attrMap.get("nodeid"));

        // 전문의 Common 영역의 거래코드를 가져온다.
        String TransactionId = ApplicationContext.get(ApplicationContext.Key.StndTelgmRecvTranCd);
        String rsux = TransactionId.substring(8, 10);
        
        System.out.println("StndCicsTrncd :" + ApplicationContext.get(ApplicationContext.Key.StndCicsTrncd));
        System.out.println("StndIntnlStndTelgmLen :" + ApplicationContext.get(ApplicationContext.Key.StndIntnlStndTelgmLen));
        System.out.println("StndTranBaseYmd :" + ApplicationContext.get(ApplicationContext.Key.StndTranBaseYmd));
        System.out.println("StndGuIdNo :" + ApplicationContext.get(ApplicationContext.Key.StndGuIdNo));
        System.out.println("StndTelgmDmndDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndTelgmDmndDstcd));
        System.out.println("StndTelgmFmatDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndTelgmFmatDstcd));
        System.out.println("StndTelgmValdYmd :" + ApplicationContext.get(ApplicationContext.Key.StndTelgmValdYmd));
        //System.out.println("StndHdrSpareArea :" + ApplicationContext.get(ApplicationContext.Key.StndHdrSpareArea));
        System.out.println("StndGroupCoCd :" + ApplicationContext.get(ApplicationContext.Key.StndGroupCoCd));
        System.out.println("StndTelgmRecvTranCd :" + ApplicationContext.get(ApplicationContext.Key.StndTelgmRecvTranCd));
        System.out.println("StndPrcssRtdTranCd :" + ApplicationContext.get(ApplicationContext.Key.StndPrcssRtdTranCd));
        System.out.println("StndScrenNo :" + ApplicationContext.get(ApplicationContext.Key.StndScrenNo));
        System.out.println("StndOsidInstiCd :" + ApplicationContext.get(ApplicationContext.Key.StndOsidInstiCd));
        System.out.println("StndTranSerno :" + ApplicationContext.get(ApplicationContext.Key.StndTranSerno));
        System.out.println("StndInoPartlDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndInoPartlDstcd));
        System.out.println("StndCmpxTranDmndDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndCmpxTranDmndDstcd));
        System.out.println("StndSysOperEvirnDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndSysOperEvirnDstcd));
        System.out.println("StndItsmMoniTagetYn :" + ApplicationContext.get(ApplicationContext.Key.StndItsmMoniTagetYn));
        System.out.println("StndTranPtrnDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndTranPtrnDstcd));
        System.out.println("StndRbndTranYn :" + ApplicationContext.get(ApplicationContext.Key.StndRbndTranYn));
        System.out.println("StndTermlWaitRqstYn :" + ApplicationContext.get(ApplicationContext.Key.StndTermlWaitRqstYn));
        System.out.println("StndOgtranRstrYn :" + ApplicationContext.get(ApplicationContext.Key.StndOgtranRstrYn));
        System.out.println("StndOgtranGuIdNo :" + ApplicationContext.get(ApplicationContext.Key.StndOgtranGuIdNo));
        System.out.println("StndBnkCd :" + ApplicationContext.get(ApplicationContext.Key.StndBnkCd));
        System.out.println("StndTranBrncd :" + ApplicationContext.get(ApplicationContext.Key.StndTranBrncd));
        System.out.println("StndFeePrcssBrncd :" + ApplicationContext.get(ApplicationContext.Key.StndFeePrcssBrncd));
        System.out.println("StndRelayChnlDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndRelayChnlDstcd));
        System.out.println("StndChnlDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndChnlDstcd));
        //System.out.println("StndChnlDBzwkDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndChnlDBzwkDstcd));
        System.out.println("StndMdiaDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndMdiaDstcd));
        System.out.println("StndLangDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndLangDstcd));
        System.out.println("StndTrmno :" + ApplicationContext.get(ApplicationContext.Key.StndTrmno));
        System.out.println("StndUserEmpid :" + ApplicationContext.get(ApplicationContext.Key.StndUserEmpid));
        System.out.println("StndTermlOprtrno :" + ApplicationContext.get(ApplicationContext.Key.StndTermlOprtrno));
        System.out.println("StndTermlSpvsrNo :" + ApplicationContext.get(ApplicationContext.Key.StndTermlSpvsrNo));
        System.out.println("StndN1StSpvsrBrncd :" + ApplicationContext.get(ApplicationContext.Key.StndN1StSpvsrBrncd));
        System.out.println("StndN2NdSpvsrBrncd :" + ApplicationContext.get(ApplicationContext.Key.StndN2NdSpvsrBrncd));
        System.out.println("StndN2NdSpvsrTrmno :" + ApplicationContext.get(ApplicationContext.Key.StndN2NdSpvsrTrmno));
        System.out.println("StndInptMsgPtrnDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndInptMsgPtrnDstcd));
        System.out.println("StndInptMsgCtnnYn :" + ApplicationContext.get(ApplicationContext.Key.StndInptMsgCtnnYn));
        System.out.println("StndInptMsgSerno :" + ApplicationContext.get(ApplicationContext.Key.StndInptMsgSerno));
        System.out.println("StndInptMsgWritYms :" + ApplicationContext.get(ApplicationContext.Key.StndInptMsgWritYms));
        System.out.println("StndAthorFnshDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndAthorFnshDstcd));
        System.out.println("StndSpvsrAthorDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndSpvsrAthorDstcd));
        System.out.println("StndN1StSpvsrEmpid :" + ApplicationContext.get(ApplicationContext.Key.StndN1StSpvsrEmpid));
        System.out.println("StndN2NdSpvsrEmpid :" + ApplicationContext.get(ApplicationContext.Key.StndN2NdSpvsrEmpid));
        System.out.println("StndSpvsrAResnNoitm :" + ApplicationContext.get(ApplicationContext.Key.StndSpvsrAResnNoitm));
        System.out.println("StndSpvsrAthorResnCd :" + ApplicationContext.get(ApplicationContext.Key.StndSpvsrAthorResnCd));
        System.out.println("StndClsngAfYn :" + ApplicationContext.get(ApplicationContext.Key.StndClsngAfYn));
       // System.out.println("StndBnkbkTranYn :" + ApplicationContext.get(ApplicationContext.Key.StndBnkbkTranYn));
        System.out.println("StndLsdtTranYn :" + ApplicationContext.get(ApplicationContext.Key.StndLsdtTranYn));
        System.out.println("StndIdtrek :" + ApplicationContext.get(ApplicationContext.Key.StndIdtrek));
        System.out.println("StndBzoprDdDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndBzoprDdDstcd));
        System.out.println("StndSodBbrnPtrnDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndSodBbrnPtrnDstcd));
        System.out.println("StndSodUserPtrnDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndSodUserPtrnDstcd));
        System.out.println("StndInotAbilYn :" + ApplicationContext.get(ApplicationContext.Key.StndInotAbilYn));
        System.out.println("StndNoRtaUserYn :" + ApplicationContext.get(ApplicationContext.Key.StndNoRtaUserYn));
        System.out.println("StndTranDscnDmndYn :" + ApplicationContext.get(ApplicationContext.Key.StndTranDscnDmndYn));
        System.out.println("StndCallgPgmName :" + ApplicationContext.get(ApplicationContext.Key.StndCallgPgmName));
        System.out.println("StndRecvLuName :" + ApplicationContext.get(ApplicationContext.Key.StndRecvLuName));
        System.out.println("StndCnclDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndCnclDstcd));
        System.out.println("StndCnclPtrnDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndCnclPtrnDstcd));
        System.out.println("StndTranCcResnDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndTranCcResnDstcd));
        System.out.println("StndTranCcResnCtnt :" + ApplicationContext.get(ApplicationContext.Key.StndTranCcResnCtnt));
        System.out.println("StndOgtranYms :" + ApplicationContext.get(ApplicationContext.Key.StndOgtranYms));
        System.out.println("StndCnclRstrInfoCtnt :" + ApplicationContext.get(ApplicationContext.Key.StndCnclRstrInfoCtnt));
        System.out.println("StndDscnTranDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndDscnTranDstcd));
        System.out.println("StndIdiviDataEdtYn :" + ApplicationContext.get(ApplicationContext.Key.StndIdiviDataEdtYn));
        System.out.println("StndOpbrnCd :" + ApplicationContext.get(ApplicationContext.Key.StndOpbrnCd));
        //System.out.println("StndCncutIScopAddr :" + ApplicationContext.get(ApplicationContext.Key.StndCncutIScopAddr));
        System.out.println("StndOutptDPtrnDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndOutptDPtrnDstcd));
        System.out.println("StndOutptMsgPtrnDstcd :" + ApplicationContext.get(ApplicationContext.Key.StndOutptMsgPtrnDstcd));
        System.out.println("StndOutptMsgCtnnYn :" + ApplicationContext.get(ApplicationContext.Key.StndOutptMsgCtnnYn));
        System.out.println("StndOutptMsgSerno :" + ApplicationContext.get(ApplicationContext.Key.StndOutptMsgSerno));
        System.out.println("StndOutptMsgWritYms :" + ApplicationContext.get(ApplicationContext.Key.StndOutptMsgWritYms));
        System.out.println("StndUserPaNotacrdYn :" + ApplicationContext.get(ApplicationContext.Key.StndUserPaNotacrdYn));
        System.out.println("StndErrcd :" + ApplicationContext.get(ApplicationContext.Key.StndErrcd));
        System.out.println("StndTreatCd :" + ApplicationContext.get(ApplicationContext.Key.StndTreatCd));
        System.out.println("StndCncutDscnRqstYn :" + ApplicationContext.get(ApplicationContext.Key.StndCncutDscnRqstYn));
        //System.out.println("StndComSpareArea :" + ApplicationContext.get(ApplicationContext.Key.StndComSpareArea));
        
        // 거래코드의 R/S/U/X 에 따라 분기
        if (rsux != null && rsux.equals("R0")) {

            treeDDTO.setNodeid(attrMap.get("nodeid"));

            idcuser = new DCUser();
            resTrees = idcuser.getListTree(treeDDTO);

            reqData.getOutputGenericDto().using(GenericDto.OUTDATA).add(resTrees);

        }
        else if (rsux != null && rsux.equals("S0")) {

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    GenericDto treeout = output.addNode("treeGrid");
                    // 개별 DTO의 속성을 설정함

                    treeout.addAttribute("treeId", "10" + i + j);
                    treeout.addAttribute("treeNm", "data=트리노드level" + j + ";level=" + j);
                    treeout.addAttribute("treeCol1", "treeCol1");
                    treeout.addAttribute("treeCol2", "treeCol2");

                }
            }
        }
        return reqData;
    }

}
