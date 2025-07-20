package com.kbstar.mbc.fc.foundation.bzcrudbus.transfer;

import java.io.Serializable;
import java.util.HashMap;

import com.kbstar.ksa.infra.po.KBData;

/**
 * 공통 DTO 인터페이스
 * 
 * 프로그램명: ICommonDTO.java
 * 설명: 공통 데이터 전송 객체를 위한 인터페이스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - KBData 접근
 * - 파라미터 맵 관리
 * - 결과 맵 관리
 * - 메타데이터 맵 관리
 * - SQL ID 관리
 * - 비즈니스 코드 관리
 * - 트랜잭션 번호 관리
 * 
 * @version 1.0
 */
public interface ICommonDTO extends Serializable, Cloneable {

	/**
	 * KBData를 반환한다.
	 * 
	 * @return KBData 객체
	 */
	public KBData getKbData();

	/**
	 * 파라미터 맵을 반환한다.
	 * 
	 * @return 파라미터 맵
	 */
	public HashMap getParameterMap();

	/**
	 * 결과 맵을 반환한다.
	 * 
	 * @return 결과 맵
	 */
	public HashMap getResultMap();

	/**
	 * 메타데이터 맵을 반환한다.
	 * 
	 * @return 메타데이터 맵
	 */
	public HashMap getMetaDataMap();

	/**
	 * SQL ID를 반환한다.
	 * 
	 * @return SQL ID
	 */
	public String getSqlId();

	/**
	 * 비즈니스 코드를 반환한다.
	 * 
	 * @return 비즈니스 코드
	 */
	public String getBizCode();

	/**
	 * 트랜잭션 번호를 반환한다.
	 * 
	 * @return 트랜잭션 번호
	 */
	public String getTransactionNo();
}
