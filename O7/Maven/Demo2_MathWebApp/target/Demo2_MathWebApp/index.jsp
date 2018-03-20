<%@page import="org.test.mathutils.MathUtils"%>
<html>
<body>
	<h2>Hello World!</h2>
	<%
		int a = 100;
		int b = 200;

		int c = MathUtils.sum(a, b);

		out.println("<h2>" + c + "</h2>");
	%>
</body>
</html>
