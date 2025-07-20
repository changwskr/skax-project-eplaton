package com.kbstar.mbc.fc.foundation.bzcrudbus.transfer;

import javax.servlet.http.HttpServletRequest;

import com.kbstar.ksa.infra.po.KBData;

/**
 * IFRS 이벤트 클래스
 * 
 * 프로그램명: IFRSEvent.java
 * 설명: 이 클래스는 business layer와 application layer 사이에 데이터를 주고받는
 * 컨테이너 역할을 한다.<br>
 * Application layer에서 business layer로 전달되는 데이터는 request field에 저장되고,
 * Business layer에서 application layer로 전달되는 데이터는 response field에 저장된다.
 * 또한, Application layer에서 business layer로 전달되는 request에는 항상 공통적으로 사용되는
 * 공통 데이터가 common field에 저장된다.
 * 
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - IFRS 공통 DTO 관리
 * - KBData 관리
 * - HTTP 서블릿 요청 관리
 * - 예외 처리
 * 
 * @version 1.0
 */
public final class IFRSEvent {

	/** IFRS 공통 DTO */
	private IFRSCommonDTO commonDto;

	/** KBData 객체 */
	private KBData kbData;

	/** HTTP 서블릿 요청 객체 */
	private HttpServletRequest servletRequest;

	/** 예외 객체 */
	private Exception exception;

	/**
	 * 기본 생성자.
	 */
	public IFRSEvent() {
		this.commonDto = new IFRSCommonDTO();
	}

	/**
	 * 모든 request에서 항상 공통적으로 사용되는 공통 데이터를 설정한다.<br>
	 *
	 * @param kbData KBData 객체
	 */
	public void setKBData(KBData kbData) {
		this.kbData = kbData;
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
	 * IFRS 공통 DTO를 반환한다.
	 * 
	 * @return IFRS 공통 DTO
	 */
	public IFRSCommonDTO getCommonDto() {
		return commonDto;
	}

	/**
	 * IFRS 공통 DTO를 설정한다.
	 * 
	 * @param commondto IFRS 공통 DTO
	 */
	public void setCommonDto(IFRSCommonDTO commondto) {
		this.commonDto = commondto;
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
	 * HTTP 서블릿 요청 객체를 반환한다.
	 * 
	 * @return HTTP 서블릿 요청 객체
	 */
	public HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	/**
	 * HTTP 서블릿 요청 객체를 설정한다.
	 * 
	 * @param servletRequest HTTP 서블릿 요청 객체
	 */
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	/**
	 * 예외 객체를 반환한다.
	 * 
	 * @return 예외 객체
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * 예외 객체를 설정한다.
	 * 
	 * @param exception 예외 객체
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
}
