package com.kbstar.mbc.dc.usermgtdc.repository.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kbstar.mbc.dc.usermgtdc.entity.UserEntity;

/**
 * JPA 사용자 Repository 인터페이스
 * 
 * 프로그램명: UserJpaRepository.java
 * 설명: JPA를 사용한 사용자 데이터 접근 인터페이스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * @version 1.0
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, String> {

    /**
     * 사용자 ID로 사용자 조회 (삭제되지 않은 사용자)
     * 
     * @param userId 사용자 ID
     * @return 사용자 엔티티
     */
    Optional<UserEntity> findByUserIdAndDelYn(String userId, String delYn);

    /**
     * 사용자 이메일로 사용자 조회 (삭제되지 않은 사용자)
     * 
     * @param userEmail 사용자 이메일
     * @param delYn     삭제 여부
     * @return 사용자 엔티티
     */
    Optional<UserEntity> findByUserEmailAndDelYn(String userEmail, String delYn);

    /**
     * 사용자명으로 사용자 목록 조회 (삭제되지 않은 사용자)
     * 
     * @param userName 사용자명
     * @param delYn    삭제 여부
     * @return 사용자 목록
     */
    List<UserEntity> findByUserNameContainingAndDelYn(String userName, String delYn);

    /**
     * 사용자 ID로 사용자 목록 조회 (삭제되지 않은 사용자)
     * 
     * @param userId 사용자 ID
     * @param delYn  삭제 여부
     * @return 사용자 목록
     */
    List<UserEntity> findByUserIdContainingAndDelYn(String userId, String delYn);

    /**
     * 사용자 이메일로 사용자 목록 조회 (삭제되지 않은 사용자)
     * 
     * @param userEmail 사용자 이메일
     * @param delYn     삭제 여부
     * @return 사용자 목록
     */
    List<UserEntity> findByUserEmailContainingAndDelYn(String userEmail, String delYn);

    /**
     * 사용 여부와 삭제 여부로 사용자 목록 조회
     * 
     * @param useYn 사용 여부
     * @param delYn 삭제 여부
     * @return 사용자 목록
     */
    List<UserEntity> findByUseYnAndDelYn(String useYn, String delYn);

    /**
     * 사용자 존재 여부 확인 (삭제되지 않은 사용자)
     * 
     * @param userId 사용자 ID
     * @param delYn  삭제 여부
     * @return 존재 여부
     */
    boolean existsByUserIdAndDelYn(String userId, String delYn);

    /**
     * 사용자 이메일 존재 여부 확인 (삭제되지 않은 사용자)
     * 
     * @param userEmail 사용자 이메일
     * @param delYn     삭제 여부
     * @return 존재 여부
     */
    boolean existsByUserEmailAndDelYn(String userEmail, String delYn);

    /**
     * 사용자 수 조회 (삭제되지 않은 사용자)
     * 
     * @param delYn 삭제 여부
     * @return 사용자 수
     */
    long countByDelYn(String delYn);

    /**
     * 조건부 사용자 검색 (JPQL)
     * 
     * @param userId    사용자 ID (부분 일치)
     * @param userName  사용자명 (부분 일치)
     * @param userEmail 사용자 이메일 (부분 일치)
     * @param delYn     삭제 여부
     * @return 사용자 목록
     */
    @Query("SELECT u FROM UserEntity u WHERE " +
            "(:userId IS NULL OR u.userId LIKE %:userId%) AND " +
            "(:userName IS NULL OR u.userName LIKE %:userName%) AND " +
            "(:userEmail IS NULL OR u.userEmail LIKE %:userEmail%) AND " +
            "u.delYn = :delYn " +
            "ORDER BY u.regDt DESC")
    List<UserEntity> findUsersByConditions(
            @Param("userId") String userId,
            @Param("userName") String userName,
            @Param("userEmail") String userEmail,
            @Param("delYn") String delYn);
}