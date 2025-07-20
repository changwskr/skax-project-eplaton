# MBC 애플리케이션 환경 설정 가이드

## 개요

MBC (Management Business Control) 애플리케이션의 환경별 설정 파일과 실행 방법을 안내합니다.

## 환경별 설정 파일

### 1. 개발 환경 (dev)

- **파일**: `application-dev.yml`
- **용도**: 개발팀이 사용하는 공통 개발 환경
- **특징**:
  - H2 인메모리 데이터베이스
  - 상세한 로깅 (DEBUG 레벨)
  - Swagger UI 활성화
  - H2 콘솔 활성화
  - 보안 설정 완화

### 2. 로컬 환경 (local)

- **파일**: `application-local.yml`
- **용도**: 개인 개발자가 사용하는 로컬 환경
- **특징**:
  - H2 인메모리 데이터베이스 (MySQL 모드)
  - 최대 디버그 로깅
  - Spring DevTools 활성화
  - Hot Reload 지원
  - 모든 Actuator 엔드포인트 노출

### 3. 테스트 환경 (test)

- **파일**: `application-test.yml`
- **용도**: 통합 테스트 및 QA 테스트
- **특징**:
  - H2 파일 데이터베이스 (포트 8081)
  - 테스트용 보안 설정
  - 중간 수준 로깅
  - 캐시 비활성화

### 4. 운영 환경 (prod)

- **파일**: `application-prod.yml`
- **용도**: 실제 운영 서버
- **특징**:
  - MySQL 데이터베이스
  - 최소 로깅 (WARN 레벨)
  - Swagger UI 비활성화
  - 강화된 보안 설정
  - 성능 최적화 설정

## 환경별 실행 스크립트

### 개발 환경

```bash
start-dev.bat
```

- 포트: 8080
- 데이터베이스: H2 인메모리
- 로그인: admin/admin123

### 로컬 환경

```bash
start-local.bat
```

- 포트: 8080
- 데이터베이스: H2 인메모리 (MySQL 모드)
- 로그인: local/local123
- Hot Reload 지원

### 테스트 환경

```bash
start-test.bat
```

- 포트: 8081
- 데이터베이스: H2 파일
- 로그인: test/test123

### 운영 환경

```bash
start-prod.bat
```

- 포트: 8080
- 데이터베이스: MySQL
- 로그인: 환경변수 설정 필요

## 환경변수 설정

### 운영 환경 필수 환경변수

```bash
# 데이터베이스 설정
DB_HOST=localhost
DB_PORT=3306
DB_NAME=mbc_prod
DB_USERNAME=mbc_user
DB_PASSWORD=your_password

# 관리자 계정
ADMIN_USERNAME=admin
ADMIN_PASSWORD=your_admin_password

# CORS 설정
ALLOWED_ORIGINS=https://mbc.kbstar.com
```

### 로컬 환경 환경변수 (선택사항)

```bash
# 로컬 개발용 설정
SPRING_PROFILES_ACTIVE=local
JAVA_OPTS=-Xmx2g -Xms1g
```

## 데이터베이스 설정

### H2 데이터베이스 (개발/로컬/테스트)

```yaml
# 인메모리 (개발/로컬)
url: jdbc:h2:mem:mbcdb_dev

# 파일 (테스트)
url: jdbc:h2:file:./data/mbcdb_test
```

### MySQL 데이터베이스 (운영)

```yaml
url: jdbc:mysql://localhost:3306/mbc_prod
username: mbc_user
password: ${DB_PASSWORD}
```

## 로깅 설정

### 개발/로컬 환경

```yaml
logging:
  level:
    root: DEBUG
    com.kbstar.mbc: DEBUG
    org.springframework: DEBUG
```

### 운영 환경

```yaml
logging:
  level:
    root: WARN
    com.kbstar.mbc: INFO
    org.springframework: WARN
```

## 보안 설정

### 개발/로컬/테스트 환경

```yaml
spring:
  security:
    user:
      name: admin
      password: admin123
      roles: ADMIN,USER
```

### 운영 환경

```yaml
spring:
  security:
    user:
      name: ${ADMIN_USERNAME}
      password: ${ADMIN_PASSWORD}
      roles: ADMIN
```

## Swagger UI 설정

### 개발/로컬/테스트 환경

```yaml
springdoc:
  swagger-ui:
    enabled: true
    path: /swagger-ui.html
```

### 운영 환경

```yaml
springdoc:
  swagger-ui:
    enabled: false
```

## 성능 설정

### 개발/로컬/테스트 환경

```yaml
# 최소 리소스 사용
server:
  tomcat:
    threads:
      max: 50
      min-spare: 5
```

### 운영 환경

```yaml
# 고성능 설정
server:
  tomcat:
    threads:
      max: 200
      min-spare: 10
    max-connections: 8192
```

## 모니터링 설정

### 개발/로컬 환경

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

### 운영 환경

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
```

## 캐시 설정

### 개발/로컬/테스트 환경

```yaml
mbc:
  cache:
    enabled: false
```

### 운영 환경

```yaml
mbc:
  cache:
    enabled: true
    ttl: 1800 # 30분
    max-size: 5000
```

## 문제 해결

### 환경변수 확인

```bash
# Windows
echo %SPRING_PROFILES_ACTIVE%

# Linux/Mac
echo $SPRING_PROFILES_ACTIVE
```

### 설정 파일 우선순위

1. `application-{profile}.yml`
2. `application.yml`
3. 환경변수
4. 시스템 속성

### 포트 충돌 해결

```bash
# 포트 사용 확인
netstat -ano | findstr :8080

# 프로세스 종료
taskkill /PID <process_id> /F
```

### 데이터베이스 연결 확인

```bash
# H2 콘솔 접속
http://localhost:8080/mbc/h2-console

# MySQL 연결 테스트
mysql -h localhost -u mbc_user -p mbc_prod
```

## 권장사항

### 개발 시

- 로컬 환경(`local`) 사용 권장
- Hot Reload 활성화
- 상세한 로깅 활용

### 테스트 시

- 테스트 환경(`test`) 사용
- 독립적인 데이터베이스 사용
- 캐시 비활성화

### 운영 시

- 환경변수 사용
- 로그 레벨 최소화
- 보안 강화
- 성능 모니터링 활성화
