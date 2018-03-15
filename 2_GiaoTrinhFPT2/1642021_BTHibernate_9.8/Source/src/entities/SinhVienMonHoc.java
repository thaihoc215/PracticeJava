package entities;

import java.io.Serializable;

public class SinhVienMonHoc implements Serializable{

	private static final long serialVersionUID = 1L; 
//	int maMonHoc;
//	int maSinhVien;
	private IDSinhVienMonHoc maSinhVienMonHoc;
	String tenSinhVien;
	String tenMonHoc;
	Boolean week1;
	Boolean week2;
	Boolean week3;
	Boolean week4;
	Boolean week5;
	Boolean week6;
	Boolean week7;
	Boolean week8;
	Boolean week9;
	Boolean week10;
	Boolean week11;
	Boolean week12;
	Boolean week13;
	Boolean week14;
	Boolean week15;
//	public int getMaMonHoc() {
//		return maMonHoc;
//	}
//	public void setMaMonHoc(int maMonHoc) {
//		this.maMonHoc = maMonHoc;
//	}
//	public int getMaSinhVien() {
//		return maSinhVien;
//	}
//	public void setMaSinhVien(int maSinhVien) {
//		this.maSinhVien = maSinhVien;
//	}
	public IDSinhVienMonHoc getMaSinhVienMonHoc() {
		return maSinhVienMonHoc;
	}
	public void setMaSinhVienMonHoc(IDSinhVienMonHoc maSinhVienMonHoc) {
		this.maSinhVienMonHoc = maSinhVienMonHoc;
	}
	public String getTenSinhVien() {
		return tenSinhVien;
	}
	public void setTenSinhVien(String tenSinhVien) {
		this.tenSinhVien = tenSinhVien;
	}
	
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public Boolean isWeek1() {
		return week1;
	}
	public void setWeek1(Boolean week1) {
		this.week1 = week1;
	}
	public Boolean isWeek2() {
		return week2;
	}
	public void setWeek2(Boolean week2) {
		this.week2 = week2;
	}
	public Boolean isWeek3() {
		return week3;
	}
	public void setWeek3(Boolean week3) {
		this.week3 = week3;
	}
	public Boolean isWeek4() {
		return week4;
	}
	public void setWeek4(Boolean week4) {
		this.week4 = week4;
	}
	public Boolean isWeek5() {
		return week5;
	}
	public void setWeek5(Boolean week5) {
		this.week5 = week5;
	}
	public Boolean isWeek6() {
		return week6;
	}
	public void setWeek6(Boolean week6) {
		this.week6 = week6;
	}
	public Boolean isWeek7() {
		return week7;
	}
	public void setWeek7(Boolean week7) {
		this.week7 = week7;
	}
	public Boolean isWeek8() {
		return week8;
	}
	public void setWeek8(Boolean week8) {
		this.week8 = week8;
	}
	public Boolean isWeek9() {
		return week9;
	}
	public void setWeek9(Boolean week9) {
		this.week9 = week9;
	}
	public Boolean isWeek10() {
		return week10;
	}
	public void setWeek10(Boolean week10) {
		this.week10 = week10;
	}
	public Boolean isWeek11() {
		return week11;
	}
	public void setWeek11(Boolean week11) {
		this.week11 = week11;
	}
	public Boolean isWeek12() {
		return week12;
	}
	public void setWeek12(Boolean week12) {
		this.week12 = week12;
	}
	public Boolean isWeek13() {
		return week13;
	}
	public void setWeek13(Boolean week13) {
		this.week13 = week13;
	}
	public Boolean isWeek14() {
		return week14;
	}
	public void setWeek14(Boolean week14) {
		this.week14 = week14;
	}
	public Boolean isWeek15() {
		return week15;
	}
	public void setWeek15(Boolean week15) {
		this.week15 = week15;
	}
	public SinhVienMonHoc() {
		super();
	}
	public SinhVienMonHoc(IDSinhVienMonHoc idSVMH, String tenSinhVien, String tenMonHoc, Boolean week1, Boolean week2, Boolean week3,
			Boolean week4, Boolean week5, Boolean week6, Boolean week7, Boolean week8, Boolean week9, Boolean week10,
			Boolean week11, Boolean week12, Boolean week13, Boolean week14, Boolean week15) {
		super();
//		this.maMonHoc = maMonHoc;
//		this.maSinhVien = maSinhVien;
		this.maSinhVienMonHoc = idSVMH;
		this.tenSinhVien = tenSinhVien;
		this.tenMonHoc = tenMonHoc;
		this.week1 = week1;
		this.week2 = week2;
		this.week3 = week3;
		this.week4 = week4;
		this.week5 = week5;
		this.week6 = week6;
		this.week7 = week7;
		this.week8 = week8;
		this.week9 = week9;
		this.week10 = week10;
		this.week11 = week11;
		this.week12 = week12;
		this.week13 = week13;
		this.week14 = week14;
		this.week15 = week15;
	}
	
	
}
