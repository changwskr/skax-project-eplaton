/*
 * (@)# PCAccount.java
 *
 * Copyright KB Kookmin Bank Inc. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.kbstar.mbc.pc.accountpc;

import java.util.List;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.oltp.biz.NewIProcessComponent;
import com.kbstar.ksa.util.NewObjectUtil;
import com.kbstar.mbc.dc.accountdc.DCAccount;
import com.kbstar.mbc.dc.accountdc.dto.AccountDDTO;
import com.kbstar.mbc.pc.dto.AccountPDTO;

/**
 * PC Component for Account Management
 * 
 * Program Name: PCAccount.java
 * Description: Process component for handling account information
 * 
 * Change History:
 * <ul>
 * <li>2008-08-26::Initial::First Creation
 * </ul>
 */
public class PCAccount implements NewIProcessComponent {

	NewIKesaLogger logger = NewKesaLogHelper.getBiz();

	/**
	 * Method Name: getAccount
	 * Description: Retrieve account information
	 * 
	 * @param AccountPDTO
	 *                    <ul>
	 *                    <li>accountNumber // Account number
	 *                    </ul>
	 * @return AccountPDTO
	 *         <ul>
	 *         <li>accountNumber // Account number
	 *         <li>name // Name
	 *         <li>identificationNumber // ID number
	 *         <li>interestRate // Interest rate
	 *         <li>lastTransaction // Last transaction date
	 *         <li>password // Password
	 *         <li>netAmount // Balance
	 *         </ul>
	 */
	public AccountPDTO getAccount(AccountPDTO accountPDTO) throws NewBusinessException {
		AccountDDTO accountDDTO = new DCAccount()
				.getAccount(NewObjectUtil.copyForClass(AccountDDTO.class, accountPDTO));
		return NewObjectUtil.copyForClass(AccountPDTO.class, accountDDTO);
	}

	/**
	 * Method Name: updateAccount
	 * Description: Update account information
	 * 
	 * @param AccountPDTO
	 *                    <ul>
	 *                    <li>accountNumber // Account number
	 *                    <li>name // Name
	 *                    <li>identificationNumber // ID number
	 *                    <li>interestRate // Interest rate
	 *                    <li>lastTransaction // Last transaction date
	 *                    <li>password // Password
	 *                    <li>netAmount // Balance
	 *                    </ul>
	 * @return void
	 */
	public void updateAccount(AccountPDTO accountPDTO) throws NewBusinessException {
		new DCAccount().updateAccount(NewObjectUtil.copyForClass(AccountDDTO.class, accountPDTO));
	}

	/**
	 * Method Name: deleteAccount
	 * Description: Delete account
	 * 
	 * @param AccountPDTO
	 *                    <ul>
	 *                    <li>accountNumber // Account number
	 *                    </ul>
	 * @return void
	 */
	public void deleteAccount(AccountPDTO accountPDTO) throws NewBusinessException {
		new DCAccount().deleteAccount(NewObjectUtil.copyForClass(AccountDDTO.class, accountPDTO));
	}

	/**
	 * Method Name: createAccount
	 * Description: Create new account
	 * 
	 * @param AccountPDTO
	 *                    <ul>
	 *                    <li>accountNumber // Account number
	 *                    <li>name // Name
	 *                    <li>identificationNumber // ID number
	 *                    <li>interestRate // Interest rate
	 *                    <li>lastTransaction // Last transaction date
	 *                    <li>password // Password
	 *                    <li>netAmount // Balance
	 *                    </ul>
	 * @return void
	 */
	public void createAccount(AccountPDTO accountPDTO) throws NewBusinessException {
		new DCAccount().createAccount(NewObjectUtil.copyForClass(AccountDDTO.class, accountPDTO));
	}

	/**
	 * Method Name: getListAccount
	 * Description: Get account list
	 * 
	 * @param AccountPDTO
	 *                    <ul>
	 *                    <li>accountNumber // Account number
	 *                    </ul>
	 * @return List <AccountPDTO>
	 *         <ul>
	 *         <li>accountNumber // Account number
	 *         <li>name // Name
	 *         <li>identificationNumber // ID number
	 *         <li>interestRate // Interest rate
	 *         <li>lastTransaction // Last transaction date
	 *         <li>password // Password
	 *         <li>netAmount // Balance
	 *         </ul>
	 */

	public List<AccountPDTO> getListAccount(AccountPDTO accountPDTO) throws NewBusinessException {
		List<AccountDDTO> dDtoList = new DCAccount()
				.getListAccount(NewObjectUtil.copyForClass(AccountDDTO.class, accountPDTO));

		return NewObjectUtil.copyForList(AccountPDTO.class, dDtoList);
	}
}
