/*
 * (@)# AccountPDTO.java
 *
 * Copyright KB Kookmin Bank Inc. All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.kbstar.mbc.pc.dto;

import java.util.Date;

import com.kbstar.ksa.infra.po.NewAbstractDTO;
import com.kbstar.ksa.infra.po.annotation.NewGPath;

/**
 * Account DTO for Process Component
 * 
 * Program Name: AccountPDTO.java
 * Description: Account data transfer object for process component
 * 
 * Change History:
 * <ul>
 * <li>2008-08-26::Initial::First Creation
 * </ul>
 */
@NewGPath(name = "Account")
public class AccountPDTO extends NewAbstractDTO {

	private static final long serialVersionUID = 1L;

	private String accountNumber; // Account number

	private String name; // Name

	private String identificationNumber; // ID number

	private Float interestRate; // Interest rate

	private Date lastTransaction; // Last transaction date

	private String password; // Password

	private String netAmount; // Balance

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public Float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Float interestRate) {
		this.interestRate = interestRate;
	}

	public Date getLastTransaction() {
		return lastTransaction;
	}

	public void setLastTransaction(Date lastTransaction) {
		this.lastTransaction = lastTransaction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}