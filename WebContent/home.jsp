<%@ page language="java" %>
    <%@ page import = "javax.servlet.RequestDispatcher" %>
    <%@ page import = "org.booksforyou.model.User" %>
    <%@ include file="authHeader.jsp" %>
    <%@ include file="header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<h1> HOME </h1>
</head>
<body>

<p><a href="http://localhost:8080/booksforyou/addBookDetails.jsp">Add a book</a></p>
<p><a href="http://localhost:8080/booksforyou/books?action=catalog">Book Catalog</a></p>
<p><a href="http://localhost:8080/booksforyou/books?action=myBooks">View My Books</a></p>
<p><a href="http://localhost:8080/booksforyou/search.jsp">Search for a book</a></p>
</body>
</html>
