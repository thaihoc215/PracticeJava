package org.hochnt.jstltutorial.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hochnt.jstltutorial.beans.Dept;
import org.hochnt.jstltutorial.utils.DBUtils;

@WebServlet("/jstlex1")
public class JSTLEx1 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	 
    public JSTLEx1() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // Truy vấn dữ liệu từ DB (Mô phỏng).
        List<Dept> list = DBUtils.queryDepartments();
 
        // Lưu dữ liệu vào thuộc tính 'departments' của request.
        request.setAttribute("departments", list);
 
        // Tạo đối tượng RequestDispatcher
        // để Forward (chuyển tiếp) yêu cầu tới jstl_core_example01.jsp
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/WEB-INF/jsps/jstl_core_example01.jsp");
 
        // Forward (Chuyển tiếp) yêu cầu, để hiển thị dữ liệu trên trang JSP.
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
