package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import conn.MyJDBC;
import model.HocSinhModel;

public class frmThongTinHocSinh extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaHS;
	private JTextField txtTenHocSinh;
	private JLabel lblNgySinh;
	private JLabel lblGhiCh;
	private JTextArea txtGhiChu;
	private JLabel lblHinhAnh;
	private JLabel lblMHcSinh;
	private JLabel lblTnHcSinh;
	private JDateChooser dtpNgaySinh;
	private JLabel picLabel;
	private JPanel imagePane;

	// class variables check type show dialog
	private static final int INFO_TYPE = 1;
	private static final int ADD_TYPE = 2;
	private static final int EDIT_TYPE = 3;

	// condition process of type show
	private int processType = 1;
	private JButton btnChonAnh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			// frmThongTinHocSinh dialog1 = new frmThongTinHocSinh();
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			frmThongTinHocSinh dialog = new frmThongTinHocSinh(0, new HocSinhModel());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param hsinh
	 * 
	 * @param i
	 */
	public frmThongTinHocSinh(int typeShow, HocSinhModel hsSelected) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setModal(true);
		// Set open in center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		setBounds(100, 100, 555, 387);
		MyJDBC dbProcess = new MyJDBC();
		processType = typeShow;

		getContentPane().setLayout(new BorderLayout());
		{
			JPanel mainPane = new JPanel();
			mainPane.setBorder(new LineBorder(new Color(0, 0, 0)));
			getContentPane().add(mainPane, BorderLayout.NORTH);

			JPanel leftPane = new JPanel();
			lblNgySinh = new JLabel("Ngày Sinh");
			lblGhiCh = new JLabel("Ghi Chú");
			lblMHcSinh = new JLabel("Mã Học Sinh");

			txtMaHS = new JTextField();
			txtMaHS.setColumns(10);
			txtMaHS.setEnabled(false);

			lblTnHcSinh = new JLabel("Tên Học Sinh");

			txtTenHocSinh = new JTextField();
			txtTenHocSinh.setColumns(10);

			JScrollPane scrollPane = new JScrollPane();

			dtpNgaySinh = new JDateChooser();
			dtpNgaySinh.setDateFormatString("yyyy-MM-dd");
			GroupLayout gl_leftPane = new GroupLayout(leftPane);
			gl_leftPane.setHorizontalGroup(
				gl_leftPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_leftPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_leftPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_leftPane.createSequentialGroup()
								.addComponent(lblTnHcSinh)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_leftPane.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_leftPane.createSequentialGroup()
									.addComponent(lblMHcSinh)
									.addGap(26))
								.addGroup(gl_leftPane.createSequentialGroup()
									.addComponent(lblNgySinh, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE))
								.addGroup(gl_leftPane.createSequentialGroup()
									.addComponent(lblGhiCh, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE))))
						.addGroup(gl_leftPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(dtpNgaySinh, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
							.addComponent(txtMaHS, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
							.addGroup(gl_leftPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtTenHocSinh, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
						.addContainerGap())
			);
			gl_leftPane.setVerticalGroup(
				gl_leftPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_leftPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_leftPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMHcSinh)
							.addComponent(txtMaHS, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGap(7)
						.addGroup(gl_leftPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(txtTenHocSinh, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTnHcSinh))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_leftPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(dtpNgaySinh, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNgySinh, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_leftPane.createParallelGroup(Alignment.LEADING)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
							.addComponent(lblGhiCh))
						.addContainerGap())
			);
			{
				txtGhiChu = new JTextArea();
				txtGhiChu.setLineWrap(true);
				txtGhiChu.setWrapStyleWord(true);
				scrollPane.setViewportView(txtGhiChu);
			}
			leftPane.setLayout(gl_leftPane);
			{
				JPanel rightPane = new JPanel();
				lblHinhAnh = new JLabel("Hình Ảnh");
				{
					btnChonAnh = new JButton("Chọn Ảnh");

					JDesktopPane desktopPane = new JDesktopPane();

					imagePane = new JPanel();
					imagePane.setBackground(Color.WHITE);
					GroupLayout gl_rightPane = new GroupLayout(rightPane);
					gl_rightPane.setHorizontalGroup(
						gl_rightPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_rightPane.createSequentialGroup()
								.addGap(87)
								.addComponent(btnChonAnh, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
								.addGap(76))
							.addGroup(gl_rightPane.createSequentialGroup()
								.addGap(106)
								.addComponent(lblHinhAnh, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
								.addGap(95))
							.addGroup(gl_rightPane.createSequentialGroup()
								.addGap(16)
								.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(imagePane, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
								.addGap(18))
					);
					gl_rightPane.setVerticalGroup(
						gl_rightPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_rightPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblHinhAnh, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_rightPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_rightPane.createSequentialGroup()
										.addGap(55)
										.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_rightPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(imagePane, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnChonAnh)
								.addContainerGap())
					);
					imagePane.setLayout(new BorderLayout(0, 0));

					picLabel = new JLabel("");
					imagePane.add(picLabel, BorderLayout.CENTER);

					rightPane.setLayout(gl_rightPane);
					GroupLayout gl_mainPane = new GroupLayout(mainPane);
					gl_mainPane.setHorizontalGroup(gl_mainPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_mainPane.createSequentialGroup()
									.addComponent(leftPane, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rightPane, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)));
					gl_mainPane.setVerticalGroup(gl_mainPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_mainPane.createSequentialGroup()
									.addGroup(gl_mainPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(leftPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 315,
													Short.MAX_VALUE)
											.addComponent(rightPane, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
									.addGap(0)));
					mainPane.setLayout(gl_mainPane);
					btnChonAnh.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							JFileChooser fileChooser = new JFileChooser();
							fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "bmp"));
							int returnValue = fileChooser.showOpenDialog(null);
							if (returnValue == JFileChooser.APPROVE_OPTION) {
								File selectedFile = fileChooser.getSelectedFile();
								ImageIcon myPicture = new ImageIcon(selectedFile.getPath());
								Image img = myPicture.getImage();
								picLabel.setIcon(new ImageIcon(img.getScaledInstance(imagePane.getWidth(),
										imagePane.getHeight(), Image.SCALE_SMOOTH)));

							}
						}
					});
				}
			}
		}

		// Process button
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		// button Save
		JButton btnSave = new JButton("Sửa");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (processType == INFO_TYPE) {
					processType = EDIT_TYPE;
					btnSave.setText("Lưu");
					txtTenHocSinh.setEnabled(true);
					txtGhiChu.setEnabled(true);
					dtpNgaySinh.setEnabled(true);
					btnChonAnh.setEnabled(true);
					return;
				}

				HocSinhModel hs = new HocSinhModel();
				hs.set_tenHS(txtTenHocSinh.getText());
				hs.set_ngaySinh(dtpNgaySinh.getDate());
				hs.set_ghiChu(txtGhiChu.getText());

				File imgToDatabase = new File("hsTempImg.png");
				try {
					ImageIcon icon = (ImageIcon) picLabel.getIcon();
					Image image = icon.getImage();
					RenderedImage rendered = null;
					if (image instanceof RenderedImage) {
						rendered = (RenderedImage) image;
					} else {
						BufferedImage buffered = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
								BufferedImage.TYPE_INT_RGB);
						Graphics2D g = buffered.createGraphics();
						g.drawImage(image, 0, 0, null);
						g.dispose();
						rendered = buffered;
					}
					ImageIO.write(rendered, "png", imgToDatabase);
				} catch (Exception e2) {
//					e2.printStackTrace();
					System.out.println("No image choose!");
					imgToDatabase = null;
				}
				if (processType == ADD_TYPE) {
					try {
						if (dbProcess.addHocSinh(hs, imgToDatabase))
							showSuccessMessage("Thêm học sinh thành công", "Thành công");
					} catch (SQLException | IOException e1) {
						e1.printStackTrace();
						showErrorMessage("Thêm học sinh không thành công", "Thất bại");
					}
				} else if (processType == EDIT_TYPE) {
					try {
						hs.set_maHS(Integer.parseInt(txtMaHS.getText()));
						if (dbProcess.editHocSinh(hs, imgToDatabase))
							showSuccessMessage("Sửa thông tin học sinh thành công", "Thành công");
						btnSave.setText("Sửa");
						txtTenHocSinh.setEnabled(false);
						txtGhiChu.setEnabled(false);
						dtpNgaySinh.setEnabled(false);
						btnChonAnh.setEnabled(false);
						processType = INFO_TYPE;
					} catch (SQLException | IOException e1) {
						e1.printStackTrace();
						showErrorMessage("Sửa thông tin không thành công", "Thất bại");
					}
				}
			}
		});
		btnSave.setActionCommand("OK");
		buttonPane.add(btnSave);
		getRootPane().setDefaultButton(btnSave);
		// button Cancel
		JButton btnCancel = new JButton("Thoát");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setActionCommand("Cancel");
		buttonPane.add(btnCancel);

		// Check dieu kien de hien thi
		if (processType == ADD_TYPE) {
			setTitle("Thêm Học Sinh");
			btnSave.setText("Thêm");
			// txtMaHS.setText("0");
		} else if (processType == INFO_TYPE) {
			setTitle("Thông Tin Hoc Sinh");
			btnSave.setText("Sửa");

			txtMaHS.setText(String.valueOf(hsSelected.get_maHS()));
			txtTenHocSinh.setText(hsSelected.get_tenHS());
			txtGhiChu.setText(hsSelected.get_ghiChu());
			dtpNgaySinh.setDate(hsSelected.get_ngaySinh());

			txtTenHocSinh.setEnabled(false);
			txtGhiChu.setEnabled(false);
			dtpNgaySinh.setEnabled(false);
			btnChonAnh.setEnabled(false);

			if (hsSelected.get_images() != null && hsSelected.get_images().length > 0) {
				ImageIcon hsImage = new ImageIcon(hsSelected.get_images());
				hsImage = new ImageIcon(hsImage.getImage().getScaledInstance(hsImage.getIconWidth(),
						hsImage.getIconHeight(), Image.SCALE_SMOOTH));
				picLabel.setIcon(hsImage);
			}
		}

	}

	private void showErrorMessage(String errorMessage, String errorTitle) {
		JOptionPane.showMessageDialog(this, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}

	private void showSuccessMessage(String successMessage, String successTitle) {
		JOptionPane.showMessageDialog(this, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
	}
}
