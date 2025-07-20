package com.kbstar.mbc.dc.usermgtdc;

import com.kbstar.ksa.infra.po.AbstractDTO;
import com.kbstar.ksa.infra.po.annotation.GPath;

@GPath(name = "pageGrid")
public class Page extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String seq;

	private String seqCtnt;
	
	private String totalLineCnt;
	
	private String outptLineCnt;
	
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeqCtnt() {
		return seqCtnt;
	}

	public void setSeqCtnt(String seqCtnt) {
		this.seqCtnt = seqCtnt;
	}

	public String getTotalLineCnt() {
		return totalLineCnt;
	}

	public void setTotalLineCnt(String totalLineCnt) {
		this.totalLineCnt = totalLineCnt;
	}

	public String getOutptLineCnt() {
		return outptLineCnt;
	}

	public void setOutptLineCnt(String outptLineCnt) {
		this.outptLineCnt = outptLineCnt;
	}

}
