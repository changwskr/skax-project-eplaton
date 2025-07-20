@echo off
echo ========================================
echo MBC Spring Boot Application Starter
echo ========================================
echo.

REM 현재 디렉토리 확인
echo 현재 작업 디렉토리: %CD%
echo.

REM Java 버전 확인
echo Java 버전 확인 중...
java -version
echo.

REM Maven 빌드
echo Maven 빌드 시작...
call mvn clean compile
if %ERRORLEVEL% NEQ 0 (
    echo 빌드 실패! 종료합니다.
    pause
    exit /b 1
)
echo 빌드 완료!
echo.

REM 애플리케이션 실행
echo MBC 애플리케이션 시작 중...
echo.
echo 접속 URL:
echo - 애플리케이션: http://localhost:8080
echo - Swagger UI: http://localhost:8080/swagger-ui.html
echo - API 문서: http://localhost:8080/v3/api-docs
echo - 데이터베이스 상태: http://localhost:8080/api/db/status
echo - 데이터베이스 통계: http://localhost:8080/api/db/stats
echo.
echo 종료하려면 Ctrl+C를 누르세요.
echo.

call mvn spring-boot:run -Dspring-boot.run.main-class=com.kbstar.mbc.MbcApplication

echo.
echo 애플리케이션이 종료되었습니다.
pause 