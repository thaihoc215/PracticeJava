package Utils;

import java.awt.Component;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class Commons {
	public static int SUNDAY = 1;
	public static int MONDAY = 1;
	public static int TUESDAY = 1;
	public static int WENESDAY = 1;
	public static int THURSDAY = 1;
	public static int FRIDAY = 1;
	public static int SATURDAY = 1;
	
	public static ArrayList<String> dateOfWeek = new ArrayList<>(Arrays.asList( "Chủ Nhật","Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm","Thứ Sáu",
			"Thứ Bảy"));
	
	public static int fifteenWeeks = 105;
	
	public static void showSuccessMessage(Component compo, String successMes, String successTit) {
		JOptionPane.showMessageDialog(compo, successMes, successTit, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showErrorMessage(Component compo,String errorMessage, String errorTitle) {
		JOptionPane.showMessageDialog(compo, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}
	
	public static String hashMD5Password(String pass) {
		byte[] bytesOfMessage;
		try {
			bytesOfMessage = new String(pass).getBytes("UTF8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(bytesOfMessage);
			String mk = new String(thedigest,"UTF8");
			return mk;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
			return "";
		}
	}
}
