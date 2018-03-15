package entities;

import java.io.Serializable;

public class IDSinhVienMonHoc implements Serializable{
	private int maMonHoc;

	private int maSinhVien;
	
	public int getMaMonHoc() {
		return maMonHoc;
	}
	public void setMaMonHoc(int maMonHoc) {
		this.maMonHoc = maMonHoc;
	}
	public int getMaSinhVien() {
		return maSinhVien;
	}
	public void setMaSinhVien(int maSinhVien) {
		this.maSinhVien = maSinhVien;
	}
}
