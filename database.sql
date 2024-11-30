create database qlnv;
use qlnv;
create table roles(
	id int primary key auto_increment,
	name varchar(15) not null unique,
	des varchar(30) not null,
	check (name != '' and des != '')
);
create table users(
	id int primary key auto_increment,
	name varchar(30) not null,
	email varchar(30) not null unique,
	password varchar(60) not null,
	address varchar(50) not null,
	phone varchar(10) not null,
	role_id int not null,
	foreign key (role_id) references roles(id),
	check (name != '' and email != '' and password != '' and address != '' and char_length(phone) = 10 and role_id != '')
);
create table projects(
	id int primary key auto_increment,
	name varchar(30) not null unique,
	start_date date not null,
	end_date date not null,
	user_id int not null,
	foreign key (user_id) references users(id),
	check (name != '' and start_date <= end_date and user_id != '')
);