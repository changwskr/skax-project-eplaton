
package com.kbstar.mbc.as.usermgtas;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewApplicationContext;
import com.kbstar.ksa.infra.po.NewGenericDto;
import com.kbstar.ksa.infra.po.NewKBData;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.oltp.biz.NewIApplicationService;
import com.kbstar.mbc.dc.usermgtdc.DCUser;
import com.kbstar.mbc.dc.usermgtdc.IDCUser;
import com.kbstar.mbc.dc.usermgtdc.Tree;

import com.kbstar.mbc.dc.usermgtdc.dto.TreeDDTO;

public class ASMBC75Z02 implements NewIApplicationService {

        protected NewIKesaLogger logger = NewKesaLogHelper.getBiz();

        public NewKBData execute(NewKBData reqData) throws NewBusinessException {

                IDCUser idcuser = null;
                List<Tree> resTrees = null;
                TreeDDTO treeDDTO = new TreeDDTO();

                String testString = null;
                int testInt = 0;
                Integer testInteger = 0;
                BigDecimal testDecimal = new BigDecimal(0);
                float testFloat = 0;
                double testDouble = 0;

                NewGenericDto input = reqData.getInputGenericDto().using(NewGenericDto.INDATA);
                NewGenericDto output = reqData.getOutputGenericDto().using(NewGenericDto.OUTDATA);

                Map<String, String> attrMap = input.getAttributeMap();

                logger.debug("testString = " + attrMap.get("testString"));
                logger.debug("testInt = " + attrMap.get("testInt"));
                logger.debug("testInteger = " + attrMap.get("testInteger"));
                logger.debug("testDecimal = " + attrMap.get("testDecimal"));
                logger.debug("testFloat = " + attrMap.get("testFloat"));
                logger.debug("testDouble = " + attrMap.get("testDouble"));
                logger.debug("nodeid = " + attrMap.get("nodeid"));

                // Get Common transaction code from ApplicationContext
                String TransactionId = NewApplicationContext.get(NewApplicationContext.Key.StndTelgmRecvTranCd);
                String rsux = TransactionId.substring(8, 10);

                System.out.println(
                                "StndCicsTrncd :" + NewApplicationContext.get(NewApplicationContext.Key.StndCicsTrncd));
                System.out.println(
                                "StndIntnlStndTelgmLen :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndIntnlStndTelgmLen));
                System.out.println(
                                "StndTranBaseYmd :"
                                                + NewApplicationContext.get(NewApplicationContext.Key.StndTranBaseYmd));
                System.out.println("StndGuIdNo :" + NewApplicationContext.get(NewApplicationContext.Key.StndGuIdNo));
                System.out.println("StndTelgmDmndDstcd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndTelgmDmndDstcd));
                System.out.println("StndTelgmFmatDstcd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndTelgmFmatDstcd));
                System.out.println(
                                "StndTelgmValdYmd :" + NewApplicationContext
                                                .get(NewApplicationContext.Key.StndTelgmValdYmd));
                // System.out.println("StndHdrSpareArea :" +
                // NewApplicationContext.get(NewApplicationContext.Key.StndHdrSpareArea));
                System.out.println(
                                "StndGroupCoCd :" + NewApplicationContext.get(NewApplicationContext.Key.StndGroupCoCd));
                System.out
                                .println("StndTelgmRecvTranCd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndTelgmRecvTranCd));
                System.out.println("StndPrcssRtdTranCd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndPrcssRtdTranCd));
                System.out.println("StndScrenNo :" + NewApplicationContext.get(NewApplicationContext.Key.StndScrenNo));
                System.out.println(
                                "StndOsidInstiCd :"
                                                + NewApplicationContext.get(NewApplicationContext.Key.StndOsidInstiCd));
                System.out.println(
                                "StndTranSerno :" + NewApplicationContext.get(NewApplicationContext.Key.StndTranSerno));
                System.out.println("StndInoPartlDstcd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndInoPartlDstcd));
                System.out.println(
                                "StndCmpxTranDmndDstcd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndCmpxTranDmndDstcd));
                System.out.println(
                                "StndSysOperEvirnDstcd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndSysOperEvirnDstcd));
                System.out
                                .println("StndItsmMoniTagetYn :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndItsmMoniTagetYn));
                System.out.println("StndTranPtrnDstcd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndTranPtrnDstcd));
                System.out.println("StndRbndTranYn :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndRbndTranYn));
                System.out
                                .println("StndTermlWaitRqstYn :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndTermlWaitRqstYn));
                System.out.println(
                                "StndOgtranRstrYn :" + NewApplicationContext
                                                .get(NewApplicationContext.Key.StndOgtranRstrYn));
                System.out.println(
                                "StndOgtranGuIdNo :" + NewApplicationContext
                                                .get(NewApplicationContext.Key.StndOgtranGuIdNo));
                System.out.println("StndBnkCd :" + NewApplicationContext.get(NewApplicationContext.Key.StndBnkCd));
                System.out.println(
                                "StndTranBrncd :" + NewApplicationContext.get(NewApplicationContext.Key.StndTranBrncd));
                System.out.println("StndFeePrcssBrncd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndFeePrcssBrncd));
                System.out.println("StndRelayChnlDstcd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndRelayChnlDstcd));
                System.out.println(
                                "StndChnlDstcd :" + NewApplicationContext.get(NewApplicationContext.Key.StndChnlDstcd));
                // System.out.println("StndChnlDBzwkDstcd :" +
                // NewApplicationContext.get(NewApplicationContext.Key.StndChnlDBzwkDstcd));
                System.out.println(
                                "StndMdiaDstcd :" + NewApplicationContext.get(NewApplicationContext.Key.StndMdiaDstcd));
                System.out.println(
                                "StndLangDstcd :" + NewApplicationContext.get(NewApplicationContext.Key.StndLangDstcd));
                System.out.println("StndTrmno :" + NewApplicationContext.get(NewApplicationContext.Key.StndTrmno));
                System.out.println(
                                "StndUserEmpid :" + NewApplicationContext.get(NewApplicationContext.Key.StndUserEmpid));
                System.out.println(
                                "StndTermlOprtrno :" + NewApplicationContext
                                                .get(NewApplicationContext.Key.StndTermlOprtrno));
                System.out.println(
                                "StndTermlSpvsrNo :" + NewApplicationContext
                                                .get(NewApplicationContext.Key.StndTermlSpvsrNo));
                System.out.println("StndN1StSpvsrBrncd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndN1StSpvsrBrncd));
                System.out.println("StndN2NdSpvsrBrncd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndN2NdSpvsrBrncd));
                System.out.println("StndN2NdSpvsrTrmno :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndN2NdSpvsrTrmno));
                System.out.println(
                                "StndInptMsgPtrnDstcd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndInptMsgPtrnDstcd));
                System.out.println("StndInptMsgCtnnYn :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndInptMsgCtnnYn));
                System.out.println(
                                "StndInptMsgSerno :" + NewApplicationContext
                                                .get(NewApplicationContext.Key.StndInptMsgSerno));
                System.out.println("StndInptMsgWritYms :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndInptMsgWritYms));
                System.out.println("StndAthorFnshDstcd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndAthorFnshDstcd));
                System.out
                                .println("StndSpvsrAthorDstcd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndSpvsrAthorDstcd));
                System.out.println("StndN1StSpvsrEmpid :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndN1StSpvsrEmpid));
                System.out.println("StndN2NdSpvsrEmpid :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndN2NdSpvsrEmpid));
                System.out
                                .println("StndSpvsrAResnNoitm :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndSpvsrAResnNoitm));
                System.out.println(
                                "StndSpvsrAthorResnCd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndSpvsrAthorResnCd));
                System.out.println(
                                "StndClsngAfYn :" + NewApplicationContext.get(NewApplicationContext.Key.StndClsngAfYn));
                // System.out.println("StndBnkbkTranYn :" +
                // NewApplicationContext.get(NewApplicationContext.Key.StndBnkbkTranYn));
                System.out.println("StndLsdtTranYn :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndLsdtTranYn));
                System.out.println("StndIdtrek :" + NewApplicationContext.get(NewApplicationContext.Key.StndIdtrek));
                System.out.println(
                                "StndBzoprDdDstcd :" + NewApplicationContext
                                                .get(NewApplicationContext.Key.StndBzoprDdDstcd));
                System.out.println(
                                "StndSodBbrnPtrnDstcd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndSodBbrnPtrnDstcd));
                System.out.println(
                                "StndSodUserPtrnDstcd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndSodUserPtrnDstcd));
                System.out.println("StndInotAbilYn :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndInotAbilYn));
                System.out.println(
                                "StndNoRtaUserYn :"
                                                + NewApplicationContext.get(NewApplicationContext.Key.StndNoRtaUserYn));
                System.out.println("StndTranDscnDmndYn :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndTranDscnDmndYn));
                System.out.println(
                                "StndCallgPgmName :" + NewApplicationContext
                                                .get(NewApplicationContext.Key.StndCallgPgmName));
                System.out.println("StndRecvLuName :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndRecvLuName));
                System.out.println(
                                "StndCnclDstcd :" + NewApplicationContext.get(NewApplicationContext.Key.StndCnclDstcd));
                System.out.println("StndCnclPtrnDstcd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndCnclPtrnDstcd));
                System.out
                                .println("StndTranCcResnDstcd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndTranCcResnDstcd));
                System.out.println("StndTranCcResnCtnt :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndTranCcResnCtnt));
                System.out.println(
                                "StndOgtranYms :" + NewApplicationContext.get(NewApplicationContext.Key.StndOgtranYms));
                System.out.println(
                                "StndCnclRstrInfoCtnt :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndCnclRstrInfoCtnt));
                System.out.println("StndDscnTranDstcd :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndDscnTranDstcd));
                System.out.println("StndIdiviDataEdtYn :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndIdiviDataEdtYn));
                System.out.println("StndOpbrnCd :" + NewApplicationContext.get(NewApplicationContext.Key.StndOpbrnCd));
                // System.out.println("StndCncutIScopAddr :" +
                // NewApplicationContext.get(NewApplicationContext.Key.StndCncutIScopAddr));
                System.out
                                .println("StndOutptDPtrnDstcd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndOutptDPtrnDstcd));
                System.out.println(
                                "StndOutptMsgPtrnDstcd :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndOutptMsgPtrnDstcd));
                System.out.println("StndOutptMsgCtnnYn :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndOutptMsgCtnnYn));
                System.out.println("StndOutptMsgSerno :"
                                + NewApplicationContext.get(NewApplicationContext.Key.StndOutptMsgSerno));
                System.out
                                .println("StndOutptMsgWritYms :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndOutptMsgWritYms));
                System.out
                                .println("StndUserPaNotacrdYn :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndUserPaNotacrdYn));
                System.out.println("StndErrcd :" + NewApplicationContext.get(NewApplicationContext.Key.StndErrcd));
                System.out.println("StndTreatCd :" + NewApplicationContext.get(NewApplicationContext.Key.StndTreatCd));
                System.out
                                .println("StndCncutDscnRqstYn :"
                                                + NewApplicationContext
                                                                .get(NewApplicationContext.Key.StndCncutDscnRqstYn));
                // System.out.println("StndComSpareArea :" +
                // NewApplicationContext.get(NewApplicationContext.Key.StndComSpareArea));

                // Check transaction code for R/S/U/X
                if (rsux != null && rsux.equals("R0")) {

                        treeDDTO.setNodeid(attrMap.get("nodeid"));

                        idcuser = new DCUser();
                        resTrees = idcuser.getListTree(treeDDTO);

                        reqData.getOutputGenericDto().using(NewGenericDto.OUTDATA).add(resTrees);

                } else if (rsux != null && rsux.equals("S0")) {

                        for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 5; j++) {
                                        NewGenericDto treeout = output.addNode("treeGrid");
                                        // Set attributes for tree DTO

                                        treeout.addAttribute("treeId", "10" + i + j);
                                        treeout.addAttribute("treeNm", "data=treeGridlevel" + j + ";level=" + j);
                                        treeout.addAttribute("treeCol1", "treeCol1");
                                        treeout.addAttribute("treeCol2", "treeCol2");

                                }
                        }
                }
                return reqData;
        }

}
