package com.kbstar.mbc.fc.foundation.bzcrudbus.log;

import org.apache.log4j.LogManager;
import org.apache.log4j.spi.LoggerFactory;

import com.kbstar.ksa.logger.KesaLoggerFactory;

/**
 * IFRS 로거 팩토리 클래스
 * 
 * 프로그램명: IfrsLoggerFactory.java
 * 설명: IFRS 로거 인스턴스를 생성하는 팩토리 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - IFRS 로거 인스턴스 생성
 * - 이름 기반 로거 생성
 * - 클래스 기반 로거 생성
 * - 루트 로거 생성
 * - 커스텀 팩토리 기반 로거 생성
 * 
 * @version 1.0
 */
public class IfrsLoggerFactory extends KesaLoggerFactory {

	/**
	 * 이름으로 IFRS 로거를 생성한다.
	 * 
	 * @param name 로거 이름
	 * @return IFRS 로거 인스턴스
	 */
	public static IIfrsLogger getIfrsLogger(String name) {
		return new IfrsLoggerImpl(LogManager.getLogger(name));
	}

	/**
	 * 클래스로 IFRS 로거를 생성한다.
	 * 
	 * @param clazz 로거를 생성할 클래스
	 * @return IFRS 로거 인스턴스
	 */
	public static IIfrsLogger getIfrsLogger(Class clazz) {
		return getIfrsLogger(clazz.getName());
	}

	/**
	 * 루트 IFRS 로거를 생성한다.
	 * 
	 * @return 루트 IFRS 로거 인스턴스
	 */
	public static IIfrsLogger getRootIfrsLogger() {
		return new IfrsLoggerImpl(LogManager.getRootLogger());
	}

	/**
	 * 커스텀 팩토리로 IFRS 로거를 생성한다.
	 * 
	 * @param name    로거 이름
	 * @param factory 로거 팩토리
	 * @return IFRS 로거 인스턴스
	 */
	public static IIfrsLogger getIfrsLogger(String name, LoggerFactory factory) {
		return new IfrsLoggerImpl(LogManager.getLogger(name, factory));
	}
}
