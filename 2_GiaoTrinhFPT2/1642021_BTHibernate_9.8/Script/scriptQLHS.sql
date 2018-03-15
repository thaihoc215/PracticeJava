/*Drop database DiemDanhDB;*/
Create database DiemDanhDB;
use DiemDanhDB;

Create Table MonHoc(
	MaMonHoc integer auto_increment primary key,
    TenMonHoc nvarchar(50),
    NgayBatDau date,
    NgayKetThuc date,
    ThuTrongTuan int,
    GioBatDau time,
    GioKetThuc time,
    TenPhong nvarchar(50)
);

Create Table NguoiDung(
	MaNguoiDung integer auto_increment primary key,
    TenNguoiDung nvarchar(50),
    TenDangNhap varchar(30),
    MatKhau varchar(100),
    GioiTinh nvarchar(3),
    NgaySinh date,
    LoaiNguoiDung integer not null,
    SoLanDangNhap integer not null,
    UNIQUE (TenDangNhap)
);

Create Table SinhVienMonHoc(
	MaMonHoc integer not null,
    MaSinhVien integer not null,
    Week1 bool,
    Week2 bool,
    Week3 bool,
    Week4 bool,
    Week5 bool,
    Week6 bool,
    Week7 bool,
    Week8 bool,
    Week9 bool,
    Week10 bool,
    Week11 bool,
    Week12 bool,
    Week13 bool,
    Week14 bool,
    Week15 bool
);

Create Table LoaiNguoiDung (
	MaLoai int primary key,
    TenLoai nvarchar(50)
);

ALTER TABLE SinhVienMonHoc ADD CONSTRAINT fk_monhoc FOREIGN KEY(MaMonHoc) REFERENCES MonHoc(MaMonHoc);
ALTER TABLE SinhVienMonHoc ADD CONSTRAINT fk_sinhvienmonhoc FOREIGN KEY(MaSinhVien) REFERENCES NguoiDung(MaNguoiDung);
ALTER table NguoiDung Add constraint fk_loainguoidunggv foreign key(LoaiNguoiDung) references LoaiNguoiDung(MaLoai);

insert into LoaiNguoiDung(MaLoai,TenLoai) values ('1',N'Giáo Vụ');
insert into LoaiNguoiDung(MaLoai,TenLoai) values ('2',N'Sinh Viên');

INSERT INTO `diemdanhdb`.`nguoidung` (`MaNguoiDung`, `TenNguoiDung`, `TenDangNhap`, `MatKhau`, `GioiTinh`, `NgaySinh`, `LoaiNguoiDung`, `SoLanDangNhap`) 
VALUES ('1', 'gv_Hoàng Phương', 'gv1', ' ,�b�Y[�K-#Kp', NULL, NULL, '1', '12');
INSERT INTO `diemdanhdb`.`nguoidung` (`MaNguoiDung`, `TenNguoiDung`, `TenDangNhap`, `MatKhau`, `GioiTinh`, `NgaySinh`, `LoaiNguoiDung`, `SoLanDangNhap`) 
VALUES ('2', 'gv_Minh Tú', 'gv2', ' ,�b�Y[�K-#Kp', NULL, NULL, '1', '1');
INSERT INTO `diemdanhdb`.`nguoidung` (`MaNguoiDung`, `TenNguoiDung`, `TenDangNhap`, `MatKhau`, `GioiTinh`, `NgaySinh`, `LoaiNguoiDung`, `SoLanDangNhap`) 
VALUES ('3', 'sv_Thái Học', 'sv1', ' ,�b�Y[�K-#Kp', NULL, NULL, '2', '34');
INSERT INTO `diemdanhdb`.`nguoidung` (`MaNguoiDung`, `TenNguoiDung`, `TenDangNhap`, `MatKhau`, `GioiTinh`, `NgaySinh`, `LoaiNguoiDung`, `SoLanDangNhap`) 
VALUES ('4', 'sv_Xuân Phúc', 'sv2', ' ,�b�Y[�K-#Kp', NULL, NULL, '2', '0');
INSERT INTO `diemdanhdb`.`nguoidung` (`MaNguoiDung`, `TenNguoiDung`, `TenDangNhap`, `MatKhau`, `GioiTinh`, `NgaySinh`, `LoaiNguoiDung`, `SoLanDangNhap`) 
VALUES ('5', 'sv_Thái Hòa', 'sv3', ' ,�b�Y[�K-#Kp', NULL, NULL, '2', '0');


INSERT INTO `diemdanhdb`.`monhoc` (`MaMonHoc`, `TenMonHoc`, `NgayBatDau`, `NgayKetThuc`, `ThuTrongTuan`, `GioBatDau`, `GioKetThuc`, `TenPhong`) VALUES ('1', 'Nhập Môn Lập Trình', '2017-08-16', '2017-08-17', '2', '00:33:57', '00:34:04', 'p1');
INSERT INTO `diemdanhdb`.`monhoc` (`MaMonHoc`, `TenMonHoc`, `NgayBatDau`, `NgayKetThuc`, `ThuTrongTuan`, `GioBatDau`, `GioKetThuc`, `TenPhong`) VALUES ('2', 'Mác - Lê Nin', '2017-08-18', '2017-08-31', '6', '00:33:57', '01:34:04', 'p2');
INSERT INTO `diemdanhdb`.`monhoc` (`MaMonHoc`, `TenMonHoc`, `NgayBatDau`, `NgayKetThuc`, `ThuTrongTuan`, `GioBatDau`, `GioKetThuc`, `TenPhong`) VALUES ('3', 'Toán Rời Rạc', '2017-08-11', '2017-08-18', '6', '13:34:02', '13:34:07', 'p3');
INSERT INTO `diemdanhdb`.`monhoc` (`MaMonHoc`, `TenMonHoc`, `NgayBatDau`, `NgayKetThuc`, `ThuTrongTuan`, `GioBatDau`, `GioKetThuc`, `TenPhong`) VALUES ('5', 'Lý thuyết đồ thị', '2017-08-19', '2017-12-02', '7', '14:58:47', '17:58:47', '');


INSERT INTO `diemdanhdb`.`sinhvienmonhoc` (`MaMonHoc`, `MaSinhVien`, `Week1`, `Week2`, `Week3`, `Week4`, `Week5`, `Week6`, `Week7`, `Week8`, `Week9`, `Week10`, `Week11`, `Week12`, `Week13`, `Week14`, `Week15`) 
VALUES ('2', '4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `diemdanhdb`.`sinhvienmonhoc` (`MaMonHoc`, `MaSinhVien`, `Week1`, `Week2`, `Week3`, `Week4`, `Week5`, `Week6`, `Week7`, `Week8`, `Week9`, `Week10`, `Week11`, `Week12`, `Week13`, `Week14`, `Week15`) 
VALUES ('2', '3', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `diemdanhdb`.`sinhvienmonhoc` (`MaMonHoc`, `MaSinhVien`, `Week1`, `Week2`, `Week3`, `Week4`, `Week5`, `Week6`, `Week7`, `Week8`, `Week9`, `Week10`, `Week11`, `Week12`, `Week13`, `Week14`, `Week15`) 
VALUES ('3', '5', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `diemdanhdb`.`sinhvienmonhoc` (`MaMonHoc`, `MaSinhVien`, `Week1`, `Week2`, `Week3`, `Week4`, `Week5`, `Week6`, `Week7`, `Week8`, `Week9`, `Week10`, `Week11`, `Week12`, `Week13`, `Week14`, `Week15`) 
VALUES ('1', '5', NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `diemdanhdb`.`sinhvienmonhoc` (`MaMonHoc`, `MaSinhVien`, `Week1`, `Week2`, `Week3`, `Week4`, `Week5`, `Week6`, `Week7`, `Week8`, `Week9`, `Week10`, `Week11`, `Week12`, `Week13`, `Week14`, `Week15`) 
VALUES ('2', '5', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `diemdanhdb`.`sinhvienmonhoc` (`MaMonHoc`, `MaSinhVien`, `Week1`, `Week2`, `Week3`, `Week4`, `Week5`, `Week6`, `Week7`, `Week8`, `Week9`, `Week10`, `Week11`, `Week12`, `Week13`, `Week14`, `Week15`) 
VALUES ('1', '3', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `diemdanhdb`.`sinhvienmonhoc` (`MaMonHoc`, `MaSinhVien`, `Week1`, `Week2`, `Week3`, `Week4`, `Week5`, `Week6`, `Week7`, `Week8`, `Week9`, `Week10`, `Week11`, `Week12`, `Week13`, `Week14`, `Week15`) 
VALUES ('1', '4', NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);



