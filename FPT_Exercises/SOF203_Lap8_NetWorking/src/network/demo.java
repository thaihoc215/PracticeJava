package network;

import java.net.MalformedURLException;
import java.net.URL;

public class demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL url= new URL("https://www.facebook.com/photo.php?fbid=1555310367814512&set=a.159006417444921.35598.100000066922398&type=3&source=11&referrer_profile_id=100000066922398");
			System.out.println("Protocol: " + url.getProtocol());
			System.out.println("Host: " + url.getHost());
			System.out.println("Port: " + url.getPort());
			System.out.println("Path: " + url.getPath());
			System.out.println("File: " + url.getFile());
			System.out.println("Query: " + url.getQuery());
			System.out.println("Ref: " + url.getRef());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
