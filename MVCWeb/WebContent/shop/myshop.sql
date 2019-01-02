���� ī�װ�

drop table upCategory;

create table upCategory(
  upCode number(5) not null, -- ���� ī�װ� �ڵ�
  upCgName varchar2(30) not null, -- ���� ī�װ� ��
  constraint upCode_pk primary key (upCode)
);

drop sequence upCode_seq;

create sequence upCode_seq nocache;

--���� ī�װ�

drop table downCategory;

create table downCategory(
  upCode number(5) not null, -- ���� ī�װ� (FK)
  downCode number(5) not null, -- ���� ī�װ� �ڵ� (PK)
  downCgName varchar2(30) not null, -- ���� ī�װ� ��
  constraint downCode_pk primary key (downCode),
  constraint upCode_fk foreign key (upCode)
    references upCategory (upCode)
);

drop sequence downCode_seq;

create sequence downCode_seq nocache;

insert into upCategory 
values(upCode_seq.nextval,'��ǻ��.������.����');

insert into upCategory 
values(upCode_seq.nextval,'��ǰ.����ǰ');

insert into upCategory 
values(upCode_seq.nextval,'�м��Ƿ�');

commit;

select * from upCategory;

insert into downCategory
values (1,downCode_seq.nextval,'��Ʈ��.PC');

insert into downCategory
values (1,downCode_seq.nextval,'�ڵ���');

insert into downCategory
values (1,downCode_seq.nextval,'��������');

insert into downCategory
values (2,downCode_seq.nextval,'����');

insert into downCategory
values (2,downCode_seq.nextval,'ȭ����');

commit;

select * from downCategory;

��ǰ ���̺�
drop table product;

create table product(
  pnum number(8) not null, -- ��ǰ ��ȣ
  upCode number(5) not null, -- ���� ī�װ� �ڵ�
  downCode number(5) not null, -- ���� ī�װ� �ڵ�
  pname varchar2(50) not null, -- ��ǰ��
  pimage1 varchar2(50) default 'noimage.png', -- ��ǰ�̹���1
  pimage2 varchar2(50) default 'noimage.png', -- ��ǰ�̹���2
  pimage3 varchar2(50) default 'noimage.png', -- ��ǰ�̹���3
  price number(8) default 0, -- ��ǰ ����
  saleprice number(8) default 0, -- ��ǰ �ǸŰ�
  pqty number(5) default 0, -- ��ǰ ����
  point number(8) default 0, --���� ����Ʈ
  pspec varchar2(20), -- ����(NEW,BEST,HIT)
  pcontents varchar2(1000), -- ��ǰ����
  pcompany varchar2(50), -- ������
  pindate date default sysdate, -- ��ǰ �����
  constraint pnum_pk primary key (pnum), -- ��ǰ ��ȣ (PK)
  constraint upCode_fk2 foreign key (upCode) -- ���� ī�װ� �ڵ� (FK)
    references upCategory (upCode),
  constraint downCode_fk foreign key (downCode) -- ���� ī�װ� �ڵ� (FK)
    references downCategory (downCode)
);

drop sequence pnum_seq;

create sequence pnum_seq nocache;

select * from product;

--��ǰ�� ���� �Խ��� ���̺�
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