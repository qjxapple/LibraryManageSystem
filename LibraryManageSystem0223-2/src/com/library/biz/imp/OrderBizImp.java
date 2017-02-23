package com.library.biz.imp;

import java.util.List;

import com.library.biz.OrderBiz;
import com.library.dao.OrderDao;
import com.library.dao.implement.OrderDaoImp;
import com.library.entity.Order;
import com.library.entity.Order2;

public class OrderBizImp implements OrderBiz {
	private OrderDao orderDao = null;

	public OrderBizImp() {
		orderDao = new OrderDaoImp();
	}

	@Override
	public boolean addOrder(Order order) {
		return orderDao.saveOrder(order);
	}

	@Override
	public boolean delOrder(int oid) {

		return orderDao.deleteOrder(oid);
	}

	@Override
	public boolean modifyOrder(Order order) {
		// TODO Auto-generated method stub
		return orderDao.updateOrder(order);
	}

	@Override
	public List<Order2> queryAllOrders() {
		// TODO Auto-generated method stub
		return orderDao.queryAllOrders();
	}

	@Override
	public List<Order2> queryOrdering() {
		// TODO Auto-generated method stub
		return orderDao.queryOrderByStatus(1);
	}

	@Override
	public List<Order2> queryOrderByUname(String uname) {
		// TODO Auto-generated method stub
		return orderDao.queryOrderByUname(uname);
	}

	@Override
	public List<Order2> queryOrderByBname(String bname) {
		// TODO Auto-generated method stub
		return orderDao.queryOrderByBname(bname);
	}

}
