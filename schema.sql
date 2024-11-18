CREATE DATABASE user;
USE user;

CREATE TABLE User(
id integer auto_increment primary key,
uname varchar(100) not null,
email varchar(50) not null unique,
country varchar(50) not null,
passwd varchar(20) not null
);
