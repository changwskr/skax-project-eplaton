@echo off
echo ========================================
echo MBC Development Environment Starter
echo ========================================
echo.

REM 개발 환경 설정
set SPRING_PROFILES_ACTIVE=dev
echo 개발 환경으로 설정: %SPRING_PROFILES_ACTIVE%
echo.

REM Java 버전 확인
echo Java 버전 확인 중...
java -version
echo.

REM Maven 빌드 (개발용)
echo Maven 빌드 시작 (개발용)...
call mvn clean compile -Dspring.profiles.active=dev
if %ERRORLEVEL% NEQ 0 (
    echo 빌드 실패! 종료합니다.
    pause
    exit /b 1
)
echo 빌드 완료!
echo.

REM 애플리케이션 실행 (개발용)
echo MBC 개발 환경 시작 중...
echo.
echo 접속 URL:
echo - 애플리케이션: http://localhost:8080
echo - Swagger UI: http://localhost:8080/swagger-ui.html
echo - API 문서: http://localhost:8080/v3/api-docs
echo - H2 콘솔: http://localhost:8080/h2-console
echo - 데이터베이스 상태: http://localhost:8080/api/db/status
echo.
echo H2 콘솔 접속 정보:
echo - JDBC URL: jdbc:h2:mem:testdb
echo - Username: sa
echo - Password: (비어있음)
echo.
echo 종료하려면 Ctrl+C를 누르세요.
echo.

call mvn spring-boot:run -Dspring-boot.run.main-class=com.kbstar.mbc.MbcApplication -Dspring-boot.run.profiles=dev

echo.
echo 개발 환경이 종료되었습니다.
pause 