<%@ page language="java" %>
    <%@ include file="authHeader.jsp" %>
    <%@ include file="header.jsp" %>
    <%@ include file="header2.jsp" %>
    <jsp:useBean id="bookDetails" class="org.booksforyou.model.Book" scope="request"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<h1>Book Details:</h1>

<form action="books" method= "post">

  Book ID: <br>
  <input type= "text" name= "book_Id" value="<%=bookDetails.getBookId()%>" readonly >
  <br> 
  Title:<br>
  <input type="text" name="title" value="<%=bookDetails.getTitle()%>" readonly>
  <br>
  Author:<br>
  <input type="text" name="author" value="<%=bookDetails.getAuthor()%>" readonly >
  <br>
  Description:<br>
  <input type="text" name="description" value="<%=bookDetails.getDescription()%>" readonly>
  <br>
  Status:<br>
  <input type="text" name="status" value="<%=bookDetails.getStatus()%>"readonly>
  <br>
  Book Owner: <br>
  <input type="text" name="book_owner" value="<%=bookDetails.getOwnerName()%>"readonly>
  <br>
  <input type= "hidden" name= "senderPage" value= "borrowBook">
 <br>
 <input type="hidden" name = "OwnerId" value="<%=bookDetails.getUserId() %>" >
  <br><br>
  <% if(bookDetails.getUserId() != user1.getUserId() && bookDetails.getStatus().equals("A")) { %>
	  <input type="submit" value="Borrow Book">
  <%
  }
  %>
  <!-- <input type="submit" value="Borrow Book"> -->
  
</form> 
<%
    

%>
</body>
</html>