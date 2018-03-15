package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import entity.KhachHang;
import util.HibernateUtil;

public class KhachHangDAO {
	public static List<KhachHang> layDanhSachKhachHang() {
		List<KhachHang> dsKhachHang = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String hql = "from KhachHang";
			Query query = session.createQuery(hql);
			dsKhachHang = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsKhachHang;
	}
}
