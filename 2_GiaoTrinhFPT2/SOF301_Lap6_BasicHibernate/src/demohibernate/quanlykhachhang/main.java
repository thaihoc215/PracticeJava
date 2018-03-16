package demohibernate.quanlykhachhang;

import java.util.List;

import dao.KhachHangDAO;
import entity.KhachHang;

public class main {

	public static void main(String[] args) {
		 List<KhachHang> dsKhachHangs = KhachHangDAO.layDanhSachKhachHang();
		 if (dsKhachHangs == null)
		 System.out.println("danh sach rong");
		 else
		 for (KhachHang khachHang : dsKhachHangs) {
		 System.out.println("Họ tên: " + khachHang.getHoVaTen() +
		 " - Email: " + khachHang.getEmail() + " - SDT: " + khachHang.getDienThoai());
		 }
		
		// KhachHang kh = KhachHangDAO.layThongTinKhachHang(1);
		// System.out.println("Lấy thông tin 1 khách hàng");
		// System.out.println("Họ tên: " + kh.getHoVaTen() +
		// " - Email: " + kh.getEmail() + " - SDT: " + kh.getDienThoai());

		// KhachHang kh = new KhachHang(5, "123123", "Trần Trọng Vĩnh", "ttv@gmail.com",
		// "097654321");
		// if (KhachHangDAO.themKhachHang(kh) == true)
		// System.out.println("Thêm thành công!");
		// else
		// System.out.println("Thêm không thành công!");

		// KhachHang kh = new KhachHang(5, "111111", "Lê Hồng Phong", "ttv@gmail.com",
		// "097654321");
		// if (KhachHangDAO.capnhatKhachHang(kh) == true) {
		// System.out.println("Cập nhật thành công!");
		// } else {
		// System.out.println("Cập nhật thất bại!");
		// }

		// if (KhachHangDAO.xoaKhachHang(5) == true) {
		// System.out.println("Xóa thành công!");
		// } else {
		// System.out.println("Xóa thất bại!");
		// }

	}

}
