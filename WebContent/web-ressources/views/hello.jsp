<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hello ${name}!</title>
<link href="${contextPath}/resources/css/main.css" rel="stylesheet">
</head>
<body>
	<h2 class="hello-title">Hello ${name}!</h2>
	<script src="${contextPath}/resources/js/main.js"></script>
</body>
</html>