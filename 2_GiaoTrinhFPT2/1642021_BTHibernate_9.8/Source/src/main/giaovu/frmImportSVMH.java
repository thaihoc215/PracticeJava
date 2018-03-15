package main.giaovu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import Utils.HeaderRenderer;
import dao.NguoiDungDAO;
import dao.SinhVienMonHocDAO;
import entities.NguoiDung;
import entities.SinhVienMonHoc;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmImportSVMH extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tbDSSV;
	private ArrayList<NguoiDung> lstFromCSV;
	public ArrayList<NguoiDung> lstSvAdd;
	private ArrayList<Boolean> isAddSv;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			frmImportSVMH dialog = new frmImportSVMH();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public frmImportSVMH() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		lstFromCSV = new ArrayList<>();
		lstSvAdd = new ArrayList<>();
		String csvFile = "";
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "csv"));
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			csvFile = selectedFile.getPath();
		} else {
			dispose();
			return;
		}
		
		setModal(true);
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "utf8"));
			br.readLine();
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] sinhvien = line.split(cvsSplitBy);
//				NguoiDung nd = new NguoiDung();
//				nd.setMaNguoiDung(Integer.parseInt(sinhvien[0]));
				int mand = Integer.parseInt(sinhvien[0]);
				NguoiDung ngSelect = null;
				ngSelect = NguoiDungDAO.layNguoiDung(mand);
				if (ngSelect != null && ngSelect.getLoaiNguoiDung() == 2)
					lstFromCSV.add(ngSelect);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		setTitle("Thêm Danh Sách Sinh Viên Vào Môn Học");
		setBounds(100, 100, 495, 339);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((dim.width - this.getSize().width) / 2,
				(dim.height - this.getSize().height) / 2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Danh S\u00E1ch Sinh Vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setLayout(new BorderLayout(0, 0));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
		);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tbDSSV = new JTable();
		tbDSSV = RenderSVMH(lstFromCSV);
		scrollPane.setViewportView(tbDSSV);
		
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnThem = new JButton("Thêm");
				btnThem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						for (int i = 0; i < lstFromCSV.size(); i++) {
							if(isAddSv.get(i))
								lstSvAdd.add(lstFromCSV.get(i));
						}
						dispose();
					}
				});
				btnThem.setActionCommand("OK");
				buttonPane.add(btnThem);
				getRootPane().setDefaultButton(btnThem);
			}
			{
				JButton btnThoat = new JButton("Thoát");
				btnThoat.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnThoat.setActionCommand("Cancel");
				buttonPane.add(btnThoat);
			}
		}
	}
	
	private JTable RenderSVMH(ArrayList<NguoiDung> lstSVMH) {
		if(lstSVMH == null) lstSVMH = new ArrayList<>();
		isAddSv = new ArrayList<>(); 
		// Set column, thuoc tinh cho table
		String[] columnNames = { "Chọn", "Mã", "Họ Tên", "Tên Đăng Nhập",};
		Object[][] tableData = new Object[lstSVMH.size()][4];
		// Fill table
		int index = 0;
		for (NguoiDung svmhIte : lstSVMH) {
			isAddSv.add(false);
			tableData[index][0] = false;
			tableData[index][1] = svmhIte.getMaNguoiDung();
			tableData[index][2] = svmhIte.getTenNguoiDung();
			tableData[index][3] = svmhIte.getTenDangNhap();
			index++;
		}

		DefaultTableModel model = new DefaultTableModel(tableData, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				switch (column) {
                case 0:
                    return true;
                default:
                    return false;
            }
			}
			@Override
			public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
			@Override
			public void setValueAt(Object aValue, int row, int column) {
				// TODO Auto-generated method stub
				super.setValueAt(aValue, row, column);
				tableData[row][column] = aValue;
				isAddSv.set(row, (Boolean) aValue);
			}
		};
		JTable dsSVMHWeek = new JTable(model);
		dsSVMHWeek.setShowVerticalLines(true);
		dsSVMHWeek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dsSVMHWeek.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	
		setColumnWidth(dsSVMHWeek, 0, 50);
		setColumnWidth(dsSVMHWeek, 1, 50);
		setColumnWidth(dsSVMHWeek, 2, 200);
		setColumnWidth(dsSVMHWeek, 3, 150);
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent lse) {

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
