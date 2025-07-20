# SKAX Project Eplaton

스프링 부트 기반의 MBC 프로젝트입니다.

## 프로젝트 구조

```
skax-project-eplaton/
├── pom.xml                    # 최상위 부모 프로젝트
├── mbc01-java/               # 공통 라이브러리 모듈
│   ├── pom.xml
│   └── src/
├── mbc-config/               # 환경 설정 모듈
│   ├── pom.xml
│   ├── ifr_dev/             # 기존 설정 파일들
│   └── src/
└── mbc-java/                # 메인 스프링 부트 애플리케이션
    ├── pom.xml
    └── src/
        └── main/
            ├── java/
            │   └── com/kbstar/mbc/
            │       ├── MbcApplication.java
            │       ├── config/
            │       └── controller/
            └── resources/
                ├── application.yml
                ├── application-dev.yml
                └── application-prod.yml
```

## 모듈 설명

### 1. mbc01-java (공통 라이브러리)

- 기존 공통 라이브러리 소스
- Spring Framework, MyBatis, Apache Commons 등 포함
- 다른 모듈에서 공통으로 사용되는 기능들

### 2. mbc-config (환경 설정)

- 환경 관련 설정 파일들
- 기존 `ifr_dev` 디렉토리의 설정 파일들 포함
- Spring 설정 XML 파일들

### 3. mbc-java (메인 애플리케이션)

- 스프링 부트 기반의 메인 애플리케이션
- 웹 서비스, REST API 제공
- Spring Security, MyBatis, JPA 통합

## 기술 스택

- **Java**: 11
- **Spring Boot**: 2.7.18
- **Spring Framework**: 5.3.31
- **Spring Security**: 5.7.11
- **MyBatis**: 3.5.13
- **H2 Database**: 2.1.214 (개발용)
- **Maven**: 멀티모듈 프로젝트

## 빌드 및 실행

### 1. 전체 프로젝트 빌드

```bash
mvn clean install
```

### 2. 메인 애플리케이션 실행

```bash
cd mbc-java
mvn spring-boot:run
```

### 3. 개발 환경으로 실행

```bash
cd mbc-java
mvn spring-boot:run -Dspring.profiles.active=dev
```

### 4. 운영 환경으로 실행

```bash
cd mbc-java
mvn spring-boot:run -Dspring.profiles.active=prod
```

## 접속 정보

- **애플리케이션**: http://localhost:8080/mbc
- **H2 콘솔**: http://localhost:8080/mbc/h2-console
- **Actuator**: http://localhost:8080/mbc/actuator

## 주요 기능

1. **사용자 관리** (User Management)

   - 사용자 등록/수정/삭제
   - 권한 관리
   - 로그인/로그아웃

2. **계정 관리** (Account Management)

   - 계정 정보 관리
   - 계정 상태 관리

3. **리포트 생성** (Report Generation)

   - 다양한 리포트 생성
   - PDF, Excel 출력

4. **시스템 관리** (System Management)
   - 시스템 설정
   - 로그 관리
   - 모니터링

## 개발 가이드

### 1. 새로운 기능 추가

1. `mbc01-java`에 공통 기능 추가
2. `mbc-java`에 비즈니스 로직 구현
3. 컨트롤러에서 REST API 제공

### 2. 설정 변경

1. `mbc-config`의 설정 파일 수정
2. `application.yml`에서 환경별 설정 관리

### 3. 데이터베이스 변경

1. MyBatis 매퍼 XML 파일 수정
2. DTO 클래스 업데이트
3. 서비스 레이어 수정

## 라이센스

KBSTAR 내부 프로젝트입니다.
