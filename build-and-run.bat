@echo off
REM SKAX Project Eplaton - Build and Run Script (Windows)

echo ========================================
echo SKAX Project Eplaton - Build and Run
echo ========================================

echo.
echo 1. Building the entire project...
call mvn clean install -DskipTests

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Build failed!
    pause
    exit /b 1
)

echo.
echo 2. Starting the application...
echo.
echo Choose environment:
echo 1. Development (dev)
echo 2. Production (prod)
echo 3. Default
echo.
set /p choice="Enter your choice (1-3): "

if "%choice%"=="1" (
    echo Starting in development mode...
    cd mbc-java
    call mvn spring-boot:run -Dspring.profiles.active=dev
) else if "%choice%"=="2" (
    echo Starting in production mode...
    cd mbc-java
    call mvn spring-boot:run -Dspring.profiles.active=prod
) else (
    echo Starting in default mode...
    cd mbc-java
    call mvn spring-boot:run
)

echo.
echo Application started!
echo Access URLs:
echo - Application: http://localhost:8080/mbc
echo - H2 Console: http://localhost:8080/mbc/h2-console
echo - Actuator: http://localhost:8080/mbc/actuator
echo.
echo Press Ctrl+C to stop the application
pause 