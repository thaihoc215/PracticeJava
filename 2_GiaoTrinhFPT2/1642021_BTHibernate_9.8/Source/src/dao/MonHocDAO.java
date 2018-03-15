package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Utils.HibernateUtils;
import entities.MonHoc;
import entities.NguoiDung;
import entities.SinhVienMonHoc;

public class MonHocDAO {
	/**
	 * Lay danh sach mon hoc
	 * @return List<MonHoc> danh sach tat ca mon hoc
	 */
	public static ArrayList<MonHoc> layDanhSachMonHoc() {
		List<MonHoc> lstMonHoc = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from MonHoc order by maMonHoc";
			Query<MonHoc> query = session.createQuery(hql);
			lstMonHoc = query.getResultList();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		return (ArrayList<MonHoc>) lstMonHoc;
	}
	
	/**
	 * Lay thong tin chi tiet mon hoc
	 * @param maMH ma mon hoc
	 * @return MonHoc 
	 */
	public static MonHoc layMonHoc(int maMH) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from entities.MonHoc nd Where nd.maMonHoc = :mamonhoc";
			Query<MonHoc> query = session.createQuery(hql);
			query.setParameter("mamonhoc", maMH);
			MonHoc monHoc = query.getSingleResult();
			session.getTransaction().commit();
			return monHoc;

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
			return null;
		}
	}
	
	/**
	 * Them moi mot mon oc
	 * @param monHoc
	 * @return Boolean Ket qua them mon hoc
	 */
	public static boolean themMonHoc(MonHoc monHoc) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			session.persist(monHoc);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
			return false;
		}
		return true;
	}

	public static ArrayList<MonHoc> timKiemMonHoc(String searchField) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		List<MonHoc> lstMonHoc = null;
		try {
			session.getTransaction().begin();
			String hql = "from entities.MonHoc mh Where mh.tenMonHoc like :tenmonhoc";
			Query<MonHoc> query = session.createQuery(hql);
			query.setParameter("tenmonhoc", "%"+searchField+"%");
			lstMonHoc = query.getResultList();
			session.getTransaction().commit();
			return (ArrayList<MonHoc>) lstMonHoc;

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
			return null;
		}
	}
	
	
}
