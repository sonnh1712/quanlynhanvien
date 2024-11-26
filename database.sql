create database qlnv;
use qlnv;
create table roles(
	id int primary key auto_increment,
	name varchar(15) not null,
	des varchar(30) not null,
	check (name != '' and des != '')
);