<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring MVC Resource example</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/scripts/common.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles/common.css">
</head>
<body>
	<pre>
	Config: /styles/** ==> /WEB-INF/resources/css/
	----------------------------------------------
        /styles/common.css ==> /WEB-INF/resources/css/common.css
        /styles/path1/abc.css ==> /WEB-INF/resources/css/path1/abc.css
        /styles/path1/path2/abc.css ==> /WEB-INF/resources/css/path1/path2/abc.css
	</pre>
	<div class="red-text">Red text</div>
    <br>
    <div class="green-text">Green text</div>
    <br>
 
    <input type="button" class="button" onclick="sayHello();"
        value="Click me!">
</body>
</html>