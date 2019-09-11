package org.booksforyou.model;


import java.util.Date;

public class BookRegister extends Book{

	private int booktransId;
	private String borrowerName; 
	private Date borrowedDate;
	private Date returnDate ;
	private int borrowedByUserId;
	private int bookId;
	
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public int getBooktransId() {
		return booktransId; 
	}
	public void setBooktransId(int booktransId) {
		this.booktransId = booktransId;
	}
	
	public Date getBorrowedDate() {
		return borrowedDate;
	}
	public void setBorrowedDate(Date borrowedDate) {
		this.borrowedDate = borrowedDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public int getBorrowedByUserId() {
		return borrowedByUserId;
	}
	public void setBorrowedByUserId(int borrowedByUserId) {
		this.borrowedByUserId = borrowedByUserId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	@Override
	public String toString() {
		return "BookRegister [booktransId=" + booktransId + ", borrowerName=" + borrowerName + ", borrowedDate="
				+ borrowedDate + ", returnDate=" + returnDate + ", borrowedByUserId=" + borrowedByUserId + ", bookId="
				+ bookId + "]";
	}
	
	

}
