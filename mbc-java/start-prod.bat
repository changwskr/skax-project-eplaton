@echo off
echo ========================================
echo MBC Production Environment Starter
echo ========================================
echo.

REM 운영 환경 설정
set SPRING_PROFILES_ACTIVE=prod
echo 운영 환경으로 설정: %SPRING_PROFILES_ACTIVE%
echo.

REM Java 버전 확인
echo Java 버전 확인 중...
java -version
echo.

REM Maven 빌드 (운영용)
echo Maven 빌드 시작 (운영용)...
call mvn clean compile -Dspring.profiles.active=prod
if %ERRORLEVEL% NEQ 0 (
    echo 빌드 실패! 종료합니다.
    pause
    exit /b 1
)
echo 빌드 완료!
echo.

REM JAR 파일 생성
echo JAR 파일 생성 중...
call mvn package -DskipTests -Dspring.profiles.active=prod
if %ERRORLEVEL% NEQ 0 (
    echo JAR 생성 실패! 종료합니다.
    pause
    exit /b 1
)
echo JAR 파일 생성 완료!
echo.

REM 애플리케이션 실행 (운영용)
echo MBC 운영 환경 시작 중...
echo.
echo 접속 URL:
echo - 애플리케이션: http://localhost:8080
echo - API 문서: http://localhost:8080/v3/api-docs
echo - 데이터베이스 상태: http://localhost:8080/api/db/status
echo.
echo 종료하려면 Ctrl+C를 누르세요.
echo.

call mvn spring-boot:run -Dspring-boot.run.main-class=com.kbstar.mbc.MbcApplication -Dspring-boot.run.profiles=prod

echo.
echo 운영 환경이 종료되었습니다.
pause 