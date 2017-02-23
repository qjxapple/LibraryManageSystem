package com.library.test;

import com.library.dao.implement.UserDaoImp;
import com.library.entity.User;

public class UserDaoImpTest {

	public static void main(String[] args) {
		UserDaoImp us = new UserDaoImp();
		//boolean flag = us.addUser(new User("hello1","hello1",0));
		//boolean flag = us.deleteUser(4);
		User flag = us.queryUser(null);
		System.out.println(flag);
	}

}
