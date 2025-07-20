/*
 * (@)# DCAccount.java
 *
 * Copyright KB Kookmin Bank Inc. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.kbstar.mbc.dc.accountdc;

import java.util.List;

import com.kbstar.ksa.das.PKDuplicationException;
import com.kbstar.ksa.das.PersistenceException;
import com.kbstar.ksa.das.RecordNotFoundException;
import com.kbstar.ksa.das.TooManyRowsException;
import com.kbstar.ksa.das.ibatis.SqlMapper;
import com.kbstar.ksa.exception.BusinessException;
import com.kbstar.ksa.exception.FrameworkException;
import com.kbstar.ksa.logger.IKesaLogger;
import com.kbstar.ksa.logger.KesaLogHelper;
import com.kbstar.ksa.oltp.biz.IDomainComponent;
import com.kbstar.ksa.util.ObjectUtil;
import com.kbstar.mbc.dc.accountdc.dto.AccountDDTO;

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
 * </ul>
 */
public class DCAccount implements IDomainComponent {
	IKesaLogger logger = KesaLogHelper.getBiz();

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
	public AccountDDTO getAccount(AccountDDTO accountDDTO) throws BusinessException {
		try {
			Account account = (Account) SqlMapper.getSqlMapClient()
					.queryForObject("account.getAccount", ObjectUtil.copyForClass(Account.class, accountDDTO));
			return ObjectUtil.copyForClass(AccountDDTO.class, account);
		} catch (TooManyRowsException e) {
			throw new BusinessException("B0100001", "processCode", e);
		} catch (PKDuplicationException e) {
			throw new BusinessException("B0100001", "processCode", e);
		} catch (PersistenceException e) {
			throw new BusinessException("B0100001", "processCode", e);
		} catch (FrameworkException e) {
			throw new BusinessException("B0100001", "processCode", e);
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
	public void updateAccount(AccountDDTO accountDDTO) throws BusinessException {
		try {
			int count = SqlMapper.getSqlMapClient().update("account.updateAccount",
					ObjectUtil.copyForClass(Account.class, accountDDTO));
			if (logger.isDebugEnabled())
				logger.debug(this.getClass().getName() + ", update count = " + count);
		} catch (PKDuplicationException e) {
			throw new BusinessException("B0000002", "processCode", e);
		} catch (PersistenceException e) {
			throw new BusinessException("B0000002", "processCode", e);
		} catch (FrameworkException e) {
			throw new BusinessException("B0000002", "processCode", e);
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
	public void deleteAccount(AccountDDTO accountDDTO) throws BusinessException {
		try {
			int count = SqlMapper.getSqlMapClient().delete("account.deleteAccount",
					ObjectUtil.copyForClass(Account.class, accountDDTO));
			if (logger.isDebugEnabled())
				logger.debug(this.getClass().getName() + ", delete count = " + count);
		} catch (RecordNotFoundException e) {
			throw new BusinessException("B0000001", "processCode", e);
		} catch (PersistenceException e) {
			throw new BusinessException("B0000002", "processCode", e);
		} catch (FrameworkException e) {
			throw new BusinessException("B0000002", "processCode", e);
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
	public void createAccount(AccountDDTO accountDDTO) throws BusinessException {
		try {
			SqlMapper.getSqlMapClient().insert("account.createAccount",
					ObjectUtil.copyForClass(Account.class, accountDDTO));
		} catch (RecordNotFoundException e) {
			throw new BusinessException("B0000001", "processCode", e);
		} catch (PersistenceException e) {
			throw new BusinessException("B0000002", "processCode", e);
		} catch (FrameworkException e) {
			throw new BusinessException("B0000002", "processCode", e);
		}
	}

	public List<AccountDDTO> getListAccount(AccountDDTO accountDDTO) throws BusinessException {
		try {
			List<Account> accountList = SqlMapper.getSqlMapClient().queryForList("account.getListAccount",
					ObjectUtil.copyForClass(Account.class, accountDDTO));
			return ObjectUtil.copyForList(AccountDDTO.class, accountList);
		} catch (RecordNotFoundException e) {
			throw new BusinessException("B0000001", "processCode", e);
		} catch (PersistenceException e) {
			throw new BusinessException("B0000002", "processCode", e);
		} catch (FrameworkException e) {
			throw new BusinessException("B0000002", "processCode", e);
		}
	}

}
