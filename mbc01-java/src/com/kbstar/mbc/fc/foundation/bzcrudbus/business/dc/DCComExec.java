package com.kbstar.mbc.fc.foundation.bzcrudbus.business.dc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.kbstar.ksa.das.PKDuplicationException;
import com.kbstar.ksa.das.PersistenceException;
import com.kbstar.ksa.das.TooManyRowsException;
import com.kbstar.ksa.das.ibatis.KesaSqlMapClient;
import com.kbstar.ksa.das.ibatis.SqlMapper;
import com.kbstar.ksa.exception.FrameworkException;
import com.kbstar.ksa.logger.IKesaLogger;
import com.kbstar.ksa.logger.KesaLogHelper;
import com.kbstar.ksa.oltp.biz.IDomainComponent;
import com.kbstar.mbc.fc.foundation.bzcrudbus.exception.CommonDCException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.log.IIfrsLogger;
import com.kbstar.mbc.fc.foundation.bzcrudbus.log.IfrsLogHelper;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSCommonDTO;

/**
 * DC 공통 실행 클래스
 * 
 * 프로그램명: DCComExec.java
 * 설명: Domain Component 공통 실행 클래스로, 데이터베이스 접근 및 CRUD 작업을 담당한다.
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 데이터 조회 (Map, String, List 형태)
 * - 데이터 저장 (INSERT, UPDATE, DELETE)
 * - iBATIS SQL 매퍼를 통한 데이터베이스 접근
 * - 예외 처리 및 로깅
 * - 싱글톤 패턴 구현
 * 
 * @version 1.0
 */
public class DCComExec implements IDomainComponent {

	/** KESA 로거 */
	IKesaLogger logger = KesaLogHelper.getBiz();

	/** 싱글톤 인스턴스 */
	private static DCComExec instance;

	/**
	 * 싱글톤 인스턴스를 반환한다.
	 * 
	 * @return DCComExec 인스턴스
	 */
	public static synchronized DCComExec getInstance() {

		if (instance == null) {
			try {
				instance = new DCComExec();
			} catch (Exception igex) {
			}
		}
		return instance;

	}

	/**
	 * 기본 생성자
	 */
	public DCComExec() {
	}

	/**
	 * 조회결과를 Map 형태로 반환한다. statementName은 iBATIS XML에 정의되어 있어야 한다.
	 * 
	 * @param sqlId SQL ID
	 * @param map   파라미터 맵
	 * @return 조회 결과 맵
	 * @throws CommonDCException 공통 DC 예외
	 */
	public Map read(String sqlId, Map map) throws CommonDCException {
		try {
			return (Map) SqlMapper.getSqlMapClient().queryForObject(sqlId, map);
		} catch (TooManyRowsException e) {
			throw new CommonDCException("D9001001", e.toString());
		} catch (PKDuplicationException e) {
			throw new CommonDCException("D9001002", e.toString());
		} catch (PersistenceException e) {
			throw new CommonDCException("D9001003", e.toString());
		} catch (FrameworkException e) {
			throw new CommonDCException("D9001004", e.toString());
		} catch (Exception e) {
			throw new CommonDCException("D9001005", e.toString());
		}
	}

	/**
	 * IFRSCommonDTO를 사용하여 조회결과를 Map 형태로 반환한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @return 조회 결과 맵
	 * @throws CommonDCException 공통 DC 예외
	 */
	public Map read(IFRSCommonDTO commonDTO) throws CommonDCException {

		try {
			IIfrsLogger logger = IfrsLogHelper.getServer();
			String seq = commonDTO.getTransactionNo();
			Map map = commonDTO.getFromMap();
			String sqlId = commonDTO.getSqlId();
			String bizCd = commonDTO.getBizCode();
			logger.info(seq, "CommExecDC read (SQLID : " + sqlId + " / BizCD : " + bizCd + ")");
			KesaSqlMapClient sqlMapClient = null;
			if (bizCd == null) {
				sqlMapClient = SqlMapper.getSqlMapClient();
			} else {
				sqlMapClient = SqlMapper.getSqlMapClient(bizCd);
			}
			return (Map) sqlMapClient.queryForObject(sqlId, map);
		} catch (TooManyRowsException e) {
			throw new CommonDCException("D9002001", e.toString());
		} catch (PKDuplicationException e) {
			throw new CommonDCException("D9002002", e.toString());
		} catch (PersistenceException e) {
			throw new CommonDCException("D9002003", e.toString());
		} catch (FrameworkException e) {
			throw new CommonDCException("D9002004", e.toString());
		} catch (Exception e) {
			throw new CommonDCException("D9002005", e.toString());
		}
	}

	/**
	 * 조회결과를 String 형태로 반환한다. statementName은 iBATIS XML에 정의되어 있어야 한다.
	 * 
	 * @param sqlId SQL ID
	 * @param map   파라미터 맵
	 * @return 조회 결과 문자열
	 * @throws CommonDCException 공통 DC 예외
	 */
	public String readString(String sqlId, Map map) throws CommonDCException {
		try {
			return (String) SqlMapper.getSqlMapClient().queryForObject(sqlId, map);

		} catch (TooManyRowsException e) {
			throw new CommonDCException("D90090001", e.toString());
		} catch (PKDuplicationException e) {
			throw new CommonDCException("D9009002", e.toString());
		} catch (PersistenceException e) {
			throw new CommonDCException("D9009003", e.toString());
		} catch (FrameworkException e) {
			throw new CommonDCException("D9009004", e.toString());
		} catch (Exception e) {
			throw new CommonDCException("D9009005", e.toString());
		}
	}

	/**
	 * 조회결과를 String 형태로 반환한다. statementName은 iBATIS XML에 정의되어 있어야 한다.
	 * 
	 * @param sqlId SQL ID
	 * @param map   파라미터 맵
	 * @return 조회 결과 문자열
	 * @throws CommonDCException 공통 DC 예외
	 */
	public String readData(String sqlId, Map map) throws CommonDCException {
		try {
			Map resultMap = (Map) SqlMapper.getSqlMapClient().queryForObject(sqlId, map);

			Iterator values = resultMap.values().iterator();
			String res = "";
			while (values.hasNext()) {
				res = (values.next()).toString();
			}
			return res;
		} catch (TooManyRowsException e) {
			throw new CommonDCException("D90030001", e.toString());
		} catch (PKDuplicationException e) {
			throw new CommonDCException("D9003002", e.toString());
		} catch (PersistenceException e) {
			throw new CommonDCException("D9003003", e.toString());
		} catch (FrameworkException e) {
			throw new CommonDCException("D9003004", e.toString());
		} catch (Exception e) {
			throw new CommonDCException("D9003005", e.toString());
		}
	}

	/**
	 * IFRSCommonDTO를 사용하여 조회결과를 String 형태로 반환한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @return 조회 결과 문자열
	 * @throws CommonDCException 공통 DC 예외
	 */
	public String readData(IFRSCommonDTO commonDTO) throws CommonDCException {

		try {
			IIfrsLogger logger = IfrsLogHelper.getServer();
			String seq = commonDTO.getTransactionNo();
			Map map = commonDTO.getFromMap();
			String sqlId = commonDTO.getSqlId();
			String bizCd = commonDTO.getBizCode();
			logger.info(seq, "CommExecDC readData (SQLID : " + sqlId + " / BizCD : " + bizCd + ")");
			KesaSqlMapClient sqlMapClient = null;
			if (bizCd == null) {
				sqlMapClient = SqlMapper.getSqlMapClient();
			} else {
				sqlMapClient = SqlMapper.getSqlMapClient(bizCd);
			}

			Map resultMap = (Map) sqlMapClient.queryForObject(sqlId, map);

			Iterator values = resultMap.values().iterator();
			String res = "";
			while (values.hasNext()) {
				res = (values.next()).toString();
			}
			return res;
		} catch (TooManyRowsException e) {
			throw new CommonDCException("D9004001", e.toString());
		} catch (PKDuplicationException e) {
			throw new CommonDCException("D9004002", e.toString());
		} catch (PersistenceException e) {
			throw new CommonDCException("D9004003", e.toString());
		} catch (FrameworkException e) {
			throw new CommonDCException("D9004004", e.toString());
		} catch (Exception e) {
			throw new CommonDCException("D9004005", e.toString());
		}
	}

	/**
	 * 조회결과를 List 형태로 반환한다. statementName은 iBATIS XML에 정의되어 있어야 한다.
	 * 
	 * @param sqlId SQL ID
	 * @param map   파라미터 맵
	 * @return 조회 결과 리스트
	 * @throws CommonDCException 공통 DC 예외
	 */
	public List<HashMap> readList(String sqlId, Map map) throws CommonDCException {

		List result = null;
		try {
			result = (List) SqlMapper.getSqlMapClient().queryForList(sqlId, map);
		} catch (TooManyRowsException e) {
			throw new CommonDCException("D9005001", e.toString());
		} catch (PKDuplicationException e) {
			throw new CommonDCException("D9005002", e.toString());
		} catch (PersistenceException e) {
			throw new CommonDCException("D9005003", e.toString());
		} catch (FrameworkException e) {
			throw new CommonDCException("D9005004", e.toString());
		} catch (Exception e) {
			throw new CommonDCException("D9005005", e.toString());
		}
		return result;
	}

	/**
	 * IFRSCommonDTO를 사용하여 조회결과를 List 형태로 반환한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @return 조회 결과 리스트
	 * @throws CommonDCException 공통 DC 예외
	 */
	public List<HashMap> readList(IFRSCommonDTO commonDTO) throws CommonDCException {

		List result = null;
		try {
			IIfrsLogger logger = IfrsLogHelper.getServer();
			String seq = commonDTO.getTransactionNo();
			Map map = commonDTO.getFromMap();
			String sqlId = commonDTO.getSqlId();
			String bizCd = commonDTO.getBizCode();
			logger.info(seq, "CommExecDC readList (SQLID : " + sqlId + " / BizCD : " + bizCd + ")");
			KesaSqlMapClient sqlMapClient = null;
			if (bizCd == null) {
				sqlMapClient = SqlMapper.getSqlMapClient();
			} else {
				sqlMapClient = SqlMapper.getSqlMapClient(bizCd);
			}
			result = (List) sqlMapClient.queryForList(sqlId, map);
		} catch (TooManyRowsException e) {
			throw new CommonDCException("D9060001", e.toString());
		} catch (PKDuplicationException e) {
			throw new CommonDCException("D9006002", e.toString());
		} catch (PersistenceException e) {
			throw new CommonDCException("D9006003", e.toString());
		} catch (FrameworkException e) {
			throw new CommonDCException("D9006004", e.toString());
		} catch (Exception e) {
			throw new CommonDCException("D9006005", e.toString());
		}
		return result;
	}

	/**
	 * INSERT, UPDATE, DELETE 처리. statementName은 iBATIS XML에 정의되어 있어야 한다.
	 * 
	 * @param sqlId SQL ID
	 * @param map   파라미터 맵
	 * @return 처리된 행 수
	 * @throws CommonDCException 공통 DC 예외
	 */
	public int save(String sqlId, Map map) throws CommonDCException {

		int result = 0;
		try {
			result = (int) SqlMapper.getSqlMapClient().update(sqlId, map);
		} catch (TooManyRowsException e) {
			throw new CommonDCException("D9007001", e.toString());
		} catch (PKDuplicationException e) {
			throw new CommonDCException("D9007002", e.toString());
		} catch (PersistenceException e) {
			throw new CommonDCException("D9007003", e.toString());
		} catch (FrameworkException e) {
			throw new CommonDCException("D9007004", e.toString());
		} catch (Exception e) {
			throw new CommonDCException("D9007005", e.toString());
		}

		return result;
	}

	/**
	 * IFRSCommonDTO를 사용하여 INSERT, UPDATE, DELETE 처리를 수행한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @return 처리된 행 수
	 * @throws CommonDCException 공통 DC 예외
	 */
	public int save(IFRSCommonDTO commonDTO) throws CommonDCException {

		int result = 0;
		try {
			IIfrsLogger logger = IfrsLogHelper.getServer();
			String seq = commonDTO.getTransactionNo();
			Map map = commonDTO.getFromMap();
			String sqlId = commonDTO.getSqlId();
			String bizCd = commonDTO.getBizCode();
			logger.info(seq, "CommExecDC save (SQLID : " + sqlId + " / BizCD : " + bizCd + ")");
			KesaSqlMapClient sqlMapClient = null;
			if (bizCd == null) {
				sqlMapClient = SqlMapper.getSqlMapClient();
			} else {
				sqlMapClient = SqlMapper.getSqlMapClient(bizCd);
			}
			map.put("userId", commonDTO.getUserId());
			result = (int) sqlMapClient.update(sqlId, map);
		} catch (TooManyRowsException e) {
			throw new CommonDCException("D9008001", e.toString());
		} catch (PKDuplicationException e) {
			throw new CommonDCException("D9008002", e.toString());
		} catch (PersistenceException e) {
			throw new CommonDCException("D9008003", e.toString());
		} catch (FrameworkException e) {
			throw new CommonDCException("D9008004", e.toString());
		} catch (Exception e) {
			throw new CommonDCException("D9008005", e.toString());
		}

		return result;
	}

}
