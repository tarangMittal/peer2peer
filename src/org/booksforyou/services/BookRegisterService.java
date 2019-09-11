package org.booksforyou.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.booksforyou.model.Book;
import org.booksforyou.model.BookRegister;

public class BookRegisterService extends BaseService{
	
	public BookRegister borrowBook(BookRegister bookRegister){
		BookRegister bookRegister1= new BookRegister();
		try {			
			 String strInsert =
					 "INSERT INTO bookregister_tbl(borrowed_by,book_id,borrowed_date) "+ 
			 "VALUES (?, ?, ?)";
			 PreparedStatement preparedStatement= connection.prepareStatement(strInsert);
			 preparedStatement.setInt(1, bookRegister.getBorrowedByUserId());
			 preparedStatement.setInt(2, bookRegister.getBookId());
			 Timestamp timeStamp = new Timestamp(bookRegister.getBorrowedDate().getTime());
			 preparedStatement.setTimestamp(3,timeStamp);
			 preparedStatement.executeUpdate();
			 BookService bookService = new BookService();
			 bookService.updateBookStatus("N", bookRegister.getBookId());
			 
			 String strSelect= "SELECT * from bookregister_tbl where borrowed_by=? and book_id=?"
					+ " and borrowed_date=? ";
			 PreparedStatement preparedStatement1 = connection.prepareStatement(strSelect);
			 preparedStatement1.setInt(1, bookRegister.getBorrowedByUserId());
			 preparedStatement1.setInt(2, bookRegister.getBookId());
			 Timestamp timeStamp1 = new Timestamp(bookRegister.getBorrowedDate().getTime());
			 preparedStatement1.setTimestamp(3,timeStamp1);
			 ResultSet rset= preparedStatement1.executeQuery();
			 while(rset.next()) {
				 bookRegister1.setBooktransId(rset.getInt("booktrans_id")); 
				 bookRegister1.setBorrowedByUserId(rset.getInt("borrowed_by"));
				 bookRegister1.setBookId(rset.getInt("book_id"));
				 bookRegister1.setBorrowedDate(rset.getDate("borrowed_date"));
			 }
		
		} catch (SQLException e) {
			System.out.println("Borrow Book failed");
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
		return bookRegister1; 
	}
	
	public ArrayList<BookRegister> getMyBorrowedBooks(int userId){
	 
		ArrayList<BookRegister> bookList=new ArrayList<BookRegister>();
		try {
		
			String strSelect = "select * From books_tbl b, bookregister_tbl r where "
					+ "b.book_id=r.book_id and r.borrowed_by= ? "
					+ "and r.return_date IS NULL";
					
			System.out.println("The SQL query is: " + strSelect); // Echo For debugging
			System.out.println();
			PreparedStatement preparedStatement= connection.prepareStatement(strSelect);
			preparedStatement.setInt(1, userId);
		     
		    ResultSet rset = preparedStatement.executeQuery(); 
		     
		    while(rset.next()) {   // Move the cursor to the next row
		    	BookRegister bookRow = new BookRegister();
		        bookRow.setBookId(rset.getInt("book_id"));
		        bookRow.setUserId(rset.getInt("book_owner"));
		        bookRow.setTitle(rset.getString("title"));
		        bookRow.setAuthor(rset.getString("author"));
		        bookRow.setDescription(rset.getString("description"));
		        bookRow.setStatus(rset.getString("status"));
		        bookRow.setBooktransId(rset.getInt("booktrans_id"));
		     
		        bookList.add(bookRow);		  
		    }
		} catch (SQLException e) {
			System.out.println("getMyBorrowedBooks Failed! ");
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
 
 	public void returnBook(int bookId) {
		try {
			Statement stmt = connection.createStatement();
			String strDelete = "delete from bookregister_tbl where book_id= ?";
			PreparedStatement preparedStatement= connection.prepareStatement(strDelete);
			preparedStatement.setInt(1, bookId);
			preparedStatement.execute(strDelete);				
		} catch (SQLException e) {
			System.out.println("returnBook Failed!");
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
 	public void updateReturnDate(int bookTransId) {
	 		try {
	 			String update= "update bookregister_tbl SET return_date=? where booktrans_id=?";
				System.out.println("The SQL query is: " + update); // Echo For debugging
			    PreparedStatement preparedStatement= connection.prepareStatement(update);
			    Timestamp timeStamp1 = new Timestamp(new Date().getTime());
				preparedStatement.setTimestamp(1,timeStamp1);
			    preparedStatement.setInt(2, bookTransId); 
			    preparedStatement.executeUpdate();
			     
			} catch (SQLException e) {
				System.out.println("updateReturnDate Failed!");
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
			     