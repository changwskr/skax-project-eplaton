/*
 * (@)# DCAccount.java
 *
 * Copyright KB Kookmin Bank Inc. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.kbstar.mbc.dc.accountdc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kbstar.ksa.das.NewPKDuplicationException;
import com.kbstar.ksa.das.NewPersistenceException;
import com.kbstar.ksa.das.NewRecordNotFoundException;
import com.kbstar.ksa.das.NewTooManyRowsException;
import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.exception.NewFrameworkException;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.oltp.biz.NewIDomainComponent;
import com.kbstar.ksa.util.NewObjectUtil;
import com.kbstar.mbc.dc.accountdc.dto.AccountDDTO;
import com.kbstar.mbc.dc.accountdc.mapper.AccountMapper;

/**
 * <br>
 * [프로그램명] DC계정
 * <br>
 * [설명] 계정에 대한 데이터를 처리하는 도메인 컴포넌트
 * <br>
 * [상세설명]
 * <br>
 * [변경이력]
 * <ul>
 * <li>2008-08-26::전체::최초작성
 * <li>2024-01-01::전체::MyBatis 마이그레이션
 * </ul>
 */
@Repository
public class DCAccount implements NewIDomainComponent {
	NewIKesaLogger logger = NewKesaLogHelper.getBiz();

	@Autowired
	private AccountMapper accountMapper;

	/**
	 * <br>
	 * [메서드명] 계정조회
	 * <br>
	 * [설명]
	 * <br>
	 * [상세설명]
	 * 
	 * @param AccountDDTO
	 *                    <ul>
	 *                    <li>accountNumber //계좌번호
	 *                    </ul>
	 * @return AccountDDTO
	 *         <ul>
	 *         <li>accountNumber //계좌번호
	 *         <li>name //이름
	 *         <li>identificationNumber //주민번호
	 *         <li>interestRate //이자율
	 *         <li>lastTransaction //마지막거래일
	 *         <li>password //비밀번호
	 *         <li>netAmount //잔액
	 *         </ul>
	 */
	public AccountDDTO getAccount(AccountDDTO accountDDTO) throws NewBusinessException {
		try {
			Account account = accountMapper.getAccount(accountDDTO);
			return NewObjectUtil.copyForClass(AccountDDTO.class, account);
		} catch (Exception e) {
			throw new NewBusinessException("B0100001", "processCode", e);
		}
	}

	/**
	 * <br>
	 * [메서드명] 계정수정
	 * <br>
	 * [설명]
	 * <br>
	 * [상세설명]
	 * 
	 * @param AccountDDTO
	 *                    <ul>
	 *                    <li>accountNumber //계좌번호
	 *                    <li>name //이름
	 *                    <li>identificationNumber //주민번호
	 *                    <li>interestRate //이자율
	 *                    <li>lastTransaction //마지막거래일
	 *                    <li>password //비밀번호
	 *                    <li>netAmount //잔액
	 *                    </ul>
	 * @return void
	 */
	public void updateAccount(AccountDDTO accountDDTO) throws NewBusinessException {
		try {
			int count = accountMapper.updateAccount(accountDDTO);
			if (logger.isDebugEnabled())
				logger.debug(this.getClass().getName() + ", update count = " + count);
		} catch (Exception e) {
			throw new NewBusinessException("B0000002", "processCode", e);
		}
	}

	/**
	 * <br>
	 * [메서드명] 계정삭제
	 * <br>
	 * [설명]
	 * <br>
	 * [상세설명]
	 * 
	 * @param AccountDDTO
	 *                    <ul>
	 *                    <li>accountNumber //계좌번호
	 *                    </ul>
	 * @return void
	 */
	public void deleteAccount(AccountDDTO accountDDTO) throws NewBusinessException {
		try {
			int count = accountMapper.deleteAccount(accountDDTO);
			if (logger.isDebugEnabled())
				logger.debug(this.getClass().getName() + ", delete count = " + count);
		} catch (Exception e) {
			throw new NewBusinessException("B0000002", "processCode", e);
		}
	}

	/**
	 * <br>
	 * [메서드명] 계정생성
	 * <br>
	 * [설명]
	 * <br>
	 * [상세설명]
	 * 
	 * @param AccountDDTO
	 *                    <ul>
	 *                    <li>accountNumber //계좌번호
	 *                    <li>name //이름
	 *                    <li>identificationNumber //주민번호
	 *                    <li>interestRate //이자율
	 *                    <li>lastTransaction //마지막거래일
	 *                    <li>password //비밀번호
	 *                    <li>netAmount //잔액
	 *                    </ul>
	 * @return void
	 */
	public void createAccount(AccountDDTO accountDDTO) throws NewBusinessException {
		try {
			accountMapper.createAccount(accountDDTO);
		} catch (Exception e) {
			throw new NewBusinessException("B0000002", "processCode", e);
		}
	}

	public List<AccountDDTO> getListAccount(AccountDDTO accountDDTO) throws NewBusinessException {
		try {
			List<Account> accountList = accountMapper.getListAccount(accountDDTO);
			return NewObjectUtil.copyForList(AccountDDTO.class, accountList);
		} catch (Exception e) {
			throw new NewBusinessException("B0000002", "processCode", e);
		}
	}

}
