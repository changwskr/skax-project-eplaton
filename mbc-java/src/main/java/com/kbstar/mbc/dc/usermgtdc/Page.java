package com.kbstar.mbc.dc.usermgtdc;

import com.kbstar.ksa.infra.po.NewAbstractDTO;

/**
 * Page class
 */
public class Page extends NewAbstractDTO {

	private int totalLineCnt;
	private int outptLineCnt;

	public Page() {
		// Default constructor
	}

	public int getTotalLineCnt() {
		return totalLineCnt;
	}

	public void setTotalLineCnt(int totalLineCnt) {
		this.totalLineCnt = totalLineCnt;
	}

	public int getOutptLineCnt() {
		return outptLineCnt;
	}

	public void setOutptLineCnt(int outptLineCnt) {
		this.outptLineCnt = outptLineCnt;
	}
}
