<%@ page language="java" %>
     <%@ include file="authHeader.jsp" %>
     <%@ include file="header.jsp" %>
     <%@ include file="header2.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<h1>Search For a Book</h1>
</head>
<body>
<form method="get" action="books">
Search by title/author: <br>
<input type=text name="title">
<input type= "hidden" name= "action" value="search">
<br>
<input type="submit" value="Submit">

</form>

</body>
</html>