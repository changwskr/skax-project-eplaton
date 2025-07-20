-- ========================================
-- MBC Project Initial Test Data
-- SKAX Project Eplaton
-- ========================================

-- 시스템 코드 데이터 삽입
INSERT INTO SYSTEM_CODE (CODE_GROUP, CODE_VALUE, CODE_NAME, CODE_DESC, SORT_ORDER) VALUES
('ACCOUNT_TYPE', 'NORMAL', '일반계좌', '일반 사용자 계좌', 1),
('ACCOUNT_TYPE', 'VIP', 'VIP계좌', 'VIP 고객 전용 계좌', 2),
('ACCOUNT_TYPE', 'ENTERPRISE', '기업계좌', '기업 고객 전용 계좌', 3),
('ACCOUNT_STATUS', 'ACTIVE', '활성', '활성 상태', 1),
('ACCOUNT_STATUS', 'INACTIVE', '비활성', '비활성 상태', 2),
('ACCOUNT_STATUS', 'SUSPENDED', '정지', '정지 상태', 3),
('USER_ROLE', 'ADMIN', '관리자', '시스템 관리자', 1),
('USER_ROLE', 'USER', '일반사용자', '일반 사용자', 2),
('USER_ROLE', 'MANAGER', '매니저', '매니저', 3),
('USER_STATUS', 'ACTIVE', '활성', '활성 상태', 1),
('USER_STATUS', 'INACTIVE', '비활성', '비활성 상태', 2),
('RELATIONSHIP_TYPE', 'OWNER', '소유자', '계좌 소유자', 1),
('RELATIONSHIP_TYPE', 'JOINT', '공동소유자', '공동 소유자', 2),
('RELATIONSHIP_TYPE', 'AUTHORIZED', '대리인', '대리인', 3);

-- 사용자 데이터 삽입
INSERT INTO USER_INFO (USER_ID, USER_NAME, EMAIL, PHONE, DEPARTMENT, ROLE, STATUS) VALUES
('USER001', '홍길동', 'hong@kbstar.com', '010-1234-5678', '개발팀', 'ADMIN', 'ACTIVE'),
('USER002', '김철수', 'kim@kbstar.com', '010-2345-6789', '영업팀', 'MANAGER', 'ACTIVE'),
('USER003', '이영희', 'lee@kbstar.com', '010-3456-7890', '고객지원팀', 'USER', 'ACTIVE'),
('USER004', '박민수', 'park@kbstar.com', '010-4567-8901', '개발팀', 'USER', 'ACTIVE'),
('USER005', '정수진', 'jung@kbstar.com', '010-5678-9012', '마케팅팀', 'USER', 'ACTIVE'),
('USER006', '최동욱', 'choi@kbstar.com', '010-6789-0123', '기획팀', 'MANAGER', 'ACTIVE'),
('USER007', '강미영', 'kang@kbstar.com', '010-7890-1234', '인사팀', 'USER', 'ACTIVE'),
('USER008', '윤태호', 'yoon@kbstar.com', '010-8901-2345', '재무팀', 'USER', 'ACTIVE'),
('USER009', '임지원', 'lim@kbstar.com', '010-9012-3456', '개발팀', 'USER', 'ACTIVE'),
('USER010', '한소영', 'han@kbstar.com', '010-0123-4567', '영업팀', 'USER', 'ACTIVE');

-- 계정 데이터 삽입
INSERT INTO ACCOUNT (ACCOUNT_ID, ACCOUNT_NAME, ACCOUNT_TYPE, BALANCE, STATUS) VALUES
('ACC001', '홍길동 일반계좌', 'NORMAL', 1000000.00, 'ACTIVE'),
('ACC002', '김철수 VIP계좌', 'VIP', 5000000.00, 'ACTIVE'),
('ACC003', '이영희 일반계좌', 'NORMAL', 250000.00, 'ACTIVE'),
('ACC004', '박민수 기업계좌', 'ENTERPRISE', 10000000.00, 'ACTIVE'),
('ACC005', '정수진 일반계좌', 'NORMAL', 750000.00, 'ACTIVE'),
('ACC006', '최동욱 VIP계좌', 'VIP', 3000000.00, 'ACTIVE'),
('ACC007', '강미영 일반계좌', 'NORMAL', 500000.00, 'ACTIVE'),
('ACC008', '윤태호 기업계좌', 'ENTERPRISE', 15000000.00, 'ACTIVE'),
('ACC009', '임지원 일반계좌', 'NORMAL', 1250000.00, 'ACTIVE'),
('ACC010', '한소영 VIP계좌', 'VIP', 8000000.00, 'ACTIVE'),
('ACC011', '홍길동 기업계좌', 'ENTERPRISE', 20000000.00, 'ACTIVE'),
('ACC012', '김철수 일반계좌', 'NORMAL', 300000.00, 'ACTIVE'),
('ACC013', '이영희 VIP계좌', 'VIP', 4500000.00, 'ACTIVE'),
('ACC014', '박민수 일반계좌', 'NORMAL', 600000.00, 'ACTIVE'),
('ACC015', '정수진 기업계좌', 'ENTERPRISE', 12000000.00, 'ACTIVE');

-- 사용자-계정 매핑 데이터 삽입
INSERT INTO USER_ACCOUNT (USER_ID, ACCOUNT_ID, RELATIONSHIP_TYPE) VALUES
('USER001', 'ACC001', 'OWNER'),
('USER001', 'ACC011', 'OWNER'),
('USER002', 'ACC002', 'OWNER'),
('USER002', 'ACC012', 'OWNER'),
('USER003', 'ACC003', 'OWNER'),
('USER003', 'ACC013', 'OWNER'),
('USER004', 'ACC004', 'OWNER'),
('USER004', 'ACC014', 'OWNER'),
('USER005', 'ACC005', 'OWNER'),
('USER005', 'ACC015', 'OWNER'),
('USER006', 'ACC006', 'OWNER'),
('USER007', 'ACC007', 'OWNER'),
('USER008', 'ACC008', 'OWNER'),
('USER009', 'ACC009', 'OWNER'),
('USER010', 'ACC010', 'OWNER'),
-- 공동 소유자 관계 추가
('USER001', 'ACC002', 'JOINT'),
('USER002', 'ACC003', 'JOINT'),
('USER003', 'ACC004', 'JOINT'),
('USER004', 'ACC005', 'JOINT'),
('USER005', 'ACC006', 'JOINT');

-- 시스템 로그 샘플 데이터 삽입
INSERT INTO SYSTEM_LOG (LOG_LEVEL, LOG_MESSAGE, LOG_CLASS, LOG_METHOD, USER_ID, IP_ADDRESS) VALUES
('INFO', '시스템 시작', 'com.kbstar.mbc.MbcApplication', 'main', 'SYSTEM', '127.0.0.1'),
('INFO', '데이터베이스 연결 성공', 'com.kbstar.mbc.config.DataAccessConfig', 'dataSource', 'SYSTEM', '127.0.0.1'),
('INFO', 'Swagger UI 초기화 완료', 'com.kbstar.mbc.config.SwaggerConfig', 'customOpenAPI', 'SYSTEM', '127.0.0.1'),
('DEBUG', '계정 생성 요청', 'com.kbstar.mbc.ac.accountac.ACMBC71001', 'createAccountPost', 'USER001', '192.168.1.100'),
('INFO', '계정 생성 성공: ACC001', 'com.kbstar.mbc.ac.accountac.ACMBC71001', 'createAccountPost', 'USER001', '192.168.1.100'),
('DEBUG', '사용자 조회 요청', 'com.kbstar.mbc.ac.usermgtac.ACMBC75Z01', 'getUserList', 'USER002', '192.168.1.101'),
('INFO', '사용자 조회 성공: 10건', 'com.kbstar.mbc.ac.usermgtac.ACMBC75Z01', 'getUserList', 'USER002', '192.168.1.101'); 