package main.sinhvien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import Utils.Commons;
import Utils.HeaderRenderer;
import dao.MonHocDAO;
import dao.SinhVienMonHocDAO;
import entities.IDSinhVienMonHoc;
import entities.MonHoc;
import entities.NguoiDung;
import entities.SinhVienMonHoc;

public class frmDiemDanh {

	private JFrame diemDanhView;
	private JTextField txtSearchTenMon;
	private JTable tbDSMH;
	private ArrayList<MonHoc> dsMonHoc;
	private JScrollPane scrDSMH;
	private MonHoc monhocSelected;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frmDiemDanh window = new frmDiemDanh(new NguoiDung());
					window.diemDanhView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	NguoiDung sinhVienItem;
	private JLabel lblTuanHoc;
	private JButton btnDiemDanh;
	protected int week;
	public frmDiemDanh(NguoiDung sinhVien) {
		sinhVienItem = new NguoiDung();
		sinhVienItem = sinhVien;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		diemDanhView = new JFrame();
		diemDanhView.setTitle("Sinh Viên Điểm Danh");
		diemDanhView.setBounds(100, 100, 565, 366);
		diemDanhView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		diemDanhView.setLocation((dim.width - diemDanhView.getSize().width) / 2,
				(dim.height - diemDanhView.getSize().height) / 2);
		diemDanhView.setVisible(true);
		
		JPanel mainPane = new JPanel();
		diemDanhView.getContentPane().add(mainPane, BorderLayout.CENTER);
		
		JLabel lblChonMon = new JLabel("Tên Môn Học");
		
		txtSearchTenMon = new JTextField();
		txtSearchTenMon.setColumns(10);
		
		JButton btnTimKiem = new JButton("Tìm");
		btnTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MonHoc> dsMonHocTimKiem = new ArrayList<>();
				for (MonHoc monHoc : dsMonHoc) {
					if (monHoc.getTenMonHoc().contains(txtSearchTenMon.getText()))
						dsMonHocTimKiem.add(monHoc);
				}

				if (dsMonHocTimKiem == null || dsMonHocTimKiem.size() == 0) {
					showErrorMessage("Không tìm thấy môn học", "Lỗi");
					dsMonHocTimKiem = new ArrayList<>();
				}
				
				dsMonHocTimKiem.sort(new Comparator<MonHoc>() {
					@Override
					public int compare(MonHoc o1, MonHoc o2) {
						return o1.getMaMonHoc() - o2.getMaMonHoc();
					}
				});
				
				tbDSMH = RenderTable(dsMonHocTimKiem);
				scrDSMH.setViewportView(tbDSMH);

				monhocSelected = null;
			}
		});
		
		JPanel paneDSMH = new JPanel();
		paneDSMH.setBorder(new TitledBorder(null, "Danh S\u00E1ch M\u00F4n H\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		paneDSMH.setLayout(new BorderLayout(0, 0));
		
		btnDiemDanh = new JButton("Điểm Danh");
		btnDiemDanh.setEnabled(false);
		btnDiemDanh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dt = new Date();
				if(dt.getDay() != monhocSelected.getNgayBatDau().getDay()){
					showErrorMessage("Không có tiết học hôm nay", "Lỗi");
					return;
				}
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		        
				if(sdf.format(cal.getTime()).compareTo(monhocSelected.getGioBatDau()) < 0){
					showErrorMessage("Tiết học chưa bắt đầu", "Lỗi");
					return;
				}
				if(sdf.format(cal.getTime()).compareTo(monhocSelected.getGioKetThuc()) > 0){
					showErrorMessage("Tiết học đã kết thúc", "Lỗi");
					return;
				}
				
				SinhVienMonHoc svmh = new SinhVienMonHoc();
				IDSinhVienMonHoc idSinhVienMonHoc = new IDSinhVienMonHoc();
				idSinhVienMonHoc.setMaMonHoc(monhocSelected.getMaMonHoc());
				idSinhVienMonHoc.setMaSinhVien(sinhVienItem.getMaNguoiDung());
				ArrayList<SinhVienMonHoc> lstSvmh = SinhVienMonHocDAO.layDSSVMHByMon(monhocSelected.getMaMonHoc());
				for (SinhVienMonHoc sv : lstSvmh) {
					if(sv.getMaSinhVienMonHoc().getMaSinhVien() == sinhVienItem.getMaNguoiDung()){
						lstSvmh.clear();
						lstSvmh.add(sv);
						break;
					}
				}

				switch (week) {
				case 1: lstSvmh.get(0).setWeek1(true);
					break;
				case 2:lstSvmh.get(0).setWeek2(true);
					break;
				case 3:lstSvmh.get(0).setWeek3(true);
					break;
				case 4:lstSvmh.get(0).setWeek4(true);
					break;
				case 5:lstSvmh.get(0).setWeek5(true);
					break;
				case 6:lstSvmh.get(0).setWeek6(true);
					break;
				case 7:lstSvmh.get(0).setWeek7(true);
					break;
				case 8:lstSvmh.get(0).setWeek8(true);
					break;
				case 9:lstSvmh.get(0).setWeek9(true);
					break;
				case 10:lstSvmh.get(0).setWeek10(true);
					break;
				case 11:lstSvmh.get(0).setWeek11(true);
					break;
				case 12:lstSvmh.get(0).setWeek12(true);
					break;
				case 13:lstSvmh.get(0).setWeek13(true);
					break;
				case 14:lstSvmh.get(0).setWeek14(true);
					break;
				case 15:lstSvmh.get(0).setWeek15(true);
					break;
				}
				if(SinhVienMonHocDAO.updateDiemDanh(lstSvmh)){
					showSuccessMessage("Điểm danh thành công", "Thành Công");
				} else
					showErrorMessage("Điểm danh không thành công", "Lỗi");
			}
		});
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diemDanhView.dispose();
			}
		});
		
		JLabel lblSinhVien = new JLabel("Sinh Viên " + sinhVienItem.getTenNguoiDung());
		lblSinhVien.setForeground(Color.RED);
		lblSinhVien.setBackground(Color.RED);
		
		lblTuanHoc = new JLabel("Tuần học: ");
		lblTuanHoc.setForeground(Color.DARK_GRAY);
		GroupLayout gl_mainPane = new GroupLayout(mainPane);
		gl_mainPane.setHorizontalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_mainPane.createSequentialGroup()
							.addGroup(gl_mainPane.createParallelGroup(Alignment.LEADING)
								.addComponent(paneDSMH, GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
								.addGroup(gl_mainPane.createSequentialGroup()
									.addComponent(lblSinhVien)
									.addPreferredGap(ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
									.addComponent(btnDiemDanh)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnThoat, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())
						.addGroup(gl_mainPane.createSequentialGroup()
							.addComponent(lblChonMon)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtSearchTenMon, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnTimKiem)
							.addPreferredGap(ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
							.addComponent(lblTuanHoc)
							.addGap(114))))
		);
		gl_mainPane.setVerticalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_mainPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChonMon)
						.addComponent(txtSearchTenMon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTimKiem)
						.addComponent(lblTuanHoc))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(paneDSMH, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_mainPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDiemDanh)
						.addComponent(btnThoat)
						.addComponent(lblSinhVien))
					.addGap(5))
		);
		
		scrDSMH = new JScrollPane();
		paneDSMH.add(scrDSMH, BorderLayout.CENTER);
		
		dsMonHoc = new ArrayList<>();
		for (MonHoc monHoc : sinhVienItem.getDsMonHoc()) {
			dsMonHoc.add(MonHocDAO.layMonHoc(monHoc.getMaMonHoc()));
		}
		dsMonHoc.sort(new Comparator<MonHoc>() {
			@Override
			public int compare(MonHoc o1, MonHoc o2) {
				return o1.getMaMonHoc() - o2.getMaMonHoc();
			}
		});
		tbDSMH = new JTable((TableModel) null);
		tbDSMH = RenderTable(dsMonHoc);
		scrDSMH.setViewportView(tbDSMH);
		mainPane.setLayout(gl_mainPane);
	}
	
	/**
	 * ve table chua danh sach mon hoc
	 * @param lstMonHoc
	 * @return
	 */
	private JTable RenderTable(ArrayList<MonHoc> lstMonHoc) {
		// Set column, thuoc tinh cho table
		String[] columnNames = { "Mã Môn", "Tên Môn","Thứ","Giờ Học", "Giờ Kết Thúc", "Ngày Học", "Ngày Kết Thúc"};
		Object[][] tableData = new Object[lstMonHoc.size()][7];
		// Fill table
		int index = 0;
		for (MonHoc mh : lstMonHoc) {
			tableData[index][0] = mh.getMaMonHoc();
			tableData[index][1] = mh.getTenMonHoc();
			tableData[index][2] = Commons.dateOfWeek.get(mh.getThuTrongTuan() - 1);
			tableData[index][3] = mh.getGioBatDau();
			tableData[index][4] = mh.getGioKetThuc();
			tableData[index][5] = mh.getNgayBatDau().toString();
			tableData[index][6] = mh.getNgayKetThuc().toString();
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
		JTable dsMonHocTable = new JTable(model);
		dsMonHocTable.setShowVerticalLines(true);
		dsMonHocTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dsMonHocTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = dsMonHocTable.getSelectionModel().getLeadSelectionIndex();
				monhocSelected = new MonHoc();
				monhocSelected = lstMonHoc.get(row);
				
				
				
				Date dateNow = new Date();
				long days = TimeUnit.DAYS.convert(dateNow.getTime() - monhocSelected.getNgayBatDau().getTime(),
						TimeUnit.MILLISECONDS);
				if(days < 0){
					lblTuanHoc.setText("Môn học chưa bắt đầu");
					btnDiemDanh.setEnabled(false);
					return;
				}
					
				else if(days > 105){
					lblTuanHoc.setText("Môn học đã kết thúc");
					btnDiemDanh.setEnabled(false);
					return;
				}
				week = 0;
				week = (int)days/7 + 1;
				lblTuanHoc.setText("Tuần học: " + week);
				btnDiemDanh.setEnabled(true);
			}
		};
		dsMonHocTable.getSelectionModel().addListSelectionListener(listSelectionListener);
		setColumnWidth(dsMonHocTable,0,50);
		setColumnWidth(dsMonHocTable,1,100);
		setColumnWidth(dsMonHocTable,2,60);
		setColumnWidth(dsMonHocTable,3,80);
		setColumnWidth(dsMonHocTable,4,80);
		setColumnWidth(dsMonHocTable,5,80);
		setColumnWidth(dsMonHocTable,6,80);
		
		JTableHeader header = dsMonHocTable.getTableHeader();
		header.setDefaultRenderer(new HeaderRenderer(dsMonHocTable));
		return dsMonHocTable;
	}
	
	private void setColumnWidth(JTable table, int column, int width) {
		TableColumn tbColumn = table.getColumnModel().getColumn(column);
		tbColumn.setPreferredWidth(width);
	}
	
	private void showSuccessMessage(String successMes, String successTit) {
		JOptionPane.showMessageDialog(this.diemDanhView, successMes, successTit, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void showErrorMessage(String errorMessage, String errorTitle) {
		JOptionPane.showMessageDialog(this.diemDanhView , errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}
}
