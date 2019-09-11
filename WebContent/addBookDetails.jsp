<%@ page language="java" %>
 <%@ include file="authHeader.jsp" %>
 <%@ include file="header.jsp" %>
 <%@ include file="header2.jsp" %>
 <%@ page import = "javax.servlet.RequestDispatcher" %>
 <%@ page import = "org.booksforyou.model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>

<h1>Please enter book details</h1>
<form action="books" method= "post">
  Title:<br>
  <input type="text" name="title">
  <br>
  Author:<br>
  <input type="text" name="author" >
  <br>
  Description:<br>
  <input type="text" name="description">
  <br>
  Status:<br>
  <input type="text" name="status" value= "available" readonly>
  <br>
  <input type= "hidden" name= "senderPage" value="addBook">
  <br><br>
  <input type="submit" value="Submit">
</form> 
</body>
</html>
