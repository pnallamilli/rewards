delete from transaction;
delete from customer;

alter table transaction add foreign key (customer_id) references customer (id);


insert into customer values(1,'Adam');
insert into customer values(2,'Bob');
insert into customer values(3,'Charle');
insert into customer values(4,'Danny');
insert into customer values(5,'Edward');
insert into customer values(6,'Finn');
insert into customer values(7,'Grason');
insert into customer values(8,'Henry');



insert into transaction values(1001,1,50,'2022-08-29');
insert into transaction values(1002,1,10,'2022-09-14');
insert into transaction values(1003,1,150,'2022-10-04');
insert into transaction values(1004,1,150,'2022-10-22');
insert into transaction values(1005,1,50,'2022-10-30');

insert into transaction values(1006,2,120,'2022-07-01');
insert into transaction values(1007,2,110,'2022-08-02');
insert into transaction values(1008,2,140,'2022-09-03');
insert into transaction values(1009,2,120,'2022-10-04');
insert into transaction values(1010,2,120,'2022-10-19');

insert into transaction values(1011,3,55,'2022-08-20');

insert into transaction values(1012,4,105,'2022-09-30');

insert into transaction values(1013,5,35,'2022-08-31');

insert into transaction values(1014,6,200,'2010-06-13');

insert into transaction values(1015,7,100,'2022-09-25');

