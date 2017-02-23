package com.library.entity;

public class Record {

	private int id;
	private int uid;// 用户id
	private int bid;// 书本书号
	private String lendTime;// 借出时间
	private String returnTime;// 归还时间
	private int renew;// 是否续借 0 代表未续借， 1代表续借

	public Record() {

	}

	public Record(int uid, int bid, String lendTime, String returnTime,
			int renew) {
		super();
		this.uid = uid;
		this.bid = bid;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
		this.renew = renew;
	}

	public Record(int id, int uid, int bid, String lendTime, String returnTime,
			int renew) {
		super();
		this.id = id;
		this.uid = uid;
		this.bid = bid;
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

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
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
		return "Record [id=" + id + ", uid=" + uid + ", bid=" + bid
				+ ", lendTime=" + lendTime + ", returnTime=" + returnTime
				+ ", renew=" + renew + "]";
	}

}
