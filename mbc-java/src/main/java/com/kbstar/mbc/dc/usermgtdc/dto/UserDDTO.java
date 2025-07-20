package com.kbstar.mbc.dc.usermgtdc.dto;

import com.kbstar.ksa.infra.po.NewAbstractDTO;
import com.kbstar.ksa.infra.po.annotation.NewGPath;

public class UserDDTO extends NewAbstractDTO {

	private static final long serialVersionUID = 1L;

	@NewGPath(name = "uID")
	public String userID;
	private String userDstcd;
	private String userName;
	private String userPwd;
	private String ssno;
	private String langDstcd;
	private String dvsnNo;
	private String dvsnName;
	private String jobclDstcd;
	private String jobclName;
	private String instiCd;
	private String instiName;
	private String telno;
	private String cphnNo;
	private String emad;
	private String faxNo;
	private String brdt;
	private String addr;
	private String zip;
	private String emalRecvYn;
	@NewGPath(name = "sMSRecvYn")
	private String smsRecvYn;
	private String useYn;
	private String prcssStusDstcd;
	private String entcoYmd;
	private String rtireYmd;
	private String regsntID;
	private String regiYmd;
	private String amndrID;
	private String amndYmd;
	private String crud;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAmndrID() {
		return amndrID;
	}

	public void setAmndrID(String amndrID) {
		this.amndrID = amndrID;
	}

	public String getAmndYmd() {
		return amndYmd;
	}

	public void setAmndYmd(String amndYmd) {
		this.amndYmd = amndYmd;
	}

	public String getBrdt() {
		return brdt;
	}

	public void setBrdt(String brdt) {
		this.brdt = brdt;
	}

	public String getCphnNo() {
		return cphnNo;
	}

	public void setCphnNo(String cphnNo) {
		this.cphnNo = cphnNo;
	}

	public String getCrud() {
		return crud;
	}

	public void setCrud(String crud) {
		this.crud = crud;
	}

	public String getDvsnName() {
		return dvsnName;
	}

	public void setDvsnName(String dvsnName) {
		this.dvsnName = dvsnName;
	}

	public String getDvsnNo() {
		return dvsnNo;
	}

	public void setDvsnNo(String dvsnNo) {
		this.dvsnNo = dvsnNo;
	}

	public String getEmad() {
		return emad;
	}

	public void setEmad(String emad) {
		this.emad = emad;
	}

	public String getEmalRecvYn() {
		return emalRecvYn;
	}

	public void setEmalRecvYn(String emalRecvYn) {
		this.emalRecvYn = emalRecvYn;
	}

	public String getEntcoYmd() {
		return entcoYmd;
	}

	public void setEntcoYmd(String entcoYmd) {
		this.entcoYmd = entcoYmd;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getInstiCd() {
		return instiCd;
	}

	public void setInstiCd(String instiCd) {
		this.instiCd = instiCd;
	}

	public String getInstiName() {
		return instiName;
	}

	public void setInstiName(String instiName) {
		this.instiName = instiName;
	}

	public String getJobclDstcd() {
		return jobclDstcd;
	}

	public void setJobclDstcd(String jobclDstcd) {
		this.jobclDstcd = jobclDstcd;
	}

	public String getJobclName() {
		return jobclName;
	}

	public void setJobclName(String jobclName) {
		this.jobclName = jobclName;
	}

	public String getLangDstcd() {
		return langDstcd;
	}

	public void setLangDstcd(String langDstcd) {
		this.langDstcd = langDstcd;
	}

	public String getPrcssStusDstcd() {
		return prcssStusDstcd;
	}

	public void setPrcssStusDstcd(String prcssStusDstcd) {
		this.prcssStusDstcd = prcssStusDstcd;
	}

	public String getRegiYmd() {
		return regiYmd;
	}

	public void setRegiYmd(String regiYmd) {
		this.regiYmd = regiYmd;
	}

	public String getRegsntID() {
		return regsntID;
	}

	public void setRegsntID(String regsntID) {
		this.regsntID = regsntID;
	}

	public String getRtireYmd() {
		return rtireYmd;
	}

	public void setRtireYmd(String rtireYmd) {
		this.rtireYmd = rtireYmd;
	}

	public String getSmsRecvYn() {
		return smsRecvYn;
	}

	public void setSmsRecvYn(String recvYn) {
		smsRecvYn = recvYn;
	}

	public String getSsno() {
		return ssno;
	}

	public void setSsno(String ssno) {
		this.ssno = ssno;
	}

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserDstcd() {
		return userDstcd;
	}

	public void setUserDstcd(String userDstcd) {
		this.userDstcd = userDstcd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

}
