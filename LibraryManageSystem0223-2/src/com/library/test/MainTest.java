package com.library.test;

import com.library.entity.User;
import com.library.view.AdminMainView;
import com.library.view.LoginView;
import com.library.view.UserMainView;

public class MainTest {

	public static void main(String[] args) {
		//new LoginView();
		new AdminMainView(new User("admin","admin",1));
		//new UserMainView(new User("user", "user", 0));
		
		//new UserMainView(new User("hello","hello",0));
	}

}
