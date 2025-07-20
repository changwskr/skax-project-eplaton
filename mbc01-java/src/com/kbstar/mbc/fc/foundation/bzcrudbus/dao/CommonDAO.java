package com.kbstar.mbc.fc.foundation.bzcrudbus.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kbstar.mbc.fc.foundation.bzcrudbus.log.IIfrsLogger;
import com.kbstar.mbc.fc.foundation.bzcrudbus.log.IfrsLogHelper;

/**
 * 공통 데이터 액세스 객체 클래스
 * 
 * 프로그램명: CommonDAO.java
 * 설명: iBatis를 사용하여 데이터베이스 CRUD 작업을 수행하는 공통 DAO 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 단일 데이터 조회 (Map 반환)
 * - 단일 데이터 조회 (String 반환)
 * - 목록 데이터 조회 (List 반환)
 * - 데이터 저장/수정
 * - 프로시저 호출
 * - 싱글톤 패턴으로 인스턴스 관리
 *
 * @author
 * @version 1.0
 * @see
 */
public class CommonDAO {

    /** 싱글톤 인스턴스 */
    private static CommonDAO instance;

    /** 로거 */
    private IIfrsLogger logger = IfrsLogHelper.getWaf();

    /**
     * 싱글톤 인스턴스를 반환한다.
     * 
     * @return CommonDAO 인스턴스
     */
    public static synchronized CommonDAO getInstance() {
        if (instance == null) {
            instance = new CommonDAO();
        }
        return instance;
    }

    /**
     * 기본 생성자
     */
    public CommonDAO() {

    }

    /**
     * 조회결과를 Map으로 반환한다. statementName은 iBATIS XML에 정의되어 있어야 한다.
     * 
     * @param statementId SQL 문 ID
     * @param map         파라미터 맵
     * @return 조회 결과 Map
     * @throws Exception SQL 예외
     */
    public Map read(String statementId, Map map) throws Exception {
        try {
            return (Map) DBConnManager.getSqlMap().queryForObject(statementId, map);
        } catch (SQLException se) {
            throw (Exception) se;
        }
    }

    /**
     * 조회결과를 String으로 반환한다. statementName은 iBATIS XML에 정의되어 있어야 한다.
     * 
     * @param statementId SQL 문 ID
     * @param map         파라미터 맵
     * @return 조회 결과 String
     * @throws Exception SQL 예외
     */
    public String readData(String statementId, Map map) throws Exception {
        try {
            return (String) DBConnManager.getSqlMap().queryForObject(statementId, map);
        } catch (SQLException se) {
            throw (Exception) se;
        }
    }

    /**
     * 조회결과를 List로 반환한다. statementName은 iBATIS XML에 정의되어 있어야 한다.
     * 
     * @param statementId SQL 문 ID
     * @param map         파라미터 맵
     * @return 조회 결과 List
     * @throws Exception SQL 예외
     */
    public List readList(String statementId, Map map) throws Exception {

        List result = null;

        try {
            result = (List) DBConnManager.getSqlMap().queryForList(statementId, map);
        } catch (SQLException se) {
            throw (Exception) se;
        }

        return result;
    }

    /**
     * 데이터를 저장/수정한다. statementName은 iBATIS XML에 정의되어 있어야 한다.
     * 
     * @param statementId SQL 문 ID
     * @param map         파라미터 맵
     * @return 처리된 행 수
     * @throws Exception SQL 예외
     */
    public int save(String statementId, Map map) throws Exception {

        int result = 0;
        try {
            result = (int) DBConnManager.getSqlMap().update(statementId, map);
        } catch (SQLException se) {
            throw (Exception) se;
        }

        return result;
    }

    /**
     * 프로시저를 호출한다.
     * 
     * @param statementId SQL 문 ID
     * @param map         파라미터 맵
     * @return 프로시저 실행 결과 Map
     * @throws Exception SQL 예외
     */
    public Map callProcedure(String statementId, Map map) throws Exception {

        try {
            return (Map) DBConnManager.getSqlMap().queryForObject(statementId, map);
        } catch (SQLException se) {
            throw (Exception) se;
        }
    }

}
