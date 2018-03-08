<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>Strut2 - Login Here</title>
</head>
<body>
	<h2>Struts 2 - Login Application with Annotations</h2>
	<s:actionerror />
	<s:form action="login.action" method="post">
		<s:textfield name="username" key="label.username" size="20" />
		<s:password name="password" key="label.password" size="20" />
		<s:submit key="label.login" align="center" />
	</s:form>
</body>
</html>