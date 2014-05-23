/* board101.sql */

create table city(
no int primary key,
cityName varchar2(20)
);

create sequence city_no_seq 
increment by 1 start with 1 nocache;

select * from city
"서울","부산","인천","대구","광주","울산","경기","강원",
				       "충북","충남","전북","전남","제주","경북","경남","세종","대전"
				       
--insert into city(no, cityName) values(city_no_seq.nextval,'서울')
insert into city(no, cityName) values(city_no_seq.nextval,'부산');
insert into city(no, cityName) values(city_no_seq.nextval,'인천');
insert into city(no, cityName) values(city_no_seq.nextval,'대구');
insert into city(no, cityName) values(city_no_seq.nextval,'광주');
insert into city(no, cityName) values(city_no_seq.nextval,'울산');
insert into city(no, cityName) values(city_no_seq.nextval,'경기');
insert into city(no, cityName) values(city_no_seq.nextval,'강원');
insert into city(no, cityName) values(city_no_seq.nextval,'충북');
insert into city(no, cityName) values(city_no_seq.nextval,'충남');
insert into city(no, cityName) values(city_no_seq.nextval,'전북');
insert into city(no, cityName) values(city_no_seq.nextval,'전남');
insert into city(no, cityName) values(city_no_seq.nextval,'제주');
insert into city(no, cityName) values(city_no_seq.nextval,'경북');
insert into city(no, cityName) values(city_no_seq.nextval,'경남');
insert into city(no, cityName) values(city_no_seq.nextval,'세종');
insert into city(no, cityName) values(city_no_seq.nextval,'대전');

drop table board101;

create table board101(
	no int primary key,
	name varchar2(20) not null,
	addr varchar2(200) not null,
	pass varchar2(100) not null,
	gender varchar2(10) not null,
	city varchar2(30) not null,
	cont varchar2(3000) not null,
	regdate date
);

create sequence board101_no_seq
increment by 1 start with 1 nocache;

select * from board101 order by no desc;

select * from board101 where no =5;