package main.giaovu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Utils.Commons;
import dao.MonHocDAO;
import dao.NguoiDungDAO;
import dao.SinhVienMonHocDAO;
import entities.MonHoc;
import entities.NguoiDung;
import entities.SinhVienMonHoc;
import sun.misc.SignalHandler;

public class frmThemSinhVienMonHoc {

	private JFrame themSinhVienMHView;
	private JTable tbDSSVMH;
	private JTable tbDSSV;
	
	private JTextField txtSearchHS;
	private JScrollPane scrDSSVMH;
	private JSpinner spnTimeBegin;
	private JSpinner spnTimeEnd;
	private JScrollPane scrDSSV;
	
	//Class variables
	ArrayList<MonHoc> lstMonHoc;
	private MonHoc monhocSelected = null;
	ArrayList<SinhVienMonHoc> lstSinhVienMonHoc;
	private SinhVienMonHoc svmhSelected = null;
	ArrayList<NguoiDung> lstSinhVien;
	private NguoiDung svSelected = null;

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frmThemSinhVienMonHoc window = new frmThemSinhVienMonHoc();
					window.themSinhVienMHView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public frmThemSinhVienMonHoc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		lstMonHoc = (ArrayList<MonHoc>) MonHocDAO.layDanhSachMonHoc();
//		lstSinhVien = NguoiDungDAO.layDanhSachSinhVien();

		themSinhVienMHView = new JFrame();
		themSinhVienMHView.setTitle("Thêm Học Sinh Vào Môn Học");
		themSinhVienMHView.setBounds(100, 100, 595, 426);
		themSinhVienMHView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		themSinhVienMHView.setLocation((dim.width - themSinhVienMHView.getSize().width) / 2,
				(dim.height - themSinhVienMHView.getSize().height) / 2);
		
		JPanel mainPane = new JPanel();
		themSinhVienMHView.getContentPane().add(mainPane, BorderLayout.CENTER);
		mainPane.setLayout(new BorderLayout(0, 0));
		
		JPanel procesPane = new JPanel();
		mainPane.add(procesPane, BorderLayout.CENTER);
		
		JLabel lblChnMnHc = new JLabel("Chọn Môn Học");
		
		JComboBox<MonHoc> cboMonHoc = new JComboBox<MonHoc>();
		cboMonHoc.setMaximumRowCount(5);
		cboMonHoc.addItem(null);
		if (lstMonHoc != null)
			for (MonHoc monHoc : lstMonHoc) {
				cboMonHoc.addItem(monHoc);
			}
		cboMonHoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				monhocSelected = ((MonHoc)cboMonHoc.getSelectedItem());
				if(monhocSelected == null){
					lstSinhVien = null;
					lstSinhVienMonHoc = null;
					tbDSSV = RenderDSSV(lstSinhVien);
					tbDSSVMH = RenderSVMH(lstSinhVienMonHoc);
					scrDSSV.setViewportView(tbDSSV);
					scrDSSVMH.setViewportView(tbDSSVMH);
					return;
				}
				//get list sinh vien theo mon hoc
				lstSinhVienMonHoc = SinhVienMonHocDAO.layDSSVMHByMon(monhocSelected.getMaMonHoc());
				tbDSSVMH = RenderSVMH(lstSinhVienMonHoc);
				scrDSSVMH.setViewportView(tbDSSVMH);
				
				//Get list sinh vien ngoai tru sinh vien da co trong mon hoc
				lstSinhVien = SinhVienMonHocDAO.laySinhVienNotInMon(monhocSelected.getMaMonHoc());
				tbDSSV = RenderDSSV(lstSinhVien);
				scrDSSV.setViewportView(tbDSSV);
				
				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				try {
					spnTimeBegin.setValue(new Time(format.parse(monhocSelected.getGioBatDau()).getTime()));
					spnTimeEnd.setValue(new Time(format.parse(monhocSelected.getGioKetThuc()).getTime()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				svSelected = null;
				svmhSelected = null;
				
//				spnTimeBegin.setValue(mhoc.getGioBatDau());
//				spnTimeEnd.setValue(mhoc.getGioKetThuc());
			}
		});
		
		JPanel sinhVienMonHocPane = new JPanel();
		sinhVienMonHocPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DS Sinh Vi\u00EAn Tham Gia M\u00F4n H\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel sinhVienPane = new JPanel();
		sinhVienPane.setBorder(new TitledBorder(null, "Danh S\u00E1ch Sinh Vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblGioHoc = new JLabel("Giờ Học");
		
		spnTimeBegin = new JSpinner();
		spnTimeBegin.setEnabled(false);
		spnTimeBegin.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnTimeBegin = new JSpinner.DateEditor(spnTimeBegin, "HH:mm:ss");
		spnTimeBegin.setEditor(de_spnTimeBegin);
		
		spnTimeEnd = new JSpinner();
		spnTimeEnd.setEnabled(false);
		spnTimeEnd.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnTimeEnd = new JSpinner.DateEditor(spnTimeEnd, "HH:mm:ss");
		spnTimeEnd.setEditor(de_spnTimeEnd);
		
		JLabel lbln = new JLabel("Đến");
		
		JLabel lblNewLabel = new JLabel("Tên Sinh Viên");
		
		txtSearchHS = new JTextField();
		txtSearchHS.setColumns(10);
		
		JButton btnTmKim = new JButton("Tìm Kiếm");
		btnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstSinhVien = SinhVienMonHocDAO.timKiemSinhVienNotInMon(monhocSelected.getMaMonHoc(),txtSearchHS.getText());
				if (lstSinhVien == null || lstSinhVien.size() == 0) {
					Commons.showErrorMessage(themSinhVienMHView,"Không tìm thấy sinh viên", "Lỗi");
					lstSinhVien = null;
				}
				tbDSSV = RenderDSSV(lstSinhVien);
				scrDSSV.setViewportView(tbDSSV);
				
				svSelected = null;
				svmhSelected = null;
			}
		});
		
		JButton btnXoa = new JButton("Xóa Khỏi Môn Học");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(svmhSelected == null){
					Commons.showErrorMessage(themSinhVienMHView,"Vui lòng chọn sinh viên", "Lỗi");
					return;
				}
				
				NguoiDung svienDel = NguoiDungDAO.layNguoiDung(svmhSelected.getMaSinhVienMonHoc().getMaSinhVien());
				if(SinhVienMonHocDAO.xoaSinhVienMonHoc(monhocSelected, svienDel)){
					Commons.showSuccessMessage(themSinhVienMHView,"Xóa sinh viên khỏi môn học thành công", "Thành Công");
					lstSinhVienMonHoc = SinhVienMonHocDAO.layDSSVMHByMon(monhocSelected.getMaMonHoc());
					tbDSSVMH = RenderSVMH(lstSinhVienMonHoc);
					scrDSSVMH.setViewportView(tbDSSVMH);
					
					lstSinhVien = SinhVienMonHocDAO.laySinhVienNotInMon(monhocSelected.getMaMonHoc());
					tbDSSV = RenderDSSV(lstSinhVien);
					scrDSSV.setViewportView(tbDSSV);
				}else
					Commons.showErrorMessage(themSinhVienMHView,"Xóa sinh viên vào môn học thất bại", "Lỗi");
				
			}
		});
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(svSelected == null){
					Commons.showErrorMessage(themSinhVienMHView,"Vui lòng chọn sinh viên", "Lỗi");
					return;
				}
				if(SinhVienMonHocDAO.themSinhVienMonHoc(monhocSelected, svSelected)){
					Commons.showSuccessMessage(themSinhVienMHView,"Thêm sinh viên vào môn học thành công", "Thành Công");
					lstSinhVienMonHoc = SinhVienMonHocDAO.layDSSVMHByMon(monhocSelected.getMaMonHoc());
					tbDSSVMH = RenderSVMH(lstSinhVienMonHoc);
					scrDSSVMH.setViewportView(tbDSSVMH);
					
					lstSinhVien = SinhVienMonHocDAO.laySinhVienNotInMon(monhocSelected.getMaMonHoc());
					tbDSSV = RenderDSSV(lstSinhVien);
					scrDSSV.setViewportView(tbDSSV);
				}else
					Commons.showErrorMessage(themSinhVienMHView,"Thêm sinh viên vào môn học thất bại", "Lỗi");
				
			}
		});
		
		JButton btnThot = new JButton("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themSinhVienMHView.dispose();
			}
		});
		
		JButton btnImport = new JButton("Chọn File");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmImportSVMH frm = new frmImportSVMH();
				frm.setVisible(true);
				if (frm.lstSvAdd.size() <= 0) {
					return;
				}
				for (NguoiDung nd : frm.lstSvAdd) {
					SinhVienMonHocDAO.themSinhVienMonHoc(monhocSelected, nd);
				}
				lstSinhVienMonHoc = SinhVienMonHocDAO.layDSSVMHByMon(monhocSelected.getMaMonHoc());
				tbDSSVMH = RenderSVMH(lstSinhVienMonHoc);
				scrDSSVMH.setViewportView(tbDSSVMH);
				
				lstSinhVien = SinhVienMonHocDAO.laySinhVienNotInMon(monhocSelected.getMaMonHoc());
				tbDSSV = RenderDSSV(lstSinhVien);
				scrDSSV.setViewportView(tbDSSV);
			
			}
		});
		
		
		GroupLayout gl_procesPane = new GroupLayout(procesPane);
		gl_procesPane.setHorizontalGroup(
			gl_procesPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_procesPane.createSequentialGroup()
					.addGroup(gl_procesPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_procesPane.createSequentialGroup()
							.addGroup(gl_procesPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_procesPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_procesPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(sinhVienMonHocPane, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
										.addGroup(gl_procesPane.createSequentialGroup()
											.addComponent(spnTimeBegin, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lbln)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(spnTimeEnd, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
											.addGap(14))
										.addGroup(gl_procesPane.createSequentialGroup()
											.addComponent(lblChnMnHc)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(cboMonHoc, 0, 188, Short.MAX_VALUE)))
									.addGap(18))
								.addGroup(gl_procesPane.createSequentialGroup()
									.addGap(121)
									.addComponent(lblGioHoc, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_procesPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(sinhVienPane, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_procesPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtSearchHS)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnTmKim))))
						.addGroup(gl_procesPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnXoa)
							.addPreferredGap(ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
							.addComponent(btnImport)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnThot)))
					.addContainerGap())
		);
		gl_procesPane.setVerticalGroup(
			gl_procesPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_procesPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_procesPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblChnMnHc)
						.addComponent(cboMonHoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(txtSearchHS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTmKim))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_procesPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_procesPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(sinhVienPane, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
							.addComponent(lblGioHoc))
						.addGroup(gl_procesPane.createSequentialGroup()
							.addGap(20)
							.addGroup(gl_procesPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(spnTimeEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbln)
								.addComponent(spnTimeBegin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(sinhVienMonHocPane, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_procesPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnThot)
						.addComponent(btnXoa)
						.addComponent(btnImport))
					.addContainerGap())
		);
		sinhVienPane.setLayout(new BorderLayout(0, 0));
		
		scrDSSV = new JScrollPane();
		sinhVienPane.add(scrDSSV);
		
		tbDSSV = new JTable();
		tbDSSV = RenderDSSV(lstSinhVien);
		scrDSSV.setViewportView(tbDSSV);
		sinhVienMonHocPane.setLayout(new BorderLayout(0, 0));
		
		scrDSSVMH = new JScrollPane();
		sinhVienMonHocPane.add(scrDSSVMH, BorderLayout.CENTER);
		
		tbDSSVMH = new JTable();
		tbDSSVMH = RenderSVMH(new ArrayList<>());
		scrDSSVMH.setViewportView(tbDSSVMH);
		procesPane.setLayout(gl_procesPane);
	}
	/**
	 * Ve bang danh sach sinh vien cua mon hoc
	 * @param lstSVMH
	 * @return
	 */
	private JTable RenderSVMH(ArrayList<SinhVienMonHoc> lstSVMH) {
		if(lstSVMH == null) lstSVMH = new ArrayList<>();
		// Set column, thuoc tinh cho table
		String[] columnNames = { "Mã Sinh Viên", "Tên Sinh Viên" };
		Object[][] tableData = new Object[lstSVMH.size()][2];
		// Fill table
		int index = 0;
		for (SinhVienMonHoc mh : lstSVMH) {
			tableData[index][0] = mh.getMaSinhVienMonHoc().getMaSinhVien();
			tableData[index][1] = mh.getTenSinhVien();
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
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = dsMonHocTable.getSelectionModel().getLeadSelectionIndex();
				svmhSelected = new SinhVienMonHoc();
				svmhSelected = lstSinhVienMonHoc.get(row);
			}
		};
		dsMonHocTable.getSelectionModel().addListSelectionListener(listSelectionListener);
		//// setColumnWidth(table,0,300);
		// setColumnWidth(table,1,150);
		// setColumnWidth(table,2,50);
		// setColumnWidth(table,3,250);
		return dsMonHocTable;
	}

	/**
	 * Ve bang danh sach tong hop sinh vien
	 * @param lstSV
	 * @return
	 */
	private JTable RenderDSSV(ArrayList<NguoiDung> lstSV) {
		if(lstSV == null) lstSV = new ArrayList<>();
		// Set column, thuoc tinh cho table
		String[] columnNames = { "Mã Sinh Viên", "Tên Sinh Viên"};
		Object[][] tableData = new Object[lstSV.size()][2];
		// Fill table
		int index = 0;
		for (NguoiDung mh : lstSV) {
			tableData[index][0] = mh.getMaNguoiDung();
			tableData[index][1] = mh.getTenNguoiDung();
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
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = dsMonHocTable.getSelectionModel().getLeadSelectionIndex();
				svSelected = new NguoiDung();
				svSelected = lstSinhVien.get(row);
			}
		};
		dsMonHocTable.getSelectionModel().addListSelectionListener(listSelectionListener);
		//	setColumnWidth(dsMonHocTable,0,100);
		return dsMonHocTable;
	}
}
