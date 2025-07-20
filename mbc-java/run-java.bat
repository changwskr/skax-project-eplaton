@echo off
echo ========================================
echo MBC Java Direct Runner
echo ========================================
echo.

REM Java 버전 확인
echo Java 버전 확인 중...
java -version
echo.

REM 클래스패스 설정
set CLASSPATH=target\classes;target\dependency\*

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

REM Java로 직접 실행
if defined PROFILE (
    java -cp %CLASSPATH% -Dspring.profiles.active=%PROFILE% com.kbstar.mbc.MbcApplication
) else (
    java -cp %CLASSPATH% com.kbstar.mbc.MbcApplication
)

echo.
echo 애플리케이션이 종료되었습니다.
pause 