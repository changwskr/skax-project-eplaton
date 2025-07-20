package com.kbstar.mbc.fc.foundation.bzcrudbus.dao;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.kbstar.mbc.fc.foundation.bzcrudbus.config.IFRSConfig;

/**
 * 데이터베이스 연결 관리 클래스
 * 
 * 프로그램명: DBConnManager.java
 * 설명: iBatis SqlMapClient를 관리하고 데이터베이스 연결을 제공하는 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - SqlMapClient 인스턴스 관리
 * - 데이터베이스 연결 설정
 * - 시퀀스 전용 SqlMapClient 관리
 * - 싱글톤 패턴으로 인스턴스 관리
 */
public class DBConnManager {

	/** 싱글톤 인스턴스 */
	private static DBConnManager instance;

	/** 기본 SqlMapClient */
	private static SqlMapClient sqlMap;
	/** 시퀀스 전용 SqlMapClient */
	private static SqlMapClient sqlMapSeq;

	/** SQL XML 설정 파일 경로 */
	private static String SQL_XML = IFRSConfig.getInstance().getInfo("ConfigFile", "DAO");
	/** 시퀀스 XML 설정 파일 경로 */
	private static String SEQ_XML = IFRSConfig.getInstance().getInfo("ConfigFile", "DAO");
	// private static final String SEQ_XML = "kdic/ksis/common/db/etc_sql.xml";

	/**
	 * DBControlManager 인스턴스를 반환
	 * 
	 * @return DBControlManager
	 */
	/*
	 * public static synchronized DBConnManager getInstance() {
	 * if (instance == null) {
	 * instance = new DBConnManager();
	 * }
	 * return instance;
	 * }
	 */
	/**
	 * 기본 생성자
	 */
	public DBConnManager() {
	}

	/**
	 * SqlMapClient 객체를 반환하는 메서드(인스턴스화)
	 * 
	 * @return SqlMapClient
	 */
	public SqlMapClient buildSqlMap() {
		SqlMapClient mapClient = null;
		try {
			mapClient = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(SQL_XML));
		} catch (Exception ex) {
		}
		return mapClient;
	}

	/**
	 * SqlMapClient 객체를 반환하는 메서드(정적객체반환)
	 * 
	 * @return SqlMapClient
	 */
	public static SqlMapClient getSqlMap() {
		if (sqlMap == null) {
			try {
				sqlMap = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(SQL_XML));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return sqlMap;
	}

	/**
	 * 시퀀스 코드를 관리하는 SqlMapClient 객체를 반환하는 메서드(정적객체반환)
	 * 
	 * @return SqlMapClient
	 */
	public static SqlMapClient getSeqSqlMap() {
		if (sqlMapSeq == null) {
			try {
				sqlMapSeq = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(SEQ_XML));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return sqlMapSeq;
	}

	/**
	 * 정적 초기화 블록
	 */
	static {
		try {
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(SQL_XML));
			sqlMapSeq = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(SEQ_XML));
		} catch (Exception ex) {
		}
	}

}
