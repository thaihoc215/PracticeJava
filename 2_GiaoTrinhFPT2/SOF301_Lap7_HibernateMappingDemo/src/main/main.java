/**
 * 
 */
package main;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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
		// TODO Auto-generated method stub
		Khachhang kh = null;
		SessionFactory ssFac = HibernateUtil.getSessionFactory();
		Session ss = ssFac.openSession();
		ss.getTransaction().begin();
		try {
//			kh = (Khachhang) ss.get(Khachhang.class, 1);
//			System.out.println("Tên Khách Hàng: " + kh.getHoVaTen());
//			System.out.println("Mã bộ phận: " + kh.getBophan().getMaBoPhan());
//			System.out.println("Tên bộ phận: " + kh.getBophan().getTenBoPhan());
			String hql="select kh from Khachhang kh left join fetch kh.bophan"
					+ " where kh.maKhachHang=:maKhachHang";
					Query query=ss.createQuery(hql);
					query.setInteger("maKhachHang", 1);
					kh = (Khachhang) query.uniqueResult();
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ss.close();
		}
		
		//trường hợp phát sinh lỗi do cơ chế lazy Initialization
		System.out.println("Tên Khách Hàng: " + kh.getHoVaTen());
		System.out.println("Mã bộ phận: " + kh.getBophan().getMaBoPhan());
		System.out.println("Tên bộ phận: " + kh.getBophan().getTenBoPhan());
	}

}
