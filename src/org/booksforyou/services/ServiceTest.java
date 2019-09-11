package org.booksforyou.services;

import org.booksforyou.model.Book;


public class ServiceTest {

	public static void main(String[] args) {
	
		BookService bookService =  new BookService();
		Book book = new Book();
		book.setAuthor("Roald Dahl");
		book.setTitle("James and Giant Peach");
		book.setDescription("Roald Dahl's beloved children's tale follows the adventures of James (Paul Terry), an orphaned young British boy");
		book.setUserId(1);
		book.setStatus("A");
		bookService.addBookDetails(book);

	}

}
