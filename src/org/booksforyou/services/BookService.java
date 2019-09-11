package org.booksforyou.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import org.booksforyou.model.Book;
import org.booksforyou.model.BookRegister;

public class BookService extends BaseService{
	
	public ArrayList<Book>  getBookList() {
	
		ArrayList<Book> bookList=new ArrayList<Book>();
		try {
	
			Statement stmt = connection.createStatement();
			String strSelect = "select * from books_tbl where book_id< 100";
		    System.out.println("The SQL query is: " + strSelect); // Echo For debugging
		  
		    ResultSet rset = stmt.executeQuery(strSelect);
		     
		    // Process the ResultSet by scrolling the cursor forward via next().
		    //  For each row, retrieve the contents of the cells with getXxx(columnName).
		   
		    int rowCount = 0;
		    while(rset.next()) {   // Move the cursor to the next row
		    	Book bookRow = new Book();
		        bookRow.setBookId(rset.getInt("book_id"));
		        bookRow.setUserId(rset.getInt("book_owner"));
		        bookRow.setTitle(rset.getString("title"));
		        bookRow.setAuthor(rset.getString("author"));
		        bookRow.setDescription(rset.getString("description"));
		        bookRow.setStatus(rset.getString("status"));
		        
		        bookList.add(bookRow);		
		        ++rowCount;
		    }
		} catch (SQLException e) {
			System.out.println("getBookList Failed!");
			e.printStackTrace();
			return null;
		}finally{
			try {
				connection.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for(Book book1:bookList){
			System.out.println(book1.toString());
		}
		return bookList;
	}
	
	public void addBookDetails(Book book){
			try {
				 String strInsert =
						 "INSERT INTO books_tbl(title,author,description,status,book_owner) "+ 
				 "VALUES (?, ?, ?, ?, ?)";
				 PreparedStatement preparedStatement= connection.prepareStatement(strInsert);
				 preparedStatement.setString(1, book.getTitle());
				 preparedStatement.setString(2, book.getAuthor());
				 preparedStatement.setString(3, book.getDescription());
				 preparedStatement.setString(4, book.getStatus());
				 preparedStatement.setInt(5, book.getUserId());
				 
				// execute insert SQL statement
				 preparedStatement.executeUpdate();
				 System.out.println("Record is inserted into books table!");
		
			} catch (SQLException e) {
				System.out.println("addBookDetails Failed! ");
				e.printStackTrace();
				return;
			}finally{
				try {
					connection.close();
					System.out.println("Connection closed");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}

	public ArrayList<BookRegister>  getMyBooks(int ownerId) {
		
		ArrayList<BookRegister> bookRegisterList=new ArrayList<BookRegister>();
			try {
				
				
				String strSelect = "select * from books_tbl a left join bookregister_tbl as b "
				 		+ " on a.book_id=b.book_id  left join users_tbl as u on u.user_id =  b.borrowed_by  where " +
						 "a.book_owner = ? and  b.booktrans_id = (select max(booktrans_id) from bookregister_tbl b1 "
						 + "where b1.book_id = b.book_id  group by b1.book_id order by b1.book_id ) or b.booktrans_id is null and a.book_owner = ?";
 
			     PreparedStatement preparedStatement= connection.prepareStatement(strSelect);
			     preparedStatement.setInt(1, ownerId);
			     preparedStatement.setInt(2, ownerId);
			     System.out.println("The SQL query is: " + preparedStatement.toString());
			  
			     ResultSet rset = preparedStatement.executeQuery();
			     
			     while(rset.next()) {   // Move the cursor to the next row
			    	BookRegister bookRow = new BookRegister();
			        bookRow.setBookId(rset.getInt("book_id"));
			        bookRow.setUserId(rset.getInt("book_owner"));
			        bookRow.setTitle(rset.getString("title"));
			        bookRow.setAuthor(rset.getString("author"));
			        bookRow.setDescription(rset.getString("description"));
			        bookRow.setStatus(rset.getString("status"));
			        bookRow.setBorrowedByUserId(rset.getInt("borrowed_by"));
			        bookRow.setBorrowedDate(rset.getDate("borrowed_date"));
			        
			        if (rset.getString("firstname")!= null && rset.getString("lastname")!= null)
			        	bookRow.setBorrowerName(rset.getString("firstname") + " " + rset.getString("lastname"));
			        if (rset.getDate("return_date") != null && "Y".equals(rset.getString("status"))){
			        	bookRow.setBorrowedByUserId(0);
				        bookRow.setBorrowedDate(null);
				        bookRow.setBorrowerName(null);
				        bookRow.setBooktransId(0);
			        }
			        bookRegisterList.add(bookRow);		 
			     }
			         
			} catch (SQLException e) {
				System.out.println("getMyBooks Failed!");
				e.printStackTrace();
				return null;
			}finally{
				try {
					connection.close();
					System.out.println("Connection closed");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			for(Book book:bookRegisterList){
				 System.out.println(book.toString());
			}				
			return bookRegisterList; 
	   }
	
	public ArrayList<Book> searchByTitleOrAuthor(String searchString){
		
			ArrayList<Book> bookList=new ArrayList<Book>();
			try {
			
				 String strSelect =
				 "SELECT * FROM books_tbl\n" + 
				 "where upper(title) like ?  or upper(author) like ?";
			     System.out.println("The SQL query is: " + strSelect); // Echo For debugging
			     PreparedStatement preparedStatement= connection.prepareStatement(strSelect);
			     
			     preparedStatement.setString(1, "%" + searchString.toUpperCase() + "%");
			     preparedStatement.setString(2, "%" + searchString.toUpperCase() + "%");
			     
			     System.out.println(strSelect);
			     System.out.println("sql is :" + preparedStatement.toString());
			  
			     ResultSet rset = preparedStatement.executeQuery();
			     
			     while(rset.next()) {   // Move the cursor to the next row
			    	Book bookRow = new Book();
			        bookRow.setBookId(rset.getInt("book_id"));
			        bookRow.setUserId(rset.getInt("book_owner"));
			        bookRow.setTitle(rset.getString("title"));
			        bookRow.setAuthor(rset.getString("author"));
			        bookRow.setDescription(rset.getString("description"));
			        bookRow.setStatus(rset.getString("status"));
			        bookList.add(bookRow);		
			     }
			         
			} catch (SQLException e) {
				System.out.println("searchByTitleOrAuthor Failed! ");
				e.printStackTrace();
				return null;
			}finally{
				try {
					connection.close();
					System.out.println("Connection closed");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			for(Book book:bookList){
				 System.out.println(book.toString());
			}				
		return bookList; 		
	}
	
	public Book getBookDetails(int book_Id){
		Book bookRow = new Book();
		try {
			String strSelect="select b.book_id,b.title,b.author,b.description,b.status,b.book_owner,u.firstname,u.lastname from books_tbl b, users_tbl u  where b.book_owner=u.user_id and book_id=?";
			System.out.println("The SQL query is: " + strSelect); // Echo For debugging
		    
		    PreparedStatement preparedStatement= connection.prepareStatement(strSelect);
		    preparedStatement.setInt(1, book_Id);
		     
		    ResultSet rset = preparedStatement.executeQuery(); 
		    
		    while(rset.next()) {   // Move the cursor to the next row
		        bookRow.setBookId(rset.getInt("book_id"));
		        bookRow.setUserId(rset.getInt("book_owner"));
		        bookRow.setTitle(rset.getString("title"));
		        bookRow.setAuthor(rset.getString("author"));
		        bookRow.setDescription(rset.getString("description"));
		        bookRow.setStatus(rset.getString("status"));
		        bookRow.setOwnerName((rset.getString("firstname") + " " + rset.getString("lastname")));
		    }
	         
		} catch (SQLException e) {
			System.out.println("getBookDetails Failed! ");
			e.printStackTrace();
			return null;	
		}finally{
			try {
				connection.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(bookRow.toString());			
		return bookRow;
	}
	
	public void updateBookStatus(String status, int bookId) {
		try {
		
			String update= "update books_tbl SET status=? where book_id=?";
			System.out.println("The SQL query is: " + update); // Echo For debugging
		    
		    PreparedStatement preparedStatement= connection.prepareStatement(update);
		    preparedStatement.setString(1, status);
		    preparedStatement.setInt(2, bookId); 
		     
		    preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("updateBookStatus Failed! ");
			e.printStackTrace();
			return;
		}finally{
			try {
				connection.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}




	

