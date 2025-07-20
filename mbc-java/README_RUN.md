# MBC 애플리케이션 실행 가이드

## 개요

MBC (Management Business Control) Spring Boot 애플리케이션의 실행 방법을 안내합니다.

## 사전 요구사항

- Java 8 이상
- Maven 3.6 이상
- Windows 환경 (배치 파일 사용)

## 실행 스크립트

### 1. 기본 실행 (start-mbc.bat)

```bash
start-mbc.bat
```

- 기본 환경으로 애플리케이션을 실행합니다.
- Maven 빌드 후 Spring Boot 애플리케이션을 시작합니다.

### 2. 개발 환경 실행 (start-dev.bat)

```bash
start-dev.bat
```

- 개발 환경(dev profile)으로 실행합니다.
- H2 인메모리 데이터베이스를 사용합니다.
- Swagger UI와 H2 콘솔이 활성화됩니다.

### 3. 운영 환경 실행 (start-prod.bat)

```bash
start-prod.bat
```

- 운영 환경(prod profile)으로 실행합니다.
- JAR 파일을 생성한 후 실행합니다.
- 프로덕션 데이터베이스를 사용합니다.

### 4. JAR 파일 직접 실행 (run-jar.bat)

```bash
run-jar.bat
```

- 이미 빌드된 JAR 파일을 직접 실행합니다.
- 환경 선택 메뉴가 제공됩니다.

### 5. Java 직접 실행 (run-java.bat)

```bash
run-java.bat
```

- Java로 직접 main 클래스를 실행합니다.
- 환경 선택 메뉴가 제공됩니다.

## 수동 실행 방법

### Maven을 통한 실행

```bash
# 개발 환경
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# 운영 환경
mvn spring-boot:run -Dspring-boot.run.profiles=prod

# 기본 환경
mvn spring-boot:run
```

### JAR 파일 직접 실행

```bash
# JAR 파일 생성
mvn clean package

# 개발 환경으로 실행
java -jar -Dspring.profiles.active=dev target/mbc-java-1.0.0-SNAPSHOT.jar

# 운영 환경으로 실행
java -jar -Dspring.profiles.active=prod target/mbc-java-1.0.0-SNAPSHOT.jar
```

### Java 직접 실행

```bash
# 클래스 컴파일
mvn clean compile

# 개발 환경으로 실행
java -cp target/classes -Dspring.profiles.active=dev com.kbstar.mbc.MbcApplication

# 운영 환경으로 실행
java -cp target/classes -Dspring.profiles.active=prod com.kbstar.mbc.MbcApplication
```

## 접속 URL

### 기본 URL

- **애플리케이션**: http://localhost:8080
- **API 문서**: http://localhost:8080/v3/api-docs

### 개발 환경 전용

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 콘솔**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (비어있음)

### 데이터베이스 관련 API

- **데이터베이스 상태**: http://localhost:8080/api/db/status
- **데이터베이스 통계**: http://localhost:8080/api/db/stats
- **테스트 데이터 조회**: http://localhost:8080/api/db/test-data

## 환경별 설정

### 개발 환경 (dev)

- H2 인메모리 데이터베이스
- Swagger UI 활성화
- H2 콘솔 활성화
- 디버그 로그 활성화

### 운영 환경 (prod)

- 프로덕션 데이터베이스 (application-prod.yml 설정)
- Swagger UI 비활성화
- 보안 강화 설정

## 문제 해결

### 빌드 실패

1. Java 버전 확인: `java -version`
2. Maven 버전 확인: `mvn -version`
3. 의존성 다운로드: `mvn dependency:resolve`

### 실행 실패

1. 포트 충돌 확인: 8080 포트가 사용 중인지 확인
2. 로그 확인: 콘솔 출력에서 오류 메시지 확인
3. 데이터베이스 연결 확인: application.yml 설정 확인

### 데이터베이스 문제

1. H2 콘솔 접속하여 테이블 확인
2. 데이터베이스 상태 API 호출: `/api/db/status`
3. 로그에서 초기화 오류 확인

## 로그 확인

애플리케이션 실행 시 콘솔에서 다음 정보를 확인할 수 있습니다:

- 데이터베이스 초기화 상태
- 테이블 생성 및 데이터 삽입 결과
- API 엔드포인트 등록 상태
- Spring Boot 시작 완료 메시지

## 종료 방법

- 콘솔에서 `Ctrl+C`를 눌러 애플리케이션을 종료합니다.
- 배치 파일 실행 시 자동으로 일시정지됩니다.
