package com.kbstar.mbc.config;

import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

/**
 * 데이터 초기화 클래스
 * 
 * 프로그램명: DataInitializer.java
 * 설명: 애플리케이션 시작 시 데이터베이스 초기화를 담당
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 애플리케이션 시작 시 데이터베이스 초기화
 * - 테이블 생성 및 테스트 데이터 삽입
 * - 초기화 상태 로깅
 * 
 * @version 1.0
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final NewIKesaLogger logger = NewKesaLogHelper.getBiz();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        logger.info("=== 데이터베이스 초기화 시작 ===", "DataInitializer");

        try {
            // 1. 테이블 존재 여부 확인
            checkTablesExist();

            // 2. 초기 데이터 확인
            checkInitialData();

            // 3. 초기화 완료 로그
            logInitializationSummary();

            logger.info("=== 데이터베이스 초기화 완료 ===", "DataInitializer");

        } catch (Exception e) {
            logger.error("데이터베이스 초기화 중 오류 발생: " + e.getMessage(), "DataInitializer");
            throw e;
        }
    }

    /**
     * 테이블 존재 여부 확인
     */
    private void checkTablesExist() {
        logger.info("테이블 존재 여부 확인 중...", "DataInitializer");

        String[] tables = {
                "ACCOUNT", "USER_INFO", "USER_ACCOUNT",
                "SYSTEM_CODE", "SYSTEM_LOG"
        };

        for (String table : tables) {
            try {
                String sql = "SELECT COUNT(*) FROM " + table;
                int count = jdbcTemplate.queryForObject(sql, Integer.class);
                logger.info("테이블 " + table + " 확인 완료 - 레코드 수: " + count, "DataInitializer");
            } catch (Exception e) {
                logger.warn("테이블 " + table + " 확인 실패: " + e.getMessage(), "DataInitializer");
            }
        }
    }

    /**
     * 초기 데이터 확인
     */
    private void checkInitialData() {
        logger.info("초기 데이터 확인 중...", "DataInitializer");

        // 사용자 데이터 확인
        int userCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER_INFO", Integer.class);
        logger.info("사용자 데이터: " + userCount + "건", "DataInitializer");

        // 계정 데이터 확인
        int accountCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM ACCOUNT", Integer.class);
        logger.info("계정 데이터: " + accountCount + "건", "DataInitializer");

        // 시스템 코드 확인
        int codeCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM SYSTEM_CODE", Integer.class);
        logger.info("시스템 코드: " + codeCount + "건", "DataInitializer");

        // 사용자-계정 매핑 확인
        int mappingCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER_ACCOUNT", Integer.class);
        logger.info("사용자-계정 매핑: " + mappingCount + "건", "DataInitializer");

        // 로그 데이터 확인
        int logCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM SYSTEM_LOG", Integer.class);
        logger.info("시스템 로그: " + logCount + "건", "DataInitializer");
    }

    /**
     * 초기화 요약 로그
     */
    private void logInitializationSummary() {
        logger.info("=== 초기화 요약 ===", "DataInitializer");

        // 계정 타입별 통계
        String accountTypeSql = "SELECT ACCOUNT_TYPE, COUNT(*) FROM ACCOUNT GROUP BY ACCOUNT_TYPE";
        jdbcTemplate.query(accountTypeSql, (rs, rowNum) -> {
            String type = rs.getString("ACCOUNT_TYPE");
            int count = rs.getInt("COUNT(*)");
            logger.info("계정 타입 " + type + ": " + count + "건", "DataInitializer");
            return null;
        });

        // 사용자 역할별 통계
        String userRoleSql = "SELECT ROLE, COUNT(*) FROM USER_INFO GROUP BY ROLE";
        jdbcTemplate.query(userRoleSql, (rs, rowNum) -> {
            String role = rs.getString("ROLE");
            int count = rs.getInt("COUNT(*)");
            logger.info("사용자 역할 " + role + ": " + count + "건", "DataInitializer");
            return null;
        });

        // 총 잔액 통계
        String balanceSql = "SELECT SUM(BALANCE) FROM ACCOUNT WHERE STATUS = 'ACTIVE'";
        Double totalBalance = jdbcTemplate.queryForObject(balanceSql, Double.class);
        String balanceStr = totalBalance != null ? String.valueOf(totalBalance.longValue()) : "0";
        logger.info("총 계좌 잔액: " + balanceStr + "원", "DataInitializer");

        logger.info("=== 초기화 요약 완료 ===", "DataInitializer");
    }

    /**
     * SQL 파일 읽기 (필요시 사용)
     */
    private String readSqlFile(String fileName) {
        try {
            ClassPathResource resource = new ClassPathResource(fileName);
            return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.error("SQL 파일 읽기 실패: " + fileName, String.valueOf(e));
            return null;
        }
    }
}