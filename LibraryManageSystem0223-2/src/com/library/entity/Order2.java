package com.library.entity;

public class Order2 {
	private int id;
	private int bid;// 书号
	private String uname;// 用户名
	private String bookName;// 书名
	private int status;// 0 代表预约取消，1 代表预约中， 2，代表预约完成
	private String orderTime;// 预约时间，如果用户一直没有拿书，一段时间后取消预约

	public Order2() {

	}

	public Order2(int bid, String uname, String bookName, int status,
			String orderTime) {
		super();
		this.bid = bid;
		this.uname = uname;
		this.bookName = bookName;
		this.status = status;
		this.orderTime = orderTime;
	}

	public Order2(int id, int bid, String uname, String bookName, int status,
			String orderTime) {
		super();
		this.id = id;
		this.bid = bid;
		this.uname = uname;
		this.bookName = bookName;
		this.status = status;
		this.orderTime = orderTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	@Override
	public String toString() {
		return "Order2 [id=" + id + ", bid=" + bid + ", uname=" + uname
				+ ", bookName=" + bookName + ", status=" + status
				+ ", orderTime=" + orderTime + "]";
	}

}
