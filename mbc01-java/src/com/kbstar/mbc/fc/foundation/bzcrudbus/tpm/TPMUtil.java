package com.kbstar.mbc.fc.foundation.bzcrudbus.tpm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;

import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.infra.po.GenericDto;
import com.kbstar.ksa.infra.po.KBData;
import com.kbstar.mbc.fc.foundation.bzcrudbus.config.ActionConfig;
import com.kbstar.mbc.fc.foundation.bzcrudbus.config.CommandObj;
import com.kbstar.mbc.fc.foundation.bzcrudbus.config.Env;
import com.kbstar.mbc.fc.foundation.bzcrudbus.config.ParameterTransform;
import com.kbstar.mbc.fc.foundation.bzcrudbus.constant.Constants;
import com.kbstar.mbc.fc.foundation.bzcrudbus.dao.IfrsCommonDAO;
import com.kbstar.mbc.fc.foundation.bzcrudbus.exception.CommonPCException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.exception.TPMRecvException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.exception.TPMSendException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.general.CommonUtil;
import com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.utility.Dom4jUtil;
import com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.utility.GenericDtoUtil;
import com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.utility.StringUtils;
import com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.utility.WafErrorUtil;
import com.kbstar.mbc.fc.foundation.bzcrudbus.log.IIfrsLogger;
import com.kbstar.mbc.fc.foundation.bzcrudbus.log.IfrsLogHelper;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.ICommonDTO;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSCommonDTO;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSEvent;

/**
 * TPM (Transaction Processing Manager) 유틸리티 클래스
 * 
 * 프로그램명: TPMUtil.java
 * 설명: 트랜잭션 처리 관리를 위한 유틸리티 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 트랜잭션 처리 관리
 * - WAF/Server 간 데이터 송수신
 * - 파라미터 변환 및 처리
 * - 에러 처리 및 로깅
 * - 세션 관리
 * 
 * @version 1.0
 */
public class TPMUtil {

	/** 싱글톤 인스턴스 */
	private static TPMUtil instance;

	/**
	 * 싱글톤 함수.
	 * 
	 * @return TPMutil 인스턴스
	 */
	public static synchronized TPMUtil getInstance() {

		if (instance == null) {

			try {

				instance = new TPMUtil();

			} catch (Exception igex) {

				// System.out.println(igex);

				igex.printStackTrace();

				return null;

			}

		}

		return instance;

	}

	/**
	 * 기본 생성자.
	 * 
	 * @throws Exception 예외
	 */
	public TPMUtil() throws Exception {

		try {

		} catch (Exception ex) {

			ex.printStackTrace();

			throw ex;

		}

	}

	/**
	 * 트랜잭션 정보를 반환.
	 * 
	 * @return int
	 */
	public static final int TPInfo() {

		return 0;

	}

	/**
	 * 트랜잭션을 시작한다.
	 * 
	 * @throws Exception 예외
	 * @return boolean
	 */
	public static final boolean TPBegin() throws Exception {

		return true;

	}

	/**
	 * 트랜잭션을 완료 처리.
	 * 
	 * @return boolean
	 */
	public static final boolean TPCommit() {

		try {

			// System.out.println("ContainerTrasactin Info tx : call" );

		} catch (Exception ex) {

			ex.printStackTrace();

			return false;

		}

		return true;

	}

	/**
	 * 트랜잭션을 롤백 처리.
	 * 
	 * @return boolean
	 */
	public static final boolean TPRollback() {

		return true;

	}

	/**
	 * WAF에서 파라미터를 수신한다.
	 * 
	 * @param request HTTP 요청 객체
	 * @return IFRSEvent 객체
	 */
	public static final IFRSEvent TPCRecv(HttpServletRequest request) {

		IFRSEvent ifrsEvent = new IFRSEvent();

		// SEQ 설정
		String seq = IfrsCommonDAO.getInstance().getWafSeq();
		ifrsEvent.getCommonDto().setWafSeq(seq);

		IIfrsLogger logger = IfrsLogHelper.getWaf();

		logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPCRecv] begin");

		HashMap paramMap = new HashMap();
		ifrsEvent.setServletRequest(request);

		try {

			Enumeration venum = request.getParameterNames();

			String paramName = null;
			while (venum.hasMoreElements()) {
				paramName = (String) venum.nextElement();
				paramMap.put(paramName, request.getParameter(paramName));
			}

			// 세션정보설정
			HttpSession session = request.getSession();

			// IFRSEvent 에 HttpServletRequest Parameter 정보를 설정
			ifrsEvent.getCommonDto().setFromMap(paramMap);

			ifrsEvent.getCommonDto().setForwardName(request.getParameter("forward"));
			ifrsEvent.getCommonDto().setCommandId(request.getParameter("commandId"));

		} catch (Exception e) {

			ifrsEvent.getCommonDto().setErrorMap(WafErrorUtil.setErrorInfo(null, "ETPCR001", e));
			// 에러 코드 설정
			ifrsEvent.getCommonDto().setErrorCode("ETPCR001");

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPCRecv] error");
		}

		logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPCRecv] end");

		return ifrsEvent;

	}

	public static final IFRSEvent TPCRecv(HttpServletRequest request, HashMap paramMap) {

		IFRSEvent ifrsEvent = new IFRSEvent();

		// SEQ ä��
		String seq = IfrsCommonDAO.getInstance().getWafSeq();
		ifrsEvent.getCommonDto().setWafSeq(seq);

		ifrsEvent.setServletRequest(request);

		IIfrsLogger logger = IfrsLogHelper.getWaf();

		logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPCRecv] begin");

		try {

			// ������������
			HttpSession session = request.getSession();

			ifrsEvent.getCommonDto().setReturnType("O");
			ifrsEvent.getCommonDto().setFromMap(paramMap);

			ifrsEvent.getCommonDto().setForwardName((String) paramMap.get("forward"));
			ifrsEvent.getCommonDto().setCommandId((String) paramMap.get("commandId"));

		} catch (Exception e) {

			ifrsEvent.getCommonDto().setErrorMap(WafErrorUtil.setErrorInfo(null, "ETPCR001", e));
			// 에러 코드 설정
			ifrsEvent.getCommonDto().setErrorCode("ETPCR001");

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPCRecv] error");
		}

		logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPCRecv] end");

		return ifrsEvent;
	}

	public static final String TPCSendRecv(IFRSEvent ifrsEvent) throws Exception {

		try {

			IIfrsLogger logger = IfrsLogHelper.getWaf();

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPCSendRecv] call delegate");

			// Delegate �� ���� ���񽺸� ȣ���Ѵ�. (KESA Ȥ�� EJB)
			IServiceDelegate svcDlg = new KesaServiceDelegate();

			return svcDlg.sendData(ifrsEvent);

		} catch (Exception ex) {
			throw ex;
		}

	}

	public static final Object TPCSend(IFRSEvent ifrsEvent) {
		// ActionForward actFwd = null;
		Object retObj = null;

		IIfrsLogger logger = IfrsLogHelper.getWaf();

		logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPCSend] begin");

		try {

			String returnType = ifrsEvent.getCommonDto().getReturnType();
			String resultXml = ifrsEvent.getCommonDto().getResultXml();

			switch (returnType.charAt(0)) {

				case 'X':

					ifrsEvent.getServletRequest().setAttribute(Constants.RESULT_XML_VAR, resultXml);

					retObj = ifrsEvent.getCommonDto().getForwardName();

					break;

				case 'O':

					// XML ������ HashMap �������� ��ȯ
					retObj = Dom4jUtil.xml2ResultMap(resultXml);

					break;
			}

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPCSend] end");

		} catch (Exception ex) {
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPCSend] error");
			ex.printStackTrace();
			// throw ex;
		}
		return retObj;
	}

	/**
	 * 
	 * �������
	 * 
	 * 1. Ŭ���̾�Ʈ�κ��� ������ ����Ÿ�� �޴´�.
	 * 
	 * 2. �⺻����Ÿ ������ �Ѵ�.
	 * 
	 * 3. Ʈ������� ��ȣ�� ä���Ѵ�.
	 * 
	 * 
	 * 
	 * @param event :
	 *              Ŭ���̾�Ʈ�� ������ �޽����ۼ��� ��ü.
	 * 
	 * @return IFRSEvent
	 * 
	 */

	public static final IFRSEvent TPSRecv(KBData kbData) throws TPMRecvException {

		IFRSEvent ifrsEvent = new IFRSEvent();

		String seq = IfrsCommonDAO.getInstance().getSvrSeq();
		ifrsEvent.getCommonDto().setTransactionNo(seq);

		IIfrsLogger logger = IfrsLogHelper.getServer();

		logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPSRecv] begin");

		try {

			IFRSCommonDTO commonDTO = (IFRSCommonDTO) ifrsEvent.getCommonDto();

			/**
			 * 
			 * # TrustForm ����
			 * <Individual>
			 * <DataHeader commandId="MBC7200101" gridName="dataGrid1;DataGrid2" />
			 * <InData>
			 * <Params name="abc" dataGrid1=
			 * "name\\^school\\^juso\\|woosung\\^dongdae\\^guri\\|woosung\\^dongdae\\^guri\\|"
			 * dataGrid2=
			 * "name\\^school\\^juso\\|woosung\\^dongdae\\^guri\\|woosung\\^dongdae\\^guri\\|"
			 * />
			 * </InData>
			 * </Individual>
			 * 
			 * # I-Works ����
			 * <Individual>
			 * <DataHeader commandId="MBC7200101" gridName="DataGrid1;DataGrid2" />
			 * <InData>
			 * <Params name="abc" city="Seoul">
			 * <DataGrid1>
			 * <User userID="" userName="" userPassword="" identificationNumber="" userCode=
			 * "" userCodeName="" deptCode="" deptName="" rankCode="" rankName="" enterDate=
			 * "" birthDate="" mobileNo="" email="" address="" usage="" crud="" />
			 * <User userID="" userName="" userPassword="" identificationNumber="" userCode=
			 * "" userCodeName="" deptCode="" deptName="" rankCode="" rankName="" enterDate=
			 * "" birthDate="" mobileNo="" email="" address="" usage="" crud="" />
			 * <User userID="" userName="" userPassword="" identificationNumber="" userCode=
			 * "" userCodeName="" deptCode="" deptName="" rankCode="" rankName="" enterDate=
			 * "" birthDate="" mobileNo="" email="" address="" usage="" crud="" />
			 * <User userID="" userName="" userPassword="" identificationNumber="" userCode=
			 * "" userCodeName="" deptCode="" deptName="" rankCode="" rankName="" enterDate=
			 * "" birthDate="" mobileNo="" email="" address="" usage="" crud="" />
			 * <User userID="" userName="" userPassword="" identificationNumber="" userCode=
			 * "" userCodeName="" deptCode="" deptName="" rankCode="" rankName="" enterDate=
			 * "" birthDate="" mobileNo="" email="" address="" usage="" crud="" />
			 * </DataGrid1>
			 * <DataGrid2>
			 * </DataGrid2>
			 * </InData>
			 * </Individual>
			 * 
			 * ArrayList [header MAP][data01 MAP][data02 MAP][data03 MAP][data04 MAP]
			 * ....[data# MAP]
			 * 
			 * private String commandId; //ȭ�鿡�� ����
			 * private String pcClassName;
			 * private String sqlId; //ȭ�鿡�� ���ð�
			 * private String pcMethodName;
			 * private String routingAS; //ȭ�鿡�� ���ð�
			 * private HashMap fromMap;
			 * 
			 */

			// DataHeader To Map
			GenericDto subDto = kbData.getInputGenericDto().using("/KB-Message/Individual/DataHeader");
			Map<String, String> headerMap = subDto.getAttributeMap();

			String kesaXml = kbData.getInputGenericDto().getXml();
			Document doc = Dom4jUtil.getDocument(kesaXml);

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPSRecv] KESA INDATA XML : " + kesaXml);

			// InData To Map (/KB-Message/Individual/InData �� ù��° ���� ��带 �о� ���δ�,
			// ù��°�� �Ķ���͵�)

			// subDto =
			// kbData.getInputGenericDto().using("/KB-Message/Individual/InData/Params");
			// Map<String, String> attributeMap = subDto.getAttributeMap();
			Map<String, String> attributeMap = Dom4jUtil.getFirstChildMap(doc, "/KB-Message/Individual/InData");

			commonDTO.setFromMap((HashMap) attributeMap);

			// ��Ÿ������ ���� ����
			HashMap metaDataMap = new HashMap();
			;
			Object metaDataObj = headerMap.get("metaData");

			if (metaDataObj != null) {
				String metaData = (String) metaDataObj;
				String[] metaDataArr = metaData.split(";");
				String metaStr = null;

				for (int i = 0; i < metaDataArr.length; i++) {
					metaStr = metaDataArr[i].trim();

					if (!metaStr.equals("")) {
						metaDataMap.put(metaStr.toUpperCase(), metaStr);
					}
				}
			}

			String gridNameStr = (String) headerMap.get("gridName");

			// �ܸ�Ÿ��
			String danMalType = Env.getProperty("TPMUtil.DANMAL_TYPE");
			commonDTO.setDanMalType(danMalType);

			if (gridNameStr != null && !gridNameStr.trim().equals("")) {

				String[] gridNameArr = gridNameStr.split(";");
				commonDTO.setGridName(gridNameArr);

				String gridName = null;

				switch (danMalType.charAt(0)) {

					case 'I':

						for (int i = 0; i < gridNameArr.length; i++) {
							gridName = gridNameArr[i].trim();

							if (!gridName.equals("")) {
								List gridDataList = Dom4jUtil.getAttributeMapList(doc,
										"/KB-Message/Individual/InData/" + gridName);
								if (gridDataList != null) {
									commonDTO.getFromMap().put(gridName, gridDataList);

									// ��Ÿ������ �߰� ����
									if (gridDataList.size() > 0) {

										HashMap rowMap = (HashMap) gridDataList.get(0);
										Set keySet = rowMap.keySet();
										String colName = null;

										for (Iterator itr = new ArrayList((Collection) keySet).iterator(); itr
												.hasNext();) {
											colName = (String) itr.next();
											if (colName != null && !metaDataMap.containsKey(colName.toUpperCase())) {
												metaDataMap.put(colName.toUpperCase(), colName);
											}
										}
									}
								}
							}
						}

						break;

					case 'T':
						ParameterTransform paramTransform = null;

						for (int i = 0; i < gridNameArr.length; i++) {
							gridName = gridNameArr[i].trim();

							if (!gridName.equals("")) {

								paramTransform = new ParameterTransform();

								Set set = attributeMap.keySet();
								String keyName = null;

								// �׸��� �����͸� �˻��ؼ� ���� �ٽ� �����Ѵ�.
								for (Iterator itr = new ArrayList((Collection) set).iterator(); itr.hasNext();) {
									keyName = (String) itr.next();

									if (keyName.equals(gridName)) {
										List gridDataList = paramTransform.getParamGridList(attributeMap.get(gridName));
										commonDTO.getFromMap().put(gridName, gridDataList);

										// grid �÷� ��Ÿ������ �߰� ����
										if (gridDataList.size() > 0) {

											HashMap rowMap = (HashMap) gridDataList.get(0);
											Set keySet = rowMap.keySet();
											String colName = null;

											for (Iterator itr2 = GenericDtoUtil.snapshotIterator(keySet); itr2
													.hasNext();) {
												colName = (String) itr2.next();
												if (colName != null
														&& !metaDataMap.containsKey(colName.toUpperCase())) {
													metaDataMap.put(colName.toUpperCase(), colName);
												}
											}
										}

										break;
									}
								}
							}
						} // for

						break;
				}

				/*
				 * // �ܸ��� I-Works �� ���
				 * if(danMalType.equals(Constants.DANMAL_TYPE_IWORKS)) {
				 * 
				 * for(int i=0; i<gridNameArr.length; i++) {
				 * gridName = gridNameArr[i].trim();
				 * 
				 * if(!gridName.equals("")) {
				 * List gridDataList = Dom4jUtil.getAttributeMapList(doc,
				 * "/KB-Message/Individual/InData/" + gridName);
				 * commonDTO.getFromMap().put(gridName, gridDataList);
				 * }
				 * }
				 * 
				 * }
				 * // �ܸ��� TrustForm �� ���
				 * else if(danMalType.equals(Constants.DANMAL_TYPE_TRUSTFORM)) {
				 * 
				 * ParameterTransform paramTransform = null;
				 * 
				 * for(int i=0; i<gridNameArr.length; i++) {
				 * gridName = gridNameArr[i].trim();
				 * 
				 * if(!gridName.equals("")) {
				 * 
				 * paramTransform = new ParameterTransform();
				 * 
				 * Set set = attributeMap.keySet();
				 * String keyName = null;
				 * 
				 * // �׸��� �����͸� �˻��ؼ� ���� �ٽ� �����Ѵ�.
				 * for (Iterator itr = new ArrayList((Collection) set).iterator();
				 * itr.hasNext();) {
				 * keyName = (String) itr.next();
				 * 
				 * if(keyName.equals(gridName)) {
				 * List gridDataList =
				 * paramTransform.getParamGridList(attributeMap.get(gridName));
				 * commonDTO.getFromMap().put(gridName, gridDataList);
				 * break;
				 * }
				 * }
				 * }
				 * } // for
				 * 
				 * }
				 */
			}

			if (metaDataMap != null) {
				commonDTO.setMetaDataMap(metaDataMap);
			}
			commonDTO.setCommandId(headerMap.get("commandId"));
			// 에러 코드 설정
			commonDTO.setUserId(headerMap.get("userId"));
			commonDTO.setKbData(kbData);

			// �� ������ �����ͼҽ��� �������� ����
			String bizDsYn = Env.getProperty("KesaDataSource.BIZDS_YN");

			if (bizDsYn != null && bizDsYn.equals("Y")) {
				String cmdId = commonDTO.getCommandId();
				if (cmdId != null) {
					ifrsEvent.getCommonDto().setBizCode(cmdId.substring(0, 3).toUpperCase());
				}
			}

			ifrsEvent.setCommonDto(commonDTO);
			ifrsEvent.setKBData(kbData);

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPSRecv] end");

		} catch (Exception ex) {
			// ex.printStackTrace();
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPSRecv] error");
			throw new TPMRecvException(ex.toString());
		}

		// ��������

		return ifrsEvent;

	}

	public static final IFRSEvent TPSRecv(Map searchOption) {

		IFRSEvent ifrsEvent = new IFRSEvent();
		IFRSCommonDTO commonDTO = (IFRSCommonDTO) ifrsEvent.getCommonDto();

		// ��������

		return ifrsEvent;

	}

	public static final IFRSEvent TPSRecv(IFRSEvent ifrsEvent) {

		IIfrsLogger logger = IfrsLogHelper.getServer();

		logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPSRecv] begin");

		String hostseq = null;

		IFRSCommonDTO commonDTO = (IFRSCommonDTO) ifrsEvent.getCommonDto();

		logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPSRecv] end");

		return ifrsEvent;

	}

	/**
	 * 
	 * ȣ�� Ʈ������� CRUD �������� .
	 * 
	 * @param methodName
	 *                   String
	 * 
	 * @return String
	 * 
	 */

	private static final String getMethodType(String methodName) {

		if (methodName.startsWith("register")) {

			return "02";

		} else if (methodName.startsWith("create")) {

			return "02";

		} else if (methodName.startsWith("modify")) {

			return "03";

		} else if (methodName.startsWith("update")) {

			return "03";

		} else if (methodName.startsWith("get")) {

			return "01";

		} else if (methodName.startsWith("inquir")) {

			return "01";

		} else if (methodName.startsWith("page")) {

			return "01";

		} else if (methodName.startsWith("remove")) {

			return "04";

		} else if (methodName.startsWith("delete")) {

			return "04";

		}

		return "03";

	}

	/**
	 * 
	 * TCFDTO ������ ������ �ϼ� �ش� �ý��۰� �޼ҵ�� �׸��� Ʈ����� ��ȣ�� �����Ѵ�.
	 * 
	 * @param event
	 *              IFRSEvent
	 * 
	 */

	/**
	 * 
	 * Ŭ���̾�Ʈ�� ����Ÿ�����ϰ� ���������� �����.
	 * 
	 * @param event :
	 *              Ŭ���̾�Ʈ�� ������ �޽��� �ۼ��� ��ü.
	 * 
	 * @return IFRSEvent
	 * 
	 */

	public static final KBData TPSSend(IFRSEvent ifrsEvent) throws TPMSendException {

		IIfrsLogger logger = IfrsLogHelper.getServer();

		logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPSSend] begin");

		try {
			KBData kbData = ifrsEvent.getKbData();
			IFRSCommonDTO commonDTO = (IFRSCommonDTO) ifrsEvent.getCommonDto();
			HashMap toMap = commonDTO.getToMap();
			// ArrayList callInfoList =
			// ActionConfig.getInstance().getCmdList(commonDTO.getCommandId());
			HashMap elementMap = ActionConfig.getInstance(commonDTO.getCommandId().substring(0, 3).toUpperCase())
					.getElementMap();
			/*
			 * toMap ������ ����
			 * HashMap [cmdObj.key=List<HashMap>][cmdObj.key=List<HashMap>]
			 * 
			 * <OutData>
			 * <cmdObj.key> <= List ����
			 * <cmdObj.sname key=value key=value key=value/>
			 * <cmdObj.sname key=value key=value key=value/>
			 * <cmdObj.sname key=value key=value key=value/>
			 * </cmdObj.key>
			 * <cmdObj.key> <= �Ϲ����� �� ����
			 * <cmdObj.element key=value key=value key=value/>
			 * </cmdObj.key>
			 * </OutData>
			 */

			Set set = toMap.keySet();
			String keyName = null;
			Object resultObj = null;
			String elementName = null;
			int i = 0;

			// �׸��� �����͸� �˻��ؼ� ���� �ٽ� �����Ѵ�.
			for (Iterator itr = GenericDtoUtil.snapshotIterator(set); itr.hasNext();) {
				keyName = (String) itr.next();

				resultObj = toMap.get(keyName);
				elementName = (String) elementMap.get((commonDTO.getCommandId() + "." + keyName));
				if (elementName == null || elementName.equals("")) {
					elementName = Env.getProperty("GenericDtoUtil.DATA_ELEMENT_NAME");
				}

				if (resultObj instanceof List) {
					GenericDtoUtil.addMapList(kbData.getOutputGenericDto(), keyName, elementName, (List) resultObj, "",
							commonDTO.getMetaDataMap());
				} else if (resultObj instanceof HashMap) {
					GenericDtoUtil.addMapList(kbData.getOutputGenericDto(), keyName, elementName, (HashMap) resultObj,
							"", commonDTO.getMetaDataMap());
				} else if (resultObj instanceof String) {
					HashMap map = new HashMap(1);
					map.put("Value", resultObj);
					GenericDtoUtil.addMapList(kbData.getOutputGenericDto(), keyName, elementName, map, "");
				} else {
					GenericDtoUtil.addMapList(kbData.getOutputGenericDto(), keyName, elementName, new Vector(), "");
				}

				i++;
			}

			/*
			 * GenericDto subDto =
			 * kbData.getInputGenericDto().using("/KB-Message/Individual/OutData");
			 * Map<String, String> attributeMap = subDto.getAttributeMap();
			 * 
			 * for (int i = 0; i < callInfoList.size(); i++) {
			 * CommandObj cmdObj = (CommandObj) callInfoList.get(i);
			 * GenericDto subDto01 = subDto.addNode(cmdObj.key);
			 * 
			 * ArrayList toSendList = (ArrayList) toMap.get(cmdObj.key);
			 * 
			 * for (int iii = 0; i < toSendList.size(); iii++) {
			 * Map gridMap = (Map) toSendList.get(i);
			 * Iterator key = gridMap.keySet().iterator();
			 * while (key.hasNext()) {
			 * String keyName = (String) key.next();
			 * subDto01.addAttribute(keyName, (String) gridMap.get(keyName));
			 * }
			 * }
			 * }
			 */

			logger.info(ifrsEvent.getCommonDto().getSeqNo(),
					"[TPSSend] KESA OUTDATA XML : " + kbData.getOutputGenericDto().getXml());

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[TPSSend] end");

			return kbData;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new TPMSendException(ex.toString());
		}
	}

	public static void TPSSendRecv(IFRSCommonDTO commonDTO, CommandObj cmdObj) throws TPMSendException {

		IIfrsLogger logger = IfrsLogHelper.getServer();

		logger.info("[TPSSendRecv] begin");

		String pcClassName = cmdObj.name;
		String pcMethodName = cmdObj.method;
		String sqlId = cmdObj.sqlId;
		String cmdKey = cmdObj.key;

		commonDTO.setSqlId(sqlId);
		commonDTO.setPcClassName(pcClassName);
		commonDTO.setPcMethodName(pcMethodName);
		commonDTO.setKey(cmdKey);

		try {
			Class actionClass = Class.forName(pcClassName);
			Class[] methodParam = { IFRSCommonDTO.class };
			Class[] methodParam2 = { ICommonDTO.class };
			Object pTarget = actionClass.newInstance();

			Method method = null;
			try {
				method = actionClass.getDeclaredMethod(pcMethodName, methodParam);
			} catch (NoSuchMethodException nsme) {

				try {
					// ���� PCŬ������ �޼ҵ尡 �������� ���� ��� �θ� Ŭ�������� ã�´�.
					method = ((Class) actionClass.getGenericSuperclass()).getDeclaredMethod(pcMethodName, methodParam);

				} catch (NoSuchMethodException nsme2) {
					// PC Ŭ������ �Ķ���Ͱ� ICommonDTO �� ��츦 ã�´�.
					method = actionClass.getDeclaredMethod(pcMethodName, methodParam2);
				}

			}

			if (method != null) {
				Object rcvObject = method.invoke(pTarget, new Object[] { commonDTO });

				commonDTO.getToMap().put(cmdKey, rcvObject);

				// ���� ��� �� ��
				// ������� KBData�� �ƴ� ��쿡�� ����� �����Ѵ�.
				// ������� KBData�� ��쿡�� PC���� ���� ������� ������ ����̴�.

				// if(!(rcvObject instanceof KBData)) {

				// commonDTO.getToMap().put(cmdKey, rcvObject);

				// }

			}

			logger.info("[TPSSendRecv] end");

		} catch (Exception e) {

			logger.info("[TPSSendRecv] error");

			e.printStackTrace();

			if (e instanceof InvocationTargetException) {
				InvocationTargetException ite = (InvocationTargetException) e;
				Throwable throwable = ite.getTargetException();

				if (throwable instanceof CommonPCException) {
					CommonPCException pce = (CommonPCException) throwable;
					throw new TPMSendException(pce.getErrorCode(), pce.getMessage());
				} else if (throwable instanceof BusinessException) {
					BusinessException be = (BusinessException) throwable;
					throw new TPMSendException(be.getErrorCode(), be.getMessage());
				} else {
					throw new TPMSendException(e.toString());
				}
			} else {
				throw new TPMSendException(e.toString());
			}
		}

	}

	/**
	 * 
	 * Ʈ����� �ð��� ��� ���.
	 * 
	 * @param starttime
	 *                  String
	 * 
	 * @param endtime
	 *                  String
	 * 
	 * @return String
	 * 
	 */

	public static final String TPMInterval(String starttime, String endtime) {

		starttime = StringUtils.left(starttime, 8, "0");

		endtime = StringUtils.left(endtime, 8, "0");

		long startmills = 0;

		long endmills = 0;

		try {

			startmills = CommonUtil.Str2Int(starttime.substring(0,

					2)) * 60 * 60 * 100

					+ CommonUtil.Str2Int(starttime.substring(2, 4)) * 60 * 100

					+ CommonUtil.Str2Int(starttime.substring(4, 6)) * 100

					+ CommonUtil.Str2Int(starttime.substring(6));

			endmills = CommonUtil.Str2Int(endtime.substring(0,

					2)) * 60 * 60 * 100

					+ CommonUtil.Str2Int(endtime.substring(2, 4)) * 60 * 100

					+ CommonUtil.Str2Int(endtime.substring(4, 6)) * 100

					+ CommonUtil.Str2Int(endtime.substring(6));

			String interval = String.valueOf((endmills - startmills) / 100f);

			System.out.println(interval);

			int len = interval.length();

			int index = interval.indexOf('.');

			if (len - (index + 1) > 2) {

				interval = interval.substring(0,

						index + 1)
						+ interval.substring(index + 1,

								index + 1 + 2);

			}

			return interval;

		} catch (Exception ex) {

			return "0";

		}

	}

}