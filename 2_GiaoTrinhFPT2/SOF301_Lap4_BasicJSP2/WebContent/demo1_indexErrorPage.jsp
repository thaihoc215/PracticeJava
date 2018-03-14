<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page errorPage="/demo1_errorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index test Error page</title>
</head>
<body>
	<center>
		<h3>divine 2 numbers</h3>
		<form action="" name="frm" method="get">
			number 1: <input type="text" name="num1"><br>
			<br>
			number 2: <input type="text" name="num2"><br>
			<br>
			<input name="btnDevine" value="Divine" type="submit">
		</form>
		<%
		String str1= request.getParameter("num1");
		String str2= request.getParameter("num2");
		if(str1!=null && str2!=null){
			Double n1 = Double.parseDouble(str1);
			Double n2 = Double.parseDouble(str2);
			Double kq = n1/n2;
			out.println(kq);
		}
		%>
	</center>
</body>
</html>