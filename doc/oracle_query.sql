------- User Table
CREATE TABLE "TB_USER" (
                           "USER_NO" NUMBER(12) NOT NULL,
                           "EMAIL" VARCHAR(100) NOT NULL,
                           "PASSWD" VARCHAR(100) NOT NULL,
                           "USER_NAME" VARCHAR(100) ,
                           "NICK_NAME" VARCHAR(100) ,
                           "PROFILE_IMG" VARCHAR(100) ,
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
COMMENT ON COLUMN "TB_USER"."PROFILE_IMG" IS '프로필 이미지';
COMMENT ON COLUMN "TB_USER"."TEL_NUMBER" IS '전화번호';
COMMENT ON COLUMN "TB_USER"."ROLES" IS '사용자 권한';
COMMENT ON COLUMN "TB_USER"."REG_DATE" IS '등록일시';
COMMENT ON COLUMN "TB_USER"."UPT_DATE" IS '수정일시';
COMMENT ON COLUMN "TB_USER"."PASSWDRESET_DATE" IS '마지막 비밀번호 수정일시';

CREATE SEQUENCE "TB_USER_SEQ"  MINVALUE 1 MAXVALUE 999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;

-- REMEMBER ME
create table persistent_logins (username varchar(64) not null,
                                series varchar(64) primary key,
                                token varchar(64) not null,
                                last_used timestamp not null);




------- 게시판 Table
CREATE TABLE "TB_BOARD_POST" (
                            "POST_NO" NUMBER(12) NOT NULL,
                            "TITLE" VARCHAR(100) NOT NULL,
                            "CONTENT" VARCHAR(2000) NOT NULL,
                            "WRITER" VARCHAR(1000) NULL,
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


--------- PowerNote 게시판 Table
CREATE TABLE "TB_POWEWRNOTEBOARD_POST" (
                            "POST_NO" NUMBER(12) NOT NULL,
--                             "BOARD_NO" NUMBER(4) default 0,
--                             "CATEGORY_CD" VARCHAR(4) default '0000',
                            "CATEGORY_NAME" VARCHAR(20) default '기본',
                            "TITLE" VARCHAR(100) NOT NULL,
                            "CONTENT" VARCHAR(2000) NOT NULL,
--                             "WRITER" VARCHAR(1000) NOT NULL,
                            "USER_NO" NUMBER(12) NOT NULL,
                            "VIEW_CNT" NUMBER(10) default 0,
                            "REG_DATE" DATE NOT NULL,
                            "UPT_DATE" DATE NOT NULL,
                            PRIMARY KEY ("POST_NO")
);

COMMENT ON TABLE "TB_POWEWRNOTEBOARD_POST" IS '게시판';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_POST"."POST_NO" IS '게시물 번호';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_POST"."BOARD_NO" IS '게시판 번호';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_POST"."CATEGORY_NAME" IS '카테고리 번호';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_POST"."TITLE" IS '제목';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_POST"."CONTENT" IS '내용';
-- COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_POST"."WRITER" IS '글쓴이정보';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_POST"."USER_NO" IS '글쓴이 번호';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_POST"."VIEW_CNT" IS '조회수';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_POST"."REG_DATE" IS '등록일시';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_POST"."UPT_DATE" IS '수정일시';


CREATE SEQUENCE "TB_POWEWRNOTEBOARD_POST_SEQ"  MINVALUE 1 MAXVALUE 999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;


--------- PowerNote 게시판 Table
CREATE TABLE "TB_POWEWRNOTEBOARD_COMMENT" (
                                           "COMMENT_NO" NUMBER(12) NOT NULL,
                                           "POST_NO" NUMBER(12) NOT NULL,
                                           "COMMENT_GNO" NUMBER(12) NOT NULL,
                                           "COMMENT_GORD" NUMBER(12) NOT NULL,
                                           "COMMENT_DEPTH" NUMBER(12) NOT NULL,
                                           "PAR_USER_NO" NUMBER(12) DEFAULT 0 NOT NULL,
--                                            "PAR_USER_WRITER" VARCHAR(1000) DEFAULT '' NOT NULL,
                                           "USER_NO" NUMBER(12) NOT NULL,
--                                            "WRITER" VARCHAR(1000) NOT NULL,
                                           "CONTENT" VARCHAR(2000) NOT NULL,
                                           "REG_DATE" DATE DEFAULT SYSDATE NOT NULL,
                                           "UPT_DATE" DATE NOT NULL,
                                           PRIMARY KEY ("COMMENT_NO")
);

COMMENT ON TABLE "TB_POWEWRNOTEBOARD_COMMENT" IS '게시판';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."COMMENT_NO" IS '댓글 번호';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."POST_NO" IS '게시글 번호';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."COMMENT_GNO" IS '댓글 그룹 번호 참조 번호';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."COMMENT_GORD" IS '댓글 그룹 정렬';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."COMMENT_DEPTH" IS '댓글 깊이 단계';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."PAR_USER_NO" IS '댓글 상대 유저 번호';
-- COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."PAR_USER_WRITER" IS '댓글 상대 유저 이름';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."USER_NO" IS '유저 번호';
-- COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."WRITER" IS '글쓴이';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."CONTENT" IS '댓글 내용';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."REG_DATE" IS '수정일시';
COMMENT ON COLUMN "TB_POWEWRNOTEBOARD_COMMENT"."UPT_DATE" IS '수정일시';

CREATE SEQUENCE "TB_POWEWRNOTEBOARD_COMMENT_SEQ"  MINVALUE 1 MAXVALUE 999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;


------- User Table
CREATE TABLE "TB_SOCIAL_CONNECT" (
                           "SOCIAL_NO" NUMBER(12) NOT NULL,
                           "USER_NO" NUMBER(12) NOT NULL,
                           "SOCIAL_TYPE" VARCHAR(100) NOT NULL,
                           "OAUTH_ID" VARCHAR(100) NOT NULL,
                           "EMAIL" VARCHAR(100) NOT NULL,
                           "USER_NAME" VARCHAR(100) ,
                           "NICK_NAME" VARCHAR(100) ,
                           "PROFILE_IMG" VARCHAR(100) ,
                           "TEL_NUMBER" VARCHAR(20) DEFAULT '',
                           "REG_DATE" DATE NOT NULL,
                           "UPT_DATE" DATE NOT NULL,
                           PRIMARY KEY ("SOCIAL_NO")
);

ALTER TABLE "TB_SOCIAL_CONNECT" ADD CONSTRAINT UK_TB_SOCIAL_CONNECT_EMAIL UNIQUE("SOCIAL_TYPE", "EMAIL") ;
ALTER TABLE "TB_SOCIAL_CONNECT" ADD CONSTRAINT UK_TB_SOCIAL_CONNECT_OAUTH_ID UNIQUE("OAUTH_ID") ;

COMMENT ON TABLE "TB_SOCIAL_CONNECT" IS 'Social 정보';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."SOCIAL_NO" IS 'Social 연동 No';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."USER_NO" IS 'Social 연동 User No';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."SOCIAL_TYPE" IS 'Social Type 구글, 네이버, 페이스북';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."OAUTH_ID" IS 'Oauth id';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."EMAIL" IS '이메일';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."USER_NAME" IS '회원이름';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."NICK_NAME" IS '닉네임';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."PROFILE_IMG" IS '프로필 이미지';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."TEL_NUMBER" IS '전화번호';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."REG_DATE" IS '등록일시';
COMMENT ON COLUMN "TB_SOCIAL_CONNECT"."UPT_DATE" IS '수정일시';

CREATE SEQUENCE "TB_SOCIAL_CONNECT_SEQ"  MINVALUE 1 MAXVALUE 999999999999 INCREMENT BY 1 START WITH 1 CACHE 100 NOORDER  NOCYCLE ;




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
