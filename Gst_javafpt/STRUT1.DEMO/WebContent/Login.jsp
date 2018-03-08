<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Form</title>
</head>
<body>
	<h3>LOGIN WITH STRUT 1</h3>
	<html:form action="/Login" focus="userName">
        User Name :<html:text name="LoginForm" property="userName" />
        Password  :<html:password name="LoginForm" property="password" />
		<html:submit value="Login" />
	</html:form>
</body>
</html>