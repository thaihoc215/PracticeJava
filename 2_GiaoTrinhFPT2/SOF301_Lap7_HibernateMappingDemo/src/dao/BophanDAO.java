package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import etities.Bophan;
import etities.Khachhang;
import utils.HibernateUtil;

public class BophanDAO {

	public static Bophan layThongTinBoPhan(int maBoPhan) {
		Bophan bp = null;
		Session ss = HibernateUtil.getSessionFactory().getCurrentSession();
		ss.beginTransaction();
		// Transaction trans = ss.getTransaction();
		// trans.begin();
		try {
			bp = ss.get(Bophan.class, maBoPhan);
			// trans.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
//			ss.close();
		}
		return bp;
	}
	
	public static void themBoPhanKhachHang(Bophan bp, Khachhang kh) {
		Session ss = HibernateUtil.getSessionFactory().getCurrentSession();
		ss.beginTransaction();
		
		try {
			ss.save(bp);
			ss.save(kh);
			ss.getTransaction().commit();
		} catch (Exception e) {
			ss.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			ss.close();
		}
	}
}
