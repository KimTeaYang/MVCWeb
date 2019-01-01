<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello.jsp</title>
</head>
<body>
	<div align="center">
		<h1>MVCWeb Hello</h1>
		<h2 style="color:blue">
			<%=request.getAttribute("msg") %>
		</h2>
		<h2 style="color:green">
			${msg}
		</h2>
	</div>
</body>
</html>