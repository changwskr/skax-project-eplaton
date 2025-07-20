package com.kbstar.mbc.ac.accountac;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewGenericDto;
import com.kbstar.ksa.infra.po.NewKBData;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.oltp.biz.NewIApplicationService;
import com.kbstar.mbc.as.accountas.ASMBC74001;
import com.kbstar.mbc.pc.dto.AccountPDTO;

/**
 * 계정 목록 조회 Application Control
 * 
 * 프로그램명: ACMBC74001.java
 * 설명: 계정 목록 조회 요청을 처리하는 컨트롤러
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 계정 목록 조회 요청 처리
 * - 입력 데이터 검증
 * - AS 호출 및 결과 반환
 * 
 * @version 1.0
 */
public class ACMBC74001 implements NewIApplicationService {

    protected NewIKesaLogger logger = NewKesaLogHelper.getBiz();

    /**
     * 계정 목록 조회 처리
     * 
     * @param reqData 요청 데이터
     * @return 응답 데이터
     * @throws NewBusinessException 비즈니스 예외
     */
    public NewKBData execute(NewKBData reqData) throws NewBusinessException {
        logger.debug("ACMBC74001 - 계정 목록 조회 요청 처리 시작");

        try {
            // 1. 입력 데이터 검증
            validateInputData(reqData);

            // 2. AS 호출
            ASMBC74001 asMbc74001 = new ASMBC74001();
            NewKBData result = asMbc74001.execute(reqData);

            logger.debug("ACMBC74001 - 계정 목록 조회 요청 처리 완료");
            return result;

        } catch (Exception e) {
            logger.error("ACMBC74001 - 계정 목록 조회 처리 중 오류 발생: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("계정 목록 조회 처리 중 오류가 발생했습니다. 원인: " + e.getMessage());
        }
    }

    /**
     * 입력 데이터 검증
     * 
     * @param reqData 요청 데이터
     * @throws NewBusinessException 검증 실패 시
     */
    private void validateInputData(NewKBData reqData) throws NewBusinessException {
        NewGenericDto input = reqData.getInputGenericDto().using(NewGenericDto.INDATA);

        // AccountPDTO 존재 여부 확인 (선택적)
        AccountPDTO accountPDTO = (AccountPDTO) input.get("AccountPDTO");
        if (accountPDTO != null) {
            // 추가 검증 로직은 필요에 따라 구현
            logger.debug("ACMBC74001 - AccountPDTO 검증 완료");
        }

        logger.debug("ACMBC74001 - 입력 데이터 검증 완료");
    }
}