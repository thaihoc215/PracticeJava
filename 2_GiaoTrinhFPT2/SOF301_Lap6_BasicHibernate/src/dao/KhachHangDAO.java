package dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import entity.KhachHang;
import util.HibernateUtil;

public class KhachHangDAO {
	/**
	 * Hibernate list
	 * @return a list from document <List>
	 */
	public static List<KhachHang> layDanhSachKhachHang() {
		List<KhachHang> dsKhachHang = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String hql = "from KhachHang";
			Query query = session.createQuery(hql);
			dsKhachHang = query.getResultList();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsKhachHang;
	}

	/**
	 * Get one record Hibernate
	 * @param maKhachHang
	 * @return
	 */
	public static KhachHang layThongTinKhachHang(int maKhachHang) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		KhachHang kh = session.get(KhachHang.class, maKhachHang);
		session.close();

		return kh;
	}

	/**
	 * Insert record hibernate
	 * @param kh
	 * @return
	 */
	public static boolean themKhachHang(KhachHang kh) {
		if (layThongTinKhachHang(kh.getMaKhachHang()) != null)
			return false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(kh);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	/**
	 * update record hibernate
	 * @param kh
	 * @return
	 */
	public static boolean capnhatKhachHang(KhachHang kh) {
		if (layThongTinKhachHang(kh.getMaKhachHang()) == null)
			return false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(kh);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
	
	/**
	 * Remove record hibernate
	 * @param makh
	 * @return
	 */
	public static boolean xoaKhachHang(int makh) {
		KhachHang kh = layThongTinKhachHang(makh);
		if (kh == null)
			return false;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(kh);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}
}
