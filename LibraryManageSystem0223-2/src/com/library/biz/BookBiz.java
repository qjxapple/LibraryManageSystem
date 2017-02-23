package com.library.biz;

import java.util.List;

import com.library.entity.Book;

public interface BookBiz {
	public boolean addBook(Book book);// 添加书籍

	public boolean delBook(int bid);// 通过指定书籍的id删除书籍

	public boolean modifyBook(Book book);// 修改书籍信息

	public List<Book> queryAllBooks();// 查询所有的书籍信息

	public List<Book> queryBookByName(String bname);// 通过书名查找书籍

	public Book queryBookById(int bid);// 通过书号查找书籍

	public List<Book> queryByAuthor(String autor);// 通过作者搜索书籍

	public List<Book> canLendBook();// 查询可借书籍

	public List<Book> hasLendedBook();// 查询已借书籍

	public int lendBook(int bid, int uid);// 借书 ，通过书籍id和用户id

	public int returnBook(int uid,int rid);// 还书，通过记录表里面的id，记录归还时间

	public int orderBook(int bid, int uid);// 预约书籍

	public int updateData();// 对归还时期的限定，预约时间的限定取消

	public int renewBook(int rid);// 续借书籍
}
