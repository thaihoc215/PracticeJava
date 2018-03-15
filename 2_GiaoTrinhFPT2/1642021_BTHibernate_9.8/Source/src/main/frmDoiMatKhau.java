package main;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import dao.NguoiDungDAO;
import entities.NguoiDung;

public class frmDoiMatKhau extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField txtOldPass;
	private JPasswordField txtNewPass;
	private JPasswordField txtXacNhanPass;
	public boolean dialogResult = false;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmDoiMatKhau dialog = new frmDoiMatKhau(new NguoiDung());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setModal(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param ndLogin 
	 */
	public frmDoiMatKhau(NguoiDung ndLogin) {
		setTitle("Đổi Mật Khẩu");
		setBounds(100, 100, 290, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width - this.getWidth()) / 2, (dim.height - this.getHeight()) / 2);
		
		this.setModal(true);
		
		JLabel lblTnngNhp = new JLabel("Tên Đăng Nhập");
		
		textField = new JTextField(ndLogin.getTenDangNhap());
		textField.setEnabled(false);
		textField.setColumns(10);
		
		JLabel lblMtKhuC = new JLabel("Mật Khẩu Cũ");
		
		txtOldPass = new JPasswordField();
		
		JLabel lblMtKhuMi = new JLabel("Mật Khẩu Mới");
		
		JLabel lblXcNhn = new JLabel("Xác Nhận Mật Khẩu");
		
		txtNewPass = new JPasswordField();
		
		txtXacNhanPass = new JPasswordField();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMtKhuC)
						.addComponent(lblMtKhuMi)
						.addComponent(lblXcNhn)
						.addComponent(lblTnngNhp))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.addComponent(txtXacNhanPass, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.addComponent(txtNewPass, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.addComponent(txtOldPass, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
					.addGap(23))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTnngNhp)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMtKhuC)
						.addComponent(txtOldPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMtKhuMi)
						.addComponent(txtNewPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXcNhn)
						.addComponent(txtXacNhanPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Lưu");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						byte[] bytesOfMessage;
						try {
							bytesOfMessage = new String(txtOldPass.getPassword()).getBytes("UTF8");
							MessageDigest md = MessageDigest.getInstance("MD5");
							byte[] thedigest = md.digest(bytesOfMessage);
							String mk = new String(thedigest,"UTF8");
							if(!ndLogin.getMatKhau().equals(mk)){
								showErrorMessage("Sai mật khẩu cũ!!!", "Lỗi");
								return;
							}
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
						
						if (!Arrays.equals(txtNewPass.getPassword(), txtXacNhanPass.getPassword())) {
							showErrorMessage("Xác nhận mật khẩu không đúng", "Lỗi");
							return;
						}
						
						try {
							bytesOfMessage = new String(txtNewPass.getPassword()).getBytes("UTF8");
							MessageDigest md = MessageDigest.getInstance("MD5");
							byte[] thedigest = md.digest(bytesOfMessage);
							
							ndLogin.setMatKhau(new String(thedigest,"UTF8"));
//							ndLogin.setSoLanDangNhap(ndLogin.getSoLanDangNhap()+1);
							if(NguoiDungDAO.updateNguoiDung(ndLogin)){
								showSuccessMessage("Thay đổi mật khẩu thành công", "Thành công");
								dialogResult = true;
								dispose();
							} else{
								showErrorMessage("Thay đổi mật khẩu không thành công", "Lỗi");
							}
						} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Hủy");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialogResult = false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void showErrorMessage(String errorMessage, String errorTitle) {
		JOptionPane.showMessageDialog(this, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	private void showSuccessMessage(String successMes, String successTit) {
		JOptionPane.showMessageDialog(this, successMes, successTit, JOptionPane.INFORMATION_MESSAGE);
	}
}
