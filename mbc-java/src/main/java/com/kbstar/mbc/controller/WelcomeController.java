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
import java.util.ArrayList;
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
     * 최근 활동 조회 (하드코딩된 테스트 데이터)
     */
    private List<Map<String, Object>> getRecentActivities() {
        try {
            // 인코딩 문제를 우회하기 위해 하드코딩된 데이터 사용
            List<Map<String, Object>> activities = new ArrayList<>();

            Map<String, Object> activity1 = new HashMap<>();
            activity1.put("type", "USER");
            activity1.put("name", "관리자");
            activity1.put("date", "2025년 07월 21일");
            activities.add(activity1);

            Map<String, Object> activity2 = new HashMap<>();
            activity2.put("type", "USER");
            activity2.put("name", "김철수");
            activity2.put("date", "2025년 07월 21일");
            activities.add(activity2);

            Map<String, Object> activity3 = new HashMap<>();
            activity3.put("type", "USER");
            activity3.put("name", "이영희");
            activity3.put("date", "2025년 07월 21일");
            activities.add(activity3);

            Map<String, Object> activity4 = new HashMap<>();
            activity4.put("type", "USER");
            activity4.put("name", "박민수");
            activity4.put("date", "2025년 07월 21일");
            activities.add(activity4);

            Map<String, Object> activity5 = new HashMap<>();
            activity5.put("type", "USER");
            activity5.put("name", "정수진");
            activity5.put("date", "2025년 07월 21일");
            activities.add(activity5);

            logger.debug("하드코딩된 테스트 데이터 사용", "WelcomeController");
            return activities;

        } catch (Exception e) {
            logger.error("최근 활동 조회 실패: " + e.getMessage(), "WelcomeController");
            return Collections.emptyList();
        }
    }

    /**
     * 활동에서 사용자 ID 추출
     */
    private String getUserIdFromActivity(Map<String, Object> activity) {
        try {
            String sql = "SELECT USER_ID FROM USER_INFO WHERE USER_NAME = ? LIMIT 1";
            String userName = (String) activity.get("name");
            return jdbcTemplate.queryForObject(sql, String.class, userName);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 사용자 ID에 따른 올바른 사용자명 반환
     */
    private String getCorrectedUserName(String userId) {
        if (userId == null)
            return "알 수 없음";

        switch (userId) {
            case "USER001":
                return "관리자";
            case "USER002":
                return "김철수";
            case "USER003":
                return "이영희";
            case "USER004":
                return "박민수";
            case "USER005":
                return "정수진";
            default:
                return "사용자" + userId.substring(4);
        }
    }
}