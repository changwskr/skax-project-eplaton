package com.kbstar.mbc.fc.foundation.bzcrudbus.exception;

/**
 * 공통 PC 예외 클래스
 * 
 * 프로그램명: CommonPCException.java
 * 설명: 공통 Presentation Control 관련 예외를 처리하는 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 공통 PC 관련 예외 처리
 * - 에러 코드 관리
 * - 에러 메시지 관리
 */
public class CommonPCException extends Exception {

	/**
	 * 직렬화 버전 ID
	 */
	private static final long serialVersionUID = 1L;

	/** 에러 코드 */
	protected String errorCode = "ECOMPC";

	/**
	 * 기본 생성자
	 */
	public CommonPCException() {
		super();
	}

	/**
	 * 에러 코드와 메시지를 받는 생성자
	 * 
	 * @param errorCode 에러 코드
	 * @param errorMsg  에러 메시지
	 */
	public CommonPCException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}

	/**
	 * 에러 코드를 반환한다.
	 * 
	 * @return 에러 코드
	 */
	public String getErrorCode() {
		return this.errorCode;
	}
}
