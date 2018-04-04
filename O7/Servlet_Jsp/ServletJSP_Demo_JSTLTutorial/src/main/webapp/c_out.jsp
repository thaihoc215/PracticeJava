<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>c:out example</title>
</head>
<body>

	<h2>c:out example</h2>
	<c:out value="${'This is true: 10 > 1' }"></c:out>
	<br/>
	Tag: <c:out value="${'<atag> , &'}"/>
</body>
</html>
