create table DEPARTMENT (
    DEPT_ID bigint not null,
    DEPT_NAME varchar(255) not null,
    DEPT_NO varchar(20) not null,
    LOCATION varchar(255),
    primary key (DEPT_ID),
    unique (DEPT_NO)
);
 
create table EMPLOYEE (
    EMP_ID bigint not null,
    EMP_NAME varchar(50) not null,
    EMP_NO varchar(20) not null,
    HIRE_DATE date not null,
    IMAGE bytea,
    JOB varchar(30) not null,
    SALARY float not null,
    DEPT_ID bigint not null,
    MNG_ID bigint,
    primary key (EMP_ID),
    unique (EMP_NO)
);
 
create table SALARY_GRADE (
    GRADE int not null,
    HIGH_SALARY float not null,
    LOW_SALARY float not null,
    primary key (GRADE)
);
 
create table TIMEKEEPER (
    Timekeeper_Id varchar(36) not null,
    Date_Time timestamp not null,
    In_Out char(1) not null,
    EMP_ID bigint not null,
    primary key (Timekeeper_Id)
);
 
alter table EMPLOYEE
    add constraint FK75C8D6AE269A3C9
    foreign key (DEPT_ID)
    references DEPARTMENT;
 
alter table EMPLOYEE
    add constraint FK75C8D6AE6106A42
    foreign key (EMP_ID)
    references EMPLOYEE;
 
alter table EMPLOYEE
    add constraint FK75C8D6AE13C12F64
    foreign key (MNG_ID)
    references EMPLOYEE;
 
alter table TIMEKEEPER
    add constraint FK744D9BFF6106A42
    foreign key (EMP_ID)
    references EMPLOYEE;
    