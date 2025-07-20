@echo off
echo ========================================
echo MBC JAR File Runner
echo ========================================
echo.

REM JAR 파일 확인
if not exist "target\mbc-java-1.0.0-SNAPSHOT.jar" (
    echo JAR 파일이 없습니다. 먼저 빌드를 실행하세요.
    echo mvn clean package
    pause
    exit /b 1
)

echo JAR 파일 발견: target\mbc-java-1.0.0-SNAPSHOT.jar
echo.

REM 환경 선택
echo 실행 환경을 선택하세요:
echo 1. 개발 환경 (dev)
echo 2. 운영 환경 (prod)
echo 3. 기본 환경 (default)
echo.
set /p choice="선택 (1-3): "

if "%choice%"=="1" (
    set PROFILE=dev
    echo 개발 환경으로 실행합니다.
) else if "%choice%"=="2" (
    set PROFILE=prod
    echo 운영 환경으로 실행합니다.
) else (
    set PROFILE=
    echo 기본 환경으로 실행합니다.
)

echo.
echo MBC 애플리케이션 시작 중...
echo.

if defined PROFILE (
    java -jar -Dspring.profiles.active=%PROFILE% target\mbc-java-1.0.0-SNAPSHOT.jar
) else (
    java -jar target\mbc-java-1.0.0-SNAPSHOT.jar
)

echo.
echo 애플리케이션이 종료되었습니다.
pause 