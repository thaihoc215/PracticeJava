package main.giaovu;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import dao.MonHocDAO;
import dao.SinhVienMonHocDAO;

import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import entities.MonHoc;
import entities.SinhVienMonHoc;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import Utils.HeaderRenderer;

import java.util.Date;
import javax.swing.JSpinner;

public class frmKetQuaDiemDanh {

	private JFrame KetQuaDiemDanhView;
	private ArrayList<SinhVienMonHoc> lstSinhVienMonHoc;
	private SinhVienMonHoc svmhSelected = null;
	private ArrayList<SinhVienMonHoc> lstSVUpdate;
	
	private JTable dsMonHocTable;
	private JScrollPane scrDSSVMH;
	protected JTable tbSinhVienMonHoc;
	protected MonHoc monhocSelected;
	private ArrayList<MonHoc> lstMonHoc;
	private JDateChooser dtpDateBegin;
	private JDateChooser dtpDateEnd;
	private JButton btnSua;
	private JSpinner spnTimeBegin;
	private JSpinner spnTimeEnd;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frmKetQuaDiemDanh window = new frmKetQuaDiemDanh();
					window.KetQuaDiemDanhView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmKetQuaDiemDanh() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		KetQuaDiemDanhView = new JFrame();
		KetQuaDiemDanhView.setTitle("Kết Quả Điểm Danh");
		KetQuaDiemDanhView.setBounds(100, 100, 950, 450);
		KetQuaDiemDanhView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		KetQuaDiemDanhView.setLocation((dim.width - KetQuaDiemDanhView.getSize().width) / 2,
				(dim.height - KetQuaDiemDanhView.getSize().height) / 2);
		
		lstMonHoc = (ArrayList<MonHoc>) MonHocDAO.layDanhSachMonHoc();
		lstSVUpdate = new ArrayList<>();
		
		JPanel paneOverview = new JPanel();
		KetQuaDiemDanhView.getContentPane().add(paneOverview, BorderLayout.CENTER);
		paneOverview.setLayout(new BorderLayout(0, 0));
		
		JPanel paneMain = new JPanel();
		paneOverview.add(paneMain, BorderLayout.CENTER);
		
		JPanel paneDSSVMH = new JPanel();
		paneDSSVMH.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DS Sinh Vi\u00EAn Tham Gia M\u00F4n H\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		paneDSSVMH.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("Chọn Môn Học");
		
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
				if(cboMonHoc.getSelectedItem() == null)
				{
					lstSinhVienMonHoc = null;
					tbSinhVienMonHoc = RenderSVMH(lstSinhVienMonHoc);
					scrDSSVMH.setViewportView(tbSinhVienMonHoc);
					return;
				}
					
				monhocSelected = ((MonHoc)cboMonHoc.getSelectedItem());
//				MonHoc mhoc = MonHocDAO.layMonHoc(monhocSelected.getMaMonHoc());
				
				lstSinhVienMonHoc = SinhVienMonHocDAO.layDSSVMHByMon(monhocSelected.getMaMonHoc());
				tbSinhVienMonHoc = RenderSVMH(lstSinhVienMonHoc);
				scrDSSVMH.setViewportView(tbSinhVienMonHoc);
				
				dtpDateBegin.setDate(monhocSelected.getNgayBatDau());
				dtpDateEnd.setDate(monhocSelected.getNgayKetThuc());
				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				try {
					spnTimeBegin.setValue(new Time(format.parse(monhocSelected.getGioBatDau()).getTime()));
					spnTimeEnd.setValue(new Time(format.parse(monhocSelected.getGioKetThuc()).getTime()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				svmhSelected = null;
				lstSVUpdate = new ArrayList<>();
				btnSua.setEnabled(false);
			}
		});
		
		JLabel label_1 = new JLabel("Ngày Bắt Đầu");
		
		dtpDateBegin = new JDateChooser(new Date());
		dtpDateBegin.setDateFormatString("dd-MM-yyyy");
		dtpDateBegin.setEnabled(false);
		
		JLabel label_2 = new JLabel("Ngày Kết Thúc");
		
		dtpDateEnd = new JDateChooser(new Date());
		dtpDateEnd.setDateFormatString("dd-MM-yyyy");
		dtpDateEnd.setEnabled(false);
		
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
		
		JLabel lblNewLabel = new JLabel("Giờ Bắt Đầu");
		
		JLabel lblGiKtThc = new JLabel("Giờ Kết Thúc");
		GroupLayout gl_paneMain = new GroupLayout(paneMain);
		gl_paneMain.setHorizontalGroup(
			gl_paneMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_paneMain.createParallelGroup(Alignment.LEADING)
						.addComponent(paneDSSVMH, GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE)
						.addGroup(gl_paneMain.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cboMonHoc, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addGroup(gl_paneMain.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_paneMain.createSequentialGroup()
									.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(dtpDateEnd, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
								.addGroup(gl_paneMain.createSequentialGroup()
									.addComponent(label_1, GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(dtpDateBegin, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
							.addGap(18)
							.addGroup(gl_paneMain.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_paneMain.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(3))
								.addComponent(lblGiKtThc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_paneMain.createParallelGroup(Alignment.LEADING)
								.addComponent(spnTimeBegin, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
								.addComponent(spnTimeEnd, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
							.addGap(176)))
					.addGap(0))
		);
		gl_paneMain.setVerticalGroup(
			gl_paneMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneMain.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_paneMain.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_paneMain.createSequentialGroup()
							.addGroup(gl_paneMain.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_paneMain.createSequentialGroup()
									.addGroup(gl_paneMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(spnTimeBegin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(15)
									.addGroup(gl_paneMain.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblGiKtThc, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(spnTimeEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_paneMain.createSequentialGroup()
									.addGroup(gl_paneMain.createParallelGroup(Alignment.TRAILING)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
										.addComponent(dtpDateBegin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(15)
									.addGroup(gl_paneMain.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(label_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(dtpDateEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_paneMain.createSequentialGroup()
							.addGroup(gl_paneMain.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(cboMonHoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(22)))
					.addComponent(paneDSSVMH, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
		);
		
		scrDSSVMH = new JScrollPane();
		paneDSSVMH.add(scrDSSVMH, BorderLayout.CENTER);
		
		tbSinhVienMonHoc = new JTable();
		tbSinhVienMonHoc = RenderSVMH(new ArrayList<>());
		scrDSSVMH.setViewportView(tbSinhVienMonHoc);
		
		paneMain.setLayout(gl_paneMain);
		
		JPanel paneButtons = new JPanel();
		paneOverview.add(paneButtons, BorderLayout.SOUTH);
		
		btnSua = new JButton("Lưu");
		btnSua.setEnabled(false);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(SinhVienMonHocDAO.updateDiemDanh(lstSVUpdate)){
					showSuccessMessage("Thay đổi thời khóa biểu thành công", "Thành công");
					lstSVUpdate.clear();
					btnSua.setEnabled(false);
				}
					
				else
					showErrorMessage("Thay đổi thời khóa biểu không thành công", "Lỗi");
			}
		});
		paneButtons.add(btnSua);
		
		JButton btnThoat = new JButton("Thoát");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KetQuaDiemDanhView.dispose();
			}
		});
		paneButtons.add(btnThoat);
	}
	private JTable RenderSVMH(ArrayList<SinhVienMonHoc> lstSVMH) {
		if(lstSVMH == null) lstSVMH = new ArrayList<>();
		// Set column, thuoc tinh cho table
		String[] columnNames = { "Mã Sinh Viên", "Tên Sinh Viên", "Tuần 1", "Tuần 2", "Tuần 3", "Tuần 4"
				,"Tuần 5", "Tuần 6", "Tuần 7", "Tuần 8", "Tuần 9", "Tuần 10", "Tuần 11", "Tuần 12", "Tuần 13"
				,"Tuần 14", "Tuần 15"};
		Object[][] tableData = new Object[lstSVMH.size()][17];
		// Fill table
		int index = 0;
		for (SinhVienMonHoc svmhIte : lstSVMH) {
//			tableData[index][0] = svmhIte.getMaSinhVien();
			tableData[index][0] = svmhIte.getMaSinhVienMonHoc().getMaSinhVien();
			tableData[index][1] = svmhIte.getTenSinhVien();
			tableData[index][2] = svmhIte.isWeek1();
			tableData[index][3] = svmhIte.isWeek2();
			tableData[index][4] = svmhIte.isWeek3();
			tableData[index][5] = svmhIte.isWeek4();
			tableData[index][6] = svmhIte.isWeek5();
			tableData[index][7] = svmhIte.isWeek6();
			tableData[index][8] = svmhIte.isWeek7();
			tableData[index][9] = svmhIte.isWeek8();
			tableData[index][10] = svmhIte.isWeek9();
			tableData[index][11] = svmhIte.isWeek10();
			tableData[index][12] = svmhIte.isWeek11();
			tableData[index][13] = svmhIte.isWeek12();
			tableData[index][14] = svmhIte.isWeek13();
			tableData[index][15] = svmhIte.isWeek14();
			tableData[index][16] = svmhIte.isWeek15();
			index++;
		}

		DefaultTableModel model = new DefaultTableModel(tableData, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// add your code here
				switch(column){
				case 0: case 1: return false;
				default: return true;
				}
			}
			@Override
			public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0: case 1:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
			@Override
			public Object getValueAt(int row, int column) {
				if(column < 0)
					return tableData[row];
				// TODO Auto-generated method stub
				return super.getValueAt(row, column);
			}
			@Override
			public void setValueAt(Object aValue, int row, int column) {
				// TODO Auto-generated method stub
				super.setValueAt(aValue, row, column);
				tableData[row][column] = aValue;
				SinhVienMonHoc svmh = lstSinhVienMonHoc.get(row);
				svmh.setWeek1((Boolean) tableData[row][2]);
				svmh.setWeek2((Boolean) tableData[row][3]);
				svmh.setWeek3((Boolean) tableData[row][4]);
				svmh.setWeek4((Boolean) tableData[row][5]);
				svmh.setWeek5((Boolean) tableData[row][6]);
				svmh.setWeek6((Boolean) tableData[row][7]);
				svmh.setWeek7((Boolean) tableData[row][8]);
				svmh.setWeek8((Boolean) tableData[row][9]);
				svmh.setWeek9((Boolean) tableData[row][10]);
				svmh.setWeek10((Boolean) tableData[row][11]);
				svmh.setWeek11((Boolean) tableData[row][12]);
				svmh.setWeek12((Boolean) tableData[row][13]);
				svmh.setWeek13((Boolean) tableData[row][14]);
				svmh.setWeek14((Boolean) tableData[row][15]);
				svmh.setWeek15((Boolean) tableData[row][16]);
				lstSinhVienMonHoc.set(row, svmh);
				if (lstSVUpdate.size() == 0)
					lstSVUpdate.add(svmh);
				else {
					for (SinhVienMonHoc sv : lstSVUpdate) {
						if (svmh.getMaSinhVienMonHoc().getMaMonHoc() == sv.getMaSinhVienMonHoc().getMaMonHoc() 
								&& svmh.getMaSinhVienMonHoc().getMaSinhVien() == sv.getMaSinhVienMonHoc().getMaSinhVien()) {
							return;
						}
					}
					lstSVUpdate.add(svmh);
				}
				if(lstSVUpdate.size()>0)
					btnSua.setEnabled(true);
			}
		};
		JTable dsSVMHWeek = new JTable(model);
		dsSVMHWeek.setShowVerticalLines(true);
		dsSVMHWeek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dsSVMHWeek.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = dsSVMHWeek.getSelectionModel().getLeadSelectionIndex();
				svmhSelected = new SinhVienMonHoc();
				svmhSelected = lstSinhVienMonHoc.get(row);
				
			}
		};
		dsSVMHWeek.getSelectionModel().addListSelectionListener(listSelectionListener);
		setColumnWidth(dsSVMHWeek, 0, 80);
		setColumnWidth(dsSVMHWeek, 1, 200);
		setColumnWidth(dsSVMHWeek, 2, 50);
		setColumnWidth(dsSVMHWeek, 3, 50);
		setColumnWidth(dsSVMHWeek, 4, 50);
		setColumnWidth(dsSVMHWeek, 5, 50);
		setColumnWidth(dsSVMHWeek, 6, 50);
		setColumnWidth(dsSVMHWeek, 7, 50);
		setColumnWidth(dsSVMHWeek, 8, 50);
		setColumnWidth(dsSVMHWeek, 9, 50);
		setColumnWidth(dsSVMHWeek, 10, 50);
		setColumnWidth(dsSVMHWeek, 11, 50);
		setColumnWidth(dsSVMHWeek, 12, 50);
		setColumnWidth(dsSVMHWeek, 13, 50);
		setColumnWidth(dsSVMHWeek, 14, 50);
		setColumnWidth(dsSVMHWeek, 15, 50);

		JTableHeader header = dsSVMHWeek.getTableHeader();
		header.setDefaultRenderer(new HeaderRenderer(dsSVMHWeek));
		return dsSVMHWeek;
	}
	private void setColumnWidth(JTable table, int column, int width) {
		TableColumn tbColumn = table.getColumnModel().getColumn(column);
		tbColumn.setPreferredWidth(width);
	}
	
	private void showSuccessMessage(String successMes, String successTit) {
		JOptionPane.showMessageDialog(this.KetQuaDiemDanhView, successMes, successTit, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void showErrorMessage(String errorMessage, String errorTitle) {
		JOptionPane.showMessageDialog(this.KetQuaDiemDanhView, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}
}
