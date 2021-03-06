package etities;
// Generated Mar 17, 2018 1:36:02 AM by Hibernate Tools 5.2.8.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Bophan generated by hbm2java
 */
@Entity
@Table(name = "bophan", schema = "dbo", catalog = "HibernateDemo")
public class Bophan implements java.io.Serializable {

	private int maBoPhan;
	private String tenBoPhan;
	private Set<Khachhang> khachhangs = new HashSet<Khachhang>(0);

	public Bophan() {
	}

	public Bophan(int maBoPhan) {
		this.maBoPhan = maBoPhan;
	}

	public Bophan(int maBoPhan, String tenBoPhan, Set<Khachhang> khachhangs) {
		this.maBoPhan = maBoPhan;
		this.tenBoPhan = tenBoPhan;
		this.khachhangs = khachhangs;
	}

	@Id

	@Column(name = "MaBoPhan", unique = true, nullable = false)
	public int getMaBoPhan() {
		return this.maBoPhan;
	}

	public void setMaBoPhan(int maBoPhan) {
		this.maBoPhan = maBoPhan;
	}

	@Column(name = "TenBoPhan")
	public String getTenBoPhan() {
		return this.tenBoPhan;
	}

	public void setTenBoPhan(String tenBoPhan) {
		this.tenBoPhan = tenBoPhan;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bophan")
	public Set<Khachhang> getKhachhangs() {
		return this.khachhangs;
	}

	public void setKhachhangs(Set<Khachhang> khachhangs) {
		this.khachhangs = khachhangs;
	}

}
