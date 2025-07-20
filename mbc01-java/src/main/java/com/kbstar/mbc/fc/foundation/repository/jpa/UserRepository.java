package com.kbstar.mbc.fc.foundation.repository.jpa;

import com.kbstar.mbc.fc.foundation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 사용자 JPA 리포지토리 인터페이스
 * 
 * 프로그램명: UserRepository.java
 * 설명: 사용자 엔티티에 대한 JPA 리포지토리
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 사용자 CRUD 작업
 * - 사용자 검색 기능
 * - 커스텀 쿼리 메서드
 * 
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 이메일로 사용자 조회
     * 
     * @param email 이메일
     * @return 사용자 Optional
     */
    Optional<User> findByEmail(String email);

    /**
     * 사용자명으로 사용자 조회
     * 
     * @param userName 사용자명
     * @return 사용자 목록
     */
    List<User> findByUserNameContaining(String userName);

    /**
     * 상태별 사용자 조회
     * 
     * @param status 사용자 상태
     * @return 사용자 목록
     */
    List<User> findByStatus(User.UserStatus status);

    /**
     * 마지막 로그인 날짜 이후 사용자 조회
     * 
     * @param lastLoginDate 마지막 로그인 날짜
     * @return 사용자 목록
     */
    List<User> findByLastLoginDateAfter(LocalDateTime lastLoginDate);

    /**
     * 이메일 존재 여부 확인
     * 
     * @param email 이메일
     * @return 존재 여부
     */
    boolean existsByEmail(String email);

    /**
     * 활성 사용자 수 조회
     * 
     * @return 활성 사용자 수
     */
    @Query("SELECT COUNT(u) FROM User u WHERE u.status = 'ACTIVE'")
    long countActiveUsers();

    /**
     * 특정 기간 동안 가입한 사용자 조회
     * 
     * @param startDate 시작 날짜
     * @param endDate   종료 날짜
     * @return 사용자 목록
     */
    @Query("SELECT u FROM User u WHERE u.createdDate BETWEEN :startDate AND :endDate")
    List<User> findUsersByRegistrationPeriod(@Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    /**
     * 사용자명과 이메일로 사용자 검색
     * 
     * @param userName 사용자명
     * @param email    이메일
     * @return 사용자 목록
     */
    @Query("SELECT u FROM User u WHERE u.userName LIKE %:userName% OR u.email LIKE %:email%")
    List<User> findByUserNameOrEmail(@Param("userName") String userName,
            @Param("email") String email);
}