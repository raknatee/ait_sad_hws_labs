insert into role (name) values ('ROLE_ADMIN');
insert into role (name) values ('ROLE_USER');


insert into user (username,password) values ('admin','$2a$10$9F1A56mRbpYdxYFGoNVTc.gDK/GRaSHmtIEVHfv.ClZ3Ch7z4XvSK');
insert into USER_ROLES (USERS_ID,ROLES_ID) values (1,1);


insert into user (username,password) values ('user','$2a$10$9F1A56mRbpYdxYFGoNVTc.gDK/GRaSHmtIEVHfv.ClZ3Ch7z4XvSK');
insert into USER_ROLES (USERS_ID,ROLES_ID) values (2,2);

insert into employee (USER_ID,BASE_SALARY, BIRTHDAY,LEVEL,NAME)  values (2,2000,'2017-06-15','1','Employee1');

insert into address (city,street,house_No,zipcode) values ('my city','my street','123','12120');
insert into EMPLOYEE_ADDRESSES (EMPLOYEES_USER_ID,ADDRESSES_ID) values (2,1);
