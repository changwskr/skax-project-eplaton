@echo off
echo ========================================
echo MBC Local Development Environment Starter
echo ========================================
echo.

REM 로컬 환경 설정
set SPRING_PROFILES_ACTIVE=local
echo 로컬 개발 환경으로 설정: %SPRING_PROFILES_ACTIVE%
echo.

REM Java 버전 확인
echo Java 버전 확인 중...
java -version
echo.

REM Maven 빌드 (로컬용)
echo Maven 빌드 시작 (로컬용)...
call mvn clean compile -Dspring.profiles.active=local
if %ERRORLEVEL% NEQ 0 (
    echo 빌드 실패! 종료합니다.
    pause
    exit /b 1
)
echo 빌드 완료!
echo.

REM 애플리케이션 실행 (로컬용)
echo MBC 로컬 개발 환경 시작 중...
echo.
echo 접속 URL:
echo - 애플리케이션: http://localhost:8080/mbc
echo - Swagger UI: http://localhost:8080/mbc/swagger-ui.html
echo - API 문서: http://localhost:8080/mbc/api-docs
echo - H2 콘솔: http://localhost:8080/mbc/h2-console
echo - 데이터베이스 상태: http://localhost:8080/mbc/api/db/status
echo - Actuator: http://localhost:8080/mbc/actuator
echo.
echo H2 콘솔 접속 정보:
echo - JDBC URL: jdbc:h2:mem:mbcdb_local
echo - Username: sa
echo - Password: (비어있음)
echo.
echo 로그인 정보:
echo - Username: local
echo - Password: local123
echo.
echo 종료하려면 Ctrl+C를 누르세요.
echo.

call mvn spring-boot:run -Dspring-boot.run.main-class=com.kbstar.mbc.MbcApplication -Dspring-boot.run.profiles=local

echo.
echo 로컬 개발 환경이 종료되었습니다.
pause 