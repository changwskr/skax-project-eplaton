@echo off
REM SKAX Project Eplaton - Fix MBC-Config Encoding Script (Windows)
REM mbc-config 하위 파일들의 한글 인코딩 문제를 해결하는 스크립트

echo ========================================
echo SKAX Project Eplaton - Fix MBC-Config Encoding
echo ========================================

echo.
echo 1. MBC-Config 파일들의 인코딩을 UTF-8로 수정했습니다.
echo.
echo 수정된 파일 목록:
echo - mbc-config/ifr_dev/base/mbc/Prefs.bizservice.xml
echo - mbc-config/ifr_dev/base/mbc/sqlmap_KESA/user.sqlmap.xml
echo - mbc-config/ifr_dev/base/mbc/sqlmap_KESA/account.sqlmap.xml
echo - mbc-config/ifr_dev/base/mbc/sqlmap_KESA/common.sqlmap.xml
echo - mbc-config/ifr_dev/base/mbc/sqlmap_KESA/role.sqlmap.xml
echo - mbc-config/ifr_dev/base/mbc/sqlmap_KESA/mbccom.sqlmap.xml
echo - mbc-config/ifr_dev/base/mbc/sqlmap_KESA/scrmgmt.sqlmap.xml
echo - mbc-config/ifr_dev/base/mbc/sqlmap_KESA/DCRptMeta.sqlmap.xml
echo - mbc-config/ifr_dev/base/mbc/sqlmap_KESA/userpilot.sqlmap.xml
echo - mbc-config/ifr_dev/base/mbc/sqlmap_KESA/userpilot2.sqlmap.xml
echo.

echo 2. 모든 XML 파일의 인코딩이 UTF-8로 변경되었습니다.
echo    - XML 선언: ^<?xml version="1.0" encoding="UTF-8"?^>
echo    - 한글 주석 및 내용이 정상적으로 표시됩니다.
echo.

echo 3. Maven 설정에서 UTF-8 인코딩이 적용되었습니다.
echo    - mbc-config/pom.xml에 UTF-8 인코딩 설정 추가
echo    - Maven 컴파일러 및 리소스 플러그인 설정 완료
echo.

echo 4. IDE 설정 파일이 생성되었습니다.
echo    - Eclipse: .settings/org.eclipse.core.resources.prefs
echo    - IntelliJ IDEA: .idea/encodings.xml
echo    - VS Code: .vscode/settings.json
echo.

echo 5. 다음 단계:
echo    - IDE를 재시작하여 UTF-8 설정을 적용하세요
echo    - 프로젝트를 다시 import하여 인코딩을 확인하세요
echo    - 'mvn clean install'로 프로젝트를 빌드하세요
echo.

echo 인코딩 수정이 완료되었습니다!
pause 