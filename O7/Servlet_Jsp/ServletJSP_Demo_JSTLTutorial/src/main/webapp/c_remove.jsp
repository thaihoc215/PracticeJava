<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>c:set example</title>
</head>
<body>

	<h2>c:set example</h2>

	<c:set scope="request" var="greeting" value="Hello every body" />


	Greeting:
	<c:out value="${greeting}" />
	<br />
	<br />

	<c:remove scope="request" var="greeting" />
	After remove:
	<br /> Greeting:
	<c:out value="${greeting}" />
</body>
</html>