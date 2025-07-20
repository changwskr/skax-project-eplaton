package com.kbstar.mbc.fc.foundation.bzcrudbus.tcf;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;

import com.kbstar.ksa.infra.po.KBData;
import com.kbstar.mbc.fc.foundation.bzcrudbus.constant.Constants;
import com.kbstar.mbc.fc.foundation.bzcrudbus.constant.TCFConstantErrcode;
import com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.general.CommonUtil;
import com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.utility.ErrorCheckUtil;
import com.kbstar.mbc.fc.foundation.bzcrudbus.log.IIfrsLogger;
import com.kbstar.mbc.fc.foundation.bzcrudbus.log.IfrsLogHelper;
import com.kbstar.mbc.fc.foundation.bzcrudbus.tpm.TPMUtil;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSCommonDTO;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSEvent;

/**
 * STF (Start Transaction Framework) 클래스
 * 
 * 프로그램명: STF.java
 * 설명: This is oversea banking package.
 * 트랜잭션을 시작하기 위한 기본적인 설정을 담당한다.
 * 
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 트랜잭션 시작 전 기본 설정
 * - 로그인 상태 확인 (WAF 모드)
 * - 에러 코드 초기화
 * - 시스템 날짜/시간 설정
 * - 트랜잭션 제어 및 로깅
 * 
 * @version 1.0
 */
public class STF {

	/** 싱글톤 인스턴스 */
	private static STF instance;

	/** 트랜잭션 모드 */
	private String transactionMode = null;

	/** IFRS 이벤트 */
	private IFRSEvent ifrsEvent;

	/** 공통 DTO */
	private IFRSCommonDTO commonDTO;

	/** IFRS 로거 */
	private IIfrsLogger logger;

	/**
	 * STF 인스턴스를 반환하는 함수.
	 * 
	 * @return STF
	 */
	public static synchronized STF getInstance() {
		if (instance == null) {
			try {
				instance = new STF();
			} catch (Exception ex) {
			}
		}
		return instance;
	}

	/**
	 * 기본 생성자 함수.
	 */
	public STF() {
	}

	/**
	 * 트랜잭션 모드를 지정하는 생성자
	 * 
	 * @param transactionMode 트랜잭션 모드
	 */
	public STF(String transactionMode) {
		this.transactionMode = transactionMode;

		if (transactionMode.equals(Constants.TRANSACTION_MODE_WAF)) {
			logger = IfrsLogHelper.getWaf();
		} else {
			logger = IfrsLogHelper.getServer();
		}
	}

	/**
	 * 실행 함수
	 * 
	 * @param ppifrsEvent ifrsEvent
	 * @return ifrsEvent
	 */
	public IFRSEvent execute(IFRSEvent ppifrsEvent) {

		try {
			ifrsEvent = ppifrsEvent;
			commonDTO = (IFRSCommonDTO) ifrsEvent.getCommonDto();

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Init] begin");

			/*
			 * 기본적인 설정을 담당하는 함수 에러코드를 IZZZ000으로 설정한다. 트랜잭션을 시작한다. 시스템 날짜를 설정한다. 시스템 시간을
			 * 설정한다. 에러코드를 설정한다.
			 */
			STF_Init();

			/*
			 * 에러가 발생했을 경우에만 에러 처리를 하고 종료한다.
			 */
			if (isError()) {
				/*
				 * 에러가 발생한 경우 STF에서 에러 처리를 하고 종료한다.
				 */
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Init] error");
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Init] end");
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_End] begin");
				STF_End();
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_End] end");

				return ifrsEvent;
			} else {
				/*
				 * 에러가 없을 경우 중간 처리를 하고 종료한다.
				 */
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Init]success");
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Init] end");
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] begin");
				STF_Middle();
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] end");
			}

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_End] begin");

			/*
			 * 트랜잭션을 종료한다.
			 */
			STF_End();
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_End] end");

		} catch (Exception ex) {
			setSTFError(TCFConstantErrcode.ESTF003, TCFConstantErrcode.ESTF003_MSG);
		}

		return ifrsEvent;
	}

	/**
	 * 기본적인 설정을 담당하는 함수. 에러코드를 I0000000으로 설정한다. 트랜잭션을 시작한다. 시스템 날짜를 설정한다. 시스템 시간을
	 * 설정한다. 에러코드를 설정한다. 화면코드를 설정한다.
	 * 
	 * @return boolean.
	 */
	public boolean STF_Init() {

		try {

			/*******************************************************************************************
			 * WAF 모드에서 로그인 상태 확인 (Code Added by 김민주)
			 *******************************************************************************************
			 * 
			 */

			switch (this.transactionMode.charAt(0)) {
				case 'S':
				case 's':
					break;
				case 'W':
				case 'w':

					// WAF 모드에서 로그인 상태 확인을 한다.
					boolean bLogin = isLogin(ifrsEvent.getServletRequest());

					if (!bLogin) {
						this.commonDTO.setErrorCode("E0000000");
						logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Init] Login Failed");
						return false;
					} else {
						break;
					}
			}

			/*
			 * *****************************************************************************
			 * ************
			 */

			/*********************************************************************************************************************
			 * 에러코드를 초기화한다.
			 ********************************************************************************************************************/

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Init] 에러코드를 초기화한다. ErrorCode Set :: I0000000");
			this.commonDTO.setErrorCode("I0000000");

			/*********************************************************************************************************************
			 * 시스템 날짜를 설정한다. # 시스템
			 ********************************************************************************************************************/
			// TimeProcess timeProcess = TimeProcess.getInstance();
			// String systemDateAndTime =
			// timeProcess.getSystemDate(commonDTO.getTimeZone());
			// commonDTO.setSystemDate(systemDateAndTime.substring(0, 8));
			// logger.info(ifrsEvent.getCommonDto().getSeqNo(), "시스템날짜를 설정한다. : " +
			// commonDTO.getSystemDate());
			// String systemDateAndTime =
			// timeProcess.getSystemDate(commonDTO.getTimeZone());
			commonDTO.setSysDate(CommonUtil.GetSysDate().substring(0, 8));

			/*********************************************************************************************************************
			 * 시스템 시간을 설정한다.
			 ********************************************************************************************************************/
			// systemDateAndTime = CommonUtil.GetSysTime();
			// commonDTO.setSystemInTime(systemDateAndTime.substring(8, 16));
			// logger.info(ifrsEvent.getCommonDto().getSeqNo(), "시스템시간을 설정한다.System Time set
			// :: " + commonDTO.getSystemInTime());

			/*********************************************************************************************************************
			 * 트랜잭션 제어 및 로깅
			 ********************************************************************************************************************/
			STF_GetBusinessDate();

			return true;

		} catch (Exception ex) {
			// ex.printStackTrace();
			setSTFError(TCFConstantErrcode.ESTF002, TCFConstantErrcode.ESTF002_MSG);
			return false;
		} finally {

		}
	}

	/**
	 * 중간 처리를 담당하는 함수. 트랜잭션 시작, 트랜잭션 종료, 트랜잭션 제어 및 로깅을 수행한다. 에러가 발생하면 종료한다.
	 * 
	 * @return boolean
	 */
	public boolean STF_Middle() {
		try {

			/*
			 * 트랜잭션 시작
			 */
			if (TPMUtil.TPBegin()) {
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] TPbegin() 시작");
			} else {
				setSTFError(TCFConstantErrcode.ESTF007, TCFConstantErrcode.ESTF007_MSG);
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] TPbegin() 실패");
			}

			/*
			 * 트랜잭션 제어 및 로깅
			 */
			STF_SPtxctl();
			if (isError()) {
				return false;
			}

			/*
			 * 트랜잭션 종료
			 */
			STF_SPeod();
			if (isError()) {
				return false;
			}

			/*********************************************************************************************************************
			 * 트랜잭션 제어 및 로깅
			 ********************************************************************************************************************/
			/*
			 * 트랜잭션 위치 확인 switch 문
			 */

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] 트랜잭션 위치 확인 :: ");
			// switch (CommonUtil.Str2Int(commonDTO.getTpfq())) {
			switch (200) {
				case 100:
					/** server-server interchange */
					logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] TPFQ : 100 ");
					break;
				case 200:
					/** waf clinet-server interchange */
					logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] TPFQ : 200 ");
					break;
				case 300:
					/** native-server interchange */
					logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] TPFQ : 300 ");
					break;
				case 400:
					/** atm client-server interchange */
					logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] TPFQ : 400 ");
					break;
				case 500:
					/** internetbank-server interchange */
					logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] TPFQ : 500 ");
					break;
				case 600:
					/** batch-server interchange */
					logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] TPFQ : 600 ");
					break;
				default:
					logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_Middle] TPFQ : 유효하지 않음 ");
					setSTFError(TCFConstantErrcode.ESTF009, TCFConstantErrcode.ESTF009_MSG);
					break;
			}

			if (isError()) {
				return false;
			}

			return true;

		} catch (Exception ex) {
			// ex.printStackTrace();
			setSTFError(TCFConstantErrcode.ESTF010, TCFConstantErrcode.ESTF010_MSG);
			// logger.info(ifrsEvent.getCommonDto().getSeqNo(), ".STF_Middle():" +
			// ex.toString());

			return false;
		}
	}

	/**
	 * 트랜잭션 종료를 담당하는 함수. 시스템 시간 설정, 패킷 이동, 로그 기록을 수행한다.
	 * 
	 * @return boolean
	 */
	public boolean STF_End() {
		try {

			/*********************************************************************************************************************
			 * 기본적인 설정 종료
			 ********************************************************************************************************************/
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_End] 기본적인 설정 종료. SystemOuttime 설정 ::");
			// commonDTO.setSystemOutTime(CommonUtil.GetSysTime());

			/*********************************************************************************************************************
			 * 패킷 이동
			 ********************************************************************************************************************/
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_End] 패킷 이동. Move Packet Rebuild ::");
			STF_MovePacket();

			/*********************************************************************************************************************
			 * 로그 기록
			 ********************************************************************************************************************/
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_End] begin]");
			if (!STF_SPcommonLog()) {
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_End] STF_SPcommonLog() 실패");
			} else {
				logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_End] STF_SPcommonLog() 성공");
			}

			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_End] true");

			// DB 종료 처리

			return true;
		} catch (Exception ex) {
			// ex.printStackTrace();
			setSTFError(TCFConstantErrcode.ESTF011, TCFConstantErrcode.ESTF011_MSG);

			return false;
		}
	}

	/**
	 * 에러 상태를 확인하는 함수.
	 * 
	 * @return boolean.
	 */
	private boolean isError() {

		return ErrorCheckUtil.getInstance().isError(ifrsEvent.getCommonDto().getErrorCode().charAt(0));
		/*
		 * switch (ifrsEvent.getCommonDto().getErrorCode().charAt(0)) {
		 * case 'e':
		 * case 'b':
		 * case 's':
		 * case 'd':
		 * case 'E':
		 * case 'B':
		 * case 'S':
		 * case 'D':
		 * return true;
		 * default:
		 * return false;
		 * }
		 */
	}

	/**
	 * 
	 * 
	 * drop table TRANSACTION_UPDOWN;
	 * 
	 * CREATE TABLE TRANSACTION_UPDOWN ( terminalID VARCHAR2(100), terminalType
	 * VARCHAR2(100), xmlSeq VARCHAR2(100), bankCode
	 * VARCHAR2(100), branchCode VARCHAR2(100), glPostBranchCode VARCHAR2(100),
	 * channelType VARCHAR2(100), userID VARCHAR2(100),
	 * eventNo VARCHAR2(100), nation VARCHAR2(100), regionCode VARCHAR2(100),
	 * timeZone VARCHAR2(100), fxRateCount VARCHAR2(100),
	 * reqName VARCHAR2(100), systemDate VARCHAR2(100), businessDate VARCHAR2(100),
	 * transactionNo VARCHAR2(100), baseCurrency
	 * VARCHAR2(100), multiPL VARCHAR2(100), userLevel VARCHAR2(100), IPAddress
	 * VARCHAR2(100), req_name VARCHAR2(100), system_name
	 * VARCHAR2(100), operation_name VARCHAR2(100), operation_method VARCHAR2(100),
	 * cdto_name VARCHAR2(100), action_name
	 * VARCHAR2(100), hostseq VARCHAR2(100), orgseq VARCHAR2(100), tx_timer
	 * VARCHAR2(100), tpfq VARCHAR2(100), errorcode
	 * VARCHAR2(100), trclass VARCHAR2(100), web_timeout VARCHAR2(100), web_intime
	 * VARCHAR2(100), web_outtime VARCHAR2(100),
	 * systemInTime VARCHAR2(100), systemOutTime VARCHAR2(100), system_date
	 * VARCHAR2(100), error_message VARCHAR2(100),
	 * logic_level VARCHAR2(100), STF_intime VARCHAR2(100), STF_outtime
	 * VARCHAR2(100), BTF_intime VARCHAR2(100), BTF_outtime
	 * VARCHAR2(100), ETF_intime VARCHAR2(100), ETF_outtime VARCHAR2(100), INPUT_DTO
	 * VARCHAR2(4000), OUTPUT_DTO VARCHAR2(4000),
	 * CONSTRAINT TRANSACTION_UPDOWN PRIMARY KEY ( hostseq ) USING INDEX TABLESPACE
	 * TSP_CORE_IND ) TABLESPACE TSP_CORE ;
	 * 
	 * CREATE SYNONYM TRANSACTION_UPDOWN FOR CORE.TRANSACTION_UPDOWN; grant
	 * select,insert,update,delete on TRANSACTION_UPDOWN to
	 * eplaton;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param event
	 * @return
	 */

	/**
	 * 로그 기록을 담당하는 함수. 트랜잭션 시작 시 로그 파일을 생성하고 로그를 기록한다.
	 * 
	 * @return boolean.
	 */
	private boolean STF_SPcommonLog() {

		String LOGFILENAME = null;
		FileOutputStream fos = null;
		PrintStream ps = null;

		try {
			/*********************************************************************************************************************
			 * 트랜잭션 시작 시 로그 파일 시간을 초기화한다.
			 ********************************************************************************************************************/
			LOGFILENAME = CommonUtil.GetHostName() + "." + commonDTO.getCommandId().substring(0, 3).toUpperCase() + "."
					+ "in" + "." + CommonUtil.GetSysDate();
			fos = new FileOutputStream(LOGFILENAME, true);
			ps = new PrintStream(fos);
			ps.println("pc_operation_name" + "|" + "hostseq" + "-"
					+ com.kbstar.mbc.fc.foundation.bzcrudbus.foundation.utility.Reflector.objectToString(ifrsEvent));
			ps.flush();
			ps.close();
			fos.close();

		} catch (Exception e) {
			try {
				if (fos != null)
					fos.close();
				if (ps != null)
					ps.close();
			} catch (Exception ex) {
			}
			// e.printStackTrace();
			setSTFError(TCFConstantErrcode.ESTF015, TCFConstantErrcode.ESTF015_MSG);
			return false;
		}
		return true;
	}

	/*****************************************************************************************************************************
	 * 트랜잭션 제어 및 로깅
	 * ************************************************************************ 트랜잭션
	 * 시작 및 종료, 트랜잭션 위치 확인, 에러 처리 등 트랜잭션 전반적인 제어 및 로깅
	 ****************************************************************************************************************************/

	/**
	 * 트랜잭션 제어 및 로깅
	 * ************************************************************************ 트랜잭션
	 * 시작 및 종료, 트랜잭션 위치 확인, 에러 처리 등 트랜잭션 전반적인 제어 및 로깅
	 * 
	 * @return int
	 */
	private int STF_SPtxctl() {
		/*************************************************************************************************************************
		 * 트랜잭션 제어 및 로깅
		 ************************************************************************************************************************/
		try {

			/*
			 * 트랜잭션 시작 및 종료
			 */
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_SPtxctl] 트랜잭션 시작 및 종료");

			/*
			 * 트랜잭션 위치 확인
			 */
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_SPtxctl] 트랜잭션 위치 확인");

			/*
			 * 에러 처리
			 */
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_SPtxctl] 에러 처리");

			/*
			 * 트랜잭션 위치 확인 범위
			 */
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_SPtxctl] 트랜잭션 위치 확인 범위");

			/*
			 * 트랜잭션 위치 확인 범위 2
			 */
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_SPtxctl] 트랜잭션 위치 확인 범위 2");

			/*
			 * 트랜잭션 위치 확인 범위 3
			 */
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_SPtxctl] 트랜잭션 위치 확인 범위 3");

			/*
			 * 트랜잭션 위치 확인 범위 4
			 */
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_SPtxctl] 트랜잭션 위치 확인 범위 4");

			/*
			 * 트랜잭션 위치 확인 범위 5
			 */
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_SPtxctl] 트랜잭션 위치 확인 범위 5");

			/*
			 * 트랜잭션 위치 확인 범위 6
			 */
			logger.info(ifrsEvent.getCommonDto().getSeqNo(), "[STF_SPtxctl] 트랜잭션 위치 확인 범위 6");

			return 0;
		} catch (Exception ex) {
			setSTFError(TCFConstantErrcode.ESTF016, TCFConstantErrcode.ESTF016_MSG);

			return -1;
		}
	}

	/**
	 * 트랜잭션 종료를 담당하는 함수. 트랜잭션 제어 및 로깅, 에러 처리를 수행한다.
	 * 
	 * @param commonDTO
	 *                  IFRSCommonDTO
	 * @throws RemoteException
	 * @throws EplatonAppException
	 * @return boolean
	 */
	private boolean checkMenuPermission(IFRSCommonDTO commonDTO) {
		return true;
	}

	/**
	 * 트랜잭션 종료를 담당하는 함수. 트랜잭션 제어 및 로깅, 에러 처리를 수행한다.
	 * 
	 * @return boolean
	 */
	private void STF_SPeod() {
		// 트랜잭션 종료 로직
		try {
			return;
		} catch (Exception ex) {
			setSTFError(TCFConstantErrcode.ESTF026, TCFConstantErrcode.ESTF026_MSG);

			return;
		}

	}

	/**
	 * 에러 코드를 설정하는 함수.
	 * 
	 * @param errorcode
	 *                  String
	 * @param message
	 *                  String
	 */
	private void setSTFError(String errorCode, String errorMsg) {
		// 에러 코드를 설정한다.
		if (!isError()) {
			ifrsEvent.getCommonDto().setErrorCode(errorCode);
			ifrsEvent.getCommonDto().setErrorMsg(errorMsg);
		}
	}

	/**
	 * 패킷 이동을 담당하는 함수. 공통 DTO를 설정하고 패킷을 이동시킨다.
	 * 
	 * @return void
	 */
	private void STF_MovePacket() {

		ifrsEvent.setCommonDto(this.commonDTO);

		/*
		 * 공통 DTO를 설정한다. (참조 복사)
		 */

		KBData kbdata = ifrsEvent.getKbData();

	}

	/**
	 * 트랜잭션 제어 및 로깅을 담당하는 함수. 트랜잭션 시작 시 로그 파일을 생성하고 로그를 기록한다.
	 * 
	 * @return String
	 */
	public String STF_GetBusinessDate() {
		String bizDt = null;
		try {
			commonDTO.setBizDate(CommonUtil.GetSysDate());
			bizDt = commonDTO.getBizDate();
		} catch (Exception ex) {
			setSTFError(TCFConstantErrcode.ESTF029, TCFConstantErrcode.ESTF029_MSG);
			return bizDt;
		}
		return bizDt;
	}

	// 로그인 상태 확인
	protected boolean isLogin(HttpServletRequest request) {
		/*
		 * String usrId = SsnBean.getUsrId(request);
		 * 
		 * if (usrId != null && usrId.length() > 0) {
		 * return true;
		 * }
		 * else {
		 * return false;
		 * }
		 */
		return true;
	}
}

/**
 * 트랜잭션 로그 엔트리 클래스
 * <p>
 * Title: ePlaton
 * </p>
 * <p>
 * Description: This is oversea banking package.
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004 IMS System CO., LTD.
 * </p>
 * <p>
 * Company: IMS System
 * </p>
 * 
 * @author xx xx xx
 * @version 1.0
 */
class TimeoutEntry {
	public long beginTime = System.currentTimeMillis();

	public long timeout;

	public String value;

	public boolean isExpired() {
		return (System.currentTimeMillis() - beginTime) > timeout;
	}

	public String toString() {
		return "TimeoutEntry:{beginTime=" + beginTime + "}, {timeout=" + timeout + "}, {value=" + value + "}";
	}
}
