package com.kbstar.mbc.ac.runtimeac;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewGenericDto;
import com.kbstar.ksa.infra.po.NewKBData;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.oltp.biz.NewIApplicationService;
import com.kbstar.mbc.as.runtimeas.ASMBC759Z1;

/**
 * 런타임 특별 Application Control
 * 
 * 프로그램명: ACMBC759Z1.java
 * 설명: 런타임 특별 요청을 처리하는 컨트롤러
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 런타임 특별 요청 처리
 * - 입력 데이터 검증
 * - AS 호출 및 결과 반환
 * 
 * @version 1.0
 */
public class ACMBC759Z1 implements NewIApplicationService {

    protected NewIKesaLogger logger = NewKesaLogHelper.getBiz();

    /**
     * 런타임 특별 처리
     * 
     * @param reqData 요청 데이터
     * @return 응답 데이터
     * @throws NewBusinessException 비즈니스 예외
     */
    public NewKBData execute(NewKBData reqData) throws NewBusinessException {
        logger.debug("ACMBC759Z1 - 런타임 특별 요청 처리 시작");

        try {
            // 1. 입력 데이터 검증
            validateInputData(reqData);

            // 2. AS 호출
            ASMBC759Z1 asMbc759Z1 = new ASMBC759Z1();
            NewKBData result = asMbc759Z1.execute(reqData);

            logger.debug("ACMBC759Z1 - 런타임 특별 요청 처리 완료");
            return result;

        } catch (Exception e) {
            logger.error("ACMBC759Z1 - 런타임 특별 처리 중 오류 발생: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("런타임 특별 처리 중 오류가 발생했습니다. 원인: " + e.getMessage());
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

        // 기본적인 입력 데이터 검증
        if (input == null) {
            throw new NewBusinessException("입력 데이터가 null입니다.");
        }

        logger.debug("ACMBC759Z1 - 입력 데이터 검증 완료");
    }
}