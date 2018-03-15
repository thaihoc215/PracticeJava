package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Utils.HibernateUtils;
import entities.MonHoc;
import entities.NguoiDung;
import entities.SinhVienMonHoc;

public class SinhVienMonHocDAO {
	
	/**
	 * Lay danh sach sinh vien ung voi mon hoc
	 * @param maMH
	 * @return
	 */
	public static ArrayList<SinhVienMonHoc> layDSSVMHByMon(int maMH) {
		List<SinhVienMonHoc> lstSVMH = new ArrayList<SinhVienMonHoc>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from entities.MonHoc nd Where nd.maMonHoc = :mamonhoc";
			Query<MonHoc> query = session.createQuery(hql);
			query.setParameter("mamonhoc", maMH);
			MonHoc monHoc = query.getSingleResult();
			Iterator<NguoiDung> dsSinhVien = monHoc.getDsSinhVien().iterator();
			
			
			while (dsSinhVien.hasNext()) {
				NguoiDung sv = dsSinhVien.next();
				String hql2 = "from entities.SinhVienMonHoc svmh Where svmh.maSinhVienMonHoc.maMonHoc = :mamonhoc "
						+ "and svmh.maSinhVienMonHoc.maSinhVien = :masinhvien";
				Query<SinhVienMonHoc> query2 = session.createQuery(hql2);
				query2.setParameter("mamonhoc", monHoc.getMaMonHoc());
				query2.setParameter("masinhvien", sv.getMaNguoiDung());
				SinhVienMonHoc svmh = query2.getSingleResult();
				svmh.setTenSinhVien(sv.getTenNguoiDung());
				lstSVMH.add(svmh);
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		
		if(lstSVMH.size() == 0) return null;
		lstSVMH.sort(new Comparator<SinhVienMonHoc>() {
			@Override
			public int compare(SinhVienMonHoc o1, SinhVienMonHoc o2) {
				return o1.getMaSinhVienMonHoc().getMaSinhVien() - o2.getMaSinhVienMonHoc().getMaSinhVien() ;
			}
		});
		return (ArrayList<SinhVienMonHoc>)lstSVMH;
	}
	
	/**
	 * Lay danh sach nguoi dung la sinh vien khong co trong danh sach theo mon hoc
	 * @param mamh
	 * @return
	 */
	public static ArrayList<NguoiDung> laySinhVienNotInMon(int mamh){
		List<NguoiDung> lstSvien = null;
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from NguoiDung Where loaiNguoiDung = 2 "
					+ " and maNguoiDung not in (select maSinhVienMonHoc.maSinhVien "
					+ " from SinhVienMonHoc where maSinhVienMonHoc.maMonHoc = :mamh)"
					+ " order by maNguoiDung";
			Query<NguoiDung> query = session.createQuery(hql);
			query.setParameter("mamh", mamh);
			lstSvien = query.getResultList();
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		return (ArrayList<NguoiDung>) lstSvien;
	}
	
	/**
	 * Tim kiem sinh vien chua them vao mon hoc trong danh sach sinh vien
	 * @param maMonHoc
	 * @param text
	 * @return
	 */
	public static ArrayList<NguoiDung> timKiemSinhVienNotInMon(int mamh, String svSearchText) {
		
			List<NguoiDung> lstSinhVien = null;
			Session session = HibernateUtils.getSessionFactory().getCurrentSession();
			try {
				session.getTransaction().begin();
				String hql = "from NguoiDung Where loaiNguoiDung = 2 "
						+ " and tenNguoiDung Like :searchfield"
						+ " and maNguoiDung not in (select maSinhVienMonHoc.maSinhVien "
						+ " from SinhVienMonHoc where maSinhVienMonHoc.maMonHoc = :mamh)"
						+ " order by maNguoiDung";
				Query<NguoiDung> query = session.createQuery(hql);
				query.setParameter("searchfield", "%"+svSearchText+"%");
				query.setParameter("mamh", mamh);
				
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
	 * Them sinh vien vao mon hoc
	 * @param maMH
	 * @param maSV
	 * @return
	 */
	public static boolean themSinhVienMonHoc(MonHoc mhoc, NguoiDung svien){
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			svien.getDsMonHoc().add(mhoc);
			session.merge(svien);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
			return false;
		}
		return true;
	}
	
	/**
	 * Xoa sinh vien khoi danh sach sinh vien trong mon hoc
	 * @param mhoc
	 * @param svien
	 * @return
	 */
	public static boolean xoaSinhVienMonHoc(MonHoc mhoc, NguoiDung svien) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
//			MonHoc mh = 
			for (MonHoc mh : svien.getDsMonHoc()) {
				if(mh.getMaMonHoc() == mhoc.getMaMonHoc()){
					svien.getDsMonHoc().remove(mh);
					break;
				}
			}
			session.saveOrUpdate(svien);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
			return false;
		}
		return true;
	}
	
	/**
	 * Update thong tin diem danh cua sinh vien boi giao vu
	 * @param lstSVUpdate
	 * @return
	 */
	public static boolean updateDiemDanh(ArrayList<SinhVienMonHoc> lstSVUpdate) {
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
			
			for (SinhVienMonHoc svmh : lstSVUpdate) {
				session.saveOrUpdate(svmh);
			}
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
	 * 
	 * @param maSV
	 * @return
	 */
	public static ArrayList<SinhVienMonHoc> layDSMHBySV(int maSV){
		List<SinhVienMonHoc> lstSVMH = new ArrayList<SinhVienMonHoc>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
//			String hql = "from entities.MonHoc nd Where nd.maMonHoc = :mamonhoc";
			String hql = "from entities.NguoiDung nd Where nd.maNguoiDung = :masinhvien";
			Query<NguoiDung> query = session.createQuery(hql);
			query.setParameter("masinhvien", maSV);
			NguoiDung svien = query.getSingleResult();
			Iterator<MonHoc> dsMonHoc = svien.getDsMonHoc().iterator();
			while (dsMonHoc.hasNext()) {
				MonHoc mhoc = dsMonHoc.next();
				String hql2 = "from entities.SinhVienMonHoc svmh Where svmh.maSinhVienMonHoc.maMonHoc = :mamonhoc "
						+ "and svmh.maSinhVienMonHoc.maSinhVien = :masinhvien";
				Query<SinhVienMonHoc> query2 = session.createQuery(hql2);
				query2.setParameter("mamonhoc", mhoc.getMaMonHoc());
				query2.setParameter("masinhvien", svien.getMaNguoiDung());
				SinhVienMonHoc svmh = query2.getSingleResult();
				svmh.setTenSinhVien(svien.getTenNguoiDung());
				svmh.setTenMonHoc(mhoc.getTenMonHoc());
				lstSVMH.add(svmh);
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		if(lstSVMH.size() == 0)
			return null;
		
		lstSVMH.sort(new Comparator<SinhVienMonHoc>() {
			@Override
			public int compare(SinhVienMonHoc o1, SinhVienMonHoc o2) {
				return o1.getMaSinhVienMonHoc().getMaMonHoc() - o2.getMaSinhVienMonHoc().getMaMonHoc() ;
			}
		});
		return (ArrayList<SinhVienMonHoc>)lstSVMH;
	}

	public static ArrayList<SinhVienMonHoc> timKiemMonHocBySV(int maSV, String tenmhTimKiem) {
		List<SinhVienMonHoc> lstSVMH = new ArrayList<SinhVienMonHoc>();
		Session session = HibernateUtils.getSessionFactory().getCurrentSession();
		try {
			session.getTransaction().begin();
//			String hql = "from entities.MonHoc nd Where nd.maMonHoc = :mamonhoc";
			String hql = "from entities.NguoiDung nd Where nd.maNguoiDung = :masinhvien";
			Query<NguoiDung> query = session.createQuery(hql);
			query.setParameter("masinhvien", maSV);
			NguoiDung svien = query.getSingleResult();
			Iterator<MonHoc> dsMonHoc = svien.getDsMonHoc().iterator();
			while (dsMonHoc.hasNext()) {
				MonHoc mhoc = dsMonHoc.next();
				if(!mhoc.getTenMonHoc().contains(tenmhTimKiem))
					continue;
				String hql2 = "from entities.SinhVienMonHoc svmh Where svmh.maSinhVienMonHoc.maMonHoc = :mamonhoc "
						+ "and svmh.maSinhVienMonHoc.maSinhVien = :masinhvien";
				Query<SinhVienMonHoc> query2 = session.createQuery(hql2);
				query2.setParameter("mamonhoc", mhoc.getMaMonHoc());
				query2.setParameter("masinhvien", svien.getMaNguoiDung());
				SinhVienMonHoc svmh = query2.getSingleResult();
				svmh.setTenSinhVien(svien.getTenNguoiDung());
				svmh.setTenMonHoc(mhoc.getTenMonHoc());
				lstSVMH.add(svmh);
			}
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback trong trường hợp có lỗi xẩy ra.
			session.getTransaction().rollback();
		}
		if(lstSVMH.size() == 0)
			return null;
		
		lstSVMH.sort(new Comparator<SinhVienMonHoc>() {
			@Override
			public int compare(SinhVienMonHoc o1, SinhVienMonHoc o2) {
				return o1.getMaSinhVienMonHoc().getMaMonHoc() - o2.getMaSinhVienMonHoc().getMaMonHoc() ;
			}
		});
		return (ArrayList<SinhVienMonHoc>)lstSVMH;
	}
	
}
