package com.kbstar.mbc.ac.usermgtac;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewGenericDto;
import com.kbstar.ksa.infra.po.NewKBData;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.oltp.biz.NewIApplicationService;
import com.kbstar.mbc.as.usermgtas.ASMBC75Z03;

/**
 * 사용자 상세 조회 Application Control
 * 
 * 프로그램명: ACMBC75Z03.java
 * 설명: 사용자 상세 조회 요청을 처리하는 컨트롤러
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 사용자 상세 조회 요청 처리
 * - 입력 데이터 검증
 * - AS 호출 및 결과 반환
 * 
 * @version 1.0
 */
public class ACMBC75Z03 implements NewIApplicationService {

    protected NewIKesaLogger logger = NewKesaLogHelper.getBiz();

    /**
     * 사용자 상세 조회 처리
     * 
     * @param reqData 요청 데이터
     * @return 응답 데이터
     * @throws NewBusinessException 비즈니스 예외
     */
    public NewKBData execute(NewKBData reqData) throws NewBusinessException {
        logger.debug("ACMBC75Z03 - 사용자 상세 조회 요청 처리 시작");

        try {
            // 1. 입력 데이터 검증
            validateInputData(reqData);

            // 2. AS 호출
            ASMBC75Z03 asMbc75Z03 = new ASMBC75Z03();
            NewKBData result = asMbc75Z03.execute(reqData);

            logger.debug("ACMBC75Z03 - 사용자 상세 조회 요청 처리 완료");
            return result;

        } catch (Exception e) {
            logger.error("ACMBC75Z03 - 사용자 상세 조회 처리 중 오류 발생: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("사용자 상세 조회 처리 중 오류가 발생했습니다. 원인: " + e.getMessage());
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

        // 사용자 ID 검증 (필수)
        Object userId = input.get("userId");
        if (userId == null || userId.toString().trim().isEmpty()) {
            throw new NewBusinessException("사용자 ID는 필수 입력 항목입니다.");
        }
        logger.debug("ACMBC75Z03 - userId 파라미터: " + userId.toString());

        logger.debug("ACMBC75Z03 - 입력 데이터 검증 완료");
    }
}