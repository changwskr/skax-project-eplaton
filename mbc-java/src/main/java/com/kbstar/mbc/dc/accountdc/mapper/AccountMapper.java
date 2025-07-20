package com.kbstar.mbc.dc.accountdc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kbstar.mbc.dc.accountdc.Account;
import com.kbstar.mbc.dc.accountdc.dto.AccountDDTO;

/**
 * Account MyBatis 매퍼 인터페이스
 * 
 * 프로그램명: AccountMapper.java
 * 설명: 계정 관리 관련 데이터베이스 작업을 위한 MyBatis 매퍼 인터페이스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 계정 CRUD 작업
 * - 계정 조회 및 검색
 * 
 * @version 1.0
 */
@Mapper
public interface AccountMapper {

    /**
     * 계정 조회
     * 
     * @param accountDDTO 계정 조회 조건
     * @return 계정 정보
     */
    Account getAccount(AccountDDTO accountDDTO);

    /**
     * 계정 목록 조회
     * 
     * @param accountDDTO 계정 조회 조건
     * @return 계정 목록
     */
    List<Account> getListAccount(AccountDDTO accountDDTO);

    /**
     * 계정 생성
     * 
     * @param accountDDTO 계정 정보
     * @return 생성된 행 수
     */
    int createAccount(AccountDDTO accountDDTO);

    /**
     * 계정 수정
     * 
     * @param accountDDTO 계정 정보
     * @return 수정된 행 수
     */
    int updateAccount(AccountDDTO accountDDTO);

    /**
     * 계정 삭제
     * 
     * @param accountDDTO 계정 정보
     * @return 삭제된 행 수
     */
    int deleteAccount(AccountDDTO accountDDTO);

    /**
     * 계좌번호로 계정 조회
     * 
     * @param accountNumber 계좌번호
     * @return 계정 정보
     */
    Account getAccountByNumber(@Param("accountNumber") String accountNumber);

    /**
     * 계정 존재 여부 확인
     * 
     * @param accountNumber 계좌번호
     * @return 존재 여부 (1: 존재, 0: 존재하지 않음)
     */
    int existsAccount(@Param("accountNumber") String accountNumber);

    /**
     * 잔액 업데이트
     * 
     * @param accountNumber 계좌번호
     * @param netAmount     잔액
     * @return 업데이트된 행 수
     */
    int updateBalance(@Param("accountNumber") String accountNumber, @Param("netAmount") double netAmount);

    /**
     * 마지막 거래일 업데이트
     * 
     * @param accountNumber   계좌번호
     * @param lastTransaction 마지막 거래일
     * @return 업데이트된 행 수
     */
    int updateLastTransaction(@Param("accountNumber") String accountNumber,
            @Param("lastTransaction") String lastTransaction);
}