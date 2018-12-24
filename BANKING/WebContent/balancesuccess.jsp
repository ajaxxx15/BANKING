<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>balance</title>
</head>
<body>
	<%
		int bal = (int) session.getAttribute("BALANCE");
		out.println("Your Balance :" + bal);
	%>
</body>
</html>