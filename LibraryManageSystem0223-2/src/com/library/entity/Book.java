package com.library.entity;

public class Book {
	private int id;// 书号
	private String bookName;// 书名
	private String bookAuthor;// 作者
	private String publishTime;// 出版时间
	private int bookCount;// 书被借出次数
	private int status;// 0代表可借，1 代表已借，2 代表已借，且被预约，3 预约中。。
	private String detail;//代表书籍详细信息
	public Book() {

	}

	public Book(String bookName, String bookAuthor, String publishTime,
			int bookCount, int status) {
		super();
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.publishTime = publishTime;
		this.bookCount = bookCount;
		this.status = status;
	}
	public Book(String bookName, String bookAuthor, String publishTime,
			int bookCount, int status,String detail) {
		super();
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.publishTime = publishTime;
		this.bookCount = bookCount;
		this.status = status;
		this.detail = detail;
	}
	public Book(int id, String bookName, String bookAuthor, String publishTime,
			int bookCount, int status) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.publishTime = publishTime;
		this.bookCount = bookCount;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDetail() {
		return detail;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", bookAuthor="
				+ bookAuthor + ", publishTime=" + publishTime + ", bookCount="
				+ bookCount + ", status=" + status + "]";
	}

}
