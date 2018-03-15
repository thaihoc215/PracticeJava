package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NguoiDung implements Serializable{
	
	private static final long serialVersionUID = 1L;
	int maNguoiDung;
	String tenNguoiDung;
	String tenDangNhap;
	String matKhau;
	String gioiTinh;
	Date ngaySinh;
	int loaiNguoiDung;
	int soLanDangNhap;
	private Set<MonHoc> dsMonHoc = new HashSet<MonHoc>();
	
	public Set<MonHoc> getDsMonHoc() {
		return dsMonHoc;
	}
	public void setDsMonHoc(Set<MonHoc> dsMonHoc) {
		this.dsMonHoc = dsMonHoc;
	}
	public int getMaNguoiDung() {
		return maNguoiDung;
	}
	public void setMaNguoiDung(int maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}
	public String getTenNguoiDung() {
		return tenNguoiDung;
	}
	public void setTenNguoiDung(String tenNguoiDung) {
		this.tenNguoiDung = tenNguoiDung;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public int getLoaiNguoiDung() {
		return loaiNguoiDung;
	}
	public void setLoaiNguoiDung(int loaiNguoiDung) {
		this.loaiNguoiDung = loaiNguoiDung;
	}
	
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public int getSoLanDangNhap() {
		return soLanDangNhap;
	}
	public void setSoLanDangNhap(int soLanDangNhap) {
		this.soLanDangNhap = soLanDangNhap;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public NguoiDung() {
		super();
	}
	
	public NguoiDung(int maNguoiDung, String tenNguoiDung, String tenDangNhap, String matKhau, String gioiTinh,
			Date ngaySinh, int loaiNguoiDung, int soLanDangNhap, Set<MonHoc> dsMonHoc) {
		super();
		this.maNguoiDung = maNguoiDung;
		this.tenNguoiDung = tenNguoiDung;
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.loaiNguoiDung = loaiNguoiDung;
		this.soLanDangNhap = soLanDangNhap;
		this.dsMonHoc = dsMonHoc;
	}
	
}
