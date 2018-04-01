<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div
		style="text-align: right; padding: 5px; margin: 5px 0px; background: #ccc;">
		<a href="${pageContext.request.contextPath}/en/login2">Login
			(English)</a> &nbsp;&nbsp; <a
			href="${pageContext.request.contextPath}/fr/login2">Login
			(French)</a> &nbsp;&nbsp; <a
			href="${pageContext.request.contextPath}/vi/login2">Login
			(Vietnamese)</a>
	</div>

	<form method="post" action="">
		<table>
			<tr>
				<td><strong> <spring:message code="label.userName" />
				</strong></td>
				<td><input name="userName" /></td>
			</tr>
			<tr>
				<td><strong> <spring:message code="label.password" />
				</strong></td>
				<td><input name="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><spring:message code="label.submit"
						var="labelSubmit"></spring:message> <input type="submit"
					value="${labelSubmit}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>