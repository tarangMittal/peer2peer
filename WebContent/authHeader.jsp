<%-- <%@ page language="java" contentType="text/html"
    pageEncoding="UTF-8"%> --%>
<%@ page language="java" %>
<%@ page import = "org.booksforyou.model.User" %>
<%@ page import = "javax.servlet.RequestDispatcher" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

</head>


<body>
<%

User user = (User)session.getAttribute("user"); 
if( user == null) {
	RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
	view.forward(request, response);	
	return;
}
%>
</body>
</html>