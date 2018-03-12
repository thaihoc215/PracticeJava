package practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
				String password = request.getParameter("sodienthoai");

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
				out.println("<title>Processing get requests with data</title>");
				out.println("</head>");

				// body section of document
				out.println("<body>");
				out.println("<h1>Hello " + userName + "- Password is " + password + ",<br />");
				out.println("Welcome to Servlets!</h1>");
				out.println("</body>");

				// end XHTML document
				out.println("</html>");
				out.close(); // close stream to complete the page
	}

}
