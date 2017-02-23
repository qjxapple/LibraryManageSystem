package com.library.test;

import java.util.List;

import com.library.dao.implement.BookDaoImp;
import com.library.entity.Book;

public class BaseDaoTest {

	public static void main(String[] args) {
		BookDaoImp bs = new BookDaoImp();
		// boolean addBook = bs.addBook(new
		// Book("hallyworld","Mark","2013-08-09",2,0));
		// System.out.println(addBook);
		// boolean deleteBook = bs.deleteBook(4);
		// System.out.println(deleteBook);
		// List<Book> queryBookByAuthor = bs.queryBookByAuthor("Mark");
		// System.out.println(queryBookByAuthor);
		// Book queryBookByID = bs.queryBookByID(5);
		// System.out.println(queryBookByID);
		// List<Book> queryBookByName = bs.queryBookByName("HelloWrold");
		// System.out.println(queryBookByName);
		List<Book> queryBooks = bs.queryBooks();
		List<Book> queryBookByStatus = bs.queryBookByStatus(1, 2, 3);
		System.out.println(queryBookByStatus);
		System.out.println("----------");
		System.out.println(queryBooks);
	}

}
