<%@ page language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
        <%@ include file="authHeader.jsp" %> 
        <%@ include file="header.jsp" %>
        <%@ include file="header2.jsp" %>
        <%@ page import = "javax.servlet.RequestDispatcher" %>
    <%@ page import = "org.booksforyou.model.User" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Catalog</title>
</head>
<body>

<h1>These are the books for you!</h1>
<table>
<tr>
    <th>Title</th>
    <th>Author</th>
    <th>Status</th>
  </tr>
<c:forEach items="${catalog}" var="item">
        <tr>
            <td> <a href="http://localhost:8080/booksforyou/books?action=details&bookId=${item.getBookId()}">${item.getTitle()}</a></td>
            <td>${item.getAuthor()}</td>
            <td>${item.getStatus()}</td>
           
        </tr>
    </c:forEach> 
</table>
</body>
</html>