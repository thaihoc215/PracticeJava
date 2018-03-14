package practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InputForm
 */
@WebServlet("/InputForm")
public class InputFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html";
    /**
     * Default constructor. 
     */
    public InputFormServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("<meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">");
		out.println("<title>InputForm</title>");
		out.println("<h1>Tim kiem danh ba dien thoai</h1>");
		out.println("<form action=\"searchaccount\" method=\"post\">");
		out.println("Nhap ten thue bao: <input name=\"ttbao\"><br>");
		out.println("Nhap so dien thoai: <input type=\"text\" name=\"sodienthoai\"><br>");
		out.println(" <input type=\"submit\" name=\"submit\" value=\"Tim kiem\">");
		out.println("</form>");
		out.println("</head>");
		out.println("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
				String userName = request.getParameter("ttbao");
				String sodienthoai = request.getParameter("sodienthoai");

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				// send XHTML document to client

				// start XHTML document
				out.println("<?xml version = \"1.0\"?>");

				out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD " + "XHTML 1.0 Strict//EN\" \"http://www.w3.org"
						+ "/TR/xhtml1/DTD/xhtml1-strict.dtd\">");

				out.println("<html xmlns = \"http://www.w3.org/1999/xhtml\">");

				// head section of document
				out.println("<head>");
				out.println("<title>Search Account</title>");
				out.println("</head>");

				// body section of document
				out.println("<body>");
				out.println("<h1>Ket qua tim kiem tai khoan theo yeu cau</h1>");
				out.println("<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\">");
				out.println("<tr>");
				out.println("<th>So thu tu</th>");
				out.println("<th>Ten tai khoan</th>");
				out.println("<th>So dien thoai</th>");
				out.println("<th>Dia chi</th>");
				out.println("</tr>");
				//xu ly sql lay danh sach tai khoan
				String query = "select * from nhanvien";
				if(userName!=null && userName.length()!=0) {
					query = query + " where HoTen like '" + userName +"%'";
				}
				Statement stmt = null;
				ResultSet rs = null;
				Connection con = null;
				try {
					String userNameDB = "sa";
					String password = "th2151994";
					String url = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYGIAIBONGDA;";
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					con = DriverManager.getConnection(url,userNameDB,password);
					System.out.println("Connect Successful");
					stmt = con.createStatement();
					rs = stmt.executeQuery(query);
					if(rs!=null) {
						for (int i = 1; rs.next();) {
							out.println("<tr>");
							out.println("<td>"+i+"</td>");
							out.println("<td>" + rs.getString(2)+ "</td>");
							out.println("<td>" + rs.getString(5)+ "</td>");
							out.println("<td>" + rs.getString(7)+ "</td>");
							out.println("</tr>");
							
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					if(con !=null) {
						try {
							rs.close();
							stmt.close();
							con.close();
							
							System.out.println("Closed connection");
						} catch (Exception e2) {
							e2.printStackTrace();
						}
					}
				}
				out.println("</table>");
				out.println("</body>");

				// end XHTML document
				out.println("</html>");
				out.close(); // close stream to complete the page
	}

}
