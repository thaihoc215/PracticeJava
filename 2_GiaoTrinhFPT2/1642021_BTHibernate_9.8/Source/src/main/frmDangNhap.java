package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import dao.NguoiDungDAO;
import entities.NguoiDung;

public class frmDangNhap {

	private JFrame loginView;
	private JTextField txtUserName;
	private JPasswordField txtPass;
	public boolean dialogResult = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frmDangNhap window = new frmDangNhap();
					window.loginView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmDangNhap() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		loginView = new JFrame();
		loginView.setTitle("Đăng Nhập");
		loginView.setBounds(100, 100, 269, 307);
		loginView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set open in center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		loginView.setLocation(dim.width / 2 - loginView.getSize().width / 2,
				dim.height / 2 - loginView.getSize().height / 2);
		
		JPanel mainPane = new JPanel();
		loginView.getContentPane().add(mainPane, BorderLayout.CENTER);
		
		JLabel lblUserName = new JLabel("Tên Đăng Nhập");
		
		JLabel lblPass = new JLabel("Mật Khẩu");
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		
		txtPass = new JPasswordField();
		
		JButton btnLogin = new JButton("Đăng Nhập");
		loginView.getRootPane().setDefaultButton(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NguoiDung ndLogin = NguoiDungDAO.layNguoiDung(txtUserName.getText());
				if(ndLogin == null){
					showErrorMessage("Tên đăng nhập không đúng!!!", "Lỗi");
					return;
				}
				byte[] bytesOfMessage;
				try {
					bytesOfMessage = new String(txtPass.getPassword()).getBytes("UTF8");
					MessageDigest md = MessageDigest.getInstance("MD5");
					byte[] thedigest = md.digest(bytesOfMessage);
					String mk = new String(thedigest,"UTF8");
//					if(!ndLogin.getMatKhau().getBytes().equals(thedigest)){
					if (!Arrays.equals(ndLogin.getMatKhau().toCharArray(), mk.toCharArray())) {
						showErrorMessage("Sai mật khẩu!!!", "Lỗi");
						return;
					}
				} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				//Kiem tra sinh vien lan dau tien dang nhap
				if(ndLogin.getLoaiNguoiDung() == 2 && ndLogin.getSoLanDangNhap() == 0){
					//hien thi man hinh doi mat khau
					frmDoiMatKhau frmDMK = new frmDoiMatKhau(ndLogin);
					frmDMK.setLocationRelativeTo(loginView);
					frmDMK.setVisible(true);
					if(frmDMK.dialogResult == true){
						ndLogin.setSoLanDangNhap(ndLogin.getSoLanDangNhap() + 1);
						NguoiDungDAO.updateNguoiDung(ndLogin);
					}
					return;
				} else {
					ndLogin.setSoLanDangNhap(ndLogin.getSoLanDangNhap() + 1);
					// update so lan dang nhap
					NguoiDungDAO.updateNguoiDung(ndLogin);
				}
				loginView.dispose();
				frmMain frmmain = new frmMain(ndLogin);
				frmmain.setVisible(true);
			}
		});
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		GroupLayout gl_mainPane = new GroupLayout(mainPane);
		gl_mainPane.setHorizontalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_mainPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUserName)
						.addComponent(lblPass))
					.addGap(18)
					.addGroup(gl_mainPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtPass)
						.addComponent(txtUserName, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
					.addGap(27))
				.addGroup(Alignment.TRAILING, gl_mainPane.createSequentialGroup()
					.addGap(87)
					.addGroup(gl_mainPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnExit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
						.addComponent(btnLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
					.addGap(79))
		);
		gl_mainPane.setVerticalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addGap(62)
					.addGroup(gl_mainPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_mainPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPass))
					.addGap(42)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(40))
		);
		mainPane.setLayout(gl_mainPane);
		

	}
	private void showErrorMessage(String errorMessage, String errorTitle) {
		JOptionPane.showMessageDialog(this.loginView, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}
	


}
