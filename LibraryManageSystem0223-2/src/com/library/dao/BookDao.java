package com.library.dao;

import java.util.List;

import com.library.entity.Book;

public interface BookDao {
	public boolean addBook(Book book);// 添加书

	public boolean updateBook(Book book);// 更新书籍

	public boolean deleteBook(int bid);// 删除书籍

	public List<Book> queryBooks();// 查询所有书籍

	public List<Book> queryBookByName(String bname);// 通过书名查询书籍

	public Book queryBookByID(int bid);// 根据书号查询书籍

	public List<Book> queryBookByStatus(int status, int status1, int status2);// 根据书籍状态查询书籍

	public List<Book> queryBookByAuthor(String author);// 根据作者查询书籍
	
	public Book queryBookDetailByID(int bid);//查询书籍详细信息
}
