-- Create table
create table USERCONNECTION
(
USERID         VARCHAR(255) not null,
PROVIDERID     VARCHAR(255) not null,
PROVIDERUSERID VARCHAR(255) not null,
RANK           INTEGER not null,
DISPLAYNAME    VARCHAR(255),
PROFILEURL     VARCHAR(512),
IMAGEURL       VARCHAR(512),
ACCESSTOKEN    VARCHAR(255) not null,
SECRET         VARCHAR(255),
REFRESHTOKEN   VARCHAR(255),
EXPIRETIME     BIGINT
) ;
 
-- Create/Recreate primary, unique and foreign key constraints
alter table USERCONNECTION
add primary key (USERID, PROVIDERID, PROVIDERUSERID) ;
 
-- Create/Recreate indexes
create unique index USERCONNECTIONRANK on USERCONNECTION (USERID, PROVIDERID, RANK) ;
 
-----------------------------------------
-- Create table
create table USER_ACCOUNTS
(
ID         VARCHAR(255) not null,
EMAIL      VARCHAR(100) not null,
USER_NAME      VARCHAR(100) not null,
FIRST_NAME VARCHAR(100) not null,
LAST_NAME  VARCHAR(100) not null,
PASSWORD   VARCHAR(255),
ROLE       VARCHAR(20) not null,
ENABLED    VARCHAR(1) default 'Y' not null
) ;
-- Create/Recreate primary, unique and foreign key constraints
alter table USER_ACCOUNTS
add primary key (ID) ;
alter table USER_ACCOUNTS
 add constraint User_Account_UK1 unique (EMAIL);
alter table USER_ACCOUNTS
 add constraint User_Account_UK2 unique (USER_NAME);