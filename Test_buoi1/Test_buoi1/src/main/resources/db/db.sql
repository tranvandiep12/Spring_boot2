create database student_management_n0325c1;
use student_management_n0325c1;

create table student
(
    id    int primary key auto_increment,
    name  varchar(50),
    score double
);

insert into student values('1','Lê Hữu Thi',8.8);
insert into student values('2','Phạm Hồng Sơn',7.5);
insert into student values('3','Nguyễn Văn A',9.2);
insert into student values('4','Phạm Thị B',8);