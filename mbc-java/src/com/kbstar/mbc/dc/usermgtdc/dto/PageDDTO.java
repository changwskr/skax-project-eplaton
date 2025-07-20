package com.kbstar.mbc.dc.usermgtdc.dto;

import com.kbstar.ksa.infra.po.AbstractDTO;

public class PageDDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private int dmndPageNo;

	private int pageLineCnt;

	public int getDmndPageNo() {
		return dmndPageNo;
	}

	public void setDmndPageNo(String dmndPageNo) {
		this.dmndPageNo = Integer.parseInt(dmndPageNo);
	}

	public int getPageLineCnt() {
		return pageLineCnt;
	}

	public void setPageLineCnt(String pageLineCnt) {
		this.pageLineCnt = Integer.parseInt(pageLineCnt);
	}

}
