insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_GOLD');
insert into role (name) values ('ROLE_SILVER');
insert into role (name) values ('ROLE_FREE');

insert into user (username,password,email,active) values ('admin','$2a$10$9F1A56mRbpYdxYFGoNVTc.gDK/GRaSHmtIEVHfv.ClZ3Ch7z4XvSK','admin@test.com',true);
insert into USER_ROLES (USERS_ID,ROLES_ID) values (1,1);

insert into user (username,password,email,active) values ('userg','$2a$10$staELzqjT4rjo3Ee8uEe2.b9yKZ71OEmKk8S3ORJtMimlrM3bkPCa','userg@test.com',true);
insert into USER_ROLES (USERS_ID,ROLES_ID) values (2,2);


insert into user (username,password,email,active) values ('users','$2a$10$staELzqjT4rjo3Ee8uEe2.b9yKZ71OEmKk8S3ORJtMimlrM3bkPCa','users@test.com',true);
insert into USER_ROLES (USERS_ID,ROLES_ID) values (3,3);

insert into user (username,password,email,active) values ('userf','$2a$10$staELzqjT4rjo3Ee8uEe2.b9yKZ71OEmKk8S3ORJtMimlrM3bkPCa','userf@test.com',true);
insert into USER_ROLES (USERS_ID,ROLES_ID) values (4,4);

insert into course (name,disciplines,type,date_offered,revenue_generated) values ('S001','SCIENCE','PAID','2017-06-15',20);
insert into course (name,disciplines,type,date_offered,revenue_generated) values ('S002','ENGINEERING','FREE','2017-06-15',20);
insert into course (name,disciplines,type,date_offered,revenue_generated) values ('S003','BUSINESS','PAID','2017-06-15',20);

insert into USER_ENROLLED_COURSE (USERS_ID,ENROLLED_COURSE_ID) values (2,3)