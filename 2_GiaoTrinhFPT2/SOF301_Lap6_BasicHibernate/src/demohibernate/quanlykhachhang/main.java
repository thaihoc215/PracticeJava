package demohibernate.quanlykhachhang;

import java.util.List;

import dao.KhachHangDAO;
import entity.KhachHang;

public class main {

	public static void main(String[] args) {
		List<KhachHang> dsKhachHangs = KhachHangDAO.layDanhSachKhachHang();
		for (KhachHang khachHang : dsKhachHangs) {
			System.out.println(khachHang);
		}

	}

}
