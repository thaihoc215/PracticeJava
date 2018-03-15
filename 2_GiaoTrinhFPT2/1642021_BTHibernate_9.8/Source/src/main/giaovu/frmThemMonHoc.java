package main.giaovu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultComboBoxModel;
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

import com.toedter.calendar.JDateChooser;

import Utils.Commons;
import dao.MonHocDAO;
import entities.MonHoc;

public class frmThemMonHoc {

	private JFrame themMonHocView;
	private JTextField txtTenMonHoc;
	private JTextField txtTenPhongHoc;
	private JTable tableDSMonHoc;

	//Class variables
	private MonHoc mhSelected = null;
	private List<MonHoc> dsMonHoc;
	protected JScrollPane scrDsMonHoc;
	private JDateChooser dtpNgayBatDau;
	private JDateChooser dtpNgayKetThuc;
	private JComboBox<ArrayList<String>> cboThuTrongTuan;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frmThemMonHoc window = new frmThemMonHoc();
					window.themMonHocView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmThemMonHoc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		dsMonHoc = MonHocDAO.layDanhSachMonHoc();
		
		themMonHocView = new JFrame();
		themMonHocView.setTitle("Thêm Môn Học");
		themMonHocView.setBounds(100, 100, 616, 484);
		themMonHocView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		themMonHocView.setLocation((dim.width - themMonHocView.getSize().width) / 2,
				(dim.height - themMonHocView.getSize().height) / 2);
		
		JPanel mainPane = new JPanel();
		themMonHocView.getContentPane().add(mainPane, BorderLayout.CENTER);
		
		JPanel infoPane = new JPanel();
		infoPane.setBorder(new TitledBorder(null, "Th\u00F4ng Tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		JLabel lblTnMnHc = new JLabel("Tên Môn Học");
		
		JLabel lblNgyBtu = new JLabel("Ngày Bắt Đầu");
		
		JLabel lblNgyKtThc = new JLabel("Ngày Kết Thúc");
		
		JLabel lblGiBtu = new JLabel("Giờ Bắt Đầu");
		
		JLabel lblGiKtThc = new JLabel("Giờ Kết Thúc");
		
		JLabel lblPhngHc = new JLabel("Phòng Học");
		
		JLabel lblTh = new JLabel("Thứ");
		
		txtTenMonHoc = new JTextField();
		txtTenMonHoc.setColumns(10);
		
		dtpNgayBatDau = new JDateChooser(new Date());
		dtpNgayBatDau.setDateFormatString("dd-MM-yyyy");
		dtpNgayBatDau.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				Calendar c = Calendar.getInstance();
				c.setTime(dtpNgayBatDau.getDate()); // Now use today date.
				c.add(Calendar.DATE, 105); // Adding 5 days
				dtpNgayKetThuc.setDate(c.getTime());
				int dayOfWeek = dtpNgayKetThuc.getDate().getDay();
				cboThuTrongTuan.setSelectedIndex(dayOfWeek);
			}
		});
		dtpNgayKetThuc = new JDateChooser(new Date());
		dtpNgayKetThuc.setDateFormatString("dd-MM-yyyy");
		dtpNgayKetThuc.setEnabled(false);
		
		txtTenPhongHoc = new JTextField();
		txtTenPhongHoc.setColumns(10);
		
		cboThuTrongTuan = new JComboBox<ArrayList<String>>();
		cboThuTrongTuan.setModel(new DefaultComboBoxModel(Commons.dateOfWeek.toArray()));
		cboThuTrongTuan.setEnabled(false);
		JSpinner spnTimeBegin = new JSpinner();
		spnTimeBegin.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnTimeBegin = new JSpinner.DateEditor(spnTimeBegin, "HH:mm:ss");
		spnTimeBegin.setEditor(de_spnTimeBegin);
		
		JSpinner spnTimeEnd = new JSpinner();
		spnTimeEnd.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor de_spnTimeEnd = new JSpinner.DateEditor(spnTimeEnd, "HH:mm:ss");
		spnTimeEnd.setEditor(de_spnTimeEnd);
		
		GroupLayout gl_infoPane = new GroupLayout(infoPane);
		gl_infoPane.setHorizontalGroup(
			gl_infoPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_infoPane.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_infoPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_infoPane.createSequentialGroup()
							.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNgyBtu)
								.addComponent(lblTnMnHc))
							.addGap(21))
						.addGroup(gl_infoPane.createSequentialGroup()
							.addComponent(lblNgyKtThc)
							.addGap(18)))
					.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(dtpNgayKetThuc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dtpNgayBatDau, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtTenMonHoc, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblTh)
							.addComponent(lblGiBtu, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addComponent(lblGiKtThc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblPhngHc, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING)
						.addComponent(spnTimeEnd, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
						.addComponent(cboThuTrongTuan, 0, 165, Short.MAX_VALUE)
						.addComponent(txtTenPhongHoc, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
						.addComponent(spnTimeBegin, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
					.addGap(26))
		);
		gl_infoPane.setVerticalGroup(
			gl_infoPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_infoPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_infoPane.createSequentialGroup()
							.addComponent(cboThuTrongTuan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_infoPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(spnTimeBegin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGiBtu))
							.addGap(18)
							.addGroup(gl_infoPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(spnTimeEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGiKtThc))
							.addGap(18)
							.addGroup(gl_infoPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtTenPhongHoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhngHc)))
						.addGroup(gl_infoPane.createSequentialGroup()
							.addGroup(gl_infoPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTnMnHc)
								.addComponent(txtTenMonHoc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTh))
							.addGap(18)
							.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dtpNgayBatDau, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNgyBtu))
							.addGap(18)
							.addGroup(gl_infoPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNgyKtThc)
								.addComponent(dtpNgayKetThuc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		infoPane.setLayout(gl_infoPane);
		
		JPanel defaultPane = new JPanel();
		defaultPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themMonHocView.dispose();
			}
		});
		
		JButton btnThemMonHoc = new JButton("Thêm");
		btnThemMonHoc.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if(txtTenMonHoc.getText() == ""){
					showErrorMessage("Nhập tên môn học", "Lỗi");
					return;
				}
				String timeBegin = new SimpleDateFormat("HH:mm:ss").format(spnTimeBegin.getValue());
				String timeEnd = new SimpleDateFormat("HH:mm:ss").format(spnTimeEnd.getValue());
				MonHoc monHoc = new MonHoc(0, txtTenMonHoc.getText(),
						dtpNgayBatDau.getDate(), dtpNgayKetThuc.getDate(),
						timeBegin,timeEnd,
						cboThuTrongTuan.getSelectedIndex()+1,txtTenPhongHoc.getText());
				long days = TimeUnit.DAYS.convert(dtpNgayKetThuc.getDate().getTime() - dtpNgayBatDau.getDate().getTime(), TimeUnit.MILLISECONDS);
				if(days < Utils.Commons.fifteenWeeks) {
					showErrorMessage("Môn học phải kéo dài ít nhất 15 tuần", "Lỗi");
					return;
				}
				try {
					Time t1 = new Time((new SimpleDateFormat("HH:mm:ss").parse(timeBegin).getTime()));
					Time t2 = new Time((new SimpleDateFormat("HH:mm:ss").parse(timeEnd).getTime()));
					if(t1.getTime() >= t2.getTime()) {
						showErrorMessage("Thời gian kết thúc phải sau thời gian bắt đầu", "Lỗi");
						return;
						}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (MonHocDAO.themMonHoc(monHoc)) {
					showSuccessMessage("Thêm môn học thành công", "Thành công");
					dsMonHoc = MonHocDAO.layDanhSachMonHoc();
					tableDSMonHoc = RenderTable((ArrayList<MonHoc>) dsMonHoc);
					scrDsMonHoc.setViewportView(tableDSMonHoc);
				}
				else
					showErrorMessage("Thêm môn học thất bại", "Lỗi");
			}
		});
		defaultPane.add(btnThemMonHoc);
		defaultPane.add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Danh S\u00E1ch M\u00F4n H\u1ECDc", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_mainPane = new GroupLayout(mainPane);
		gl_mainPane.setHorizontalGroup(
			gl_mainPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addGap(189)
					.addComponent(defaultPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(183))
				.addGroup(Alignment.LEADING, gl_mainPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_mainPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(infoPane, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_mainPane.setVerticalGroup(
			gl_mainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mainPane.createSequentialGroup()
					.addGap(5)
					.addComponent(infoPane, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(defaultPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
					.addGap(10))
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		scrDsMonHoc = new JScrollPane();
		panel.add(scrDsMonHoc, BorderLayout.CENTER);
		
		tableDSMonHoc = new JTable();
		tableDSMonHoc = RenderTable((ArrayList<MonHoc>) dsMonHoc);
		scrDsMonHoc.setViewportView(tableDSMonHoc);
		mainPane.setLayout(gl_mainPane);
	}
	
	/**
	 * 
	 * @param lstMonHoc
	 * @return
	 */
	private JTable RenderTable(ArrayList<MonHoc> lstMonHoc) {
		// Set column, thuoc tinh cho table
		String[] columnNames = { "Mã MH", "Tên MH","Giờ Bắt Đầu", "Giờ Kết Thúc", "Ngày Học", "Ngày Kết Thúc","Thứ", "Phòng Học"};
		Object[][] tableData = new Object[lstMonHoc.size()][8];
		// Fill table
		int index = 0;
		for (MonHoc mh : lstMonHoc) {
			tableData[index][0] = mh.getMaMonHoc();
			tableData[index][1] = mh.getTenMonHoc();
			tableData[index][2] = mh.getGioBatDau();
			tableData[index][3] = mh.getGioKetThuc();
			tableData[index][4] = mh.getNgayBatDau().toString();
			tableData[index][5] = mh.getNgayKetThuc().toString();
			tableData[index][6] = Commons.dateOfWeek.get(mh.getThuTrongTuan()-1);
			tableData[index][7] = mh.getTenPhongHoc();
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
				mhSelected = new MonHoc();
				mhSelected = lstMonHoc.get(row);
			}
		};
		dsMonHocTable.getSelectionModel().addListSelectionListener(listSelectionListener);
////	setColumnWidth(table,0,300);
//		setColumnWidth(table,1,150);
//		setColumnWidth(table,2,50);
//		setColumnWidth(table,3,250);
		return dsMonHocTable;
	}
	
	private void showSuccessMessage(String successMes, String successTit) {
		JOptionPane.showMessageDialog(this.themMonHocView, successMes, successTit, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void showErrorMessage(String errorMessage, String errorTitle) {
		JOptionPane.showMessageDialog(this.themMonHocView, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}
}
