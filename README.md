# phonebook
phonebook using simple jdbc template using spring

# queries

1) For creating database.....

CREATE DATABASE phonebook
  WITH ENCODING='UTF8'
       OWNER=postgres
       LC_COLLATE='en_US.UTF-8'
       LC_CTYPE='en_US.UTF-8'
       CONNECTION LIMIT=-1
       TABLESPACE=pg_default;
=========================================

2) For creating phonebook table.....

create table phonebook (
id serial primary key not null,
firstName varchar(50) not null,
lastName varchar(50) not null,
email varchar(50) not null unique,
mobile varchar(10) not null unique,
address text not null,
nickName varchar(50) not null,
relation varchar(50) not null,
photo text not null 
);

=========================================