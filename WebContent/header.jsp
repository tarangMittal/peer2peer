<%@ page language="java" %>
<%@ page import = "org.booksforyou.model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
header, footer {
    color: red;
    text-align: right;
}</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<header>
<%
User user1 = (User)session.getAttribute("user");
if (user1!= null)
 out.println(user1.getFirstname()+" " + user1.getLastname());
%>
</header>
<header>
<a href="logout.jsp">Logout</a>
</header>
<body>

</body>
</html>