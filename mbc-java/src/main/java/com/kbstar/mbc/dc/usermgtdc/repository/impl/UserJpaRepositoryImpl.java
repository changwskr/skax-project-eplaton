package com.kbstar.mbc.dc.usermgtdc.repository.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.kbstar.ksa.exception.NewBusinessException;
import com.kbstar.ksa.logger.NewIKesaLogger;
import com.kbstar.ksa.logger.NewKesaLogHelper;
import com.kbstar.ksa.util.NewObjectUtil;
import com.kbstar.mbc.dc.usermgtdc.User;
import com.kbstar.mbc.dc.usermgtdc.Page;
import com.kbstar.mbc.dc.usermgtdc.Tree;
import com.kbstar.mbc.dc.usermgtdc.dto.UserDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.PageDDTO;
import com.kbstar.mbc.dc.usermgtdc.dto.TreeDDTO;
import com.kbstar.mbc.dc.usermgtdc.entity.UserEntity;
import com.kbstar.mbc.dc.usermgtdc.repository.UserRepository;
import com.kbstar.mbc.dc.usermgtdc.repository.jpa.UserJpaRepository;

/**
 * JPA 기반 사용자 Repository 구현체
 * 
 * 프로그램명: UserJpaRepositoryImpl.java
 * 설명: JPA를 사용한 사용자 관리 Repository 구현체
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - JPA를 통한 사용자 CRUD 작업
 * - 페이징 처리
 * - 트리 구조 데이터 조회
 * 
 * @version 1.0
 */
@Repository
@Profile("jpa")
public class UserJpaRepositoryImpl implements UserRepository {

    private NewIKesaLogger logger = NewKesaLogHelper.getBiz();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public List<User> getListUser(UserDDTO userDDTO) throws NewBusinessException {
        try {
            logger.debug("UserJpaRepositoryImpl.getListUser called");

            List<UserEntity> userEntities = userJpaRepository.findUsersByConditions(
                    userDDTO.getUserID(),
                    userDDTO.getUserName(),
                    userDDTO.getEmad(),
                    "N" // 삭제되지 않은 사용자만
            );

            return NewObjectUtil.copyForList(User.class, userEntities);
        } catch (Exception e) {
            logger.error("Error in getListUser: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("B0000001", "getListUser", e);
        }
    }

    @Override
    public int insertUser(UserDDTO userDDTO) throws NewBusinessException {
        try {
            logger.debug("UserJpaRepositoryImpl.insertUser called");

            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(userDDTO.getUserID());
            userEntity.setUserName(userDDTO.getUserName());
            userEntity.setUserEmail(userDDTO.getEmad());
            userEntity.setUserPassword(userDDTO.getUserPwd());
            userEntity.setUserRole(userDDTO.getUserDstcd());
            userEntity.setUseYn("Y");
            userEntity.setDelYn("N");
            userEntity.setRegDt(LocalDateTime.now().format(formatter));
            userEntity.setUpdDt(LocalDateTime.now().format(formatter));

            userJpaRepository.save(userEntity);
            return 1;
        } catch (Exception e) {
            logger.error("Error in insertUser: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("B0000002", "insertUser", e);
        }
    }

    @Override
    public int updateUser(UserDDTO userDDTO) throws NewBusinessException {
        try {
            logger.debug("UserJpaRepositoryImpl.updateUser called");

            Optional<UserEntity> optionalUser = userJpaRepository.findByUserIdAndDelYn(userDDTO.getUserID(), "N");
            if (optionalUser.isPresent()) {
                UserEntity userEntity = optionalUser.get();
                userEntity.setUserName(userDDTO.getUserName());
                userEntity.setUserEmail(userDDTO.getEmad());
                userEntity.setUserPassword(userDDTO.getUserPwd());
                userEntity.setUserRole(userDDTO.getUserDstcd());
                userEntity.setUpdDt(LocalDateTime.now().format(formatter));

                userJpaRepository.save(userEntity);
                return 1;
            }
            return 0;
        } catch (Exception e) {
            logger.error("Error in updateUser: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("B0000003", "updateUser", e);
        }
    }

    @Override
    public int deleteUser(UserDDTO userDDTO) throws NewBusinessException {
        try {
            logger.debug("UserJpaRepositoryImpl.deleteUser called");

            Optional<UserEntity> optionalUser = userJpaRepository.findByUserIdAndDelYn(userDDTO.getUserID(), "N");
            if (optionalUser.isPresent()) {
                UserEntity userEntity = optionalUser.get();
                userEntity.setDelYn("Y");
                userEntity.setUpdDt(LocalDateTime.now().format(formatter));

                userJpaRepository.save(userEntity);
                return 1;
            }
            return 0;
        } catch (Exception e) {
            logger.error("Error in deleteUser: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("B0000004", "deleteUser", e);
        }
    }

    @Override
    public List<Page> getListPage(PageDDTO pageDDTO) throws NewBusinessException {
        try {
            logger.debug("UserJpaRepositoryImpl.getListPage called");
            // JPA에서는 페이징을 위해 Pageable을 사용할 수 있지만,
            // 기존 인터페이스와의 호환성을 위해 임시로 빈 리스트 반환
            // 실제 구현시에는 Pageable을 사용하여 페이징 처리
            return new java.util.ArrayList<>();
        } catch (Exception e) {
            logger.error("Error in getListPage: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("B0000005", "getListPage", e);
        }
    }

    @Override
    public String getPageCount(PageDDTO pageDDTO) throws NewBusinessException {
        try {
            logger.debug("UserJpaRepositoryImpl.getPageCount called");
            // JPA에서는 count() 메서드를 사용하여 총 개수를 조회할 수 있지만,
            // 기존 인터페이스와의 호환성을 위해 임시로 "0" 반환
            return "0";
        } catch (Exception e) {
            logger.error("Error in getPageCount: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("B0000006", "getPageCount", e);
        }
    }

    @Override
    public List<Tree> getListTree(TreeDDTO treeDDTO) throws NewBusinessException {
        try {
            logger.debug("UserJpaRepositoryImpl.getListTree called");
            // JPA에서는 트리 구조를 위해 별도의 엔티티와 쿼리가 필요하지만,
            // 기존 인터페이스와의 호환성을 위해 임시로 빈 리스트 반환
            return new java.util.ArrayList<>();
        } catch (Exception e) {
            logger.error("Error in getListTree: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("B0000007", "getListTree", e);
        }
    }

    @Override
    public User getUserById(String userId) throws NewBusinessException {
        try {
            logger.debug("UserJpaRepositoryImpl.getUserById called");

            Optional<UserEntity> optionalUser = userJpaRepository.findByUserIdAndDelYn(userId, "N");
            if (optionalUser.isPresent()) {
                UserEntity userEntity = optionalUser.get();
                return NewObjectUtil.copyForClass(User.class, userEntity);
            }
            return null;
        } catch (Exception e) {
            logger.error("Error in getUserById: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("B0000008", "getUserById", e);
        }
    }

    @Override
    public User getUserByEmail(String userEmail) throws NewBusinessException {
        try {
            logger.debug("UserJpaRepositoryImpl.getUserByEmail called");

            Optional<UserEntity> optionalUser = userJpaRepository.findByUserEmailAndDelYn(userEmail, "N");
            if (optionalUser.isPresent()) {
                UserEntity userEntity = optionalUser.get();
                return NewObjectUtil.copyForClass(User.class, userEntity);
            }
            return null;
        } catch (Exception e) {
            logger.error("Error in getUserByEmail: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("B0000009", "getUserByEmail", e);
        }
    }

    @Override
    public int existsUser(String userId) throws NewBusinessException {
        try {
            logger.debug("UserJpaRepositoryImpl.existsUser called");

            boolean exists = userJpaRepository.existsByUserIdAndDelYn(userId, "N");
            return exists ? 1 : 0;
        } catch (Exception e) {
            logger.error("Error in existsUser: " + e.getMessage(), String.valueOf(e));
            throw new NewBusinessException("B0000010", "existsUser", e);
        }
    }
}