package com.library.dao;

import java.util.List;

import com.library.entity.Order;
import com.library.entity.Order2;

public interface OrderDao {
	public boolean saveOrder(Order order);// 保存order记录

	public boolean updateOrder(Order order);// 更新order记录

	public boolean deleteOrder(int oid);// 删除order记录

	public List<Order2> queryAllOrders();// 查询所有预约信息

	public Order queryOrderByUId(int uid);// 查询指定用户的预约记录

	public Order queryOrderByBid(int bid);// 根据书号查询预约记录

	public List<Order2> queryOrderByUname(String uname);// 查询指定用户名的预约记录

	public List<Order2> queryOrderByBname(String bname);// 查询指定书籍的预约记录

	public List<Order2> queryOrderByStatus(int status);// 通过书籍的预约状态查询书籍

	public List<Order> findAllOrders();// 查询所有order记录

}
