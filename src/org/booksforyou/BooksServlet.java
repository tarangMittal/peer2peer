package org.booksforyou;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.booksforyou.model.Book;
import org.booksforyou.model.BookRegister;
import org.booksforyou.model.User;
import org.booksforyou.services.BookRegisterService;
import org.booksforyou.services.BookService;
import org.booksforyou.services.Mailer;
import org.booksforyou.services.UserService;


/**
 * Servlet implementation class ParamServlet
 */

@WebServlet("/books")  
public class BooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("I am in GET");
		HttpSession session= request.getSession(false);
		User user = (User)session.getAttribute("user"); 
		if( user == null) {
			RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
			view.forward(request, response);	
			return;
		}
		
		String action = (String)request.getParameter("action");
		if(action != null && action.equals("catalog")) {
			BookService bookService= new BookService();
			ArrayList<Book> bookList = bookService.getBookList();
			System.out.println("Hello there from book servlet ");
			request.setAttribute("catalog",bookList);
			RequestDispatcher view = request.getRequestDispatcher("/bookCatalog.jsp");
			view.forward(request,response);
			
		}else if(action != null && action.equals("search")) {
			BookService bookService= new BookService();
			String title = request.getParameter("title");
			ArrayList<Book> bookList = bookService.searchByTitleOrAuthor(title);
			request.setAttribute("catalog", bookList);
			RequestDispatcher view = request.getRequestDispatcher("/bookCatalog.jsp");
			view.forward(request, response);
			
	}    else if(action !=null && action.equals("myBooks")) {
			BookService bookService= new BookService();
			
			int ownerId = user.getUserId();
			ArrayList<BookRegister> bookRegisterList = bookService.getMyBooks(ownerId);
			request.setAttribute("ownBooks", bookRegisterList);
			
			BookRegisterService bookRegisterService = new BookRegisterService();
			
			ArrayList<BookRegister> borrowedList = bookRegisterService.getMyBorrowedBooks(ownerId);
			request.setAttribute("borrowedBooks", borrowedList);
			RequestDispatcher view = request.getRequestDispatcher("/myBooks.jsp");
			view.forward(request, response);	
		}
		
		else if(action !=null && action.equals("details")) {
			BookService bookService= new BookService();
			System.out.println("working!");
			int id= Integer.parseInt(request.getParameter("bookId"));
			Book book = bookService.getBookDetails(id);
			request.setAttribute("bookDetails", book);
			System.out.println(book);
			RequestDispatcher view = request.getRequestDispatcher("/bookDetails.jsp");
			view.forward(request, response);
			
		} 
		
		
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("I am in POST method testing only");
		String action = (String)request.getParameter("action");
		HttpSession session= request.getSession(false);
		if(session.getAttribute("user") == null && !request.getParameter("senderPage").equals("registerUser")) {
			RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
			view.forward(request, response);	
			return;
			
		}
		
		if (request.getParameter("senderPage").equals("addBook")) {
			
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String description = request.getParameter("description");
			String status = request.getParameter("status");
			if (status.equals("available")){ 
					status = "A";
			} else {
				status = "N";
			}
			User user = (User)session.getAttribute("user"); 
			int userId = user.getUserId();
			Book book=new Book();
			book.setTitle(title);
			book.setAuthor(author);
			book.setDescription(description);
			book.setStatus(status);
			book.setUserId(userId);
			
			BookService bs = new BookService();
			bs.addBookDetails(book);
			
			RequestDispatcher view1 = request.getRequestDispatcher("/addBookResponse.jsp");
			view1.forward(request,response);
			
		} 
		else if(request.getParameter("senderPage").equals("registerUser")) {
			String password = request.getParameter("password");
			String emailId= request.getParameter("emailId");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String homeAddress = request.getParameter("homeAddress");
			String alternativeAddress= request.getParameter("alternativeAddress");
			String phoneNumber = request.getParameter("phoneNumber");
			
			User user= new User();
			user.setEmailId(emailId);
			user.setPassword(password);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setHomeAddress(homeAddress);
			user.setAlternativeAddress(alternativeAddress);
			user.setPhoneNumber(phoneNumber);
			
			UserService us= new UserService();
			us.registerUser(user);
			System.out.println("reached register user");
			RequestDispatcher view2 = request.getRequestDispatcher("/registerUserResponse.jsp");
			view2.forward(request,response);
			
		}else if(request.getParameter("senderPage").equals("borrowBook")) {
			System.out.println("working  in borrowbooks!");
			BookRegisterService bookRegisterService= new BookRegisterService();
			UserService userService= new UserService();
			BookService bookService= new BookService();
			User borrower = (User)session.getAttribute("user");
			int id= Integer.parseInt(request.getParameter("OwnerId"));
			int bookId= Integer.parseInt(request.getParameter("book_Id"));
		    	
			BookRegister bookRegister= new BookRegister();
			bookRegister.setBookId(bookId);
			bookRegister.setBorrowedByUserId(borrower.getUserId());
			bookRegister.setBorrowedDate(new Date());
            bookRegisterService.borrowBook(bookRegister);
			System.out.println("user: " + borrower);
			
			Book borrowedBook = bookService.getBookDetails(bookId);
			User owner = userService.getUser(borrowedBook.getUserId());
			
			bookRegister.setTitle(borrowedBook.getTitle());
			bookRegister.setAuthor(borrowedBook.getAuthor());
			bookRegister.setBorrowerName(borrower.getFirstname() + " " + borrower.getLastname());
			bookRegister.setOwnerName(owner.getFirstname()+ " " + owner.getLastname());
			sendOwnerMail(bookRegister, owner,borrower);
			sendBorrowerMail(bookRegister,owner,borrower);
			
			RequestDispatcher view = request.getRequestDispatcher("/borrowBookResponse.jsp");
			view.forward(request,response);
			
		}else if (request.getParameter("senderPage").equals("updateBook")) {
			int selectedBookId = Integer.parseInt(request.getParameter("selectedBookId"));
			String status = (String)request.getParameter("status");
			
			BookService bookService = new BookService();
			bookService.updateBookStatus(status, selectedBookId);
			
			System.out.println("selectedBookId : " + selectedBookId);
			System.out.println("status : " + status);
			RequestDispatcher view = request.getRequestDispatcher("/home.jsp");
			view.forward(request,response);
		}else if (request.getParameter("senderPage").equals("returnBook")) {
			int borrowedBookId = Integer.parseInt(request.getParameter("borrowedBookId"));
			int bookTransId = Integer.parseInt(request.getParameter("bookTransId"));
			System.out.println("borrowedBookId : " + borrowedBookId);
			System.out.println("bookTransId : " + bookTransId);
			BookRegisterService bookRegisterService= new BookRegisterService();
			bookRegisterService.updateReturnDate(bookTransId);
			RequestDispatcher view = request.getRequestDispatcher("/home.jsp");
			view.forward(request,response);
		}
		
	}
	public String sendOwnerMail(BookRegister bookRegister, User owner, User borrower) {
		String s1= "Hi " + bookRegister.getOwnerName() + "!,\n" + 
				bookRegister.getBorrowerName() + " would like to borrow this book from you:\n" 
				+ bookRegister.getTitle() + " by " + bookRegister.getAuthor() + "\n" +
				"booktransId: " + bookRegister.getBooktransId() + "\n\n"
				+ "Thank You \n" + "- BooksForYou"; 
		System.out.println("emailid : " + owner.getEmailId());
	     Mailer.send("booksforyoulibrary@gmail.com","books123@", owner.getEmailId(),
	    		 "Request to borrow your book",s1);  
		return s1;
	}
	public String sendBorrowerMail(BookRegister bookRegister, User owner, User borrower) {
		String s2= new String();
		s2= "Hi" + bookRegister.getBorrowerName() + "!\n" + "Please Collect your Book:\n"
		+  bookRegister.getTitle() + " by " + bookRegister.getAuthor() + "Book ID:" + 
				bookRegister.getBookId() + "\n" + "from Owner name:\n" + 
		bookRegister.getOwnerName() + "at Home Address:\n" + owner.getHomeAddress() + 
		"Phone Number:\n" + owner.getPhoneNumber() + "\n" + "Thank You\n" + "BooksForYou";
	    Mailer.send("booksforyoulibrary@gmail.com","books123@", borrower.getEmailId(),
	    		 "Request to collect your book",s2);  
		return s2;
	} 
		
	} 
		
	

