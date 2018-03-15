package entity;

import java.io.Serializable;

public class KhachHang implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8622009301118768367L;
	private int maKhachHang;
	private String matKhau;
	private String hoVaTen;
	private String email;
	private String dienThoai;
	/**
	 * @return the maKhachHang
	 */
	public int getMaKhachHang() {
		return maKhachHang;
	}
	/**
	 * @param maKhachHang the maKhachHang to set
	 */
	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}
	/**
	 * @return the matKhau
	 */
	public String getMatKhau() {
		return matKhau;
	}
	/**
	 * @param matKhau the matKhau to set
	 */
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	/**
	 * @return the hoVaTen
	 */
	public String getHoVaTen() {
		return hoVaTen;
	}
	/**
	 * @param hoVaTen the hoVaTen to set
	 */
	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the dienThoai
	 */
	public String getDienThoai() {
		return dienThoai;
	}
	/**
	 * @param dienThoai the dienThoai to set
	 */
	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}
}
