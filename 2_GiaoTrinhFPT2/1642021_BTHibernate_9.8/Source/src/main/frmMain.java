package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import entities.NguoiDung;
import main.giaovu.frmKetQuaDiemDanh;
import main.giaovu.frmThemMonHoc;
import main.giaovu.frmThemSinhVien;
import main.giaovu.frmThemSinhVienMonHoc;
import main.sinhvien.frmDiemDanh;
import main.sinhvien.frmXemKetQua;

public class frmMain extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
//	public static void main(NguoiDung ndLogin) {
		public static void main(String[] ndLogin1) {
		try {
//			NguoiDung ndLogin = new NguoiDung();
//			ndLogin.setLoaiNguoiDung(1);
//			ndLogin.setTenNguoiDung("test");
			frmMain dialog = new frmMain(new NguoiDung());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param ngDung 
	 */
	public frmMain(NguoiDung ngDung) {
		super(null, java.awt.Dialog.ModalityType.MODELESS);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		// Set open in center screen
		setBounds(100, 100, 450, 250);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
		setTitle("Chọn Chức Năng");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblMaNguoiDung = new JLabel("Mã Người Dùng: ");
		lblMaNguoiDung.setText(lblMaNguoiDung.getText() + ngDung.getMaNguoiDung());
		
		JLabel lblTenNguoiDung = new JLabel("Tên người Dùng: ");
		lblTenNguoiDung.setText(lblTenNguoiDung.getText() + ngDung.getTenNguoiDung());
		
		JLabel lblLoaiNguoiDung = new JLabel("Loại Người Dùng: ");
		lblLoaiNguoiDung.setText(lblLoaiNguoiDung.getText() + (ngDung.getLoaiNguoiDung() == 1 ? "Giáo Vụ" : "Sinh Viên"));
	
		JButton btnKetQuaDiemDanh = new JButton("Kết Quả Điểm Danh");
		btnKetQuaDiemDanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ngDung.getLoaiNguoiDung() == 1){
					frmKetQuaDiemDanh.main(null);
				}else{
					frmXemKetQua frmSVXemketqua = new frmXemKetQua(ngDung);
					frmSVXemketqua.show();
				}
			}
		});
		JButton btnThem_DiemDanh = new JButton("Thêm Sinh Viên Môn Học");
		if(ngDung.getLoaiNguoiDung() == 2) btnThem_DiemDanh.setText("Điểm Danh");
		btnThem_DiemDanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ngDung.getLoaiNguoiDung() == 1){
					//Them sinh vien vao mon hoc
					frmThemSinhVienMonHoc.main(null);
				} else{
					//Sinh vien diem danh
					frmDiemDanh frmDD = new frmDiemDanh(ngDung);
					
				}
			}
		});
		
		JButton btnTaoMonHoc = new JButton("Tạo Môn Học");
		if(ngDung.getLoaiNguoiDung() == 2)
			btnTaoMonHoc.setVisible(false);
		btnTaoMonHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmThemMonHoc.main(null);
			}
		});
		
		JButton btnThemSinhVien = new JButton("Thêm Mới Sinh Viên");
		btnThemSinhVien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmThemSinhVien.main(null);
			
			}
		});
		if(ngDung.getLoaiNguoiDung() == 2)
			btnThemSinhVien.setVisible(false);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTenNguoiDung)
						.addComponent(lblMaNguoiDung)
						.addComponent(lblLoaiNguoiDung))
					.addGap(67)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnThemSinhVien, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(btnTaoMonHoc, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(btnThem_DiemDanh, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
						.addComponent(btnKetQuaDiemDanh, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
					.addGap(27))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(20)
							.addComponent(lblMaNguoiDung)
							.addGap(18)
							.addComponent(lblTenNguoiDung)
							.addGap(18)
							.addComponent(lblLoaiNguoiDung))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(34)
									.addComponent(btnThem_DiemDanh)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnTaoMonHoc, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnKetQuaDiemDanh))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnThemSinhVien)))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnLogout = new JButton("Đăng Xuất");
				btnLogout.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frmDangNhap.main(null);
						dispose();
					}
				});
				
				JButton btnDoiMatKhau = new JButton("Đổi Mật Khẩu");
				btnDoiMatKhau.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frmDoiMatKhau frmDMK = new frmDoiMatKhau(ngDung);
						frmDMK.setVisible(true);
						
						if(frmDMK.dialogResult == true){
							frmDangNhap.main(null);
							dispose();
						}
					}
				});
				buttonPane.add(btnDoiMatKhau);
				btnLogout.setActionCommand("OK");
				buttonPane.add(btnLogout);
				getRootPane().setDefaultButton(btnLogout);
			}
			{
				JButton btnExit = new JButton("Thoát");
				btnExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(1);
					}
				});
				btnExit.setActionCommand("Cancel");
				buttonPane.add(btnExit);
			}
		}
	}
}
