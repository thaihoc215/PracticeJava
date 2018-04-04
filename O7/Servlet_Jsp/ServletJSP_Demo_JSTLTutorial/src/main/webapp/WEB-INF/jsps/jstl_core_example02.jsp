<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Khai báo sử dụng JSTL Core Tags -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Core Tags Example 01</title>
</head>
<body>
	<h2>Department and Employess</h2>
	<!-- Dùng for để duyệt trên các phòng ban (departments) -->
	<c:forEach items="${departments}" var="dept">
		<!-- Kiểm tra một tập hợp có phần tử hay không -->
		<c:if test="${not empty dept.employees}">
			<h3>${dept.deptName}</h3>
			<ul>
				<!-- Dùng for để duyệt trên các nhân viên
                   thuộc phòng ban hiện tại -->
				<c:forEach items="${dept.employees }" var="emp">
					<c:out value="${emp.job }"></c:out>
					<li>${emp.empName }-(${emp.job})</li>
				</c:forEach>
			</ul>
		</c:if>
	</c:forEach>
</body>
</html>