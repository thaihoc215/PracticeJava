package network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class demoDownloadPage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		URL url;
		try {
			url = new URL("https://vnexpress.net");
			InputStream is = url.openStream();
			byte[] block = new byte[4 * 1024];
			while (true) {
				int n = is.read(block);// doc toi da 4kb mot lan
				if (n <= 0) {
					// het du lieu
					break;
				}
				buffer.write(block,0,n); //tich luy du lieu doc duoc
			}
			
			byte[] date = buffer.toByteArray();
			//chuyen noi dung da doc tu url sang string
			String content = buffer.toString("utf-8");
			System.out.println(content);
			buffer.close();
			
			Scanner scn = new Scanner(System.in);
			int choose = scn.nextInt();
			if(choose == 1) {
				System.out.println("abc");
			}
			scn.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
