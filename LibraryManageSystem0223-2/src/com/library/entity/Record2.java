package com.library.entity;

public class Record2 {
	private int id;
	private int bid;// 书本书号
	private String uname;// 用户名
	private String bookName;// 书名
	private String lendTime;// 借出时间
	private String returnTime;// 归还时间
	private int renew;// 是否续借

	public Record2() {

	}

	public Record2(int bid, String uname, String bookName, String lendTime,
			String returnTime, int renew) {
		super();
		this.bid = bid;
		this.uname = uname;
		this.bookName = bookName;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
		this.renew = renew;
	}

	public Record2(int id, int bid, String uname, String bookName,
			String lendTime, String returnTime, int renew) {
		super();
		this.id = id;
		this.bid = bid;
		this.uname = uname;
		this.bookName = bookName;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
		this.renew = renew;
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

	public String getLendTime() {
		return lendTime;
	}

	public void setLendTime(String lendTime) {
		this.lendTime = lendTime;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public int getRenew() {
		return renew;
	}

	public void setRenew(int renew) {
		this.renew = renew;
	}

	@Override
	public String toString() {
		return "Record2 [id=" + id + ", bid=" + bid + ", uname=" + uname
				+ ", bookName=" + bookName + ", lendTime=" + lendTime
				+ ", returnTime=" + returnTime + ", renew=" + renew + "]";
	}

}
