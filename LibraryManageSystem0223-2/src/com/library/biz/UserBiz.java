package com.library.biz;

import java.util.List;

import com.library.entity.User;

public interface UserBiz {
	// 用户登录，返回的是一个用户对象（用户信息）
	public User login(User user);

	// 用户注册
	public int registUser(User user);

	public boolean addUser(User user);

	public boolean modifyUser(User user);

	public boolean deleteUser(int uid);

	public List<User> queryAllUsers();

	public User queryUserById(int uid);
}
