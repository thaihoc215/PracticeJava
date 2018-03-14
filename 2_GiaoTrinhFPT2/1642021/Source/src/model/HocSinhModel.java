package model;

import java.util.Date;

public class HocSinhModel {
	
	int _maHS;
	String _tenHS;
	Date _ngaySinh;
	String _ghiChu;
	byte[] _images;
	
	public byte[] get_images() {
		return _images;
	}


	public void set_images(byte[] _images) {
		this._images = _images;
	}


	public int get_maHS() {
		return _maHS;
	}


	public void set_maHS(int _maHS) {
		this._maHS = _maHS;
	}


	public String get_tenHS() {
		return _tenHS;
	}


	public void set_tenHS(String _tenHS) {
		this._tenHS = _tenHS;
	}


	public Date get_ngaySinh() {
		return _ngaySinh;
	}


	public void set_ngaySinh(Date _ngaySinh) {
		this._ngaySinh = _ngaySinh;
	}


	public String get_ghiChu() {
		return _ghiChu;
	}


	public void set_ghiChu(String _ghiChu) {
		this._ghiChu = _ghiChu;
	}


	public HocSinhModel(){
		
	}

	public HocSinhModel(int _maHS, String _tenHS, Date _ngaySinh, String _ghiChu, byte[] _images) {
		this._maHS = _maHS;
		this._tenHS = _tenHS;
		this._ngaySinh = _ngaySinh;
		this._ghiChu = _ghiChu;
		this._images = _images;
	}
}
