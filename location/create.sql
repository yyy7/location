create table t_location(
 id int primary key,
 latitude varchar2(30) not null,
 longitude varchar2(30) not null,
 ctime date default  sysdate
)
create sequence  seq_location
start with  1  -- ��ʼ���
increment  by 1  -- ÿ�β���
nocache;
insert into t_location values(seq_location.nextval,'0','0',sysdate)
select * from t_location;
drop table t_location
create table t_location(
 id int primary key,
 latitude varchar2(30) not null,
 longitude varchar2(30) not null,
 city varchar2(30) not null,
 ctime date default  sysdate
)
insert into t_location values(seq_location.nextval,'0','0','�Ͼ�',sysdate)
