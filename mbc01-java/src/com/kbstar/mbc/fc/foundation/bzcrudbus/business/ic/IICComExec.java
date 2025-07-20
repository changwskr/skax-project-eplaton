package com.kbstar.mbc.fc.foundation.bzcrudbus.business.ic;

import java.util.HashMap;
import java.util.List;

import com.kbstar.ksa.oltp.biz.IProcessComponent;
import com.kbstar.mbc.fc.foundation.bzcrudbus.exception.CommonPCException;
import com.kbstar.mbc.fc.foundation.bzcrudbus.transfer.IFRSCommonDTO;

/**
 * IC 공통 실행 인터페이스
 * 
 * 프로그램명: IICComExec.java
 * 설명: Integration Component 공통 실행을 위한 인터페이스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 데이터 조회 (단일 데이터, 리스트)
 * - 데이터 저장 (INSERT, UPDATE, DELETE)
 * - 그리드 데이터 처리 (CRUD 작업)
 * - DC 계층과의 연동
 * 
 * @version 1.0
 */
public interface IICComExec extends IProcessComponent {

	/**
	 * IFRSCommonDTO를 사용하여 데이터를 조회한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @return 조회 결과 문자열
	 * @throws CommonPCException 공통 PC 예외
	 */
	public String getData(IFRSCommonDTO commonDTO) throws CommonPCException;

	/**
	 * IFRSCommonDTO를 사용하여 리스트를 조회한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @return 조회 결과 리스트
	 * @throws CommonPCException 공통 PC 예외
	 */
	public List<HashMap> getList(IFRSCommonDTO commonDTO) throws CommonPCException;

	/**
	 * IFRSCommonDTO를 사용하여 데이터를 저장한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @return 처리된 행 수
	 * @throws CommonPCException 공통 PC 예외
	 */
	public int saveData(IFRSCommonDTO commonDTO) throws CommonPCException;

	/**
	 * 그리드 데이터 삽입을 수행한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @throws CommonPCException 공통 PC 예외
	 */
	public void insertList(IFRSCommonDTO commonDTO) throws CommonPCException;

	/**
	 * 그리드 데이터 업데이트를 수행한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @throws CommonPCException 공통 PC 예외
	 */
	public void updateList(IFRSCommonDTO commonDTO) throws CommonPCException;

	/**
	 * 그리드 데이터 삭제를 수행한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @throws CommonPCException 공통 PC 예외
	 */
	public void deleteList(IFRSCommonDTO commonDTO) throws CommonPCException;

	/**
	 * 그리드 데이터 저장을 수행한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @throws CommonPCException 공통 PC 예외
	 */
	public void saveList(IFRSCommonDTO commonDTO) throws CommonPCException;

	/**
	 * 그리드 데이터 전체 저장을 수행한다.
	 * 
	 * @param commonDTO IFRS 공통 DTO
	 * @throws CommonPCException 공통 PC 예외
	 */
	public void saveGrid(IFRSCommonDTO commonDTO) throws CommonPCException;

}
