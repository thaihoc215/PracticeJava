package conn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import model.HocSinhModel;

public class MyJDBC {
	private static Connection con = null;
	private String host;
	private String dbName;
	private String usr;
	private String pwd;
	
	public MyJDBC() {
		String dir = System.getProperty("user.dir").toString() + "/connectDB.xml";
		File fXmlFile = new File(dir);

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("connect");
			Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				host = eElement.getElementsByTagName("host").item(0).getTextContent();
				dbName = eElement.getElementsByTagName("dbname").item(0).getTextContent();
				usr = eElement.getElementsByTagName("user").item(0).getTextContent();
				pwd = eElement.getElementsByTagName("pass").item(0).getTextContent();
			}

		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tao ket noi voi database
	 */
	private void connectDB() {
		// String hostName = "jdbc:sqlserver://localhost:1433;";
		// // String sqlInstanceName = "SQLEXPRESS";
		// String database = "QuanLyHocSinh";
		// String userName = "sa";
		// String password = "th2151994";
		// connectDB(hostName, database, userName, password);
		connectDB(host, dbName, usr, pwd);
		System.out.println("\nConnect Successfull");
	}

	/**
	 * Ket noi Database cung thong tin
	 * @param hostName
	 * @param database
	 * @param userName
	 * @param password
	 */
	private void connectDB(String hostName, String database, String userName, String password) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = hostName + "databaseName=" + database;
			con = DriverManager.getConnection(connectionURL, userName, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ngay ket noi den server
	 */
	private static void closeConnect() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Lay danh sach tat ca hoc sinh
	 * @return ArrayList<HocSinhModel>
	 */
	public ArrayList<HocSinhModel> getListHS() {
		connectDB();
		ArrayList<HocSinhModel> list = new ArrayList<HocSinhModel>();
		String sql = "Select * From HocSinh";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				HocSinhModel hs = new HocSinhModel();
				hs.set_maHS(rs.getInt("MaHS"));
				hs.set_tenHS(rs.getString("TenHS"));
				hs.set_ngaySinh(rs.getDate("NgaySinh"));
				hs.set_ghiChu(rs.getString("GhiChu"));
				hs.set_images(rs.getBytes("ExtInfo"));
				list.add(hs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnect();
		return list;
	}
	
	/**
	 * Tim kiem hoc sinh theo dieu kien
	 * @param maHS Ma Hoc Sinh
	 * @param tenHS Ten Hoc Sinh
	 * @param ngaySinh Ngay thang nam sinh Hoc Sinh
	 * @return ArrayList<HocSinhModel>
	 */
	public ArrayList<HocSinhModel> findHS(int maHS, String tenHS, Date ngaySinh) {
		connectDB();
		ArrayList<HocSinhModel> list = new ArrayList<HocSinhModel>();
		String sql = "Select * From HocSinh"
				+ " Where MaHS = ? or TenHS Like ? or NgaySinh = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setInt(1, maHS);
			pstm.setString(2, tenHS + "%");
			pstm.setDate(3, new java.sql.Date(ngaySinh.getTime()));
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				HocSinhModel hs = new HocSinhModel();
				hs.set_maHS(rs.getInt("MaHS"));
				hs.set_tenHS(rs.getString("TenHS"));
				hs.set_ngaySinh(rs.getDate("NgaySinh"));
				hs.set_ghiChu(rs.getString("GhiChu"));
				hs.set_images(rs.getBytes("ExtInfo"));
				list.add(hs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeConnect();
		return list;
	}
	
	/**
	 * Them moi mot hoc sinh
	 * @param hsinh Model thong tin hoc sinh
	 * @param imgPath 
	 * @return Boolean
	 * @throws SQLException 
	 * @throws FileNotFoundException 
	 */
	public Boolean addHocSinh(HocSinhModel hsinh, File imgHs) throws SQLException, FileNotFoundException {
		connectDB();
		String sql = "Insert Into HocSinh(TenHS,NgaySinh,GhiChu,ExtInfo)" + " Values(?,?,?,?)";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, hsinh.get_tenHS());
			pstm.setDate(2, hsinh.get_ngaySinh() == null ? null : new java.sql.Date( hsinh.get_ngaySinh().getTime()));
			pstm.setString(3, hsinh.get_ghiChu());
			if (imgHs != null) {
				FileInputStream fis = new FileInputStream(imgHs);
				int len = (int) imgHs.length();
				pstm.setBinaryStream(4, fis, len);
			} else
				pstm.setBinaryStream(4, null);

			pstm.executeUpdate();
			return true;
		} finally {
			closeConnect();
		}
	}

	/**
	 * Chinh sua thong tin hoc sinh
	 * @param hsinh hosinhmodel
	 * @param imgHs anh hoc sinh
	 * @return Boolean
	 * @throws SQLException
	 * @throws FileNotFoundException
	 */
	public Boolean editHocSinh(HocSinhModel hsinh, File imgHs) throws SQLException, FileNotFoundException {
		connectDB();
		String sql = "Update HocSinh" + " Set TenHS = ?," + "		NgaySinh = ?," + "		GhiChu = ?,"
				+ "		ExtInfo = ?" + " Where MaHS = ?";
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, hsinh.get_tenHS());
			pstm.setDate(2, hsinh.get_ngaySinh() == null ? null : new java.sql.Date( hsinh.get_ngaySinh().getTime()));
			pstm.setString(3, hsinh.get_ghiChu());
			if (imgHs != null) {
				FileInputStream fis = new FileInputStream(imgHs);
				int len = (int) imgHs.length();
				pstm.setBinaryStream(4, fis, len);
			} else
				pstm.setBinaryStream(4, null);
			pstm.setInt(5,hsinh.get_maHS());
			pstm.executeUpdate();
			return true;
		} finally {
			closeConnect();
		}
	}

	/**
	 * Xoa hoc sinh
	 * @param maHs
	 * @return Boolean
	 * @throws SQLException
	 */
	public Boolean deleteHocSinh(int maHs) throws SQLException {
		connectDB();
		String sql = "Delete From HocSinh " + "Where MaHS = " + maHs;
		try {
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.executeUpdate();
			return true;
		} finally {
			closeConnect();
		}
	}

}
