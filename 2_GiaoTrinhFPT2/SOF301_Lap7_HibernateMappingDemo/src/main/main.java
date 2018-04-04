/**
 * 
 */
package main;

import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.BophanDAO;
import dao.KhachHangDAO;
import etities.Bophan;
import etities.Khachhang;
import utils.HibernateUtil;

/**
 * @author hochnt
 *
 */
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// lazy initialization and fetch
		// getHocSinhLazyAndFetch();
		// Cascade update-save
		 cascadeUpdateSave();
		// one to many
//		oneManyLayThongTinBoPhan(1);
		
//		inverseThemBoPhanKhachHang();
	}

	private static void getHocSinhLazyAndFetch() {
		Khachhang kh = null;
		SessionFactory ssFac = HibernateUtil.getSessionFactory();
		Session ss = ssFac.openSession();
		ss.getTransaction().begin();
		try {
			kh = (Khachhang) ss.get(Khachhang.class, 1);
			System.out.println("Tên Khách Hàng: " + kh.getHoVaTen());
			System.out.println("Mã bộ phận: " + kh.getBophan().getMaBoPhan());
			System.out.println("Tên bộ phận: " + kh.getBophan().getTenBoPhan());
			// String hql="select kh from Khachhang kh left join fetch kh.bophan"
			// + " where kh.maKhachHang=:maKhachHang";
			// Query query=ss.createQuery(hql);
			// query.setInteger("maKhachHang", 1);
			// kh = (Khachhang) query.uniqueResult();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			ss.close();
		}

		// trường hợp phát sinh lỗi do cơ chế lazy Initialization
		// System.out.println("Tên Khách Hàng: " + kh.getHoVaTen());
		// System.out.println("Mã bộ phận: " + kh.getBophan().getMaBoPhan());
		// System.out.println("Tên bộ phận: " + kh.getBophan().getTenBoPhan());
	}

	private static void cascadeUpdateSave() {
		Khachhang kh = new Khachhang(5);
		kh.setHoVaTen("kh5");
		kh.setEmail("kh5@gmail.com");
		kh.setDienThoai("12345");
		kh.setMatKhau("123");

		Bophan bp = new Bophan(3);
		bp.setTenBoPhan("cascade");

		kh.setBophan(bp);
		if (KhachHangDAO.themKhachHang(kh)) {
			System.out.println("Thêm thành công!");
		} else
			System.out.println("Thêm thất bại!");
	}

	private static void oneManyLayThongTinBoPhan(int maBoPhan) {
		Bophan bp = BophanDAO.layThongTinBoPhan(maBoPhan);
		System.out.println("Mã bộ phận: " + bp.getMaBoPhan());
		System.out.println("Tên lớp: " + bp.getTenBoPhan());
		System.out.println("--------------------------------");

		Iterator<Khachhang> dsKH = bp.getKhachhangs().iterator();
		while (dsKH.hasNext()) {
			Khachhang kh = dsKH.next();
			System.out.println("Tên Khách Hàng: " + kh.getHoVaTen());
			System.out.println("Mã bộ phận: " + kh.getBophan().getMaBoPhan());
			System.out.println("Tên bộ phận: " + kh.getBophan().getTenBoPhan());
		}
	}
	
	private static void inverseThemBoPhanKhachHang() {
		
		Khachhang kh = new Khachhang(5);
		kh.setHoVaTen("kh5");
		kh.setEmail("kh5@gmail.com");
		kh.setDienThoai("12345");
		kh.setMatKhau("123");

		Bophan bp = new Bophan(3);
		bp.setTenBoPhan("cascade");
		
//		kh.setBophan(bp);
		bp.getKhachhangs().add(kh);
		
		BophanDAO.themBoPhanKhachHang(bp, kh);
		
	}

}
