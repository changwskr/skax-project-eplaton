package com.kbstar.mbc.ac.accountac;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.infra.po.NewGenericDto;
import com.kbstar.ksa.infra.po.NewKBData;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.oltp.biz.NewIApplicationService;
import com.kbstar.mbc.as.accountas.ASMBC73001;
import com.kbstar.mbc.pc.dto.AccountPDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 계정 삭제 Application Control
 * 
 * 프로그램명: ACMBC73001.java
 * 설명: 계정 삭제 요청을 처리하는 컨트롤러
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 계정 삭제 요청 처리 (GET, POST, DELETE)
 * - 입력 데이터 검증
 * - AS 호출 및 결과 반환
 * 
 * @version 1.0
 */
@RestController
@RequestMapping("/api/account/delete")
@CrossOrigin(origins = "*")
public class ACMBC73001 implements NewIApplicationService {

    protected NewIKesaLogger logger = NewKesaLogHelper.getBiz();

    /**
     * 계정 삭제 처리 (DELETE)
     * 
     * @param accountId 계좌번호
     * @return 응답 데이터
     * @throws NewBusinessException 비즈니스 예외
     */
    @DeleteMapping
    public ResponseEntity<Map<String, Object>> deleteAccount(@RequestParam String accountId)
            throws NewBusinessException {
        logger.debug("ACMBC73001 - 계정 삭제 요청 처리 시작 (DELETE)");

        try {
            AccountPDTO accountPDTO = new AccountPDTO();
            accountPDTO.setAccountId(accountId);

            // 1. 입력 데이터 검증
            validateInputData(accountPDTO);

            // 2. AS 호출
            NewKBData reqData = new NewKBData();
            NewGenericDto input = reqData.getInputGenericDto().using(NewGenericDto.INDATA);
            input.put("AccountPDTO", accountPDTO);

            ASMBC73001 asMbc73001 = new ASMBC73001();
            NewKBData result = asMbc73001.execute(reqData);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "계정이 성공적으로 삭제되었습니다.");
            response.put("data", result);

            logger.debug("ACMBC73001 - 계정 삭제 요청 처리 완료");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("ACMBC73001 - 계정 삭제 처리 중 오류 발생: " + e.getMessage(), String.valueOf(e));
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "계정 삭제 처리 중 오류가 발생했습니다. 원인: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 계정 삭제 처리 (POST)
     * 
     * @param accountPDTO 계정 정보
     * @return 응답 데이터
     * @throws NewBusinessException 비즈니스 예외
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> deleteAccountPost(@RequestBody AccountPDTO accountPDTO)
            throws NewBusinessException {
        logger.debug("ACMBC73001 - 계정 삭제 요청 처리 시작 (POST)");

        try {
            // 1. 입력 데이터 검증
            validateInputData(accountPDTO);

            // 2. AS 호출
            NewKBData reqData = new NewKBData();
            NewGenericDto input = reqData.getInputGenericDto().using(NewGenericDto.INDATA);
            input.put("AccountPDTO", accountPDTO);

            ASMBC73001 asMbc73001 = new ASMBC73001();
            NewKBData result = asMbc73001.execute(reqData);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "계정이 성공적으로 삭제되었습니다.");
            response.put("data", result);

            logger.debug("ACMBC73001 - 계정 삭제 요청 처리 완료");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("ACMBC73001 - 계정 삭제 처리 중 오류 발생: " + e.getMessage(), String.valueOf(e));
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "계정 삭제 처리 중 오류가 발생했습니다. 원인: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 계정 삭제 처리 (GET)
     * 
     * @param accountId 계좌번호
     * @return 응답 데이터
     * @throws NewBusinessException 비즈니스 예외
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> deleteAccountGet(@RequestParam String accountId)
            throws NewBusinessException {
        logger.debug("ACMBC73001 - 계정 삭제 요청 처리 시작 (GET)");

        try {
            AccountPDTO accountPDTO = new AccountPDTO();
            accountPDTO.setAccountId(accountId);

            // 1. 입력 데이터 검증
            validateInputData(accountPDTO);

            // 2. AS 호출
            NewKBData reqData = new NewKBData();
            NewGenericDto input = reqData.getInputGenericDto().using(NewGenericDto.INDATA);
            input.put("AccountPDTO", accountPDTO);

            ASMBC73001 asMbc73001 = new ASMBC73001();
            NewKBData result = asMbc73001.execute(reqData);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "계정이 성공적으로 삭제되었습니다.");
            response.put("data", result);

            logger.debug("ACMBC73001 - 계정 삭제 요청 처리 완료");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("ACMBC73001 - 계정 삭제 처리 중 오류 발생: " + e.getMessage(), String.valueOf(e));
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "계정 삭제 처리 중 오류가 발생했습니다. 원인: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 기존 execute 메서드 (호환성 유지)
     * 
     * @param reqData 요청 데이터
     * @return 응답 데이터
     * @throws NewBusinessException 비즈니스 예외
     */
    public NewKBData execute(NewKBData reqData) throws NewBusinessException {
        logger.debug("ACMBC73001 - 계정 삭제 요청 처리 시작");

        try {
            // 1. 입력 데이터 검증
            validateInputData(reqData);

            // 2. AS 호출
            ASMBC73001 asMbc73001 = new ASMBC73001();
            NewKBData result = asMbc73001.execute(reqData);

            logger.debug("ACMBC73001 - 계정 삭제 요청 처리 완료");
            return result;

        } catch (Exception e) {
            logger.error("ACMBC73001 - 계정 삭제 처리 중 오류 발생: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("계정 삭제 처리 중 오류가 발생했습니다. 원인: " + e.getMessage());
        }
    }

    /**
     * 입력 데이터 검증 (NewKBData용)
     * 
     * @param reqData 요청 데이터
     * @throws NewBusinessException 검증 실패 시
     */
    private void validateInputData(NewKBData reqData) throws NewBusinessException {
        NewGenericDto input = reqData.getInputGenericDto().using(NewGenericDto.INDATA);

        // AccountPDTO 존재 여부 확인
        AccountPDTO accountPDTO = (AccountPDTO) input.get("AccountPDTO");
        if (accountPDTO == null) {
            throw new NewBusinessException("AccountPDTO가 null입니다.");
        }

        // 계좌번호 검증 (필수 필드)
        if (accountPDTO.getAccountId() == null || accountPDTO.getAccountId().trim().isEmpty()) {
            throw new NewBusinessException("계좌번호는 필수 입력 항목입니다.");
        }

        logger.debug("ACMBC73001 - 입력 데이터 검증 완료");
    }

    /**
     * 입력 데이터 검증 (AccountPDTO용)
     * 
     * @param accountPDTO 계정 정보
     * @throws NewBusinessException 검증 실패 시
     */
    private void validateInputData(AccountPDTO accountPDTO) throws NewBusinessException {
        if (accountPDTO == null) {
            throw new NewBusinessException("AccountPDTO가 null입니다.");
        }

        // 계좌번호 검증 (필수 필드)
        if (accountPDTO.getAccountId() == null || accountPDTO.getAccountId().trim().isEmpty()) {
            throw new NewBusinessException("계좌번호는 필수 입력 항목입니다.");
        }

        logger.debug("ACMBC73001 - 입력 데이터 검증 완료");
    }
}