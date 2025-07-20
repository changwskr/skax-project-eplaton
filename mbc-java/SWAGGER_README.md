# Swagger/OpenAPI 문서화 가이드

## 📋 개요

MBC 프로젝트에 Swagger/OpenAPI 3.0 기반의 API 문서화 환경을 구축했습니다.

## 🚀 접속 방법

### Swagger UI 접속

```
http://localhost:8080/mbc/swagger-ui.html
```

### OpenAPI JSON 스펙

```
http://localhost:8080/mbc/api-docs
```

## 📦 설치된 의존성

### pom.xml에 추가된 의존성

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```

## ⚙️ 설정 파일

### 1. SwaggerConfig.java

- OpenAPI 3.0 스펙 설정
- API 정보 및 서버 정보 구성
- 위치: `src/main/java/com/kbstar/mbc/config/SwaggerConfig.java`

### 2. application.yml 설정

```yaml
# Swagger/OpenAPI Configuration
springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operations-sorter: method
    tags-sorter: alpha
    doc-expansion: none
    disable-swagger-default-url: true
  packages-to-scan: com.kbstar.mbc.ac
  paths-to-match: /api/**
```

## 📝 API 문서화 방법

### 1. 클래스 레벨 어노테이션

```java
@Tag(name = "계정 관리", description = "계정 관련 API")
@RestController
@RequestMapping("/api/account")
public class AccountController {
    // ...
}
```

### 2. 메서드 레벨 어노테이션

```java
@Operation(
    summary = "계정 생성",
    description = "새로운 계정을 생성합니다."
)
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "계정 생성 성공"
    ),
    @ApiResponse(
        responseCode = "400",
        description = "잘못된 요청"
    )
})
@PostMapping("/create")
public ResponseEntity<Map<String, Object>> createAccount(
    @RequestBody AccountPDTO accountPDTO) {
    // ...
}
```

### 3. 파라미터 문서화

```java
@Parameter(
    description = "계좌번호",
    required = true,
    example = "ACC001"
)
@RequestParam String accountId
```

### 4. 요청 본문 문서화

```java
@io.swagger.v3.oas.annotations.parameters.RequestBody(
    description = "계정 정보",
    required = true,
    content = @Content(
        mediaType = "application/json",
        schema = @Schema(implementation = AccountPDTO.class),
        examples = @ExampleObject(
            name = "계정 생성 예시",
            value = "{\"accountId\": \"ACC001\", \"accountName\": \"테스트 계정\"}"
        )
    )
)
@RequestBody AccountPDTO accountPDTO
```

## 🧪 테스트 API

### SwaggerTestController

- 위치: `src/main/java/com/kbstar/mbc/controller/SwaggerTestController.java`
- 다양한 HTTP 메서드 테스트용 API 제공

#### 테스트 API 목록

1. **GET** `/api/test/hello` - Hello API
2. **POST** `/api/test/echo` - Echo API
3. **PUT** `/api/test/update/{id}` - Update API
4. **DELETE** `/api/test/delete/{id}` - Delete API

## 📚 주요 어노테이션

### 클래스 레벨

- `@Tag` - API 그룹 분류
- `@RestController` - REST 컨트롤러
- `@RequestMapping` - 기본 경로 설정

### 메서드 레벨

- `@Operation` - API 작업 설명
- `@ApiResponses` - 응답 코드 정의
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` - HTTP 메서드

### 파라미터 레벨

- `@Parameter` - 파라미터 설명
- `@RequestBody` - 요청 본문
- `@RequestParam` - 쿼리 파라미터
- `@PathVariable` - 경로 변수

## 🔧 설정 옵션

### springdoc 설정 옵션

```yaml
springdoc:
  # API 문서 경로
  api-docs:
    path: /api-docs

  # Swagger UI 설정
  swagger-ui:
    path: /swagger-ui.html
    operations-sorter: method # 메서드별 정렬
    tags-sorter: alpha # 알파벳 순 정렬
    doc-expansion: none # 기본 접힘 상태
    disable-swagger-default-url: true

  # 스캔할 패키지
  packages-to-scan: com.kbstar.mbc.ac

  # 매칭할 경로 패턴
  paths-to-match: /api/**
```

## 🌐 CORS 설정

모든 AC 컨트롤러에 CORS 설정이 적용되어 있습니다:

```java
@CrossOrigin(origins = "*")
```

## 📋 API 그룹 분류

### 1. 계정 관리 (Account)

- `ACMBC71001` - 계정 생성
- `ACMBC72001` - 계정 조회
- `ACMBC72002` - 계정 수정
- `ACMBC73001` - 계정 삭제
- `ACMBC74001` - 계정 목록 조회

### 2. 사용자 관리 (User)

- `ACMBC75Z01` - 사용자 목록 조회
- `ACMBC75Z02` - 사용자 트리 조회
- `ACMBC75Z03` - 사용자 상세 조회

### 3. 런타임 처리 (Runtime)

- `ACMBC75999` - 런타임 요청 처리
- `ACMBC759Z1` - 런타임 특별 처리

### 4. 공통 처리 (Common)

- `MBCComAC` - 공통 처리

### 5. 테스트 API (Test)

- `SwaggerTestController` - 테스트용 API

## 🚀 사용 예시

### 1. 계정 생성 API 테스트

```bash
# POST 방식
curl -X POST "http://localhost:8080/mbc/api/account/create" \
  -H "Content-Type: application/json" \
  -d '{"accountId": "ACC001", "accountName": "테스트 계정"}'

# GET 방식
curl "http://localhost:8080/mbc/api/account/create?accountId=ACC001&accountName=테스트계정"
```

### 2. 사용자 목록 조회 API 테스트

```bash
curl "http://localhost:8080/mbc/api/user/list?page=1&size=10&searchKeyword=홍길동"
```

### 3. 테스트 API 사용

```bash
# Hello API
curl "http://localhost:8080/mbc/api/test/hello?name=홍길동"

# Echo API
curl -X POST "http://localhost:8080/mbc/api/test/echo" \
  -H "Content-Type: application/json" \
  -d '{"message": "Hello", "data": "test"}'
```

## 🔍 문제 해결

### 1. Swagger UI가 로드되지 않는 경우

- 애플리케이션이 정상적으로 시작되었는지 확인
- 포트 8080이 사용 가능한지 확인
- 브라우저 캐시 삭제 후 재시도

### 2. API가 Swagger UI에 표시되지 않는 경우

- `@RestController` 어노테이션이 있는지 확인
- `@RequestMapping` 경로가 `/api/**` 패턴과 일치하는지 확인
- 패키지가 `com.kbstar.mbc.ac`에 포함되어 있는지 확인

### 3. 의존성 문제

```bash
# Maven 의존성 새로고침
mvn clean install

# IDE에서 프로젝트 새로고침
```

## 📞 지원

문제가 발생하거나 추가 설정이 필요한 경우 SKAX Project Team에 문의하세요.

---

**버전**: 1.0.0  
**작성일**: 2024-01-01  
**작성자**: SKAX Project Team
