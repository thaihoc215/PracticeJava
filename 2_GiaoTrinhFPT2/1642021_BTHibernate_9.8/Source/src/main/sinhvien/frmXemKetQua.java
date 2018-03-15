package main.sinhvien;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import Utils.HeaderRenderer;
import dao.MonHocDAO;
import dao.SinhVienMonHocDAO;
import entities.MonHoc;
import entities.NguoiDung;
import entities.SinhVienMonHoc;

public class frmXemKetQua {

	private JFrame xemKetQuaDiemDanhView;
	private JTable tbDSMH;
	private JTextField txtTimMonHoc;
	private JScrollPane scrDSMH;

	private NguoiDung sinhVienItem;
	private ArrayList<SinhVienMonHoc> lstMHBySV;
	private JDateChooser dtpDateEnd;
	private JDateChooser dtpDateBegin;
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
					frmXemKetQua window = new frmXemKetQua(new NguoiDung());
					window.xemKetQuaDiemDanhView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmXemKetQua(NguoiDung ndLogin) {
		sinhVienItem = ndLogin;
		initialize();
	}

	public void show(){
		xemKetQuaDiemDanhView.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		xemKetQuaDiemDanhView = new JFrame();
		xemKetQuaDiemDanhView.setTitle("Káº¿t Quáº£ Ä�iá»ƒm Danh Sinh ViÃªn");
		xemKetQuaDiemDanhView.setBounds(100, 100, 950, 450);
		xemKetQuaDiemDanhView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		xemKetQuaDiemDanhView.setLocation((dim.width - xemKetQuaDiemDanhView.getSize().width) / 2,
				(dim.height - xemKetQuaDiemDanhView.getSize().height) / 2);
		
		JPanel mainPane = new JPanel();
		xemKetQuaDiemDanhView.getContentPane().add(mainPane, BorderLayout.CENTER);
		mainPane.setLayout(new BorderLayout(0, 0));
		
		JPanel paneInfo = new JPanel();
		
		JPanel paneDSMH = new JPanel();
		paneDSMH.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DS M\u00F4n H\u1ECDc Tham Gia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		paneDSMH.setLayout(new BorderLayout(0, 0));
		
		
		
		JLabel label_2 = new JLabel("NgÃ y Báº¯t Ä�áº§u");

		dtpDateBegin = new JDateChooser(new Date());
		dtpDateBegin.setEnabled(false);
		dtpDateBegin.setDateFormatString("dd-MM-yyyy");
		JLabel label_1 = new JLabel("NgÃ y Káº¿t ThÃºc");
		dtpDateEnd = new JDateChooser(new Date());
		dtpDateEnd.setEnabled(false);
		dtpDateEnd.setDateFormatString("dd-MM-yyyy");
		JLabel label_3 = new JLabel("Giá»� Báº¯t Ä�áº§u");

		JLabel label_4 = new JLabel("Giá»� Káº¿t ThÃºc");
		
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "T\u00ECm Ki\u1EBFm M\u00F4n H\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_paneInfo = new GroupLayout(paneInfo);
		gl_paneInfo.setHorizontalGroup(
			gl_paneInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneInfo.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_paneInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(paneDSMH, GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
						.addGroup(gl_paneInfo.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addGroup(gl_paneInfo.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
							.addGap(38)
							.addGroup(gl_paneInfo.createParallelGroup(Alignment.TRAILING)
								.addComponent(dtpDateEnd, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
								.addComponent(dtpDateBegin, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_paneInfo.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_paneInfo.createSequentialGroup()
									.addComponent(label_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(3))
								.addComponent(label_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_paneInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(spnTimeBegin, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
								.addComponent(spnTimeEnd, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_paneInfo.setVerticalGroup(
			gl_paneInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paneInfo.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_paneInfo.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_paneInfo.createSequentialGroup()
							.addGroup(gl_paneInfo.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(dtpDateBegin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(gl_paneInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(dtpDateEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_paneInfo.createSequentialGroup()
							.addGroup(gl_paneInfo.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(spnTimeBegin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(15)
							.addGroup(gl_paneInfo.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(spnTimeEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(paneDSMH, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
		);
		
		JLabel lblTnMnHc = new JLabel("TÃªn MÃ´n Há»�c");
		
		txtTimMonHoc = new JTextField();
		txtTimMonHoc.setColumns(10);
		
		JButton btnNewButton = new JButton("TÃ¬m");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstMHBySV = SinhVienMonHocDAO.timKiemMonHocBySV(sinhVienItem.getMaNguoiDung(),txtTimMonHoc.getText());
				tbDSMH = RenderSVMH(lstMHBySV);		
				scrDSMH.setViewportView(tbDSMH);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblTnMnHc)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtTimMonHoc, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(txtTimMonHoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTnMnHc))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		scrDSMH = new JScrollPane();
		paneDSMH.add(scrDSMH, BorderLayout.CENTER);
		
		lstMHBySV = SinhVienMonHocDAO.layDSMHBySV(sinhVienItem.getMaNguoiDung());
		tbDSMH = new JTable();
		tbDSMH = RenderSVMH(lstMHBySV);		
		scrDSMH.setViewportView(tbDSMH);
		paneInfo.setLayout(gl_paneInfo);
		mainPane.add(paneInfo);
		
		JPanel paneExit = new JPanel();
		mainPane.add(paneExit, BorderLayout.SOUTH);
		
		JButton button_1 = new JButton("ThoÃ¡t");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xemKetQuaDiemDanhView.dispose();
			}
		});
		paneExit.setLayout(new BorderLayout(0, 0));
		paneExit.add(button_1, BorderLayout.EAST);
		
		JLabel lblTenNguoiDung = new JLabel(sinhVienItem.getTenNguoiDung());
		paneExit.add(lblTenNguoiDung, BorderLayout.WEST);
	}
	
	private JTable RenderSVMH(ArrayList<SinhVienMonHoc> lstSVMH) {
		if(lstSVMH == null) lstSVMH = new ArrayList<>();
		// Set column, thuoc tinh cho table
		String[] columnNames = { "MÃ£", "TÃªn MÃ´n Há»�c", "Tuáº§n 1", "Tuáº§n 2", "Tuáº§n 3", "Tuáº§n 4"
				,"Tuáº§n 5", "Tuáº§n 6", "Tuáº§n 7", "Tuáº§n 8", "Tuáº§n 9", "Tuáº§n 10", "Tuáº§n 11", "Tuáº§n 12", "Tuáº§n 13"
				,"Tuáº§n 14", "Tuáº§n 15"};
		Object[][] tableData = new Object[lstSVMH.size()][17];
		// Fill table
		int index = 0;
		for (SinhVienMonHoc svmhIte : lstSVMH) {
			tableData[index][0] = svmhIte.getMaSinhVienMonHoc().getMaMonHoc();
			tableData[index][1] = svmhIte.getTenMonHoc();
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
				return false;
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
		};
		JTable dsSVMHWeek = new JTable(model);
		dsSVMHWeek.setShowVerticalLines(true);
		dsSVMHWeek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dsSVMHWeek.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		setColumnWidth(dsSVMHWeek, 0, 50);
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

		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {
				int row = dsSVMHWeek.getSelectionModel().getLeadSelectionIndex();
				int maMHSelected = lstMHBySV.get(row).getMaSinhVienMonHoc().getMaMonHoc();
				MonHoc mhSelected = MonHocDAO.layMonHoc(maMHSelected);
				dtpDateBegin.setDate(mhSelected.getNgayBatDau());
				dtpDateEnd.setDate(mhSelected.getNgayKetThuc());
				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
				try {
					spnTimeBegin.setValue(new Time(format.parse(mhSelected.getGioBatDau()).getTime()));
					spnTimeEnd.setValue(new Time(format.parse(mhSelected.getGioKetThuc()).getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		};
		dsSVMHWeek.getSelectionModel().addListSelectionListener(listSelectionListener);
		
		//align center cho header
		JTableHeader header = dsSVMHWeek.getTableHeader();
		header.setDefaultRenderer(new HeaderRenderer(dsSVMHWeek));
		return dsSVMHWeek;
	}
	
	private void setColumnWidth(JTable table, int column, int width) {
		TableColumn tbColumn = table.getColumnModel().getColumn(column);
		tbColumn.setPreferredWidth(width);
	}
}
