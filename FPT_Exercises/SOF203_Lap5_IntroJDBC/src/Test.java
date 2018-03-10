import java.sql.Connection;
import java.sql.DriverManager;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			String userName = "sa";
			String password = "th2151994";
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYGIAIBONGDA;";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url,userName,password);
			System.out.println("Connect Successful");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(con !=null) {
				try {
					con.close();
					System.out.println("Closed connection");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

}
