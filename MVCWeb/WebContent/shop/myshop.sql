상위 카테고리

drop table upCategory;

create table upCategory(
  upCode number(5) not null, -- 상위 카테고리 코드
  upCgName varchar2(30) not null, -- 상위 카테고리 명
  constraint upCode_pk primary key (upCode)
);

drop sequence upCode_seq;

create sequence upCode_seq nocache;

--하위 카테고리

drop table downCategory;

create table downCategory(
  upCode number(5) not null, -- 상위 카테고리 (FK)
  downCode number(5) not null, -- 하위 카테고리 코드 (PK)
  downCgName varchar2(30) not null, -- 하위 카테고리 명
  constraint downCode_pk primary key (downCode),
  constraint upCode_fk foreign key (upCode)
    references upCategory (upCode)
);

drop sequence downCode_seq;

create sequence downCode_seq nocache;

insert into upCategory 
values(upCode_seq.nextval,'컴퓨터.디지털.가전');

insert into upCategory 
values(upCode_seq.nextval,'식품.생필품');

insert into upCategory 
values(upCode_seq.nextval,'패션의류');

commit;

select * from upCategory;

insert into downCategory
values (1,downCode_seq.nextval,'노트북.PC');

insert into downCategory
values (1,downCode_seq.nextval,'핸드폰');

insert into downCategory
values (1,downCode_seq.nextval,'대형가전');

insert into downCategory
values (2,downCode_seq.nextval,'세제');

insert into downCategory
values (2,downCode_seq.nextval,'화장지');

commit;

select * from downCategory;

상품 테이블
drop table product;

create table product(
  pnum number(8) not null, -- 상품 번호
  upCode number(5) not null, -- 상위 카테고리 코드
  downCode number(5) not null, -- 하위 카테고리 코드
  pname varchar2(50) not null, -- 상품명
  pimage1 varchar2(50) default 'noimage.png', -- 상품이미지1
  pimage2 varchar2(50) default 'noimage.png', -- 상품이미지2
  pimage3 varchar2(50) default 'noimage.png', -- 상품이미지3
  price number(8) default 0, -- 상품 정가
  saleprice number(8) default 0, -- 상품 판매가
  pqty number(5) default 0, -- 상품 수량
  point number(8) default 0, --지급 포인트
  pspec varchar2(20), -- 스펙(NEW,BEST,HIT)
  pcontents varchar2(1000), -- 상품설명
  pcompany varchar2(50), -- 제조사
  pindate date default sysdate, -- 상품 등록일
  constraint pnum_pk primary key (pnum), -- 상품 번호 (PK)
  constraint upCode_fk2 foreign key (upCode) -- 상위 카테고리 코드 (FK)
    references upCategory (upCode),
  constraint downCode_fk foreign key (downCode) -- 하위 카테고리 코드 (FK)
    references downCategory (downCode)
);

drop sequence pnum_seq;

create sequence pnum_seq nocache;

select * from product;

--상품평 리뷰 게시판 테이블
create table review(
    ridx number(8) not null,
    title varchar2(100) not null,
    content varchar2(2000),
    score number(2) default 0,
    filename varchar2(100) default 'noimage.JPG',
    indate Date default sysdate,
    midx_fk number(8) not null,
    pnum_fk number(8) not null,
    constraint review_score_ck check (score>=0 and score <=5),
    constraint review_midx_fk foreign key (midx_fk)
    references member (idx),
    constraint review_pnum_fk foreign key (pnum_fk)
    references product (pnum)
);

create sequence review_seq
start with 1
increment by 1
nocache;