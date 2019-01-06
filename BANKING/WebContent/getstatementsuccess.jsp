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
		/*Iterator itr = al.iterator();

		while (itr.hasNext() == true) {
			out.println(itr.next()+"\n");
			out.println("<BR>");*/
			Object []a = new Object[al.size()];
			
			for(int i=0;i<al.size();i++){
				a[i] = al.get(i);
			}
			for(Object i:a)
			{
				out.println(i);
			}
		
	%>
</body>
</html>