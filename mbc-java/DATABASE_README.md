# 데이터베이스 초기화 및 테스트 데이터 가이드

## 📋 개요

MBC 프로젝트의 H2 인메모리 데이터베이스에 초기 테이블 생성과 테스트 데이터 입력 처리를 구현했습니다.

## 🗄️ 데이터베이스 스키마

### 테이블 구조

#### 1. ACCOUNT (계정 테이블)

```sql
CREATE TABLE ACCOUNT (
    ACCOUNT_ID VARCHAR(20) PRIMARY KEY,
    ACCOUNT_NAME VARCHAR(100) NOT NULL,
    ACCOUNT_TYPE VARCHAR(20) DEFAULT 'NORMAL',
    BALANCE DECIMAL(15,2) DEFAULT 0.00,
    STATUS VARCHAR(10) DEFAULT 'ACTIVE',
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY VARCHAR(50) DEFAULT 'SYSTEM',
    UPDATED_BY VARCHAR(50) DEFAULT 'SYSTEM'
);
```

#### 2. USER_INFO (사용자 테이블)

```sql
CREATE TABLE USER_INFO (
    USER_ID VARCHAR(20) PRIMARY KEY,
    USER_NAME VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100),
    PHONE VARCHAR(20),
    DEPARTMENT VARCHAR(50),
    ROLE VARCHAR(20) DEFAULT 'USER',
    STATUS VARCHAR(10) DEFAULT 'ACTIVE',
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY VARCHAR(50) DEFAULT 'SYSTEM',
    UPDATED_BY VARCHAR(50) DEFAULT 'SYSTEM'
);
```

#### 3. USER_ACCOUNT (사용자-계정 매핑 테이블)

```sql
CREATE TABLE USER_ACCOUNT (
    USER_ID VARCHAR(20),
    ACCOUNT_ID VARCHAR(20),
    RELATIONSHIP_TYPE VARCHAR(20) DEFAULT 'OWNER',
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (USER_ID, ACCOUNT_ID),
    FOREIGN KEY (USER_ID) REFERENCES USER_INFO(USER_ID),
    FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNT(ACCOUNT_ID)
);
```

#### 4. SYSTEM_CODE (시스템 코드 테이블)

```sql
CREATE TABLE SYSTEM_CODE (
    CODE_GROUP VARCHAR(50),
    CODE_VALUE VARCHAR(50),
    CODE_NAME VARCHAR(100) NOT NULL,
    CODE_DESC VARCHAR(200),
    SORT_ORDER INTEGER DEFAULT 0,
    USE_YN CHAR(1) DEFAULT 'Y',
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (CODE_GROUP, CODE_VALUE)
);
```

#### 5. SYSTEM_LOG (시스템 로그 테이블)

```sql
CREATE TABLE SYSTEM_LOG (
    LOG_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    LOG_LEVEL VARCHAR(10) NOT NULL,
    LOG_MESSAGE TEXT,
    LOG_CLASS VARCHAR(200),
    LOG_METHOD VARCHAR(100),
    USER_ID VARCHAR(20),
    IP_ADDRESS VARCHAR(45),
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 📊 초기 테스트 데이터

### 사용자 데이터 (10명)

- **USER001** - 홍길동 (개발팀, ADMIN)
- **USER002** - 김철수 (영업팀, MANAGER)
- **USER003** - 이영희 (고객지원팀, USER)
- **USER004** - 박민수 (개발팀, USER)
- **USER005** - 정수진 (마케팅팀, USER)
- **USER006** - 최동욱 (기획팀, MANAGER)
- **USER007** - 강미영 (인사팀, USER)
- **USER008** - 윤태호 (재무팀, USER)
- **USER009** - 임지원 (개발팀, USER)
- **USER010** - 한소영 (영업팀, USER)

### 계정 데이터 (15개)

- **일반계좌**: 6개 (ACC001, ACC003, ACC005, ACC007, ACC009, ACC012, ACC014)
- **VIP계좌**: 5개 (ACC002, ACC006, ACC010, ACC013)
- **기업계좌**: 4개 (ACC004, ACC008, ACC011, ACC015)

### 시스템 코드 데이터

- **ACCOUNT_TYPE**: NORMAL, VIP, ENTERPRISE
- **ACCOUNT_STATUS**: ACTIVE, INACTIVE, SUSPENDED
- **USER_ROLE**: ADMIN, USER, MANAGER
- **USER_STATUS**: ACTIVE, INACTIVE
- **RELATIONSHIP_TYPE**: OWNER, JOINT, AUTHORIZED

## ⚙️ 설정 파일

### 1. schema.sql

- 위치: `src/main/resources/schema.sql`
- 테이블 생성 스크립트
- 인덱스 생성 스크립트

### 2. data.sql

- 위치: `src/main/resources/data.sql`
- 초기 테스트 데이터 삽입 스크립트
- 시스템 코드 데이터
- 사용자 및 계정 데이터

### 3. DataInitializer.java

- 위치: `src/main/java/com/kbstar/mbc/config/DataInitializer.java`
- 애플리케이션 시작 시 데이터베이스 초기화
- 테이블 존재 여부 확인
- 초기 데이터 통계 로깅

## 🔍 데이터베이스 확인 API

### DatabaseTestController

- 위치: `src/main/java/com/kbstar/mbc/controller/DatabaseTestController.java`
- Swagger UI에서 데이터베이스 상태 확인 가능

#### 제공 API

1. **GET** `/api/database/status` - 데이터베이스 상태 확인
2. **GET** `/api/database/statistics` - 테이블별 데이터 통계
3. **GET** `/api/database/users` - 사용자 데이터 조회
4. **GET** `/api/database/accounts` - 계정 데이터 조회
5. **GET** `/api/database/user-accounts` - 사용자-계정 매핑 조회
6. **GET** `/api/database/codes` - 시스템 코드 조회
7. **GET** `/api/database/logs` - 시스템 로그 조회

## 🚀 사용 방법

### 1. 애플리케이션 실행

```bash
# Windows
build-and-run.bat

# 또는 직접 실행
cd mbc-java
mvn spring-boot:run
```

### 2. H2 콘솔 접속

```
http://localhost:8080/mbc/h2-console
```

- JDBC URL: `jdbc:h2:mem:mbcdb`
- Username: `sa`
- Password: (비어있음)

### 3. Swagger UI에서 데이터 확인

```
http://localhost:8080/mbc/swagger-ui.html
```

- "데이터베이스 테스트" 섹션에서 API 테스트

### 4. API 직접 호출

```bash
# 데이터베이스 상태 확인
curl "http://localhost:8080/mbc/api/database/status"

# 사용자 데이터 조회
curl "http://localhost:8080/mbc/api/database/users?limit=5"

# 계정 데이터 조회
curl "http://localhost:8080/mbc/api/database/accounts?limit=5"

# 사용자-계정 매핑 조회
curl "http://localhost:8080/mbc/api/database/user-accounts"
```

## 📈 초기화 로그

애플리케이션 시작 시 다음과 같은 로그가 출력됩니다:

```
=== 데이터베이스 초기화 시작 ===
테이블 존재 여부 확인 중...
테이블 ACCOUNT 확인 완료 - 레코드 수: 15
테이블 USER_INFO 확인 완료 - 레코드 수: 10
테이블 USER_ACCOUNT 확인 완료 - 레코드 수: 20
테이블 SYSTEM_CODE 확인 완료 - 레코드 수: 14
테이블 SYSTEM_LOG 확인 완료 - 레코드 수: 7
초기 데이터 확인 중...
사용자 데이터: 10건
계정 데이터: 15건
시스템 코드: 14건
사용자-계정 매핑: 20건
시스템 로그: 7건
=== 초기화 요약 ===
계정 타입 NORMAL: 6건
계정 타입 VIP: 5건
계정 타입 ENTERPRISE: 4건
사용자 역할 ADMIN: 1건
사용자 역할 USER: 7건
사용자 역할 MANAGER: 2건
총 계좌 잔액: 89,500,000원
=== 초기화 요약 완료 ===
=== 데이터베이스 초기화 완료 ===
```

## 🔧 데이터 수정 방법

### 1. schema.sql 수정

- 테이블 구조 변경 시 수정
- 애플리케이션 재시작 필요

### 2. data.sql 수정

- 초기 데이터 변경 시 수정
- 애플리케이션 재시작 필요

### 3. 런타임 데이터 수정

- H2 콘솔에서 직접 수정
- API를 통한 데이터 수정

## 📋 데이터 통계

### 초기 데이터 요약

- **사용자**: 10명 (ADMIN 1명, MANAGER 2명, USER 7명)
- **계정**: 15개 (일반 6개, VIP 5개, 기업 4개)
- **매핑**: 20개 (소유자 15개, 공동소유자 5개)
- **시스템 코드**: 14개 (5개 그룹)
- **총 계좌 잔액**: 89,500,000원

### 계정 타입별 분포

- **일반계좌**: 40% (6개)
- **VIP계좌**: 33% (5개)
- **기업계좌**: 27% (4개)

### 사용자 역할별 분포

- **관리자**: 10% (1명)
- **매니저**: 20% (2명)
- **일반사용자**: 70% (7명)

## 🔍 문제 해결

### 1. 테이블이 생성되지 않는 경우

- `schema.sql` 파일이 `src/main/resources/`에 있는지 확인
- 애플리케이션 로그에서 오류 메시지 확인
- H2 콘솔에서 직접 테이블 생성 확인

### 2. 데이터가 삽입되지 않는 경우

- `data.sql` 파일이 `src/main/resources/`에 있는지 확인
- 외래키 제약조건 위반 여부 확인
- 애플리케이션 로그에서 오류 메시지 확인

### 3. 초기화 로그가 출력되지 않는 경우

- `DataInitializer` 클래스가 정상적으로 컴파일되었는지 확인
- Spring Boot 애플리케이션이 정상적으로 시작되었는지 확인

## 📞 지원

문제가 발생하거나 추가 설정이 필요한 경우 SKAX Project Team에 문의하세요.

---

**버전**: 1.0.0  
**작성일**: 2024-01-01  
**작성자**: SKAX Project Team
