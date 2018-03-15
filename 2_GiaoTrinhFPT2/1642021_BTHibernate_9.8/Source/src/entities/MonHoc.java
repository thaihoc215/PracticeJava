package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class MonHoc implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int maMonHoc;
	String tenMonHoc;
	Date ngayBatDau;
	Date ngayKetThuc;
	int thuTrongTuan;
	String gioBatDau;
	String gioKetThuc;
	String tenPhongHoc;
	private Set<NguoiDung> dsSinhVien = new HashSet<NguoiDung>();
	
	public Set<NguoiDung> getDsSinhVien() {
		return dsSinhVien;
	}
	public void setDsSinhVien(Set<NguoiDung> dsSinhVien) {
		this.dsSinhVien = dsSinhVien;
	}
	/**
	 * @return the maMonHoc
	 */
	public int getMaMonHoc() {
		return maMonHoc;
	}
	/**
	 * @param maMonHoc the maMonHoc to set
	 */
	public void setMaMonHoc(int maMonHoc) {
		this.maMonHoc = maMonHoc;
	}
	/**
	 * @return the tenMonHoc
	 */
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	/**
	 * @param tenMonHoc the tenMonHoc to set
	 */
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	/**
	 * @return the ngayBatDau
	 */
	public Date getNgayBatDau() {
		return ngayBatDau;
	}
	/**
	 * @param ngayBatDau the ngayBatDau to set
	 */
	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	/**
	 * @return the ngayKetThuc
	 */
	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}
	/**
	 * @param ngayKetThuc the ngayKetThuc to set
	 */
	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	/**
	 * @return the thuTrongTuan
	 */
	public int getThuTrongTuan() {
		return thuTrongTuan;
	}
	/**
	 * @param thuTrongTuan the thuTrongTuan to set
	 */
	public void setThuTrongTuan(int thuTrongTuan) {
		this.thuTrongTuan = thuTrongTuan;
	}
	/**
	 * @return the gioBatDau
	 */
	public String getGioBatDau() {
		return gioBatDau;
	}
	/**
	 * @param gioBatDau the gioBatDau to set
	 */
	public void setGioBatDau(String gioBatDau) {
		this.gioBatDau = gioBatDau;
	}
	/**
	 * @return the gioKetThuc
	 */
	public String getGioKetThuc() {
		return gioKetThuc;
	}
	/**
	 * @param gioKetThuc the gioKetThuc to set
	 */
	public void setGioKetThuc(String gioKetThuc) {
		this.gioKetThuc = gioKetThuc;
	}
	/**
	 * @return the tenPhongHoc
	 */
	public String getTenPhongHoc() {
		return tenPhongHoc;
	}
	/**
	 * @param tenPhongHoc the tenPhongHoc to set
	 */
	public void setTenPhongHoc(String tenPhongHoc) {
		this.tenPhongHoc = tenPhongHoc;
	}
	
	
	
	public MonHoc() {
		super();
	}
	public MonHoc(int maMonHoc, String tenMonHoc, Date ngayBatDau, Date ngayKetThuc, String gioBatDau,
			String gioKetThuc, int thuTrongTuan, String tenPhongHoc) {
		super();
		this.maMonHoc = maMonHoc;
		this.tenMonHoc = tenMonHoc;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.gioBatDau = gioBatDau;
		this.gioKetThuc = gioKetThuc;
		this.thuTrongTuan = thuTrongTuan;
		this.tenPhongHoc = tenPhongHoc;
	}
	
	@Override
    public String toString() {
        return this.tenMonHoc;
    }
}
