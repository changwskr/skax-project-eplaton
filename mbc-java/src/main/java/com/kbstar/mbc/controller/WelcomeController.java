package com.kbstar.mbc.controller;

import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Welcome 페이지 컨트롤러
 * 
 * 프로그램명: WelcomeController.java
 * 설명: 메인 Welcome 페이지를 담당하는 컨트롤러
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 메인 페이지 표시
 * - 시스템 상태 정보 제공
 * - 대시보드 데이터 제공
 * 
 * @version 1.0
 */
@Controller
public class WelcomeController {

    private final NewIKesaLogger logger = NewKesaLogHelper.getBiz();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 메인 Welcome 페이지
     */
    @GetMapping("/")
    public String welcome(Model model) {
        logger.info("Welcome 페이지 접속", "WelcomeController");

        try {
            // 현재 시간
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss");
            model.addAttribute("currentTime", now.format(formatter));

            // 시스템 정보
            Map<String, Object> systemInfo = getSystemInfo();
            model.addAttribute("systemInfo", systemInfo);

            // 사용자 통계
            Map<String, Object> userStats = getUserStats();
            model.addAttribute("userStats", userStats);

            // 계정 통계
            Map<String, Object> accountStats = getAccountStats();
            model.addAttribute("accountStats", accountStats);

            // 최근 활동
            List<Map<String, Object>> recentActivities = getRecentActivities();
            model.addAttribute("recentActivities", recentActivities);

            return "welcome";
        } catch (Exception e) {
            logger.error("Welcome 페이지 로딩 중 오류: " + e.getMessage(), "WelcomeController");
            model.addAttribute("error", "페이지 로딩 중 오류가 발생했습니다.");
            return "error";
        }
    }

    /**
     * 시스템 정보 조회
     */
    private Map<String, Object> getSystemInfo() {
        Map<String, Object> systemInfo = new HashMap<>();

        try {
            // 데이터베이스 연결 상태 확인
            String dbStatus = "정상";
            try {
                jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            } catch (Exception e) {
                dbStatus = "오류";
            }

            systemInfo.put("databaseStatus", dbStatus);
            systemInfo.put("applicationName", "MBC 시스템");
            systemInfo.put("version", "1.0.0");
            systemInfo.put("environment", "개발환경");

        } catch (Exception e) {
            logger.error("시스템 정보 조회 실패: " + e.getMessage(), "WelcomeController");
        }

        return systemInfo;
    }

    /**
     * 사용자 통계 조회
     */
    private Map<String, Object> getUserStats() {
        Map<String, Object> userStats = new HashMap<>();

        try {
            // 전체 사용자 수
            Integer totalUsers = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER_INFO", Integer.class);
            userStats.put("totalUsers", totalUsers != null ? totalUsers : 0);

            // 활성 사용자 수
            Integer activeUsers = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM USER_INFO WHERE STATUS = 'ACTIVE'", Integer.class);
            userStats.put("activeUsers", activeUsers != null ? activeUsers : 0);

            // 관리자 수
            Integer adminUsers = jdbcTemplate.queryForObject(
                    "SELECT COUNT(*) FROM USER_INFO WHERE ROLE = 'ADMIN'", Integer.class);
            userStats.put("adminUsers", adminUsers != null ? adminUsers : 0);

        } catch (Exception e) {
            logger.error("사용자 통계 조회 실패: " + e.getMessage(), "WelcomeController");
            userStats.put("totalUsers", 0);
            userStats.put("activeUsers", 0);
            userStats.put("adminUsers", 0);
        }

        return userStats;
    }

    /**
     * 계정 통계 조회
     */
    private Map<String, Object> getAccountStats() {
        Map<String, Object> accountStats = new HashMap<>();

        try {
            // 전체 계정 수
            Integer totalAccounts = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM ACCOUNT", Integer.class);
            accountStats.put("totalAccounts", totalAccounts != null ? totalAccounts : 0);

            // 거래 내역 수
            Integer totalTransactions = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TRANSACTION", Integer.class);
            accountStats.put("totalTransactions", totalTransactions != null ? totalTransactions : 0);

        } catch (Exception e) {
            logger.error("계정 통계 조회 실패: " + e.getMessage(), "WelcomeController");
            accountStats.put("totalAccounts", 0);
            accountStats.put("totalTransactions", 0);
        }

        return accountStats;
    }

    /**
     * 최근 활동 조회
     */
    private List<Map<String, Object>> getRecentActivities() {
        try {
            String sql = "SELECT 'USER' as type, USER_NAME as name, CREATED_DATE as date " +
                    "FROM USER_INFO " +
                    "ORDER BY CREATED_DATE DESC " +
                    "LIMIT 5";

            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            logger.error("최근 활동 조회 실패: " + e.getMessage(), "WelcomeController");
            return Collections.emptyList();
        }
    }
}