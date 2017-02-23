package com.library.biz;

import java.util.List;

import com.library.entity.Order;
import com.library.entity.Order2;

public interface OrderBiz {
	public boolean addOrder(Order order);// 添加预约记录

	public boolean delOrder(int oid);// 删除预约记录

	public boolean modifyOrder(Order order);// 更新预约记录

	public List<Order2> queryAllOrders();// 查看所有的预约记录

	public List<Order2> queryOrdering();// 查询预约中的书籍

	public List<Order2> queryOrderByUname(String uname);// 管理员功能，查询指定用户的预约记录

	public List<Order2> queryOrderByBname(String bname);// 管理员功能，查询指定书籍名的预约记录

}
