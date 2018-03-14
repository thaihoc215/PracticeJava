<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Declaration tag example</title>
</head>
<body>
	<%! String name = "Ha Nguyen Thai Hoc";
		int age = 24;
	%>
	
	<%= "Name is: " + name %><br>
	<%= "Age is: " + age %>
</body>
</html>