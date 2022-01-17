------- User Table
CREATE TABLE "TB_USER" (
                           "USER_NO" NUMBER(12) NOT NULL,
                           "EMAIL" VARCHAR(100) NOT NULL,
                           "PASSWD" VARCHAR(100) NOT NULL,
                           "USER_NAME" VARCHAR(100) ,
                           "NICK_NAME" VARCHAR(100) ,
                           "TEL_NUMBER" VARCHAR(20) DEFAULT '',
                           "ROLES" VARCHAR(16) NOT NULL,
                           "REG_DATE" DATE NOT NULL,
                           "UPT_DATE" DATE NOT NULL,
                           "PASSWDRESET_DATE" DATE NOT NULL,
                           PRIMARY KEY ("USER_NO")
);

ALTER TABLE "TB_USER" ADD CONSTRAINT UK_TB_USER_EMAIL UNIQUE("EMAIL") ;
ALTER TABLE "TB_USER" ADD CONSTRAINT UK_TB_USER_NICK_NAME UNIQUE("NICK_NAME") ;

COMMENT ON TABLE "TB_USER" IS '회원정보';
COMMENT ON COLUMN "TB_USER"."USER_NO" IS '회원번호';
COMMENT ON COLUMN "TB_USER"."EMAIL" IS '이메일';
COMMENT ON COLUMN "TB_USER"."PASSWD" IS '비밀번호';
COMMENT ON COLUMN "TB_USER"."USER_NAME" IS '회원이름';
COMMENT ON COLUMN "TB_USER"."NICK_NAME" IS '닉네임';
COMMENT ON COLUMN "TB_USER"."TEL_NUMBER" IS '전화번호';
COMMENT ON COLUMN "TB_USER"."ROLES" IS '사용자 권한';
COMMENT ON COLUMN "TB_USER"."REG_DATE" IS '등록일시';
COMMENT ON COLUMN "TB_USER"."UPT_DATE" IS '수정일시';
COMMENT ON COLUMN "TB_USER"."PASSWDRESET_DATE" IS '마지막 비밀번호 수정일시';

CREATE SEQUENCE "TB_USER_SEQ"  MINVALUE 1 MAXVALUE 999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;


------- 게시판 Table
CREATE TABLE "TB_BOARD_POST" (
                            "POST_NO" NUMBER(12) NOT NULL,
                            "TITLE" VARCHAR(100) NOT NULL,
                            "CONTENT" VARCHAR(2000) NOT NULL,
                            "WRITER" VARCHAR(1000) NOT NULL,
                            "USER_NO" NUMBER(12) NOT NULL,
                            "VIEW_CNT" NUMBER(10) default 0,
                            "REG_DATE" DATE NOT NULL,
                            "UPT_DATE" DATE NOT NULL,
                            PRIMARY KEY ("POST_NO")
);

COMMENT ON TABLE "TB_BOARD_POST" IS '게시판';
COMMENT ON COLUMN "TB_BOARD_POST"."POST_NO" IS '게시물 번호';
COMMENT ON COLUMN "TB_BOARD_POST"."TITLE" IS '제목';
COMMENT ON COLUMN "TB_BOARD_POST"."CONTENT" IS '내용';
COMMENT ON COLUMN "TB_BOARD_POST"."WRITER" IS '글쓴이정보';
COMMENT ON COLUMN "TB_BOARD_POST"."USER_NO" IS '글쓴이 번호';
COMMENT ON COLUMN "TB_BOARD_POST"."VIEW_CNT" IS '조회수';
COMMENT ON COLUMN "TB_BOARD_POST"."REG_DATE" IS '등록일시';
COMMENT ON COLUMN "TB_BOARD_POST"."UPT_DATE" IS '수정일시';

CREATE SEQUENCE "TB_BOARD_POST_SEQ"  MINVALUE 1 MAXVALUE 999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;















select TB_USER_SEQ.NEXTVAL from DUAL;

SELECT * FROM USER_SEQUENCES;


insert into TB_USER ( USER_NO ,  EMAIL ,  PASSWD ,  USER_NAME, NICK_NAME,  TEL_NUMBER ,  ROLES ,  REG_DATE ,  UPT_DATE ,  PASSWDRESET_DATE ) values (
                                                                                                                                                        TB_USER_SEQ.NEXTVAL,
                                                                                                                                                        'powerkkim4@gmail.com',
                                                                                                                                                        '1234',
                                                                                                                                                        'AAAA',
                                                                                                                                                        '0101111111',
                                                                                                                                                        'BBBBB',
                                                                                                                                                        SYSDATE,
                                                                                                                                                        SYSDATE,
                                                                                                                                                        SYSDATE
                                                                                                                                                    );