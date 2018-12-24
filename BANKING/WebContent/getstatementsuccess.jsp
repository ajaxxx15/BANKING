<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		ArrayList al = new ArrayList();
		al.add(session.getAttribute("STMT"));
		Iterator itr = al.iterator();

		while (itr.hasNext() == true) {
			out.println(itr.next());
			out.println("<BR>");

		}
	%>
</body>
</html>