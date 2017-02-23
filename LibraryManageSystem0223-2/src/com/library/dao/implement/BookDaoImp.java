package com.library.dao.implement;

import java.util.ArrayList;
import java.util.List;

import com.library.dao.BookDao;
import com.library.entity.Book;

public class BookDaoImp extends BaseDao implements BookDao {

	@Override
	public boolean addBook(Book book) {
		String sql = "insert into books(bookName,bookAuthor,publishTime,bookCount,status,detail) values (?,?,?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(book.getBookName());
		params.add(book.getBookAuthor());
		params.add(book.getPublishTime());
		params.add(book.getBookCount());
		params.add(book.getStatus());
		params.add(book.getDetail());
		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean updateBook(Book book) {
		String sql = "update books set bookName=?,bookAuthor=?,publishTime=?,bookCount=?,status=?,detail=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(book.getBookName());
		params.add(book.getBookAuthor());
		params.add(book.getPublishTime());
		params.add(book.getBookCount());
		params.add(book.getStatus());
		params.add(book.getDetail());
		params.add(book.getId());
		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean deleteBook(int bid) {
		String sql = "delete from books where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(bid);
		return this.operateUpdate(sql, params);
	}

	@Override
	public List<Book> queryBooks() {
		String sql = "select id,bookName,bookAuthor,publishTime,bookCount,status from books";
		List<Book> bookList = null;
		try {
			bookList = this.operateQuery(sql, null, Book.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public List<Book> queryBookByName(String bname) {
		String sql = "select id,bookName,bookAuthor,publishTime,bookCount,status from books where bookName  like ?";
		List<Book> bookList = null;
		List<Object> params = new ArrayList<Object>();
		params.add("%" + bname + "%");
		try {
			bookList = this.operateQuery(sql, params, Book.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookList;
	}

	@Override
	public Book queryBookByID(int bid) {
		String sql = "select id,bookName,bookAuthor,publishTime,bookCount,status,detail from books where id=?";
		List<Book> bookList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(bid);
		try {
			bookList = this.operateQuery(sql, params, Book.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (bookList.size() > 0)
			return bookList.get(0);
		return null;
	}

	@Override
	public Book queryBookDetailByID(int bid) {
		String sql = "select detail from books where id=?";
		List<Book> bookList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(bid);
		try {
			bookList = this.operateQuery(sql, params, Book.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (bookList.size() > 0)
			return bookList.get(0);
		return null;
	}

	@Override
	public List<Book> queryBookByStatus(int status, int status1, int status2) {
		String sql = "select id,bookName,bookAuthor,publishTime,bookCount,status from books where status=? or status=? or status=?";
		List<Book> bookList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		params.add(status1);
		params.add(status2);
		try {
			bookList = this.operateQuery(sql, params, Book.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (bookList.size() > 0)
			return bookList;
		return null;
	}

	@Override
	public List<Book> queryBookByAuthor(String author) {
		String sql = "select id,bookName,bookAuthor,publishTime,bookCount,status from books where binary bookAuthor=?";
		List<Book> bookList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(author);
		try {
			bookList = this.operateQuery(sql, params, Book.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (bookList.size() > 0)
			return bookList;
		return null;
	}

}
