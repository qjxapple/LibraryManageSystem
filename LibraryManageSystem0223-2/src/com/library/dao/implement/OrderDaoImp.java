package com.library.dao.implement;

import java.util.ArrayList;
import java.util.List;

import com.library.dao.OrderDao;
import com.library.entity.Order;
import com.library.entity.Order2;

public class OrderDaoImp extends BaseDao implements OrderDao {

	@Override
	public boolean saveOrder(Order order) {
		String sql = "insert into orders (uid,bid,orderTime,status) values (?,?,?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(order.getUid());
		params.add(order.getBid());
		params.add(order.getOrderTime());
		params.add(order.getStatus());
		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean updateOrder(Order order) {
		String sql = "update orders set uid=?,bid=?,orderTime=?,status=? where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(order.getUid());
		params.add(order.getBid());
		params.add(order.getOrderTime());
		params.add(order.getStatus());
		params.add(order.getId());
		return this.operateUpdate(sql, params);
	}

	@Override
	public boolean deleteOrder(int oid) {
		String sql = "delete from orders where id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(oid);
		return this.operateUpdate(sql, params);
	}

	@Override
	public Order queryOrderByUId(int uid) {
		String sql = "select o.id,o.uid,o.bid,o.orderTime,o.status from orders o,users  u where o.uid = u.id and u.id=?";
		List<Order> orderList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(uid);
		try {
			orderList = this.operateQuery(sql, params, Order.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (orderList.size() > 0)
			return orderList.get(0);
		else
			return null;
	}

	@Override
	public Order queryOrderByBid(int bid) {
		String sql = "select o.id,o.uid,o.bid,o.orderTime,o.status from orders o,users u where o.uid = u.id and o.bid=?";
		List<Order> orderList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(bid);
		try {
			orderList = this.operateQuery(sql, params, Order.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (orderList.size() > 0)
			return orderList.get(0);
		else
			return null;
	}

	@Override
	public List<Order2> queryAllOrders() {
		String sql = "select o.id,o.bid,u.uname,b.bookName,o.orderTime,o.status from users u,books b,orders o "
				+ "where u.id=o.uid and b.id=o.bid";
		List<Order2> orderList = null;
		try {
			orderList = this.operateQuery(sql, null, Order2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<Order> findAllOrders() {
		String sql = "select o.id,o.bid,o.uid,o.orderTime,o.status from users u,books b,orders o "
				+ "where u.id=o.uid and b.id=o.bid";
		List<Order> orderList = null;
		try {
			orderList = this.operateQuery(sql, null, Order.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<Order2> queryOrderByUname(String uname) {
		String sql = "select o.id,o.bid,u.uname,b.bookName,o.orderTime,o.status from "
				+ "users u,books b,orders o where u.id=o.uid and b.id=o.bid and u.uname=?";
		List<Order2> orderList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(uname);
		try {
			orderList = this.operateQuery(sql, params, Order2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<Order2> queryOrderByBname(String bname) {
		String sql = "select o.id,o.bid,u.uname,b.bookName,o.orderTime,o.status from "
				+ "users u,books b,orders o where u.id=o.uid and b.id=o.bid and b.bookName=?";
		List<Order2> orderList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(bname);
		try {
			orderList = this.operateQuery(sql, params, Order2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<Order2> queryOrderByStatus(int status) {
		String sql = "select u.uname,b.bookName,o.orderTime,o.status from "
				+ "users u,books b,orders o where u.id=o.uid and b.id=o.bid and o.status=?";
		List<Order2> orderList = null;
		List<Object> params = new ArrayList<Object>();
		params.add(status);
		try {
			orderList = this.operateQuery(sql, params, Order2.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

}
