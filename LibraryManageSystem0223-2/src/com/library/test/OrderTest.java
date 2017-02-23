package com.library.test;

import java.util.List;

import com.library.dao.implement.OrderDaoImp;
import com.library.entity.Order2;

public class OrderTest {

	public static void main(String[] args) {
		OrderDaoImp od = new OrderDaoImp();
	//	boolean saveOrder = od.saveOrder(new Order(2,3,"2017-02-16",0));
		List<Order2> queryOrderByBname = od.queryOrderByBname("HeadFirstJava");
		System.out.println(queryOrderByBname);
	}

}
