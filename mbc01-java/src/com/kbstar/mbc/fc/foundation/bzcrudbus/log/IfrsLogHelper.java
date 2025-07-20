package com.kbstar.mbc.fc.foundation.bzcrudbus.log;

/**
 * IFRS 로그 헬퍼 클래스
 * 
 * 프로그램명: IfrsLogHelper.java
 * 설명: IFRS 시스템의 다양한 로거 인스턴스를 제공하는 헬퍼 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - WAF 로거 제공
 * - 서버 로거 제공
 * - 공통 로거 제공
 * - MBA/MBB/MBC 로거 제공
 * - HDD 로거 제공
 * - 실시간 로거 제공
 * 
 * @version 1.0
 */
public class IfrsLogHelper {

    /**
     * WAF 로거를 반환한다.
     * 
     * @return WAF 로거 인스턴스
     */
    public static IIfrsLogger getWaf() {
        return IfrsLoggerFactory.getIfrsLogger("log.ifrs.waf");
    }

    /**
     * 서버 로거를 반환한다.
     * 
     * @return 서버 로거 인스턴스
     */
    public static IIfrsLogger getServer() {
        return IfrsLoggerFactory.getIfrsLogger("log.ifrs.server");
    }

    /**
     * 공통 로거를 반환한다.
     * 
     * @return 공통 로거 인스턴스
     */
    public static IIfrsLogger getCom() {
        return IfrsLoggerFactory.getIfrsLogger("log.ifrs.com");
    }

    /**
     * MBA 로거를 반환한다.
     * 
     * @return MBA 로거 인스턴스
     */
    public static IIfrsLogger getMBA() {
        return IfrsLoggerFactory.getIfrsLogger("log.ifrs.mba");
    }

    /**
     * MBB 로거를 반환한다.
     * 
     * @return MBB 로거 인스턴스
     */
    public static IIfrsLogger getMBB() {
        return IfrsLoggerFactory.getIfrsLogger("log.ifrs.mbb");
    }

    /**
     * MBC 로거를 반환한다.
     * 
     * @return MBC 로거 인스턴스
     */
    public static IIfrsLogger getMBC() {
        return IfrsLoggerFactory.getIfrsLogger("log.ifrs.mbc");
    }

    /**
     * HDD 로거를 반환한다.
     * 
     * @return HDD 로거 인스턴스
     */
    public static IIfrsLogger getHDD() {
        return IfrsLoggerFactory.getIfrsLogger("log.ifrs.hdd");
    }

    /**
     * WAF 실시간 로거를 반환한다.
     * 
     * @return WAF 실시간 로거 인스턴스
     */
    public static IIfrsLogger getWafInTime() {
        return IfrsLoggerFactory.getIfrsLogger("log.ifrs.wafintime");
    }

    /**
     * AS 실시간 로거를 반환한다.
     * 
     * @return AS 실시간 로거 인스턴스
     */
    public static IIfrsLogger getAsInTime() {
        return IfrsLoggerFactory.getIfrsLogger("log.ifrs.asintime");
    }
}
