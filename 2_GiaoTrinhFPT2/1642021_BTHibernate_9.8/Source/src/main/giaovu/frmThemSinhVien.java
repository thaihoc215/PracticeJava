package main.giaovu;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import Utils.Commons;
import dao.NguoiDungDAO;
import entities.NguoiDung;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class frmThemSinhVien {

	private JFrame themSinhVienView;
	private JTextField txtTenDangNhap;
	private JTextField txtHoTen;
	private JPasswordField txtMatKhau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmThemSinhVien window = new frmThemSinhVien();
					window.themSinhVienView.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmThemSinhVien() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		themSinhVienView = new JFrame();
		themSinhVienView.setTitle("Thêm Sinh Viên");
		themSinhVienView.setBounds(100, 100, 450, 232);
		themSinhVienView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		themSinhVienView.setLocation((dim.width - themSinhVienView.getSize().width) / 2,
				(dim.height - themSinhVienView.getSize().height) / 2);
		
		JPanel infoPane = new JPanel();
		themSinhVienView.getContentPane().add(infoPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Tên Đăng Nhập");
		
		txtTenDangNhap = new JTextField();
		txtTenDangNhap.setColumns(10);
		
		JLabel lblHTn = new JLabel("Họ Tên");
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Mật Khẩu");
		
		txtMatKhau = new JPasswordField();
		
		JLabel lblGiiTnh = new JLabel("Giới Tính");
		
		JComboBox<String> cboGioiTinh = new JComboBox<String>();
		cboGioiTinh.addItem("Nam");
		cboGioiTinh.addItem("Nữ");
		JLabel lblNgySinh = new JLabel("Ngày Sinh");
		
		JDateChooser dtpNgaySinh = new JDateChooser(new Date());
		dtpNgaySinh.setDateFormatString("dd-MM-yyyy");
		GroupLayout gl_infoPane = new GroupLayout(infoPane);
		gl_infoPane.setHorizontalGroup(
			gl_infoPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infoPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblMtKhu)
						.addComponent(lblNgySinh))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(dtpNgaySinh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtMatKhau)
						.addComponent(txtTenDangNhap, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblHTn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblGiiTnh, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(12)
					.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(cboGioiTinh, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtHoTen, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_infoPane.setVerticalGroup(
			gl_infoPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infoPane.createSequentialGroup()
					.addGap(23)
					.addGroup(gl_infoPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtTenDangNhap, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHTn)
						.addComponent(txtHoTen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_infoPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMtKhu)
						.addComponent(txtMatKhau, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGiiTnh)
						.addComponent(cboGioiTinh, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_infoPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNgySinh)
						.addComponent(dtpNgaySinh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		infoPane.setLayout(gl_infoPane);
		
		JPanel checkPane = new JPanel();
		themSinhVienView.getContentPane().add(checkPane, BorderLayout.SOUTH);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mkhau = Commons.hashMD5Password(new String(txtMatKhau.getPassword()));
				if("".equals(txtHoTen.getText())||"".equals(txtHoTen.getText()) || "".equals(new String(txtMatKhau.getPassword()))){
					Commons.showErrorMessage(themSinhVienView, "Vui lòng nhập đầy đủ thông tin sinh viên", "Lỗi");
				}
				NguoiDung ndAdd = new NguoiDung(0, txtHoTen.getText(), txtTenDangNhap.getText(), mkhau,
						(String) cboGioiTinh.getSelectedItem(), dtpNgaySinh.getDate(), 2, 0, null);
				if(NguoiDungDAO.themNguoiDung(ndAdd)){
					Commons.showSuccessMessage(themSinhVienView, "Thêm mới sinh viên thành công", "Thành Công");
					themSinhVienView.dispose();
				}else{
					Commons.showErrorMessage(themSinhVienView, "Thêm mói sinh viên không thành công", "Lỗi");
				}
			}
		});
		checkPane.add(btnThem);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themSinhVienView.dispose();
			}
		});
		checkPane.add(btnHuy);
	}
}
