package com.kbstar.mbc.fc.foundation.bzcrudbus.transfer;

import java.util.HashMap;
import java.util.Map;
import com.kbstar.ksa.infra.po.AbstractDTO;
import com.kbstar.ksa.infra.po.KBData;

/**
 * IFRS 공통 DTO 클래스
 * 
 * 프로그램명: IFRSCommonDTO.java
 * 설명: 이 클래스는 모든 request에서 공통적으로 사용되는 정보를 표현한다.
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - IFRS 시스템 공통 데이터 관리
 * - 국가, 시간대, 통화 정보 관리
 * - 명령 ID, 라우팅 AS 관리
 * - PC 클래스명, 메서드명 관리
 * - SQL ID, 키 관리
 * - 에러 정보 관리
 * - 시간 정보 관리 (입력/출력 시간)
 * - 사용자 정보 관리
 * 
 * @version 1.30
 */
public class IFRSCommonDTO extends AbstractDTO implements ICommonDTO {

	/** KBData 객체 */
	private KBData kbData;

	/** 국가 */
	private String nation;

	/** 시간대 */
	private String timeZone;

	/** 기준 통화 */
	private String baseCurrency;

	/** IP 주소 */
	private String IPAddress;

	/** 언어 */
	private String language;

	/** 명령 ID (화면에서 설정) */
	private String commandId;

	/** 라우팅 AS (화면에서 설정) */
	private String routingAS;

	/** PC 클래스명 */
	private String pcClassName;

	/** PC 메서드명 */
	private String pcMethodName;

	/** SQL ID (화면에서 설정) */
	private String sqlId;

	/** 키 */
	private String key;

	/** 단말 타입 */
	private String danMalType;

	/** 입력 맵 */
	private HashMap fromMap;

	/** 출력 맵 */
	private HashMap toMap;

	/** 에러 맵 */
	private HashMap errorMap;

	/** 메타데이터 맵 */
	private HashMap metaDataMap;

	/** 결과 XML */
	private String resultXml;

	/** 포워드 이름 */
	private String forwardName;

	/** 그리드 이름 배열 */
	private String[] gridName;

	/** 반환 타입 (XML, Object) */
	private String returnType = "XML";

	/** 트랜잭션 코드 */
	private String txCd;

	/** 호스트 서버 */
	private String hostServer;

	/** 호스트 AS */
	private String hostAs;

	/** 에러 코드 */
	private String errorCode;

	/** 에러 메시지 */
	private String errorMsg;

	/** 단말 시퀀스 */
	private String danmalSeq;

	/** WAF 시퀀스 */
	private String wafSeq;

	/** 트랜잭션 번호 */
	private String transactionNo;

	/** 단말 입력 시간 */
	private String danmalInTime;

	/** 단말 출력 시간 */
	private String danmalOutTime;

	/** WAF 입력 시간 */
	private String wafInTime;

	/** WAF 출력 시간 */
	private String wafOutTime;

	/** AS 입력 시간 */
	private String asInTime;

	/** AS 출력 시간 */
	private String asOutTime;

	/** 사용자 ID */
	private String userId;

	/** 시스템 날짜 */
	private String sysDate;

	/** 비즈니스 날짜 */
	private String bizDate;

	/** 시퀀스 번호 */
	private String seqNo;

	/** 비즈니스 코드 */
	private String bizCode;

	/**
	 * 비즈니스 코드를 반환한다.
	 * 
	 * @return 비즈니스 코드
	 */
	public String getBizCode() {
		return bizCode;
	}

	/**
	 * 비즈니스 코드를 설정한다.
	 * 
	 * @param bizCode 비즈니스 코드
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	/**
	 * AS 입력 시간을 반환한다.
	 * 
	 * @return AS 입력 시간
	 */
	public String getAsInTime() {
		return asInTime;
	}

	/**
	 * AS 입력 시간을 설정한다.
	 * 
	 * @param asInTime AS 입력 시간
	 */
	public void setAsInTime(String asInTime) {
		this.asInTime = asInTime;
	}

	/**
	 * AS 출력 시간을 반환한다.
	 * 
	 * @return AS 출력 시간
	 */
	public String getAsOutTime() {
		return asOutTime;
	}

	/**
	 * AS 출력 시간을 설정한다.
	 * 
	 * @param asOutTime AS 출력 시간
	 */
	public void setAsOutTime(String asOutTime) {
		this.asOutTime = asOutTime;
	}

	/**
	 * 기준 통화를 반환한다.
	 * 
	 * @return 기준 통화
	 */
	public String getBaseCurrency() {
		return baseCurrency;
	}

	/**
	 * 기준 통화를 설정한다.
	 * 
	 * @param baseCurrency 기준 통화
	 */
	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	/**
	 * 비즈니스 날짜를 반환한다.
	 * 
	 * @return 비즈니스 날짜
	 */
	public String getBizDate() {
		return bizDate;
	}

	/**
	 * 비즈니스 날짜를 설정한다.
	 * 
	 * @param bizDate 비즈니스 날짜
	 */
	public void setBizDate(String bizDate) {
		this.bizDate = bizDate;
	}

	/**
	 * 명령 ID를 반환한다.
	 * 
	 * @return 명령 ID
	 */
	public String getCommandId() {
		return commandId;
	}

	/**
	 * 명령 ID를 설정한다.
	 * 
	 * @param commandId 명령 ID
	 */
	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	/**
	 * 단말 입력 시간을 반환한다.
	 * 
	 * @return 단말 입력 시간
	 */
	public String getDanmalInTime() {
		return danmalInTime;
	}

	/**
	 * 단말 입력 시간을 설정한다.
	 * 
	 * @param danmalInTime 단말 입력 시간
	 */
	public void setDanmalInTime(String danmalInTime) {
		this.danmalInTime = danmalInTime;
	}

	/**
	 * 단말 출력 시간을 반환한다.
	 * 
	 * @return 단말 출력 시간
	 */
	public String getDanmalOutTime() {
		return danmalOutTime;
	}

	/**
	 * 단말 출력 시간을 설정한다.
	 * 
	 * @param danmalOutTime 단말 출력 시간
	 */
	public void setDanmalOutTime(String danmalOutTime) {
		this.danmalOutTime = danmalOutTime;
	}

	/**
	 * 단말 시퀀스를 반환한다.
	 * 
	 * @return 단말 시퀀스
	 */
	public String getDanmalSeq() {
		return danmalSeq;
	}

	/**
	 * 단말 시퀀스를 설정한다.
	 * 
	 * @param danmalSeq 단말 시퀀스
	 */
	public void setDanmalSeq(String danmalSeq) {
		this.danmalSeq = danmalSeq;
	}

	/**
	 * 단말 타입을 반환한다.
	 * 
	 * @return 단말 타입
	 */
	public String getDanMalType() {
		return danMalType;
	}

	/**
	 * 단말 타입을 설정한다.
	 * 
	 * @param danMalType 단말 타입
	 */
	public void setDanMalType(String danMalType) {
		this.danMalType = danMalType;
	}

	/**
	 * 에러 코드를 반환한다.
	 * 
	 * @return 에러 코드
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 에러 코드를 설정한다.
	 * 
	 * @param errorCode 에러 코드
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * 에러 맵을 반환한다.
	 * 
	 * @return 에러 맵
	 */
	public HashMap getErrorMap() {
		return errorMap;
	}

	/**
	 * 에러 맵을 설정한다.
	 * 
	 * @param errorMap 에러 맵
	 */
	public void setErrorMap(HashMap errorMap) {
		this.errorMap = errorMap;
	}

	/**
	 * 에러 메시지를 반환한다.
	 * 
	 * @return 에러 메시지
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * 에러 메시지를 설정한다.
	 * 
	 * @param errorMsg 에러 메시지
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * 포워드 이름을 반환한다.
	 * 
	 * @return 포워드 이름
	 */
	public String getForwardName() {
		return forwardName;
	}

	/**
	 * 포워드 이름을 설정한다.
	 * 
	 * @param forwardName 포워드 이름
	 */
	public void setForwardName(String forwardName) {
		this.forwardName = forwardName;
	}

	/**
	 * 입력 맵을 반환한다.
	 * 
	 * @return 입력 맵
	 */
	public HashMap getFromMap() {
		return fromMap;
	}

	/**
	 * 입력 맵을 설정한다.
	 * 
	 * @param fromMap 입력 맵
	 */
	public void setFromMap(HashMap fromMap) {
		this.fromMap = fromMap;
	}

	/**
	 * 그리드 이름 배열을 반환한다.
	 * 
	 * @return 그리드 이름 배열
	 */
	public String[] getGridName() {
		return gridName;
	}

	/**
	 * 그리드 이름 배열을 설정한다.
	 * 
	 * @param gridName 그리드 이름 배열
	 */
	public void setGridName(String[] gridName) {
		this.gridName = gridName;
	}

	/**
	 * 호스트 AS를 반환한다.
	 * 
	 * @return 호스트 AS
	 */
	public String getHostAs() {
		return hostAs;
	}

	/**
	 * 호스트 AS를 설정한다.
	 * 
	 * @param hostAs 호스트 AS
	 */
	public void setHostAs(String hostAs) {
		this.hostAs = hostAs;
	}

	/**
	 * 호스트 서버를 반환한다.
	 * 
	 * @return 호스트 서버
	 */
	public String getHostServer() {
		return hostServer;
	}

	/**
	 * 호스트 서버를 설정한다.
	 * 
	 * @param hostServer 호스트 서버
	 */
	public void setHostServer(String hostServer) {
		this.hostServer = hostServer;
	}

	/**
	 * IP 주소를 반환한다.
	 * 
	 * @return IP 주소
	 */
	public String getIPAddress() {
		return IPAddress;
	}

	/**
	 * IP 주소를 설정한다.
	 * 
	 * @param address IP 주소
	 */
	public void setIPAddress(String address) {
		IPAddress = address;
	}

	/**
	 * 키를 반환한다.
	 * 
	 * @return 키
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 키를 설정한다.
	 * 
	 * @param key 키
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 언어를 반환한다.
	 * 
	 * @return 언어
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * 언어를 설정한다.
	 * 
	 * @param language 언어
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 국가를 반환한다.
	 * 
	 * @return 국가
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * 국가를 설정한다.
	 * 
	 * @param nation 국가
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}

	/**
	 * PC 클래스명을 반환한다.
	 * 
	 * @return PC 클래스명
	 */
	public String getPcClassName() {
		return pcClassName;
	}

	/**
	 * PC 클래스명을 설정한다.
	 * 
	 * @param pcClassName PC 클래스명
	 */
	public void setPcClassName(String pcClassName) {
		this.pcClassName = pcClassName;
	}

	/**
	 * PC 메서드명을 반환한다.
	 * 
	 * @return PC 메서드명
	 */
	public String getPcMethodName() {
		return pcMethodName;
	}

	/**
	 * PC 메서드명을 설정한다.
	 * 
	 * @param pcMethodName PC 메서드명
	 */
	public void setPcMethodName(String pcMethodName) {
		this.pcMethodName = pcMethodName;
	}

	/**
	 * 결과 XML을 반환한다.
	 * 
	 * @return 결과 XML
	 */
	public String getResultXml() {
		return resultXml;
	}

	/**
	 * 결과 XML을 설정한다.
	 * 
	 * @param resultXml 결과 XML
	 */
	public void setResultXml(String resultXml) {
		this.resultXml = resultXml;
	}

	/**
	 * 라우팅 AS를 반환한다.
	 * 
	 * @return 라우팅 AS
	 */
	public String getRoutingAS() {
		return routingAS;
	}

	/**
	 * 라우팅 AS를 설정한다.
	 * 
	 * @param routingAS 라우팅 AS
	 */
	public void setRoutingAS(String routingAS) {
		this.routingAS = routingAS;
	}

	/**
	 * SQL ID를 반환한다.
	 * 
	 * @return SQL ID
	 */
	public String getSqlId() {
		return sqlId;
	}

	/**
	 * SQL ID를 설정한다.
	 * 
	 * @param sqlId SQL ID
	 */
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	/**
	 * 시스템 날짜를 반환한다.
	 * 
	 * @return 시스템 날짜
	 */
	public String getSysDate() {
		return sysDate;
	}

	/**
	 * 시스템 날짜를 설정한다.
	 * 
	 * @param sysDate 시스템 날짜
	 */
	public void setSysDate(String sysDate) {
		this.sysDate = sysDate;
	}

	/**
	 * 시간대를 반환한다.
	 * 
	 * @return 시간대
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * 시간대를 설정한다.
	 * 
	 * @param timeZone 시간대
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * 출력 맵을 반환한다.
	 * 
	 * @return 출력 맵
	 */
	public HashMap getToMap() {
		return toMap;
	}

	/**
	 * 출력 맵을 설정한다.
	 * 
	 * @param toMap 출력 맵
	 */
	public void setToMap(HashMap toMap) {
		this.toMap = toMap;
	}

	/**
	 * 트랜잭션 번호를 반환한다.
	 * 
	 * @return 트랜잭션 번호
	 */
	public String getTransactionNo() {
		return transactionNo;
	}

	/**
	 * 트랜잭션 번호를 설정한다.
	 * 
	 * @param transactionNo 트랜잭션 번호
	 */
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
		this.seqNo = transactionNo;
	}

	/**
	 * 트랜잭션 코드를 반환한다.
	 * 
	 * @return 트랜잭션 코드
	 */
	public String getTxCd() {
		return txCd;
	}

	/**
	 * 트랜잭션 코드를 설정한다.
	 * 
	 * @param txCd 트랜잭션 코드
	 */
	public void setTxCd(String txCd) {
		this.txCd = txCd;
	}

	/**
	 * 사용자 ID를 반환한다.
	 * 
	 * @return 사용자 ID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 사용자 ID를 설정한다.
	 * 
	 * @param userId 사용자 ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * WAF 입력 시간을 반환한다.
	 * 
	 * @return WAF 입력 시간
	 */
	public String getWafInTime() {
		return wafInTime;
	}

	/**
	 * WAF 입력 시간을 설정한다.
	 * 
	 * @param wafInTime WAF 입력 시간
	 */
	public void setWafInTime(String wafInTime) {
		this.wafInTime = wafInTime;
	}

	/**
	 * WAF 출력 시간을 반환한다.
	 * 
	 * @return WAF 출력 시간
	 */
	public String getWafOutTime() {
		return wafOutTime;
	}

	/**
	 * WAF 출력 시간을 설정한다.
	 * 
	 * @param wafOutTime WAF 출력 시간
	 */
	public void setWafOutTime(String wafOutTime) {
		this.wafOutTime = wafOutTime;
	}

	/**
	 * WAF 시퀀스를 반환한다.
	 * 
	 * @return WAF 시퀀스
	 */
	public String getWafSeq() {
		return wafSeq;
	}

	/**
	 * WAF 시퀀스를 설정한다.
	 * 
	 * @param wafSeq WAF 시퀀스
	 */
	public void setWafSeq(String wafSeq) {
		this.wafSeq = wafSeq;
		this.seqNo = wafSeq;
	}

	/**
	 * KBData 객체를 반환한다.
	 * 
	 * @return KBData 객체
	 */
	public KBData getKbData() {
		return kbData;
	}

	/**
	 * KBData 객체를 설정한다.
	 * 
	 * @param kbData KBData 객체
	 */
	public void setKbData(KBData kbData) {
		this.kbData = kbData;
	}

	/**
	 * 파라미터 맵을 반환한다.
	 * 
	 * @return 파라미터 맵
	 */
	public HashMap getParameterMap() {
		return fromMap;
	}

	/**
	 * 결과 맵을 반환한다.
	 * 
	 * @return 결과 맵
	 */
	public HashMap getResultMap() {
		return toMap;
	}

	/**
	 * 반환 타입을 반환한다.
	 * 
	 * @return 반환 타입
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * 반환 타입을 설정한다.
	 * 
	 * @param returnType 반환 타입
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	/**
	 * 시퀀스 번호를 반환한다.
	 * 
	 * @return 시퀀스 번호
	 */
	public String getSeqNo() {
		return seqNo;
	}

	/**
	 * 시퀀스 번호를 설정한다.
	 * 
	 * @param seqNo 시퀀스 번호
	 */
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	/**
	 * 메타데이터 맵을 반환한다.
	 * 
	 * @return 메타데이터 맵
	 */
	public HashMap getMetaDataMap() {
		return metaDataMap;
	}

	/**
	 * 메타데이터 맵을 설정한다.
	 * 
	 * @param metaDataMap 메타데이터 맵
	 */
	public void setMetaDataMap(HashMap metaDataMap) {
		this.metaDataMap = metaDataMap;
	}

}
