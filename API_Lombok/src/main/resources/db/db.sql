create database student_management_n0325c1;

use student_management_n0325c1;

create table student (
     id int primary key auto_increment,
     name varchar(50),
     score double
);

insert into student (id, name, score) values (1, 'Thịnh', 9.6);
insert into student (id, name, score) values (2, 'Điệp', 9.5);
insert into student (id, name, score) values (3, 'Bảo', 9.7);

