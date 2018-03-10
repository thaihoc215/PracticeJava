package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import conn.MyJDBC;
import model.HocSinhModel;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;

public class QuanLyHocSinh {

	private JFrame frmQuanLyHocSinh;
	private JPanel mainPanel;
	private JTextField txtSearcMa;
	private JTable lstHSTable;
	private JScrollPane lstHSScroll;

	//Class variables
	HocSinhModel hsSelected = null;
	private JDateChooser dtpNgaySinh;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					QuanLyHocSinh window = new QuanLyHocSinh();
					window.frmQuanLyHocSinh.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuanLyHocSinh() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQuanLyHocSinh = new JFrame("Quản Lý Học Sinh");
		frmQuanLyHocSinh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQuanLyHocSinh.setBounds(100, 100, 690, 550);

		// Set open in center screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frmQuanLyHocSinh.setLocation(dim.width / 2 - frmQuanLyHocSinh.getSize().width / 2,
				dim.height / 2 - frmQuanLyHocSinh.getSize().height / 2);

		mainPanel = new JPanel(new BorderLayout(3, 3));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frmQuanLyHocSinh.setContentPane(mainPanel);

		// Get data
		MyJDBC dbProcess = new MyJDBC();
		ArrayList<HocSinhModel> lstHS = dbProcess.getListHS();

		JPanel searchPane = new JPanel();
		mainPanel.add(searchPane, BorderLayout.NORTH);
		
		JLabel lbMaTen = new JLabel("Mã | Tên HS");
		searchPane.add(lbMaTen);

		txtSearcMa = new JTextField();
		searchPane.add(txtSearcMa);
		txtSearcMa.setColumns(10);

		JButton btnSearch = new JButton("Tìm Kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<HocSinhModel> lstHSFind = new ArrayList<HocSinhModel>();
				Date nsinh = dtpNgaySinh.getDate();
				try {
					int maHS;
					maHS = Integer.parseInt(txtSearcMa.getText());
					lstHSFind = dbProcess.findHS(maHS, txtSearcMa.getText(), nsinh == null?new Date():nsinh);
				} catch (NumberFormatException ex) {
					lstHSFind = dbProcess.findHS(-1, txtSearcMa.getText(), nsinh == null?new Date():nsinh);
				}
				if (lstHSFind.size() <= 0) {
					showErrorMessage("Không tìm thấy học sinh", "Not Found");
					return;
				}
				lstHSTable = RenderTable(lstHSFind);
				lstHSScroll.setViewportView(lstHSTable);
			}
		});
		
		JLabel lblNgaySinh = new JLabel("Ngày Sinh");
		searchPane.add(lblNgaySinh);
		
		dtpNgaySinh = new JDateChooser();
		dtpNgaySinh.setDateFormatString("\r\nyyyy-MM-dd");
		searchPane.add(dtpNgaySinh);
		searchPane.add(btnSearch);

		JButton btnGetAll = new JButton("Tìm Tất Cả");
		btnGetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstHSTable = RenderTable(dbProcess.getListHS());
				lstHSScroll.setViewportView(lstHSTable);
			}
		});
		searchPane.add(btnGetAll);

		JPanel listHSPane = new JPanel();
		mainPanel.add(listHSPane, BorderLayout.CENTER);
		listHSPane.setLayout(new BorderLayout(0, 0));

		// add table hoc sinh
		lstHSTable = RenderTable(lstHS);
		
		lstHSScroll = new JScrollPane();
		lstHSScroll.setViewportView(lstHSTable);
		listHSPane.add(lstHSScroll);
		
		JPanel processPane = new JPanel();
		mainPanel.add(processPane, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmThongTinHocSinh frm = new frmThongTinHocSinh(2,null);
				frm.setLocationRelativeTo(frmQuanLyHocSinh);
				frm.setVisible(true);				
				lstHSTable = RenderTable(dbProcess.getListHS());
				lstHSScroll.setViewportView(lstHSTable);
			}
		});
		
				JButton btnEdit = new JButton("Thông Tin");
				btnEdit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(hsSelected == null) {
							showErrorMessage("Bạn chưa chọn học sinh", "Lỗi");
							return;
						}
						frmThongTinHocSinh frm = new frmThongTinHocSinh(1,hsSelected);
						frm.setLocationRelativeTo(frmQuanLyHocSinh);
						frm.setVisible(true);
						lstHSTable = RenderTable(dbProcess.getListHS());
						lstHSScroll.setViewportView(lstHSTable);
					}
				});
				processPane.add(btnEdit);
		processPane.add(btnAdd);

		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hsSelected == null){
					showErrorMessage("Bạn chưa chọn học sinh", "Lỗi");
					return;
				}
				if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa học sinh này?", "Xóa học sinh",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.NO_OPTION)
					return;
				
				try {
					dbProcess.deleteHocSinh(hsSelected.get_maHS());
					showSuccessMessage("Xóa học sinh thành công", "Thành công");
					lstHSTable = RenderTable(dbProcess.getListHS());
					lstHSScroll.setViewportView(lstHSTable);
					
				} catch (SQLException e1) {
					e1.printStackTrace();
					
				}
			}
		});
		processPane.add(btnDelete);

	}

	// Ha xu lý set chieu rong cho column cua table
	private void setColumnWidth(JTable table, int column, int width) {
		TableColumn tbColumn = table.getColumnModel().getColumn(column);
		tbColumn.setPreferredWidth(width);
	}

	
	/**/
	private JTable RenderTable(ArrayList<HocSinhModel> lstDSHS) {
		// Set column, thuoc tinh cho table
		String[] columnNames = { "Mã HS", "Họ Tên", "Ngày Sinh", "Ghi Chú"};
		Object[][] tableData = new Object[lstDSHS.size()][4];
		// Fill table
		int index = 0;
		for (HocSinhModel hsinh : lstDSHS) {
			tableData[index][0] = hsinh.get_maHS();
			tableData[index][1] = hsinh.get_tenHS();
			tableData[index][2] = hsinh.get_ngaySinh() == null ? null : hsinh.get_ngaySinh().toString();
			tableData[index][3] = hsinh.get_ghiChu();
			index++;
		}
		
		DefaultTableModel model = new DefaultTableModel(tableData, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// add your code here
				return false;
			}
		};
		JTable table = new JTable(model);
		table.setShowVerticalLines(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateColumnsFromModel(true);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = table.getSelectionModel().getLeadSelectionIndex();
				hsSelected = new HocSinhModel();
				hsSelected = lstDSHS.get(row);
			}
		};
		table.getSelectionModel().addListSelectionListener(listSelectionListener);
//		setColumnWidth(table,0,300);
		setColumnWidth(table,1,150);
		setColumnWidth(table,2,50);
		setColumnWidth(table,3,250);
		return table;
	}

	// Ham show message loi
	private void showErrorMessage(String errorMessage, String errorTitle) {
		JOptionPane.showMessageDialog(this.frmQuanLyHocSinh, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	private void showSuccessMessage(String successMessage, String successTitle) {
		JOptionPane.showMessageDialog(this.frmQuanLyHocSinh, successMessage, successTitle, JOptionPane.INFORMATION_MESSAGE);
	}
}
