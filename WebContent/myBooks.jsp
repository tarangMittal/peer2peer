<%@ page language="java" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <%@ include file="authHeader.jsp" %>
 <%@ include file="header.jsp" %> 
 <%@ include file="header2.jsp" %>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Books</title>
<Style>
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
</Style>
</head>
<body>
<h1>Books added by me</h1>
<table>
  <tr>
    <th>Book ID</th>
    <th>Title</th>
    <th>Author</th>
    <th>Description</th>
    <th>Status</th>
    <th>Borrowed By</th>
    <th>Borrowed Date</th>
  </tr>
    <c:forEach items="${ownBooks}" var="book">
    <form  action="books" method="Post">
        <tr>
            <td>${book.getBookId()}</td>
            <td>${book.getTitle()}</td>
            <td>${book.getAuthor()}</td>
            <td>${book.getDescription()}</td>
            <td> 
            		<select name = "status"> 
            			<option value="N" ${book.getStatus() == "N" ? "selected" : ""}>Not Available</option>
            			<option value="A" ${book.getStatus() == "A" ? "selected" : ""}>Available</option>
            			
	   			</select>
            </td>
            <td>${book.getBorrowerName()}</td>
            <td>${book.getBorrowedDate()}</td>
            <td><input type="submit" name="Save" value="Save"></td>
            <td><input type= "hidden" name= "senderPage" value= "updateBook"></td>
			<td><input type= "hidden" name= "selectedBookId" value= "${book.getBookId()}"></td>           
        </tr>
        </form>
    </c:forEach> 
</table>


<h1>Books Borrowed by Me</h1>

<table>
  <tr>
    <th>Book ID</th>
    <th>Title</th>
    <th>Author</th>
    <th>Description</th>
    <th>Status</th>
  </tr>
  <c:forEach items="${borrowedBooks}" var="book">
  <form  action="books" method="Post">
        <tr>
            <td>${book.getBookId()}</td>
            <td>${book.getTitle()}</td>
            <td>${book.getAuthor()}</td>
            <td>${book.getDescription()}</td>
            <td>${book.getStatus()}</td>
            <td><input type="submit" name="Return" value="Return"></td>
            <td><input type= "hidden" name= "senderPage" value= "returnBook"></td>
            <td><input type= "hidden" name= "borrowedBookId" value="${book.getBookId()}"></td>
            <td><input type= "hidden" name= "bookTransId" value= "${book.getBooktransId()}"></td>
        </tr>
    </form>
   </c:forEach>   
</table>

</body>
</html>