<%@ page language="java" %>
   <%@  page import = "javax.servlet.RequestDispatcher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
session.removeAttribute("user");
session.invalidate();
RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
rd.forward(request, response);
%>

</body>
</html>