package com.kbstar.mbc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * JPA 설정 클래스
 * 
 * 프로그램명: JpaConfig.java
 * 설명: JPA 관련 설정을 담당하는 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - JPA Auditing 활성화
 * - JPA Repository 스캔
 * - JPA 관련 설정
 * 
 * @version 1.0
 */
@Configuration
@Profile("jpa")
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.kbstar.mbc.fc.foundation.repository.jpa")
public class JpaConfig {

    // JPA 관련 추가 설정이 필요한 경우 여기에 추가
}