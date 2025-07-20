package com.kbstar.mbc.fc.foundation.service.impl;

import com.kbstar.mbc.fc.foundation.entity.User;
import com.kbstar.mbc.fc.foundation.repository.jpa.UserRepository;
import com.kbstar.mbc.fc.foundation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 사용자 서비스 구현 클래스
 * 
 * 프로그램명: UserServiceImpl.java
 * 설명: 사용자 관련 비즈니스 로직을 구현하는 서비스 클래스
 * 작성일: 2024-01-01
 * 작성자: SKAX Project Team
 * 
 * 주요 기능:
 * - 사용자 CRUD 작업 구현
 * - 사용자 검색 기능 구현
 * - 비즈니스 로직 처리
 * 
 * @version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        if (user.getCreatedDate() == null) {
            user.setCreatedDate(LocalDateTime.now());
        }
        user.setModifiedDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUsersByName(String userName) {
        return userRepository.findByUserNameContaining(userName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUsersByStatus(User.UserStatus status) {
        return userRepository.findByStatus(status);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUserStatus(Long userId, User.UserStatus status) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setStatus(status);
            user.setModifiedDate(LocalDateTime.now());
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found with ID: " + userId);
    }

    @Override
    public User updateLastLoginDate(Long userId, LocalDateTime loginDate) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setLastLoginDate(loginDate);
            user.setModifiedDate(LocalDateTime.now());
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found with ID: " + userId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countActiveUsers() {
        return userRepository.countActiveUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findUsersByRegistrationPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return userRepository.findUsersByRegistrationPeriod(startDate, endDate);
    }
}