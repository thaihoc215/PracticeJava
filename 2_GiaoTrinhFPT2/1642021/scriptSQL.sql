CREATE DATABASE QuanLyHocSinh
GO

CREATE TABLE HocSinh(
	MaHS int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TenHS nvarchar(50) NULL,
	NgaySinh datetime NULL,
	GhiChu nvarchar(100) NULL,
	ExtInfo [image] NULL
	)