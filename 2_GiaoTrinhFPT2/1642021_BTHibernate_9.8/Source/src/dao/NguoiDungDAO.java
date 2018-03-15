package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import Utils.HibernateUtils;
import entities.NguoiDung;

public class NguoiDungDAO {
	public static List<NguoiDung> layDanhSachNguoiDung() {
		List<NguoiDung> lstNguoiDung = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from NguoiDung";
			Query<NguoiDung> query = session.createQuery(hql);
			lstNguoiDung = query.getResultList();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		return lstNguoiDung;
	}
	public static NguoiDung layNguoiDung(String tendangnhap) {
		NguoiDung nd = null;
		Configuration cfg = new Configuration();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from NguoiDung nd"
					+ " Where nd.tenDangNhap = :tendangnhap";
			Query<NguoiDung> query = session.createQuery(hql);
			query.setParameter("tendangnhap", tendangnhap);
			nd = query.getSingleResult();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		return nd;
	}
	
	public static NguoiDung layNguoiDung(int maNguoiDung) {
		NguoiDung nd = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from NguoiDung nd"
					+ " Where nd.maNguoiDung = :manguoidung";
			Query<NguoiDung> query = session.createQuery(hql);
			query.setParameter("manguoidung", maNguoiDung);
			nd = query.getSingleResult();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		return nd;
	}
	
	/**
	 * Lay danh sach sinh vien
	 * @return list nguoi dung la sinh vien
	 */
	public static ArrayList<NguoiDung> layDanhSachSinhVien() {
		List<NguoiDung> lstSinhVien = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from NguoiDung Where loaiNguoiDung = 2";
			Query<NguoiDung> query = session.createQuery(hql);
			lstSinhVien = query.getResultList();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		return (ArrayList<NguoiDung>) lstSinhVien;
	}
	
	/**
	 * Tim kiem sinh vien
	 * @param svSearchText
	 * @return List sinh vien theo dieu kien tim kiem
	 */
	public static ArrayList<NguoiDung> timKiemSinhVien(String svSearchText){
		List<NguoiDung> lstSinhVien = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from NguoiDung Where loaiNguoiDung = 2 And tenNguoiDung Like :searchfield";
			Query<NguoiDung> query = session.createQuery(hql);
			query.setParameter("searchfield", "%"+svSearchText+"%");
			lstSinhVien = query.getResultList();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		return (ArrayList<NguoiDung>) lstSinhVien;
	}
	
	/**
	 * update so lan dang nhap cua nguoi dung
	 * @param maNguoiDung
	 * @return [boolean] ket qua up date 
	 */
	public static boolean updateNguoiDung(NguoiDung nd) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.saveOrUpdate(nd);
			session.getTransaction().commit();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
			return false;
		}
	}
	/**
	 * Them moi mot nguoi dung
	 * @param ndAdd
	 * @return
	 */
	public static boolean themNguoiDung(NguoiDung ndAdd) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.persist(ndAdd);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
			return false;
		}
	}
}
