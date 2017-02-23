package com.library.entity;

/**
 * 用户进行预约操作的时候，在预约表中插入一条记录，记录预约信息。更新图书状态。 当此用户拿到书的时候，更新预约中的信息。更新图书信息
 * 
 * @author SkyEgine
 *
 */
public class Order {

	private int id;
	private int uid;// 用户id
	private int bid;// 书籍书号
	private int status;// 0 代表预约取消，1 代表预约中， 2，代表预约完成
	private String orderTime;// 预约时间，如果用户一直没有拿书，一段时间后取消预约

	public Order() {

	}

	public Order(int uid, int bid, String orderTime, int status) {
		super();
		this.uid = uid;
		this.bid = bid;
		this.orderTime = orderTime;
		this.status = status;
	}

	public Order(int id, int uid, int bid, String orderTime, int status) {
		super();
		this.id = id;
		this.uid = uid;
		this.bid = bid;
		this.orderTime = orderTime;
		this.status = status;
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
		return "Order [id=" + id + ", uid=" + uid + ", bid=" + bid
				+ ", status=" + status + ", orderTime=" + orderTime + "]";
	}

}
