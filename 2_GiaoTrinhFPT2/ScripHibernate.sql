CREATE DATABASE HibernateDemo;
use HibernateDemo;
CREATE TABLE khachhang (
    MaKhachHang int,
    MatKhau nvarchar(255),
    HoVaTen nvarchar(255),
    Email nvarchar(255),
    DienThoai nvarchar(255) 
);

Insert into khachhang values(1,'123','kh1','kh1@gmail.com','123456');
Insert into khachhang values(2,'123','kh2','kh2@gmail.com','123456');