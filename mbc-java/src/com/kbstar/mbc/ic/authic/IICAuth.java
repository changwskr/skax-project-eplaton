package com.kbstar.mbc.ic.authic;

public interface IICAuth {

	/**
	 * 
	 * �ش��ư�� �̺�Ʈ ó�� ���ɿ��θ� ��ȯ�ϴ� �޼ҵ�
	 * 
	 * @param usrId : �����ȣ
	 * @param grpCd : �׷��ڵ�
	 * @param winNo :  ȭ���ȣ
	 * @param buttonCd : ��ư�ڵ�
	 * @return boolean
	 */
	public boolean isButtonHasAuth(String usrId, String grpCd, String winNo, String buttonCd);
	
}
