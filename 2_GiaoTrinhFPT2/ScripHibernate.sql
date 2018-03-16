CREATE DATABASE HibernateDemo;
Go
use HibernateDemo;
Go
CREATE TABLE khachhang (
    MaKhachHang int primary key,
    MatKhau nvarchar(255),
    HoVaTen nvarchar(255),
    Email nvarchar(255),
    DienThoai nvarchar(255),
	BoPhan int
);
Create Table bophan(
	MaBoPhan int primary key,
	TenBoPhan nvarchar(255)
);

alter table khachhang add constraint fk_kh_bp foreign key (BoPhan) references bophan(MaBoPhan);

Insert into bophan values(1,'binh thuong');
Insert into bophan values(2,'tiem nang');

Insert into khachhang values(1,'123','kh1','kh1@gmail.com','123456',1);
Insert into khachhang values(2,'123','kh2','kh2@gmail.com','123456',1);
Insert into khachhang values(3,'123','kh3','kh3@gmail.com','123456',2);
Insert into khachhang values(4,'123','kh4','kh4@gmail.com','123456',2);

--drop database HibernateDemo;