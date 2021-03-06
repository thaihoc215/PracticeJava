package etities;
// Generated Mar 17, 2018 1:36:02 AM by Hibernate Tools 5.2.8.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Khachhang generated by hbm2java
 */
@Entity
@Table(name = "khachhang", schema = "dbo", catalog = "HibernateDemo")
public class Khachhang implements java.io.Serializable {

	private int maKhachHang;
	private Bophan bophan;
	private String matKhau;
	private String hoVaTen;
	private String email;
	private String dienThoai;

	public Khachhang() {
	}

	public Khachhang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public Khachhang(int maKhachHang, Bophan bophan, String matKhau, String hoVaTen, String email, String dienThoai) {
		this.maKhachHang = maKhachHang;
		this.bophan = bophan;
		this.matKhau = matKhau;
		this.hoVaTen = hoVaTen;
		this.email = email;
		this.dienThoai = dienThoai;
	}

	@Id

	@Column(name = "MaKhachHang", unique = true, nullable = false)
	public int getMaKhachHang() {
		return this.maKhachHang;
	}

	public void setMaKhachHang(int maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BoPhan")
	public Bophan getBophan() {
		return this.bophan;
	}

	public void setBophan(Bophan bophan) {
		this.bophan = bophan;
	}

	@Column(name = "MatKhau")
	public String getMatKhau() {
		return this.matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Column(name = "HoVaTen")
	public String getHoVaTen() {
		return this.hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	@Column(name = "Email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "DienThoai")
	public String getDienThoai() {
		return this.dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

}
