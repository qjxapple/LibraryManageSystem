package com.library.test;

import com.library.biz.imp.UserBizImp;
import com.library.entity.User;

public class UserBizTest {

	public static void main(String[] args) {
		UserBizImp ub = new UserBizImp();
		//User login = ub.login(new User("user","user",0));
		int registUser = ub.registUser(new User("haha","haha",0));
		System.out.println(registUser);
	}

}
