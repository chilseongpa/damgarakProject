-- 테이블 삭제 구문
DROP TABLE SUGGEST_MENU;
DROP TABLE EMPLOYEE;
DROP TABLE DEPT;
DROP TABLE JOB_CODE;
DROP TABLE SUGGESTION_RESPONSE;
DROP TABLE POST;
DROP TABLE PAYMENT;
DROP TABLE LUNCHBOX_RESERVATION;
DROP TABLE TABLE_RESERVATION;
DROP TABLE ORDER_DETAILS;
DROP TABLE ORDER_HISTORY;
DROP TABLE MENU;
DROP TABLE MENU_CATEGORY;
DROP TABLE MEMBERSHIP;
DROP TABLE RESERVATION;
DROP TABLE NOTICE;
DROP TABLE POST_CATEGORY;
DROP TABLE REPLY;
DROP TABLE USERS;

-- 테이블 생성 -------------------------------------------------------

-- 사용자 정보 저장 테이블
CREATE TABLE USERS (
    USERS_ID VARCHAR2(20) PRIMARY KEY, -- 사용자 ID, 고유 식별자
    USERS_PASSWORD VARCHAR2(100) NOT NULL, -- 사용자 비밀번호
    USERS_NAME VARCHAR2(30) NOT NULL, -- 사용자 이름
    EMAIL VARCHAR2(30) NOT NULL UNIQUE, -- 사용자 이메일, 고유해야 함
    JOIN_DATE DATE DEFAULT SYSDATE NOT NULL, -- 가입일, 기본값은 현재 날짜
    WITHDRAWAL_DATE DATE, -- 탈퇴 날짜
    USERS_STATE VARCHAR2(1) DEFAULT 'n' CHECK (USERS_STATE IN ('y', 'n')) NOT NULL, -- 계정 상태 (y/n)
    USERS_TYPE VARCHAR2(9) DEFAULT '고객' NOT NULL, -- 사용자 유형 (고객, 직원)
    CONSTRAINT CHK_USER_TYPE CHECK (USERS_TYPE IN ('고객', '직원')) -- 사용자 유형 제한
);

CREATE TABLE MEMBERSHIP (
    USERS_ID VARCHAR2(20) NOT NULL,
    MEMBER_LEVEL VARCHAR2(20) DEFAULT '일반회원' CHECK(MEMBER_LEVEL IN('일반회원', 'VIP', 'V.VIp')),
    ACCUMULATED_AMOUNT NUMBER DEFAULT 0,
   PRIMARY KEY (USERS_ID),
   FOREIGN KEY (USERS_ID) REFERENCES USERS (USERS_ID)
);

INSERT INTO MEMBERSHIP(USERS_ID, MEMBER_LEVEL, ACCUMULATED_AMOUNT)
VALUES('whdrns456', '일반회원', 0);

-- 메뉴 카테고리 테이블
CREATE TABLE MENU_CATEGORY (
    CATEGORY_CODE NUMBER PRIMARY KEY, -- 카테고리 코드, 기본 키
    CATEGORY_NAME VARCHAR2(15) NOT NULL -- 카테고리 이름
);

-- 메뉴 정보 테이블
CREATE TABLE MENU (
    MENU_NO NUMBER PRIMARY KEY, -- 메뉴 번호
    MENU_NAME VARCHAR2(50), -- 메뉴 이름
    PRICE NUMBER DEFAULT 0 NOT NULL, -- 메뉴 가격
    MENU_TYPE VARCHAR2(2) CHECK (MENU_TYPE IN ('r', 'l', 'a')), -- 메뉴 유형 (r, l, a)
    MENU_IMAGE VARCHAR2(200), -- 메뉴 이미지 경로
    MENU_DESCRIPTION VARCHAR2(150), -- 메뉴 설명
    CALORIE NUMBER NOT NULL, -- 메뉴 칼로리
    CATEGORY_CODE NUMBER NOT NULL, -- 메뉴 카테고리 코드
    MENU_COUNT NUMBER DEFAULT 0, -- 메뉴 수량
    FOREIGN KEY (CATEGORY_CODE) REFERENCES MENU_CATEGORY(CATEGORY_CODE)
);

-- 예약 테이블 (ORDER_HISTORY 및 다른 테이블에서 참조하므로 ORDER_HISTORY보다 앞에 정의)
CREATE TABLE RESERVATION (
    RESERVATION_NO NUMBER PRIMARY KEY, -- 예약 번호
    USERS_ID VARCHAR2(20) NOT NULL, -- 사용자 ID
    RESERVATION_DATE DATE DEFAULT SYSDATE NOT NULL, -- 예약 날짜
    RESERVATION_STATUS VARCHAR2(1) CHECK (RESERVATION_STATUS IN ('y', 'n')) NOT NULL, -- 예약 상태
    FOREIGN KEY (USERS_ID) REFERENCES USERS(USERS_ID)
);
-- 주문 내역 시퀀스
DROP SEQUENCE ORDERHISTORY_SEQ;
CREATE SEQUENCE ORDERHISTORY_SEQ START WITH 1 INCREMENT BY 1;

-- 주문 내역 테이블
CREATE TABLE ORDER_HISTORY (
    ORDER_NO NUMBER PRIMARY KEY, -- 주문 번호
    RESERVATION_NO NUMBER NOT NULL, -- 예약 번호
    ORDER_DATE DATE DEFAULT SYSDATE NOT NULL, -- 주문 날짜
    FOREIGN KEY (RESERVATION_NO) REFERENCES RESERVATION(RESERVATION_NO)
);
-- 주문 세부 내역 테이블
CREATE TABLE ORDER_DETAILS (
    ORDER_NO NUMBER, -- 주문 번호
    MENU_NO NUMBER, -- 메뉴 번호
    FOREIGN KEY (ORDER_NO) REFERENCES ORDER_HISTORY(ORDER_NO),
    FOREIGN KEY (MENU_NO) REFERENCES MENU(MENU_NO)
);

-- 테이블 예약 정보 테이블
CREATE TABLE TABLE_RESERVATION (
    RESERVATION_NO NUMBER PRIMARY KEY, -- 예약 번호
    TABLE_NO VARCHAR2(5) NOT NULL, -- 테이블 번호
    FOREIGN KEY (RESERVATION_NO) REFERENCES RESERVATION(RESERVATION_NO)
);

CREATE SEQUENCE LUNCHBOX_RESERVATION_SEQ START WITH 1 INCREMENT BY 1;

DROP SEQUENCE LUNCHBOX_RESERVATION_SEQ;

CREATE TABLE LUNCHBOX_RESERVATION (
    RESERVATION_NO NUMBER PRIMARY KEY, -- 예약 ID
    AMOUNT NUMBER NOT NULL,            -- 수량
    LUNCHBOX_DETAILS VARCHAR2(5) CHECK(LUNCHBOX_DETAILS IN('3첩', '5첩', '7첩')), -- 도시락 세부 정보
    FOREIGN KEY (RESERVATION_NO) REFERENCES RESERVATION(RESERVATION_NO)
);

-- 결제 정보 테이블
CREATE TABLE PAYMENT (
    PAYMENT_NO NUMBER PRIMARY KEY, -- 결제 번호
    RESERVATION_NO NUMBER, -- 예약 번호
    TOTAL_PRICE NUMBER, -- 총 결제 금액
    PAYMENT_DATE DATE, -- 결제 날짜
    PAYMENT_METHOD VARCHAR2(20), -- 결제 방법
    FOREIGN KEY (RESERVATION_NO) REFERENCES RESERVATION(RESERVATION_NO)
);

-- 게시물 테이블
CREATE TABLE POST (
    POST_NO NUMBER PRIMARY KEY, -- 게시물 번호
    POST_COUNT NUMBER NOT NULL, -- 조회수
    TITLE VARCHAR2(300) NOT NULL, -- 제목
    CONTENT VARCHAR2(600) NOT NULL, -- 내용
    CREATION_DATE DATE NOT NULL, -- 생성 날짜
    MODIFICATION_DATE DATE, -- 수정 날짜
    DELETE_STATE VARCHAR2(1) CHECK (DELETE_STATE IN ('y', 'n')) NOT NULL, -- 삭제 상태
    USERS_ID VARCHAR2(20) NOT NULL, -- 사용자 ID
    CATEGORY_CODE NUMBER, -- 카테고리 코드
    FOREIGN KEY (USERS_ID) REFERENCES USERS(USERS_ID)
);
CREATE TABLE POST_CATEGORY(
    POST_CATEGORY_CODE VARCHAR2(2) PRIMARY KEY,
    POST_CATEGORY_NAME VARCHAR2(30),
    FOREIGN KEY (CATEGORY_CODE) REFERENCES POST(CATEGORY_CODE)
);
-- 제안 응답 테이블
CREATE TABLE SUGGESTION_RESPONSE (
    RESPONSE_ID NUMBER PRIMARY KEY, -- 응답 ID
    POST_NO NUMBER, -- 게시물 번호
    RESPONSE_CONTENT CLOB, -- 응답 내용
    RESPONSE_DATE DATE, -- 응답 날짜
    FOREIGN KEY (POST_NO) REFERENCES POST(POST_NO)
);
-------------------------------------직책 코드-----------------------------------
-- 직책 코드 테이블
CREATE TABLE JOB_CODE (
    JOB_CODE VARCHAR2(9) PRIMARY KEY, -- 직책 코드
    JOB_NAME VARCHAR2(15) NOT NULL -- 직책 이름
);
INSERT INTO JOB_CODE (JOB_CODE, JOB_NAME)VALUES(
'J01', '사장'
);

INSERT INTO JOB_CODE (JOB_CODE, JOB_NAME)VALUES(
'J02', '부장'
);

INSERT INTO JOB_CODE (JOB_CODE, JOB_NAME)VALUES(
'c01', '주방장'
);

INSERT INTO JOB_CODE (JOB_CODE, JOB_NAME)VALUES(
'c02', '부주방장'
);

INSERT INTO JOB_CODE (JOB_CODE, JOB_NAME)VALUES(
'g01', '가드'
);
--------------------------------------------------------------------------------

-- 부서 테이블
CREATE TABLE DEPT (
    DEPT_CODE VARCHAR2(9) PRIMARY KEY, -- 부서 코드
    DEPT_NAME VARCHAR2(15) NOT NULL -- 부서 이름
);

INSERT INTO DEPT (DEPT_CODE, DEPT_NAME)VALUES(
'D01', '경영'
);

INSERT INTO JOB_CODE (JOB_CODE, JOB_NAME)VALUES(
'D02', '관리'
);

CREATE TABLE EMPLOYEE (
    USERS_ID VARCHAR2(20) PRIMARY KEY, -- 직원 ID
    DEPT_CODE VARCHAR2(9), -- 부서 코드
    JOB_CODE VARCHAR2(9), -- 직책 코드
    SALARY NUMBER,
    EMPLOYEE_TYPE VARCHAR2(9) CHECK(EMPLOYEE_TYPE IN('관리자','직원')),
    FOREIGN KEY (DEPT_CODE) REFERENCES DEPT(DEPT_CODE),
    FOREIGN KEY (JOB_CODE) REFERENCES JOB_CODE(JOB_CODE)
);

-- 추천 메뉴 테이블
CREATE TABLE SUGGEST_MENU (
    MENU_NO NUMBER PRIMARY KEY, -- 메뉴 번호
    SUGGEST_STATUS VARCHAR2(1) CHECK (SUGGEST_STATUS IN ('y', 'n')) NOT NULL, -- 추천 상태
    FOREIGN KEY (MENU_NO) REFERENCES MENU(MENU_NO)
);

-- 시퀀스 생성
---------------------------- 예약 테이블 번호 시퀀스 -------------------------------
DROP SEQUENCE RESERVATION_SEQ;
CREATE SEQUENCE RESERVATION_SEQ START WITH 1 INCREMENT BY 1;

-- 예약 데이터 삽입
INSERT INTO USERS VALUES(
    'whdrns456', 
    'damgarak123!',
    '최종군',
    'whdrns456@naver.com',
    SYSDATE, 
    NULL, 
    'n', 
    '직원'
);

INSERT INTO RESERVATION (RESERVATION_NO, USERS_ID, RESERVATION_DATE, RESERVATION_STATUS)
VALUES (RESERVATION_SEQ.NEXTVAL, 'whdrns456', SYSDATE, 'y');

-- INSERT INTO RESERVATION (RESERVATION_NO, USERS_ID, RESERVATION_DATE, RESERVATION_STATUS) VALUES (RESERVATION_SEQ.NEXTVAL, 'didalsdnr123', SYSDATE, 'y');

INSERT INTO TABLE_RESERVATION (RESERVATION_NO, TABLE_NO)
VALUES (RESERVATION_SEQ.CURRVAL, 'A-1');


-- 메뉴 카테고리 데이터 삽입
INSERT INTO MENU_CATEGORY (CATEGORY_CODE, CATEGORY_NAME) VALUES ('01', '구이류');
INSERT INTO MENU_CATEGORY (CATEGORY_CODE, CATEGORY_NAME) VALUES ('02', '국물류');
INSERT INTO MENU_CATEGORY (CATEGORY_CODE, CATEGORY_NAME) VALUES ('03', '찜류');
INSERT INTO MENU_CATEGORY (CATEGORY_CODE, CATEGORY_NAME) VALUES ('04', '볶음류');
INSERT INTO MENU_CATEGORY (CATEGORY_CODE, CATEGORY_NAME) VALUES ('05', '후식');
INSERT INTO MENU_CATEGORY (CATEGORY_CODE, CATEGORY_NAME) VALUES ('06', '반찬류');
-- 메뉴 데이터 삽입

-- 구이류
INSERT INTO MENU VALUES (1001, '삼겹살 구이', 14000, 'a', 'menu_01_1001.jpg', '노릇노릇하게 구운 한돈 삼겹살', 650, '01', 50);
INSERT INTO MENU VALUES (1002, '고등어 구이', 13000, 'a', 'menu_01_1002.jpg', '방금까지 살아있던 고등어 구이', 300, '01', 5);
INSERT INTO MENU VALUES (1003, '훈제 가슴살', 8000, 'a', 'menu_01_1003.jpg', '다이어터를 위한 훈제 가슴살', 250, '01', 2000);

-- 국물류
INSERT INTO MENU VALUES (2001, '김치찌개', 9000, 'r', 'menu_02_2001.jpg', '김치를 끓는 물에 넣어 고기와 여러 재료들을 넣은 국물요리', 450, '02', 70);
INSERT INTO MENU VALUES (2002, '순두부찌개', 8500, 'l', 'menu_02_2002.jpg', '순두부를 가득 넣고 양념을 한 국물요리', 450, '02', 60);
INSERT INTO MENU VALUES (2003, '된장찌개', 8000, 'l', 'menu_02_2003.jpg', '된장을 넣고 두부를 넣어서 구수한 맛을 낸 국물요리', 300, '02', 50);
INSERT INTO MENU VALUES (2004, '부대찌개', 12000, 'r', 'menu_02_2004.jpg', '여러가지 햄들을 넣고 라면사리를 넣은 국물요리', 600, '02', 80);
INSERT INTO MENU VALUES (2005, '동태찌개', 11000, 'r', 'menu_02_2005.jpg', '동태와 여러가지 야채를 넣어 시원한 맛을 낸 국물요리', 550, '02', 55);
INSERT INTO MENU VALUES (2006, '삼계탕', 12000, 'r', 'menu_02_2006.jpg', '닭을 한 그릇에 넣어 인삼과 같이 요리한 국물요리', 600, '02', 90);
INSERT INTO MENU VALUES (2007, '순대국', 9000, 'l', 'menu_02_2007.jpg', '순대를 넣고 요리한 국물요리', 600, '02', 95);
INSERT INTO MENU VALUES (2008, '육개장', 9000, 'l', 'menu_02_2008.jpg', '소고기와 고사리 등 여러 야채를 넣고 끓인 국물요리', 480, '02', 83);
INSERT INTO MENU VALUES (2009, '갈비탕', 12000, 'l', 'menu_02_2009.jpg', '갈비를 넣고 시원하게 끓인 국물요리', 630, '02', 73);
INSERT INTO MENU VALUES (2010, '매운탕', 11000, 'r', 'menu_02_2010.jpg', '생선과 해물을 넣고 여러 야채를 넣고 맵게 끓인 국물요리', 700, '02', 80);
-- 찜류
INSERT INTO MENU VALUES (3001, '갈비찜', 50000, 'r', 'menu_03_3001.jpg', '맛있는 갈비찜', 2000, '03', 5);
INSERT INTO MENU VALUES (3002, '김치찜', 50000, 'r', 'menu_03_3002.jpg', '맛있는 김치찜', 3000, '03', 2);
INSERT INTO MENU VALUES (3003, '해물찜', 50000, 'r', 'menu_03_3003.jpg', '맛있는 해물찜', 100, '03', 21);

-- 볶음류
INSERT INTO MENU VALUES (4001, '낚지볶음', 21000, 'r', 'menu_04_4001.jpg', '맛있는 낚지볶음', 2, '04', 80);
INSERT INTO MENU VALUES (4002, '소고기 버섯볶음', 22000, 'r', 'menu_04_4002.jpg', '맛있는 소고기 버섯볶음', 60, '04', 60);
INSERT INTO MENU VALUES (4003, '제육볶음', 23000, 'r', 'menu_04_4003.jpg', '맛있는 제육볶음', 900, '04', 1);

-- 후식
INSERT INTO MENU VALUES (5001, '인절미', 3000, 'r', 'menu_05_5001.jpg', '부드러운 인절미와 달콤한 꿀의 조합', 340, '05', 30);
INSERT INTO MENU VALUES (5002, '식혜', 2400, 'l', 'menu_05_5002.jpg', '작게 썰어 말린 대추를 올린 달콤한 식혜', 240, '05', 50);
INSERT INTO MENU VALUES (5003, '곳감수정과', 2500, 'a', 'menu_05_5003.jpg', '잘 말린 곳감을 작게 썰어 함께 즐기는 새콤달콤한 곳감수정과', 200, '05', 30);
INSERT INTO MENU VALUES (5004, '양갱', 2500, 'a', 'menu_05_5004.jpg', '다양한 맛을 즐길 수 있는 전통적인 양갱', 250, '05', 60);
INSERT INTO MENU VALUES (5005, '오메기떡', 3000, 'a', 'menu_05_5005.jpg', '단백하고 다양한 토핑으로 즐길 수 있는 현대적 전통 떡', 150, '05', 20);

-- 반찬류
INSERT INTO MENU VALUES (6001, '한우오이볶음', 4000, 'r', 'menu_06_6001.jpg', '담백한 한우고기와 상큼한 오이의 만남', 140, '06', 80);
INSERT INTO MENU VALUES (6002, '장조림', 3400, 'l', 'menu_06_6002.jpg', '남녀노소 누구나 좋아하는 장조림', 110, '06', 50);
INSERT INTO MENU VALUES (6003, '오징어채', 2500, 'a', 'menu_06_6003.jpg', '매콤하고 부드러운 국민반찬 오징어채', 210, '06', 40);
INSERT INTO MENU VALUES (6004, '계란말이', 2500, 'a', 'menu_06_6004.jpg', '부드럽고 짭짤한 국민 밥반찬 계란말이', 250, '06', 60);
INSERT INTO MENU VALUES (6005, '느타리버섯볶음', 2500, 'a', 'menu_06_6005.jpg', '느타리버섯을 쫄깃하게 볶아낸 감칠맛 반찬', 200, '06', 20);

-- 추천 메뉴 데이터 삽입
INSERT INTO SUGGEST_MENU (MENU_NO, SUGGEST_STATUS) VALUES (1003, 'y');
INSERT INTO SUGGEST_MENU (MENU_NO, SUGGEST_STATUS) VALUES (2008, 'y');
INSERT INTO SUGGEST_MENU (MENU_NO, SUGGEST_STATUS) VALUES (3001, 'y');
INSERT INTO SUGGEST_MENU (MENU_NO, SUGGEST_STATUS) VALUES (4002, 'y');
INSERT INTO SUGGEST_MENU (MENU_NO, SUGGEST_STATUS) VALUES (5005, 'y');

INSERT INTO POST (POST_NO, POST_COUNT, TITLE, CONTENT, CREATION_DATE, MODIFICATION_DATE, DELETE_STATE, USERS_ID, CATEGORY_CODE)
VALUES (1, 100, '나 집갈래', '수고링~', SYSDATE, SYSDATE, 'n', 'whdrns456', 1);

INSERT INTO POST (POST_NO, POST_COUNT, TITLE, CONTENT, CREATION_DATE, MODIFICATION_DATE, DELETE_STATE, USERS_ID, CATEGORY_CODE)
VALUES (2, 50 , '월급좀 올려주세요', '올려줘올려줘올려줘올려줘올려줘올려줘올려줘', SYSDATE, SYSDATE, 'n', 'whdrns456', 1);

INSERT INTO POST (POST_NO, POST_COUNT, TITLE, CONTENT, CREATION_DATE, MODIFICATION_DATE, DELETE_STATE, USERS_ID, CATEGORY_CODE)
VALUES (3, 60 , '재료좀 사주세요', '사달라고사달라고사달라고사달라고사달라고', SYSDATE, SYSDATE, 'n', 'whdrns456', 1);


create table REPLY (
    POST_NO NUMBER NOT NULL,
    REPLY_COMMENT VARCHAR2(300) NULL,
    CREATION_DATE DATE NOT NULL,
    DELETE_STATE VARCHAR2(1) ,
    USERS_ID VARCHAR2(20)
);

INSERT INTO REPLY (POST_NO, REPLY_COMMENT, CREATION_DATE, DELETE_STATE, USERS_ID)
VALUES (1, '그래 알았어 근데 니가 뭘 할 수 있는데', TO_DATE('2024-11-24', 'YYYY-MM-DD'), 'N', 'kimchangsik123');

INSERT INTO REPLY (POST_NO, REPLY_COMMENT, CREATION_DATE, DELETE_STATE, USERS_ID)
VALUES (1, '응 안할거지롱~', TO_DATE('2024-11-08', 'YYYY-MM-DD'), 'N', 'kimchangsik123');

INSERT INTO REPLY (POST_NO, REPLY_COMMENT, CREATION_DATE, DELETE_STATE, USERS_ID)
VALUES (2, '어쩔티비 저쩔티비', TO_DATE('2024-11-09', 'YYYY-MM-DD'), 'N', 'kimchangsik123');

INSERT INTO REPLY (POST_NO, REPLY_COMMENT, CREATION_DATE, DELETE_STATE, USERS_ID)
VALUES (3, '베놈3 스토리 살인자가 칼들고 오는데 춤추고 있음', TO_DATE('2024-11-10', 'YYYY-MM-DD'), 'N', 'kimchangsik123');

CREATE TABLE NOTICE(
    USERS_ID VARCHAR2(20) NOT NULL,
    NOTICE_TITLE VARCHAR2(200) NOT NULL,
    NOTICE_CONTENT VARCHAR2(1000) NOT NULL,
    NOTICE_CREATION_DATE DATE NOT NULL,
    NOTICE_NO NUMBER NOT NULL
);

CREATE SEQUENCE NOTICE_SEQ
START WITH 1
INCREMENT BY 1
NOCACHE;

ALTER TABLE NOTICE
ADD CONSTRAINT fk_user_id
FOREIGN KEY (USERS_ID)
REFERENCES USERS (USERS_ID)
ON DELETE CASCADE;

INSERT INTO NOTICE (USERS_ID, NOTICE_TITLE, NOTICE_CONTENT,NOTICE_NO,NOTICE_CREATION_DATE)
VALUES ('kimchangsik123','내일부터 야근입니다.','필수이니깐 무조건하세요',1,TO_DATE('2024-11-11', 'YYYY-MM-DD'));

INSERT INTO NOTICE (USERS_ID, NOTICE_TITLE, NOTICE_CONTENT,NOTICE_NO,NOTICE_CREATION_DATE)
VALUES ('kimchangsik123','하기싫다','하기싫다 하기싫다 하기싫다 하기싫다 하기싫다 하기싫다',2,TO_DATE('2024-11-11', 'YYYY-MM-DD'));

INSERT INTO NOTICE (USERS_ID, NOTICE_TITLE, NOTICE_CONTENT,NOTICE_NO,NOTICE_CREATION_DATE)
VALUES ('kimchangsik123','집가고 싶다 ','집가고 싶다 집가고 싶다 집가고 싶다 집가고 싶다 집가고 싶다 집가고 싶다 ',3,TO_DATE('2024-11-11', 'YYYY-MM-DD'));

INSERT INTO NOTICE (USERS_ID, NOTICE_TITLE, NOTICE_CONTENT, NOTICE_NO)
VALUES ('imij0126', '안녕', '잘가', NOTICE_SEQ.NEXTVAL);
-- 변경사항 커밋

--------------------------------김인창 insert -----------------------------------
INSERT INTO RESERVATION (RESERVATION_NO,USERS_ID,RESERVATION_DATE,RESERVATION_STATUS)
VALUES (2, 'imij0126',SYSDATE,'y');

INSERT INTO RESERVATION (RESERVATION_NO,USERS_ID,RESERVATION_DATE,RESERVATION_STATUS)
VALUES (3, 'whdrns456',SYSDATE,'y');

INSERT INTO RESERVATION (RESERVATION_NO,USERS_ID,RESERVATION_DATE,RESERVATION_STATUS)
VALUES (4, 'JKW456',SYSDATE,'y');

INSERT INTO LUNCHBOX_RESERVATION (RESERVATION_NO,AMOUNT,LUNCHBOX_DETAILS)
VALUES (2,1,'3첩');

INSERT INTO LUNCHBOX_RESERVATION (RESERVATION_NO,AMOUNT,LUNCHBOX_DETAILS)
VALUES (3,2,'5첩');

INSERT INTO LUNCHBOX_RESERVATION (RESERVATION_NO,AMOUNT,LUNCHBOX_DETAILS)
VALUES (4,3,'7첩');
------------------------------------------------------------------------------------------
INSERT INTO REPLY (POST_NO,REPLY_COMMENT,CREATION_DATE,DELETE_STATE)
VALUES (1,'응 안할거지롱~',SYSDATE,'N');

INSERT INTO REPLY (POST_NO,REPLY_COMMENT,CREATION_DATE,DELETE_STATE)
VALUES (2,'어쩔티비 저쩔티비',SYSDATE,'N');

INSERT INTO REPLY (POST_NO,REPLY_COMMENT,CREATION_DATE,DELETE_STATE)
VALUES (3,'베놈3 스토리 살인자가 칼들고 오는데 춤추고 있음',SYSDATE,'N');

INSERT INTO REPLY (POST_NO,REPLY_COMMENT,CREATION_DATE,DELETE_STATE)
VALUES (4,'뭘봐',SYSDATE,'N');

INSERT INTO REPLY (POST_NO,REPLY_COMMENT,CREATION_DATE,DELETE_STATE)
VALUES (5,'이제 쓰기도 귀찮다....',SYSDATE,'N');
--------------------------------김인창 insert -----------------------------------

COMMIT;

